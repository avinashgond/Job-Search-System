package com.cg.jbs.services;

import java.util.List;

import com.cg.jbs.dto.EmployerDto;
import com.cg.jbs.dto.JobDto;
import com.cg.jbs.dto.JobSeekerDto;

import jakarta.validation.Valid;

public interface EmployerService {

	/**
	 * This method use to add the employer details
	 * @param employerDto -- contains employer details
	 * @return EmployerDto -- will return employer dto
	 */
	public EmployerDto addEmployer(final EmployerDto employerDto);

	/**
	 * This method use to post the new jobs
	 * @param jobDto -- contains job details
	 * @param empId -- contains employer id
	 * @return JobDto -- will return new added job details
	 */
	public JobDto postJob(final JobDto jobDto, final Integer empId);

	/**
	 * This method use to update the existing job details
	 * @param jobDto -- contains jobDto
	 * @param jobId -- contains Job Id
	 * @return JobDto -- will return Job Dto
	 */
	public JobDto updateJobDetails(JobDto jobDto, Integer jobId);

	/**
	 * This method use to delete job by job id
	 * @param jobId -- contains job id
	 */
	public void deleteJobByJobId(Integer jobId);

	/**
	 * This method use to find all jobs by given employer id
	 * @param empId -- contains employer id
	 * @return List<JobDto> -- will return List of all Job Dto
	 */
	public List<JobDto> viewAllJobsByEmpId(Integer empId);

	/**
	 * This method use to search job seekers by skill sets
	 * @param skillSets -- contains skill sets
	 * @return List<JobSeekerDto> -- will return List<JobSeekerDto>
	 */
	public List<JobSeekerDto> searchJobSeekersByTheirSkillSets(String skillSets);
}
