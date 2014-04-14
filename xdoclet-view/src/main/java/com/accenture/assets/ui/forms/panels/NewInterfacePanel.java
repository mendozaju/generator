package com.accenture.assets.ui.forms.panels;


import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.swing.BoxLayout;
import javax.swing.DefaultCellEditor;
import javax.swing.DefaultListCellRenderer;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import org.jdesktop.beansbinding.AutoBinding;
import org.jdesktop.beansbinding.BindingGroup;
import org.jdesktop.beansbinding.ELProperty;
import org.jdesktop.swingbinding.JComboBoxBinding;
import org.jdesktop.swingbinding.JTableBinding;
import org.jdesktop.swingbinding.SwingBindings;

import com.accenture.assets.beans.Attribute;
import com.accenture.assets.beans.ClassElement;
import com.accenture.assets.beans.Type;
import com.accenture.assets.enums.TiposLlamadaVentana;
import com.accenture.assets.helpers.ContextHelper;
import com.accenture.assets.helpers.Features;
import com.accenture.assets.ui.actions.CloseNewClassPanelAction;
import com.accenture.assets.ui.forms.MainFrame;
import com.accenture.assets.ui.forms.table.NewDomainClassAttributeTable;
import com.accenture.assets.utils.TypeConverter;
import com.accenture.assets.utils.TypeListConverter;

public class NewInterfacePanel extends JPanel{

	/**	 */
	private static final long serialVersionUID = 1L;

	private static final Dimension INPUT_DIMENSION = new Dimension(300,20);
	
	private static final Dimension LABEL_DIMENSION = new Dimension(100,20);

	private static final Dimension LIST_DIMENSION = new Dimension(200,80);
	
	private static final Dimension BUTTON_DIMENSION = new Dimension(100,20);
	
	private static final Dimension THIRD_ELEMENT_DIMENSION = new Dimension(40,20);

//	CLASS
	
	private JLabel classNameLabel;
	
	private JTextField classNameInput;
	
	private JLabel classLeyend;
	
//	EXTENDS
//	
//	private JLabel extendsLabel;
//	
//	private JComboBox extendsCombo;
//	
////	IMPLEMENTS
//	private JLabel implementsLabel;
//	
//	private JComboBox implementsCombo;
//	
//	private JList interfacesList;
//	
//	private DefaultListModel interfacesListModel;
//
//	private JScrollPane interfacesListScroll;
//	
//	private JButton addInterfaceButton;
//	
//	private JButton removeInterfaceButton;

//	ATRIBUTTES
	private JButton addAtributteButton;
	
	private JButton removeAtributteButton;
	
	private JButton backButton;
	
	private JTable atributtesTable;
	
	private JScrollPane atributtesTableScroll;
	
	private NewDomainClassAttributeTable atributtesTableModel;

//	Bottom Buttons
	
	private JButton cancelButton;
	
	private JButton createButton;
	
//	PANELS
	
	/** Panel de clase*/
	private JPanel classPanel;
	
	/** Panel de clase padre*/
	private JPanel extendsPanel;
	
	/** Panel de interfaz a implementar*/
	private JPanel implementsPanel;
	
	/** Panel de lista de interfaces a implementar*/
	private JPanel interfacesListPanel;
	
	/**	Panel para boton de agregar atributo*/
	private JPanel addAtributtePanel;
	
	/** Panel para tabla de atributos*/
	private JPanel atributtesTablePanel;
	
	/** Panel para boton de eliminar atributos seleccionados en la tabla*/
	private JPanel removeAtributtesPanel;
	
	/** Panal para botones de abajo*/
	private JPanel bottomButtonsPanel;
	
	/** Panel final a agregar al formulario*/
	private JPanel finalPanel;
	
	
//	OWNER

	/** Frame que lo contiene*/
	private JFrame owner;
	
	public NewInterfacePanel(JFrame owner){
		super();
		init();
	//	addActionsListeners();
		this.owner = owner;
	}
	
	private void addActionsListeners() {
		cancelButton.addActionListener(new CloseNewClassPanelAction(this));
	}

