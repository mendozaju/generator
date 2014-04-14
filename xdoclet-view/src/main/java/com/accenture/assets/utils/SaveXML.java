package com.accenture.assets.utils;

import java.io.File;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.DOMImplementation;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Text;

import com.accenture.assets.beans.Attribute;
import com.accenture.assets.beans.ClassElement;
import com.accenture.assets.beans.Project;
import com.accenture.assets.beans.Type;

public class SaveXML {

	/**
	 * recibimos un objeto tipo PROJECT y lo vamos a guardar en XML
	 * 
	 * @param project
	 */

	public static void saveData(Project project) {

		try {

			DocumentBuilderFactory factory = DocumentBuilderFactory
					.newInstance();
			DocumentBuilder builder = factory.newDocumentBuilder();
			DOMImplementation implementation = builder.getDOMImplementation();
			Document document = implementation.createDocument(null,
					"AppGenerator", null);
			XML confXML = new XML(document);
			// document.setXmlVersion("1.0");

			Text text;

			// Pegamos la RAIZ al documento
			// document.getDocumentElement().appendChild(raiz);

			// pegamos ProjectName, Group, Artifac, Version, Tecnologia

			// pongo el nombre del proyecto en el documento
			text = document.createTextNode(project.getName());
			confXML.seteProjectName(text);

			// pongo el GROUPID
			text = document.createTextNode(project.getGroupId());
			confXML.seteGroupID(text);

			// pongo el ARTI
			text = document.createTextNode(project.getArtifactId());
			confXML.seteArtifactID(text);

			// pongo el Version
			text = document.createTextNode(project.getVersion());
			confXML.seteVersion(text);

			// pongo el Tecnologia
			text = document.createTextNode(project.getTechnology());
			confXML.seteTecnologia(text);

			// Compruebo si hay paquetes
			// if (!project.getPackages().isEmpty()) {
			// confXML.setrPackages(confXML.getrRaiz());
			// buildPackage(project, confXML, document);
			// }

			// saltamos la parte de paquetes y llamamos a clases

			if (!project.getClasses().isEmpty()) {
				confXML.setrClasses(confXML.getrRaiz());

				buildClasses(project.getClasses(), confXML, document);
			}

			// Grabo en el Disco

			Source source = new DOMSource(document);
			String archivo = project.getArchivo().toString();
			archivo = borraExtencion(archivo);
			Result result = new StreamResult(new java.io.File(archivo + ".xml")); // nombre
			// Result result = new StreamResult(new
			// java.io.File(project.getName()+".xml")); // nombre
			// del
			// archivo
			Transformer transformer = TransformerFactory.newInstance()
					.newTransformer();
			transformer.transform(source, result);

		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (TransformerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// private static void buildPackage(Project project, XML confXML, Document
	// doc) {
	// Text text;
	// for (int i=0; i<project.getPackages().size();i++){
	// //while (!project.getPackages().isEmpty()) {
	// confXML.setrPackage(confXML.getrPackages());
	// PackageElement pkgTmp = new PackageElement();
	// //pkgTmp = project.getPackages().get(0);
	// pkgTmp = project.getPackages().get(i);
	//
	// text = doc.createTextNode(pkgTmp.getName());
	// confXML.setePackageName(text, confXML.getrPackage());
	//
	// if (!pkgTmp.getListClassElement().isEmpty()) {
	// confXML.setrClasses(confXML.getrPackage());
	//
	// buildClasses(pkgTmp, confXML, doc);
	// }
	// // Interface buildInterface
	// // if (!EMPTY)
	// //project.getPackages().remove(0);
	// }
	//
	// }

	// private static void buildClasses(PackageElement pkgTmp, XML confXML,
	// Document doc) {
	private static void buildClasses(List<ClassElement> pkgTmp, XML confXML,
			Document doc) {
		Text text;
		// for (int i=0;i<pkgTmp.getListClassElement().size();i++){
		for (int i = 0; i < pkgTmp.size(); i++) {
			// while (!pkgTmp.getListClassElement().isEmpty()) {

			ClassElement classE = new ClassElement();
			// classE = pkgTmp.getListClassElement().get(0);
			// classE = pkgTmp.getListClassElement().get(i);
			classE = pkgTmp.get(i);
			if (!classE.getIsInterface()) {
				confXML.setrClass(confXML.getrClasses());
				text = doc.createTextNode(classE.getName());
				confXML.seteClassName(text, confXML.getrClass());
				// Para Extends
				// text = doc.createTextNode(classE.getExtend());
				// confXML.seteClassExtends(text, confXML.getrClass());
				// Interfaces
				if (null != classE.getInterfaces()
						&& !classE.getInterfaces().isEmpty()) {
					confXML.setrClassImplements(confXML.getrClass());
					// text = doc.createTextNode(classE.getInterfaces());
					// confXML.seteClassImplement(text,
					// confXML.getrClassImplements());
				}
				if (null != classE.getAttributes()
						|| !classE.getAttributes().isEmpty()) {
					confXML.setrClassAttributes(confXML.getrClass());
					//buildAttribute(classE, confXML, doc);
					buildAttribute(classE.getAttributes(), confXML, doc, confXML.getrClassAttributes());
				}
				
				//guardo atributos de atriburos
				if (null != classE.getAttributesOfAttributes() && !classE.getAttributesOfAttributes().isEmpty()){
					confXML.setrClassAttributesOfAttributes(confXML.getrClass());
					buildAttribute(classE.getAttributesOfAttributes(), confXML, doc,confXML.getrClassAttriburesOfAttributes());
				}
			}
			// pkgTmp.getListClassElement().remove(0);

		}

	}

	//private static void buildAttribute(ClassElement classE, XML confXML,
	private static void buildAttribute(List <Attribute>classE, XML confXML,
			Document doc, Element attibutos) {
		Text text;
		for (int i = 0; i < classE.size(); i++) {
			// while (!classE.getAttributes().isEmpty()) {
			//confXML.setrClassAttribute(confXML.getrClassAttributes());
			confXML.setrClassAttribute(attibutos);
			Attribute attr = new Attribute();
			// attr = classE.getAttributes().get(0);
			attr = classE.get(i);

			text = doc.createTextNode(attr.getName());
			confXML.seteClassAttributeName(text, confXML.getrClassAttribute());

			text = doc.createTextNode((attr.getType()).getName());
			confXML.seteClassAttributeType(text, confXML.getrClassAttribute());
			// confXML.getrClassAttribute());

			//multi
			
			if(null !=attr.getMulti()){
				text = doc.createTextNode(new Boolean(attr.getMulti()).toString());
				confXML.seteClassAttributeMulti(text, confXML.getrClassAttribute());
			}
			//multi Fin
			
			if (null != attr.getId()) {
				text = doc.createTextNode(new Boolean(attr.getId()).toString());
				confXML.seteClassAttributeId(text, confXML.getrClassAttribute());
			}

			if (null != attr.getDetail()) {
				text = doc.createTextNode(new Boolean(attr.getDetail())
						.toString());
				confXML.seteClassAttributeDetail(text,
						confXML.getrClassAttribute());
			}

			if (null != attr.getSearch()) {
				text = doc.createTextNode(new Boolean(attr.getSearch())
						.toString());
				confXML.seteClassAttributeSearch(text,
						confXML.getrClassAttribute());
			}

			if (null != attr.getGrid()) {
				text = doc.createTextNode(new Boolean(attr.getGrid())
						.toString());
				confXML.seteClassAttributeGrid(text,
						confXML.getrClassAttribute());
			}

			if (null != attr.getCreate()) {
				text = doc.createTextNode(new Boolean(attr.getCreate())
						.toString());
				confXML.seteClassAttributeCreate(text,
						confXML.getrClassAttribute());
			}

			if (null != attr.getModify()) {
				text = doc.createTextNode(new Boolean(attr.getModify())
						.toString());
				confXML.seteClassAttributeModify(text,
						confXML.getrClassAttribute());
			}

			if (null != attr.getDelete()) {
				text = doc.createTextNode(new Boolean(attr.getDelete())
						.toString());
				confXML.seteClassAttributeDelete(text,
						confXML.getrClassAttribute());
			}
			
			
			//** para los Generic
			//if(null!=attr.getGenericTypeValue()&&!attr.getGenericTypeValue().equals("")){
			if(attr.getMulti()){
				
//				if(!attr.getGenericTypeValue().equals("")){
//					text = doc.createTextNode(attr.getGenericTypeValue());
//					confXML.seteClassGGenericType(text, confXML.getrClassAttribute());
//				
//				}
				if(!attr.getType().getName().equals("")){
					text = doc.createTextNode(attr.getType().getName());
					confXML.seteClassGGenericType(text, confXML.getrClassAttribute());
				}
				if(null != attr.isCustomTypeValue()){
					text = doc.createTextNode(new Boolean(attr.isCustomTypeValue()).toString());
					confXML.seteClassGCustomType(text, confXML.getrClassAttribute());
				
				}
				if(!attr.getIdCustomTypeValue().equals("")){
					text = doc.createTextNode(attr.getIdCustomTypeValue());
					confXML.seteClassGIdCustomType(text, confXML.getrClassAttribute());
				
				}
				
				if(!attr.getViewFieldCustomTypeValue().equals("")){
					text = doc.createTextNode(attr.getViewFieldCustomTypeValue());
					confXML.seteClassGViewFieldCustomType(text, confXML.getrClassAttribute());
				
				}
			
			}

			// classE.getAttributes().remove(0);
		}
	}

	private static void buildInterface(ClassElement classE, XML confXML,
			Document doc) {

	}

	public static void main(String[] args) {
		Project proj = new Project();
		proj.setArtifactId("artiF");
		proj.setGroupId("groupI");
		proj.setName("NombrePro");
		proj.setTechnology("tecnologia");
		proj.setVersion("4.0");
		proj.setLocWorkSpace("C:");

		File archivo = new File("pepe");
		proj.setArchivo(archivo);

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
		stringType.setName("String");
		attId.setType(stringType);
		attId.setCreate(true);

		Attribute attNombre = new Attribute();
		attNombre.setName("nombre");
		stringType1.setName("int");
		attNombre.setType(stringType1);
		attNombre.setDelete(true);
		attNombre.setCreate(true);
		attNombre.setDetail(true);
		attNombre.setGrid(true);

		Attribute attApellido = new Attribute();
		attApellido.setName("apellido");
		stringType2.setName("float");
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
		tipoG.setName("String");
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
		// proj.addPackageE(packEB);
		// Marceclo-FIN

		saveData(proj);
	}

	private static String borraExtencion(String url) {
		// These first few lines the same as Justin's
		File f = new File(url);

		// if it's a directory, don't remove the extention
		// if (f.isDirectory()) return url;

		String name = f.getName();

		// Now we know it's a file - don't need to do any special hidden
		// checking or contains() checking because of:
		final int lastPeriodPos = name.lastIndexOf('.');
		if (lastPeriodPos <= 0) {
			// No period after first character - return name as it was passed in
			return url;
		} else {
			// Remove the last period and everything after it
			File renamed = new File(f.getParent(), name.substring(0,
					lastPeriodPos));
			return renamed.getPath();
		}

	}

}
