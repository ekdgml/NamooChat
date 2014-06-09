package com.vizend.town.reign.people.shared.dto;

import java.io.Serializable;

import com.vizend.town.reign.people.domain.Castellan;
import com.vizend.town.reign.people.domain.Citizen;

/**
 * Citizen creatioal DTO 
 *
 * @author <a href="mailto:tsong@nextree.co.kr">Song,Taegook</a>
 * @since 2013. 4. 12.
 */
public class CitizenCDto implements Serializable {
	//
	private static final long serialVersionUID = 8208174773973565059L;

	private String loginId;
	private String loginName; 
	private String password;
	private String email; 

	//--------------------------------------------------------------------------
	// 0. default public constructor

    public CitizenCDto() {
    }

	//--------------------------------------------------------------------------
	// 1. private constructor

	public CitizenCDto(
			String loginId, 
			String loginName, 
			String password,
			String email) {
		//
		this.loginId = loginId;
		this.loginName = loginName; 
		this.password = password;
		this.email = email; 
	}

	protected CitizenCDto(Citizen citizen) {
		//
		this.loginId = citizen.getLoginId();
		this.loginName = citizen.getLoginName(); 
		this.password = citizen.getPassword();
		this.email = citizen.getEmail();
	}

    //--------------------------------------------------------------------------
    // 2. Domain object creation from this DTO


	public Citizen createDomain(Castellan castellan, String metroId, String citizenId) {
        //
    	Citizen citizen = new Citizen(
    			castellan,
    			metroId,
    			citizenId, 
    			loginId);
    	
    	return citizen;
    }

    //--------------------------------------------------------------------------
    // 4. methods
	
//	public static List<CitizenCDto> readRowDatas(String metroId, List<ExcelRowData> rowDatas) {
//		//
//		List<CitizenCDto> citizenDtos = new ArrayList<CitizenCDto>();
//
//		for(ExcelRowData rowData : rowDatas){
//			List<NameValue> columnDatas = rowData.getValues();
//			String email = NameValue.getValueStr("email", columnDatas);
//			String loginId = NameValue.getValueStr("loginId", columnDatas);
//			String password = NameValue.getValueStr("password", columnDatas);
//			String phoneNumber = NameValue.getValueStr("phoneNumber", columnDatas);
//			String loginName = NameValue.getValueStr("loginName", columnDatas);
//
//			CitizenCDto cdto = new CitizenCDto(metroId, loginId, password);
//			cdto.setEmail(email);
//			cdto.setPhoneNumber(phoneNumber);
//			cdto.setLoginName(loginName);
//			citizenDtos.add(cdto);
//		}
//
//		return citizenDtos;
//	}

    //--------------------------------------------------------------------------
    // 5. getter/setter

    public String getLoginId() {
    	return loginId;
    }

    public String getLoginName() {
    	return loginName; 
    }
    
    public String getPassword() {
    	return password;
    }

	public void setLoginId(String loginId) {
		this.loginId = loginId;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}
}
