package com.vizend.town.reign.enrollment.facade;

import java.util.List;

import com.vizend.dream.util.type.IdNameList;
import com.vizend.town.reign.enrollment.domain.Enrollment;

public interface EnrollmentEntity {
	//
	public Long create(Enrollment enrollment);
	public Enrollment retrieve(Long enrollmentOId);
	public Enrollment retrieve(String guildId, Long memberId);
	public List<Enrollment> retrieveByCitizenId(String citizenId);
	public List<Enrollment> retrieveByGuildId(String guildId);
	public IdNameList retrieveGuildIdNames(String loginNameLike, String guildId);
	public void update(Enrollment enrollment);
	public void delete(Enrollment enrollment);
}