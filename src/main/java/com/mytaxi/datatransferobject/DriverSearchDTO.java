package com.mytaxi.datatransferobject;

public class DriverSearchDTO {

	private DriverQuery driverQuery;
	
	private CarQuery carQuery;
	public DriverQuery getDriverQuery() {
		return driverQuery;
	}
	public void setDriverQuery(DriverQuery driverQuery) {
		this.driverQuery = driverQuery;
	}
	public CarQuery getCarQuery() {
		return carQuery;
	}
	public void setCarQuery(CarQuery carQuery) {
		this.carQuery = carQuery;
	}
	
	
}

