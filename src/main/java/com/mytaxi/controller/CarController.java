package com.mytaxi.controller;

import java.util.Collections;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.mytaxi.controller.mapper.CarMapper;
import com.mytaxi.controller.mapper.DriverMapper;
import com.mytaxi.dataaccessobject.CarRepository;
import com.mytaxi.datatransferobject.CarDTO;
import com.mytaxi.datatransferobject.DriverDTO;
import com.mytaxi.datatransferobject.DriverSearchDTO;
import com.mytaxi.domainobject.CarDO;
import com.mytaxi.domainobject.DriverDO;
import com.mytaxi.exception.CarAlreadyInUseException;
import com.mytaxi.exception.ConstraintsViolationException;
import com.mytaxi.exception.EntityNotFoundException;
import com.mytaxi.service.car.CarService;

/**
 * All operations with a driver will be routed by this controller.
 * <p/>
 */
@RestController
@RequestMapping("v1/cars")
public class CarController
{

	private final CarService carService;


	@Autowired
	public CarController(final CarService carService)
	{
		this.carService = carService;
	}

	@GetMapping
	public List<CarDTO> getAllCars(){
		List<CarDO> list=carService.findAll();
		return CarMapper.makeCarDTOList(list);
	}

	@GetMapping("/{carId}")
	public CarDTO getCar(@PathVariable long carId) throws EntityNotFoundException
	{
		return CarMapper.makeCarDTO(carService.find(carId));
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public CarDTO createCar(@Valid @RequestBody CarDTO driverDTO) throws ConstraintsViolationException
	{
		CarDO carDO = CarMapper.makeCarDO(driverDTO);
		return CarMapper.makeCarDTO(carService.create(carDO));
	}

	@PutMapping("/{carId}")
	public void update(
			@PathVariable int carId, @RequestParam String manufaturer)
					throws EntityNotFoundException
	{
		carService.update(carId,manufaturer);
	}

	@DeleteMapping("/{carId}")
	public void deleteDriver(@PathVariable long carId) throws EntityNotFoundException
	{
		carService.delete(carId);
	}


	@GetMapping("cars/{carId}")
	public List<CarDTO> searchCar(@PathVariable Long carId) throws EntityNotFoundException
	{
		List<CarDO> list= carService.serchCar(carId);
		return CarMapper.makeCarDTOList(list);
	}
}

