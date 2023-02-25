package com.example.flow.Repo;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.example.flow.Entity.Extension;
import com.example.flow.Entity.FixExtension;


@Transactional
@Repository
public class ExtensionRepository {
	
	@PersistenceContext
	private EntityManager em;
	
	public void save(Extension exten) {
		em.persist(exten);
	}
	
	public void delete(String name) {
		Extension exten = em.find(Extension.class, name);
		em.remove(exten);
	}
	
	public List<Extension> findAll() {
		return em.createQuery("select m from Extension m", Extension.class).getResultList();
	}
	
	public String findCount() {
		return String.valueOf(em.createNativeQuery("select COUNT(*) from Extension").getSingleResult()) ;
	}
	
	public String findByName(String name) {
		Extension extension = new Extension(); // 조회할 타입과 식별자 PK값
		if(em.find(Extension.class , name)!= null) {
			return "exist";
		}else {
			return "N";
		}
		
		
	}

}