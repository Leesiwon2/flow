package com.example.flow.Controller;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.flow.Entity.FixExtension;
import com.example.flow.Repo.FixExtensionRepository;

import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/fix")
@RequiredArgsConstructor
public class FixExtensionController {
	
	private final FixExtensionRepository fixExtensionRepo;

	
	@PostMapping("/update")
	public String updateFix(@RequestParam String name,Model model) {
		System.out.println(name);
		//fixExtensionRepo.save();
		
		return "index";
	}
}
