package com.accenture.assets.ui.dialogs.panels;

import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.accenture.assets.helpers.JComboBoxProvider;

public class NewAtributteDialogPanel extends JPanel {

	private static final Dimension LABEL_DIMENSION = new Dimension(100,20);
	
	private static final Dimension INPUT_DIMENSION = new Dimension(100,20);
	
	/**	 */
	private static final long serialVersionUID = 1L;

	/** Etiqueta Nombre de atributo*/
	private JLabel atributteNameLabel;
	
	/** Entrada de nombre de atributo*/
	private JTextField atributteNameInput;
	
	/** Combo de Lista de tipos*/
	private JComboBox typeListCombo;
	
	/** Combo de Lista de elementos disponibles a partir del combo de tipos*/
	private JComboBox elementsAvaliableCombo;

	
//	Panels	
	
	/** */
	private JPanel generalPanel;
		
	public NewAtributteDialogPanel(){
		super();
		init();
	}

	private void init() {
		add(getFinalPanel());
	}
	
	/**
	 * 
	 * @return
	 */
	private JLabel getAtributteNameLabel(){
		atributteNameLabel = new JLabel("Atribute name:");
		atributteNameLabel.setPreferredSize(LABEL_DIMENSION);
		return atributteNameLabel;
	}
	/**
	 * 
	 * @return
	 */
	private JTextField getAtributteNameInput(){
		atributteNameInput = new JTextField();
		atributteNameInput.setPreferredSize(INPUT_DIMENSION);
		return atributteNameInput;
	}
	/**
	 * 
	 * @return
	 */
	private JComboBox getTypeListCombo(){
		typeListCombo = JComboBoxProvider.getTypes();
		typeListCombo.setPreferredSize(INPUT_DIMENSION);
		return typeListCombo;
	}
	/**
	 * 
	 * @return
	 */
	//FIXME: SE SETEA LA LISTA DE ELEMENTOS DISPONIBLES EN INVISIBLE. 
	//QUEDA PENDIENTE SABER SI SE SACA O SE HABILITA
	@SuppressWarnings("unused")
	private JComboBox getElementsAvaliableCombo(){
		elementsAvaliableCombo = new JComboBox();
		elementsAvaliableCombo.setPreferredSize(INPUT_DIMENSION);
		elementsAvaliableCombo.setVisible(Boolean.FALSE);
		return elementsAvaliableCombo;
	}
	/**
	 * 
	 * @return
	 */
	private JPanel getFinalPanel(){
		generalPanel = new JPanel();
		generalPanel.setLayout(new GridLayout(2,1,15,15));
		
		JPanel topPanel = new JPanel();
		topPanel.setLayout(new GridLayout(1,2,15,15));
		
		JPanel buttonPanel = new JPanel();
		buttonPanel.setLayout(new GridLayout(1,1,15,15));
		
		topPanel.add(getAtributteNameLabel());
		topPanel.add(getAtributteNameInput());
		buttonPanel.add(getTypeListCombo());
		
		generalPanel.add(topPanel);
		generalPanel.add(buttonPanel);
		
		return generalPanel;
	}

}
