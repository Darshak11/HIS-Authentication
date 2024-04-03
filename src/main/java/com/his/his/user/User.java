package com.his.his.user;

import java.util.Collection;
import java.util.List;


import jakarta.persistence.*;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.his.his.converter.AesEncryptor;
import com.his.his.converter.ObjectCryptoConverter;
import com.his.his.converter.StringCryptoConverter;
import com.his.his.token.Token;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.UUID;

import org.hibernate.annotations.GenericGenerator;
import org.springframework.security.core.GrantedAuthority;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "users")
public class User implements UserDetails
{
    public enum EmployeeStatus {
        CHECKED_IN, CHECKED_OUT
    }

    public enum EmployeeType{
        NURSE,
        DOCTOR,
        HEAD_NURSE,
        PHARMACIST,
        ADMIN,
        ADMISSION_DESK
    }

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "employeeId", updatable = false, nullable = false )
    private UUID employeeId; 
    
    @Convert(converter = ObjectCryptoConverter.class) 
    @Column(name = "Name", nullable = false)
    private String name;

    @Column(name = "DateOfBirth", nullable = false )
    private String dateOfBirth;

    @Column(name="Password", nullable = false)
    private String password;

    @Convert(converter = ObjectCryptoConverter.class) 
    @Column(name = "LastCheckIn" )
    private String lastCheckIn;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private EmployeeStatus employeeStatus;

    @Enumerated(EnumType.STRING)
    private Role role;

    @Enumerated(EnumType.STRING)
    private EmployeeType employeeType;

    @OneToMany(mappedBy = "user",cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<Token> tokens;

    // Implementing UserDetails methods
    @Override
    public String getUsername() {
        // Add your implementation here
        return String.valueOf(employeeId);
    }

    @Override
    public boolean isEnabled() {
        // Add your implementation here
        return true;
    }


    @Override
    public boolean isCredentialsNonExpired() {
        // Add your implementation here
        return true;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // Add your implementation here
        return role.getAuthorities();
    }
    @Override
    public boolean isAccountNonExpired() {
        // Add your implementation here
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        // Add your implementation here
        return true;
    }
}
