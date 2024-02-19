package com.cg.jbs.services;

import com.cg.jbs.dto.JobSeekerDto;

public interface JobSeekerService {

	/**
	 * This method use to add new job seeker details in db.
	 * @param jobSeekerDto -- contains Job Seeker Details
	 * @return JobSeekerDto -- will return Job Seeker Details
	 */
	public JobSeekerDto addJobSeeker(final JobSeekerDto jobSeekerDto);
}
