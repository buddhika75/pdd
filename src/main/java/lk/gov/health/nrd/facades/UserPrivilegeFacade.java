/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.gov.health.nrd.facades;


import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import lk.gov.health.nrd.entity.UserPrivilege;

/**
 *
 * @author buddh
 */
@Stateless
public class UserPrivilegeFacade extends AbstractFacade<UserPrivilege> {

    @PersistenceContext(unitName = "pu")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public UserPrivilegeFacade() {
        super(UserPrivilege.class);
    }
    
}
