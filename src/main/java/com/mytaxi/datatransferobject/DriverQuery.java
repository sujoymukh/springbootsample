package com.mytaxi.datatransferobject;

import javax.validation.constraints.NotNull;

import com.mytaxi.domainvalue.OnlineStatus;

public class DriverQuery{
	private String username;
	@NotNull
	private OnlineStatus onlineStatus;
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public OnlineStatus getOnlineStatus() {
		return onlineStatus;
	}
	public void setOnlineStatus(OnlineStatus onlineStatus) {
		this.onlineStatus = onlineStatus;
	}
	
}