package com.vizend.town.reign.shared;

public enum ProvisionMode {
	//
	InternalService("InternalService"), 	// example, www.nextree.co.kr
	PublicService("PublicService"),		// example, www.auction.co.kr
	PlatformService("PlatformService");		// example, social.vizend.com

	private String krName;

	ProvisionMode(String krName){
		//
		this.krName = krName;
	}

	public String getKrName(){
		return this.krName;
	}
}