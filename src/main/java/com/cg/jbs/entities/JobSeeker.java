package com.cg.jbs.entities;

import java.util.Collection;
import java.util.Set;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.cg.jbs.helper.UserRole;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class JobSeeker implements UserDetails{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(unique = true)
	private Integer id;
	
	private String name;
	
	private String address;
	
	private String mobileNumber;
	
	@Column(unique = true)
	private String email;
	
	private String password;
	
	private String skillSets;	
	/**
	 * role -- contains role
	 */
	private UserRole role;
	
	@OneToMany(mappedBy = "jobSeeker", cascade = CascadeType.ALL)
	Set<AppliedJobs> appliedJobs;
	
	@OneToMany(mappedBy = "jobSeeker", cascade = CascadeType.ALL)
	Set<EmployerMessage> allMessage;
	
	@OneToOne(mappedBy = "jobSeeker", cascade = CascadeType.ALL)
	private Basket basket;

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
