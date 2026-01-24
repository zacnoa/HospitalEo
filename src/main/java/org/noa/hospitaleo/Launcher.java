package org.noa.hospitaleo;

import javafx.application.Application;

@SuppressWarnings("java:S1118") // Treba postojati implicitni public konstruktor
public class Launcher {
     static void main(String[] args) {
        Application.launch(HospitalEoApplication.class, args);
    }
}

