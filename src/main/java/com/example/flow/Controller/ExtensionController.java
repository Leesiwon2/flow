package com.example.flow.Controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.flow.Entity.Extension;
import com.example.flow.Repo.ExtensionRepository;

import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/")
@RequiredArgsConstructor
public class ExtensionController {
	
	private final ExtensionRepository extensionRepo;
	
	@GetMapping("")
	public String index(Model model) {
		List<Extension> extension = new ArrayList<>();
		if(!extensionRepo.findAll().isEmpty()) {
			System.out.println("뭔가 값이 있어");
			extension.addAll(extensionRepo.findAll());

			model.addAttribute("customExtension",extension);
		}else {

			
		}
		model.addAttribute("extension",new Extension());
//		for(int i=0; i<extensionRepo.findAll().size(); i++) {
//			String name = extensionRepo.findAll().get(i).getExtensionName();
//			extension.add(new Extension(name,"N"));
//		}
		System.out.println("여기까지 가능");
		
		
		return "index";
	}
	
	@PostMapping("extension/add")
	public String addExtension(@Validated Extension extension,BindingResult bd) {
		if(bd.hasErrors()) {
			List<ObjectError> list =  bd.getAllErrors();
            for(ObjectError e : list) {
                 System.out.println(e.getDefaultMessage());
            }
		}
		extensionRepo.save(extension);
		
		return "redirect:/";
	}
	
	public String deleteExtension(@Validated String name,BindingResult bd) {
		System.out.println("ddddd");
		if(bd.hasErrors()) {
			List<ObjectError> list =  bd.getAllErrors();
            for(ObjectError e : list) {
                 System.out.println(e.getDefaultMessage());
            }
		}
		System.out.println(":::::"+name);
		//extensionRepo.save(extension);
		
		return "redirect:/";
	}
}
