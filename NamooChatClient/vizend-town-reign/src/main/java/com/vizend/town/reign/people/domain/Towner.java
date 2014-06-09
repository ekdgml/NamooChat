package com.vizend.town.reign.people.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

/**
 * Towner who has a residential permit on town
 * [rule] No update, add and remove only
 *
 * @author <a href="mailto:tsong@nextree.co.kr">Taegook,Song</a>
 * @since 2012. 12. 08.
 */
@Entity
public class Towner {
    //
    @Id
    @Column(name = "TOWNER_ID")		
    private String id;	// townId-citizenId

    @Column
    private String townId;

    @Column
    private String positionCode;

    @Column
    private String empStatusCode;

    @Column(nullable = false)
    private Long registDate;

    @ManyToOne 
    private Citizen citizen; 
    
    //--------------------------------------------------------------------------
    // constructor

    public Towner() {
    }

    public Towner(Citizen citizen, String townId){
    	//
    	this.id = townId + "-" + citizen.getId(); 
    	this.townId = townId;
    	this.registDate = System.currentTimeMillis(); 
    	this.citizen = citizen;
    }

    //--------------------------------------------------------------------------
    // methods
    
    @Override
    public String toString(){
    	//
    	StringBuilder builder = new StringBuilder(200);
    	
    	builder.append("## Towner ##");
    	builder.append(", id:"+id);
    	builder.append(", townId:"+townId);
    	builder.append(", positionCode:"+positionCode);
    	builder.append(", empStatusCode:"+empStatusCode);
    	builder.append(", registDate:"+registDate);
    	
    	return builder.toString();
    }

    public String getId() {
    	return id; 
    }
    
	public Long getRegistDate() {
		return registDate;
	}

	public String getTownId() {
		return townId;
	}

	public void setTownId(String townId) {
		this.townId = townId;
	}

	public String getPositionCode() {
		return positionCode;
	}

	public void setPositionCode(String positionCode) {
		this.positionCode = positionCode;
	}

	public String getEmpStatusCode() {
		return empStatusCode;
	}

	public void setEmpStatusCode(String empStatusCode) {
		this.empStatusCode = empStatusCode;
	}

	public Citizen getCitizen() {
		return citizen;
	}

	public void setCitizen(Citizen citizen) {
		this.citizen = citizen;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setRegistDate(Long registDate) {
		this.registDate = registDate;
	}

}