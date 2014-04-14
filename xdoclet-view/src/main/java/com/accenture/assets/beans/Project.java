package com.accenture.assets.beans;

import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.accenture.assets.helpers.ContextHelper;
import com.accenture.assets.helpers.Features;

/**
 * Provides parameters of configuration for generate Application.
 * 
 * @author adrian.musante
 * 
 */
public class Project implements Serializable {

	private static final long serialVersionUID = -8289172060357545866L;

	private String locInputSources;
	private String locWorkSpace;

	// Mvn parameters
	private String name;
	private String groupId;
	private String artifactId;
	private String version;
	private String technology;

	// Archivo
	private File archivo = null;

	// Model attributes
	// ReestructuracionPaquete
	private List<ClassElement> classes = new ArrayList<ClassElement>();
	// private List<PackageElement> packageE= new ArrayList<PackageElement>();

	// Marcelo-INICIO
	private Map<String, Type> mapeoTypes = new LinkedHashMap<String, Type>();
	
	//nn
	private Map<String, Type> mappTypesJava;

	
	public void setearTypes() {
		mapeoTypes = new LinkedHashMap<String, Type>();

		if( mappTypesJava == null)
			mappTypesJava = ((Features) ContextHelper.getBean(ContextHelper.FEATURES)).getTypes();
		
		for (Map.Entry entry : mappTypesJava.entrySet()) {
			mapeoTypes.put((String) entry.getKey(), (Type) entry.getValue());
		}

		Collections.sort(classes);
		Iterator<ClassElement> it = classes.iterator();
		while (it.hasNext()) {
			Type tipo = new Type();
			tipo.setName(it.next().getName());
			mapeoTypes.put(tipo.getName(), tipo);
		}
	}

	public void addType(Type type) {
		mapeoTypes.put(type.getTypeName(), type);
	}

	public void removeType(Type type) {
		mapeoTypes.remove(type);
	}

	public Map<String, Type> getTypes() {
		return mapeoTypes;
	}

	//nn
	public Map<String, Type> getMappTypesJava() {
		return mappTypesJava;
	}
		
	public Type getType(String typeName) {
		return mapeoTypes.get(typeName);
	}
	
	// Marcelo-FIN
	/**
	 * @return the locInputSources
	 */
	public String getLocInputSources() {
		return locInputSources;
	}

	/**
	 * @param locInputSources
	 *            the locInputSources to set
	 */
	public void setLocInputSources(String locInputSources) {
		this.locInputSources = locInputSources;
	}

	/**
	 * @return the locWorkSpace
	 */
	public String getLocWorkSpace() {
		return locWorkSpace;
	}

	/**
	 * @param locWorkSpace
	 *            the locWorkSpace to set
	 */
	public void setLocWorkSpace(String locWorkSpace) {
		this.locWorkSpace = locWorkSpace;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name
	 *            the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the groupId
	 */
	public String getGroupId() {
		return groupId;
	}

	/**
	 * @param groupId
	 *            the groupId to set
	 */
	public void setGroupId(String groupId) {
		this.groupId = groupId;
	}

	/**
	 * @return the artifactId
	 */
	public String getArtifactId() {
		return artifactId;
	}

	/**
	 * @param artifactId
	 *            the artifactId to set
	 */
	public void setArtifactId(String artifactId) {
		this.artifactId = artifactId;
	}

	/**
	 * @return the version
	 */
	public String getVersion() {
		return version;
	}

	/**
	 * @param version
	 *            the version to set
	 */
	public void setVersion(String version) {
		this.version = version;
	}

	/**
	 * @return the technology
	 */
	public String getTechnology() {
		return technology;
	}

	/**
	 * @param technology
	 *            the technology to set
	 */
	public void setTechnology(String technology) {
		this.technology = technology;
	}

	// ReestructuracionPaquete
	// Fran - Inicio
	// public List<PackageElement> getPackages(){
	// return packageE;
	// }
	// public void setPackageE(List <PackageElement> packageE){
	// this.packageE=packageE;
	// }
	// public void addPackageE(PackageElement newPackage) throws
	// DuplicatedNameException{
	// if (this.packageE==null){
	// this.packageE = new ArrayList<PackageElement>();
	// }
	// if (packageE.contains(newPackage)){
	// throw new DuplicatedNameException("paquete");
	// }else{
	// this.packageE.add(newPackage);
	// }
	// }
	//
	// /**
	// * @return the classes
	// */
	// ReestructuracionPaquete
	public List<ClassElement> getClasses() {
		return classes;
	}

	// ReestructuracionPaquete

	/**
	 * @param classes
	 *            the classes to set
	 */
	public void setClasses(List<ClassElement> classes) {
		this.classes = classes;
	}

	/**
	 * 
	 * @param classElement
	 */
	public void addClasses(ClassElement classElement) {
		if (this.classes == null) {
			this.classes = new ArrayList<ClassElement>();
		}

		this.classes.add(classElement);
		Type nuevoTipo = new Type();
		nuevoTipo.setName(classElement.getName());
		this.addType(nuevoTipo);
	}

	public void removeClasses(ClassElement classElement) {
		if (this.classes == null) {
			this.classes = new ArrayList<ClassElement>();
		}
		this.classes.remove(classElement);
		Type nuevoTipo = new Type();
		nuevoTipo.setName(classElement.getName());
		this.removeType(nuevoTipo);
	}

	// ReestructuracionPaquete
	// Fran - Fin
	// Marcelo-INICIO
	// public PackageElement obtenerPackageElement(String packageElementName) {
	// for(int i=0;i<packageE.size();i++){
	// if (packageE.get(i).getName().equals(packageElementName)){
	// return packageE.get(i);
	// }
	// }
	// return null;
	// }
	//
	// public void addClasses(String packageElementName, ClassElement classElem)
	// throws DuplicatedNameException{
	// PackageElement packageElement =
	// obtenerPackageElement(packageElementName);
	// if (packageElement.getListClassElement().contains(classElem)){
	// throw new DuplicatedNameException("clase");
	// }
	// packageElement.addListClassElement(classElem);
	// }

	public File getArchivo() {
		return archivo;
	}

	public void setArchivo(File archivo) {
		this.archivo = archivo;
	}

	public ClassElement getClassByName(String clase) {
		ClassElement claseAux = new ClassElement();

		claseAux.setName(clase);

		for (int i = 0; i < classes.size(); i++) {
			if (classes.get(i).equals(claseAux)) {
				return classes.get(i);
			}
		}
		return null;
	}

	// Marcelo-FIN	

}
