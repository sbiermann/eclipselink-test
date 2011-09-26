package org.test.entity;

import java.util.Date;
import javax.xml.datatype.XMLGregorianCalendar;


public enum RelationType
{

	STRING_T( String.class, String.class ),
	DATE_T( XMLGregorianCalendar.class, Date.class ),
	INTEGER_T( Double.class, Integer.class ),
	DOUBLE_T( Double.class, Double.class ),
	BOOLEAN_T( Boolean.class, Boolean.class );

	private Class<?> clazz1;
	private Class<?> clazz2;

	private RelationType( Class<?> clazz1, Class<?> clazz2 )
	{
		this.clazz1 = clazz1;
		this.clazz2 = clazz2;
	}


	public Class<?> getClazz1()
	{
		return clazz1;
	}


	public Class<?> getClazz2()
	{
		return clazz2;
	}

}