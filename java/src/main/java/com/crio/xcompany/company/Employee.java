package com.crio.xcompany.company;
import java.util.ArrayList;
import java.util.List;

public class Employee {
    private String name;
    private Gender gender;
    boolean isManager;
    List<Employee> reportees;
    Employee manager;

    public Employee(String name, Gender gender) {
        this.name=name;
        this.gender=gender;
        this.isManager=false;
        this.reportees= new ArrayList<>();
        this.manager=null;
    }

    public String getName() {
        return name;
    }

    public Gender getGender() {
        return gender;
    }
    public void setManager (Employee mngr){
        this.manager=mngr;
    }

    // TODO: CRIO_TASK_MODULE_XCOMPANY
    // Please define all the methods required here as mentioned in the XCompany BuildOut Milestone before implementing the logic.
    // This will ensure that the project can be compiled successfully.


    @Override
    public String toString() {
        return "Employee [name=" + name + ", gender=" + gender + "]";
    }   
    
}
