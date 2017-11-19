package lk.gov.health.nrd.controllers;

import lk.gov.health.nrd.entity.Area;
import lk.gov.health.nrd.controllers.util.JsfUtil;
import lk.gov.health.nrd.controllers.util.JsfUtil.PersistAction;
import lk.gov.health.nrd.facades.AreaFacade;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import lk.gov.health.nrd.enums.AreaType;

@Named("areaController")
@SessionScoped
public class AreaController implements Serializable {

    @EJB
    private lk.gov.health.nrd.facades.AreaFacade ejbFacade;
    private List<Area> items = null;
    private Area selected;

    public AreaController() {
    }

    public List<Area> getRdhsAreas(){
        return getAreas(AreaType.RDHS);
    }
    
    public List<Area> getProvinces(){
        return getAreas(AreaType.Province);
    }
    
    public List<Area> getDistricts(){
        return getAreas(AreaType.District);
    }
    
    public List<Area> getMohAreas(){
        return getAreas(AreaType.MOH);
    }
    
    public List<Area> getPhiAreas(){
        return getAreas(AreaType.PHI);
    }
    
    public List<Area> getPhmAreas(){
        return getAreas(AreaType.PHM);
    }
    
    public List<Area> getAreas(AreaType t) {
        String j;
        Map m = new HashMap();
        m.put("t", t);
        j = "select a from Area a "
                + " where a.areaType=:t "
                + " order by a.name";
        return getFacade().findBySQL(j, m);
    }

    public List<Area> getMohAreas(Area rdhs) {
        String j;
        Map m = new HashMap();
        m.put("r", rdhs);
        m.put("t", AreaType.MOH);
        j = "select a from Area a "
                + " where a.areaType=:t "
                + " and a.rdhs=:r "
                + " order by a.name";
        return getFacade().findBySQL(j, m);
    }

    public List<Area> getPhmAreas(Area moh) {
        String j;
        Map m = new HashMap();
        m.put("r", moh);
        m.put("t", AreaType.PHM);
        j = "select a from Area a "
                + " where a.areaType=:t "
                + " and a.moh=:r "
                + " order by a.name";
        return getFacade().findBySQL(j, m);
    }

    public Area getSelected() {
        return selected;
    }

    public void setSelected(Area selected) {
        this.selected = selected;
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    private AreaFacade getFacade() {
        return ejbFacade;
    }

    public Area prepareCreate() {
        selected = new Area();
        initializeEmbeddableKey();
        return selected;
    }

    public void create() {
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/BundleArea").getString("AreaCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public void update() {
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/BundleArea").getString("AreaUpdated"));
    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/BundleArea").getString("AreaDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public List<Area> getItems() {
        if (items == null) {
            String j ="select a from Area a order by a.name";
            items = getFacade().findBySQL(j);
        }
        return items;
    }

    private void persist(PersistAction persistAction, String successMessage) {
        if (selected != null) {
            setEmbeddableKeys();
            try {
                if (persistAction != PersistAction.DELETE) {
                    getFacade().edit(selected);
                } else {
                    getFacade().remove(selected);
                }
                JsfUtil.addSuccessMessage(successMessage);
            } catch (EJBException ex) {
                String msg = "";
                Throwable cause = ex.getCause();
                if (cause != null) {
                    msg = cause.getLocalizedMessage();
                }
                if (msg.length() > 0) {
                    JsfUtil.addErrorMessage(msg);
                } else {
                    JsfUtil.addErrorMessage(ex, ResourceBundle.getBundle("/BundleArea").getString("PersistenceErrorOccured"));
                }
            } catch (Exception ex) {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
                JsfUtil.addErrorMessage(ex, ResourceBundle.getBundle("/BundleArea").getString("PersistenceErrorOccured"));
            }
        }
    }

    public Area getArea(java.lang.Long id) {
        return getFacade().find(id);
    }

    public List<Area> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<Area> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }

    @FacesConverter(forClass = Area.class)
    public static class AreaControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            AreaController controller = (AreaController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "areaController");
            return controller.getArea(getKey(value));
        }

        java.lang.Long getKey(String value) {
            java.lang.Long key;
            key = Long.valueOf(value);
            return key;
        }

        String getStringKey(java.lang.Long value) {
            StringBuilder sb = new StringBuilder();
            sb.append(value);
            return sb.toString();
        }

        @Override
        public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
            if (object == null) {
                return null;
            }
            if (object instanceof Area) {
                Area o = (Area) object;
                return getStringKey(o.getId());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), Area.class.getName()});
                return null;
            }
        }

    }

}
