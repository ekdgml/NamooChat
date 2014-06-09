package com.vizend.town.reign.people.shared.dto;

import java.io.Serializable;

import com.vizend.town.reign.people.domain.Castellan;
import com.vizend.town.reign.people.domain.PublicServant;

public class ServantCDto implements Serializable {
    //
    private static final long serialVersionUID = -6140009117342710547L;

    private String nationId;
    private String loginEmail;
    private String password;
    private String loginName;
    private String phoneNumber;

    //--------------------------------------------------------------------------
    // 0. default public constructor

    public ServantCDto() {
    }

    //--------------------------------------------------------------------------
    // 1. public constructor

    public ServantCDto(
    		String nationId,
    		String loginName,
    		String loginEmail,
    		String password,
    		String phoneNumber){
    	//
    	this.nationId = nationId;
    	this.loginName = loginName;
    	this.loginEmail = loginEmail;
    	this.password = password;
    	this.phoneNumber = phoneNumber;
    }

    protected ServantCDto(PublicServant servant) {
    	//
    	this.nationId = servant.getNationId();
    	this.loginName = servant.getLoginName();
    	this.loginEmail = servant.getLoginEmail();
    	this.password = servant.getPassword();
    	this.phoneNumber = servant.getPhoneNumber();
    }

    //--------------------------------------------------------------------------
    // 2. Domain object creation from this DTO

    public PublicServant createDomain() {
    	//
    	PublicServant servant = new PublicServant(nationId, loginName, loginEmail, password);
    	servant.setPhoneNumber(phoneNumber);

    	return servant;
    }

	public Castellan createCastellan() {
		//
		return new Castellan(nationId, loginName, loginEmail, phoneNumber);
	}
    //--------------------------------------------------------------------------
    // 3. getter/setter

    public String getLoginEmail() {
        return loginEmail;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

}
