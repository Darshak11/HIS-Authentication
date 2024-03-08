package com.his.his.auth;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.his.his.user.Role;
import com.his.his.user.User.EmployeeStatus;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest 
{
    private String username;
    private String dateOfBirth;
    private String lastCheckIn;
    private String password;
    private EmployeeStatus employeeStatus;
    private Role role;
}
