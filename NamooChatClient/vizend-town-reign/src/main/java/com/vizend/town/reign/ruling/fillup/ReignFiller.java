package com.vizend.town.reign.ruling.fillup;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.vizend.town.idgen.autogen.facade.TownIdGenerator;
import com.vizend.town.reign.nation.domain.Metro;
import com.vizend.town.reign.nation.domain.Nation;
import com.vizend.town.reign.nation.domain.Town;
import com.vizend.town.reign.nation.facade.MetroEntity;
import com.vizend.town.reign.nation.facade.NationEntity;
import com.vizend.town.reign.nation.facade.TownEntity;
import com.vizend.town.reign.nation.shared.helper.NationHelper;
import com.vizend.town.reign.people.domain.Castellan;
import com.vizend.town.reign.people.domain.Citizen;
import com.vizend.town.reign.people.domain.Towner;
import com.vizend.town.reign.people.facade.CastellanEntity;
import com.vizend.town.reign.people.facade.CitizenEntity;
import com.vizend.town.reign.people.facade.TownerEntity;
import com.vizend.town.reign.people.shared.helper.PeopleHelper;

/**
 *
 * @author <a href="mailto:vaslife@gmail.com">ahn, young-su</a>
 * @since Apr 23, 2013
 */
@Repository
@Transactional(propagation = Propagation.SUPPORTS)
public class ReignFiller {
	//
	@Autowired private TownerEntity townerEntity;
	@Autowired private TownEntity townEntity;
	@Autowired private TownIdGenerator idGen;
	@Autowired private CastellanEntity castellanEntity;
	@Autowired private CitizenEntity citizenEntity;
	@Autowired private NationEntity nationEntity;
	@Autowired private MetroEntity metroEntity;
	
	//--------------------------------------------------------------------------
	
	public NationParcel prepareNationParcel(){
		//
		String nationId = idGen.generateNationId();
		Nation nation = NationHelper.getSampleNation(nationId);
		nationEntity.create(nation);
		
		String metroId = idGen.generateMetroId(nation.getId());
		Metro metro = NationHelper.getSampleMetro(nation, metroId, 1);
		metroEntity.create(metro);

		String townId = idGen.generateTownId(metroId);
		Town town = NationHelper.getSampleTown(metro, townId, 1);
		townEntity.create(town);
		
		NationParcel nationParcel = new NationParcel(nation, metro, town);
		
		return nationParcel;
	}
	
	public ResidenceParcel prepareResidenceParcel(NationParcel nationParcel){
		//
		Nation nation = nationParcel.getNation();
		Metro metro = nationParcel.getMetro();
		Town town = nationParcel.getTown();
		
		String nationId = nation.getId();
		String metroId = metro.getId();
		String townId = town.getId();
		
		String castellanId = idGen.generateCastellanId(nationId);
		Castellan castellan = PeopleHelper.getSampleCastellan(nationId,
				castellanId, 1);
		castellanEntity.create(castellan);
		
		String citizenId = idGen.generateCitizenId(metroId);
		Citizen citizen = PeopleHelper.getSampleCitizen(metroId, castellan,
				citizenId, 1);
		citizenEntity.create(citizen);

		Towner towner = PeopleHelper.getSampleTowner(townId, citizen, 1);
		townerEntity.create(towner);
		
		return new ResidenceParcel(nationParcel, castellan, citizen, towner);
	}
	
	public Town prepareTown() {
		//
		NationParcel nationParcel = prepareNationParcel();
		return nationParcel.getTown();
	}

	public Citizen prepareCitizen() {
		//
		NationParcel nationParcel = prepareNationParcel();
		ResidenceParcel residenceParcel = prepareResidenceParcel(nationParcel);
		return residenceParcel.getCitizen();
	}
}
