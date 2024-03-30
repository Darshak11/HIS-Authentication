package com.his.his.user;

import java.util.Collection;
import java.util.List;


import org.springframework.security.core.userdetails.UserDetails;

import com.his.his.token.Token;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import org.hibernate.annotations.GenericGenerator;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@JsonIgnoreProperties({"password", "role", "tokens"})
@Table(name = "users")
public class User implements UserDetails
{
    public enum EmployeeStatus {
        CHECKED_IN, CHECKED_OUT
    }

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "org.hibernate.id.UUIDGenerator")
    @Column( updatable = false, nullable = false )
    private UUID employeeId; 
    
    @Column(name = "Name", nullable = false )
    private String name;

    @Column(name = "DateOfBirth", nullable = false )
    private String dateOfBirth;

    @Column(name="Password", nullable = false)
    @JsonIgnore
    private String password;

    @Column(name = "LastCheckIn" )
    private String lastCheckIn;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private EmployeeStatus employeeStatus;

    @JsonIgnore
    @Enumerated(EnumType.STRING)
    private Role role;

    @JsonIgnore
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
