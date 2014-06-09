package com.vizend.town.reign.ruling.fillup;

import com.vizend.town.reign.people.domain.Castellan;
import com.vizend.town.reign.people.domain.Citizen;
import com.vizend.town.reign.people.domain.Towner;

/**
 *
 * @author <a href="mailto:vaslife@gmail.com">ahn, young-su</a>
 * @since Apr 23, 2013
 */
public class ResidenceParcel {
	//
	private NationParcel nationParcel;
	private Castellan castellan;
	private Citizen citizen;
	private Towner towner;

	public ResidenceParcel(
			NationParcel nationParcel, 
			Castellan castellan,
			Citizen citizen, 
			Towner towner) {
		// 
		this.nationParcel = nationParcel;
		this.castellan = castellan;
		this.citizen = citizen;
		this.towner = towner;
	}

	public NationParcel getNationParcel() {
		return nationParcel;
	}

	public Castellan getCastellan() {
		return castellan;
	}

	public Citizen getCitizen() {
		return citizen;
	}

	public Towner getTowner() {
		return towner;
	}

}
