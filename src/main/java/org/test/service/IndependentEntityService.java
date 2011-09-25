package org.test.service;

import org.test.entity.IndependentEntity;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


@Stateless
public class IndependentEntityService
{
	private static final Logger log = LoggerFactory.getLogger( IndependentEntityService.class );
	
	@PersistenceContext
	private EntityManager em;


	public IndependentEntity selectOrCreate( Long id )
	{
		IndependentEntity cl = em.find( IndependentEntity.class, id );
		if( null == cl )
		{
			log.info( "No IndependetEntity found for id: {}. Create new ...", id );
			cl = new IndependentEntity();
		}
		return cl;
	}
	
	
	public IndependentEntity merge( IndependentEntity ie )
	{
		IndependentEntity merged = em.merge( ie );
		em.flush();
		return merged;
	}

}