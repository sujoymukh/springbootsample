package com.mytaxi.service.car;

import java.util.List;
import com.mytaxi.domainobject.CarDO;
import com.mytaxi.exception.ConstraintsViolationException;
import com.mytaxi.exception.EntityNotFoundException;

public interface CarService
{

	List<CarDO> findAll();
	CarDO find(Long carId) throws EntityNotFoundException;
	CarDO create(CarDO carDO) throws ConstraintsViolationException;
	void update(long carId) throws EntityNotFoundException;
	void update(long carId, String mantufaturer) throws EntityNotFoundException;
	void delete(long id) throws EntityNotFoundException;
	List<CarDO> serchCar(long id);


}
