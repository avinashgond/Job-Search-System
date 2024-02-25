package com.cg.jbs.entities;


import java.util.Collection;
import java.util.Set;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.cg.jbs.helper.UserRole;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Employer implements UserDetails{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * id -- contains employer id
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	
	/**
	 * email -- contains employer email
	 */
	private String email;
	
	/**
	 * password -- contains employer password
	 */
	private String password;
	
	/**
	 * organizationName -- contains employer organizationName
	 */
	private String organizationName;
	
	/**
	 * address -- contains employer address
	 */
	private String address;
	
	/**
	 * phoneNumber -- contains employer phone number
	 */
	private String phoneNumber;
	
	/**
	 * role -- contains role details
	 */
	private UserRole role;
	
	@OneToMany(mappedBy = "employer", cascade = CascadeType.ALL)
	private Set<Job> allJobs;

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return null;
	}

	@Override
	public String getUsername() {
		return this.email;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}
}
