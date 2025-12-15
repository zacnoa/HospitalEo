package org.noa.hospitaleo.entity;


public abstract class Employee extends Person {

    private Double salary;

    protected Employee(String name, String oib, Double salary) {
        super(name, oib);
        this.salary = salary;
    }


    public Double getSalary() {
        return salary;
    }

    public void setSalary(Double salary) {
        this.salary = salary;
    }
}


