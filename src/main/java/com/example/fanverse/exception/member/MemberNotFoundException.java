package com.example.fanverse.exception.member;

import com.example.fanverse.exception.ClientErrorException;
import org.springframework.http.HttpStatus;

public class MemberNotFoundException extends ClientErrorException {

		public MemberNotFoundException() {
				super(HttpStatus.NOT_FOUND, "Member Not Found");
		}

		public MemberNotFoundException(String email) {
				super(HttpStatus.NOT_FOUND, "Member with Email " + email + " Not Found");
		}
}
