package com.shares.baltic_nasdaq.controller;

import com.shares.baltic_nasdaq.Model.Share;
import com.shares.baltic_nasdaq.service.ShareService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/")
public class ShareController {

	@Resource
	private ShareService shareService;

	@GetMapping("/nasdaq")
	public List<Share> convertNastaqResponse() {
		return shareService.convertNastaqResponse();
	}
}
