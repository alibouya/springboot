package com.springboot.request;

import javax.validation.constraints.NotBlank;

public class LoginRequest {
	@NotBlank
	  private String name;

		@NotBlank
		private String password;

		public String getUsername() {
			return name;
		}

		public void setUsername(String username) {
			this.name = username;
		}

		public String getPassword() {
			return password;
		}

		public void setPassword(String password) {
			this.password = password;
		}
}
