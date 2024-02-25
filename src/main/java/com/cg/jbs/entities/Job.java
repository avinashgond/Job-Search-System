package com.cg.jbs.entities;

import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "jobs")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Job {
	
	/**
	 * id -- contains job id
	 */
	@Id
	@GeneratedValue(strategy =  GenerationType.AUTO)
	private Integer id;
	
	/**
	 * jobTitile -- contains job title
	 */
	private String jobTitile;
	
	/**
	 * location -- contains job location
	 */
	private String location;
	
	/**
	 * description -- contains job description
	 */
	private String description;
	
	/**
	 * experience -- contains job experience
	 */
	private String experience;
	
	/**
	 * salary -- contains salary details
	 */
	private String salary;
	
	/**
	 * requiredSkills -- contains job requiredSkills
	 */
	private String requiredSkills;
	
	/**
	 * noticePeriod -- contains 
	 */
	private String noticePeriod;
	
	/**
	 * contactEmail -- contains email for contact to employer
	 */
	private String contactEmail;
	
	/**
	 * status -- contains job status
	 */
	private String status;
	
	/**
	 * date -- contains date
	 */
	private Date date;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "employer_id")
	private Employer employer;
	
	@ManyToOne(fetch =  FetchType.EAGER)
	@JoinColumn(name = "jobSeeker_id")
	private JobSeeker jobSeeker;
}
