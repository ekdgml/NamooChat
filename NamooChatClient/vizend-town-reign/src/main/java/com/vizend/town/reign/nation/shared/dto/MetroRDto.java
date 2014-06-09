package com.vizend.town.reign.nation.shared.dto;

import java.util.ArrayList;
import java.util.List;

import com.vizend.dream.util.enumtype.PidProperty;
import com.vizend.town.reign.nation.domain.Metro;

/**
 * Metro read DTO
 * 
 * @author <a href="mailto:tsong@nextree.co.kr">Song,Taegook</a>
 * @since 2012. 10. 20.
 */
public class MetroRDto extends MetroCDto {
	//
	private static final long serialVersionUID = -8309540902046791488L;

	private String nationId;
	private String metroId;
    private String intro;
    private String flagImageId; // Metro flag id
    private Long registDate;

	private PidProperty pidProperty;

    // policy

    //--------------------------------------------------------------------------
    // 0. default public constructor

    public MetroRDto() {}

    //--------------------------------------------------------------------------
    // 1. private constructor

    private MetroRDto(Metro metro) {
        //
    	super(metro); 
    	this.nationId = metro.getNation().getId();
    	this.metroId = metro.getId();
    	this.intro = metro.getIntro();
    	this.flagImageId = metro.getFlagImageId();
    	this.registDate = metro.getRegistDate();
    	this.pidProperty = metro.getPolicy().getPidProperty();
    }

    //--------------------------------------------------------------------------
    // 2. DTO creation from a domain object

    public static MetroRDto createRDto(Metro metro) {
        //
        return new MetroRDto(metro);
    }

    public static List<MetroRDto> createDtos(List<Metro> metros){
    	//
    	List<MetroRDto> dtos = new ArrayList<MetroRDto>();
    	for(Metro metro : metros){
    		dtos.add(createRDto(metro));
    	}

    	return dtos;
    }

    //--------------------------------------------------------------------------
    // 3. check and prepare the collections

    //--------------------------------------------------------------------------
    // 4. getter/setter

    public String getNationId() {
    	return nationId;
    }

	public String getMetroId() {
		return metroId;
	}

	public String getIntro() {
		return intro;
	}

	public String getFlagImageId() {
		return flagImageId;
	}

	public Long getRegistDate() {
		return registDate;
	}

	public PidProperty getPidProperty() {
		return pidProperty;
	}
}
