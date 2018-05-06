package lk.gov.health.nrd.controllers;

import lk.gov.health.nrd.entity.UserInstitute;
import lk.gov.health.nrd.controllers.util.JsfUtil;
import lk.gov.health.nrd.controllers.util.JsfUtil.PersistAction;
import lk.gov.health.nrd.facades.UserInstituteFacade;

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
import lk.gov.health.nrd.entity.Institute;
import lk.gov.health.nrd.entity.UserPrivilege;
import lk.gov.health.nrd.entity.WebUser;

@Named("userInstituteController")
@SessionScoped
public class UserInstituteController implements Serializable {

    @EJB
    private UserInstituteFacade ejbFacade;
    private List<UserInstitute> items = null;
    private List<UserInstitute> webUserInstitutes = null;
    private UserInstitute selected;
    Institute institute;
    WebUser webUser;

    public UserInstituteController() {
    }

    public void addWebUserInstitute() {
        if (webUser == null) {
            JsfUtil.addErrorMessage("Select User");
            return;
        }
        if (institute == null) {
            JsfUtil.addErrorMessage("Select Institute");
        }
        UserInstitute up;
        String j = "select up from UserInsitute up where up.webUser=:u and up.institute=:p";
        Map m = new HashMap();
        m.put("u", webUser);
        m.put("p", institute);
        up = getFacade().findFirstBySQL(j, m);
        if (up == null) {
            up = new UserInstitute();
            up.setWebUser(webUser);
            up.setInstitute(institute);
            getFacade().create(up);
            webUserInstitutes = null;
            getWebUserInstitutes();
            JsfUtil.addSuccessMessage("Privilege added");
        } else {
            JsfUtil.addErrorMessage("Already added");
        }

    }
    
    
    public void removeUserInstitute() {
        if (selected == null) {
            JsfUtil.addErrorMessage("Nothing to remove");
            return;
        }
        getFacade().remove(selected);
        webUserInstitutes = null;
        getWebUserInstitutes();
    }
    
    public UserInstitute getSelected() {
        return selected;
    }

    public void setSelected(UserInstitute selected) {
        this.selected = selected;
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    private UserInstituteFacade getFacade() {
        return ejbFacade;
    }

    public UserInstitute prepareCreate() {
        selected = new UserInstitute();
        initializeEmbeddableKey();
        return selected;
    }
    
     public void createOrUpdate(UserInstitute ui) {
        try {
            if (ui.getId() == null) {
                getFacade().create(ui);
                JsfUtil.addSuccessMessage(ui + " created.");
            } else {
                getFacade().edit(ui);
                JsfUtil.addSuccessMessage(ui + " updated.");
            }
            items = null;
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e.getMessage());
        }

    }

    public void create() {
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/BundleUser2").getString("UserInstituteCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public void update() {
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/BundleUser2").getString("UserInstituteUpdated"));
    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/BundleUser2").getString("UserInstituteDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public List<UserInstitute> getItems() {
        if (items == null) {
            items = getFacade().findAll();
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
                    JsfUtil.addErrorMessage(ex, ResourceBundle.getBundle("/BundleUser2").getString("PersistenceErrorOccured"));
                }
            } catch (Exception ex) {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
                JsfUtil.addErrorMessage(ex, ResourceBundle.getBundle("/BundleUser2").getString("PersistenceErrorOccured"));
            }
        }
    }

    public UserInstitute getUserInstitute(java.lang.Long id) {
        return getFacade().find(id);
    }

    public List<UserInstitute> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<UserInstitute> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }

    public Institute getInstitute() {
        return institute;
    }

    public void setInstitute(Institute institute) {
        this.institute = institute;
    }

    public List<UserInstitute> getWebUserInstitutes() {
        
        if (webUser == null) {
            webUserInstitutes = null;
        }
        if (webUserInstitutes == null) {
            String j = "select up from UserInstitute up where up.webUser=:u";
            Map m = new HashMap();
            m.put("u", webUser);
            webUserInstitutes = getFacade().findBySQL(j, m);
        }
        
        
        return webUserInstitutes;
    }

    public void setWebUserInstitutes(List<UserInstitute> webUserInstitutes) {
        this.webUserInstitutes = webUserInstitutes;
    }

    public WebUser getWebUser() {
        return webUser;
    }

    public void setWebUser(WebUser webUser) {
        this.webUser = webUser;
        webUserInstitutes=null;
    }

    
    
    
    @FacesConverter(forClass = UserInstitute.class)
    public static class UserInstituteControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            UserInstituteController controller = (UserInstituteController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "userInstituteController");
            return controller.getUserInstitute(getKey(value));
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
            if (object instanceof UserInstitute) {
                UserInstitute o = (UserInstitute) object;
                return getStringKey(o.getId());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), UserInstitute.class.getName()});
                return null;
            }
        }

    }

}
