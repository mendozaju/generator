/**
 * 
 */
package com.accenture.test.projectPropertiesContext;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import com.accenture.assets.beans.Attribute;
import com.accenture.assets.beans.ClassElement;
import com.accenture.assets.beans.Interface;
import com.accenture.assets.beans.Project;
import com.accenture.assets.beans.Type;
import com.accenture.assets.exceptions.attributes.AtributoException;
import com.accenture.assets.exceptions.classes.ClaseException;
import com.accenture.assets.helpers.ContextHelper;

/**
 * @author mauro.pereira
 * 
 */
public class ProjectPropertiesContextTest {

	private int cantidad;

	@Test
	public void caseSaveProjectPropertiesTest() {
		Project project = (Project) ContextHelper
				.getBean(ContextHelper.PROJECT_CONFIGURATION);
		// Marcelo-INICIO
		// List<PackageElement> packages = new ArrayList<PackageElement>();
		// project.setPackageE(packages);
		// PackageElement pe = new PackageElement();
		// pe.setName("paquete.prueba");
		// Marcelo-FIN
		List<ClassElement> classes = initializeClassElementList();
		// Marcelo-INICIO
		project.setClasses(classes);
		// pe.setListClassElement(classes);
		// Marcelo-FIN
		project.setArtifactId("artifactID");
		project.setGroupId("groupId");
		project.setVersion("version");
		while (cantidad < 4) {
			addProperties();
		}

		checkValues();
	}

	private List<ClassElement> initializeClassElementList() {
		ClassElement classObject = new ClassElement();
		List<ClassElement> classes = new ArrayList<ClassElement>();
		List<Attribute> attributes = new ArrayList<Attribute>();
		List<Interface> interfaces = new ArrayList<Interface>();

		classObject.setName("clase");

		classObject.setExtend(classObject);

		classObject.setAttributes(attributes);

		classObject.setInterfaces(interfaces);
		classes.add(classObject);
		return classes;
	}

	private void addProperties() {

		Project project = (Project) ContextHelper
				.getBean(ContextHelper.PROJECT_CONFIGURATION);
		// Marcelo-INICIO
		ClassElement classObject = project.getClasses().get(0);
		// ClassElement classObject =
		// project.getPackages().get(0).getListClassElement().get(0);
		// Marcelo-FIN
		Attribute attribute = new Attribute();
		Type type = new Type();
		// Marcelo-INCIO
		List<Attribute> attributes = project.getClasses().get(0)
				.getAttributes();
		// List<Attribute> attributes =
		// project.getPackages().get(0).getListClassElement().get(0).getAttributes();
		// Marcelo-FIN
		Interface interfaceElement = new Interface();
		// Marcelo-INCIO
		List<Interface> interfaces = project.getClasses().get(0)
				.getInterfaces();
		List<ClassElement> classes = project.getClasses();
		// List<Interface> interfaces =
		// project.getPackages().get(0).getListClassElement().get(0).getInterfaces();
		// List<ClassElement> classes =
		// project.getPackages().get(0).getListClassElement();
		// Marcelo-FIN
		attribute.setName("atributo" + cantidad);
		type.setName("tipo" + cantidad);
		attribute.setType(type);
		attributes.add(attribute);
		interfaceElement.setName("interfaz" + cantidad);
		interfaces.add(interfaceElement);
		classObject.setAttributes(attributes);
		classObject.setInterfaces(interfaces);

		classes.add(classObject);
		// Marcelo-INCIO
		project.setClasses(classes);
		// project.getPackages().get(0).setListClassElement(classes);
		// Marcelo-FIN
		cantidad++;
	}

	private void checkValues() {
		Project project = (Project) ContextHelper
				.getBean(ContextHelper.PROJECT_CONFIGURATION);
		// Marcelo-INCIO
		Assert.assertEquals(4, project.getClasses().get(0).getAttributes()
				.size());
		Assert.assertEquals(4, project.getClasses().get(0).getInterfaces()
				.size());
		Assert.assertEquals(null, project.getClasses().get(0).getExtend());
		// Assert.assertEquals(4,
		// project.getPackages().get(0).getListClassElement().get(0).getAttributes().size());
		// Assert.assertEquals(4,
		// project.getPackages().get(0).getListClassElement().get(0).getInterfaces().size());
		// Assert.assertEquals(null,
		// project.getPackages().get(0).getListClassElement().get(0).getExtend());
		// Marcelo-FIN
		System.out
				.println("--------------------------------------------------------------------------------"
						+ "--------------------------------------------------------------------------------");
		System.out
				.println("Test satisfactorio de almacenamiento de las propiedades del proyecto utilizando applicationContext. -ArtifactID: "
						+ project.getArtifactId()
						+ " -GroupID: "
						+ project.getGroupId()
						+ " -Version: "
						+ project.getVersion());
		System.out
				.println("--------------------------------------------------------------------------------"
						+ "--------------------------------------------------------------------------------");
	}

}
