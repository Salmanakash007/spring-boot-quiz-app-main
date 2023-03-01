package com.rassal.main.controller;

import java.util.List;

import com.rassal.main.model.Question;
import com.rassal.main.repository.QuestionRepo;
import com.rassal.main.repository.ResultRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import com.rassal.main.model.QuestionForm;
import com.rassal.main.model.Result;
import com.rassal.main.service.QuizService;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api")
public class MainController {

	@Autowired
	QuestionRepo qrepo;
	@Autowired
	QuizService quizService;
@Autowired
	ResultRepo resultRepo;


	@GetMapping("/quiz")
	public List<Question> getAllQuestion(){
		return quizService.getQuestions().getQuestions();
	}



	@GetMapping("/r")
	public List<com.rassal.main.model.Result> sList() {
		return quizService .getTopScore();
	}



}
