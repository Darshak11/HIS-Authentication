package com.his.his;

import static com.his.his.user.Role.ADMIN;
import static com.his.his.user.Role.DESK;
import static com.his.his.user.Role.DOCTOR;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.his.his.user.User;
import com.his.his.user.UserRepository;

@SpringBootApplication
public class HisApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(HisApplication.class, args);
	}

	@Autowired
	private  UserRepository employeeRepository;

	@Override
	public void run(String... args) throws Exception {
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

		User employee=new User();
		employee.setDateOfBirth("12/05/12");
		employee.setName("Karan");
		employee.setLastCheckIn("1:02");
		employee.setPassword(encoder.encode("1234"));
		employee.setEmployeeStatus(User.EmployeeStatus.CHECKED_IN);
		employee.setRole(DOCTOR);
		employeeRepository.save(employee);
		
		User employee1=new User();
		employee1.setDateOfBirth("12/03/12");
		employee1.setName("Darshak");
		employee1.setLastCheckIn("1:02");
		employee1.setPassword(encoder.encode("1234"));
		employee1.setEmployeeStatus(User.EmployeeStatus.CHECKED_OUT);
		employee1.setRole(ADMIN);
		employeeRepository.save(employee1);

		User desk=new User();
		desk.setDateOfBirth("12/03/12");
		desk.setName("Darshak");
		desk.setLastCheckIn("1:02");
		desk.setPassword(encoder.encode("1234"));
		desk.setEmployeeStatus(User.EmployeeStatus.CHECKED_OUT);
		desk.setRole(DESK);
		employeeRepository.save(desk);



		System.out.println("Id for Doctor is "+employee.getEmployeeId().toString());
		System.out.println("Id for ADMIN is "+employee1.getEmployeeId().toString());
		System.out.println("Id for ADMISSION DESK is "+desk.getEmployeeId().toString());
	}

	

}
