/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.gov.health.nrd.facades;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import lk.gov.health.nrd.entity.Questionnaire;

/**
 *
 * @author User
 */
@Stateless
public class MailFacade extends AbstractFacade<Questionnaire> {

    @PersistenceContext(unitName = "pu")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public MailFacade() {
        super(Questionnaire.class);
    }
    
}
