package org.test.service;

import org.test.entity.Parent;
import java.io.Serializable;
import java.util.Collections;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;



/**
 * @author sbn
 */
@Stateless
public class ParentService implements Serializable
{

	private static final Logger logger = LoggerFactory.getLogger( ParentService.class );

	@PersistenceContext
	private EntityManager em;


	public List<Parent> getAllParents()
	{
		TypedQuery<Parent> query = em.createNamedQuery( "findAllParents", Parent.class );
		try
		{
			return query.getResultList();
		}
		catch( NoResultException e )
		{
			logger.info( "No parents exists ...", e );
			return Collections.emptyList();
		}
	}
	
	
	public void persist(Object o)
	{
		em.persist( o );
	}

}