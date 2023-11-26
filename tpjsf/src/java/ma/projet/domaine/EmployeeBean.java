/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ma.projet.domaine;

import java.util.List;
import javax.faces.application.FacesMessage;
import ma.projet.beans.Employee;
import ma.projet.beans.Service;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import ma.projet.service.EmployeeService;
import ma.projet.service.ServiceService;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.event.RowEditEvent;
import org.primefaces.model.UploadedFile;
import org.primefaces.model.chart.CartesianChartModel;
import org.primefaces.model.chart.ChartModel;
import org.primefaces.model.chart.ChartSeries;

/**
 *
 * @author hp
 */
@ManagedBean(name = "employeBean")
public class EmployeeBean {

    private Employee employe;

    private Service service;
    private List<Employee> employes;
    private EmployeeService employeService;
    private ServiceService serviceService;
    private static ChartModel barModel;
  
    public EmployeeBean() {
        employe = new Employee();
        employe.setEmployee(new Employee());
        employeService = new EmployeeService();
        serviceService = new ServiceService();

    }

    public List<Employee> getEmployees() {
        if (employes == null) {
            employes = employeService.getAll();
        }
        return employes;
    }

    public void setEmployees(List<Employee> employes) {
        this.employes = employes;
    }

    public EmployeeService getEmployeeService() {
        return employeService;
    }

    public void setEmployeeService(EmployeeService employeService) {
        this.employeService = employeService;
    }

    public Employee getEmployee() {
        return employe;
    }

    public void setEmployee(Employee employe) {
        this.employe = employe;
    }
    public String onCreateAction() {
        employeService.create(employe);
        employe = new Employee();
        employe.setEmployee(new Employee());
        return null;
    }

  public String onDeleteAction() {
    employeService.delete(employe); // Use the selected employee directly
    return null;
}

    public List<Employee> serviceLoad() {
        for (Employee m : employeService.getAll()) {
            if (m.getService().equals(service)) {
                employes.add(m);
            }
        }
        return employes;

    }

    public void load() {
        System.out.println(service.getNom());
        service = serviceService.getById((int) service.getId());
        getEmployee();
    }

    public void onEdit(RowEditEvent event) {
        employe = (Employee) event.getObject();
        Service service = serviceService.getById((int) this.employe.getService().getId());
        employe.setService(service);
        employe.getService().setNom(service.getNom());
        employeService.update(employe);
    }
     public void onCancel(RowEditEvent event) {
    }

    public ChartModel getBarModel() {
        return barModel;
    }
    
    public Service getService() {
        return service;
    }

    public void setService(Service service) {
        this.service = service;
    }
    
    public void handleFileUpload(FileUploadEvent event) {
    UploadedFile uploadedFile = event.getFile();
    // Traitement du fichier, vous pouvez le sauvegarder sur le disque ou en base de données
    // Assurez-vous de stocker le chemin du fichier correctement dans votre modèle d'employé.
    // employeBean.employee.setPhoto(cheminDuFichier);

    FacesMessage message = new FacesMessage("Succès", event.getFile().getFileName() + " a été téléchargé.");
    FacesContext.getCurrentInstance().addMessage(null, message);
}


    public ChartModel initBarModel() {
        CartesianChartModel model = new CartesianChartModel();
        ChartSeries employes = new ChartSeries();
        employes.setLabel("employes");
        model.setAnimate(true);

        for (Object[] e : employeService.nbemployes()) {
            employes.set((String) e[1], Integer.parseInt(e[0].toString()));
        }

        model.addSeries(employes);

        return model;
    }

   

    }