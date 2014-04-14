package com.accenture.assets.beans;

import java.io.Serializable;
import java.util.Map;

/**
 * Attribute to ClassElement.
 * 
 * @author adrian.musante
 *
 */
public class Attribute implements Serializable,Comparable<Attribute> {

	private static final long serialVersionUID = 2747211528052976844L;
	
	//Parametros
//	ver
//	
//	  @genericType Role
//	  @customType  true
//	  @idCustomType id
//	  @viewFieldCustomType name 
	 
	
	// Nombre del atributo
	private String name;
	
	// Indica si es Multi, una Lista
	private Boolean multi=false;
	
	// Tipo de clase del atributo
	private Type type;
	
	// Indica si es ID de la clase que lo contiene
	private Boolean id=false;
	// Indica si es mostrable en el detalle
	private Boolean detail=false;
	// Indica si se utilizará como criterio de busqueda
	private Boolean search=false;
	// Indica que el campo se mostrará en la grilla
	private Boolean grid=false;
	// Indica que el campo se puede crear
	private Boolean create=false;
	// Indica que el campo se puede modificar
	private Boolean modify=false;
	// Indica que el campo se puede eliminar
	private Boolean delete=false;
	// texto para el botón de modificación
//	private String modificar=">";	
	
	
	//Es @genericType ?
	//private Boolean isGenericType = false;
	//Nombre del Tipo de Generic
	private String genericTypeValue="";
	//Es CustomType ?
	private Boolean customTypeValue=false;
	// ID que representa el CustomTypeValue
	private String idCustomTypeValue="";
	// Variable definida GenericTypes
	private String viewFieldCustomTypeValue="";
	//**************Falta alguno mas?
	
	
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the type
	 */
	public Type getType() {
		return type;
	}

	/**
	 * @param type the type to set
	 */
	public void setType(Type type) {
		this.type = type;
	}

	/**
	 * @return the id
	 */
	public Boolean getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Boolean id) {
		this.id = id;
	}

	/**
	 * @return the detail
	 */
	public Boolean getDetail() {
		return detail;
	}

	/**
	 * @param detail the detail to set
	 */
	public void setDetail(Boolean detail) {
		this.detail = detail;
	}

	/**
	 * @return the search
	 */
	public Boolean getSearch() {
		return search;
	}

	/**
	 * @param search the search to set
	 */
	public void setSearch(Boolean search) {
		this.search = search;
	}

	/**
	 * @return the grid
	 */
	public Boolean getGrid() {
		return grid;
	}

	/**
	 * @param grid the grid to set
	 */
	public void setGrid(Boolean grid) {
		this.grid = grid;
	}

	/**
	 * @return the create
	 */
	public Boolean getCreate() {
		return create;
	}

	/**
	 * @param create the create to set
	 */
	public void setCreate(Boolean create) {
		this.create = create;
	}

	/**
	 * @return the modify
	 */
	public Boolean getModify() {
		return modify;
	}

	/**
	 * @param modify the modify to set
	 */
	public void setModify(Boolean modify) {
		this.modify = modify;
	}

	/**
	 * @return the delete
	 */
	public Boolean getDelete() {
		return delete;
	}

	/**
	 * @param delete the delete to set
	 */
	public void setDelete(Boolean delete) {
		this.delete = delete;
	}

	/**
	 * @return the serialversionuid
	 */
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	

//	public Boolean isGenericType() {
//		return isGenericType;
//	}
//
//	public void setGenericType(Boolean isGenericType) {
//		this.isGenericType = isGenericType;
//	}

	public Boolean getMulti() {
		return multi;
	}
	
	public void setMulti(Boolean multi) {
		this.multi = multi;
	}

	public void setMultiMapa(Boolean multi, Map<String, Type> mapa) {
		this.multi = multi;
		
				
		if(multi && !mapa.containsKey(type.getName())){
			customTypeValue = true;
		}
		else{
			customTypeValue = false;
		}
		
	}

	public String getGenericTypeValue() {
		return genericTypeValue;
	}

	public void setGenericTypeValue(String genericTypeValue) {
		this.genericTypeValue = genericTypeValue;
	}

	public Boolean isCustomTypeValue() {
		return customTypeValue;
	}

	public void setCustomTypeValue(Boolean customTypeValue) {
		this.customTypeValue = customTypeValue;
	}

	public String getIdCustomTypeValue() {
		return idCustomTypeValue;
	}

	public void setIdCustomTypeValue(String idCustomTypeValue) {
		this.idCustomTypeValue = idCustomTypeValue;
	}

	public String getViewFieldCustomTypeValue() {
		return viewFieldCustomTypeValue;
	}

	public void setViewFieldCustomTypeValue(String viewFieldCustomTypeValue) {
		this.viewFieldCustomTypeValue = viewFieldCustomTypeValue;
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
		Attribute other = (Attribute) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}

	@Override
	public int compareTo(Attribute o) {
		return (this.name.compareTo(o.getName()));
	}

	
	/**
	 * @return the modificar
	 */
//	public String getModificar() {
//	
//		return modificar;
//	}

	
	/**
	 * @param modificar the modificar to set
	 */
//	public void setModificar(String modificar) {
//	
//		this.modificar = modificar;
//	}
	
	//nn
	public boolean isAnySelected(){
		return (id||search||create||modify||delete);
	}
}
