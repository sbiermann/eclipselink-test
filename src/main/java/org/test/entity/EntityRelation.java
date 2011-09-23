package org.test.entity;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


/**
 * @author lgf
 */
@Entity
public class EntityRelation implements Serializable
{

	@Id
	@GeneratedValue( strategy = GenerationType.AUTO )
	private Long id;
	public Long getId()
	{
		return id;
	}
	
	
	@Override
	public boolean equals( Object obj )
	{
		if( obj == null )
			return false;
		if( getClass() != obj.getClass() )
			return false;
		final EntityRelation other = ( EntityRelation ) obj;
		if( this.id != other.id && (this.id == null || !this.id.equals( other.id )) )
			return false;
		return true;
	}


	@Override
	public int hashCode()
	{
		int hash = 7;
		hash = 97 * hash + (this.id != null ? this.id.hashCode() : 0);
		return hash;
	}

}