	/**
	 * Inicializa los elementos en el panel
	 */
	public void init(){
		this.add(getFinalPanel());
	}
	
	
	/**
	 * 
	 * @return
	 */
	private JLabel getClassNameLabel(){
		if(classNameLabel==null){			
			classNameLabel = new JLabel("Interface name:");
			classNameLabel.setPreferredSize(LABEL_DIMENSION);
		}
		return classNameLabel;
	}
	
	/**
	 * 
	 * @return
	 */
	private JTextField getClassNameInput(){
		if(classNameInput==null){			
			classNameInput = new JTextField();
			classNameInput.setPreferredSize(INPUT_DIMENSION);
		}
		return classNameInput;
	}

	/**
	 * 
	 * @return
	 */
	private JLabel getClassLeyend(){
		if(classLeyend==null){
			classLeyend = new JLabel(".java");
			classLeyend.setPreferredSize(THIRD_ELEMENT_DIMENSION);	
		}
		return classLeyend;
	}
	
//	/**
//	 * 
//	 * @return
//	 */
//	private JLabel getExtendsLabel(){
//		if(extendsLabel==null){
//			extendsLabel = new JLabel("Extiende de:");
//			extendsLabel.setPreferredSize(LABEL_DIMENSION);			
//		}
//		return extendsLabel;
//	}
//	
//	/**
//	 * 
//	 * @return
//	 */
//	private JComboBox getExtendsCombo(){
//		if(extendsCombo==null){			
//			extendsCombo = new JComboBox();
//			extendsCombo.setPreferredSize(INPUT_DIMENSION);
//		}
//		return extendsCombo;
//	}
//	
//	/**
//	 * 
//	 * @return
//	 */
//	private JLabel getImplementsLabel(){
//		if(implementsLabel==null){			
//			implementsLabel = new JLabel("Implementa:");
//			implementsLabel.setPreferredSize(LABEL_DIMENSION);
//		}
//		return implementsLabel;
//	}
//	
//	/**
//	 * 
//	 * @return
//	 */
//	private JComboBox getImplementsCombo(){
//		if(implementsCombo==null){			
//			implementsCombo = new JComboBox();
//			implementsCombo.setPreferredSize(INPUT_DIMENSION);
//		}
//		return implementsCombo;
//	}
//	
//	/**
//	 * 
//	 * @return
//	 */
//	private JButton getAddInterfaceButton(){
//		if(addInterfaceButton==null){			
//			addInterfaceButton = new JButton("+");
//			addInterfaceButton.setPreferredSize(THIRD_ELEMENT_DIMENSION);
//			addInterfaceButton.addActionListener(new ImplementsInterfaceActionListener(getImplementsCombo(),getInterfacesListModel()));
//		}
//		return addInterfaceButton;
//	}
//	
//	/**
//	 * 
//	 * @return
//	 */
//	private JList getinterfacesList(){
//		if(interfacesList==null){			
//			interfacesList = new JList(getInterfacesListModel());
//		}
//		return interfacesList;
//	}
//	
//	/**
//	 * 
//	 * @return
//	 */
//	private DefaultListModel getInterfacesListModel(){
//		if(interfacesListModel==null){			
//			this.interfacesListModel = new DefaultListModel();
//		}
//		return interfacesListModel;
//	}
//	
//	/**
//	 * 
//	 * @return
//	 */
//	private JScrollPane getInterfacesListScroll(){
//		if(interfacesListScroll==null){			
//			interfacesListScroll = new JScrollPane();
//			interfacesListScroll.getViewport().add(getinterfacesList());
//			interfacesListScroll.setPreferredSize(LIST_DIMENSION);
//		}
//		return interfacesListScroll;
//	}
//	
//	/**
//	 * 
//	 * @return
//	 */
//	private JButton getRemoveInterfaceButton(){
//		if(removeInterfaceButton==null){			
//			removeInterfaceButton = new  JButton("-");
//			removeInterfaceButton.setAlignmentY(TOP_ALIGNMENT);
//			removeInterfaceButton.addActionListener(new RemoveInterfaceImlementationActionListener(getinterfacesList(), getInterfacesListModel()));
//		}
//		return removeInterfaceButton;
//	}
	
