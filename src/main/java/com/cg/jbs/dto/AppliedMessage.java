package com.cg.jbs.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AppliedMessage {
	/**
	 * message -- message after apply for job
	 */
	private String message;
	
	/**
	 * successfull -- contains status
	 */
	private boolean successfull;

}
