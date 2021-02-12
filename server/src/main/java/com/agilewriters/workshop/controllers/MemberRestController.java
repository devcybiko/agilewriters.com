package com.agilewriters.workshop.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.agilewriters.workshop.entities.Member;
import com.agilewriters.workshop.repos.MemberRepository;

@RestController
public class MemberRestController {

	private static final Logger LOGGER = LoggerFactory.getLogger(MemberRestController.class);

	@Autowired
	MemberRepository MemberRepository;

	@RequestMapping("/member/{id}")
	public Member findMember(@PathVariable("id") Long id) {
		LOGGER.info("Inside findMember() for id: " + id);
		return MemberRepository.findById(id).get();
	}

	@RequestMapping("/member")
	public Member updateMember(@RequestBody MemberUpdateRequest request) {
		LOGGER.info("Inside updateMember() for " + request);
		Member Member = MemberRepository.findOne(request.getId());
		Member.setNumberOfBags(request.getNumberOfBags());
		Member.setCheckedIn(request.getCheckedIn());
		LOGGER.info("Saving Member");
		return MemberRepository.save(Member);

	}

}
