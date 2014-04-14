package com.accenture.assets.ui.dialogs;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;

import com.accenture.assets.ui.dialogs.panels.NewAtributteDialogPanel;
import com.accenture.assets.utils.SwingUtils;

public class NewAtributteDialog extends JDialog{

	/** TAMAÑO VENTANA*/
	private static final Dimension WINDOW_SIZE = new Dimension(250,170);
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	/** Boton Cancelar*/
	private JButton cancelButton;

	/** Boton Aceptar*/
	private JButton agreeButton;
	
	/**
	 * 
	 */
	private JPanel contentPane;
	
	/** */
	private JPanel buttonsPanel;

	
	/**
	 * 
	 * @param owner
	 */
	public NewAtributteDialog(JFrame owner){
		super(owner,true);
		init();
	}

	/**
	 * 
	 */
	private void init() {
		setResizable(false);
		setTitle("New Attribute");
		setSize(WINDOW_SIZE);
		setLocation(SwingUtils.getCenterPoint(this));
		setContentPane(getContentPanel());
	}
	
	/**
	 * 
	 * @return
	 */
	private JButton getCancelButton(){
		cancelButton = new JButton("Cancel");
		return cancelButton;
	}
	/**
	 * 
	 * @return
	 */
	private JButton getAgreeButton(){
		agreeButton = new JButton("OK");
		return agreeButton;
	}
	
	/**
	 * 
	 * @return
	 */
	private JPanel getContentPanel(){
		contentPane = new JPanel();
		contentPane.setLayout(new BorderLayout());
		contentPane.add(BorderLayout.NORTH,new NewAtributteDialogPanel());
		contentPane.add(BorderLayout.SOUTH,getButtonsPanel());
		return contentPane;
	}
	
	/**
	 * 
	 * @return
	 */
	private JPanel getButtonsPanel(){
		buttonsPanel = new JPanel();
		buttonsPanel.setAlignmentX(LEFT_ALIGNMENT);
		buttonsPanel.add(getAgreeButton());
		buttonsPanel.add(getCancelButton());
		return buttonsPanel;
	}
}
