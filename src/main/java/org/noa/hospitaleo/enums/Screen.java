package org.noa.hospitaleo.enums;

public enum Screen {

    // MAIN STAGE screens
    MAIN_SCREEN(
            "main-screen.fxml",
            "Home",
            "Došlo je do greške pri prikazivanju glavnog ekrana",
            true
    ),
    DOCTOR_SEARCH(
            "doctor-search-screen.fxml",
            "Doctor Search",
            "Došlo je do greške pri prikazivanju ekrana za upravljanje doktorima",
            true
    ),
    PATIENT_SEARCH(
            "patient-search-screen.fxml",
            "Patient Search",
            "Došlo je do greške pri prikazivanju ekrana za upravljanje pacijentima",
            true
    ),
    DEPARTMENT_OVERVIEW(
            "department-overview-screen.fxml",
            "Department Overview",
            "Došlo je do greške pri prikazivanju ekrana za pregled odjeljka",
            true
    ),
    LOG_OVERVIEW(
            "log-overview-screen.fxml",
            "Log Overview",
            "Došlo je do greške pri prikazivanju ekrana za pregled loga",
            true
    ),

    // NEW STAGE (dialog / popup) screens
    PATIENT_CREATION(
            "patient-creation-screen.fxml",
            "Patient Creation",
            "Došlo je do greške pri prikazivanju ekrana za kreiranje pacijenta",
            false
    ),
    DOCTOR_CREATION(
            "doctor-creation-screen.fxml",
            "Doctor Creation",
            "Došlo je do greške pri prikazivanju ekrana za kreiranje doktora",
            false
    ),
    ROOM_CREATION(
            "room-creation-screen.fxml",
            "Room Creation",
            "Došlo je do greške pri prikazivanju ekrana za kreiranje soba",
            false
    ),
    DEPARTMENT_CREATION(
            "department-creation-screen.fxml",
            "Department Creation",
            "Došlo je do greške pri prikazivanju ekrana za kreiranje odjela",
            false
    );

    private final String fxml;
    private final String title;
    private final String errorMessage;
    private final boolean useMainStage;

    Screen(String fxml, String title, String errorMessage, boolean useMainStage) {
        this.fxml = fxml;
        this.title = title;
        this.errorMessage = errorMessage;
        this.useMainStage = useMainStage;
    }

    public String getFxml() {
        return fxml;
    }

    public String getTitle() {
        return title;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public boolean isUseMainStage() {
        return useMainStage;
    }
}
