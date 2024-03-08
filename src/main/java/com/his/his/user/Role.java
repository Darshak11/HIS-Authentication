package com.his.his.user;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import static com.his.his.user.Permission.*;

@RequiredArgsConstructor
public enum Role {

  USER(Collections.emptySet()),
  ADMIN(
          Set.of(
                  ADMIN_READ,
                  ADMIN_UPDATE,
                  ADMIN_DELETE,
                  ADMIN_CREATE,
                  DEP_DELETE,
                  DEP_READ,
                  DEP_UPDATE
          )
  ),
  DESK(
          Set.of(
                  DESK_READ,
                  DESK_UPDATE,
                  DESK_DELETE,
                  DESK_CREATE
          )
  ),
  DOCTOR(
          Set.of(
                  DOCTOR_READ,
                  DOCTOR_UPDATE,
                  DOCTOR_CREATE,
                  PATIENT_READ,
                  PATIENT_UPDATE,
                  PATIENT_CREATE
          )
  ),
  PHARAMACIST(
          Set.of(
                  PRECRIPTION_READ
          )
  ),
  HEAD_NURSE(
          Set.of(
                  NURSE_READ,
                  NURSE_CREATE,
                  NURSE_UPDATE,
                  PATIENT_READ,
                  PATIENT_UPDATE,
                  PATIENT_CREATE
          )
  ),
  NURSE(
          Set.of(
                  NURSE_READ,
                  PATIENT_READ,
                  PATIENT_UPDATE,
                  PATIENT_CREATE
          )
  )

  ;

  @Getter
  private final Set<Permission> permissions;

  public List<SimpleGrantedAuthority> getAuthorities() {
    var authorities = getPermissions()
            .stream()
            .map(permission -> new SimpleGrantedAuthority(permission.getPermission()))
            .collect(Collectors.toList());
    authorities.add(new SimpleGrantedAuthority("ROLE_" + this.name()));
    return authorities;
  }
}
