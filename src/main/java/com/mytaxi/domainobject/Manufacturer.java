package com.mytaxi.domainobject;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;

public class Manufacturer {

    @Id
    @GeneratedValue
    private Long id;
    @Column(nullable = false)
    @NotNull(message = "Manufacturee can not null")
    private String name;

    private Set<CarDO> cars;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	@OneToMany(mappedBy = "carManufatuture", cascade = CascadeType.ALL)
    public Set<CarDO> getCars() {
        return cars;
    }



    public void setCars(Set<CarDO> cars) {
        this.cars = cars;
    }
}
