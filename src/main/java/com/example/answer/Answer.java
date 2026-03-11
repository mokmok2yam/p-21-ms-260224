package com.example.answer;

import com.example.question.Question;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
public class Answer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(columnDefinition = "text")
    private String content;
    @ManyToOne
    private Question question;
    private LocalDateTime createDate;
}
