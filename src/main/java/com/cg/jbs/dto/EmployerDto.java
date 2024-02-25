package com.cg.jbs.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor

@Getter
@Setter
public class EmployerDto {

	/**
	 * email -- contains employer email
	 */
	@NotEmpty(message = "Email must be required")
	@Email(regexp = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$", message = "Email should be well formed")
	private String email;

	/**
	 * password -- contains employer password
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
	 * organizationName -- contains employer organizationName
	 */
	@NotEmpty(message = "Organization Name must be required")
	private String organizationName;

	/**
	 * address -- contains employer address
	 */
	@NotEmpty(message = "Address must be required")
	private String address;

	/**
	 * phoneNumber -- contains employer phone number
	 */
	@NotEmpty(message = "Address must be required")
	@Pattern(regexp = "^[6-9]\\d{9}$", message = "Please enter correct mobile number")
	@Size(min = 10, max = 11,message = "Phone number should contains atleast 10 digits")
	private String phoneNumber;
	
	/**
	 * jobSeeker -- will contains job seeker details
	 */
//	private JobSeekerDto jobSeekerDto;
	
	@JsonIgnore
	public String getPassword() {
		return this.password;
	}
	
	@JsonProperty
	public void setPassword(String password) {
		this.password=password;
	}
}
