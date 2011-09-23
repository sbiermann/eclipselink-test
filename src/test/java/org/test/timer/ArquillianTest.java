/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.test.timer;

import javax.ejb.EJB;
import java.util.List;
import java.util.ArrayList;
import java.util.Set;
import java.util.HashSet;
import org.junit.Before;
import javax.persistence.PersistenceContext;
import javax.persistence.EntityManager;
import org.test.entity.Child;
import org.test.service.ParentService;
import java.io.IOException;
import org.jboss.arquillian.container.test.api.Deployment;
import java.io.Serializable;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.runner.RunWith;
import org.jboss.arquillian.junit.Arquillian;
import org.junit.Test;
import org.test.entity.EntityRelation;
import org.test.entity.Parent;
import static org.junit.Assert.*;


/**
 *
 * @author sbn
 */
@RunWith( Arquillian.class )
public class ArquillianTest implements Serializable
{
	
	@EJB
	private ParentService ps;
	
	@Deployment
	public static WebArchive createDeploymentPackage() throws IOException
	{
		WebArchive war = ShrinkWrap.create( WebArchive.class, "test.war" ).
				addPackage( Child.class.getPackage() ).
				addPackage( ParentService.class.getPackage() ).
				addPackage( Runner.class.getPackage() ).addAsResource(
				"persistence.xml", "META-INF/persistence.xml" );
		return war;
	}
	
	
	@Before
	public void setUpDB()
	{
		List<EntityRelation> ers = new ArrayList();
		
		EntityRelation er1 = new EntityRelation();
		EntityRelation er2 = new EntityRelation();
		EntityRelation er3 = new EntityRelation();
		ps.persist( er1 );
		ps.persist( er2 );
		ps.persist( er3 );
		ers.add( er1 );
		ers.add( er2 );
		ers.add( er3 );
		
		
		
		for( int i = 1; i < 3; i++ )
		{
			Parent p = new Parent();
			Set<Child> childs = new HashSet();
			for( EntityRelation er : ers )
			{
				Child c = new Child();
				c.setEntityRelation( er );
				childs.add( c );
			}
			p.setChildren( childs );
			ps.persist( p );
		}
		

	}
	
	
	@Test
	public void testImportGoodCoupons() throws Exception
	{
				
		assertTrue( true );
		Thread.sleep( 120000 );
	}
	
	
}
