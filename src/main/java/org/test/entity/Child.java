package org.test.entity;

import java.io.Serializable;

import javax.persistence.Embeddable;
import javax.persistence.ManyToOne;


/**
 * @author lgf
 */
@Embeddable
public class Child implements Serializable
{

	@ManyToOne( optional = false )
	private EntityRelation entityRelation;
	
	private String childName;
	
	
	public Child() {}
	public Child( String childName, EntityRelation entityRelation )
	{
		this.childName = childName;
		this.entityRelation = entityRelation;
	}

	
	public EntityRelation getEntityRelation()
	{
		return entityRelation;
	}
	
	
	public String getChildName()
	{
		return childName;
	}
	
	
	@Override
	public boolean equals( Object obj )
	{
		if( obj == null )
			return false;
		if( getClass() != obj.getClass() )
			return false;
		final Child other = ( Child ) obj;
		if( this.entityRelation != other.entityRelation && (this.entityRelation == null || !this.entityRelation.equals( other.entityRelation )) )
			return false;
		return true;
	}


	@Override
	public int hashCode()
	{
		int hash = 7;
		hash = 97 * hash + (this.entityRelation != null ? this.entityRelation.hashCode() : 0);
		return hash;
	}
	
}