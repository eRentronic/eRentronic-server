package com.server.erentronic.common.member.repository;

import com.server.erentronic.common.member.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {

}
