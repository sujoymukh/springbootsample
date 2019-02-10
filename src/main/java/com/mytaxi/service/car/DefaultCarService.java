package com.mytaxi.service.car;

import java.util.Collections;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mytaxi.dataaccessobject.CarRepository;
import com.mytaxi.datatransferobject.CarDTO;
import com.mytaxi.domainobject.CarDO;
import com.mytaxi.domainobject.DriverDO;
import com.mytaxi.domainvalue.ChooseCarAction;
import com.mytaxi.domainvalue.GeoCoordinate;
import com.mytaxi.exception.ConstraintsViolationException;
import com.mytaxi.exception.EntityNotFoundException;

/**
 * Service to encapsulate the link between DAO and controller and to have
 * business logic for Car related.
 * <p/>
 */
@Service
public class DefaultCarService implements CarService {

	private static final Logger LOG = LoggerFactory.getLogger(DefaultCarService.class);

	private final CarRepository carRepository;

	public DefaultCarService(final CarRepository carRepository) {
		this.carRepository = carRepository;
	}
	 /**
     * Selects a car  by id.
     *
     * @param carId
     * @return found car
     * @throws EntityNotFoundException if no car with the given id was found.
     */
	@Override
	public CarDO find(Long carId) throws EntityNotFoundException {
		return findCarChecked(carId);
	}

	@Override
	public CarDO create(CarDO carDO) throws ConstraintsViolationException {

		CarDO car;
		try {
			car = carRepository.save(carDO);
		} catch (DataIntegrityViolationException e) {
			LOG.warn("ConstraintsViolationException while creating a car: {}", carDO, e);
			throw new ConstraintsViolationException(e.getMessage());
		}
		return car;
	}

	@Override
	@Transactional
	public void update(long carId, String manufaturer) throws EntityNotFoundException {
		CarDO carDO = findCarChecked(carId);
		carDO.setManufacturer(manufaturer);
	}

	@Override
	@Transactional
	public void update(long carId) throws EntityNotFoundException {
		CarDO CarDO = findCarChecked(carId);
		// CarDO.setCoordinate(new GeoCoordinate(latitude, longitude));
	}

	private CarDO findCarChecked(Long carId) throws EntityNotFoundException {
		return carRepository.findById(carId)
				.orElseThrow(() -> new EntityNotFoundException("Could not find entity with id: " + carId));
	}

	@Override
	public List<CarDO> findAll() {
		return carRepository.findAll();
	}
	/*
	 * @Override public List<CarDO> delete(long id) { return
	 * carRepository.deleteAll(); }
	 */

	@Override
	public void delete(long id) throws EntityNotFoundException {
		// TODO Auto-generated method stub
		CarDO carDO = findCarChecked(id);
		carDO.setDeleted(true);
		carRepository.save(carDO);

	}

	/*
	 * @Override public List<CarDO> search(long id) { return
	 * carRepository.searchCar(); }
	 */
	@Override
	public List<CarDO> serchCar(long carId) {
		// TODO Auto-generated method stub
		return carRepository.searchCar((carId));
		
		
	}

}
