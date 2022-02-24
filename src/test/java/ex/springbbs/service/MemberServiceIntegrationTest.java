package ex.springbbs.service;

import ex.springbbs.entity.Member;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
@Transactional
public class MemberServiceIntegrationTest {

    @Autowired BbsService bbsService;

    Logger logger = LoggerFactory.getLogger(BbsService.class);

    @Test
    void 해언가입() {
        Member member = Member.builder()
                .email("bbsemail.com")
                .nickname("응애")
                .password("1234")
                .build();

        //bbsService.join(member);
    }

    @Test
    void 중복테스트() {
        //given
        Member member1 = Member.builder()
                .email("bbs@email.com")
                .nickname("응애")
                .password("1234")
                .build();


        Member member2 =Member.builder()
                .email("bbs@email.com")
                .nickname("응애")
                .password("1234")
                .build();

        //when
        //bbsService.join(member1);
       //IllegalStateException e = assertThrows(IllegalStateException.class, () -> bbsService.join(member2));

        //assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
    }
}
