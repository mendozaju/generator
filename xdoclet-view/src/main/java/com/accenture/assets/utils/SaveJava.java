package com.accenture.assets.utils;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.accenture.assets.beans.Attribute;
import com.accenture.assets.beans.ClassElement;
import com.accenture.assets.beans.Project;
import com.accenture.assets.beans.Type;
import com.accenture.assets.exceptions.attributes.AtributoException;

public class SaveJava {

	public static void generateJava(Project project) {

		// for(int i =0;i<project.getPackages().size();i++){
		// //while (!project.getPackages().isEmpty()) {
		// PackageElement pkgTmp = new PackageElement();
		// //pkgTmp = project.getPackages().get(0);
		// pkgTmp = project.getPackages().get(i);
		// createPackageDirectory(pkgTmp, project.getLocWorkSpace());
		createPackageDirectory(project);
		// //project.getPackages().remove(0);
		//
		// }

	}

	// private static void createPackageDirectory(PackageElement pkgE,String
	// directorioWorkSpace) {
	// String directory = buildDirectorys(pkgE.getName());
	//
	// if (null != pkgE.getName() && !"".equals(pkgE.getName())) {
	// // creo directoros
	// //(new File("C:/AppGenerator/" + directory)).mkdirs();
	// (new File(directorioWorkSpace+ "/" + directory)).mkdirs();
	//
	// // creo clases dentro de ese directorio
	// for(int j=0; j< pkgE.getListClassElement().size();j++){
	// //while (!pkgE.getListClassElement().isEmpty()) {
	//
	// //
	// //createClass(pkgE.getListClassElement().get(0),
	// directory,pkgE.getName());
	// createClass(pkgE.getListClassElement().get(j),
	// directory,pkgE.getName(),directorioWorkSpace);
	// //pkgE.getListClassElement().remove(0);
	// }
	//
	// }
	// }
	
	private static void createPackageDirectory(Project project) {
		String directorioWorkSpace = project.getLocWorkSpace();
		String paquete = project.getGroupId() + "." + project.getArtifactId();
		String directory = buildDirectorys(paquete);
		
		if (null != directory && !"".equals(directory)) {
			// creo directoros
			//(new File(directorioWorkSpace + "/" + directory)).mkdirs();
			(new File(directorioWorkSpace)).mkdirs();
			
			// creo clases dentro de ese directorio
			for (int j = 0; j < project.getClasses().size(); j++) {
				// while (!pkgE.getListClassElement().isEmpty()) {
				
				//
				// createClass(pkgE.getListClassElement().get(0),
				// directory,pkgE.getName());
				//createClass(project.getClasses().get(j), directory, paquete, directorioWorkSpace);
				createClass(project.getClasses().get(j), directorioWorkSpace, project.getMappTypesJava());
				// pkgE.getListClassElement().remove(0);
			}
		}
	}

	

	//private static void createClass(ClassElement classE, String Direcotry, String paquete, String directorioWorkSpace) {
	private static void createClass(ClassElement classE, String directorioWorkSpace, Map<String,Type> mapaVariable) {
		AttrClass attC = new AttrClass();
		if (null != classE.getName() && !"".equals(classE.getName())) {
			attC.setNombreClase(classE.getName());
			// seteo el paquete
			//attC.setNombrePaquete(paquete);
			createAnnotations(attC, classE);

			// paso todos los datos YA procesados para ARMAR el .JAVA
			//armadoClaseGrabar(attC, Direcotry, directorioWorkSpace);
			armadoClaseGrabar(attC, directorioWorkSpace, mapaVariable);

		}

	}

	private static void createAnnotations(AttrClass attC, ClassElement classE) {

		if (null != classE.getAttributes()) {
			for (int i = 0; i < classE.getAttributes().size(); i++) {
				// while (!classE.getAttributes().isEmpty()) {
				Attribute attribute = new Attribute();
				// attribute = classE.getAttributes().get(0);
				attribute = classE.getAttributes().get(i);
				recorroAtributos(attC, attribute);
				// classE.getAttributes().remove(0);
			}
		}
		
		if(null != classE.getAttributesOfAttributes()){
			for (int i = 0; i < classE.getAttributesOfAttributes().size(); i++) {
				Attribute attribute = new Attribute();
				attribute = classE.getAttributesOfAttributes().get(i);
				//si el nombre tiene punto("XXX.YYY"), entonces entramos.
				if(attribute.getName().contains(".")){
					String tmpNombre = attribute.getName();
					//seteo el nombre para sacar el nombre de la clase
					attribute.setName(eliminarNombreClase(attC, attribute.getName()));
					recorroAtributos(attC, attribute);
					//vuelvo a poner el nombre original para que no se pierda.
					attribute.setName(tmpNombre);
				}	
				
			}
		}

	}

	private static void recorroAtributos(AttrClass attC, Attribute attributo) {

		if (null != attributo.getName() && !("").equals(attributo.getName())) {
			// guardo nombre, tipo de la variable y todo lo relacionado a si es un GENERIC.
			//attC.addVariable(attributo.getType(), attributo.getName());
//			un objeto Attribute que lo guardo en el HashMp
//			guardo el nombre
//			Si isGeneric == true entonces guardo los demas valores dentro del objeto, sino nada.
			attC.addVariable(attributo.getType(), attributo);
			
			// compruebo si la variable tiene seteado TRUE en alguna Annote
			if (null != attributo.getCreate() && attributo.getCreate()) {
				attC.addCreate(attributo.getName());
			}
			if (null != attributo.getDelete() && attributo.getDelete()) {
				attC.addDelete(attributo.getName());
			}
			if (null != attributo.getDetail() && attributo.getDetail()) {
				attC.addDetail(attributo.getName());
			}
			if (null != attributo.getGrid() && attributo.getGrid()) {
				attC.addGrid(attributo.getName());
			}
			if (null != attributo.getId() && attributo.getId()) {
				attC.addId(attributo.getName());
			}
			if (null != attributo.getModify() && attributo.getModify()) {
				attC.addModify(attributo.getName());
			}
			if (null != attributo.getSearch() && attributo.getSearch()) {
				attC.addSearch(attributo.getName());
			}

		}

	}

