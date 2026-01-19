package org.noa.hospitaleo.frontend.entity;


import org.noa.hospitaleo.frontend.enums.PatientStatus;

import java.util.UUID;


public class UnderagePatient extends Patient{
    private Visitor legalGuardian;



    public UnderagePatient(String name, String oib, String diagnosis, UUID doctorId, UUID roomId, Visitor legalGuardian, PatientStatus status)
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

