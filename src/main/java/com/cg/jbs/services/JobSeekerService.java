package com.cg.jbs.services;

import java.util.List;

import com.cg.jbs.dto.AppliedMessage;
import com.cg.jbs.dto.JobDto;
import com.cg.jbs.dto.JobSeekerDto;
import com.cg.jbs.entities.JobSeekerMessage;


public interface JobSeekerService {

	/**
	 * This method use to add new job seeker details in db.
	 * @param jobSeekerDto -- contains Job Seeker Details
	 * @return JobSeekerDto -- will return Job Seeker Details
	 */
	public JobSeekerDto addJobSeeker(final JobSeekerDto jobSeekerDto);

	/**
	 * This method use to search job by given keyword
	 * @param keyword -- contains keyword
	 * @return List<JobDto> -- will return list of job dto
	 */
	public List<JobDto> searchJobBySkills(String keyword);

	/**
	 * This method use to search all jobs by using organization
	 * @param organization -- contains organization
	 * @return List<JobDto> -- will return list of job dto
	 */
	public List<JobDto> searchJobByOrganization(String organization);

	/**
	 * This method use to search jobs using location
	 * @param location -- contains location
	 * @return List<JobDto> -- will return list of job dto
	 */
	public List<JobDto> searchJobByLocation(String location);

	/**
	 * This method use to apply the jobs
	 * @param jobId -- contains jobId
	 * @param jsId -- contains job-seeker id
	 * @return AppliedMessage -- will return applied message
	 */
	public AppliedMessage applyJob(Integer jobId, Integer jsId);
	
	/**
	 * This method use to add jobs in baskets
	 * @param jobSeekerId -- contains job seeker id
	 * @param jobId -- contains jobId
	 */
	public void addJobToBasket(Integer jobSeekerId, Integer jobId);

	/**
	 * This method use to view job in basket
	 * @param jobSeekerId -- contains jobSeeekerId
	 * @return List<JobDto> -- will return List Of Job Dto
	 */
	public List<JobDto> viewJobBasket(Integer jobSeekerId);

	/**
	 * This method use to remove job from basket
	 * @param jobId -- contains job id
	 * @param jobSeekerId -- contains job seeker id
	 */
	public void removeJobFromBasket(Integer jobId, Integer jobSeekerId);

	/**
	 * This method use to send message to employer
	 * @param jobId -- contains jobId
	 * @param JobSeekerMessage -- contains job seeker message
	 * @param jobSeekerId -- contains job seeker id
	 * @return JobSeekerMessage -- will return job seeker message
	 */
	public void sendMessageToEmployer(Integer jobId, JobSeekerMessage jobSeekerMessage, Integer jobSeekerId);
}
