package com.cg.jbs.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class JwtRequest {
	/**
	 * email -- contains email
	 */
	private String email;
	
	/**
	 * password -- contains password
	 */
	private String password;

}
