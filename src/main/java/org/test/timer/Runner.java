package org.test.timer;

import org.test.entity.IndependentEntity;
import org.test.entity.Parent;
import org.test.entity.Child;
import org.test.service.ParentService;
import org.test.service.IndependentEntityService;

import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Schedule;
import javax.ejb.Singleton;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * @author sbn
 */
@Singleton
public class Runner
{
	private static final Logger log = LoggerFactory.getLogger( Runner.class );
	
	@EJB
	private ParentService cs;

	@EJB
	private IndependentEntityService ls;
	

	@Schedule( hour="*", minute="*" )
	public void run()
	{
		List<Parent> parents = cs.getAllParents();
		log.debug( "{} parents found.", parents.size() );
		for( Parent parent : parents )
		{
			log.debug( "Current parent: {} ...", parent.getId() );
			log.debug( "Select or create IndependetEntity ..." );
			IndependentEntity clog = ls.selectOrCreate( -1L );
			log.debug( "Get childs for parent: {} ...", parent.getId() );
			log.debug( "{} childs for parent: {} found.", parent.getChilds().size(), parent.getId() );
			for( Child child : parent.getChilds() )
			{
				log.debug( "Found child: {}", child );
				log.debug( "Get EntityRelation: {}", child.getEntityRelation() );
			}
			log.debug( "Merge IndependentEntity ..." );
			ls.merge( clog );
		}
	}

}