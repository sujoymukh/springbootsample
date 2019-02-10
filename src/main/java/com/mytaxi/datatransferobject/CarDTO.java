package com.mytaxi.datatransferobject;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.mytaxi.domainobject.Manufacturer;
import com.mytaxi.domainvalue.EngineType;
import com.mytaxi.domainvalue.GeoCoordinate;
import javax.validation.constraints.NotNull;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class CarDTO
{
    @JsonIgnore
    private Long id;
    
    @NotNull(message = "license plate can not be null!")
    private String licensePlate;
    
    @NotNull(message = "convertible  can not be null!")
    private Boolean convertible;
    
    @NotNull(message = "engine type  plate can not be null!")
    private EngineType engineType;
 
    private Float rating;
    private String  manufacturer;
	


 
    @JsonProperty
    public String getLicensePlate() {
		return licensePlate;
	}


	public void setLicensePlate(String licensePlate) {
		this.licensePlate = licensePlate;
	}

	 @JsonProperty
	public Boolean getConvertible() {
		return convertible;
	}


	public void setConvertible(Boolean convertible) {
		this.convertible = convertible;
	}

	 @JsonProperty
	public EngineType getEngineType() {
		return engineType;
	}


	public void setEngineType(EngineType engineType) {
		this.engineType = engineType;
	}


	public void setId(Long id) {
		this.id = id;
	}


	private CarDTO()
    {
    }

	 @JsonProperty
    public Float getRating() {
		return rating;
	}

	 @JsonProperty
	public void setRating(Float rating) {
		this.rating = rating;
	}


	public CarDTO(Long id, EngineType engineType,String licensePlate, Boolean convertible,String manufacture)
    {
        this.id = id;
        this.engineType=engineType;
        this.licensePlate=licensePlate;
        this.convertible=convertible;
        this.manufacturer=manufacture;
        
    }
	 public CarDTO(Long id,  Boolean convertible) {
			super();
			this.id = id;
		    this.licensePlate = licensePlate;
			this.convertible = convertible;
			this.engineType = EngineType.GAS;
			this.rating = rating;
		}


    public static CarDTOBuilder newBuilder()
    {
        return new CarDTOBuilder();
    }


    @JsonProperty
    public Long getId()
    {
        return id;
    }

    @JsonProperty
    public String getManufacturer() {
		return manufacturer;
	}

    @JsonProperty
	public void setManufacturer(String manufacturer) {
		this.manufacturer = manufacturer;
	}


	public static class CarDTOBuilder
    {
        private Long id;
        private String licensePlate;
        private Boolean convertible;
        private EngineType engineType;
        private Float rating;
        private String  manufacturer;
        
     

        public CarDTOBuilder setId(Long id)
        {
            this.id = id;
            return this;
        }


        public CarDTOBuilder setLicensePlate(String licensePlate)
        {
            this.licensePlate = licensePlate;
            return this;
        }


       public CarDTOBuilder setEngineType(EngineType engineType) {
			this.engineType = engineType;
			return this;
		}


	public CarDTOBuilder isConvertible(Boolean convertible)
        {
            this.convertible = convertible;
            return this;
        }




        public CarDTO createCarDTO()
        {
            return new CarDTO(id,engineType, licensePlate, convertible,manufacturer);
        }
        public String getManufacturer() {
    		return manufacturer;
    	}


    	public CarDTOBuilder setManufacturer(String manufacturer) {
    		this.manufacturer = manufacturer;
			return this;
    	}

    }
}
