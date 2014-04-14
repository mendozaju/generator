package com.accenture.assets.ui.actions;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultListModel;
import javax.swing.JList;

public class RemoveInterfaceImlementationActionListener implements
		ActionListener {

	private JList sourceList;
	
	private DefaultListModel sourceListModel;
	
	public RemoveInterfaceImlementationActionListener(JList sourceList,DefaultListModel sourceListModel){
		this.sourceList = sourceList;
		this.sourceListModel = sourceListModel;
	}
	
	public void actionPerformed(ActionEvent e) {
//		Controlo si hay algun elemento seleccionado en la lista
		if(!sourceList.isSelectionEmpty()){
//			Obtengo el item seleccionado de la lista
			Object itemSelected = sourceList.getSelectedValue();
//			Remuevo el elemento seleccionado de la lista
			sourceListModel.removeElement(itemSelected);
		}
		else{
//			TODO NOTIFICAR AL USUARIO QUE DEBE SELECCIONAR UN ELEMENTO DE LA LISTA
		}
	}

}