	/**
	 * 
	 * @return
	 */
	private JButton getAddAtrubutteButton(){
		if(addAtributteButton==null){
			addAtributteButton = new JButton("New");
			addAtributteButton.setPreferredSize(BUTTON_DIMENSION);
//			addAtributteButton.addActionListener(new AddAtributteActionListener(owner,(NewDomainClassAttributeTable) getAtributteTableModel()));
			addAtributteButton.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					addRowToTable();
					
					
				}
			});
					
		}
		return addAtributteButton;
	}
	
	/**
	 * 
	 * @return
	 */
	private JButton getRemoveAtributteButton(){
		if(removeAtributteButton==null){			
			removeAtributteButton = new JButton("X");
			
			removeAtributteButton.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					 org.jdesktop.swingbinding.JTableBinding jTableBinding = org.jdesktop.swingbinding.SwingBindings.createJTableBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, getAtributosList(), atributtesTable);
				        
				        
				        org.jdesktop.swingbinding.JTableBinding.ColumnBinding columnBinding = jTableBinding.addColumnBinding(org.jdesktop.beansbinding.ELProperty.create("${name}"));
				        columnBinding.setColumnName("Nombree");
				        columnBinding.setColumnClass(String.class);
				        
				        bindingGroup.addBinding(jTableBinding);
				        jTableBinding.bind();
				        
				        jScrollPane1.setViewportView(atributtesTable);
				        atributtesTable.getColumnModel().getColumn(0).setHeaderValue("atributos.title0");
				
				}
			});
		}
		return removeAtributteButton;
	}
	
	/* ********************************************** */
	/* ********************************************** */
	/* ********************************************** */
	/* ********************************************** */
	/* ********************************************** */
	/* ********************************************** */
	
	BindingGroup bindingGroup;
	JScrollPane jScrollPane1;
	JTableBinding jTableBinding;

	
	/**
	 * 
	 * @return
	 */
	private JTable getAtributteTable(){
		if(atributtesTable==null) {
			bindingGroup = new BindingGroup();
			jScrollPane1 = new JScrollPane();
			JComboBox typeCmbbox = new JComboBox();
			
			atributtesTable = new JTable();
//			atributtesTable = new JTable(getAtributteTableModel());
//			atributtesTable.addMouseListener(new ModifyAtributteMouseListener(owner,getAtributteTable()));
			

			atributtesTable.setAutoscrolls(true);
			atributtesTable.setName("atributosTable"); // NOI18N

			
			atributtesTable.setAutoscrolls(true);
			atributtesTable.setName("atributosTable"); // NOI18N

	        jTableBinding = SwingBindings.createJTableBinding(AutoBinding.UpdateStrategy.READ_WRITE, getAtributosList(), atributtesTable);
	        
//	    	// Nombre del atributo
//	    	private String name;
//	    	// Tipo de clase del atributo
//	    	private Type type;
//	    	// Indica si es ID de la clase que lo contiene
//	    	private Boolean id;
//	    	// Indica si es mostrable en el detalle
//	    	private Boolean detail;
//	    	// Indica si se utilizará como criterio de busqueda
//	    	private Boolean search;
//	    	// Indica que el campo se mostrará en la grilla
//	    	private Boolean grid;
//	    	// Indica que el campo se puede crear
//	    	private Boolean create;
//	    	// Indica que el campo se puede modificar
//	    	private Boolean modify;
//	    	// Indica que el campo se puede eliminar
//	    	private Boolean delete;

	        
	        
	        
	        // Columna -> Nombre
	    	JTableBinding.ColumnBinding columnBinding;	        
	        columnBinding = jTableBinding.addColumnBinding(ELProperty.create("${name}"));
	        columnBinding.setColumnName("Name");
	        columnBinding.setColumnClass(String.class);
	        
	        // Columna -> Tipos
	        columnBinding = jTableBinding.addColumnBinding(org.jdesktop.beansbinding.ELProperty.create("${type}"));
	        columnBinding.setColumnName("Type");
	        columnBinding.setColumnClass(Type.class);
	        columnBinding.setConverter(new TypeConverter());
	        

	        
	        bindingGroup.addBinding(jTableBinding);
	        jTableBinding.bind();
	        
	        jScrollPane1.setName("jScrollPane1"); // NOI18N
	        jScrollPane1.setViewportView(atributtesTable);
	        atributtesTable.getColumnModel().getColumn(0).setHeaderValue("Method Name");
	        atributtesTable.getColumnModel().getColumn(1).setHeaderValue("Type"); // NOI18N 
	        atributtesTable.getColumnModel().getColumn(1).setCellEditor(new DefaultCellEditor(typeCmbbox));
	        atributtesTable.getColumnModel().getColumn(1).setResizable(true);
//	        atributtesTable.getColumnModel().getColumn(2).setHeaderValue("Id");
//	        atributtesTable.getColumnModel().getColumn(3).setHeaderValue("Detail");
//	        atributtesTable.getColumnModel().getColumn(4).setHeaderValue("Search");
//	        atributtesTable.getColumnModel().getColumn(5).setHeaderValue("Grid");
//	        atributtesTable.getColumnModel().getColumn(6).setHeaderValue("Create");
//	        atributtesTable.getColumnModel().getColumn(7).setHeaderValue("Modify");
//	        atributtesTable.getColumnModel().getColumn(8).setHeaderValue("Delete");
	        

	        // ComoBox de tipos
	        Map<String, Type> typesMap = ((Features) ContextHelper.getBean(ContextHelper.FEATURES)).getTypes();
			ArrayList<Type> types = new ArrayList<Type>();
			types.addAll(typesMap.values());
	        
	        typeCmbbox.setName("typeJComboBox");
	        typeCmbbox.setRenderer(new DefaultListCellRenderer() {
	        	@Override
	        	public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
	        		if (value instanceof Type) {
						Type type = (Type) value;
						setText(type.getName());
	        		}
	        		return super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
	        	}
	        });

	        
	        JComboBoxBinding<Type,List<Type>,JComboBox> jComboBoxBinding = SwingBindings.createJComboBoxBinding(AutoBinding.UpdateStrategy.READ_WRITE, types, typeCmbbox);
	        
	        jComboBoxBinding.setConverter(new TypeListConverter());
	        
	        bindingGroup.addBinding(jComboBoxBinding);
	        jComboBoxBinding.bind();
	        
		}
		return atributtesTable;
	}
	
	
	
	public List<Attribute> getAtributosList() {
		ClassElement classElement = ((ClassElement) ContextHelper.getBean(ContextHelper.CLASS_ELEMENT_TEMP));
		if (classElement.getAttributes().size() == 1) {
			Type exampleType = ((Features) ContextHelper.getBean(ContextHelper.FEATURES)).getTypes().values().iterator().next();
			
			Attribute attId = new Attribute();
			attId.setName("Id");
			attId.setType(exampleType);

			Attribute attNombre = new Attribute();
			attNombre.setName("Nombre");
			attNombre.setType(exampleType);
			
			Attribute attApellido = new Attribute();
			attApellido.setName("Apellido");
			attApellido.setType(exampleType);

			//Marce-INICIO
//			classElement.addAttribute(attId);
//			classElement.addAttribute(attNombre);
//			classElement.addAttribute(attApellido);
			//Marce-FIN
		}
		return classElement.getAttributes();
	}
	
	
	/**
	 * 
	 * @return
	 */
	private JScrollPane getAtributteTableScroll(){
		if(atributtesTableScroll==null){
			atributtesTableScroll = new JScrollPane();
			atributtesTableScroll.getViewport().add(getAtributteTable());			
		}
		return atributtesTableScroll;
	}
	
	
	private void addRowToTable() {
		Attribute newAttribute = new Attribute();
		((ClassElement) ContextHelper.getBean(ContextHelper.CLASS_ELEMENT_TEMP)).getAttributes().add(newAttribute );
		atributtesTableScroll.getViewport().removeAll();
		atributtesTable = null;
		atributtesTableScroll.getViewport().add(getAtributteTable());
	}
	
	/**
	 * 
	 * @return
	 */
	private DefaultTableModel getAtributteTableModel(){
		if(atributtesTableModel==null){
			atributtesTableModel = new NewDomainClassAttributeTable();
		}
		return atributtesTableModel;
	}
	
