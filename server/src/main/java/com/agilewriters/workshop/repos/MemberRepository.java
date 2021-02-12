package com.agilewriters.workshop.repos;

import com.agilewriters.workshop.entities.Member;

import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {
    
}