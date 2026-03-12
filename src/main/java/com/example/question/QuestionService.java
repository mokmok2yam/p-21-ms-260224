package com.example.question;

import com.example.sbb.DataNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class QuestionService {
    private final QuestionRepository questionRepository;
    //다건 조회
    public List<Question> getList(){
        return questionRepository.findAll();
    }
    //단건 조회
    public Question getQuestion(Integer id) {
        Optional<Question> question = questionRepository.findById(id);
        if(question.isPresent()){
            return question.get();
        }else{
            throw new DataNotFoundException("question not found");
        }
    }

}
