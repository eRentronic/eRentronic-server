package com.server.erentronic.common.member;

import com.server.erentronic.common.address.Address;
import com.server.erentronic.common.exception.AuthException;
import com.server.erentronic.common.message.ErrorDetail;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Slf4j
public class Member {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String name;

	@Email
	private String email;

	private String password;

	@Embedded
	private Address address;

	@Builder
	private Member(Long id, String name, String email, String password, Address address) {
		this.id = id;
		this.name = name;
		this.email = email;
		this.password = password;
		this.address = address;
	}

	public void validatePassword(String password) {
		if (!this.password.equals(password)) {
			log.info("memberPassword: {}, requestPassword: {}", this.password, password);
			throw new AuthException(ErrorDetail.NOT_MATCH_PASSWORD);
		}
	}
}
