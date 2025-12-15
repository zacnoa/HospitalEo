package org.noa.hospitaleo.entity;


import org.noa.hospitaleo.enums.PatientStatus;



public class UnderagePatient extends Patient{
    private Visitor legalGuardian;



    public UnderagePatient(String name,String oib,String diagnosis,Visitor legalGuardian,PatientStatus status)
    {
        super(name, oib, diagnosis, status);
        this.legalGuardian=legalGuardian;
    }
    public UnderagePatient(){}

    public Visitor getLegalGuardian()
    {
        return legalGuardian;
    }
    public void setLegalGuardian(Visitor legalGuardian)
    {
        this.legalGuardian =legalGuardian;
    }

}

