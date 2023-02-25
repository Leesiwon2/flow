package com.example.flow.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.flow.Entity.Extension;
import com.example.flow.Repo.ExtensionRepository;

import lombok.RequiredArgsConstructor;

@Service
@Transactional(readOnly=true)
@RequiredArgsConstructor
public class ExtensionService {
	
	private final ExtensionRepository extensionRepo;
	
	
	@Transactional
	public String add(Extension extension) {
		extensionRepo.save(extension);
		return extension.getExtensionName();
	}
	
	public List<Extension> findExtensions() {
		return extensionRepo.findAll();
	}
	
}