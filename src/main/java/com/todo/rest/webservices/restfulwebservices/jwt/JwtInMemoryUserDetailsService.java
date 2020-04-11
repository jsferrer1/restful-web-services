package com.todo.rest.webservices.restfulwebservices.jwt;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class JwtInMemoryUserDetailsService implements UserDetailsService {

	static List<JwtUserDetails> inMemoryUserList = new ArrayList<>();

	static {
		inMemoryUserList.add(new JwtUserDetails(1L, "test",
				"$2a$10$Ajo0HrzdCXDuA1zWbsKRjebJ7JT8do/G71nr7TjVLnsrZgkXjxIxu", "ROLE_USER_2"));

		// $2a$10$EApw1yxG.c6a3/aaFmgCZuCbZJVRtxzDBrG2c6m3EG9cQcGKa9NKK (online)
		// $2a$10$Ajo0HrzdCXDuA1zWbsKRjebJ7JT8do/G71nr7TjVLnsrZgkXjxIxu (BcryptEncoder)
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<JwtUserDetails> findFirst = inMemoryUserList.stream()
				.filter(user -> user.getUsername().equals(username)).findFirst();

		if (!findFirst.isPresent()) {
			throw new UsernameNotFoundException(String.format("USER_NOT_FOUND '%s'.", username));
		}

		return findFirst.get();
	}

}