//	/**
//	 * 
//	 * @return
//	 */
//	private JButton getCancelButton(){
//		if(cancelButton==null){			
//			cancelButton = new JButton("Cancelar");
//			cancelButton.setPreferredSize(BUTTON_DIMENSION);
//		}
//		return cancelButton;
//	}
	
	/**
	 * 
	 * @return
	 */
	private JButton getCancelButton(){
		System.out.println("boton cancelar1");
		if(cancelButton == null){
			System.out.println("boton cancelar2");
			cancelButton = new JButton("Cancel");
			cancelButton.setPreferredSize(BUTTON_DIMENSION);
			cancelButton.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					System.out.println("boton cancelar");
				//	owner.dispose();
					((MainFrame)owner).cleanPanelRight();
				}
			});
		}
		return cancelButton;
	}
	
	/**
	 * 
	 * @return
	 */
	private JButton getCreateButton(){
		if(createButton==null){			
			createButton = new JButton("New");
			createButton.setPreferredSize(BUTTON_DIMENSION);
		}
		return createButton;
	}
	
	/**
	 * 
	 * @return
	 */
	private JPanel getClassPanel(){
		if(classPanel==null){
			classPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
			classPanel.add(getClassNameLabel());
			classPanel.add(getClassNameInput());
			classPanel.add(getClassLeyend());			
		}
		return classPanel;
	}
