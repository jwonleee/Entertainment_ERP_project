package com.y4j.final_project.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AjaxReactController {

	@GetMapping("/react")
	public List<String> react(){
        return Arrays.asList("서버서버", "뷰뷰");
    }
}
