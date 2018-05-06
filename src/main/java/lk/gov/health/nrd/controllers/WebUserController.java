package lk.gov.health.nrd.controllers;

import lk.gov.health.nrd.entity.WebUser;
import lk.gov.health.nrd.controllers.util.JsfUtil;
import lk.gov.health.nrd.controllers.util.JsfUtil.PersistAction;
import lk.gov.health.nrd.facades.WebUserFacade;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.enterprise.context.SessionScoped;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.inject.Inject;
import javax.inject.Named;
import lk.gov.health.nrd.entity.Department;
import lk.gov.health.nrd.entity.Institute;
import lk.gov.health.nrd.entity.UserDepartment;
import lk.gov.health.nrd.entity.UserInstitute;
import lk.gov.health.nrd.entity.UserPrivilege;
import lk.gov.health.nrd.enums.Privilege;

@Named
@SessionScoped
public class WebUserController implements Serializable {

    @EJB
    private WebUserFacade ejbFacade;

    @Inject
    UserDepartmentController userDepartmentController;
    @Inject
    UserInstituteController userInstituteController;
    @Inject
    UserPrivilegeController userPrivilegeController;
    @Inject
    InstituteController instituteController;
    @Inject
    DepartmentController departmentController;

    private List<WebUser> items = null;
    private WebUser selected;

    String userName;
    String password;

    WebUser loggedUser;
    Institute loggedInstitute;
    Department loggedDepartment;
    List<UserPrivilege> loggedUserPrivileges;
    List<Institute> loggableInstitutes;
    List<Department> loggableDepartments;
    boolean canAddCase;
    boolean canSearchAnyCase;
    boolean canEditCase;
    boolean canDeleteCase;
    boolean canInstituteManagement;
    boolean canSystemManagement;
    boolean canSearchAreaCases;
    boolean canSearchInstituteCases;
    boolean canSystemAdministration;

    public String toManageUsers() {
        return "/webUser/index";
    }

    
    public String toAddNewUser() {
        selected = new WebUser();
        selected.setInstitute(loggedInstitute);
        selected.setDepartment(loggedDepartment);
        selected.setActive(true);
        return "/webUser/add_new_user";
    }
    
    

    public String toManagePrivilege() {
//        userPrivilegeController.setWebUser(selected);
        return "/webUser/manage_privileges";
    }
    
    public String toManageInstitutes() {
        return "/webUser/manage_institutes";
    }
    
