package com.accenture.assets.ui.forms.table;

import java.awt.Component;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultCellEditor;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JTable;

import com.accenture.assets.enums.TiposLlamadaVentana;
import com.accenture.assets.ui.forms.MainFrame;
import com.accenture.assets.ui.forms.panels.NewDomainClassPanel;

public class JTableButtonModel extends DefaultCellEditor {
	
	/**
	 * @return the isPushed
	 */
	public boolean isPushed() {
	
		return isPushed;
	}


	
	/**
	 * @param isPushed the isPushed to set
	 */
	public void setPushed(boolean isPushed) {
	
		this.isPushed = isPushed;
	}

	protected JButton button;

	private String label;

	private boolean isPushed;

	private NewDomainClassPanel newDomainClassPanel;
	private Window parent;	
	private JTable table;


	public JTableButtonModel(JCheckBox checkBox, Window parent, NewDomainClassPanel newDomainClassPanel,JTable table){
		super(checkBox);
		button = new JButton();
		button.setOpaque(true);
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				fireEditingStopped();
			}
		});
		this.parent = parent;
		this.newDomainClassPanel = newDomainClassPanel;
		this.table = table;
	}


	public JTableButtonModel(JCheckBox checkBox) {
		super(checkBox);
		button = new JButton();
		button.setOpaque(true);
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				fireEditingStopped();
			}
		});
	}

	public Component getTableCellEditorComponent(JTable table, Object value,
	                                             boolean isSelected, int row, int column) {
		if (isSelected) {
			button.setForeground(table.getSelectionForeground());
			button.setBackground(table.getSelectionBackground());
		} else {
			button.setForeground(table.getForeground());
			button.setBackground(table.getBackground());
		}
		label = (value == null) ? "" : ""+value.toString();
		button.setText(label);
		isPushed = true;
		return button;
	}

	public Object getCellEditorValue() {
		if (isPushed) {
			if (!(newDomainClassPanel.getTipoLlamada().equals(TiposLlamadaVentana.SOLO_LECTURA))) {
				//NO PERMITE INGRESAR AL ID SI NO ESTA CREADA LA CLASS
				if(MainFrame.getInstancia().getProject().getClassByName(newDomainClassPanel.getClassNameInputText()) != null){
					// PARCHE PARA NO EJECUTAR INCEPTION DE INCEPTION (Eliminar cuando se implemente)
					if(!(newDomainClassPanel.getTipoLlamada().equals(TiposLlamadaVentana.INCEPTION))){    		
						int row = table.getSelectedRow();			 
						String valor = table.getValueAt(row, 2).toString();
						Boolean multiActivado = Boolean.parseBoolean(table.getValueAt(row, 1).toString());
						if(!(MainFrame.getInstancia().getProject().getMappTypesJava().containsKey(valor)) && (!multiActivado)) {
							MainFrame padre = ((MainFrame)parent); 
							padre.crearVentanaNewClass(valor, TiposLlamadaVentana.INCEPTION);
						} else {
							JOptionPane.showMessageDialog(button, "No se pueden editar atributos de tipos primitivos/listas");
						}
					}
				}
			}
		}
		isPushed = false;
		return new String(label);
	}

	public boolean stopCellEditing() {
		isPushed = false;
		return super.stopCellEditing();
	}

	protected void fireEditingStopped() {
		super.fireEditingStopped();
	}
}