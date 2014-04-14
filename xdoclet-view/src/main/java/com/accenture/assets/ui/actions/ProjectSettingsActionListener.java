package com.accenture.assets.ui.actions;

import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JComboBox;
import javax.swing.JTextField;

public class ProjectSettingsActionListener implements ActionListener {
	
	private Window ventana;
	
	private JTextField groupID;
	
	private JTextField artifactID;
	
	private JTextField version;
	
	private JTextField folderRoot;
	
	private JComboBox technologies; 
	
	
	public ProjectSettingsActionListener(Window ventana,JTextField groupIDField,JTextField artifactIDField,JTextField versionField,JTextField folderRootField, JComboBox technologies){
		this.ventana = ventana;
		
		this.groupID = groupIDField;
		this.artifactID = artifactIDField;
		this.version = versionField;
		this.folderRoot = folderRootField;
		this.technologies = technologies;
		
	}
	
	public void actionPerformed(ActionEvent e) {
//		TODO GUARDAR LOS DATOS DEL PROJECTO EN UN ARCHIVO DE PROPIEDADES
//		Se Cierra la ventana
		ventana.dispose();
	}

}
