package lk.gov.health.nrd.controllers;

import lk.gov.health.nrd.entity.CaseRecord;
import lk.gov.health.nrd.controllers.util.JsfUtil;
import lk.gov.health.nrd.controllers.util.JsfUtil.PersistAction;
import lk.gov.health.nrd.facades.PatientFacade;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
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
import javax.inject.Inject;
import lk.gov.health.nrd.entity.Area;
import lk.gov.health.nrd.enums.PlaceOfDelivery;

@Named("caseRecordController")
@SessionScoped
public class CaseRecordController implements Serializable {

    @Inject
    WebUserController webUserController;
    @Inject
    AreaController areaController;

    @EJB
    private lk.gov.health.nrd.facades.PatientFacade ejbFacade;
    private List<CaseRecord> items = null;
    private CaseRecord selected;

    
    public String addNewCase(){
        selected = new CaseRecord();
        return "/caseRecord/caseRecord";
    }
    
    public AreaController getAreaController() {
        return areaController;
    }

    public void setAreaController(AreaController areaController) {
        this.areaController = areaController;
    }

    public List<Area> getSelectedMohAreas() {
        if (selected == null) {
            return new ArrayList<Area>();
        }
        return getAreaController().getMohAreas(getSelected().getRdhsArea());
    }
    
     public List<Area> getSelectedPhmAreas() {
        if (selected == null) {
            return new ArrayList<Area>();
        }
        return getAreaController().getPhmAreas(getSelected().getMohArea());
    }

    public CaseRecordController() {
    }

    public WebUserController getWebUserController() {
        return webUserController;
    }

    public String toAddNewCase() {
        selected = new CaseRecord();
        selected.setCreateAt(new Date());
        selected.setCreatedBy(getWebUserController().getLoggedUser());
        selected.setPlaceOfDelivery(PlaceOfDelivery.Institute);
        selected.setPlaceOfDeliveryInstitute(getWebUserController().getLoggedUser().getInstitute());
        selected.setCreateInstitute(getWebUserController().getLoggedUser().getInstitute());
        selected.setCreatedDepartment(getWebUserController().getLoggedUser().getDepartment());
        return "/caseRecord/caseRecord";
    }

    public String saveCase() {
        if (selected == null) {
            JsfUtil.addErrorMessage("Nothing to save");
            return "";
        }
        if (selected.getId() == null) {
            getFacade().create(selected);
            JsfUtil.addSuccessMessage("Created");
        } else {
            getFacade().edit(selected);
            JsfUtil.addSuccessMessage("Updated");
        }
        return "";
    }

    public CaseRecord getSelected() {
        return selected;
    }

    public void setSelected(CaseRecord selected) {
        this.selected = selected;
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    private PatientFacade getFacade() {
        return ejbFacade;
    }

    public CaseRecord prepareCreate() {
        selected = new CaseRecord();
        initializeEmbeddableKey();
        return selected;
    }

    public void create() {
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/BundlePt").getString("PatientCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public void update() {
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/BundlePt").getString("PatientUpdated"));
    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/BundlePt").getString("PatientDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public List<CaseRecord> getItems() {
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
                    JsfUtil.addErrorMessage(ex, ResourceBundle.getBundle("/BundlePt").getString("PersistenceErrorOccured"));
                }
            } catch (Exception ex) {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
                JsfUtil.addErrorMessage(ex, ResourceBundle.getBundle("/BundlePt").getString("PersistenceErrorOccured"));
            }
        }
    }

    public CaseRecord getPatient(java.lang.Long id) {
        return getFacade().find(id);
    }

    public List<CaseRecord> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<CaseRecord> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }

    @FacesConverter(forClass = CaseRecord.class)
    public static class PatientControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            CaseRecordController controller = (CaseRecordController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "caseRecordController");
            return controller.getPatient(getKey(value));
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
            if (object instanceof CaseRecord) {
                CaseRecord o = (CaseRecord) object;
                return getStringKey(o.getId());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), CaseRecord.class.getName()});
                return null;
            }
        }

    }

}
