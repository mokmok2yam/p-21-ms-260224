package com.example;

import com.example.answer.AnswerRepository;
import com.example.question.Question;
import com.example.question.QuestionRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class QuestionTest {
    @Autowired
    private QuestionRepository questionRepository;
    @Autowired
    private AnswerRepository answerRepository;

    @Test
    void 제목으로찾기() {
        Question q = questionRepository.findBySubject("sbb가 무엇인가요?").stream()
                .findFirst().get();
        assertThat(1).isEqualTo(q.getId());
    }

    @Test
    void id로찾기() {
        Optional<Question> oq = questionRepository.findById(1);
        if (oq.isPresent()) {
            Question q = oq.get();
            assertThat("sbb가 무엇인가요?").isEqualTo(q.getSubject());
        }
    }

    //데이터 생성 테스트
    @Test
    void 데이터생성() {
        Question q1 = new Question();
        q1.setSubject("sbb가 무엇인가요?");
        q1.setContent("sbb에 대해서 배우고싶습니다.");
        q1.setCreateDate(LocalDateTime.now());
        this.questionRepository.save(q1);
        Question q2 = new Question();
        q2.setSubject("스프링부트 모델 질문입니다.");
        q2.setContent("id는 자동으로 생성되나요?");
        q2.setCreateDate(LocalDateTime.now());
        this.questionRepository.save(q2);
    }

    //find all테스트
    @Test
    void 전체찾기() {
        List<Question> all = questionRepository.findAll();
        assertThat(4).isEqualTo(all.size());
        Question q = all.get(0);
        assertThat("sbb가 무엇인가요?").isEqualTo(q.getSubject());
    }

    //제목과 내용으로 찾기
    @Test
    void 제목과내용으로찾기(){
        Question question = questionRepository.findBySubjectAndContent("sbb가 무엇인가요?","sbb에 대해서 배우고싶습니다.");
        assertThat(1).isEqualTo(question.getId());
    }
    //findBySubjectLike테스트
    @Test
    void 제목에포함된단어로찾기(){
        List<Question> questions = questionRepository.findBySubjectLike("sbb%");
        Question question = questions.get(0);
        assertThat("sbb가 무엇인가요?").isEqualTo(question.getSubject());
    }
    @Test
    void 제목수정하기(){
        Optional<Question> oq = questionRepository.findById(1);
        assertThat(oq).isPresent();
        Question q = oq.get();
        q.setSubject("수정된 제목");
        questionRepository.save(q);
        String rst = questionRepository.findById(1).get().getSubject();
        assertThat("수정된 제목").isEqualTo(rst);
    }//질묵 삭제 테스트
    @Test
    void 질문삭제하기(){
        questionRepository.deleteById(1);
        Optional<Question> oq = questionRepository.findById(1);
        assertThat(oq).isEmpty();
    }

}
