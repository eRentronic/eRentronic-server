package com.server.erentronic.common.member.dto;

import com.server.erentronic.common.address.Address;
import com.server.erentronic.common.member.Member;
import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
@Setter
public class SignUpRequest {

	@NotEmpty
	@Pattern(regexp = "^[ㄱ-ㅎ|가-힣|]+$")
	private String name;

	@NotEmpty
	@Email
	private String email;

	@NotEmpty
	private String password;

	@Valid
	private Address address;

	public Member toEntity() {

		return Member.builder()
			.name(name)
			.email(email)
			.password(password)
			.address(address)
			.build();
	}
}
