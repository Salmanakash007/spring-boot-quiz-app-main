package com.devrezaur.main.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import com.devrezaur.main.model.QuestionForm;
import com.devrezaur.main.model.Result;
import com.devrezaur.main.service.QuizService;

@Controller
public class MainController {

	public LocalDateTime dateTime;
	public  String na = "abcd";
	public LocalDateTime ta;
	
	@Autowired
	Result result;
	@Autowired
	QuizService qService;
	
	Boolean submitted = false;
	
	@ModelAttribute("result")
	public Result getResult() {
		return result;
	}

	@GetMapping("/time")
	public String time(Model m) {



		return "time.html";
	}
	@RequestMapping("/tt")
	public String tt(Model m){
		m.addAttribute("time",dateTime);

		if(ta.now().isAfter(dateTime)){
			System.out.println("okay");
			return "redirect:/";
		}else {
			m.addAttribute("war","Please wait for right time :");
			System.out.println("else ----------------");
		}
		return "start.html";
	}


	@RequestMapping("/t")
	public String t(@RequestParam String a, String hour, String munite, Model m) {


		 dateTime = LocalDateTime.parse(a);
	        ta = LocalDateTime.now();



		m.addAttribute("time",dateTime);



		if(ta.now().isAfter(dateTime)){
			System.out.println("okay");
			return "redirect:/";
		}








return "start.html";

	}

	
	@GetMapping("/")
	public String home() {
		return "index.html";
	}
	
	@PostMapping("/quiz")
	public String quiz(@RequestParam String username, Model m, RedirectAttributes ra) {
		if(username.equals("")) {
			ra.addFlashAttribute("warning", "You must enter your name");
			return "redirect:/";
		}
		
		submitted = false;
		result.setUsername(username);
		
		QuestionForm qForm = qService.getQuestions();
		m.addAttribute("qForm", qForm);
		
		return "quiz.html";
	}
	
	@PostMapping("/submit")
	public String submit(@ModelAttribute QuestionForm qForm, Model m) {
		if(!submitted) {
			result.setTotalCorrect(qService.getResult(qForm));
			qService.saveScore(result);
			submitted = true;
		}
		
		return "result.html";
	}
	
	@GetMapping("/score")
	public String score(Model m) {
		List<Result> sList = qService.getTopScore();
		m.addAttribute("sList", sList);
		
		return "scoreboard.html";
	}

}