    public String saveNewUser() {
        if (selected == null) {
            JsfUtil.addErrorMessage("Error");
            return "";
        }
        if (selected.getUserName() == null || selected.getUserName().trim().equals("")) {
            JsfUtil.addErrorMessage("Please enter a username");
            return "";
        }
        if (selected.getName() == null || selected.getName().trim().equals("")) {
            JsfUtil.addErrorMessage("Please enter a Name");
            return "";
        }
        if (selected.getPassword() == null || selected.getPassword().trim().equals("")) {
            JsfUtil.addErrorMessage("Please enter a Password");
            return "";
        }
        if (!selected.getPassword().equals(selected.getRepeatPassword())) {
            JsfUtil.addErrorMessage("Passwords are NOT maching");
            return "";
        }
        if (selected.getUserName().length() < 5) {
            JsfUtil.addErrorMessage("Please use a longer username");
            return "";
        }
        if (selected.getPassword().length() < 6) {
            JsfUtil.addErrorMessage("Please use a longer password");
        }
        try {
            getFacade().create(selected);
        } catch (Exception e) {
            JsfUtil.addErrorMessage("That username already exists. Please use another username");
            System.out.println("e = " + e);
            return "";
        }

        UserDepartment ud = new UserDepartment();
        ud.setDepartment(loggedDepartment);
        ud.setWebUser(selected);
        getUserDepartmentController().createOrUpdate(ud);

        UserInstitute ui = new UserInstitute();
        ui.setInstitute(loggedInstitute);
        ui.setWebUser(selected);
        getUserInstituteController().createOrUpdate(ui);

        UserPrivilege ui1 = new UserPrivilege();
        ui1.setWebUser(selected);
        ui1.setPrivilege(Privilege.Add_Case);
        getUserPrivilegeController().createOrUpdate(ui1);

//        UserPrivilege ui2 = new UserPrivilege();
//        ui2.setWebUser(selected);
//        ui2.setPrivilege(Privilege.Delete_Case);
//        getUserPrivilegeController().createOrUpdate(ui2);

        UserPrivilege ui3 = new UserPrivilege();
        ui3.setWebUser(selected);
        ui3.setPrivilege(Privilege.Search_institute_Cases);
        getUserPrivilegeController().createOrUpdate(ui3);

//        UserPrivilege ui4 = new UserPrivilege();
//        ui4.setWebUser(selected);
//        ui4.setPrivilege(Privilege.Search_Area_Cases);
//        getUserPrivilegeController().createOrUpdate(ui4);

//        UserPrivilege ui5 = new UserPrivilege();
//        ui5.setWebUser(selected);
//        ui5.setPrivilege(Privilege.System_Management);
//        getUserPrivilegeController().createOrUpdate(ui5);

//        UserPrivilege ui6 = new UserPrivilege();
//        ui6.setWebUser(selected);
//        ui6.setPrivilege(Privilege.Search_institute_Cases);
//        getUserPrivilegeController().createOrUpdate(ui6);

        UserPrivilege ui7 = new UserPrivilege();
        ui7.setWebUser(selected);
        ui7.setPrivilege(Privilege.Edit_Case);
        getUserPrivilegeController().createOrUpdate(ui7);

//        UserPrivilege ui8 = new UserPrivilege();
//        ui8.setWebUser(selected);
//        ui8.setPrivilege(Privilege.System_administration);
//        getUserPrivilegeController().createOrUpdate(ui8);

//        UserPrivilege ui9 = new UserPrivilege();
//        ui9.setWebUser(loggedUser);
//        ui9.setPrivilege(Privilege.Institute_Management);
//        getUserPrivilegeController().createOrUpdate(ui9);

        return "/webUser/index";
    }

    
    public String toViewUsers(){
        items=null;
        getItems();
        return "/webUser/view_users";
    }
    
    
    private void createLoginData() {
        System.out.println("createLoginData");
        System.out.println("loggedUser = " + loggedUser);
        if (loggedUser == null) {
            System.out.println("Logged User Null");
            return;
        }
        loggedUserPrivileges = getUserPrivilegeController().getItems(loggedUser);
        System.out.println("loggedUserPrivileges = " + loggedUserPrivileges);
        canAddCase = hasPrivilege(Privilege.Add_Case);
        canDeleteCase = hasPrivilege(Privilege.Delete_Case);
        canEditCase = hasPrivilege(Privilege.Edit_Case);
        canSearchAreaCases = hasPrivilege(Privilege.Search_Area_Cases);
        canSystemManagement = hasPrivilege(Privilege.System_Management);
        canSearchInstituteCases = hasPrivilege(Privilege.Search_institute_Cases);
        canInstituteManagement = hasPrivilege(Privilege.Institute_Management);
        canSearchAnyCase = hasPrivilege(Privilege.Search_Any_Case);
        canSystemAdministration = hasPrivilege(Privilege.System_administration);

        String jpql;
        Map m = new HashMap();
        System.out.println("isCanSystemAdministration = " + isCanSystemAdministration());
        

        System.out.println("going to get loggable institutions");
        if (isCanSystemAdministration()) {
            System.out.println("can sys admin");
            loggableInstitutes = getInstituteController().getAllItems();
        } else {
            System.out.println("can not sys admin");
            m = new HashMap();
            jpql = "Select distinct(ud.institute) "
                    + " from UserInstitute ud "
                    + " where ud.webUser=:wu"
                    + " order by ud.institute.name";
            m.put("wu", loggedUser);
            loggableInstitutes = instituteController.getItems(jpql, m);

        }
        if (loggableInstitutes == null || loggableInstitutes.isEmpty()) {
            JsfUtil.addErrorMessage("You have no privilege to log to any institute.");
            return;
        }
        if (loggableInstitutes.size() == 1) {
            loggedInstitute = loggableInstitutes.get(0);
        }
        loggableDepartments = getDepartmentController().getItems();
    }

    
    
    
    private boolean hasPrivilege(Privilege p) {
        boolean b = false;
        for (UserPrivilege up : getLoggedUserPrivileges()) {
            if (p.equals(up.getPrivilege())) {
                b = true;
            }
        }
        return b;
    }

    public boolean isCanAddCase() {
        return canAddCase;
    }

    public void setCanAddCase(boolean canAddCase) {
        this.canAddCase = canAddCase;
    }

    public boolean isCanSearchAnyCase() {
        return canSearchAnyCase;
    }

    public void setCanSearchAnyCase(boolean canSearchAnyCase) {
        this.canSearchAnyCase = canSearchAnyCase;
    }

    public boolean isCanEditCase() {
        return canEditCase;
    }

