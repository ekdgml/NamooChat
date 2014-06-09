package com.vizend.town.reign.people.shared.dto;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.vizend.town.reign.people.domain.Castellan;

/**
 * Castellan read DTO
 *
 * @author <a href="mailto:tsong@nextree.co.kr">Song,Taegook</a>
 * @since 2012. 5. 12.
 */
public class CastellanRDto extends CastellanCDto implements Serializable {
    //
	private static final long serialVersionUID = 9128443942581288360L;

    private String id;
    private String nationId; 
    private String photoId;
    private Long registDate;

	//--------------------------------------------------------------------------
	// 0. default public constructor

    public CastellanRDto() {
    }

	//--------------------------------------------------------------------------
	// 1. private constructor

	private CastellanRDto(Castellan castellan) {
		//
		super(castellan);

		this.id = castellan.getId();
		this.nationId = castellan.getNationId(); 
		this.photoId = castellan.getPhotoId();
		this.registDate = castellan.getRegistDate();
	}

    //--------------------------------------------------------------------------
    // 2. DTO creation form a domain object

    public static CastellanRDto createRDto(Castellan castellan) {
        //
        return new CastellanRDto(castellan);
    }

    public static List<CastellanRDto> createDtos(List<Castellan> castellans) {
        //
        List<CastellanRDto> dtos = new ArrayList<CastellanRDto>();
        for (Castellan castellan : castellans) {
        	dtos.add(createRDto(castellan));
        }

        return dtos;
    }

    //--------------------------------------------------------------------------
    // 3. check and prepare the collections
 
    //--------------------------------------------------------------------------
    // 4. getter only in RDto

    public String getNationId() {
    	return nationId; 
    }
    
	public String getId() {
		return id;
	}

	public String getPhotoId() {
		return photoId;
	}

	public Long getRegistDate() {
		return registDate;
	}
}