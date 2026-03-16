package com.example;

import com.example.question.QuestionService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class PageTest {
    @Autowired
    private QuestionService questionService;
    @Test
    void t1(){
        for(int i=1;i<=300;i++){
            String subject = "테스트 데이터 입니다:[%03d]".formatted(i);
            String content = "내용무";
            questionService.create(subject,content);

        }
    }
}
