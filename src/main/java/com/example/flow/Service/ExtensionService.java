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
		validateDuplicateExtension(extension);
		extensionRepo.save(extension);
		return extension.getExtensionName();
	}

	private void validateDuplicateExtension(Extension extension) {
		List<Extension> findExtensions = extensionRepo.findByName(extension.getExtensionName());
		if (!findExtensions.isEmpty()) {
			throw new IllegalStateException("이미 존재하는 확장자입니다.");
		}
	}
	
	public List<Extension> findExtensions() {
		return extensionRepo.findAll();
	}
	
}