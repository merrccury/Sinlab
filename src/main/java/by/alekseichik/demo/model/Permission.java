package by.alekseichik.demo.model;

public enum Permission {
    PATIENT_READ("patients:read"),
    PATIENT_WRITE("patients:write"),
    PATIENT_DELETE("patients:delete"),
    DIAGNOSES_READ("diagnoses:read"),
    PATIENT_LOAD("patient:load"),
    DOCTOR_LOAD("doctor:load"),
    ADMIN_LOAD("admin:load");


    private final String permission;

    Permission(String permission) {
        this.permission = permission;
    }

    public String getPermission(){
        return permission;
    }
}
