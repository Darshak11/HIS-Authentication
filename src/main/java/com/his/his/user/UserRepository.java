package com.his.his.user;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.UUID;

 // Import the JpaRepository class
public interface UserRepository extends JpaRepository<User, UUID>
{
    Optional<User> findByEmployeeId(UUID employeeId);
    

    
    
}
