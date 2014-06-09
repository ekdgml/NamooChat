/*******************************************************************************
 * Copyright(c) 2012-2017 ACE Life. All rights reserved.
 * This software is the proprietary information of ACE Life.
 *******************************************************************************/
package com.vizend.town.reign.provider.logic;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.vizend.dream.util.enumtype.GuildType;
import com.vizend.dream.util.exception.VizendTownException;
import com.vizend.dream.util.type.IdNameList;
import com.vizend.town.reign.enrollment.domain.Enrollment;
import com.vizend.town.reign.enrollment.domain.Guild;
import com.vizend.town.reign.enrollment.facade.EnrollmentEntity;
import com.vizend.town.reign.enrollment.facade.GuildEntity;
import com.vizend.town.reign.people.domain.Citizen;
import com.vizend.town.reign.people.facade.CitizenEntity;
import com.vizend.town.reign.provider.facade.EnrollmentProvider;
import com.vizend.town.reign.provider.shared.dto.EnrollmentDto;
import com.vizend.town.reign.provider.shared.dto.GuildDto;

/**
 *
 * @author <a href="mailto:ysahn@nextree.co.kr">ysahn</a>
 * @since 2012. 12. 12.
 */
@Repository
public class EnrollmentProviderLogic implements EnrollmentProvider {
	//
	@Autowired
	private EnrollmentEntity enrollmentEntity;

	@Autowired
	private GuildEntity guildEntity;

	@Autowired
	private CitizenEntity citizenEntity;
	
	//--------------------------------------------------------------------------
	// methods

	public void registEnrollment(EnrollmentDto enrollmentDto) {
		//
		Guild guild = guildEntity.retrieve(enrollmentDto.getGuildId());
		Citizen citizen = citizenEntity.retrieve(enrollmentDto.getCitizenId());

		if (citizen == null) {
			throw new VizendTownException("No such a citizen:" + enrollmentDto.getCitizenId());
		}

		Enrollment enrollment = enrollmentDto.createDomain(
				citizen.getLoginId(),
				citizen.getLoginName());

		enrollmentEntity.create(enrollment);
		enrollment.setGuild(guild);
		guildEntity.update(guild);
	}

	public void removeEnrollmnet(String guildId, Long memberId) {
		//
		Enrollment enrollment = enrollmentEntity.retrieve(guildId, memberId);
		enrollmentEntity.delete(enrollment);
	}

	public IdNameList requestGuildIdNames(String citizenId) {
		//
		List<Enrollment> enrollments = enrollmentEntity.retrieveByCitizenId(citizenId);
		IdNameList idNames = IdNameList.getInstance();

		for (int i=0; i<enrollments.size(); i++) {
			Enrollment enrollment = enrollments.get(i);
			String id = enrollment.getGuild().getId();
			GuildType guildType = GuildType.typeOf(id);
			if (guildType == GuildType.OrgGroup) {
				continue;		// group is not real team 
			}
			String name = enrollment.getGuild().getGuildName();
			idNames.add(id, name);
		}

		return idNames; 
	}

	public IdNameList requestGuildIdNameRoles(String citizenId) {
		//
		List<Enrollment> enrollments = enrollmentEntity.retrieveByCitizenId(citizenId);
		IdNameList idNames = IdNameList.getInstance();

		for (int i=0; i<enrollments.size(); i++) {
			Enrollment enrollment = enrollments.get(i);
			String id = enrollment.getGuild().getId();
			GuildType guildType = GuildType.typeOf(id);
			if (guildType == GuildType.OrgGroup) {
				continue;		// group is not real team 
			}
			String name = enrollment.getGuild().getGuildName();
			String role = enrollment.getRoleName();
			idNames.add(id, (name + "(" + guildType.name() + "/" + role + ")"));
		}

		return idNames;
	}

	public void registGuild(GuildDto guildDto) {
		//
		Guild childGuild = guildDto.createDomain();
		guildEntity.create(childGuild);
	}
	
	public IdNameList requestGuildIdNames(String namePart, String guildId) {
		//
		String loginNameLike = namePart + "%";
		return enrollmentEntity.retrieveGuildIdNames(loginNameLike, guildId);
	}
}
