package com.accenture.assets.validators;

import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import com.accenture.assets.beans.Attribute;
import com.accenture.assets.beans.ClassElement;
import com.accenture.assets.exceptions.attributes.*;
import com.accenture.assets.exceptions.classes.ClaseException;
import com.accenture.assets.exceptions.classes.EmptyClassNameException;
import com.accenture.assets.exceptions.classes.LowerCaseClassNameException;
import com.accenture.assets.exceptions.classes.SpacesInClassNameException;
import com.accenture.assets.helpers.ContextHelper;
import com.accenture.assets.helpers.Features;
import com.accenture.assets.ui.forms.MainFrame;

public class ValidadorObjetos {

	// Marcelo-INICIO
	public static void validarAtributo(Attribute attribute,
			List<Attribute> atributos) throws AtributoException {
		if (attribute.getName() == null)
			throw new EmptyAttributeNameException();
		if (attribute.getName().isEmpty())
			throw new EmptyAttributeNameException();
		if (attribute.getType().getName() == null)
			throw new EmptyAttributeTypeException(attribute.getName());
		if (Collections.frequency(atributos, attribute) > 1)
			throw new DuplicatedAttributeNameException(attribute.getName());
		if (attribute.getName().contains(" "))
			throw new SpacesInAttributeNameException(attribute.getName());
		if (Character.isUpperCase(attribute.getName().charAt(0)))
			throw new UpperCaseAttributeNameException(attribute.getName());
		if ((((Features) ContextHelper.getBean(ContextHelper.FEATURES))
				.getReservedWords().containsValue(attribute.getName()))) {
			throw new ReservedWordAttributeNameException(attribute.getName());
		}
		if( !MainFrame.getInstancia().getProject().getMappTypesJava().containsKey(attribute.getType().toString().trim()) && attribute.isAnySelected()) {
			throw new InvalidSelectedException(attribute.getName());
		}
			
	}

//	public static void validarClase(ClassElement clase) throws ClaseException {
//		if (clase.getName() == null)
//			throw new EmptyClassNameException();
//		if (clase.getName().isEmpty())
//			throw new EmptyClassNameException();
//		if (MainFrame.getInstancia().getProject()
//				.getClassByName(clase.getName()) != null)
//			throw new DuplicatedClassNameException();
//		if (clase.getName().contains(" "))
//			throw new SpacesInClassNameException();
//		if (Character.isLowerCase(clase.getName().charAt(0)))
//			throw new LowerCaseClassNameException();
//	}

	public static void validarModificacionClase(ClassElement clase)
			throws ClaseException {
		if (clase.getName() == null)
			throw new EmptyClassNameException();
		if (clase.getName().isEmpty())
			throw new EmptyClassNameException();
		if (clase.getName().contains(" "))
			throw new SpacesInClassNameException();
		if (Character.isLowerCase(clase.getName().charAt(0)))
			throw new LowerCaseClassNameException();
	}
	
	public static void validarSeleccionadosClase(ClassElement clase) throws ClaseException {
		if (clase.getName() == null)
			throw new EmptyClassNameException();
		
		Iterator<Attribute> it = clase.getAttributes().iterator();
		while (it.hasNext()) {
			Attribute valor = (Attribute) it.next();

			if( !MainFrame.getInstancia().getProject().getMappTypesJava().containsKey(valor.getType().toString().trim()) && valor.isAnySelected()) {
				throw new EmptyClassNameException();
			}	
		}
	
	}
	// Marcelo-FIN
}


