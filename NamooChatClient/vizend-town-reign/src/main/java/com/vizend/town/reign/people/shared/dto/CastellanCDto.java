package com.vizend.town.reign.people.shared.dto;


import java.io.Serializable;

import com.vizend.town.reign.people.domain.Castellan;

/**
 * Castellan create DTO 
 *
 * @author <a href="mailto:tsong@nextree.co.kr">Song,Taegook</a>
 * @since 2012. 5. 12.
 */
public class CastellanCDto implements Serializable {
    //
	private static final long serialVersionUID = 7041840702035905559L;

    private String authEmail;
    private String displayName;
    
    // opitonal 
    private String authPhone;

	//--------------------------------------------------------------------------
	// 0. default public constructor

    public CastellanCDto() {
    }

	//--------------------------------------------------------------------------
	// 1. public constructor
	public CastellanCDto(
			String displayName,
			String authEmail) {
		//
		this.displayName = displayName;
		this.authEmail = authEmail;
	}

	public CastellanCDto(Castellan castellan) {
		//
		this.displayName = castellan.getDisplayName();
		this.authEmail = castellan.getAuthEmail();
		this.authPhone = castellan.getAuthPhone(); 
	}

    //--------------------------------------------------------------------------
    // 2. Domain object creation from this DTO

    public Castellan createDomain(String nationId, String castellanId) {
        //
    	Castellan castellan = new Castellan(nationId, castellanId, displayName, authEmail);
    	castellan.setAuthPhone(authPhone); 
    	
    	return castellan; 
    }

    //--------------------------------------------------------------------------
    // 3. getter/setter

	public String getAuthEmail() {
		return authEmail;
	}

	public String getAuthPhone() {
		return authPhone;
	}

	public String getDisplayName() {
		return displayName;
	}
}