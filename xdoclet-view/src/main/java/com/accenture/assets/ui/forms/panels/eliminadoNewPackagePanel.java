package com.accenture.assets.ui.forms.panels;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.accenture.assets.ui.actions.CloseNewClassPanelAction;
import com.accenture.assets.ui.forms.MainFrame;

public class eliminadoNewPackagePanel extends JPanel {

	/**	 */
	private static final long serialVersionUID = 1L;

	private static final Dimension INPUT_DIMENSION = new Dimension(300, 20);

	private static final Dimension LABEL_DIMENSION = new Dimension(150, 20);

	private static final Dimension BUTTON_DIMENSION = new Dimension(100, 20);

	private static final Dimension THIRD_ELEMENT_DIMENSION = new Dimension(40,
			20);

	// PACKAGE

	private JLabel packageNameLabel;

	private JTextField packageNameInput;

	// Bottom Buttons

	private JButton cancelButton;

	private JButton createButton;

	// PANELS

	/** Panel de clase */
	private JPanel packagePanel;

	/** Panel final a agregar al formulario */
	private JPanel finalPanel;

	/** Panal para botones de abajo */
	private JPanel bottomButtonsPanel;

	// OWNER

	/** Frame que lo contiene */
	private JFrame owner;

	public eliminadoNewPackagePanel(JFrame owner) {
		super();
		init();
		// addActionsListeners();
		this.owner = owner;
	}

	private void addActionsListeners() {
		cancelButton.addActionListener(new CloseNewClassPanelAction(this));
	}

	/**
	 * Inicializa los elementos en el panel
	 */
	public void init() {
		this.add(getFinalPanel());
	}

	/**
	 * 
	 * @return
	 */
	private JLabel getPackageNameLabel() {
		if (packageNameLabel == null) {
			packageNameLabel = new JLabel("Package name:");
			packageNameLabel.setPreferredSize(LABEL_DIMENSION);
		}
		return packageNameLabel;
	}

	/**
	 * 
	 * @return
	 */
	private JTextField getPackageNameInput() {
		if (packageNameInput == null) {
			packageNameInput = new JTextField();
			packageNameInput.setPreferredSize(INPUT_DIMENSION);
			packageNameInput.addFocusListener(new FocusListener() {

				@Override
				public void focusLost(FocusEvent e) {
					if (packageNameInput.getText().isEmpty()) {
						createButton.setEnabled(false);
					} else {
						createButton.setEnabled(true);
					}

				}

				@Override
				public void focusGained(FocusEvent e) {

				}
			});
		}
		return packageNameInput;
	}

	/**
	 * 
	 * @return
	 */
	/*
	 * private JLabel getClassLeyend(){ if(classLeyend==null){ classLeyend = new
	 * JLabel(".java"); classLeyend.setPreferredSize(THIRD_ELEMENT_DIMENSION); }
	 * return classLeyend; }
	 */
	// /**
	// *
	// * @return
	// */
	// private JLabel getExtendsLabel(){
	// if(extendsLabel==null){
	// extendsLabel = new JLabel("Extiende de:");
	// extendsLabel.setPreferredSize(LABEL_DIMENSION);
	// }
	// return extendsLabel;
	// }
	//
	// /**
	// *
	// * @return
	// */
	// private JComboBox getExtendsCombo(){
	// if(extendsCombo==null){
	// extendsCombo = new JComboBox();
	// extendsCombo.setPreferredSize(INPUT_DIMENSION);
	// }
	// return extendsCombo;
	// }
	//
	// /**
	// *
	// * @return
	// */
	// private JLabel getImplementsLabel(){
	// if(implementsLabel==null){
	// implementsLabel = new JLabel("Implementa:");
	// implementsLabel.setPreferredSize(LABEL_DIMENSION);
	// }
	// return implementsLabel;
	// }
	//
	// /**
	// *
	// * @return
	// */
	// private JComboBox getImplementsCombo(){
	// if(implementsCombo==null){
	// implementsCombo = new JComboBox();
	// implementsCombo.setPreferredSize(INPUT_DIMENSION);
	// }
	// return implementsCombo;
	// }
	//
	// /**
	// *
	// * @return
	// */
	// private JButton getAddInterfaceButton(){
	// if(addInterfaceButton==null){
	// addInterfaceButton = new JButton("+");
	// addInterfaceButton.setPreferredSize(THIRD_ELEMENT_DIMENSION);
	// addInterfaceButton.addActionListener(new
	// ImplementsInterfaceActionListener(getImplementsCombo(),getInterfacesListModel()));
	// }
	// return addInterfaceButton;
	// }
	//
	// /**
	// *
	// * @return
	// */
	// private JList getinterfacesList(){
	// if(interfacesList==null){
	// interfacesList = new JList(getInterfacesListModel());
	// }
	// return interfacesList;
	// }
	//
	// /**
	// *
	// * @return
	// */
	// private DefaultListModel getInterfacesListModel(){
	// if(interfacesListModel==null){
	// this.interfacesListModel = new DefaultListModel();
	// }
	// return interfacesListModel;
	// }
	//
	// /**
	// *
	// * @return
	// */
	// private JScrollPane getInterfacesListScroll(){
	// if(interfacesListScroll==null){
	// interfacesListScroll = new JScrollPane();
	// interfacesListScroll.getViewport().add(getinterfacesList());
	// interfacesListScroll.setPreferredSize(LIST_DIMENSION);
	// }
	// return interfacesListScroll;
	// }
	//
	// /**
	// *
	// * @return
	// */
	// private JButton getRemoveInterfaceButton(){
	// if(removeInterfaceButton==null){
	// removeInterfaceButton = new JButton("-");
	// removeInterfaceButton.setAlignmentY(TOP_ALIGNMENT);
	// removeInterfaceButton.addActionListener(new
	// RemoveInterfaceImlementationActionListener(getinterfacesList(),
	// getInterfacesListModel()));
	// }
	// return removeInterfaceButton;
	// }