    public void setCanEditCase(boolean canEditCase) {
        this.canEditCase = canEditCase;
    }

    public boolean isCanDeleteCase() {
        return canDeleteCase;
    }

    public void setCanDeleteCase(boolean canDeleteCase) {
        this.canDeleteCase = canDeleteCase;
    }

    public boolean isCanInstituteManagement() {
        return canInstituteManagement;
    }

    public void setCanInstituteManagement(boolean canInstituteManagement) {
        this.canInstituteManagement = canInstituteManagement;
    }

    public boolean isCanSystemManagement() {
        return canSystemManagement;
    }

    public void setCanSystemManagement(boolean canSystemManagement) {
        this.canSystemManagement = canSystemManagement;
    }

    public boolean isCanSearchAreaCases() {
        return canSearchAreaCases;
    }

    public void setCanSearchAreaCases(boolean canSearchAreaCases) {
        this.canSearchAreaCases = canSearchAreaCases;
    }

    public boolean isCanSearchInstituteCases() {
        return canSearchInstituteCases;
    }

    public void setCanSearchInstituteCases(boolean canSearchInstituteCases) {
        this.canSearchInstituteCases = canSearchInstituteCases;
    }

    public boolean isCanSystemAdministration() {
        return canSystemAdministration;
    }

    public void setCanSystemAdministration(boolean canSystemAdministration) {
        this.canSystemAdministration = canSystemAdministration;
    }

    public void setLoggedInstitute(Institute loggedInstitute) {
        this.loggedInstitute = loggedInstitute;
    }

    public Institute getLoggedInstitute() {
        return loggedInstitute;
    }

    public Department getLoggedDepartment() {
        return loggedDepartment;
    }

    public void setLoggedDepartment(Department loggedDepartment) {
        this.loggedDepartment = loggedDepartment;
    }

    public List<UserPrivilege> getLoggedUserPrivileges() {
        return loggedUserPrivileges;
    }

    public void setLoggedUserPrivileges(List<UserPrivilege> loggedUserPrivileges) {
        this.loggedUserPrivileges = loggedUserPrivileges;
    }

    public List<Institute> getLoggableInstitutes() {
        return loggableInstitutes;
    }

    public void setLoggableInstitutes(List<Institute> loggableInstitutes) {
        this.loggableInstitutes = loggableInstitutes;
    }

    public List<Department> getLoggableDepartments() {
        return loggableDepartments;
    }

    public void setLoggableDepartments(List<Department> loggableDepartments) {
        this.loggableDepartments = loggableDepartments;
    }

    private void resetLoginData() {
        loggedUser = null;
        loggedInstitute = null;
        loggedDepartment = null;

        loggableInstitutes = null;

        canAddCase = false;
        canSearchAnyCase = false;
        canEditCase = false;
        canDeleteCase = false;
        canInstituteManagement = false;
        canSystemManagement = false;
        canSearchAreaCases = false;
        canSearchInstituteCases = false;
        canSystemAdministration = false;
        loggedInstitute = null;
        loggedDepartment = null;
        loggedUserPrivileges = new ArrayList<UserPrivilege>();
        loggableInstitutes = new ArrayList<Institute>();
        loggableDepartments = new ArrayList<Department>();
    }