	//private static void armadoClaseGrabar(AttrClass attC, String Directory, String directorioWorkSpace) {
	private static void armadoClaseGrabar(AttrClass attC, String directorioWorkSpace, Map<String,Type> mapaVariable) {
		try {

			// if Interface
			// else clase

			// Create file
			// FileWriter fstream = new FileWriter("C:/AppGenerator/"+Directory
			// + "/" + attC.getNombreClase() + ".java");
			//FileWriter fstream = new FileWriter(directorioWorkSpace + "/" + Directory + "/" + attC.getNombreClase() + ".java");
			FileWriter fstream = new FileWriter(directorioWorkSpace + "/" + attC.getNombreClase() + ".java");
			BufferedWriter bWriter = new BufferedWriter(fstream);

			FormatJava fomatToJava = new FormatJava();
			bWriter = fomatToJava.fomatToJava(bWriter, attC, mapaVariable);

			bWriter.close();
		} catch (Exception e) {// Catch exception if any
			System.err.println("Error: " + e.getMessage());
		}

	}

	private static String buildDirectorys(String directory) {
		directory = directory.replace('.', '/');
		return directory;
	}

	public static void main(String[] args) {

		Project proj = new Project();
		proj.setArtifactId("artiF");
		proj.setGroupId("groupI");
		proj.setName("NombrePro");
		proj.setTechnology("tecnologia");
		proj.setVersion("4.0");
		proj.setLocWorkSpace("C:\\");

		// PackageElement packEA = new PackageElement();
		// packEA.setName("com.accenture");
		//
		// PackageElement packEB = new PackageElement();
		// packEB.setName("com.accenture.fran");

		// Type stringType = ((Features)
		// ContextHelper.getBean(ContextHelper.FEATURES)).getTypes().values().iterator().next();
		Type stringType = new Type();
		Type stringType1 = new Type();
		Type stringType2 = new Type();

		Attribute attId = new Attribute();
		attId.setName("id");
		stringType.setName("Float");
		attId.setType(stringType);
		attId.setCreate(true);

		Attribute attNombre = new Attribute();
		attNombre.setName("nombre");
		stringType1.setName("String");
		attNombre.setType(stringType1);
		attNombre.setDelete(true);
		attNombre.setCreate(true);
		attNombre.setDetail(true);
		attNombre.setGrid(true);

		Attribute attApellido = new Attribute();
		attApellido.setName("apellido");
		stringType2.setName("int");
		attApellido.setType(stringType2);
		attApellido.setCreate(false);

		Attribute attCalle = new Attribute();
		attCalle.setName("calle");
		attCalle.setType(stringType);

		Attribute attNumero = new Attribute();
		attNumero.setName("numero");
		attNumero.setType(stringType);

		Attribute attLocalidad = new Attribute();
		attLocalidad.setName("localidad");
		attLocalidad.setType(stringType);
		

		Attribute attTipo = new Attribute();
		attTipo.setName("tipo");
		attTipo.setType(stringType);

		//****Attributo GENERIC
		Attribute attGene = new Attribute();
		attGene.setName("listaG");
		Type tipoG = new Type();
		tipoG.setName("List");
		attGene.setType(tipoG);
		attGene.setGenericTypeValue("java.lang.String");
		attGene.setCustomTypeValue(false);
		
		//Fin Generic
		
		ClassElement claEA = new ClassElement();
		claEA.setName("Cliente");
		claEA.addAttribute(attId);
		claEA.addAttribute(attNombre);
		claEA.addAttribute(attApellido);
		claEA.addAttribute(attGene);
		
		ClassElement domicilio = new ClassElement();
		domicilio.setName("Domicilio");
		domicilio.addAttribute(attId);
		domicilio.addAttribute(attCalle);
		domicilio.addAttribute(attNumero);
		domicilio.addAttribute(attLocalidad);

		ClassElement domicilio1 = new ClassElement();
		domicilio1.setName("Domicilio1");
		domicilio1.addAttribute(attId);
		domicilio1.addAttribute(attCalle);
		domicilio1.addAttribute(attNumero);
		domicilio1.addAttribute(attLocalidad);

		// packEA.addListClassElement(claEA);
		// packEA.addListClassElement(domicilio);
		//
		// packEB.addListClassElement(domicilio1);

		proj.addClasses(claEA);
		proj.addClasses(domicilio);
		proj.addClasses(domicilio1);
		// Marcelo-INICIO

		// proj.addPackageE(packEA);
		//
		// proj.addPackageE(packEB);

		// Marceclo-FIN
		generateJava(proj);
	}
	
	private static String eliminarNombreClase(AttrClass attC, String nombreAtributo){
		
		String resultado="";
		resultado = nombreAtributo.replaceFirst(attC.getNombreClase()+".", "");
		String variable = resultado.substring(resultado.indexOf("."), resultado.length());
		resultado = resultado.substring(0, resultado.indexOf("."));
		
		List<Attribute> tmpLista = new ArrayList<Attribute>();
		Type typeTmp = new Type();
		typeTmp.setName(resultado);
		tmpLista = attC.getVariable().get(typeTmp);
		resultado = tmpLista.get(0).getName();
		return resultado+variable;
	}
	

}
