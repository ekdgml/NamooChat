package com.vizend.town.reign.nation.shared.dto;

import com.vizend.town.reign.nation.domain.Nation;

/**
 *
 * @author <a href="mailto:jinason@nextree.co.kr">Son JinAh</a>
 * @since 2012. 10. 20.
 */
public class NationRDto extends NationCDto {
	//
	private static final long serialVersionUID = -793615252627800283L;

	private String nationId;
	private String intro;
	private String flagImageId; // for national flag
	private Long registDate;

	//--------------------------------------------------------------------------
	// 0. default public constructor

	public NationRDto() {
	}

	//--------------------------------------------------------------------------
	// 1. private constructor

	private NationRDto(Nation nation) {
		//
		this.intro = nation.getIntro();
		this.flagImageId = nation.getFlagImageId();
		this.nationId = nation.getId();
		this.registDate = nation.getRegistDate();
	}

	//--------------------------------------------------------------------------
	// 2. DTO creation from some attrs

	public static NationRDto createDto(Nation nation) {
		//
		return new NationRDto(nation);
	}

	//--------------------------------------------------------------------------
	// 3. getter/setter

	public String getIntro() {
		return intro;
	}

	public String getFlagImageId() {
		return flagImageId;
	}

	public String getNationId() {
		return nationId;
	}

	public Long getRegistDate() {
		return registDate;
	}
}
