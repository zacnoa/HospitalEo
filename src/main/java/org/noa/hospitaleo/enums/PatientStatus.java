package org.noa.hospitaleo.enums;

public enum PatientStatus {

    HOSPITALIZED("Hospitaliziran"),
    DISCHARGED("Otpušten"),
    UNDER_DIAGNOSIS("Na pregledu");


    private final String status;

    PatientStatus(String status){
        this.status=status;
    }

    public String getValue() {
        return switch (this) {
            case HOSPITALIZED -> "Hospitaliziran";
            case DISCHARGED -> "Otpušten";
            case UNDER_DIAGNOSIS -> "Na pregledu";
        };
    }
    public static PatientStatus fromValue(String value) {
        return switch (value) {
            case "Hospitaliziran" -> HOSPITALIZED;
            case "Otpušten" -> DISCHARGED;
            case "Na pregledu" -> UNDER_DIAGNOSIS;
            default -> null;
        };
    }



}
