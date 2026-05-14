package com.back.domain.member.member;

import com.back.domain.member.member.dto.Member;
import com.back.domain.member.member.service.MemberService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

@Transactional
@ActiveProfiles("test")
@SpringBootTest
public class MemberServiceTests {

    @Autowired
    private MemberService memberService;

    @Test
    @DisplayName("회원 다건 조회")
    void t1() {
        List<Member> members = memberService.findAll();

        assertThat(members).hasSize(2);
    }

    @Test
    @DisplayName("회원 단건 조회 - ID로 조회")
    void t2() {
        Member member = memberService.findById(1);

        assertThat(member.getUsername()).isEqualTo("user1");
        assertThat(member.getName()).isEqualTo("유저1");
        assertThat(member.getEmail()).isEqualTo("user1@test.com");
    }
}
