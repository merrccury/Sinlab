package by.alekseichik.demo.model;

import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Set;
import java.util.stream.Collectors;

public enum Role {
    ADMIN(Set.of(Permission.PATIENT_WRITE, Permission.PATIENT_READ, Permission.PATIENT_DELETE,
            Permission.DIAGNOSES_READ, Permission.ADMIN_LOAD)),
    DOCTOR(Set.of(Permission.PATIENT_WRITE, Permission.PATIENT_READ,
            Permission.DIAGNOSES_READ, Permission.DOCTOR_LOAD)),
    PATIENT(Set.of(Permission.PATIENT_READ,
            Permission.DIAGNOSES_READ, Permission.PATIENT_LOAD));

    private final Set<Permission> permissions;

    Role(Set<Permission> permissions) {
        this.permissions = permissions;
    }

    public Set<Permission> getPermissions(){
        return permissions;
    }

    public Set<SimpleGrantedAuthority> getAuthorities(){
        return getPermissions().stream()
                .map(permission -> new SimpleGrantedAuthority(permission.getPermission()))
                .collect(Collectors.toSet());
    }
}
