package org.noa.hospitaleo.entity;


import org.noa.hospitaleo.enums.PatientStatus;



public class UnderagePatient extends Patient{
    private Visitor legalGuardian;



    public UnderagePatient(String name,String oib,String diagnosis,String doctorId,String roomId,Visitor legalGuardian,PatientStatus status)
    {
        super(name, oib, diagnosis,doctorId,roomId, status);
        this.legalGuardian=legalGuardian;
    }


    public Visitor getLegalGuardian()
    {
        return legalGuardian;
    }
    public void setLegalGuardian(Visitor legalGuardian)
    {
        this.legalGuardian =legalGuardian;
    }

}

