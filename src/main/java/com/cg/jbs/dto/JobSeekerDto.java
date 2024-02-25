package com.cg.jbs.dto;

import com.cg.jbs.entities.Employer;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class JobSeekerDto {
	/**
	 * name -- contains job seeker name
	 */
	@NotEmpty(message = "Name must be required")
	private String name;
	
	/**
	 * address -- contains job seeker address
	 */
	@NotEmpty(message = "Address must be required")
	private String address;
	
	/**
	 * mobileNumber -- contains job seeker mobile number
	 */
	@NotEmpty(message = "Mobile number must be required")
	@Pattern(regexp = "^[6-9]\\d{9}$", message = "Please enter correct mobile number")
	@Size(min = 10, max = 11,message = "Phone number should contains atleast 10 digits")
	private String mobileNumber;
	
	/**
	 * email -- contains job seeker email id
	 */
	@NotEmpty(message = "Email must be required")
	@Email(regexp = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$", message = "Email should be well formed")
	private String email;
	
	/**
	 * password -- contains job seeker password
	 */
	@NotEmpty(message = "Password must be required")
	@Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=!])(?=\\S+$).{8,}$",message = "Password should contains: At least one digit [0-9]\r\n"
			+ "At least one lowercase letter [a-z]\r\n"
			+ "At least one uppercase letter [A-Z]\r\n"
			+ "At least one special character [@#$%^&+=!]\r\n"
			+ "No whitespace allowed \\S\r\n"
			+ "Minimum length of 8 characters {8,}")
	private String password;
	
	/**
	 * skillSets -- contains job seeker skill sets
	 */
	@NotEmpty(message = "Skill sets must be required")
	private String skillSets;
	
	/**
	 * employerDto -- contains employer details
	 */
	private EmployerDto employerDto;
}
