package com.his.his;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.his.his.PublicPrivateMapping.PublicPrivateService;
import com.his.his.auth.AuthenticationService;
import com.his.his.user.User;
import com.his.his.user.UserRepository;
import com.his.his.user.User.EmployeeType;

import static com.his.his.user.Role.*;

@SpringBootApplication
public class HisApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(HisApplication.class, args);
	}

	@Autowired
	private  UserRepository employeeRepository;

	@Autowired
	private PublicPrivateService publicPrivateService;

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
		employee.setEmployeeType(EmployeeType.DOCTOR);
		employee.setEmail("kemekm");
		employeeRepository.save(employee);
		publicPrivateService.savePublicPrivateId(employee.getEmployeeId(),employee.getEmployeeType().toString());
		
		User employee1=new User();
		employee1.setDateOfBirth("12/03/12");
		employee1.setName("Darshak");
		employee1.setLastCheckIn("1:02");
		employee1.setPassword(encoder.encode("1234"));
		employee1.setEmployeeStatus(User.EmployeeStatus.CHECKED_IN);
		employee1.setRole(ADMIN);
		employee1.setEmployeeType(EmployeeType.ADMIN);
		employee.setEmail("kemekm");
		employeeRepository.save(employee1);
		publicPrivateService.savePublicPrivateId(employee1.getEmployeeId(),employee1.getEmployeeType().toString());

		User desk=new User();
		desk.setDateOfBirth("12/03/12");
		desk.setName("Darshak");
		desk.setLastCheckIn("1:02");
		desk.setPassword(encoder.encode("1234"));
		desk.setEmployeeStatus(User.EmployeeStatus.CHECKED_IN);
		desk.setRole(DESK);
		desk.setEmployeeType(EmployeeType.ADMISSION_DESK);
		employee.setEmail("kemekm");
		employeeRepository.save(desk);
		publicPrivateService.savePublicPrivateId(desk.getEmployeeId(),desk.getEmployeeType().toString());

		User employee3=new User();
		employee3.setDateOfBirth("12/05/12");
		employee3.setName("Karan");
		employee3.setLastCheckIn("1:02");
		employee3.setPassword(encoder.encode("1234"));
		employee3.setEmployeeStatus(User.EmployeeStatus.CHECKED_IN);
		employee3.setRole(PHARAMACIST);
		employee3.setEmployeeType(EmployeeType.PHARMACIST);
		employee3.setEmail("kemekm");
		employeeRepository.save(employee3);
		publicPrivateService.savePublicPrivateId(employee3.getEmployeeId(),employee3.getEmployeeType().toString());

		System.out.println("Id for Doctor is "+publicPrivateService.publicIdByPrivateId(employee.getEmployeeId()));
		System.out.println("Id for ADMIN is "+publicPrivateService.publicIdByPrivateId(employee1.getEmployeeId()));
		System.out.println("Id for ADMISSION DESK is "+publicPrivateService.publicIdByPrivateId(desk.getEmployeeId()));
		System.out.println("Id for PHARMACIST is "+publicPrivateService.publicIdByPrivateId(employee3.getEmployeeId()));
	}

	

}
