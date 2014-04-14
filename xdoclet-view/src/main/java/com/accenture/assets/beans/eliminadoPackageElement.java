package com.accenture.assets.beans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.apache.bsf.Main;

import com.accenture.assets.ui.forms.MainFrame;

public class eliminadoPackageElement {
	private static final long serialVersionUID = 2912145599192462751L;
	private String name;
	private List<ClassElement> listClassElement;
	
//Marcelo-INICIO
	public eliminadoPackageElement() {
		super();
		listClassElement = new ArrayList<ClassElement>();
	}
//Marcelo-FIN	
	public String getName() {
		return name;
	}

	

	public void setName(String name) {
		this.name = name;
	}
	
	public void addListClassElement(ClassElement clase){
		if(listClassElement == null){
			listClassElement = new ArrayList<ClassElement>();
		}
		listClassElement.add(clase);
		//Marcelo-INICIO
		Type typeClase = new Type();
		typeClase.setName(this.name + "." + clase.getName());
		MainFrame.getInstancia().getProject().addType(typeClase);
		//Marcelo-FIN
	}
	
	public List<ClassElement> getListClassElement (){
		return listClassElement;
	}

	//Marcelo-INICIO
	public ClassElement obtenerClase(String clase) {
		for(int i=0;i<listClassElement.size();i++){
			if (listClassElement.get(i).equals(clase)){
				return listClassElement.get(i);
			}
		}
		return null;
	}
	
	public void setListClassElement(List<ClassElement> classes) {
		listClassElement = classes;
		
	}
	
//	@Override
//	public int compareTo(PackageElement p) {
//		return this.name.compareTo(p.getName());
//	}
//	
//	@Override
//	public boolean equals(Object obj) {
//		String nombre = ((PackageElement) obj).getName();
//		return this.name.equals(nombre);
//	}
//	
	
	//Marcelo-FIN


}
