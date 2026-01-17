//package org.noa.hospitaleo.backend;
//
//import org.noa.hospitaleo.entity.*;
//import org.noa.hospitaleo.enums.PatientStatus;
//
//public class MockCache extends EntityRepository  {
//
//    public void loadAll() {
//
//
//        Department neurology = new Department("Neurologija");
//        Department cardiology = new Department("Kardiologija");
//
//        super.getDepartmentMap().put(neurology.getId(), neurology);
//        super.getDepartmentMap().put(cardiology.getId(), cardiology);
//
//
//        Doctor neuroDoc1 = new Doctor(
//                "Ivan Barišić", "12232144", "neurolog", 12.5
//        );
//
//        neurology.addDoctor(neuroDoc1);
//        super.getDoctorMap().put(neuroDoc1.getId(), neuroDoc1);
//
//        Room neuroRoom1 = new Room("Neuro soba 1");
//        neurology.addRoom(neuroRoom1);
//        super.getRoomMap().put(neuroRoom1.getId(), neuroRoom1);
//
//        Patient neuroPatient1 = new Patient(
//                "Luka Lukačević",
//                "129495",
//                "Java",
//                neuroDoc1.getId(),
//                neuroRoom1.getId(),
//                PatientStatus.HOSPITALIZED
//        );
//
//        neurology.addPatient(neuroPatient1);
//        neuroDoc1.addPatient(neuroPatient1);
//        neuroRoom1.addPatient(neuroPatient1);
//        super.getPatientMap().put(neuroPatient1.getId(), neuroPatient1);
//
//
//        Doctor neuroDoc2 = new Doctor(
//                "Marija Novak", "98765432", "neurolog", 10.0
//        );
//
//        neurology.addDoctor(neuroDoc2);
//        super.getDoctorMap().put(neuroDoc2.getId(), neuroDoc2);
//
//        Room neuroRoom2 = new Room("Neuro soba 2");
//        neurology.addRoom(neuroRoom2);
//        super.getRoomMap().put(neuroRoom2.getId(), neuroRoom2);
//
//        Patient neuroPatient2 = new Patient(
//                "Ana Anić",
//                "559912",
//                "Migrena",
//                neuroDoc2.getId(),
//                neuroRoom2.getId(),
//                PatientStatus.UNDER_DIAGNOSIS
//        );
//
//        neurology.addPatient(neuroPatient2);
//        neuroDoc2.addPatient(neuroPatient2);
//        neuroRoom2.addPatient(neuroPatient2);
//        super.getPatientMap().put(neuroPatient2.getId(), neuroPatient2);
//
//
//        Doctor cardioDoc1 = new Doctor(
//                "Marko Horvat", "22334455", "kardiolog", 15.0
//        );
//
//        cardiology.addDoctor(cardioDoc1);
//        super.getDoctorMap().put(cardioDoc1.getId(), cardioDoc1);
//
//        Room cardioRoom1 = new Room("Kardio soba 1");
//        cardiology.addRoom(cardioRoom1);
//        super.getRoomMap().put(cardioRoom1.getId(), cardioRoom1);
//
//        Patient cardioPatient1 = new Patient(
//                "Petar Perić",
//                "778899",
//                "Aritmija",
//                cardioDoc1.getId(),
//                cardioRoom1.getId(),
//                PatientStatus.HOSPITALIZED
//        );
//
//        cardiology.addPatient(cardioPatient1);
//        cardioDoc1.addPatient(cardioPatient1);
//        cardioRoom1.addPatient(cardioPatient1);
//        super.getPatientMap().put(cardioPatient1.getId(), cardioPatient1);
//
//
//        Doctor cardioDoc2 = new Doctor(
//                "Ivana Križman", "66778899", "kardiolog", 13.0
//        );
//
//        cardiology.addDoctor(cardioDoc2);
//        super.getDoctorMap().put(cardioDoc2.getId(), cardioDoc2);
//
//        Room cardioRoom2 = new Room("Kardio soba 2");
//        cardiology.addRoom(cardioRoom2);
//        super.getRoomMap().put(cardioRoom2.getId(), cardioRoom2);
//
//        Patient cardioPatient2 = new Patient(
//                "Marija Marinković",
//                "443322",
//                "Hipertenzija",
//                cardioDoc2.getId(),
//                cardioRoom2.getId(),
//                PatientStatus.UNDER_DIAGNOSIS
//        );
//
//        cardiology.addPatient(cardioPatient2);
//        cardioDoc2.addPatient(cardioPatient2);
//        cardioRoom2.addPatient(cardioPatient2);
//        super.getPatientMap().put(cardioPatient2.getId(), cardioPatient2);
//    }
//}
