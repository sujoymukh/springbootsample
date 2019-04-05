package com.mytaxi.dataaccessobject;

import com.mytaxi.domainobject.CarDO;
import com.mytaxi.domainvalue.OnlineStatus;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

/**
 * Database Access Object for driver table.
 * <p/>
 */
public interface CarRepository extends CrudRepository<CarDO, Long>
{

   List<CarDO> findAll();
   @Query("Select c from  CarDO c   WHERE   c.id = :carId")
   List<CarDO> searchCar(@Param("carId") Long carId);
   
}
