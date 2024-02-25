package com.cg.jbs;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class JobSearchSystemApplication{
	
	public static void main(String[] args) {
		SpringApplication.run(JobSearchSystemApplication.class, args);
	}

//	@Override
//	public void run(String... args) throws Exception {
//		try {
//			Roles role1 = new Roles();
//			role1.setId(AppConstant.ROLE_EMPLOYER_ID);
//			role1.setRole("ROLE_EMPLOYER");
//
//			Roles role2 = new Roles();
//			role2.setId(AppConstant.ROLE_JOB_SEEKER_ID);
//			role2.setRole("ROLE_JOB_SEEKER");
//
//			List<Roles> roles = List.of(role1, role2);
//
//			List<Roles> result = this.roleRepo.saveAll(roles);
//
//			result.forEach(r -> {
//				System.out.println(r.getRole());
//			});
//		} catch (Exception e) {
//			// TODO: handle exception
//			e.printStackTrace();
//		}
//	}
}