    public String login() {
        resetLoginData();
        if (userName.trim().equals("")) {
            JsfUtil.addErrorMessage("Enter a Username");
            return "";
        }
        if (password.trim().equals("")) {
            JsfUtil.addErrorMessage("Enter a Password");
            return "";
        }
        String j = "select w from WebUser w where w.userName=:un and w.password=:pw";
        Map m = new HashMap();
        m.put("un", userName);
        m.put("pw", password);
        loggedUser = getFacade().findFirstBySQL(j, m);
        if (loggedUser == null) {
            if (getFacade().count() == 0) {
                Institute ins = new Institute();
                ins.setName("Hospital");
                getInstituteController().createOrUpdate(ins);
                Department dep = new Department();
                dep.setInstitute(ins);
                dep.setName("Ward");
                getDepartmentController().createOrUpdate(dep);

                loggedUser = new WebUser();
                loggedUser.setActive(true);
                loggedUser.setEmail("buddhika.ari@gmail.com");
                loggedUser.setExecutiveOfficer(true);
                loggedUser.setName("Buddhika");
                loggedUser.setUserName("buddhika");
                loggedUser.setPassword("buddhika123@");
                loggedUser.setInstitute(ins);
                loggedUser.setDepartment(dep);
                getFacade().create(loggedUser);

                UserDepartment ud = new UserDepartment();
                ud.setDepartment(dep);
                ud.setWebUser(loggedUser);
                getUserDepartmentController().createOrUpdate(ud);

                UserInstitute ui = new UserInstitute();
                ui.setInstitute(ins);
                ui.setWebUser(loggedUser);
                getUserInstituteController().createOrUpdate(ui);

                UserPrivilege ui1 = new UserPrivilege();
                ui1.setWebUser(loggedUser);
                ui1.setPrivilege(Privilege.Add_Case);
                getUserPrivilegeController().createOrUpdate(ui1);

                UserPrivilege ui2 = new UserPrivilege();
                ui2.setWebUser(loggedUser);
                ui2.setPrivilege(Privilege.Delete_Case);
                getUserPrivilegeController().createOrUpdate(ui2);

                UserPrivilege ui3 = new UserPrivilege();
                ui3.setWebUser(loggedUser);
                ui3.setPrivilege(Privilege.Edit_Case);
                getUserPrivilegeController().createOrUpdate(ui3);

                UserPrivilege ui4 = new UserPrivilege();
                ui4.setWebUser(loggedUser);
                ui4.setPrivilege(Privilege.Search_Area_Cases);
                getUserPrivilegeController().createOrUpdate(ui4);

                UserPrivilege ui5 = new UserPrivilege();
                ui5.setWebUser(loggedUser);
                ui5.setPrivilege(Privilege.System_Management);
                getUserPrivilegeController().createOrUpdate(ui5);

                UserPrivilege ui6 = new UserPrivilege();
                ui6.setWebUser(loggedUser);
                ui6.setPrivilege(Privilege.Search_institute_Cases);
                getUserPrivilegeController().createOrUpdate(ui6);

                UserPrivilege ui7 = new UserPrivilege();
                ui7.setWebUser(loggedUser);
                ui7.setPrivilege(Privilege.Search_Any_Case);
                getUserPrivilegeController().createOrUpdate(ui7);

                UserPrivilege ui8 = new UserPrivilege();
                ui8.setWebUser(loggedUser);
                ui8.setPrivilege(Privilege.System_administration);
                getUserPrivilegeController().createOrUpdate(ui8);

                UserPrivilege ui9 = new UserPrivilege();
                ui9.setWebUser(loggedUser);
                ui9.setPrivilege(Privilege.Institute_Management);
                getUserPrivilegeController().createOrUpdate(ui9);

            } else {
                JsfUtil.addErrorMessage("Wrong Credentials. Please try again.");
                return "";
            }
        }
        createLoginData();
        JsfUtil.addErrorMessage("Logged Successfully.");
        return "/index";
    }

    public String logOut() {
        loggedUser = null;
        return "";
    }

    public WebUser getLoggedUser() {
        return loggedUser;
    }

    public void setLoggedUser(WebUser loggedUser) {
        this.loggedUser = loggedUser;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public WebUserController() {
    }

    public WebUser getSelected() {
        return selected;
    }

    public void setSelected(WebUser selected) {
        this.selected = selected;
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    private WebUserFacade getFacade() {
        return ejbFacade;
    }

    public WebUser prepareCreate() {
        selected = new WebUser();
        initializeEmbeddableKey();
        return selected;
    }

    public void create() {
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/Bundle").getString("WebUserCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public void update() {
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/Bundle").getString("WebUserUpdated"));
    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/Bundle").getString("WebUserDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public List<WebUser> getItems() {
        if (items == null) {
            String j = "select u from WebUser u order by u.name";
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
                    JsfUtil.addErrorMessage(ex, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
                }
            } catch (Exception ex) {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
                JsfUtil.addErrorMessage(ex, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
            }
        }
    }

    public List<WebUser> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<WebUser> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }

    public UserDepartmentController getUserDepartmentController() {
        return userDepartmentController;
    }

    public UserInstituteController getUserInstituteController() {
        return userInstituteController;
    }

    public UserPrivilegeController getUserPrivilegeController() {
        return userPrivilegeController;
    }

    public InstituteController getInstituteController() {
        return instituteController;
    }

    public DepartmentController getDepartmentController() {
        return departmentController;
    }

    @FacesConverter(forClass = WebUser.class)
    public static class WebUserControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            WebUserController controller = (WebUserController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "webUserController");
            return controller.getFacade().find(getKey(value));
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
            if (object instanceof WebUser) {
                WebUser o = (WebUser) object;
                return getStringKey(o.getId());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), WebUser.class.getName()});
                return null;
            }
        }

    }

}
