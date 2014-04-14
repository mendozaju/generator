package com.accenture.assets.utils;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.accenture.assets.beans.Attribute;
import com.accenture.assets.beans.ClassElement;
import com.accenture.assets.beans.Project;
import com.accenture.assets.beans.Type;
import com.accenture.assets.exceptions.classes.ClaseException;

public class ReadXMLFile {

	private static Project project = new Project();
	public static void main(String argv[]) {

		File fXmlFile = new File("NombrePro.xml");
		//project = new Project();
		project.setArchivo(fXmlFile);
		leerXML(project);
	}

	public static Project leerXML(Project project) {
		File fXmlFile = project.getArchivo();
		//project = new Project();
		try {

			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(fXmlFile);
			doc.getDocumentElement().normalize();

			doc.getChildNodes();
			doc.getFirstChild();
			doc.getLastChild();

			NodeList nList = doc.getElementsByTagName("AppGenerator");

			for (int temp = 0; temp < nList.getLength(); temp++) {

				Node nNode = nList.item(temp);
				nNode.getChildNodes();

				Element eElement = (Element) nNode;

				project.setName(getTagValue("project", eElement));

				project.setGroupId(getTagValue("groupId", eElement));

				project.setArtifactId(getTagValue("artifactID", eElement));

				project.setVersion(getTagValue("version", eElement));

				project.setTechnology(getTagValue("tecnologia", eElement));

				classSN("classes", eElement, project);

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return project;
	}

	private static void classSN(String sTag, Element eElement, Project project) {
		NodeList nlList = eElement.getElementsByTagName(sTag).item(0).getChildNodes();
		for (int i = 0; i < nlList.getLength(); i++) {
			ClassElement classE = new ClassElement();
			Node nNode = nlList.item(i);
			Element eElement1 = (Element) nNode;
			classE.setName(getTagValue("className", eElement1));

			// extends
			extendsSN("classExtend", eElement1, classE);
			// implement
			implementSN("implements", eElement1, classE);
			// atributos
			//sattributesSN("attributes", eElement1, classE);
			classE.setAttributes(attributesSN("attributes", eElement1));

			// atributos de atriburos
			//attributesOfAttriburesSN("attributesOfAttribures", eElement1, classE);
			classE.setAttributesOfAttributes(attributesOfAttriburesSN("attributesOfAttribures", eElement1));
			
			// packageE.addListClassElement(classE);
			project.addClasses(classE);
		}

	}

	private static void extendsSN(String sTag, Element eElement, ClassElement classE) {
		// System.out.println("terminar lo de EXTENDS");
		if (null != eElement.getElementsByTagName(sTag).item(0)) {
			NodeList nlList = eElement.getElementsByTagName(sTag).item(0).getChildNodes();
			// System.out.println("EXTENDS: " + getTagValue("classExtend",
			// eElement));
			// ... veremos
			// classE.setExtend(extend)
		}
	}

	private static void implementSN(String sTag, Element eElement, ClassElement classE) {
		// System.out.println("terminar lo de IMPLEMENT");
		if (null != eElement.getElementsByTagName(sTag).item(0)) {
			NodeList nlList = eElement.getElementsByTagName(sTag).item(0).getChildNodes();
			// System.out.println("implements Tamaño: " + nlList.getLength());
			// Debe ser del tipo Interface!!
			List<String> implementLista = new ArrayList<String>();
			for (int i = 0; i < nlList.getLength(); i++) {

				Node nNode = nlList.item(i);
				Element eElement1 = (Element) nNode;
				// System.out.println("implements nombre: " +
				// getTagValue("classImplement", eElement1));
				implementLista.add(getTagValue("classImplement", eElement1));
			}
			// classE.setInterfaces(implementLista);
		}
	}

	//private static void attributesSN(String sTag, Element eElement, ClassElement classE) {
	private static List<Attribute> attributesSN(String sTag, Element eElement) {
		List<Attribute> attributesList = new ArrayList<Attribute>();
		if (null != eElement.getElementsByTagName(sTag).item(0)) {
			NodeList nlList = eElement.getElementsByTagName(sTag).item(0).getChildNodes();
			//List<Attribute> attributesList = new ArrayList<Attribute>();
			for (int i = 0; i < nlList.getLength(); i++) {
				Attribute attrib = new Attribute();
				Node nNode = nlList.item(i);
				Element eElement1 = (Element) nNode;
				// nombre atributo
				attrib.setName(getTagValue("attributeName", eElement1));
				// tipo
				Type tipo = new Type();
				tipo.setName(getTagValue("attributeType", eElement1));
				attrib.setType(tipo);

				// Annotation!!
				attrib.setId(Boolean.parseBoolean(getTagValue("id", eElement1)));
				attrib.setDetail(Boolean.parseBoolean(getTagValue("detail", eElement1)));
				attrib.setSearch(Boolean.parseBoolean(getTagValue("search", eElement1)));
				attrib.setGrid(Boolean.parseBoolean(getTagValue("grid", eElement1)));
				attrib.setCreate(Boolean.parseBoolean(getTagValue("create", eElement1)));
				attrib.setModify(Boolean.parseBoolean(getTagValue("modify", eElement1)));
				attrib.setDelete(Boolean.parseBoolean(getTagValue("delete", eElement1)));

				attrib.setMulti(Boolean.parseBoolean(getTagValue("multi", eElement1)));

				// Defino los Generic !
				if (null != getTagValue("genericType", eElement1)) {
					attrib.setGenericTypeValue(getTagValue("genericType", eElement1));

					attrib.setCustomTypeValue(Boolean.parseBoolean(getTagValue("customType", eElement1)));
					project.setearTypes();
					attrib.setMultiMapa(attrib.getMulti(), project.getMappTypesJava());

					if (null != getTagValue("idCustomType", eElement1)) {
						attrib.setIdCustomTypeValue(getTagValue("idCustomType", eElement1));
					}
					if (null != getTagValue("viewFieldCustomType", eElement1)) {
						attrib.setViewFieldCustomTypeValue(getTagValue("viewFieldCustomType", eElement1));
					}

				}

				attributesList.add(attrib);
			}

			//classE.setAttributes(attributesList);
			
		}
		return attributesList;
	}

	private static List<Attribute> attributesOfAttriburesSN(String sTag, Element eElement) {
		List<Attribute> attributesOfAttributes = new ArrayList<Attribute>();
		if (null != eElement.getElementsByTagName(sTag).item(0)) {
			//NodeList nlList = eElement.getElementsByTagName(sTag).item(0).getChildNodes();
			attributesOfAttributes = (attributesSN("attributesOfAttribures", eElement));
		}
		return attributesOfAttributes;
	}

	private static String getTagValue(String sTag, Element eElement) {

		if (null != eElement.getElementsByTagName(sTag).item(0)) {
			NodeList nlList = eElement.getElementsByTagName(sTag).item(0).getChildNodes();

			Node nValue = (Node) nlList.item(0);
			return nValue.getNodeValue();
		} else {
			return "";
		}

	}

}
