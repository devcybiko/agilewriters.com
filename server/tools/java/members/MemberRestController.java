package com.agilewriters.workshop.members;

import java.util.List;
import java.util.Optional;

import com.agilewriters.workshop.members.dtos.MemberUpdateRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MemberRestController {

    private static final Logger LOGGER = LoggerFactory.getLogger(MemberRestController.class);

    @Autowired
    MemberRepository MemberRepository;

    @GetMapping("/member/{id}")
    public Member findMember(@PathVariable("id") Long id) {
        LOGGER.info("Inside findMember() for id: " + id);
        return MemberRepository.findById(id).get();
    }

    @GetMapping("/member")
    public List<Member> findAllMembers() {
        LOGGER.info("Inside findAllMembers()");
        return MemberRepository.findAll();
    }

    @PutMapping("/member/{id}")
    public Member updateMember(@PathVariable("id") Long id, @RequestBody MemberUpdateRequest request) {
        LOGGER.info("Inside updateMember() for " + request);
        Optional<Member> member = MemberRepository.findById(id);

    member.get().setFname(request.getFname());
    member.get().setLname(request.getLname());
    member.get().setEmail(request.getEmail());
    member.get().setPassword(request.getPassword());
		LOGGER.info("Updating Member");
		Member result = MemberRepository.save(member.get());
        
    result.setPassword(null);
        return result;
	}

    @PostMapping("/member")
    public Member insertMember(@RequestBody MemberUpdateRequest request) {
        LOGGER.info("Inside insertMember() for " + request);
        Member member = new Member();

    member.setFname(request.getFname());
    member.setLname(request.getLname());
    member.setEmail(request.getEmail());
    member.setPassword(request.getPassword());
		LOGGER.info("Inserting Member");
		Member result =  MemberRepository.save(member);
        
    result.setPassword(null);
        return result;
	}

}
