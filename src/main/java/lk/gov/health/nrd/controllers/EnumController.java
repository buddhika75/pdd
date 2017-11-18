/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.gov.health.nrd.controllers;

import javax.faces.bean.ApplicationScoped;
import javax.inject.Named;
import lk.gov.health.nrd.entity.Occupation;
import lk.gov.health.nrd.entity.Ethnicity;
import lk.gov.health.nrd.entity.ReferredBy;
import lk.gov.health.nrd.entity.Religion;
import lk.gov.health.nrd.enums.AreaType;
import lk.gov.health.nrd.enums.MethodOfAssessment;
import lk.gov.health.nrd.enums.CaseType;
import lk.gov.health.nrd.enums.MaternalIcdPm;
import lk.gov.health.nrd.enums.PlaceOfDelivery;
import lk.gov.health.nrd.enums.TypeOfPregnancy;
import lk.gov.health.nrd.enums.Sex;
import lk.gov.health.nrd.enums.TimingOfDeath;
import lk.gov.health.nrd.enums.TypeOfDelivery;

/**
 *
 * @author User
 */
@Named
@ApplicationScoped
public class EnumController {

    /**
     * Creates a new instance of EnumController
     */
    public EnumController() {
    }

    public Sex[] getSexes() {
        return Sex.values();
    }

    public MethodOfAssessment[] getPlaces() {
        return MethodOfAssessment.values();
    }

    public CaseType[] getResponses() {
        return CaseType.values();
    }

    public TypeOfPregnancy[] getResponseCategories() {
        return TypeOfPregnancy.values();
    }

    public ReferredBy[] getReferredBys() {
        return ReferredBy.values();
    }

    public Occupation[] getOccupations() {
        return Occupation.values();
    }

    public Ethnicity[] getEthnicities() {
        return Ethnicity.values();
    }

    public CaseType[] getCaseTypes() {
        return CaseType.values();
    }

    public TypeOfPregnancy[] getTypesOfPregnancy() {
        return TypeOfPregnancy.values();
    }

    
    public TypeOfDelivery[] getTypesOfDelivary(){
        return TypeOfDelivery.values();
    }
    
     
    public MethodOfAssessment[] getMethodsOfAssessment(){
        return MethodOfAssessment.values();
    }
    
    public PlaceOfDelivery[] getPlacesOfDelivary(){
        return PlaceOfDelivery.values();
    }
    
    public Religion[] getReligions() {
        return Religion.values();
    }

    public AreaType[] getAreaTypes() {
        return AreaType.values();
    }
    
    public TimingOfDeath[] getTimingOfDeaths() {
        return TimingOfDeath.values();
    }

    public MaternalIcdPm[] getMaternalIcdPms(){
        return MaternalIcdPm.values();
    }
}
