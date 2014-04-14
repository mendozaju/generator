package com.accenture.assets.ui.actions;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultListModel;
import javax.swing.JComboBox;

public class ImplementsInterfaceActionListener implements ActionListener {

	/** */
	private JComboBox source;
	
	/** */
	private DefaultListModel target;
	
	/**
	 * 
	 * @param source
	 * @param target
	 */
	public ImplementsInterfaceActionListener(JComboBox source,DefaultListModel target){
		this.target = target;
		
		this.source = source;
	}
	
	/**
	 * 
	 */
	public void actionPerformed(ActionEvent e) {
		Object itemSelected = source.getSelectedItem();
//		Controlo que se haya seleccionado
		if(itemSelected!=null){
//			Controlo que no este contenido en la lista de elementos destino
			if(!target.contains(itemSelected)){				
				target.addElement(itemSelected);
			}
		}
		else{
//			TODO NOTIFICAR AL USUARIO QUE DEBE SELCCIONAR UNA INTERFAZ EN EL COMBO BOX
		}
	}
}