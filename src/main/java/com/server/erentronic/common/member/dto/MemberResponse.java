package com.server.erentronic.common.member.dto;

import com.server.erentronic.common.address.Address;
import com.server.erentronic.common.member.Member;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public class MemberResponse {

	private final Long id;

	private final String name;

	private final String email;

	private final String password;

	private final Address address;

	private final String profile;

	private final String profileImage;

	public static MemberResponse from(Member member) {
		return new MemberResponse(member.getId(),
			member.getName(),
			member.getEmail(),
			member.getPassword(),
			member.getAddress(),
			member.getProfile(),
			member.getProfileImage());
	}
}
