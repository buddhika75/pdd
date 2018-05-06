package lk.gov.health.nrd.controllers;

import lk.gov.health.nrd.entity.Institute;
import lk.gov.health.nrd.controllers.util.JsfUtil;
import lk.gov.health.nrd.controllers.util.JsfUtil.PersistAction;
import lk.gov.health.nrd.facades.InstituteFacade;

import java.io.Serializable;
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

@Named("instituteController")
@SessionScoped
public class InstituteController implements Serializable {

    @EJB
    private lk.gov.health.nrd.facades.InstituteFacade ejbFacade;
    private List<Institute> items = null;
    private Institute selected;

    public InstituteController() {
    }

    public Institute getSelected() {
        return selected;
    }

    public void setSelected(Institute selected) {
        this.selected = selected;
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    private InstituteFacade getFacade() {
        return ejbFacade;
    }

    public Institute prepareCreate() {
        selected = new Institute();
        initializeEmbeddableKey();
        return selected;
    }

    public void create() {
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/BundleIns").getString("InstituteCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public void update() {
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/BundleIns").getString("InstituteUpdated"));
    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/BundleIns").getString("InstituteDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public List<Institute> getItems(String jpql) {
        return getFacade().findBySQL(jpql);
    }

    public List<Institute> getItems(String jpql, Map m) {
        return getFacade().findBySQL(jpql, m);
    }

    public List<Institute> getItems() {
        if (items == null) {
            String j = "select i from Institute i order by i.name";
            items = getFacade().findBySQL(j);
        }
        return items;
    }

    public List<Institute> getAllItems() {
        String j = "select i from Institute i order by i.name";
        items = getFacade().findBySQL(j);
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
                    JsfUtil.addErrorMessage(ex, ResourceBundle.getBundle("/BundleIns").getString("PersistenceErrorOccured"));
                }
            } catch (Exception ex) {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
                JsfUtil.addErrorMessage(ex, ResourceBundle.getBundle("/BundleIns").getString("PersistenceErrorOccured"));
            }
        }
    }

    public Institute getInstitute(java.lang.Long id) {
        return getFacade().find(id);
    }

    public List<Institute> getItemsAvailableSelectMany() {
        return getItems();
    }

    public List<Institute> getItemsAvailableSelectOne() {
        return getItems();
    }

    public void createOrUpdate(Institute ins) {
        try {
            if (ins.getId() == null) {
                getFacade().create(ins);
                JsfUtil.addSuccessMessage(ins + " created.");
            } else {
                getFacade().edit(ins);
                JsfUtil.addSuccessMessage(ins + " updated.");
            }
            items = null;
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e.getMessage());
        }

    }

    @FacesConverter(forClass = Institute.class)
    public static class InstituteControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            InstituteController controller = (InstituteController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "instituteController");
            return controller.getInstitute(getKey(value));
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
            if (object instanceof Institute) {
                Institute o = (Institute) object;
                return getStringKey(o.getId());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), Institute.class.getName()});
                return null;
            }
        }

    }

}
