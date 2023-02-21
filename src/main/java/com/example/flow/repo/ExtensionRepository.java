package com.example.flow.repo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.example.flow.Entity.Extension;

@Transactional
@Repository
public class ExtensionRepository {
	
	private Map<String, Extension> extensions = new HashMap<>();
	
	public String save(Extension exten) {
		extensions.put("exten",exten);
		System.out.println(exten.getExtensionName());
		return exten.getExtensionName();
	}
	
	public List<Extension> findAll() {
		return new ArrayList<>(extensions.values());
	}
}