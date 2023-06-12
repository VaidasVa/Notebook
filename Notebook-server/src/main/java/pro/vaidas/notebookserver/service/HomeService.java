package pro.vaidas.notebookserver.service;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;

import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class HomeService {

	@PreAuthorize("hasRole('USER')")
	public Map<String, String> user() {
		var jwt = (Jwt) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		return Map.of("home", "USER: " + jwt.getSubject());
	}
	
	@PreAuthorize("hasRole('ADMIN')")
	public Map<String, String> admin() {
		var jwt = (Jwt) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		return Map.of("home", "ADMIN: " + jwt.getSubject());
	}
}
