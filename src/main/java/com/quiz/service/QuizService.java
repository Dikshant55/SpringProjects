package com.quiz.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.quiz.entity.Quiz;
import com.quiz.quizRepo.QuizRepo;

@Service
public class QuizService {

	@Autowired
	QuizRepo quizRepo;
	
	@Autowired
	QuestionClient questionClient;
	
	public Quiz save(Quiz quiz) {
		
		return quizRepo.save(quiz);
	}
	
	public List<Quiz> get(){
		
		List<Quiz> quizzes = quizRepo.findAll();
	List<Quiz> listOfQuizzes =	quizzes.stream().map(values ->{
		values.setQuestions(questionClient.getQuestionByQuizId(values.getQuizId()));	
			return values;
		}).collect(Collectors.toList());
		return listOfQuizzes;
		
	}
	
}
