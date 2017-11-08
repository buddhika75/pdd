/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.gov.health.nrd.entity;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import lk.gov.health.nrd.enums.AreaType;

/**
 *
 * @author User
 */
@Entity
public class Area implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    String name;
    @Enumerated(EnumType.STRING)
    AreaType areaType;
    @ManyToOne
    Area province;
    @ManyToOne
    Area district;
    @ManyToOne
    Area rdhs;
    @ManyToOne
    Area moh;
    @ManyToOne
    Area phi;
    @ManyToOne
    Area phm;

    public Area getDistrict() {
        return district;
    }

    public void setDistrict(Area district) {
        this.district = district;
    }

    
    
    public Area getPhi() {
        return phi;
    }

    public void setPhi(Area phi) {
        this.phi = phi;
    }

    public Area getPhm() {
        return phm;
    }

    public void setPhm(Area phm) {
        this.phm = phm;
    }
    
    

    public Area getProvince() {
        return province;
    }

    public void setProvince(Area province) {
        this.province = province;
    }

    public Area getRdhs() {
        return rdhs;
    }

    public void setRdhs(Area rdhs) {
        this.rdhs = rdhs;
    }

    public Area getMoh() {
        return moh;
    }

    public void setMoh(Area moh) {
        this.moh = moh;
    }
    
    

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public AreaType getAreaType() {
        return areaType;
    }

    public void setAreaType(AreaType areaType) {
        this.areaType = areaType;
    }
    
    

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
        if (!(object instanceof Area)) {
            return false;
        }
        Area other = (Area) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "lk.gov.health.nrd.entity.Area[ id=" + id + " ]";
    }
    
}
