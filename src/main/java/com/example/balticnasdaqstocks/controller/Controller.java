package com.example.balticnasdaqstocks.controller;

import com.example.balticnasdaqstocks.Model.Stock;
import com.example.balticnasdaqstocks.service.NasdaqService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/")
public class Controller {
	@Resource
	private NasdaqService nasdaqService;


	@GetMapping("/nasdaq")
	public List<Stock> webClient() {
		return nasdaqService.webClient();
	}
}
