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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.flow.Entity.Extension;
import com.example.flow.Entity.FixExtension;
import com.example.flow.Repo.ExtensionRepository;
import com.example.flow.Repo.FixExtensionRepository;

import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/")
@RequiredArgsConstructor
public class ExtensionController {
	
	private final ExtensionRepository extensionRepo;
	private final FixExtensionRepository fixExtensionRepo;
	String rslt = null;
	@GetMapping("")
	public String index(Model model) {
		List<Extension> extension = new ArrayList<>();
		List<FixExtension> fixExtension = fixExtensionRepo.findAllFix();
		Map<String, Boolean> fix = new LinkedHashMap<>();
		
		for(var i=0; i< fixExtension.size(); i++) {
			Boolean checkYn = false;
			if((fixExtension.get(i).getCheck_yn().toString()).equals("true")) {
				checkYn = true;
				
			}
			fix.put(fixExtension.get(i).getExtensionName().toString(),checkYn);
		}
		if(!extensionRepo.findAll().isEmpty()) {
			extension.addAll(extensionRepo.findAll());
			model.addAttribute("customExtension",extension);
		}
		model.addAttribute("extension",new Extension());
		model.addAttribute("extenCount",extensionRepo.findCount());
		model.addAttribute("fix", fix);
		if(Integer.parseInt(extensionRepo.findCount()) > 200) {
			model.addAttribute("result","200개까지 입력할 수 있습니다. ");
		} 
		if (rslt !=null) {
			model.addAttribute("result",rslt);
			rslt = null;
		}
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
		
		if(extension.getExtensionName().strip() =="") {
			rslt="공백값입니다. ";
			return "redirect:/ ";
		}else {
			if(extensionRepo.findByName(extension.getExtensionName()).equals("exist")) {
				rslt ="이미 존재하는 확장자입니다. ";
			}else {
				if(Integer.parseInt(extensionRepo.findCount()) > 199) {
					return "redirect:/ ";
				} else {
					extensionRepo.save(extension);	
				}
			}
		}
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
		model.addAttribute("extenCount",extensionRepo.findCount());
		return "index :: #custom";
	}
}
