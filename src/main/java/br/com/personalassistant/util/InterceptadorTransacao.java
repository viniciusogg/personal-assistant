package br.com.personalassistant.util;

import java.io.Serializable;

import javax.inject.Inject;
import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

/**
 * 
 * Créditos: algaworks (ebook javaEE) www.algaworks.com.br
 *
 */

@Transacional
@Interceptor
public class InterceptadorTransacao implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private EntityManager entityManager;
	
	@AroundInvoke
	public Object invoke(InvocationContext context) throws Exception {
		
		/*EntityTransaction entityTransaction = entityManager.getTransaction();
		boolean criador = false;

		try {
			if (!entityTransaction.isActive()) {
				// truque para fazer rollback no que já passou
				// (se não, um futuro commit, confirmaria até mesmo operações sem transação)
				entityTransaction.begin();
				entityTransaction.rollback();
				
				// agora sim inicia a transação
				entityTransaction.begin();
				
				criador = true;
			}
			return context.proceed();
		} 
		catch (Exception ex) {
			if (entityTransaction != null && criador) {
				entityTransaction.rollback();
			}
			throw ex;
		} 
		finally {
			if (entityTransaction != null && entityTransaction.isActive() && criador) {
				entityTransaction.commit();
			}
		}*/
		
		Object object = null;

		EntityTransaction entityTransaction = entityManager.getTransaction();
		entityTransaction.begin();

		try {
			object = context.proceed();
			entityTransaction.commit();
		} 
		catch (Exception ex) {
			entityTransaction.rollback();
			throw ex;
		}

		return object;
		
	}
}
