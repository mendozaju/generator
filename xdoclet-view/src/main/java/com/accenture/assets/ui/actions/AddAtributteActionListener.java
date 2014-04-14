package com.accenture.assets.ui.actions;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.table.DefaultTableModel;

import com.accenture.assets.ui.dialogs.NewAtributteDialog;

public class AddAtributteActionListener implements ActionListener {
	
	private JFrame owner;
	
	private DefaultTableModel model;
	
	public AddAtributteActionListener(JFrame owner,DefaultTableModel model){
		this.owner = owner;
		this.model = model;
	}
	
	/**
	 * 
	 */
	public void actionPerformed(ActionEvent e) {
//		'FALSE' PARA QUE EL CHECKBOX ESTE DESTILDADO Y 'TRUE' PARA QUE ESTE ACTIVADO
//		model.addRow(new Object[]{false,"nombre","String",true,false,false,false,false,false,false,false});
		JDialog dialog = new NewAtributteDialog(owner);
		dialog.setVisible(true);
	}

}
