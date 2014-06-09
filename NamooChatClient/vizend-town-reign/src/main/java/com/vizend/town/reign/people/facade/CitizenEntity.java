package com.vizend.town.reign.people.facade;

import java.util.List;

import com.vizend.town.reign.people.domain.Citizen;
import com.vizend.town.reign.people.domainbackup.CitizenBackup;

/**
 * Citizen Entity Interface
 *
 * @author <a href="mailto:tsong@nextree.co.kr">Song,Taegook</a>
 * @since 2012. 10. 20.
 */
public interface CitizenEntity {
	//
	public String create(Citizen citizen);
	public void createBackup(CitizenBackup citizenBackup);
	public Citizen retrieve(String citizenId);
	public Citizen retrieveByLoginId(String loginId);
	public List<Citizen> retrieveByMetroId(String metroId, int offset, int pageSize);
	public List<Citizen> retrieveByIds(List<String> citizenIds);
	public void update(Citizen citizen);
	public void delete(Citizen citizen);
	public int countByMetroId(String metroId);
	public boolean authenticate(String loginId, String password);
	public boolean existByLoginId(String loginId);
}