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
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.flow.Entity.Extension;
import com.example.flow.Entity.FixExtension;
import com.example.flow.Repo.FixExtensionRepository;

import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/fix")
@RequiredArgsConstructor
public class FixExtensionController {
	
	private final FixExtensionRepository fixExtensionRepo;

	@PostMapping("")
	@ResponseBody
	public Map<String, Boolean> selectFixExtension() {
		List<FixExtension> fixExtension = fixExtensionRepo.findAllFix();
		Map<String, Boolean> fix = new LinkedHashMap<>();
		
		for(var i=0; i< fixExtension.size(); i++) {
			Boolean checkYn = false;
			if((fixExtension.get(i).getCheck_yn().toString()).equals("true")) {
				checkYn = true;
				
			}
			fix.put(fixExtension.get(i).getExtensionName().toString(),checkYn);
		}
		return fix;
	}
	@PostMapping("/update")
	public String updateFix(@ModelAttribute("fix") @RequestParam Map<String,String> prmt,Model model) {
		FixExtension fixExtension = new FixExtension();
		Map<String, Boolean> fix = new LinkedHashMap<>();
		Boolean checkYn = false;
		
		fixExtension.setExtensionName(prmt.get("name"));
		fixExtension.setCheck_yn(prmt.get("checkYn"));
		fixExtensionRepo.save(fixExtension);
		List<FixExtension> data = fixExtensionRepo.findAllFix();
		
		for(var i=0; i< data.size(); i++) {
			if(data.get(i).getCheck_yn().toString() =="true") {
				checkYn = true;
			}
			fix.put(data.get(i).getExtensionName().toString(), checkYn);
		}
		return "redirect:/ ";
	}
}
