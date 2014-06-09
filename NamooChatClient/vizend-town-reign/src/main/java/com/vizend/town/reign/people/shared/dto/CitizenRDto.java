package com.vizend.town.reign.people.shared.dto;

import java.util.ArrayList;
import java.util.List;

import com.vizend.town.reign.people.domain.Citizen;


/**
 *
 * @author <a href="mailto:ysahn@nextree.co.kr">ysahn</a>
 * @since 2012. 11. 29.
 */
public class CitizenRDto extends CitizenCDto{
	//
	private static final long serialVersionUID = 8668518973762390363L;

	private String id;
	private String phoneNumber; 
    private Long registDate;
    private String photoId;

    //--------------------------------------------------------------------------
    // 0. default public constructor.

    public CitizenRDto(){
    }

    //--------------------------------------------------------------------------
    // 1. private constructor.

    private CitizenRDto(Citizen citizen){
    	//
    	super(citizen);
    	
    	this.id = citizen.getId();
    	this.phoneNumber = citizen.getPhoneNumber(); 
		this.registDate = citizen.getRegistDate();
		this.photoId = citizen.getPhotoId();
    }

    //--------------------------------------------------------------------------
    // 2. DTO createn from a domain object

    public static CitizenRDto createDto(Citizen citizen) {
    	//
    	return new CitizenRDto(citizen);
    }

    public static List<CitizenRDto> createDtos(List<Citizen> citizens) {
        //
        List<CitizenRDto> dtos = new ArrayList<CitizenRDto>();
        for (Citizen citizen : citizens) {
        	dtos.add(createDto(citizen));
        }

        return dtos;
    }

    //--------------------------------------------------------------------------
    // 3. check and prepare the collections

    //--------------------------------------------------------------------------
    // 4. getter/setter

    public Long getRegistDate() {
    	return registDate;
    }

    public String getId() {
		return id;
	}

    public String getPhotoId() {
    	return photoId;
    }

	public String getPhoneNumber() {
		return phoneNumber;
	}
}
