package com.accenture.assets.beans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.accenture.assets.exceptions.attributes.AtributoException;
import com.accenture.assets.exceptions.attributes.DuplicatedAttributeNameException;
import com.accenture.assets.exceptions.attributes.EmptyAttributeNameException;
import com.accenture.assets.exceptions.attributes.EmptyAttributeTypeException;
import com.accenture.assets.exceptions.attributes.SpacesInAttributeNameException;
import com.accenture.assets.exceptions.attributes.UpperCaseAttributeNameException;
import com.accenture.assets.exceptions.classes.ClaseException;
import com.accenture.assets.exceptions.classes.EmptyClassNameException;

/**
 * ClassElement to project.
 * 
 * @author adrian.musante
 *
 */
@SuppressWarnings("rawtypes")
public class ClassElement implements Serializable,Comparable<ClassElement> {

	private static final long serialVersionUID = 2912145599192462634L;
	
	private String name;
	
	private List<Attribute> attributes;
	
	private List<Attribute> attributesOfAttributes;	
		
	/**
	 * @return the attributesOfAttributes
	 */
	public List<Attribute> getAttributesOfAttributes() {
	
		return attributesOfAttributes;
	}

	
	/**
	 * @param attributesOfAttributes the attributesOfAttributes to set
	 */
	public void setAttributesOfAttributes(List<Attribute> attributesOfAttributes) {
	
		this.attributesOfAttributes = attributesOfAttributes;
	}
	private ClassElement extend;
	
	private List<Interface> interfaces;

	//Marcelo-INICIO
	public ClassElement() {
		super();
		attributes = new ArrayList<Attribute>();
		interfaces = new ArrayList<Interface>();
		attributesOfAttributes = new ArrayList<Attribute>();
	}
	//Marcelo-FIN
	//Fran I
	private boolean isInterface=false;
	
	
	

	public boolean getIsInterface() {
		return isInterface;
	}

	public void setInterface(boolean isInterface) {
		this.isInterface = isInterface;
	}
	//Fran F
	/**
	 * 
	 * @return
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * 
	 * @param name
	 */
	public void setName(String name){
		this.name = name;
	}
	
	/**
	 * 
	 */
	public void addAttribute(Attribute attribute){
		if (this.attributes == null) {
			this.attributes = new ArrayList<Attribute>();
		}
		this.attributes.add(attribute);
	}

	public void addAttributeOfAttribute(Attribute attribute){
		if (this.attributesOfAttributes == null) {
			this.attributesOfAttributes = new ArrayList<Attribute>();
		}
		this.attributesOfAttributes.add(attribute);
	}	
	/**
	 * 
	 * @param attributes
	 * @throws DuplicatedNameException 
	 * @throws EmptyTypeException 
	 * @throws EmptyNameException 
	 */
	public void setAttributes(List<Attribute> attributes) {
		this.attributes = attributes;
	//Marcelo-INICIO
	//	Se agregan de a 1 los atributos para que sean validados
	//	this.attributes = attributes;
//		this.attributes = new ArrayList<Attribute>();
//		Iterator<Attribute> it = attributes.iterator();
//		while(it.hasNext()){
//			this.addAttribute(it.next());
//		}
	}
	//Marcelo-FIN	
	/**
	 * 
	 * @param extend
	 */
	public void setExtend(ClassElement extend) {
		if (!this.equals(extend)) {
			this.extend = extend;
		}
	}
	
	/**
	 * 
	 * @return
	 */
	public ClassElement getExtend() {
		return extend;
	}
	
	/**
	 * 
	 * @return
	 */
	public List<Interface> getInterfaces() {
		return interfaces;
	}
	
	/**
	 * 
	 * @param interfaces
	 */
	public void setInterfaces(List<Interface> interfaces) {
		this.interfaces = interfaces;
	}
	
	
	/**
	 * 
	 * @param interfaze
	 */
	public void addInterface(Interface interfaze) {
		if (this.interfaces == null) {
			this.interfaces = new ArrayList<Interface>();
		}
		
		this.interfaces.add(interfaze);
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
		ClassElement other = (ClassElement) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}

	public List<Attribute> getAttributes() {
		return this.attributes;
	}

//Marcelo-Inicio
@Override
public int compareTo(ClassElement o) {
	 ClassElement ce = (ClassElement)o;  
	           
	 return this.name.compareTo(ce.getName());      
}
//Marcelo-Fin
}
