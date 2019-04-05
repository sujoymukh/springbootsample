package com.mytaxi.domainobject;
import java.time.ZonedDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import org.springframework.format.annotation.DateTimeFormat;
import com.mytaxi.domainvalue.EngineType;

@Entity
@Table(
		name = "car"

		)
public class CarDO
{

	@Id
	@GeneratedValue
	private Long id;

	@Column(nullable = false)
	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
	private ZonedDateTime dateCreated = ZonedDateTime.now();

	@Column
	private String licensePlate;

	@Column
	private Boolean convertible;

	@Enumerated(EnumType.STRING)
	@Column
	private EngineType  engineType ;

	@Column
	private Float  rating ;

	@Column
	private String manufacturer;
	@Column()
	private Boolean deleted = false;

	public Boolean getDeleted() {
		return deleted;
	}



	public void setDeleted(Boolean deleted) {
		this.deleted = deleted;
	}



	public CarDO(Long id,  Boolean convertible) {
		super();
		this.id = id;
		//this.licensePlate = licensePlate;
		this.convertible = convertible;
		//this.engineType = EngineType.GAS;
		//this.rating = rating;
		//this.manufacturer=manufacturer;
	}



	public CarDO() {
		super();
	}



	public Long getId()
	{
		return id;
	}


	public void setId(Long id)
	{
		this.id = id;
	}




	public CarDO(Long id, ZonedDateTime dateCreated,
			@NotNull(message = "license_plate can not be null!") String licensePlate,
			@NotNull(message = "convertible") @NotNull(message = "convertible") Boolean convertible,
			ZonedDateTime dateCoordinateUpdated, EngineType engineType, Float rating,Boolean deleted) {
		super();
		this.id = id;
		this.dateCreated = dateCreated;
		this.licensePlate = licensePlate;
		this.convertible = convertible;
		this.engineType = EngineType.GAS;
		this.rating = rating;
		this.deleted=deleted;
	}






	public ZonedDateTime getDateCreated() {
		return dateCreated;
	}


	public void setDateCreated(ZonedDateTime dateCreated) {
		this.dateCreated = dateCreated;
	}


	public String getLicensePlate() {
		return licensePlate;
	}


	public void setLicensePlate(String licensePlate) {
		this.licensePlate = licensePlate;
	}


	public String getManufacturer() {
		return manufacturer;
	}



	public void setManufacturer(String manufacturer) {
		this.manufacturer = manufacturer;
	}



	public Boolean getConvertible() {
		return convertible;
	}


	public void setConvertible(@NotNull(message = "convertible") Boolean convertible) {
		this.convertible = convertible;
	}


	public EngineType getEngineType() {
		return engineType;
	}


	public void setEngineType(EngineType engineType) {
		this.engineType = engineType;
	}


	public Float getRating() {
		return rating;
	}


	public void setRating(Float rating) {
		this.rating = rating;
	}


	@OneToOne(mappedBy="carDO")

	private DriverDO driverDo;

	public DriverDO getDriverDo() {
		return driverDo;
	}



	public void setDriverDo(DriverDO driverDo) {
		this.driverDo = driverDo;
	}




}
