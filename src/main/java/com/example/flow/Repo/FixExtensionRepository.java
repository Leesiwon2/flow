package com.example.flow.Repo;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.example.flow.Entity.FixExtension;


@Transactional
@Repository
public class FixExtensionRepository {
	
	@PersistenceContext
	private EntityManager em;
	
	public void save() {
	
		em.flush();
	}
	
	
	public List<FixExtension> findAll() {
		return em.createQuery("select m from Extension m", FixExtension.class).getResultList();
	}

}