//	/**
//	 * 
//	 * @return
//	 */
//	private JPanel getExtendsPanel(){
//		if(extendsPanel==null){
//			extendsPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
//			extendsPanel.add(getExtendsLabel());
//			extendsPanel.add(getExtendsCombo());			
//		}
//		return extendsPanel;
//	}
//	/**
//	 * 
//	 * @return
//	 */
//	private JPanel getImplementsPanel(){
//		if(implementsPanel==null){
//			implementsPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
//			implementsPanel.add(getImplementsLabel());
//			implementsPanel.add(getImplementsCombo());
//			implementsPanel.add(getAddInterfaceButton());			
//		}
//		return implementsPanel;
//	}
//	/**
//	 * 
//	 * @return
//	 */
//	private JPanel getInterfacesListPanel(){
//		if(interfacesListPanel==null){
//			interfacesListPanel = new JPanel();
//			interfacesListPanel.add(getInterfacesListScroll());
//			interfacesListPanel.add(getRemoveInterfaceButton());			
//		}
//		return interfacesListPanel;
//	}
	
	private JPanel getAddAtributtePanel(){
		if(addAtributtePanel==null){
			addAtributtePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
			addAtributtePanel.add(getAddAtrubutteButton());
		}
		return addAtributtePanel;
	}
	
	/**
	 * 
	 * @return
	 */
	private JPanel getAtributteTablePanel(){
		if(atributtesTablePanel==null){
			atributtesTablePanel = new JPanel();
			atributtesTablePanel.add(getAtributteTableScroll());			
		}
		return atributtesTablePanel;
	}
	
	/**
	 * 
	 * @return
	 */
	private JPanel getRemoveAtributtesPanel(){
		if(removeAtributtesPanel==null){
			removeAtributtesPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
			removeAtributtesPanel.add(getRemoveAtributteButton());
		}
		return removeAtributtesPanel;
	}
	/**
	 * 
	 * @return
	 */
	private JPanel getBottomButtonsPanel(){
		if(bottomButtonsPanel==null){
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
	private JPanel getFinalPanel(){
		if(finalPanel==null){			
			finalPanel = new JPanel();
			finalPanel.setLayout(new BoxLayout(finalPanel,BoxLayout.Y_AXIS));
			finalPanel.add(getClassPanel());
//			finalPanel.add(getExtendsPanel());
//			finalPanel.add(getImplementsPanel());
//			finalPanel.add(getInterfacesListPanel());
			finalPanel.add(getAddAtributtePanel());
			finalPanel.add(getAtributteTablePanel());
			finalPanel.add(getRemoveAtributtesPanel());
			finalPanel.add(getBottomButtonsPanel());
		}
		return finalPanel;
	}
}
