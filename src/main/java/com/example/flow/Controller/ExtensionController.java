package com.example.flow.Controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.flow.Entity.Extension;
import com.example.flow.repo.ExtensionRepository;

import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/")
@RequiredArgsConstructor
public class ExtensionController {
	
	private final ExtensionRepository extensionRepo;
	
	@GetMapping("")
	public String index(Model model) {
		model.addAttribute("extension",new Extension());
		return "index";
	}
	
	@PostMapping("extension/add")
	@ResponseBody
	public String addExtension(@Validated Extension extension,BindingResult bd) {
		if(bd.hasErrors()) {
			List<ObjectError> list =  bd.getAllErrors();
            for(ObjectError e : list) {
                 System.out.println(e.getDefaultMessage());
            }
		}
		extensionRepo.save(extension);
		return "/";
	}
}
