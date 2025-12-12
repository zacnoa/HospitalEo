package org.noa.hospitaleo.entity;


import org.noa.hospitaleo.enums.PatientStatus;

import java.util.Map;
import java.util.Scanner;

public class UnderagePatient extends Patient{
    private Visitor legalGuardian;

    @Override
    public void basicInformation()
    {
        System.out.println("Maloljetni pacijent: "+ this.getName()+ " OIB: "+ this.getOib() + " Skrbnik: " + this.getLegalGuardian().getName());
    }


    private UnderagePatient(UnderagePatientBuilder builder)
    {
        super(builder);
        this.legalGuardian=builder.legalGuardian;
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



    public static class UnderagePatientBuilder extends PatientBuilder<UnderagePatientBuilder>
    {
        Visitor legalGuardian;

        public UnderagePatientBuilder(String name, String OIB, String diagnosis, PatientStatus status)
        {
            super(name,OIB,diagnosis,status);
        }

        public UnderagePatientBuilder legalGuardian(Visitor legalGuardian)
        {
            this.legalGuardian=legalGuardian;
            return this;
        }

        @Override
        public UnderagePatient build()
        {
            return new UnderagePatient(this);
        }



    }

}

