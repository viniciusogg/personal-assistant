package br.com.personalassistant.dao;

import java.io.Serializable;

import javax.inject.Inject;
import javax.persistence.EntityManager;

public abstract class DAO implements Serializable{

	private static final long serialVersionUID = -5275558894052492460L;

	@Inject
	private EntityManager entityManager;
	
	protected EntityManager getEntityManager(){
		return this.entityManager;
	}

}
