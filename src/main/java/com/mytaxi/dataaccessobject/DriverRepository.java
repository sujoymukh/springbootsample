package com.mytaxi.dataaccessobject;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.mytaxi.datatransferobject.DriverDTO;
import com.mytaxi.datatransferobject.DriverQuery;
import com.mytaxi.datatransferobject.DriverSearchDTO;
import com.mytaxi.domainobject.CarDO;
import com.mytaxi.domainobject.DriverDO;
import com.mytaxi.domainvalue.OnlineStatus;

/**
 * Database Access Object for driver table.
 * <p/>
 */
public interface DriverRepository extends CrudRepository<DriverDO, Long>
{

    List<DriverDO> findByOnlineStatus(OnlineStatus onlineStatus);
    
    @Modifying
    @Query("UPDATE DriverDO c  SET carDO.id = :carId WHERE c.onlineStatus = 'ONLINE' AND Id=:driverId and carDO.id = null")
    int selectCar(@Param("driverId") Long driverId, @Param("carId") Long carId);
    
    @Modifying
    @Query("UPDATE DriverDO c  SET carDO.id = null WHERE c.onlineStatus = 'ONLINE' AND Id=:driverId and carDO.id = :carId")
    int deSelectCar(@Param("driverId") Long driverId, @Param("carId") Long carId);
   
    
    @Query("SELECT new  com.mytaxi.domainobject.DriverDO(d.username,d.coordinate,d.onlineStatus) FROM DriverDO d inner JOIN  d.carDO  c where " +
    		 "d.onlineStatus=:#{#driverSearchDTO.driverQuery.onlineStatus}" +
    		" or d.username=:#{#driverSearchDTO.driverQuery.username}" +
    		" or c.licensePlate=:#{#driverSearchDTO.carQuery.licensePlate}" +
    		" or c.rating=:#{#driverSearchDTO.carQuery.rating}") 
    		
    List<DriverDO> searchDriver(@Param("driverSearchDTO") DriverSearchDTO driverSearchDTO);
	

    
    
}
