package org.vj.api.gateway;

import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthenticationController {

	static class User {

		public User() {
			super();
			// TODO Auto-generated constructor stub
		}

		String username;
		String password;

		public String getUsername() {
			return username;
		}

		public void setUsername(String username) {
			this.username = username;
		}

		public String getPassword() {
			return password;
		}

		public void setPassword(String password) {
			this.password = password;
		}

	}

	@Autowired
	AuthenticationManager authenticationManager;
	@Autowired
	MyUserDetailsService userDetailsService;

	@PostMapping("/authenticate")
	@ResponseBody
	public ResponseEntity<?> authenticate(@RequestBody User user) throws Exception {
		try {

			UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
					user.getUsername(), user.getPassword(), Collections.emptyList());
			authenticationManager.authenticate(authenticationToken);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ResponseEntity
				.ok(new JwtUtil().generateToken(userDetailsService.loadUserByUsername(user.getUsername())));

	}

	@GetMapping("/hello")
	@ResponseBody
	public ResponseEntity<?> hello() {
		return ResponseEntity.ok("Hello World");
	}
}
