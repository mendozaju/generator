package com.accenture.assets.beans;

import java.io.Serializable;

/**
 * Type to Attribute.
 * 
 * @author adrian.musante
 *
 */
public class Type implements Serializable {

	private static final long serialVersionUID = 5994220542506546378L;

	private String name;
	
	public void setName(String name) {
		this.name = name;
	}

	public String getName() {	
		return name;
	}
	
	public String getTypeName(){
		return name != null ? name.substring(name.lastIndexOf(".")+1) : null;
	}

	@Override
	public String toString() {
		return getTypeName();
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Type other = (Type) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}
	
	
	
	
}
