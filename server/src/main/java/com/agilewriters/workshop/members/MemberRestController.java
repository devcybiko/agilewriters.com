package com.agilewriters.workshop.members;

import java.util.Optional;

import com.agilewriters.workshop.members.dtos.MemberUpdateRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
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

    @PutMapping("/member/{id}")
    public Member updateMember(@PathVariable("id") Long id, @RequestBody MemberUpdateRequest request) {
        LOGGER.info("Inside updateMember() for " + request);
        Optional<Member> member = MemberRepository.findById(request.getId());
		member.get().setFname(request.getFname());
		member.get().setLname(request.getLname());
		member.get().setEmail(request.getEmail());
		member.get().setPassword(request.getPassword());
		LOGGER.info("Saving Member");
		return MemberRepository.save(member.get());

	}

}
