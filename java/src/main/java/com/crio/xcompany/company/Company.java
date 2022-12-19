package com.crio.xcompany.company;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Company{
    private String companyName;
    private Employee founder;
    private Map<String,Employee> employeeBook;
    

    private Company(String companyName, Employee founder) {
        this.companyName = companyName;
        this.founder = founder;
        employeeBook = new HashMap<String,Employee>();
        employeeBook.put(founder.getName(), founder);
    }
    

    public static Company create(String companyName, Employee founder){
        return new Company(companyName,founder);
    } 


    public String getCompanyName() {
        return companyName;
    }

    // TODO: CRIO_TASK_MODULE_XCOMPANY
    // Please define all the methods required here as mentioned in the XCompany BuildOut Milestone for each functionality before implementing the logic.
    // This will ensure that the project can be compiled successfully.
    public  void registerEmployee(String employeeName, Gender gender){
        employeeBook.put(employeeName, new Employee(employeeName,gender));
    } 
    public Employee getEmployee(String employeeName){
        return employeeBook.get(employeeName);
    }
    public void assignManager(String employeeName, String managerName){
        Employee mngr = employeeBook.get(managerName);
        Employee reportee = employeeBook.get(employeeName);
        mngr.isManager=true;
        reportee.setManager(mngr);
        mngr.reportees.add(reportee);
    }
    public List<Employee> getDirectReports(String managerName){
        List<Employee> ans = employeeBook.get(managerName).reportees;
        return ans;
    }
    public List<Employee> getTeamMates(String employeeName){
        Employee reportee = employeeBook.get(employeeName);
        Employee mngr = reportee.manager;
        List<Employee> ans = new ArrayList<>();
        ans.add(mngr);
        ans.addAll(mngr.reportees);
        return ans;
    }
    public void deleteEmployee(String employeeName){
        
        Employee emp = employeeBook.get(employeeName);
        if(emp==null){
            return;
        }
        Employee mngr= emp.manager;
        if(emp.isManager){
            for(Employee reportee:emp.reportees){
                if(mngr!=null){
                    reportee.manager=mngr;
                    mngr.reportees.add(reportee);
                }
                else{
                    reportee.manager=null;
                }
            }
        }
        if(mngr!=null){
            mngr.reportees.remove(emp);
        } 
        employeeBook.remove(employeeName);
    }

    public List<List<Employee>> getEmployeeHierarchy(String managerName){
        Employee mngr = employeeBook.get(managerName);
        List<List<Employee>> ans = new ArrayList<>();
        List<Employee> arlist=new ArrayList<>();
        arlist.add(mngr);
        ans.add(arlist);
        if(mngr.isManager){
            ans.add(mngr.reportees); 
            traverseTree(ans, mngr.reportees);
        }
        return ans;
    }

    public void traverseTree(List<List<Employee>> ans, List<Employee> reportees){
        List<Employee> arlist=new ArrayList<>();
        for(Employee em:reportees){
            if(em.isManager){
                arlist.addAll(em.reportees);
            }
        }
        if(arlist.size()!=0){
            ans.add(arlist);
            traverseTree(ans, arlist);
        }
        else{
            return;
        }  
    }

}