	/**
	 * 
	 * @return
	 */

	/**
	 * 
	 * @return
	 */
	private JButton getCancelButton() {
		System.out.println("boton cancelar1");
		if (cancelButton == null) {
			System.out.println("boton cancelar2");
			cancelButton = new JButton("Cancel");
			cancelButton.setPreferredSize(BUTTON_DIMENSION);
			cancelButton.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					System.out.println("boton cancelar");
					// owner.dispose();
					((MainFrame) owner).cleanPanelRight();
				}
			});
		}
		return cancelButton;
	}

	/**
	 * 
	 * @return
	 */
	private JButton getCreateButton() {
		if (createButton == null) {
			createButton = new JButton("New");
			createButton.setPreferredSize(BUTTON_DIMENSION);
			createButton.setEnabled(false);
			// Marcelo-INICIO
			createButton.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
//					crearNuevoPaquete();
				}

			});
			// Marcelo-FIN
		}
		return createButton;
	}

//	private void crearNuevoPaquete() {
//		try{
//		PackageElement packageElement = new PackageElement();
//		packageElement.setName(packageNameInput.getText());
//		((MainFrame) owner).getProject().addPackageE(packageElement);
//		((MainFrame) owner).agregarNodoPaquete(packageElement);
//		inicializarPanel();
//		}catch(DuplicatedNameException e){
//			mensajeError(e);
//		}
//	}

	/**
	 * 
	 * @return
	 */
	private JPanel getPackagePanel() {
		if (packagePanel == null) {
			packagePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
			packagePanel.add(getPackageNameLabel());
			packagePanel.add(getPackageNameInput());
		}

		return packagePanel;
	}

	// /**
	// *
	// * @return
	// */
	// private JPanel getExtendsPanel(){
	// if(extendsPanel==null){
	// extendsPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
	// extendsPanel.add(getExtendsLabel());
	// extendsPanel.add(getExtendsCombo());
	// }
	// return extendsPanel;
	// }
	// /**
	// *
	// * @return
	// */
	// private JPanel getImplementsPanel(){
	// if(implementsPanel==null){
	// implementsPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
	// implementsPanel.add(getImplementsLabel());
	// implementsPanel.add(getImplementsCombo());
	// implementsPanel.add(getAddInterfaceButton());
	// }
	// return implementsPanel;
	// }
	// /**
	// *
	// * @return
	// */
	// private JPanel getInterfacesListPanel(){
	// if(interfacesListPanel==null){
	// interfacesListPanel = new JPanel();
	// interfacesListPanel.add(getInterfacesListScroll());
	// interfacesListPanel.add(getRemoveInterfaceButton());
	// }
	// return interfacesListPanel;
	// }

	/**
	 * 
	 * @return
	 */
	private JPanel getBottomButtonsPanel() {
		if (bottomButtonsPanel == null) {
			bottomButtonsPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
			bottomButtonsPanel.add(getCreateButton());
			bottomButtonsPanel.add(getCancelButton());
		}
		return bottomButtonsPanel;
	}

	/**
	 * 
	 * @return
	 */
	private JPanel getFinalPanel() {
		if (finalPanel == null) {
			finalPanel = new JPanel();
			finalPanel.setLayout(new BoxLayout(finalPanel, BoxLayout.Y_AXIS));
			finalPanel.add(getPackagePanel());
			// finalPanel.add(getExtendsPanel());
			// finalPanel.add(getImplementsPanel());
			// finalPanel.add(getInterfacesListPanel());

			finalPanel.add(getBottomButtonsPanel());
		}
		return finalPanel;
	}

	public void inicializarPanel() {
		packageNameInput.setText("");
		createButton.setEnabled(false);
	}
	
	private void mensajeError(Exception e) {
		JOptionPane.showMessageDialog(new JFrame(), e.getMessage(),
				"Validación", JOptionPane.ERROR_MESSAGE);
	}
}
