package com.vizend.town.reign.people.shared.dto;

import java.util.ArrayList;
import java.util.List;

import com.vizend.town.reign.people.domain.PublicServant;

public class ServantRDto extends ServantCDto {
    //
    private static final long serialVersionUID = -6140009117342710547L;

    private String id; 
    private String castellanId;
    private String photoId;
    private Long registDate;

    //--------------------------------------------------------------------------
    // 0. default public constructor

    public ServantRDto() {
    }

    //--------------------------------------------------------------------------
    // 1. private constructor

    private ServantRDto(PublicServant servant){
    	//
    	super(servant); 

    	this.id = servant.getId(); 
    	this.castellanId = servant.getCastellan().getId();
    	this.photoId = servant.getPhotoId();
    	this.registDate = servant.getRegistDate();
    }

    //--------------------------------------------------------------------------
    // 2. DTO creation from a domain object

    public static ServantRDto createDto(PublicServant servant) {
        //
        return new ServantRDto(servant);
    }

    public static List<ServantRDto> createDtos(List<PublicServant> publicServants) {
    	//
    	List<ServantRDto> dtos = new ArrayList<ServantRDto>();
    	for (PublicServant publicServant : publicServants) {
    		dtos.add(createDto(publicServant));
    	}

    	return dtos;
    }

    //--------------------------------------------------------------------------
    // 6. getter/setter

    public String getId() {
    	return id; 
    }
    
    public Long getRegistDate() {
        return registDate;
    }

	public String getCastellanId() {
		return castellanId;
	}

	public String getPhotoId() {
		return photoId;
	}

}
