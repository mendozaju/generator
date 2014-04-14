package com.accenture.assets.utils;

import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.Transformer;
import org.jdesktop.beansbinding.Converter;

import com.accenture.assets.beans.Type;
import com.accenture.assets.helpers.ContextHelper;
import com.accenture.assets.helpers.Features;

/**
 * Converter de List<Type> a List<String> y viceversa. 
 * 
 * Es necesario para bindear el combobox de la tabla de atributos. Ver NewClassDomainPanel.java
 * 
 */
public class TypeListConverter extends Converter<List<Type>, List> {
	
	@Override
	public List<Type> convertReverse(List value) {
		System.out.println("Converter Reverse - List");
		Map<String, Type> typesMap = ((Features) ContextHelper.getBean(ContextHelper.FEATURES)).getTypes();
		CollectionUtils.transform(value, new Transformer() {
			
			@Override
			public Object transform(Object input) {
				Map<String, Type> typesMap = ((Features) ContextHelper.getBean(ContextHelper.FEATURES)).getTypes();
				return typesMap.get(input);
			}
		});
		return value;
	}
	
	@Override
	public List convertForward(List<Type> value) {
		System.out.println("Converter fordward - List");
		CollectionUtils.transform(value, new Transformer() {
			
			@Override
			public Object transform(Object input) {
				
				return ((Type) input).getTypeName();
			}
		});
		return value;
	}
}