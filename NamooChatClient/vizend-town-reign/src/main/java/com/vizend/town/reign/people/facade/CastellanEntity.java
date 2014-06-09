package com.vizend.town.reign.people.facade;

import com.vizend.town.reign.people.domain.Castellan;

/**
 * RecycleBin Entity interface
 *
 * @author <a href="mailto:tsong@nextree.co.kr">Song,Taegook</a>
 * @since 2011. 5. 10.
 */
public interface CastellanEntity {
    //
    public String create(Castellan castellan);
    public Castellan retrieve(String castellanId);
    public Castellan retrieveByAuthEmail(String authEmail);
    public Castellan retrieveByAuthPhone(String authPhone);
    public void update(Castellan castellan);
    public void delete(Castellan castellan);
    public boolean existByAuthEmail(String authEmail);
    public boolean existByAuthPhone(String authPhone);
    public boolean existByCastellanId(String castellanId);
}