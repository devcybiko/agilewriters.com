package com.agilewriters.workshop.services;

import java.util.List;
import java.util.Optional;

import com.agilewriters.workshop.entities.Member;
import com.agilewriters.workshop.repos.MemberRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MemberServiceImpl implements MemberService {

    @Autowired
    private MemberRepository repository;

    @Override
    public Member saveMember(Member Member) {
        return repository.save(Member);
    }

    @Override
    public Member updateMember(Member Member) {
        return repository.save(Member);
    }

    @Override
    public void deleteMember(Member Member) {
        repository.delete(Member);
    }

    @Override
    public Optional<Member> getMemberById(Long id) {
		return repository.findById(id);
	}

	@Override
	public List<Member> getAllMembers() {
		return (List<Member>) repository.findAll();
	}

	public MemberRepository getRepository() {
		return repository;
	}

	public void setRepository(MemberRepository repository) {
		this.repository = repository;
	}

}
