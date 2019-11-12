/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.utbm.formationecole.service;

import fr.utbm.formationecole.entity.Location;
import fr.utbm.formationecole.repository.LocationJpaController;
import java.util.List;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author wuying
 */
public class LocationService {
    EntityManagerFactory emf=Persistence.createEntityManagerFactory("my_persistence_unit");
    public List<Location> getAllLocationService(){
        LocationJpaController hef=new LocationJpaController(emf);
        return hef.findLocationEntities();

    }
    public Location getLocationById(Integer id){
        LocationJpaController hef=new LocationJpaController(emf);
        return hef.findLocation(id);
    }
}
