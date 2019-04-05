package com.mytaxi.datatransferobject;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

public class ManufacturerDTO {
	 @JsonIgnore
	    private Long id;
	    
	    @NotNull(message = "Manufaturer  can not be null!")
	    private String  name;

		public Long getId() {
			return id;
		}

		public void setId(Long id) {
			this.id = id;
		}
		@JsonProperty
		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}
	    
}
