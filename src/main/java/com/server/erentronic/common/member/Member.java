package com.server.erentronic.common.member;

import com.server.erentronic.common.address.Address;
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

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
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
}
