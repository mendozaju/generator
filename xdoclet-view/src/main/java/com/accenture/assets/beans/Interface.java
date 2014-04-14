package com.accenture.assets.beans;

import java.io.Serializable;
import java.util.Set;

/**
 * Interface model.
 * 
 * @author adrian.musante
 *
 */
public class Interface implements Serializable {

	private static final long serialVersionUID = 3540380219042119475L;
	
	private String name;
	private Set<Interface> extendList;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setExtendList(Set<Interface> extendList) {
		this.extendList = extendList;
	}
	public Set<Interface> getExtendList() {
		return extendList;
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
		Interface other = (Interface) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}
	
}
