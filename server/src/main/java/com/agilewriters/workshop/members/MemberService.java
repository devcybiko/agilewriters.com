package com.agilewriters.workshop.members;

import java.util.List;
import java.util.Optional;


public interface MemberService {

	Member saveMember(Member Member);

	Member updateMember(Member Member);

	void deleteMember(Member Member);

	Optional<Member> getMemberById(Long id);

	List<Member> getAllMembers();
}
