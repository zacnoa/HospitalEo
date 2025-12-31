package org.noa.hospitaleo.repository;

import org.noa.hospitaleo.entity.*;
import org.noa.hospitaleo.enums.PatientStatus;

public class MockEntityRepository extends EntityRepository implements Loadable {

    @Override
    public void loadAll() {

        Department d1 = new Department("Neurologija");
        Department d2 = new Department("Kardiologija");

        departmentMap.put(d1.getId(),d1);
        departmentMap.put(d2.getId(),d2);



        Doctor d1Doc1 = new Doctor.DoctorBuilder("Ivan Barisic", "12232144", "neurolog", 12.5).build();
        d1.addDoctor(d1Doc1);


        Room d1Room1 = new Room();
        d1.addRoom(d1Room1);

        Patient d1Patient1 = new Patient("Luka Lukacevic", "129495", "java", PatientStatus.HOSPITALIZED);
        d1Patient1.setDoctor(d1Doc1);
        d1Patient1.setRoom(d1Room1);
        d1.addPatient(d1Patient1);


        doctorMap.put(d1Doc1.getId(),  d1Doc1);
        roomMap.put(d1Room1.getId(),  d1Room1);
        patientMap.put(d1Patient1.getId(),  d1Patient1);


        Doctor d1Doc2 = new Doctor.DoctorBuilder("Marija Novak", "98765432", "neurolog", 10.0).build();
        d1.addDoctor(d1Doc2);

        Room d1Room2 = new Room();
        d1.addRoom(d1Room2);

        Patient d1Patient2 = new Patient("Ana Anic", "559912", "migrena", PatientStatus.UNDER_DIAGNOSIS);
        d1Patient2.setDoctor(d1Doc2);
        d1Patient2.setRoom(d1Room2);
        d1.addPatient(d1Patient2);

        doctorMap.put(d1Doc2.getId(),  d1Doc2);
        roomMap.put(d1Room2.getId(),  d1Room2);
        patientMap.put(d1Patient2.getId(),  d1Patient2);


        Doctor d2Doc1 = new Doctor.DoctorBuilder("Marko Horvat", "22334455", "kardiolog", 15.0).build();
        d2.addDoctor(d2Doc1);

        Room d2Room1 = new Room();
        d2.addRoom(d2Room1);

        Patient d2Patient1 = new Patient("Petar Peric", "778899", "aritmija", PatientStatus.HOSPITALIZED);
        d2Patient1.setDoctor(d2Doc1);
        d2Patient1.setRoom(d2Room1);
        d2.addPatient(d2Patient1);


        doctorMap.put(d2Doc1.getId(),  d2Doc1);
        roomMap.put(d2Room1.getId(),  d2Room1);
        patientMap.put(d2Patient1.getId(),  d2Patient1);


        Doctor d2Doc2 = new Doctor.DoctorBuilder("Ivana Krizman", "66778899", "kardiolog", 13.0).build();
        d2.addDoctor(d2Doc2);

        Room d2Room2 = new Room();
        d2.addRoom(d2Room2);

        Patient d2Patient2 = new Patient("Marija Marinkovic", "443322", "hipertenzija", PatientStatus.UNDER_DIAGNOSIS);
        d2Patient2.setDoctor(d2Doc2);
        d2Patient2.setRoom(d2Room2);
        d2.addPatient(d2Patient2);

        doctorMap.put(d2Doc2.getId(),  d2Doc2);
        roomMap.put(d2Room2.getId(),  d2Room2);
        patientMap.put(d2Patient2.getId(),  d2Patient2);


    }







}

