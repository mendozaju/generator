package com.accenture.assets.helpers;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.swing.JComboBox;


/**
 * Provides JComboBox for Application.
 * 
 * @author adrian.musante
 *
 */
public class JComboBoxProvider implements Serializable {

	private static final long serialVersionUID = 690687385170079935L;
	
	private static final Features features = (Features) ContextHelper.getBean(ContextHelper.FEATURES); 
	
	/**
	 * Generates a JComboBox from a collection.
	 * 
	 * @param list
	 *            Collection to become JComboBox.
	 * @param withSelectOption
	 *            <code>true</code>, if the generated Combobox should contains
	 *            label of select. Otherwise, set in <code>false</code>.
	 * @return {@link JComboBox} instance.
	 */
	private static JComboBox generate(Collection<?> list, boolean withSelectOption){
		JComboBox comboBox = new JComboBox();
		if(withSelectOption){
			comboBox.addItem("Select");//TODO: take it in the properties.
		}
		if(list != null && !list.isEmpty()){
			for (Object object : prepareListToCombo(list, true)) {
				comboBox.addItem(object);
			}
		}
		
		return comboBox;
	}
	
	/**
	 * Prepares a collection of objects to be converted to a JComboBox.
	 * 
	 * @param list
	 *            Collection to prepare.
	 * @param isAscending
	 *            <code>true</code>, if the list generated should be in
	 *            ascending order. Otherwise, set in <code>false</code>.
	 * @return {@link List}
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	private static <T> List<T> prepareListToCombo(Collection<T> list, final boolean isAscending) {
		List<T> newList = new ArrayList(list);
		Collections.sort(newList, new Comparator() {
			public int compare(Object obj1, Object obj2) {
				int state = 0;
				String firstString = obj1.toString();
				String secondString = obj2.toString();
				state = isAscending ? firstString
						.compareToIgnoreCase(secondString) : secondString
						.compareToIgnoreCase(firstString);
				return state;
			}
		});
		return newList;
	}
	
	// Provided.
	// -----------------------------------------------------------------------
	
	public static JComboBox getTechnologies(){
		return generate(features.getTechnologies().values(), true);
	}
	
	public static JComboBox getTypes(){
		return generate(features.getTypes().values(), true);
	}
	
}
