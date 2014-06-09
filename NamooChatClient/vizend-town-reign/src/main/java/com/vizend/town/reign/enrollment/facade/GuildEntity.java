package com.vizend.town.reign.enrollment.facade;

import java.util.List;

import com.vizend.dream.util.enumtype.GuildType;
import com.vizend.town.reign.enrollment.domain.Guild;

public interface GuildEntity {
	//
	public String create(Guild guild);
	public Guild retrieve(String guildId);
	public List<Guild> retrieveByGuildType(GuildType guildType);
	public void update(Guild guild);
	public void delete(Guild guild);
	public boolean exists(String guildId);
}