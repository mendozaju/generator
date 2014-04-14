package com.accenture.assets.ui.forms.table;

import java.util.HashMap;
import java.util.Map;

import javax.swing.table.DefaultTableModel;

import com.accenture.assets.beans.Attribute;
import com.accenture.assets.beans.ClassElement;
import com.accenture.assets.helpers.ContextHelper;
import com.accenture.assets.helpers.Features;

public class NewDomainClassAttributeTable extends DefaultTableModel {

	/**	*/
	private static final long serialVersionUID = 1L;

	/** */
	private static Map<Integer, Class> editableColumnClasses;
	private static Map<Integer, Boolean> notEditableColumnClasses;

	static {
		// Column Classes
		editableColumnClasses = new HashMap<Integer, Class>();

		// Columna de seleccion
		editableColumnClasses.put(0, Boolean.class);
		// Nombre del atributo
		editableColumnClasses.put(1, String.class);
		// Tipo de clase del atributo
		editableColumnClasses.put(2, String.class);
		// Indica si es ID de la clase que lo contiene
		editableColumnClasses.put(3, Boolean.class);
		// Indica si es mostrable en el detalle
		editableColumnClasses.put(4, Boolean.class);
		// Indica si se utilizará como criterio de busqueda
		editableColumnClasses.put(5, Boolean.class);
		// Indica que el campo se mostrará en la grilla
		editableColumnClasses.put(6, Boolean.class);
		// Indica que el campo se puede crear
		editableColumnClasses.put(7, Boolean.class);
		// Indica que el campo se puede modificar
		editableColumnClasses.put(8, Boolean.class);
		// Indica que el campo se puede eliminar
		editableColumnClasses.put(9, Boolean.class);

		// Columnas no editable
		notEditableColumnClasses = new HashMap<Integer, Boolean>();
		notEditableColumnClasses.put(1, Boolean.FALSE);
		notEditableColumnClasses.put(2, Boolean.FALSE);
	}

	/**
	 * 
	 */
	public NewDomainClassAttributeTable() {
		
		// Contruyo la tabla, aqui cada una de las columnas
		super(new String[]{"", "Nombre", "Multi","Tipo", "Id", "Detalle", "Buscar", "Grilla", "Crear", "Modificar", "Eliminar","IdPadre"}, 0);
		
		// Prueba!
		Attribute att = new Attribute();
		att.setName("descripcion");
		att.setType(((Features) ContextHelper.getBean(ContextHelper.FEATURES)).getTypes().values().iterator().next());
		
		addRow(att);
	}

	/**
	 * 
	 */
	public void addRow(Attribute attribute) {
		this.addRow(new Object[]{
				Boolean.FALSE,
                attribute.getName(),
                attribute.getMulti(),
                attribute.getType().getName(),
                attribute.getId(),
                attribute.getDetail(),
                attribute.getSearch(),
                attribute.getGrid(),
                attribute.getCreate(),
                attribute.getModify(),
                attribute.getDelete()});
	}
	
	/**
	 * 
	 */
	public void deleteRow(ClassElement classElement) {
	
	}
	
	/**
	 * @see javax.swing.table.DefaultTableModel#isCellEditable(int, int)
	 */
	@Override
	public boolean isCellEditable(int row, int column) {
		Boolean isEditable = notEditableColumnClasses.get(column);
		if (isEditable != null) {
			return isEditable;
		}
		return true;
	}

	/**
	 * @see javax.swing.table.AbstractTableModel#getColumnClass(int)
	 */
	@Override
	public Class<?> getColumnClass(int columnIndex) {
		Class<?> columClass = editableColumnClasses.get(columnIndex);
		if (columClass != null) {
			return columClass;
		}
		return super.getColumnClass(columnIndex);
	}
}
