package com.accenture.assets.utils;

import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.jdesktop.beansbinding.Converter;

import com.accenture.assets.beans.Type;
import com.accenture.assets.helpers.ContextHelper;
import com.accenture.assets.helpers.Features;
import com.accenture.assets.ui.forms.MainFrame;


/**
 * Converter de Type a String 
 * 
 * Es necesario para bindear el combobox de la tabla de atributos. Ver NewClassDomainPanel.getAttributeTable()
 *  - columnBinding.setConverter(new TypeConverter()); -
 * 
 */
public class TypeConverter extends Converter<Object, Object> {

	
	/*
	 * TODO: Hay q reemplazar esto .. acá hice un convertidor 
	 * de Type a String y viceversa bidireccional. Siempre q entra un
	 * String lo convierto a Type y viceversa.
	 * 
	 * La implementación no debería ser de esta manera, se debería respetar 
	 * los métodos convertForward y convertReverse para convertir en una 
	 * direccion y en otra.
	 * 
	 * Lo que pasó es que la libreria beansbinding.jar tiene q llamar al metodo:
	 *   public String convertForward(Type obj);
	 * para dibujar la lista en el panel. Y luego debería llamar al metodo:
	 *   public Type convertReverse(String obj);
	 * para setear el valor elegido del combo al objeto correspondiente de la 
	 * lista de objetos de negocio.
	 * 
	 * Esto es la teoría pero a mi me estaba llamando siempre a los metodos:
	 * public Type convertForward(String obj);
	 * public Type convertReverse(String obj);
	 * 
	 * por eso puse este parche, pero hay q verlo con mas detenimiento.
	 */
	
	@Override
	public Object convertForward(Object obj) {
		
		Object converterFwdRvrs = converterFwdRvrs(obj);
		System.out.println(" ------------------------------------------------------------");
		System.out.println(" convertForward   ( TypeConverter )");
		System.out.println(" From: " + obj.getClass() + "  ( " + obj + " )");
		System.out.println(" To: " + converterFwdRvrs.getClass() + "  ( " + converterFwdRvrs + " )");
		return converterFwdRvrs;
	}

	@Override
	public Object convertReverse(Object obj) {
		Object converterFwdRvrs = converterFwdRvrs(obj);
		System.out.println(" ------------------------------------------------------------");
		System.out.println(" convertReverse   ( TypeConverter )");
		System.out.println(" From: " + obj.getClass() + "  ( " + obj + " )");
		System.out.println(" To: " + converterFwdRvrs.getClass() + "  ( " + converterFwdRvrs + " )");
		return converterFwdRvrs;
	}

	private Object converterFwdRvrs(Object obj) {
		if (obj instanceof String) {
			String value = (String) obj;
			//Marcelo-INICIO
			//Obteniendo los tipos del context helper no es posible 
			//seleccionar las clases que se agreguen como tipo de dato. 
			//Se modifica para que el map sea actualizado desde la ventana.
			//Map<String, Type> typesMap = ((Features) ContextHelper.getBean(ContextHelper.FEATURES)).getTypes();
			Map<String, Type> typesMap = MainFrame.getInstancia().getProject().getTypes();
			//Marcelo-FIN
			if (StringUtils.contains(value, ".")) {
				value = StringUtils.substringAfterLast(value, ".");
			}
			
			Type type = typesMap.get(value);
			return type;
		}		
		
		else if (obj instanceof Type) {
			Type value = (Type) obj;
			String typeValue = value.getTypeName();
			if (typeValue == null) {
				return "";
				
			}
			return typeValue;
		}
		return null;
	}

//	@Override
//	public Type convertForward(Object obj) {
//		
//		String value = (String) obj;
//		
//		System.out.println("Convert Reverse - Column");
//		Map<String, Type> typesMap = ((Features) ContextHelper.getBean(ContextHelper.FEATURES)).getTypes();
//		
//		if (StringUtils.contains(value, ".")) {
//			value = StringUtils.substringAfterLast(value, ".");
//		}
//		
//		Type type = typesMap.get(value);
//		return type;
//	}
//	
//	@Override
//	public Type convertReverse(Object obj) {
//		
//		String value = (String) obj;
//		
//		System.out.println("Convert Reverse - Column");
//		Map<String, Type> typesMap = ((Features) ContextHelper.getBean(ContextHelper.FEATURES)).getTypes();
//		
//		if (StringUtils.contains(value, ".")) {
//			value = StringUtils.substringAfterLast(value, ".");
//		}
//		
//		Type type = typesMap.get(value);
//		return type;
//	}
//
//	@Override
//	public Object convertReverse(Type value) {
//		System.out.println("Convert Fordward - Column");
//		return value.getTypeName();
//	}

//	@Override
//	public String convertReverse(Type value) {
//		System.out.println("Convert Fordward - Column");
//		return value.getName();
//	}

//	public Object convertForward(Object value) {
//		System.out.println("Convert Fordward - Column");
//		Object vl = value;
//		return vl;
//	}
	
	
	
	
//	public String convertForward(Type value) {
//		System.out.println("Convert Fordward - Column");
//		return value.getName();
//	}
//
//	public Type convertReverse(String value) {
//		System.out.println("Convert Reverse - Column");
//		Map<String, Type> typesMap = ((Features) ContextHelper.getBean(ContextHelper.FEATURES)).getTypes();
//		return typesMap.get(value);
//	}
//
//	@Override
//	public String convertForward(Object value) {
//		System.out.println("Convert Fordward - Column");
//		return ((Type)value).getName();
//	}
	
}