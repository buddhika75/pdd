/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.gov.health.nrd.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import lk.gov.health.nrd.enums.MethodOfAssessment;
import lk.gov.health.nrd.enums.CaseType;
import lk.gov.health.nrd.enums.Sex;

/**
 *
 * @author User
 */
@Entity
public class Questionnaire implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    @ManyToOne
    Item position;
    @Enumerated(EnumType.STRING)
    Sex sex;
    Integer age;
    Integer servicePeriod;
    Integer institutionServicePeriod;
    
    @Enumerated(EnumType.STRING)
    MethodOfAssessment place;
    
    @Enumerated(EnumType.STRING)
    CaseType increments;
    
    @Enumerated(EnumType.STRING)
    CaseType environment;
    
    @Enumerated(EnumType.STRING)
    CaseType clineliness;
    
    @Enumerated(EnumType.STRING)
    CaseType arrangement;
    
    @Enumerated(EnumType.STRING)
    CaseType groupWork;
    
    @Enumerated(EnumType.STRING)
    CaseType leadership;
    
    @Enumerated(EnumType.STRING)
    CaseType training;
    
    @Enumerated(EnumType.STRING)
    CaseType evaluation;
    
    @Enumerated(EnumType.STRING)
    CaseType wellfare;
    
    @Enumerated(EnumType.STRING)
    CaseType decpline;
    
    @Enumerated(EnumType.STRING)
    CaseType communication;
    
    @Enumerated(EnumType.STRING)
    CaseType discussions;
    
    @Enumerated(EnumType.STRING)
    CaseType requirement_Identification;
    
    @Enumerated(EnumType.STRING)
    CaseType quality_service;
    
    @Enumerated(EnumType.STRING)
    CaseType expectation_achievement;
    
    
    @Lob
    String suggestions;
    
    @Lob
    String expectingFacilities;
    
    
    
    @Temporal(javax.persistence.TemporalType.DATE)
    Date questionnaireDate;
    @Temporal(javax.persistence.TemporalType.DATE)
    Date createdDate;
    
    @ManyToOne
    WebUser addedUser;
    @Temporal(javax.persistence.TemporalType.DATE)
    Date addedDate;
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    Date addedTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public MethodOfAssessment getPlace() {
        return place;
    }

    public void setPlace(MethodOfAssessment place) {
        this.place = place;
    }
    
    

    public Item getPosition() {
        return position;
    }

    public void setPosition(Item position) {
        this.position = position;
    }

    public Sex getSex() {
        return sex;
    }

    public void setSex(Sex sex) {
        this.sex = sex;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Integer getServicePeriod() {
        return servicePeriod;
    }

    public void setServicePeriod(Integer servicePeriod) {
        this.servicePeriod = servicePeriod;
    }

    public Integer getInstitutionServicePeriod() {
        return institutionServicePeriod;
    }

    public void setInstitutionServicePeriod(Integer institutionServicePeriod) {
        this.institutionServicePeriod = institutionServicePeriod;
    }

    public CaseType getIncrements() {
        return increments;
    }

    public void setIncrements(CaseType increments) {
        this.increments = increments;
    }

    public CaseType getEnvironment() {
        return environment;
    }

    public void setEnvironment(CaseType environment) {
        this.environment = environment;
    }

    public CaseType getClineliness() {
        return clineliness;
    }

    public void setClineliness(CaseType clineliness) {
        this.clineliness = clineliness;
    }

    public CaseType getArrangement() {
        return arrangement;
    }

    public void setArrangement(CaseType arrangement) {
        this.arrangement = arrangement;
    }

    public CaseType getGroupWork() {
        return groupWork;
    }

    public void setGroupWork(CaseType groupWork) {
        this.groupWork = groupWork;
    }

    public CaseType getLeadership() {
        return leadership;
    }

    public void setLeadership(CaseType leadership) {
        this.leadership = leadership;
    }

    public CaseType getTraining() {
        return training;
    }

    public void setTraining(CaseType training) {
        this.training = training;
    }

    public CaseType getEvaluation() {
        return evaluation;
    }

    public void setEvaluation(CaseType evaluation) {
        this.evaluation = evaluation;
    }

    public CaseType getWellfare() {
        return wellfare;
    }

    public void setWellfare(CaseType wellfare) {
        this.wellfare = wellfare;
    }

    public CaseType getDecpline() {
        return decpline;
    }

    public void setDecpline(CaseType decpline) {
        this.decpline = decpline;
    }

    public CaseType getCommunication() {
        return communication;
    }

    public void setCommunication(CaseType communication) {
        this.communication = communication;
    }

    public CaseType getDiscussions() {
        return discussions;
    }

    public void setDiscussions(CaseType discussions) {
        this.discussions = discussions;
    }

    public CaseType getRequirement_Identification() {
        return requirement_Identification;
    }

    public void setRequirement_Identification(CaseType requirement_Identification) {
        this.requirement_Identification = requirement_Identification;
    }

    public CaseType getQuality_service() {
        return quality_service;
    }

    public void setQuality_service(CaseType quality_service) {
        this.quality_service = quality_service;
    }

    public CaseType getExpectation_achievement() {
        return expectation_achievement;
    }

    public void setExpectation_achievement(CaseType expectation_achievement) {
        this.expectation_achievement = expectation_achievement;
    }

    public String getSuggestions() {
        return suggestions;
    }

    public void setSuggestions(String suggestions) {
        this.suggestions = suggestions;
    }

    public String getExpectingFacilities() {
        return expectingFacilities;
    }

    public void setExpectingFacilities(String expectingFacilities) {
        this.expectingFacilities = expectingFacilities;
    }

    public Date getQuestionnaireDate() {
        return questionnaireDate;
    }

    public void setQuestionnaireDate(Date questionnaireDate) {
        this.questionnaireDate = questionnaireDate;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public WebUser getAddedUser() {
        return addedUser;
    }

    public void setAddedUser(WebUser addedUser) {
        this.addedUser = addedUser;
    }

    public Date getAddedDate() {
        return addedDate;
    }

    public void setAddedDate(Date addedDate) {
        this.addedDate = addedDate;
    }

    public Date getAddedTime() {
        return addedTime;
    }

    public void setAddedTime(Date addedTime) {
        this.addedTime = addedTime;
    }




    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Questionnaire)) {
            return false;
        }
        Questionnaire other = (Questionnaire) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "lk.gov.health.dailymail.entity.Mail[ id=" + id + " ]";
    }
    
}
