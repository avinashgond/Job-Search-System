package com.cg.jbs.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AppliedJobsDto {

	private Integer id;
	
	private Integer jobId;
	private Integer jobSeekerId;
}
