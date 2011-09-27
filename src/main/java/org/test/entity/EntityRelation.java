package org.test.entity;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
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
	
	private String relationName;
	
	@Enumerated( EnumType.STRING )
	private RelationType relationType;
	
	public EntityRelation()	{}
	
	public EntityRelation( String relationName, RelationType relationType )
	{
		this.relationName = relationName;
		this.relationType = relationType;
	}

	
	public Long getId()
	{
		return id;
	}
	
	
	public String getRelationName()
	{
		return relationName;
	}
		
	
	public RelationType getRelationType()
	{
		return relationType;
	}
	
	
	@Override
	public boolean equals( Object obj )
	{
		if( obj == null )
			return false;
		if( getClass() != obj.getClass() )
			return false;
		final EntityRelation other = ( EntityRelation ) obj;
		if( (this.relationName == null) ? (other.relationName != null) : !this.relationName.equals( other.relationName ) )
			return false;
//		if( this.relationType != other.relationType )
//			return false;
		return true;
	}


	@Override
	public int hashCode()
	{
		int hash = 5;
		hash = 67 * hash + (this.relationName != null ? this.relationName.hashCode() : 0);
//		hash = 67 * hash + (this.relationType != null ? this.relationType.hashCode() : 0);
		return hash;
	}
	
}