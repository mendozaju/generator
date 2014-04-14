package com.accenture.assets.ui.dialogs;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Window;
import java.security.acl.Owner;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import com.accenture.assets.beans.Project;
import com.accenture.assets.ui.dialogs.panels.NewProjectConfigurationDialogPanel;
import com.accenture.assets.ui.forms.MainFrame;
import com.accenture.assets.utils.SwingUtils;

public class NewProjectConfigurationDialog extends JDialog {

	private static final Dimension WINDOWS_SIZE = new Dimension(350,250);
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/** Content Pane*/
	private JPanel contentPane;
	
	/** Panel*/
	private JPanel panelToBePut;
	
	/**
	 * 
	 * @param owner
	 */
	public NewProjectConfigurationDialog(JFrame owner){
		super(owner,true);
		init();
	}
	
	/**
	 */
	public NewProjectConfigurationDialog(JDialog owner){
		super(owner,true);
		init();	
	}
	

	private void init() {
		setTitle("Project Data");
		setResizable(false);
		setSize(WINDOWS_SIZE);
		setLocation(SwingUtils.getCenterPoint(this));
		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		
//		//Fran Inicio - Recupero la ventana padre y con ello el PROJECT
//		Object prueba =SwingUtilities.getWindowAncestor(this); 
//		MainFrame mF;
//		Project project = null; 
//		if(prueba instanceof JFrame){
//			project = new Project();
//			mF = (MainFrame)(prueba);
//			project = mF.getProject();
//		}
//		//Fran Fin
		setContentPane(getContent());
	}
	
	/**
	 * 
	 * @return
	 */
	private JPanel getPaneToBePut(){
		if(panelToBePut==null){
			panelToBePut= new NewProjectConfigurationDialogPanel(this);
		}
		return panelToBePut;
	}
	
	/**
	 * 
	 * @return
	 */
	private JPanel getContent(){
		if(contentPane==null){
			contentPane = new JPanel(new BorderLayout());
			contentPane.add(getPaneToBePut());
		}
		return contentPane;
	}

}
