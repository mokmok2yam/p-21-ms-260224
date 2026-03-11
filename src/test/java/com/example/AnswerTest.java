package com.example;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class AnswerTest {
    @Autowired
    private AnswerRepository answerRepository;
    @Autowired
    private QuestionRepository questionRepository;
    //답변생성하기
    @Test
    void 답변생성하기(){
        Optional<Question> oq = questionRepository.findById(2);
        assertThat(oq).isPresent();
        Question q = oq.get();
        Answer answer = new Answer();
        answer.setContent("네 자동으로 생성됩니다.");
        answer.setQuestion(q);
        answer.setCreateDate(LocalDateTime.now());
        answerRepository.save(answer);
    }
    //답변 데이터 조회하기
    @Test
    void 답변데이터조회하기(){
        //given
        Optional<Answer> oa = answerRepository.findById(1);
        assertThat(oa).isPresent();
        Answer answer = oa.get();
        //when
        String rst = answer.getContent();
        int rstId = answer.getQuestion().getId();
        //then
        assertThat("네 자동으로 생성됩니다.").isEqualTo(rst);
        assertThat(2).isEqualTo(rstId);
    }
    //질문을 통하여 답변 데이터 검색하기
    @Test
    @Transactional
    @DisplayName(value = "질문을 통하여 답변데이터 조회하기")
    void t1(){
        //given
        Optional<Question> oq = questionRepository.findById(2);
        assertThat(oq).isPresent();
        Question q = oq.get();
        //when
        List<Answer> answers = q.getAnswerList();
        int rstSize = answers.size();
        Answer answer = answers.get(0);
        String rst = answer.getContent();
        //then
        assertThat(1).isEqualTo(rstSize);
        assertThat("네 자동으로 생성됩니다.").isEqualTo(rst);
    }
}
