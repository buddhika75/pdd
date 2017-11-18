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
import lk.gov.health.nrd.enums.CaseType;
import lk.gov.health.nrd.enums.MaternalIcdPm;
import lk.gov.health.nrd.enums.MethodOfAssessment;
import lk.gov.health.nrd.enums.PlaceOfDelivery;
import lk.gov.health.nrd.enums.PostMortemType;
import lk.gov.health.nrd.enums.Sex;
import lk.gov.health.nrd.enums.TimingOfDeath;
import lk.gov.health.nrd.enums.TypeOfDelivery;
import lk.gov.health.nrd.enums.TypeOfPregnancy;

/**
 *
 * @author User
 */
@Entity
public class CaseRecord implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    Institute institute;
    @ManyToOne
    Department department;

    String mothersNumber;
    String babysNumber;
    String nameOfCase;
    @Enumerated(EnumType.STRING)
    Ethnicity ethnicity;

    String ethnicityOther;
    @Lob
    String residentialAddress;
    @ManyToOne
    Area rdhsArea;
    @ManyToOne
    Area mohArea;
    @ManyToOne
    Area phmArea;
    Double mothersAge;
    String mothersNic;
    @Enumerated(EnumType.STRING)
    CaseType caseType;
    String contactNumbers;
    Integer g;
    Integer p;
    Integer c;
    Integer parity;
    Integer parityT;
    Integer parityP;
    Integer parityA;
    Integer parityL;
    @Enumerated(EnumType.STRING)
    TypeOfPregnancy typeOfPregnancy;
    Integer higherPregnancy;
    @Temporal(javax.persistence.TemporalType.DATE)
    Date dateOfDeliveryOrBirth;
    @Temporal(javax.persistence.TemporalType.TIME)
    Date timeOfDelivery;
    @Enumerated(EnumType.STRING)
    PlaceOfDelivery placeOfDelivery;
    @ManyToOne
    Institute placeOfDeliveryInstitute;
    @ManyToOne
    Area placeOfDeliveryArea;
    String placeOfDeliveryString;
    @Enumerated(EnumType.STRING)
    TypeOfDelivery typeOfDelivery;
    
    String otherTypeOfDeliveryString;
    Integer pogWeeks;
    Integer pogDays;
    @Enumerated(EnumType.STRING)
    MethodOfAssessment methodOfAssessment;
    Double birthWeight;
    @Enumerated(EnumType.STRING)
    Sex Sex;
    @Temporal(javax.persistence.TemporalType.DATE)
    Date dateOfDeath;
    Integer ageOfDeathDays;
    Double ageOfDeathHours;
    TimingOfDeath timingOfDeath;
    Boolean a1;
    Boolean a2;
    Boolean a3;
    Boolean a4;
    Boolean a5;
    Boolean a6;
    Boolean auc;
    Boolean i1;
    Boolean i2;
    Boolean i3;
    Boolean i4;
    Boolean i5;
    Boolean i6;
    Boolean i7;
    Boolean iuc;

    Boolean n1;
    Boolean n2;
    Boolean n3;
    Boolean n4;
    Boolean n5;
    Boolean n6;
    Boolean n7;
     private Boolean n8;
    private Boolean n9;
    private Boolean n10;
    private Boolean n11;
    Boolean nuc;

    @Lob
    String broadIcdPm;
    @Lob
    String icdSpecific;

    @Enumerated
    MaternalIcdPm maternalIcdPm;
    @Lob
    String maternalIcdPmSpecific;

    @Enumerated(EnumType.STRING)
    PostMortemType postMortemType;

    Boolean postMortemDone;

    @Lob
    String postMortemDetails;

    String postMortemRecordNo;

    @Temporal(javax.persistence.TemporalType.DATE)
    Date dateOfRegistration;

    Boolean b22b33Filled;
    String deathCertificateNo;

    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    Date createAt;
    @ManyToOne
    WebUser createdBy;
    @ManyToOne
    Institute createInstitute;
    @ManyToOne
    Department createdDepartment;

    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    Date lastEditedAt;
    @ManyToOne
    WebUser lastEditedBy;

    Boolean retired;
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    Date retiredAt;
    @ManyToOne
    WebUser retiredBy;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Institute getInstitute() {
        return institute;
    }

    public void setInstitute(Institute institute) {
        this.institute = institute;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public String getMothersNumber() {
        return mothersNumber;
    }

    public void setMothersNumber(String mothersNumber) {
        this.mothersNumber = mothersNumber;
    }

    public String getBabysNumber() {
        return babysNumber;
    }

    public void setBabysNumber(String babysNumber) {
        this.babysNumber = babysNumber;
    }

    public String getNameOfCase() {
        return nameOfCase;
    }

    public void setNameOfCase(String nameOfCase) {
        this.nameOfCase = nameOfCase;
    }

    public Ethnicity getEthnicity() {
        return ethnicity;
    }

    public void setEthnicity(Ethnicity ethnicity) {
        this.ethnicity = ethnicity;
    }

    public String getEthnicityOther() {
        return ethnicityOther;
    }

    public void setEthnicityOther(String ethnicityOther) {
        this.ethnicityOther = ethnicityOther;
    }

    public String getResidentialAddress() {
        return residentialAddress;
    }

    public void setResidentialAddress(String residentialAddress) {
        this.residentialAddress = residentialAddress;
    }

    public Area getRdhsArea() {
        return rdhsArea;
    }

    public Institute getCreateInstitute() {
        return createInstitute;
    }

    public void setCreateInstitute(Institute createInstitute) {
        this.createInstitute = createInstitute;
    }

    public Department getCreatedDepartment() {
        return createdDepartment;
    }

    public void setCreatedDepartment(Department createdDepartment) {
        this.createdDepartment = createdDepartment;
    }

    public void setRdhsArea(Area rdhsArea) {
        this.rdhsArea = rdhsArea;
    }

    public Area getMohArea() {
        return mohArea;
    }

    public void setMohArea(Area mohArea) {
        this.mohArea = mohArea;
    }

    public Area getPhmArea() {
        return phmArea;
    }

    public void setPhmArea(Area phmArea) {
        this.phmArea = phmArea;
    }

    public Double getMothersAge() {
        return mothersAge;
    }

    public void setMothersAge(Double mothersAge) {
        this.mothersAge = mothersAge;
    }

    public String getMothersNic() {
        return mothersNic;
    }

    public void setMothersNic(String mothersNic) {
        this.mothersNic = mothersNic;
    }

    public CaseType getCaseType() {
        return caseType;
    }

    public void setCaseType(CaseType caseType) {
        this.caseType = caseType;
    }

    public String getContactNumbers() {
        return contactNumbers;
    }

    public void setContactNumbers(String contactNumbers) {
        this.contactNumbers = contactNumbers;
    }

    public Integer getG() {
        return g;
    }

    public void setG(Integer g) {
        this.g = g;
    }

    public Integer getP() {
        return p;
    }

    public void setP(Integer p) {
        this.p = p;
    }

    public Integer getC() {
        return c;
    }

    public void setC(Integer c) {
        this.c = c;
    }

    public Integer getParity() {
        return parity;
    }

    public void setParity(Integer parity) {
        this.parity = parity;
    }

    public Integer getParityT() {
        return parityT;
    }

    public void setParityT(Integer parityT) {
        this.parityT = parityT;
    }

    public Integer getParityP() {
        return parityP;
    }

    public void setParityP(Integer parityP) {
        this.parityP = parityP;
    }

    public Integer getParityA() {
        return parityA;
    }

    public void setParityA(Integer parityA) {
        this.parityA = parityA;
    }

    public Integer getParityL() {
        return parityL;
    }

    public void setParityL(Integer parityL) {
        this.parityL = parityL;
    }

    public TypeOfPregnancy getTypeOfPregnancy() {
        return typeOfPregnancy;
    }

    public void setTypeOfPregnancy(TypeOfPregnancy typeOfPregnancy) {
        this.typeOfPregnancy = typeOfPregnancy;
    }

    public Integer getHigherPregnancy() {
        return higherPregnancy;
    }

    public void setHigherPregnancy(Integer higherPregnancy) {
        this.higherPregnancy = higherPregnancy;
    }

    public Date getDateOfDeliveryOrBirth() {
        return dateOfDeliveryOrBirth;
    }

    public void setDateOfDeliveryOrBirth(Date dateOfDeliveryOrBirth) {
        this.dateOfDeliveryOrBirth = dateOfDeliveryOrBirth;
    }

    public Date getTimeOfDelivery() {
        return timeOfDelivery;
    }

    public void setTimeOfDelivery(Date timeOfDelivery) {
        this.timeOfDelivery = timeOfDelivery;
    }

    public PlaceOfDelivery getPlaceOfDelivery() {
        return placeOfDelivery;
    }

    public void setPlaceOfDelivery(PlaceOfDelivery placeOfDelivery) {
        this.placeOfDelivery = placeOfDelivery;
    }

    public Institute getPlaceOfDeliveryInstitute() {
        return placeOfDeliveryInstitute;
    }

    public void setPlaceOfDeliveryInstitute(Institute placeOfDeliveryInstitute) {
        this.placeOfDeliveryInstitute = placeOfDeliveryInstitute;
    }

    public Area getPlaceOfDeliveryArea() {
        return placeOfDeliveryArea;
    }

    public void setPlaceOfDeliveryArea(Area placeOfDeliveryArea) {
        this.placeOfDeliveryArea = placeOfDeliveryArea;
    }

    public String getPlaceOfDeliveryString() {
        return placeOfDeliveryString;
    }

    public void setPlaceOfDeliveryString(String placeOfDeliveryString) {
        this.placeOfDeliveryString = placeOfDeliveryString;
    }

    public TypeOfDelivery getTypeOfDelivery() {
        return typeOfDelivery;
    }

    public void setTypeOfDelivery(TypeOfDelivery typeOfDelivery) {
        this.typeOfDelivery = typeOfDelivery;
    }

    

    public String getOtherTypeOfDeliveryString() {
        return otherTypeOfDeliveryString;
    }

    public void setOtherTypeOfDeliveryString(String otherTypeOfDeliveryString) {
        this.otherTypeOfDeliveryString = otherTypeOfDeliveryString;
    }

    public Integer getPogWeeks() {
        return pogWeeks;
    }

    public void setPogWeeks(Integer pogWeeks) {
        this.pogWeeks = pogWeeks;
    }

    public Integer getPogDays() {
        return pogDays;
    }

    public void setPogDays(Integer pogDays) {
        this.pogDays = pogDays;
    }

    public MethodOfAssessment getMethodOfAssessment() {
        return methodOfAssessment;
    }

    public void setMethodOfAssessment(MethodOfAssessment methodOfAssessment) {
        this.methodOfAssessment = methodOfAssessment;
    }

    public Double getBirthWeight() {
        return birthWeight;
    }

    public void setBirthWeight(Double birthWeight) {
        this.birthWeight = birthWeight;
    }

    public Sex getSex() {
        return Sex;
    }

    public void setSex(Sex Sex) {
        this.Sex = Sex;
    }

    public Date getDateOfDeath() {
        return dateOfDeath;
    }

    public void setDateOfDeath(Date dateOfDeath) {
        this.dateOfDeath = dateOfDeath;
    }

    public Integer getAgeOfDeathDays() {
        return ageOfDeathDays;
    }

    public void setAgeOfDeathDays(Integer ageOfDeathDays) {
        this.ageOfDeathDays = ageOfDeathDays;
    }

    public Double getAgeOfDeathHours() {
        return ageOfDeathHours;
    }

    public void setAgeOfDeathHours(Double ageOfDeathHours) {
        this.ageOfDeathHours = ageOfDeathHours;
    }

    public TimingOfDeath getTimingOfDeath() {
        return timingOfDeath;
    }

    public void setTimingOfDeath(TimingOfDeath timingOfDeath) {
        this.timingOfDeath = timingOfDeath;
    }

    public Boolean getA1() {
        return a1;
    }

    public void setA1(Boolean a1) {
        this.a1 = a1;
    }

    public Boolean getA2() {
        return a2;
    }

    public void setA2(Boolean a2) {
        this.a2 = a2;
    }

    public Boolean getA3() {
        return a3;
    }

    public void setA3(Boolean a3) {
        this.a3 = a3;
    }

    public Boolean getA4() {
        return a4;
    }

    public void setA4(Boolean a4) {
        this.a4 = a4;
    }

    public Boolean getA5() {
        return a5;
    }

    public void setA5(Boolean a5) {
        this.a5 = a5;
    }

    public Boolean getA6() {
        return a6;
    }

    public void setA6(Boolean a6) {
        this.a6 = a6;
    }

    public Boolean getAuc() {
        return auc;
    }

    public void setAuc(Boolean auc) {
        this.auc = auc;
    }

    public Boolean getI1() {
        return i1;
    }

    public void setI1(Boolean i1) {
        this.i1 = i1;
    }

    public Boolean getI2() {
        return i2;
    }

    public void setI2(Boolean i2) {
        this.i2 = i2;
    }

    public Boolean getI3() {
        return i3;
    }

    public void setI3(Boolean i3) {
        this.i3 = i3;
    }

    public Boolean getI4() {
        return i4;
    }

    public void setI4(Boolean i4) {
        this.i4 = i4;
    }

    public Boolean getI5() {
        return i5;
    }

    public void setI5(Boolean i5) {
        this.i5 = i5;
    }

    public Boolean getI6() {
        return i6;
    }

    public void setI6(Boolean i6) {
        this.i6 = i6;
    }

    public Boolean getI7() {
        return i7;
    }

    public void setI7(Boolean i7) {
        this.i7 = i7;
    }

    public Boolean getIuc() {
        return iuc;
    }

    public void setIuc(Boolean iuc) {
        this.iuc = iuc;
    }

    public Boolean getN1() {
        return n1;
    }

    public void setN1(Boolean n1) {
        this.n1 = n1;
    }

    public Boolean getN2() {
        return n2;
    }

    public void setN2(Boolean n2) {
        this.n2 = n2;
    }

    public Boolean getN3() {
        return n3;
    }

    public void setN3(Boolean n3) {
        this.n3 = n3;
    }

    public Boolean getN4() {
        return n4;
    }

    public void setN4(Boolean n4) {
        this.n4 = n4;
    }

    public Boolean getN5() {
        return n5;
    }

    public void setN5(Boolean n5) {
        this.n5 = n5;
    }

    public Boolean getN6() {
        return n6;
    }

    public void setN6(Boolean n6) {
        this.n6 = n6;
    }

    public Boolean getN7() {
        return n7;
    }

    public void setN7(Boolean n7) {
        this.n7 = n7;
    }

    public Boolean getNuc() {
        return nuc;
    }

    public void setNuc(Boolean nuc) {
        this.nuc = nuc;
    }

    public String getBroadIcdPm() {
        return broadIcdPm;
    }

    public void setBroadIcdPm(String broadIcdPm) {
        this.broadIcdPm = broadIcdPm;
    }

    public String getIcdSpecific() {
        return icdSpecific;
    }

    public void setIcdSpecific(String icdSpecific) {
        this.icdSpecific = icdSpecific;
    }

    public MaternalIcdPm getMaternalIcdPm() {
        return maternalIcdPm;
    }

    public void setMaternalIcdPm(MaternalIcdPm maternalIcdPm) {
        this.maternalIcdPm = maternalIcdPm;
    }

    

    public String getMaternalIcdPmSpecific() {
        return maternalIcdPmSpecific;
    }

    public void setMaternalIcdPmSpecific(String maternalIcdPmSpecific) {
        this.maternalIcdPmSpecific = maternalIcdPmSpecific;
    }

    public PostMortemType getPostMortemType() {
        return postMortemType;
    }

    public void setPostMortemType(PostMortemType postMortemType) {
        this.postMortemType = postMortemType;
    }

    public Boolean getPostMortemDone() {
        return postMortemDone;
    }

    public void setPostMortemDone(Boolean postMortemDone) {
        this.postMortemDone = postMortemDone;
    }

    public String getPostMortemDetails() {
        return postMortemDetails;
    }

    public void setPostMortemDetails(String postMortemDetails) {
        this.postMortemDetails = postMortemDetails;
    }

    public String getPostMortemRecordNo() {
        return postMortemRecordNo;
    }

    public void setPostMortemRecordNo(String postMortemRecordNo) {
        this.postMortemRecordNo = postMortemRecordNo;
    }

    public Date getDateOfRegistration() {
        return dateOfRegistration;
    }

    public void setDateOfRegistration(Date dateOfRegistration) {
        this.dateOfRegistration = dateOfRegistration;
    }

    public Boolean getB22b33Filled() {
        return b22b33Filled;
    }

    public void setB22b33Filled(Boolean b22b33Filled) {
        this.b22b33Filled = b22b33Filled;
    }

    public String getDeathCertificateNo() {
        return deathCertificateNo;
    }

    public void setDeathCertificateNo(String deathCertificateNo) {
        this.deathCertificateNo = deathCertificateNo;
    }

    public Date getCreateAt() {
        return createAt;
    }

    public void setCreateAt(Date createAt) {
        this.createAt = createAt;
    }

    public WebUser getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(WebUser createdBy) {
        this.createdBy = createdBy;
    }

    public Date getLastEditedAt() {
        return lastEditedAt;
    }

    public void setLastEditedAt(Date lastEditedAt) {
        this.lastEditedAt = lastEditedAt;
    }

    public WebUser getLastEditedBy() {
        return lastEditedBy;
    }

    public void setLastEditedBy(WebUser lastEditedBy) {
        this.lastEditedBy = lastEditedBy;
    }

    public Boolean getRetired() {
        return retired;
    }

    public void setRetired(Boolean retired) {
        this.retired = retired;
    }

    public Date getRetiredAt() {
        return retiredAt;
    }

    public void setRetiredAt(Date retiredAt) {
        this.retiredAt = retiredAt;
    }

    public WebUser getRetiredBy() {
        return retiredBy;
    }

    public void setRetiredBy(WebUser retiredBy) {
        this.retiredBy = retiredBy;
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
        if (!(object instanceof CaseRecord)) {
            return false;
        }
        CaseRecord other = (CaseRecord) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "lk.gov.health.nrd.entity.Patient[ id=" + id + " ]";
    }

    public Boolean getN8() {
        return n8;
    }

    public void setN8(Boolean n8) {
        this.n8 = n8;
    }

    public Boolean getN9() {
        return n9;
    }

    public void setN9(Boolean n9) {
        this.n9 = n9;
    }

    public Boolean getN10() {
        return n10;
    }

    public void setN10(Boolean n10) {
        this.n10 = n10;
    }

    public Boolean getN11() {
        return n11;
    }

    public void setN11(Boolean n11) {
        this.n11 = n11;
    }

}
