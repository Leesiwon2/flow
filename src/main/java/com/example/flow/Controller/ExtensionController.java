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

import com.example.flow.Entity.Extension;
import com.example.flow.Repo.ExtensionRepository;

import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/")
@RequiredArgsConstructor
public class ExtensionController {
	
	private final ExtensionRepository extensionRepo;
	
	@ModelAttribute("fix")
	public Map<String, String> fix(Model model) {
		Map<String, String> fix = new LinkedHashMap<>();
		
		fix.put("bat", "bat");
		fix.put("cmd", "cmd");
		fix.put("com", "com");
		fix.put("cpl", "cpl");
		fix.put("exe", "exe");
		fix.put("scr", "scr");
		fix.put("js", "js");
		
		return fix;
	}
	@GetMapping("")
	public String index(Model model) {
		
		List<Extension> extension = new ArrayList<>();
		if(!extensionRepo.findAll().isEmpty()) {
			extension.addAll(extensionRepo.findAll());
			model.addAttribute("customExtension",extension);
		}
		model.addAttribute("extension",new Extension());
		model.addAttribute("extenCount",extensionRepo.findCount());
		
		return "index";
	}
	
	@PostMapping("extension/add")
	public String addExtension(@Validated Extension extension,BindingResult bd, Model model) {
		if(bd.hasErrors()) {
			List<ObjectError> list =  bd.getAllErrors();
            for(ObjectError e : list) {
                 System.out.println(e.getDefaultMessage());
            }
		}
		extensionRepo.save(extension);
		model.addAttribute("extenCount",extensionRepo.findCount());
		return "redirect:/ ";
	}
	
	@GetMapping("extension/delete")
	public String deleteExtension(@RequestParam String name,Model model) {
		extensionRepo.delete(name);
		List<Extension> extension = new ArrayList<>();
		if(!extensionRepo.findAll().isEmpty()) {
			extension.addAll(extensionRepo.findAll());

			model.addAttribute("customExtension",extension);
		}
		return "index :: #custom";
	}
}
