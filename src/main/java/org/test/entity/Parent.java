package org.test.entity;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CollectionTable;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;


/**
 * @author sbn
 */
@Entity
@NamedQueries( 
{
	@NamedQuery( name = "findAllParents", query = "SELECT parent FROM Parent parent order by parent.id asc" )
} )
public class Parent implements Serializable
{

	@Id
	@GeneratedValue( strategy = GenerationType.AUTO )
	private Long id;
		

	@ElementCollection
	@CollectionTable( joinColumns = @JoinColumn( name = "parent_id", nullable = false ) )
	private Set<Child> children = new HashSet<Child>();

	public Long getId()
	{
		return id;
	}

	public Set<Child> getChilds()
	{
		return children;
	}
	
	public void setChildren( Set<Child> children )
	{
		this.children = children;
	}

	

	@Override
	public int hashCode()
	{
		int hash = 0;
		hash += (getId() != null ? getId().hashCode() : 0);
		return hash;
	}


	@Override
	public boolean equals( Object object )
	{
		// TODO: Warning - this method won't work in the case the id fields are not set
		if( !(object instanceof Parent) )
		{
			return false;
		}
		Parent other = ( Parent ) object;
		if( (this.getId() == null && other.getId() != null) || (this.getId() != null && !this.getId().equals( other.
				getId() )) )
		{
			return false;
		}
		return true;
	}

}