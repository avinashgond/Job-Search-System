package com.cg.jbs.services;

import com.cg.jbs.dto.EmployerDto;
import com.cg.jbs.dto.JobDto;

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
}
