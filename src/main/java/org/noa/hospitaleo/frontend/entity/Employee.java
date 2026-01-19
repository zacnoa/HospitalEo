package org.noa.hospitaleo.frontend.entity;


import java.util.UUID;

public abstract class Employee extends Person {

    private Double salary;

    protected Employee(String name, String oib, Double salary) {
        super(name, oib);
        this.salary = salary;
    }
    protected Employee(String name, String oib, Double salary,UUID id) {
        super(name, oib,id);
    }


    public Double getSalary() {
        return salary;
    }

    public void setSalary(Double salary) {
        this.salary = salary;
    }
}


