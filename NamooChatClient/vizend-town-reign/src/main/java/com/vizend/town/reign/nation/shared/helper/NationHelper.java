package com.vizend.town.reign.nation.shared.helper;

import com.vizend.dream.util.enumtype.PidProperty;
import com.vizend.dream.util.enumtype.UserIdType;
import com.vizend.town.reign.nation.domain.Metro;
import com.vizend.town.reign.nation.domain.MetroPolicy;
import com.vizend.town.reign.nation.domain.Nation;
import com.vizend.town.reign.nation.domain.Town;
import com.vizend.town.reign.nation.domain.TownType;
import com.vizend.town.reign.nation.shared.dto.TownCDto;
import com.vizend.town.reign.shared.ProvisionMode;

/**
 * Test data helper for nation 
 * 
 * @author <a href="mailto:tsong@nextree.co.kr">Song,Taegook</a>
 * @since 2013. 04. 22.
 */
public class NationHelper {
	// 
	public static Nation getSampleNation(String nationId) {
		// 
		String name = "Nextree Nation"; 
		ProvisionMode provisionMode = ProvisionMode.InternalService; 
		String intro = "This is a sample nation."; 
		
		Nation sample = new Nation(nationId, name, provisionMode);
		sample.setIntro(intro); 
		
		return sample; 
	}
	
	public static Metro getSampleMetro(Nation nation, String metroId, int count) {
		// 
		String name = "[" + count + "]Nextree Metro"; 
		String intro = "[" + count + "]This is a sample metro."; 
		MetroPolicy metroPolicy = new MetroPolicy(UserIdType.GsId, PidProperty.Email); 
		
		Metro sample = new Metro(nation, metroId, name, metroPolicy);
		sample.setIntro(intro); 
		
		return sample;
	}
	
	public static Town getSampleTown(Metro metro, String townId, int count) {
		// 
		String name = "[" + count + "]Nextree Town"; 
		String intro = "[" + count + "]This is a sample town."; 
		boolean defaultTown = false; 
		if (count == 1) {
			defaultTown = true; 
		}
		TownType townType = TownType.Org; 
		String flagImageId = null;

		Town sample = new Town(metro, townId, name, defaultTown, townType);
		sample.setIntro(intro); 
		sample.setFlagImageId(flagImageId); 
		
		return sample; 
	}

	public static TownCDto getSampleTownCDto(int count) {
		//
		String name = "["+count+"] Nextree Town from CDto";
		boolean defaultTown = false;
		if (count == 1) {
			defaultTown = true; 
		}
		TownType townType = TownType.Org; 
		
		return new TownCDto(name, defaultTown, townType);
	}
}