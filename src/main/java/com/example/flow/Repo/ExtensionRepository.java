package com.example.flow.Repo;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.example.flow.Entity.Extension;


@Transactional
@Repository
public class ExtensionRepository {
	
	@PersistenceContext
	private EntityManager em;
	
	public void save(Extension exten) {
		em.persist(exten);
	}
	
	public List<Extension> findAll() {
		return em.createQuery("select m from Extension m", Extension.class).getResultList();
	}

	public List<Extension> findByName(String name) {
		return em.createQuery("select extensionName from Extension m where m.extensionName = :name", Extension.class)
				.setParameter("name", name)
				.getResultList();
	}
}