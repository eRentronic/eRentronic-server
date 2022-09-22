package com.server.erentronic.common.member.service;

import com.server.erentronic.common.dto.CUDResponse;
import com.server.erentronic.common.member.Member;
import com.server.erentronic.common.member.dto.SignUpRequest;
import com.server.erentronic.common.member.repository.MemberRepository;
import com.server.erentronic.common.message.Message;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Slf4j
public class MemberService {

	private final MemberRepository memberRepository;

	@Transactional
	public CUDResponse singUp(SignUpRequest signUpRequest) {
		//todo email 중복 체크

		log.info("password: {}", signUpRequest.getPassword());

		Member member = signUpRequest.toEntity();
		memberRepository.save(member);
		return CUDResponse.of(member.getId(), Message.MEMBER_SIGN_UP_MESSAGE);
	}
}
