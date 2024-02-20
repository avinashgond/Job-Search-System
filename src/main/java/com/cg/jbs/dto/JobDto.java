package com.cg.jbs.dto;

import com.cg.jbs.entities.Employer;
import com.cg.jbs.entities.JobSeeker;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class JobDto {
	
	/**
	 * jobTitile -- contains job title
	 */
	@NotEmpty(message = "Job Title Must Be Reqired")
	private String jobTitile;
	
	/**
	 * location -- contains job location
	 */
	@NotEmpty(message = "Location Must Be Reqired")
	private String location;
	
	/**
	 * description -- contains job description
	 */
	@NotEmpty(message = "Description Must Be Reqired")
	private String description;
	
	/**
	 * experience -- contains job experience
	 */
	@NotEmpty(message = "Experience Must Be Reqired")
	private String experience;
	
	/**
	 * salary -- contains salary details
	 */
	@NotEmpty(message = "Salary Must Be Reqired")
	private String salary;
	
	/**
	 * requiredSkills -- contains job requiredSkills
	 */
	@NotEmpty(message = "Required Skills Must Be Reqired")
	private String requiredSkills;
	
	/**
	 * noticePeriod -- contains 
	 */
	@NotEmpty(message = "Notice Period Must Be Reqired")
	private String noticePeriod;
	
	/**
	 * contactEmail -- contains email for contact to employer
	 */
	@NotEmpty(message = "Email Must Be Reqired")
	@Email(regexp = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$", message = "Email should be well formed")
	private String contactEmail;
	
	/**
	 * status -- contains job status
	 */
	@NotEmpty(message = "Status Must Be Reqired")
	private String status;
	
	private Employer employer;
	
	private JobSeeker jobSeeker;

}
