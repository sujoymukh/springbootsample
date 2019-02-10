package com.mytaxi.service.driver;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.mytaxi.dataaccessobject.DriverRepository;
import com.mytaxi.datatransferobject.DriverSearchDTO;
import com.mytaxi.domainobject.DriverDO;
import com.mytaxi.domainvalue.ChooseCarAction;
import com.mytaxi.domainvalue.GeoCoordinate;
import com.mytaxi.domainvalue.OnlineStatus;
import com.mytaxi.exception.CarAlreadyInUseException;
import com.mytaxi.exception.ConstraintsViolationException;
import com.mytaxi.exception.EntityNotFoundException;

/**
 * Service to encapsulate the link between DAO and controller and to have business logic for some driver specific things.
 * <p/>
 */
@Service
public class DefaultDriverService implements DriverService
{

    private static final Logger LOG = LoggerFactory.getLogger(DefaultDriverService.class);
    private final DriverRepository driverRepository;
    public DefaultDriverService(final DriverRepository driverRepository)
    {
        this.driverRepository = driverRepository;
    }
    /**
     * Selects a driver by id.
     *
     * @param driverId
     * @return found driver
     * @throws EntityNotFoundException if no driver with the given id was found.
     */
    @Override
    public DriverDO find(Long driverId) throws EntityNotFoundException
    {
        return findDriverChecked(driverId);
    }
   /**
     * Creates a new driver.
     *
     * @param driverDO
     * @return
     * @throws ConstraintsViolationException if a driver already exists with the given username, ... .
     */
    @Override
    public DriverDO create(DriverDO driverDO) throws ConstraintsViolationException
    {
        DriverDO driver;
        try
        {
            driver = driverRepository.save(driverDO);
        }
        catch (DataIntegrityViolationException e)
        {
            LOG.warn("ConstraintsViolationException while creating a driver: {}", driverDO, e);
            throw new ConstraintsViolationException(e.getMessage());
        }
        return driver;
    }


    /**
     * Deletes an existing driver by id.
     *
     * @param driverId
     * @throws EntityNotFoundException if no driver with the given id was found.
     */
    @Override
    @Transactional
    public void delete(Long driverId) throws EntityNotFoundException
    {
        DriverDO driverDO = findDriverChecked(driverId);
        driverDO.setDeleted(true);
        
        driverRepository.save(driverDO);
    }


    /**
     * Update the location for a driver.
     *
     * @param driverId
     * @param longitude
     * @param latitude
     * @throws EntityNotFoundException
     */
    @Override
    @Transactional
    public void updateLocation(long driverId, double longitude, double latitude) throws EntityNotFoundException
    {
        DriverDO driverDO = findDriverChecked(driverId);
        driverDO.setCoordinate(new GeoCoordinate(latitude, longitude));
        // NOT CODED earlier , fixed it
        
        driverRepository.save(driverDO);
        
    }


    /**
     * Find all drivers by online state.
     *
     * @param onlineStatus
     */
    @Override
    public List<DriverDO> find(OnlineStatus onlineStatus)
    {
        return driverRepository.findByOnlineStatus(onlineStatus);
    }
   

    private DriverDO findDriverChecked(Long driverId) throws EntityNotFoundException
    {
        return driverRepository.findById(driverId)
            .orElseThrow(() -> new EntityNotFoundException("Could not find entity with id: " + driverId));
    }

    private DriverDO findCarChecked(Long carId) throws EntityNotFoundException
    {
        return driverRepository.findById(carId)
            .orElseThrow(() -> new EntityNotFoundException("Could not find entity with id: " + carId));
    }
    /**
     * Select and De-select car.
     ** @param action (select/de-select)
     * @param driverId
     * @param carID
     * 
     */

	@Override
	@Transactional(rollbackFor={CarAlreadyInUseException.class})
	public void selectCar(Long driverId,Long carId,ChooseCarAction action) throws EntityNotFoundException,CarAlreadyInUseException {
		try {
			int count;
		if(action==ChooseCarAction.SELECT) {
			 count = driverRepository.selectCar(driverId, carId);	
		}else {
			 count=driverRepository.deSelectCar(driverId,carId);
		}
		if(count==0) {
			LOG.info("Either the driver is offline or already selected a car");
		}
		}catch(DataIntegrityViolationException exception) {
			//TODO: HOw do I catch referntial integrity contraint violation, when the car doesn't exist to be assigned
			LOG.error("voilation "+exception.getMessage());
			throw new CarAlreadyInUseException("Car already in Use");
		}
		
	}

    /**
     * Search Driver all drivers.
     *
     * @param DriverSearchDTO
     */

	@Override
	public List<DriverDO> searchDriver(DriverSearchDTO driverSearchDTO) {
		// TODO Auto-generated method stub
		return driverRepository.searchDriver(driverSearchDTO);
	}


    /**
     * Search Driver all drivers based on online status.
     *
     * @param OnlineStatus
     */
	@Override
	public List<DriverDO> findOnline(OnlineStatus onlineStatus) {
		// TODO Auto-generated method stub
		return driverRepository.findByOnlineStatus(OnlineStatus.ONLINE);
	}}

