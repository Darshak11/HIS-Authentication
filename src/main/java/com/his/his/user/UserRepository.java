package com.his.his.user;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.UUID;

 // Import the JpaRepository class
public interface UserRepository extends JpaRepository<User, UUID>
{
    Optional<User> findByEmployeeId(UUID employeeId);
}
