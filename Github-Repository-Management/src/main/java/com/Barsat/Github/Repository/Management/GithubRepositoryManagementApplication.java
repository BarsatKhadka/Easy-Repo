package com.Barsat.Github.Repository.Management;

import com.Barsat.Github.Repository.Management.Repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class GithubRepositoryManagementApplication  {


//	@Autowired
//	UserRepo userRepo;


	public static void main(String[] args) {

		SpringApplication.run(GithubRepositoryManagementApplication.class, args);
	}


	//test to check how things are going and if cascade is working. Commenting for future reference
//	public void run(String... args) throws Exception {
//		Integer masterIdToDelete = 1;
//		if (userRepo.existsById(masterIdToDelete)) {
//			System.out.println("exists");
//			userRepo.deleteById(masterIdToDelete);
//		} else {
//			System.out.println("User with ID " + masterIdToDelete + " does not exist.");
//		}
//	}


}
