package com.vizend.town.reign.establish.logic;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.vizend.town.idgen.autogen.facade.TownIdGenerator;
import com.vizend.town.reign.establish.facade.NationFounder;
import com.vizend.town.reign.nation.facade.NationEntity;
import com.vizend.town.reign.people.facade.CastellanEntity;
import com.vizend.town.reign.people.facade.CivicAdminEntity;
import com.vizend.town.reign.people.facade.ServantEntity;

/**
 * Nation Founder
 *
 * @author <a href="mailto:tsong@nextree.co.kr">Song,Taegook</a>
 * @since 2012.12.03.
 */
@Service
@Transactional(propagation = Propagation.SUPPORTS)
public class NationFounderLogic implements NationFounder {
	//
	@Autowired
	private TownIdGenerator townIdGenerator;

	@Autowired
	private ServantEntity servantEntity;

	@Autowired
	private NationEntity nationEntity;

	@Autowired
	private CivicAdminEntity civicAdminEntity;

	@Autowired
	private CastellanEntity castellanEntity;

	//--------------------------------------------------------------------------
	// constructor

	public NationFounderLogic() {
	}

	//--------------------------------------------------------------------------
	// public methods

//	@Override
//	@Transactional(propagation = Propagation.REQUIRED)
//	public String foundNation(NationFoundationDto nationFoundationDto) {
//		//
//		String nationId = townIdGenerator.generateNationId();
//		Nation nation = nationFoundationDto.createNation(nationId);
//		nationEntity.create(nation);
//
//		String castellanId = townIdGenerator.generateCastellanId(nationId); 
//		Castellan castellan = nationFoundationDto.createCastellan(nationId, castellanId);
//		castellanEntity.create(castellan);
//
//		PublicServant servant = nationFoundationDto.createServant(nationId);
//		servant.setCastellan(castellan);
//		servant.setId(townIdGenerator.generateServantId(nationId)); 
//		servantEntity.create(servant);
//
//		CivicAdmin rootAdmin = new CivicAdmin(nationId, CivicUnit.Nation.name(), CivicAdminLevel.Primary.name());
//		rootAdmin.setServant(servant);
//		rootAdmin.setId(servant.getId(), nation.getId()); 
//		civicAdminEntity.create(rootAdmin);
//
//		return nation.getId();
//	}

//	@Override
//	@Transactional(propagation = Propagation.REQUIRED)
//	public String buildMetro(String nationId, MetroBuildDto metroBuildDto) {
//		// nation의 primary rootAdmin 한 명이 생성할 수 있음
//		Nation nation = nationEntity.retrieve(nationId);
//		
//		Metro metro = metroBuildDto.createMetro(nation);
//		String metroId = townIdGenerator.generateMetroId(nationId);
//		metro.setId(metroId);
//		metroEntity.create(metro);
//
//		CivicAdmin rootAdmin = civicAdminEntity.retrieveByCivicUnit(nationId, CivicAdminLevel.Primary, CivicUnit.Nation);
//		if (rootAdmin == null) {
//			throw new VizendTownException("No root(nation:" + nationId +") admin.");
//		}
//		
//		CivicAdmin metroAdmin = new CivicAdmin(metroId, CivicUnit.Metro.name());
//		metroAdmin.setServant(rootAdmin.getServant());
//		metroAdmin.setId(rootAdmin.getServant().getId(), metro.getId()); 
//		civicAdminEntity.create(metroAdmin);
//
//		return metro.getId();
//	}

//	@Override
//	@Transactional(propagation = Propagation.REQUIRED)
//	public String buildTown(String metroId, TownBuildDto townBuildDto) {
//		//
//		if(townBuildDto.isDefaultTown() && townEntity.existDefaultTown(metroId)){
//			throw new VizendTownException("Default Town already exists.");
//		}
//		Metro metro = metroEntity.retrieve(metroId);
//
//		Town town = townBuildDto.createTown(metro);
//		String townId = townIdGenerator.generateTownId(metroId);
//		town.setId(townId);
//		town.setDefaultTown(true);
//		townEntity.create(town);
//
//		PublicServant rootServant = servantEntity.retrievePrimaryAdminServant(metro.getNation().getId());
//		CivicAdmin townAdmin = new CivicAdmin(townId, CivicUnit.Town.name());
//		townAdmin.setServant(rootServant);
//		townAdmin.setId(rootServant.getId(), town.getId()); 
//		civicAdminEntity.create(townAdmin);
//
//		return town.getId();
//	}

//	@Override
//	@Transactional(propagation = Propagation.REQUIRED)
//	public String buildSingleTown(SingleTownBuildDto singleTownBuildDto) {
//		//
//		String nationId = townIdGenerator.generateNationId();
//		Nation nation = singleTownBuildDto.createNation(nationId);
//		
//		Metro metro = singleTownBuildDto.createMetro(nation);
//		String metroId = townIdGenerator.generateMetroId(nationId);
//		metro.setId(metroId);
//		metroEntity.create(metro);
//
//		Town town = singleTownBuildDto.createTown(metro);
//		String townId = townIdGenerator.generateTownId(metroId);
//		town.setId(townId);
//		townEntity.create(town);
//
//		nationEntity.create(nation);
//
//		String castellanId = townIdGenerator.generateCastellanId(nationId); 
//		CastellanCDto castellanCDto = singleTownBuildDto.createCastellan(nationId, castellanId);
//		Castellan castellan = castellanCDto.createDomain(nationId, castellanId);
//		castellan.setId(townIdGenerator.generateCastellanId(nationId));
//		castellanEntity.create(castellan);
//
//		PublicServant servant = singleTownBuildDto.createServant(nationId);
//		servant.setCastellan(castellan);
//		servant.setId(townIdGenerator.generateServantId(nationId)); 
//		servantEntity.create(servant);
//
//		CivicAdmin rootAdmin = new CivicAdmin(nationId, CivicUnit.Nation.name());
//		rootAdmin.setServant(servant);
//		rootAdmin.setId(servant.getId(), nation.getId()); 
//		civicAdminEntity.create(rootAdmin);
//
//		CivicAdmin metroAdmin = new CivicAdmin(metroId, CivicUnit.Metro.name());
//		metroAdmin.setServant(servant);
//		metroAdmin.setId(servant.getId(), metro.getId()); 
//		civicAdminEntity.create(metroAdmin);
//
//		CivicAdmin townAdmin = new CivicAdmin(townId, CivicUnit.Town.name());
//		townAdmin.setServant(servant);
//		townAdmin.setId(servant.getId(), town.getId()); 
//		civicAdminEntity.create(townAdmin);
//
//		return town.getId();
//	}
}