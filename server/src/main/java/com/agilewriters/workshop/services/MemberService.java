package com.agilewriters.workshop.services;

import java.util.List;
import java.util.Optional;

import com.agilewriters.workshop.entities.Member;


public interface MemberService {

	Member saveMember(Member Member);

	Member updateMember(Member Member);

	void deleteMember(Member Member);

	Optional<Member> getMemberById(Long id);

	List<Member> getAllMembers();
}
