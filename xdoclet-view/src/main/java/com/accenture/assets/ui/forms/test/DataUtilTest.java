/**
 * 
 */
package com.accenture.assets.ui.forms.test;

import com.accenture.assets.beans.Attribute;
import com.accenture.assets.beans.ClassElement;
import com.accenture.assets.beans.CustomType;
import com.accenture.assets.beans.Project;
import com.accenture.assets.beans.Type;
import com.accenture.assets.exceptions.attributes.AtributoException;
import com.accenture.assets.exceptions.classes.ClaseException;
import com.accenture.assets.helpers.ContextHelper;
import com.accenture.assets.helpers.Features;

/**
 * @author pablo.m.ibarrola
 * 
 */
public class DataUtilTest {

	public static Project getExampleProject() {
		// ReestructuracionPaquete
		// Marcelo-INICIO
		// PackageElement pe = null;
		Project exampleProject = null;
		// Marcelo-FIN

		Type stringType = ((Features) ContextHelper
				.getBean(ContextHelper.FEATURES)).getTypes().values()
				.iterator().next();

		Attribute attId = new Attribute();
		attId.setName("id");
		attId.setType(stringType);

		Attribute attNombre = new Attribute();
		attNombre.setName("nombre");
		attNombre.setType(stringType);

		Attribute attApellido = new Attribute();
		attApellido.setName("apellido");
		attApellido.setType(stringType);

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

		ClassElement documento = new ClassElement();
		documento.setName("Documento");
		documento.addAttribute(attTipo);
		documento.addAttribute(attNumero);

		Attribute attDocumento = new Attribute();
		attDocumento.setName("documento");
		attDocumento
				.setType(new CustomType("ar.com.accenture.beans.Documento"));

		ClassElement cliente = new ClassElement();
		cliente.setName("Cliente");
		cliente.addAttribute(attId);
		cliente.addAttribute(attNombre);
		cliente.addAttribute(attApellido);
		cliente.addAttribute(attDocumento);

		ClassElement domicilio = new ClassElement();
		domicilio.setName("Domicilio");
		domicilio.addAttribute(attId);
		domicilio.addAttribute(attCalle);
		domicilio.addAttribute(attNumero);
		domicilio.addAttribute(attLocalidad);

		exampleProject = new Project();
		exampleProject.setName("Proyecto Prueba");
		exampleProject.setArtifactId("Artifact");
		exampleProject.setGroupId("Grupo");
		exampleProject.setTechnology("Stpring");
		exampleProject.setVersion("2.1");
		// ReestructuracionPaquete
		// Marcelo-INICIO
		//
		// pe = new PackageElement();
		// pe.setName("paquete.prueba");
		// exampleProject.addPackageE(pe);
		exampleProject.addClasses(cliente);
		exampleProject.addClasses(documento);
		exampleProject.addClasses(domicilio);

		// exampleProject.addClasses(cliente);
		// exampleProject.addClasses(documento);
		// exampleProject.addClasses(domicilio);
		// Marcelo-FIN
		return exampleProject;
	}
}