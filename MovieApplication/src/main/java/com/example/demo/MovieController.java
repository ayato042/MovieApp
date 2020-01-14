package com.example.demo;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller

public class MovieController {

	private final JdbcTemplate jdbcTemplate;

	@Autowired
	public MovieController(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	public List<Map<String,Object>> getAll(int i) {
		String sql = "SELECT comment FROM comment" + i;
		List<Map<String, Object>> resultList = jdbcTemplate.queryForList(sql);
		return resultList;
	}

	public List<Map<String, Object>> getRecommend() {
		String sql = "SELECT title FROM recommend";
		List<Map<String, Object>> resultList = jdbcTemplate.queryForList(sql);
		return resultList;
	}

	public void update(Comment comment, int i) {
		jdbcTemplate.update("INSERT INTO comment"+ i +"(comment) VALUES(?)",
				comment.getComment());
	}

	public void updateTitle(Title title) {
		jdbcTemplate.update("INSERT INTO recommend(title) VALUES(?)",
				title.getTitle());
	}

	@GetMapping("/start")
	public String start() {
		return "start";
	}

	@GetMapping("/movie")
	public String index(Model model) {
		model.addAttribute("placeholder", "コメントを入力");
		return "index";
	}

	@GetMapping("/review")
	public String MovieContrller(Model model) {

		List<Map<String, Object>> resultList = getAll(0);
		model.addAttribute("resultList", resultList);

		List<Map<String, Object>> resultList1 = getAll(1);
		model.addAttribute("resultList1", resultList1);

		List<Map<String, Object>> resultList2 = getAll(2);
		model.addAttribute("resultList2", resultList2);

		List<Map<String, Object>> resultList3 = getAll(3);
		model.addAttribute("resultList3", resultList3);

		List<Map<String, Object>> resultList4 = getAll(4);
		model.addAttribute("resultList4", resultList4);

		List<Map<String, Object>> resultList5 = getAll(5);
		model.addAttribute("resultList5", resultList5);

		List<Map<String, Object>> resultList6 = getAll(6);
		model.addAttribute("resultList6", resultList6);

		List<Map<String, Object>> resultList7 = getAll(7);
		model.addAttribute("resultList7", resultList7);
		return "review";
	}

	@GetMapping("/recommends")
	public String recommends(Model model) {

		List<Map<String, Object>> resultList = getRecommend();

		model.addAttribute("resultList", resultList);
		model.addAttribute("placeholder", "あなたのおすすめ映画");
		return "recommend";
	}

	@PostMapping("/recommend")
	public String recommend(@Validated Title title, BindingResult result, Model model) {

		if(result.hasErrors()) {
			List<Map<String, Object>> resultList = getRecommend();
			model.addAttribute("resultList", resultList);
			model.addAttribute("placeholder", "無効な入力です");
			return "recommend";
		}	
		updateTitle(title);
		List<Map<String, Object>> resultList = getRecommend();
		model.addAttribute("resultList", resultList);
		model.addAttribute("placeholder", "送信しました");
		return "recommend";
	}

	@PostMapping("/confirm")
	public String confirm(@Validated Comment comment , BindingResult result, Model model, @RequestParam("confirm") int i) {

		if(result.hasErrors()) {
			model.addAttribute("placeholder", "無効な入力です");
			return "index";
		}
		model.addAttribute("i", i);
		return "confirm";
	}

	@PostMapping("/result")
	public String result(@Validated Comment comment, Model model, @RequestParam("result") int i) {
		update(comment, i);
		model.addAttribute("placeholder", "送信しました");
		return "index";
	}
}