package com.cg.jbs.services;

import com.cg.jbs.dto.EmployerDto;

public interface EmployerService {

	/**
	 * This method use to add the employer details
	 * @param employerDto -- contains employer details
	 * @return EmployerDto -- will return employer dto
	 */
	public EmployerDto addEmployer(final EmployerDto employerDto);
}
