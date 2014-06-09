package com.vizend.town.reign.people.domain;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;

import com.vizend.dream.util.enumtype.EmployeeStatusType;
import com.vizend.dream.util.exception.VizendTownException;
import com.vizend.dream.util.type.NameValue;

/**
 * Some codes used in OrgGroup
 *
 * @author <a href="mailto:tsong@nextree.co.kr">Song,Taegook</a>
 * @since 2013. 1. 12.
 */
@Entity
public class StatusCode {
	//
	@Id
    @Column(name = "STATUSCODE_ID")
    private String id; 	// townId-C[seq]

    @Column(nullable = false)
    private String townId;

    @Column
	@Enumerated(EnumType.STRING)
    private EmployeeStatusType codeType;

    @Column
    private String codeValue;

    @Column
    private String codeName;

    @Column
    private String intro;		

    //--------------------------------------------------------------------------
    // 1. constructor

    public StatusCode() {
    }

    public StatusCode(String townId, EmployeeStatusType employeeStatusType, String codeValue, String codeName){
    	//
    	this.townId = townId;
    	this.codeType = employeeStatusType;
    	this.codeValue = codeValue;
    	this.codeName = codeName;
    }

    //--------------------------------------------------------------------------
    // 2. methods

    @Override
    public String toString() {
        //
        StringBuilder builder = new StringBuilder();

        builder.append("## StatusCode ##");
        builder.append("id:" + id);
        builder.append(", townId:" + townId);
        builder.append(", codeType:" + codeType.name());
        builder.append(", codeValue:" + codeValue);
        builder.append(", codeName:" + codeName);

        return builder.toString();
    }

    public void setValues(List<NameValue> pairs) {
        //
        for (NameValue attrValue : pairs) {
            String name = attrValue.name();

            if (name == null || name.equals("") || name.equals("null")) {
                continue;
            }

            String value = (String) attrValue.value();

            if (name.equals("townId")) {
                this.townId = value;
            } else if (name.equals("codeType")) {
                this.codeType = EmployeeStatusType.valueOf(value);
            } else if (name.equals("codeValue")) {
                this.codeValue = value;
            } else if (name.equals("codeName")) {
                this.codeName = value;
            } else {
                throw new VizendTownException("No such a attr name or can't modify the attr --> " + name);
            }
        }
    }

    //-------------------------------------------------------------------------
    // 3. getters/setters

 	public String getTownId() {
		return townId;
	}

	public void setTownId(String townId) {
		this.townId = townId;
	}

	public EmployeeStatusType getCodeType() {
		return codeType;
	}

	public void setCodeType(EmployeeStatusType codeType) {
		this.codeType = codeType;
	}

	public String getCodeValue() {
		return codeValue;
	}

	public void setCodeValue(String codeValue) {
		this.codeValue = codeValue;
	}

	public String getCodeName() {
		return codeName;
	}

	public void setCodeName(String codeName) {
		this.codeName = codeName;
	}

	public String getIntro() {
		return intro;
	}

	public void setIntro(String intro) {
		this.intro = intro;
	}
}
