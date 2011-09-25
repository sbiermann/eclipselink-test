package org.test.timer;

import javax.ejb.EJB;
import java.util.List;
import java.util.ArrayList;
import java.util.Set;
import java.util.HashSet;
import org.test.entity.Child;
import org.test.service.IndependentEntityService;
import org.test.service.ParentService;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.runner.RunWith;
import org.jboss.arquillian.junit.Arquillian;
import org.junit.Test;
import org.test.entity.EntityRelation;
import org.test.entity.IndependentEntity;
import org.test.entity.Parent;
import static org.junit.Assert.*;


/**
 * @author sbn
 */
@RunWith( Arquillian.class )
public class ArquillianTest
{
	
	@EJB
	private ParentService ps;
	
	@EJB
	private IndependentEntityService is;
	
	
	@Deployment
	public static WebArchive createDeploymentPackage() throws Exception
	{
		return ShrinkWrap.create( WebArchive.class, "test.war" ).
				addPackage( Parent.class.getPackage() ).
				addPackage( ParentService.class.getPackage() ).
				addAsResource( "persistence.xml", "META-INF/persistence.xml" );
	}
	
	
	@Test
	public void forceDuplicateKeyException() throws Exception
	{
		setUpDB();
		
		List<Parent> parents = ps.getAllParents();
		assertEquals( 2, parents.size() );
		for( Parent parent : parents )
		{
			assertEquals( 3, parent.getChilds().size() );
			IndependentEntity ie = is.selectOrCreate( -1L );
			assertNull( ie.getId() );
			for( Child child : parent.getChilds() )
			{
				assertNotNull( child.getEntityRelation() );
			}
			ie = is.merge( ie );
			assertNotNull( ie.getId() );
		}			
	}
	
	
	private void setUpDB() throws Exception
	{
		List<EntityRelation> ers = new ArrayList<EntityRelation>();
		for( int i=0; i<3; i++ )
		{
			EntityRelation er = new EntityRelation();
			ps.persist( er );
			ers.add( er );
		}
		
		for( int i=0; i<2; i++ )
		{
			Parent p = new Parent();
			Set<Child> childs = new HashSet<Child>();
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
	
}
