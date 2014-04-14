package com.accenture.assets.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.accenture.assets.beans.Attribute;
import com.accenture.assets.beans.Type;

public class AttrClass {

	// variables

	private HashMap<Type, List<Attribute>> variable = new HashMap<Type, List<Attribute>>();

	// Atributos / Anotaciones
	private List<String> id = new ArrayList<String>();
	private List<String> detail = new ArrayList<String>();
	private List<String> search = new ArrayList<String>();
	private List<String> grid = new ArrayList<String>();
	private List<String> create = new ArrayList<String>();
	private List<String> modify = new ArrayList<String>();
	private List<String> delete = new ArrayList<String>();

	// Nombre de Clase
	private String nombreClase = "";
	
	//Paquete
	//private String nombrePaquete = "";

	// Atriburos
	private List<String> atributos = new ArrayList<String>();

	// Estructura del JAVA
	private String comentI = "/**";
	private String comentAs = "*";
	private String comentC = "*/";
	// annot
	private String arrID = "* @id";
	private String arrDetail = "* @detail";
	private String arrSearch = "* @search";
	private String arrGrid = "* @grid";
	private String arrCreate = "* @create";
	private String arrModify = "* @modify";
	private String arrDelete = "* @delete";
	private String arrGeneric="* @genericType";
	private String arrCustomType = "* @customType";
	private String arrIdCustomType = "* @idCustomType";
	private String arrViewFieldCustomType = "@* viewFieldCustomType";
	
	// public
	private String publCl = "public class";
	private String llaveAb = "{";
	private String llaveCe = "}";

	private String privado = "private";
	private String puntoYComa = ";";
	
	private String extiende = "extends";
	private String implementa = "implements";
	private String interfas = "interface";
	//private String paquete = "package";
	
	private String extendClase = "";
	private List<String> implementaLista = new ArrayList<String>();
	

//	public String getPaquete() {
//		return paquete;
//	}

	public String getExtendClase() {
		return extendClase;
	}

	public void setExtendClase(String extendClase) {
		this.extendClase = extendClase;
	}

	public List<String> getImplementaLista() {
		return implementaLista;
	}

	public void setImplementaLista(List<String> implementaLista) {
		this.implementaLista = implementaLista;
	}

	public HashMap<Type, List<Attribute>> getVariable() {
		return variable;
	}

	public void addVariable(Type tipo, Attribute attributo) {
		List<Attribute> variables = new ArrayList<Attribute>();
		if (variable.containsKey(tipo)) {
			variables = variable.get(tipo);
			variables.add(attributo);
			variable.put(tipo, variables);
		}
		else{
			variables.add(attributo);
			variable.put(tipo, variables);
		}
	}

	public List<String> getId() {
		return id;
	}

	public void addId(String idS) {
		id.add(idS);
	}

	public List<String> getDetail() {
		return detail;
	}

	public void addDetail(String detailS) {
		detail.add(detailS);
	}

	public List<String> getSearch() {
		return search;
	}

	public void addSearch(String searchS) {
		search.add(searchS);
	}

	public List<String> getGrid() {
		return grid;
	}

	public void addGrid(String gridS) {
		grid.add(gridS);
	}

	public List<String> getCreate() {
		return create;
	}

	public void addCreate(String createS) {
		create.add(createS);
	}

	public List<String> getModify() {
		return modify;
	}

	public void addModify(String modifyS) {
		modify.add(modifyS);
	}

	public List<String> getDelete() {
		return delete;
	}

	public void addDelete(String deleteS) {
		delete.add(deleteS);
	}

	public String getNombreClase() {
		return nombreClase;
	}

	public void setNombreClase(String nombreClase) {
		this.nombreClase = nombreClase;
	}

	public List<String> getAtributos() {
		return atributos;
	}

	public void setAtributos(List<String> atributos) {
		this.atributos = atributos;
	}

	public String getComentI() {
		return comentI;
	}

	public String getComentAs() {
		return comentAs;
	}

	public String getComentC() {
		return comentC;
	}

	public String getArrID() {
		return arrID;
	}

	public String getArrDetail() {
		return arrDetail;
	}

	public String getArrSearch() {
		return arrSearch;
	}

	public String getArrGrid() {
		return arrGrid;
	}

	public String getArrCreate() {
		return arrCreate;
	}

	public String getArrModify() {
		return arrModify;
	}

	public String getArrDelete() {
		return arrDelete;
	}

	public String getPublCl() {
		return publCl;
	}

	public String getLlaveAb() {
		return llaveAb;
	}

	public String getLlaveCe() {
		return llaveCe;
	}

	public String getPrivado() {
		return privado;
	}

	public String getPuntos() {
		return puntoYComa;
	}

	public String getExtiende() {
		return extiende;
	}

	public String getImplementa() {
		return implementa;
	}

	public String getInterfas() {
		return interfas;
	}

	public String getArrGeneric() {
		return arrGeneric;
	}

	public void setArrGeneric(String arrGeneric) {
		this.arrGeneric = arrGeneric;
	}

	public String getArrCustomType() {
		return arrCustomType;
	}

	public void setArrCustomType(String arrCustomType) {
		this.arrCustomType = arrCustomType;
	}

	public String getArrIdCustomType() {
		return arrIdCustomType;
	}

	public void setArrIdCustomType(String arrIdCustomType) {
		this.arrIdCustomType = arrIdCustomType;
	}

	public String getArrViewFieldCustomType() {
		return arrViewFieldCustomType;
	}

	public void setArrViewFieldCustomType(String arrViewFieldCustomType) {
		this.arrViewFieldCustomType = arrViewFieldCustomType;
	}

	
	
//	public String getNombrePaquete() {
//		return nombrePaquete;
//	}
//
//	public void setNombrePaquete(String nombrePaquete) {
//		this.nombrePaquete = nombrePaquete;
//	}

	
}
