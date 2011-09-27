package org.test.se;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import java.util.List;
import java.util.ArrayList;
import java.util.Set;
import java.util.HashSet;
import org.test.entity.Child;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.test.entity.EntityRelation;
import org.test.entity.IndependentEntity;
import org.test.entity.Parent;
import org.test.entity.RelationType;
import static org.junit.Assert.*;


/**
 * @author lgf
 */
public class DuplicateKeyTest
{

	private static EntityManagerFactory emf;
	private EntityManager em;

	@BeforeClass
	public static void createEntityManagerFactory() throws Exception
	{
		emf = Persistence.createEntityManagerFactory( "eclipselinkTestPU" );
	}

	@Before
	public void createEntityManager() throws Exception
	{
		em = emf.createEntityManager();
	}

	@After
	public void destroyEntityManager() throws Exception
	{
		if ( null != em )
		{
			em.close();
			em = null;
		}
	}

	@AfterClass
	public static void destroyEntityManagerFactory() throws Exception
	{
		if ( null != emf )
		{
			emf.close();
			emf = null;
		}
	}

	@Test
	public void forceDuplicateKeyException() throws Exception
	{
		cleanUpDBInOwnTransaction();
		setUpDBInOwnTransaction();

		em.getTransaction().begin();
		List<Parent> parents = em.createNamedQuery( "findAllParents", Parent.class )
				.getResultList();
		assertEquals( 2, parents.size() );
		for ( Parent parent : parents )
		{
			assertEquals( 3, parent.getChildren().size() );
			IndependentEntity ie = em.find( IndependentEntity.class, -1L );
			if ( null == ie )
			{
				ie = new IndependentEntity();
			}
			assertNull( ie.getId() );
			for ( Child child : parent.getChildren() )
			{
				assertNotNull( child.getEntityRelation() );
			}
			ie = em.merge( ie );
			assertNotNull( ie.getId() );
		}
		em.getTransaction().commit();

		cleanUpDBInOwnTransaction();
	}

	private void setUpDBInOwnTransaction() throws Exception
	{
		em.getTransaction().begin();

		em.createNativeQuery( "INSERT INTO entityrelation VALUES (1, 'relation1', 'STRING_T')" ).executeUpdate();
		em.createNativeQuery( "INSERT INTO entityrelation VALUES (2, 'relation2', 'INTEGER_T')" ).executeUpdate();
		em.createNativeQuery( "INSERT INTO entityrelation VALUES (3, 'relation3', 'DATE_T')" ).executeUpdate();
		
		em.createNativeQuery( "INSERT INTO parent VALUES (1)" ).executeUpdate();
		em.createNativeQuery( "INSERT INTO parent VALUES (2)" ).executeUpdate();

		em.createNativeQuery( "INSERT INTO parent_children VALUES (1, 'child1', 1)" ).executeUpdate();
		em.createNativeQuery( "INSERT INTO parent_children VALUES (2, 'child2', 1)" ).executeUpdate();
		em.createNativeQuery( "INSERT INTO parent_children VALUES (3, 'child3', 1)" ).executeUpdate();
		em.createNativeQuery( "INSERT INTO parent_children VALUES (1, 'child4', 2)" ).executeUpdate();
		em.createNativeQuery( "INSERT INTO parent_children VALUES (2, 'child5', 2)" ).executeUpdate();
		em.createNativeQuery( "INSERT INTO parent_children VALUES (3, 'child6', 2)" ).executeUpdate();
		
		em.createNativeQuery( "INSERT INTO sequence VALUES ('SEQ_GEN', 50)" ).executeUpdate();
		
		em.getTransaction().commit();
		destroyEntityManager();
		createEntityManager();
	}

	
	private void cleanUpDBInOwnTransaction() throws Exception
	{
		em.getTransaction().begin();
		em.createNativeQuery( "DELETE FROM parent_children" ).executeUpdate();
		em.createNativeQuery( "DELETE FROM parent" ).executeUpdate();
		em.createNativeQuery( "DELETE FROM entityrelation" ).executeUpdate();
		em.createNativeQuery( "DELETE FROM independententity" ).executeUpdate();
		em.createNativeQuery( "DELETE FROM sequence WHERE seq_name = 'SEQ_GEN'" ).executeUpdate();
		em.getTransaction().commit();
		destroyEntityManager();
		createEntityManager();
	}

}
