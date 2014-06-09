package com.vizend.town.reign.ruling.fillup;

import com.vizend.town.reign.nation.domain.Metro;
import com.vizend.town.reign.nation.domain.Nation;
import com.vizend.town.reign.nation.domain.Town;

/**
 *
 * @author <a href="mailto:vaslife@gmail.com">ahn, young-su</a>
 * @since Apr 23, 2013
 */
public class NationParcel {
	//
	private Nation nation;
	private Metro metro;
	private Town town;

	/**
	 * @param nation
	 * @param metro
	 * @param town
	 */
	public NationParcel(Nation nation, Metro metro, Town town) {
		//
		this.nation = nation;
		this.metro = metro;
		this.town = town;
	}

	public Nation getNation() {
		return nation;
	}

	public Metro getMetro() {
		return metro;
	}

	public Town getTown() {
		return town;
	}

	
}
