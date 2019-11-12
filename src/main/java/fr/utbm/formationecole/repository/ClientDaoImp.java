/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.utbm.formationecole.repository;

import fr.utbm.formationecole.entity.Client;
import org.springframework.stereotype.Repository;

/**
 *
 * @author wuying
 */
@Repository(value = "ClientDao")  
public class ClientDaoImp extends BaseDaoImp<Client> implements ClientDao {
    
}
