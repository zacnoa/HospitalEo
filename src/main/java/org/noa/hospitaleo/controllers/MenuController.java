package org.noa.hospitaleo.controllers;

import org.noa.hospitaleo.enums.Screen;
import util.ScreenNavigator;

public class MenuController {

    public void showDoctorSearchScreen() {
        ScreenNavigator.ChangeScene(Screen.DOCTOR_SEARCH);
    }

    public void showPatientSearchScreen() {
        ScreenNavigator.ChangeScene(Screen.PATIENT_SEARCH);
    }

    public void showMainScreen() {
        ScreenNavigator.ChangeScene(Screen.MAIN_SCREEN);
    }

    public void showDepartmentOverviewScreen() {
        ScreenNavigator.ChangeScene(Screen.DEPARTMENT_OVERVIEW);
    }

    public void showLogOverviewScreen() {
        ScreenNavigator.ChangeScene(Screen.LOG_OVERVIEW);
    }

    public void showPatientCreationScreen() {
        ScreenNavigator.ChangeScene(Screen.PATIENT_CREATION);
    }

    public void showDoctorCreationScreen() {
        ScreenNavigator.ChangeScene(Screen.DOCTOR_CREATION);
    }

    public void showRoomCreationScreen() {
        ScreenNavigator.ChangeScene(Screen.ROOM_CREATION);
    }

    public void showDepartmentCreationScreen() {
        ScreenNavigator.ChangeScene(Screen.DEPARTMENT_CREATION);
    }
}
