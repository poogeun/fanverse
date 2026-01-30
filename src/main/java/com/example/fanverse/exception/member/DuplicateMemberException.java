package com.example.fanverse.exception.member;

import com.example.fanverse.exception.ClientErrorException;
import org.springframework.http.HttpStatus;

public class DuplicateMemberException extends ClientErrorException {

		public DuplicateMemberException() {
				super(HttpStatus.CONFLICT, "Duplicate member");
		}

		public DuplicateMemberException(String email) {
				super(HttpStatus.CONFLICT, "Member with Email " + email + " already exists");
		}
}
