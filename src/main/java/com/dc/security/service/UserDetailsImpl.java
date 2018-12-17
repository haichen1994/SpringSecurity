package com.dc.security.service;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.dc.security.entity.user.TestResource;
import com.dc.security.entity.user.TestUser;


public class UserDetailsImpl implements UserDetails {
	
	List<TestResource>  resources;
	private TestUser user;

    public UserDetailsImpl(TestUser user,List<TestResource> resources) {
        this.user = user;
        this.resources = resources;
    }
    
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		List<GrantedAuthority> auths = new ArrayList<>();
		for(TestResource resource: resources){
			auths.add(new SimpleGrantedAuthority(resource.getResourceId()));
		}
		return auths;
	}

	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return user.getPassword();
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return user.getUserName();
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return !"0".equals (user.getIsEnable());
	}

}
