package com.vizend.town.reign.provider.shared.dto;

import com.vizend.dream.util.enumtype.GuildType;
import com.vizend.town.reign.enrollment.domain.Guild;

public class GuildDto {
	//
	private String townId; 
	private String guildId;
	private String guildName; 
	private GuildType guildType; 

	//--------------------------------------------------------------------------
	// constructor

	public GuildDto() {
	}

	public GuildDto(String townId, String guildId, String guildName, GuildType guildType, String parentId) {
		//
		this.townId = townId; 
		this.guildId = guildId; 
		this.guildName = guildName; 
		this.guildType = guildType; 
	}

	public Guild createDomain() {
		// 
		Guild guild = new Guild(townId, guildId, guildName, guildType);
		
		return guild; 
	}
	
	//--------------------------------------------------------------------------
	// methods

	public String getGuildId() {
		return guildId;
	}

	public void setGuildId(String guildId) {
		this.guildId = guildId;
	}

	public GuildType getGuildType() {
		return guildType;
	}

	public void setGuildType(GuildType guildType) {
		this.guildType = guildType;
	}

	public String getGuildName() {
		return guildName;
	}

	public void setGuildName(String guildName) {
		this.guildName = guildName;
	}
}