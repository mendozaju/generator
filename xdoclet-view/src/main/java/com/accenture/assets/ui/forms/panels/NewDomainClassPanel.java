package com.accenture.assets.ui.forms.panels;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.swing.BoxLayout;
import javax.swing.DefaultCellEditor;
import javax.swing.DefaultListCellRenderer;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
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
import com.accenture.assets.exceptions.attributes.AtributoException;
import com.accenture.assets.exceptions.classes.ClaseException;
import com.accenture.assets.ui.actions.CloseNewClassPanelAction;
import com.accenture.assets.ui.forms.MainFrame;
import com.accenture.assets.ui.forms.table.ButtonRenderer;
import com.accenture.assets.ui.forms.table.JTableButtonModel;
import com.accenture.assets.ui.forms.table.NewDomainClassAttributeTable;
import com.accenture.assets.utils.TypeConverter;
import com.accenture.assets.utils.TypeListConverter;
import com.accenture.assets.validators.ValidadorObjetos;

/**
 * 
 * @author santiago.a.gonzalez
 * 
 */
public class NewDomainClassPanel
	extends JPanel {

	/**	 */
	private static final long serialVersionUID = 1L;

	private static final Dimension INPUT_DIMENSION = new Dimension(300, 20);

	private static final Dimension LABEL_DIMENSION = new Dimension(100, 20);

	private static final Dimension LIST_DIMENSION = new Dimension(200, 80);

	private static final Dimension BUTTON_DIMENSION = new Dimension(100, 20);

	private static final Dimension THIRD_ELEMENT_DIMENSION = new Dimension(40,20);
	
	private static final Dimension DINAMIC_LABEL_ELEMENT_DIMENSION = new Dimension(200,20);
	
	private static final Dimension SMALL_LABEL_ELEMENT_DIMENSION = new Dimension(22,20);

	// CLASS

	private JLabel classNameLabel;

	private JTextField classNameInput;

	private JLabel classLeyend;

	// nn
	private JLabel classParent;

	private JLabel classParentInfo;

	// EXTENDS

	private JLabel extendsLabel;

	private JComboBox extendsCombo;

	// // IMPLEMENTS
	// private JLabel implementsLabel;
	//
	// private JComboBox implementsCombo;
	//
	// private JList interfacesList;
	//
	// private DefaultListModel interfacesListModel;
	//
	// private JScrollPane interfacesListScroll;
	//
	// private JButton addInterfaceButton;
	//
	// private JButton removeInterfaceButton;

	// ATRIBUTTES
	private JButton addAttributeButton;

	// Marcelo-INICIO
	private JButton removeOneAttributeButton;

	// Marcelo-FIN
	private JButton removeAttributeButton;

	private JButton backButton;

	private JTable attributesTable;

	private JScrollPane attributesTableScroll;

	private NewDomainClassAttributeTable attributesTableModel;

	// Bottom Buttons

	private JButton cancelButton;

	private JButton createButton;

	// PANELS

	/** Panel de clase */
	private JPanel classPanel;

	/** Panel de clase padre */
	private JPanel extendsPanel;

	/** Panel de interfaz a implementar */
	private JPanel implementsPanel;

	/** Panel de lista de interfaces a implementar */
	private JPanel interfacesListPanel;

	/** Panel para boton de agregar atributo */
	private JPanel addAtributtePanel;

	/** Panel para tabla de atributos */
	private JPanel atributtesTablePanel;

	/** Panel para boton de eliminar atributos seleccionados en la tabla */
	private JPanel removeAtributtesPanel;

	/** Panal para botones de abajo */
	private JPanel bottomButtonsPanel;

	/** Panel final a agregar al formulario */
	private JPanel finalPanel;

	// OWNER

	/** Frame que lo contiene */
	private JFrame owner;

	// Marcelo-INICIO
	private TiposLlamadaVentana tipoLlamada;

	// private String paquete;
	private ClassElement claseActual;

	public TiposLlamadaVentana getTipoLlamada() {

		return tipoLlamada;
	}

	/**
	 * @return the claseActual
	 */
	public ClassElement getClaseActual() {

		return claseActual;
	}

	/**
	 * @param claseActual the claseActual to set
	 */
	public void setClaseActual(ClassElement claseActual) {

		this.claseActual = claseActual;
	}

	private List<Attribute> listaAtributos;
	private List<Attribute> fakeList;

	// Se agrega una variable con el nombre de la clase a modificar, ya que si
	// el usuario cambia el nombre de la clase
	// se debe poder encontrarla para eliminarla y agregar la nueva
	private String nombreClaseAEliminar;

	// Marcelo-FIN
	public NewDomainClassPanel(JFrame owner, String clase,
								TiposLlamadaVentana llamada) {
		
		super();
		// Fran I
		// addActionsListeners();
		this.owner = owner;
		// Fran F
		// Marcelo-INICIO
		// this.paquete = paquete;
		listaAtributos = new ArrayList<Attribute>();
		claseActual = new ClassElement();
		// Marcelo-FIN
		init();
		// Marcelo-INICIO
		inicializarSegunLlamada(llamada, clase);
		// Marcelo-FIN
		
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
	private JLabel getClassNameLabel() {

		if (classNameLabel == null) {
			classNameLabel = new JLabel("Class name:");
			classNameLabel.setPreferredSize(LABEL_DIMENSION);
		}
		return classNameLabel;
	}

	/**
	 * 
	 * @return
	 */
	private JTextField getClassNameInput() {

		if (classNameInput == null) {
			classNameInput = new JTextField();
			classNameInput.setPreferredSize(INPUT_DIMENSION);
		}

		return classNameInput;
	}

	/**
	 * 
	 * @return
	 */
	private JLabel getClassLeyend() {

		if (classLeyend == null) {
			classLeyend = new JLabel(".java");
			classLeyend.setPreferredSize(THIRD_ELEMENT_DIMENSION);
		}
		return classLeyend;
	}

	// nn
	public String getClassNameInputText() {

		return classNameInput.getText();
	}

	/**
	 * 
	 * @return
	 */
	private JLabel getClassParent() {

		if (classParent == null) {
			classParent = new JLabel("Ref:");
			classParent.setPreferredSize(SMALL_LABEL_ELEMENT_DIMENSION);
		}
		classParent.setVisible(false);
		return classParent;
	}

	private JLabel getClassParentInfo() {

		if (classParentInfo == null) {
			classParentInfo = new JLabel();
			classParentInfo.setPreferredSize(DINAMIC_LABEL_ELEMENT_DIMENSION);
			classParentInfo.setText("");
		}
		classParentInfo.setVisible(false);
		return classParentInfo;
	}

	/**
	 * 
	 * @return
	 */
	private JLabel getExtendsLabel() {

		if (extendsLabel == null) {
			extendsLabel = new JLabel("Extends:");
			extendsLabel.setPreferredSize(LABEL_DIMENSION);
		}
		return extendsLabel;
	}

	/**
	 * 
	 * @return
	 */
	private JComboBox getExtendsCombo() {
		
		if (extendsCombo == null) {
			extendsCombo = new JComboBox();
			extendsCombo.setPreferredSize(INPUT_DIMENSION);
		}
		return extendsCombo;
	}

	/**
	 * 
	 * @return
	 */
	// private JLabel getImplementsLabel() {
	// if (implementsLabel == null) {
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
	// private JComboBox getImplementsCombo() {
	// if (implementsCombo == null) {
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
	// private JButton getAddInterfaceButton() {
	// if (addInterfaceButton == null) {
	// addInterfaceButton = new JButton("+");
	// addInterfaceButton.setPreferredSize(THIRD_ELEMENT_DIMENSION);
	// addInterfaceButton
	// .addActionListener(new ImplementsInterfaceActionListener(
	// getImplementsCombo(), getInterfacesListModel()));
	// }
	// return addInterfaceButton;
	// }
	//
	// /**
	// *
	// * @return
	// */
	// private JList getinterfacesList() {
	// if (interfacesList == null) {
	// interfacesList = new JList(getInterfacesListModel());
	// }
	// return interfacesList;
	// }
	//
	// /**
	// *
	// * @return
	// */
	// private DefaultListModel getInterfacesListModel() {
	// if (interfacesListModel == null) {
	// this.interfacesListModel = new DefaultListModel();
	// }
	// return interfacesListModel;
	// }
	//
	// /**
	// *
	// * @return
	// */
	// private JScrollPane getInterfacesListScroll() {
	// if (interfacesListScroll == null) {
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
	// private JButton getRemoveInterfaceButton() {
	// if (removeInterfaceButton == null) {
	// removeInterfaceButton = new JButton("-");
	// removeInterfaceButton.setAlignmentY(TOP_ALIGNMENT);
	// removeInterfaceButton
	// .addActionListener(new RemoveInterfaceImlementationActionListener(
	// getinterfacesList(), getInterfacesListModel()));
	// }
	// return removeInterfaceButton;
	// }

	/**
	 * 
	 * @return
	 */
	private JButton getAddAtrubutteButton() {

		if (addAttributeButton == null) {
			addAttributeButton = new JButton("Add");
			addAttributeButton.setPreferredSize(BUTTON_DIMENSION);
			// addAtributteButton.addActionListener(new
			// AddAtributteActionListener(owner,(NewDomainClassAttributeTable)
			// getAtributteTableModel()));
			addAttributeButton.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {

					addRowToTable();

				}
			});

		}
		return addAttributeButton;
	}

	// Marcelo-INICIO
	private JButton getRemoveOneAtrubutteButton() {

		if (removeOneAttributeButton == null) {
			removeOneAttributeButton = new JButton("Remove");
			removeOneAttributeButton.setPreferredSize(BUTTON_DIMENSION);
			// addAtributteButton.addActionListener(new
			// AddAtributteActionListener(owner,(NewDomainClassAttributeTable)
			// getAtributteTableModel()));
			removeOneAttributeButton.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {

					fakeList.remove(attributesTable.getSelectedRow());
					attributesTableScroll.getViewport().removeAll();
					attributesTable = null;
					attributesTableScroll.getViewport()
						.add(getAtributteTable(false));
				}
			});

		}
		return removeOneAttributeButton;
	}

	// Marcelo-FIN
	/**
	 * 
	 * @return
	 */
	private JButton getRemoveAtributteButton() {

		if (removeAttributeButton == null) {
			removeAttributeButton = new JButton("Clean");

			removeAttributeButton.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {

					// Marcelo-INICIO
					org.jdesktop.swingbinding.JTableBinding jTableBinding =
						org.jdesktop.swingbinding.SwingBindings
							.createJTableBinding(
								org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE,
								getAtributosList(false), attributesTable);

					org.jdesktop.swingbinding.JTableBinding.ColumnBinding columnBinding =
						jTableBinding
							.addColumnBinding(org.jdesktop.beansbinding.ELProperty
								.create("${name}"));
					columnBinding.setColumnName("Nombree");
					columnBinding.setColumnClass(String.class);

					bindingGroup.addBinding(jTableBinding);
					jTableBinding.bind();

					jScrollPane1.setViewportView(attributesTable);
					attributesTable.getColumnModel().getColumn(0)
						.setHeaderValue("atributos.title0");

					borrarAtributos();

					// Marcelo-FIN
				}
			});
		}
		return removeAttributeButton;
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

	NewDomainClassPanel this1 = this;
	/**
	 * 
	 * @return
	 */
	private JTable getAtributteTable(boolean inicializar) {

		if (attributesTable == null) {
			bindingGroup = new BindingGroup();
			jScrollPane1 = new JScrollPane();
			
			JComboBox typeCmbbox = new JComboBox();
			
			attributesTable = new JTable();
			// atributtesTable = new JTable(getAtributteTableModel());
//			attributesTable.addMouseListener(new ModifyAtributteMouseListener(
//				owner, this, attributesTable));

			attributesTable.setDragEnabled(false);
			attributesTable.getTableHeader().setReorderingAllowed(false);

			attributesTable.setAutoscrolls(true);
			attributesTable.setName("atributosTable"); // NOI18N

			attributesTable.setAutoscrolls(true);
			attributesTable.setName("atributosTable"); // NOI18N
			

			jTableBinding =
				SwingBindings.createJTableBinding(
					AutoBinding.UpdateStrategy.READ_WRITE, getAtributosList(inicializar),
					attributesTable);

			// // Nombre del atributo
			// private String name;
			// // Tipo de clase del atributo
			// private Type type;
			// // Indica si es ID de la clase que lo contiene
			// private Boolean id;
			// // Indica si es mostrable en el detalle
			// private Boolean detail;
			// // Indica si se utilizará como criterio de busqueda
			// private Boolean search;
			// // Indica que el campo se mostrará en la grilla
			// private Boolean grid;
			// // Indica que el campo se puede crear
			// private Boolean create;
			// // Indica que el campo se puede modificar
			// private Boolean modify;
			// // Indica que el campo se puede eliminar
			// private Boolean delete;

			// Columna -> Nombre
			JTableBinding.ColumnBinding columnBinding;			
			columnBinding =
				jTableBinding.addColumnBinding(ELProperty.create("${name}"));
			columnBinding.setColumnName("Name");
			columnBinding.setColumnClass(String.class);

			// Columna -> Id
			columnBinding =
				jTableBinding
					.addColumnBinding(org.jdesktop.beansbinding.ELProperty
						.create("${multi}"));
			columnBinding.setColumnName("Multi");
//			if((MainFrame.getInstancia().getProject().getMappTypesJava())!=null&&!(MainFrame.getInstancia().getProject().getMappTypesJava().containsKey("C1"))) {
			columnBinding.setColumnClass(Boolean.class);
//			}

			// Columna -> Tipos
			columnBinding =
				jTableBinding
					.addColumnBinding(org.jdesktop.beansbinding.ELProperty
						.create("${type}"));
			columnBinding.setColumnName("Type");
			columnBinding.setColumnClass(Type.class);
			columnBinding.setConverter(new TypeConverter());

			// Columna -> Id
			columnBinding =
				jTableBinding
					.addColumnBinding(org.jdesktop.beansbinding.ELProperty
						.create("${id}"));
			columnBinding.setColumnName("Id");
			columnBinding.setColumnClass(Boolean.class);
			// Marcelo-INICIO
			columnBinding.setSourceNullValue(false);
			// Marcelo-FIN
			// Columna -> Detalle
			columnBinding =
				jTableBinding
					.addColumnBinding(org.jdesktop.beansbinding.ELProperty
						.create("${detail}"));
			columnBinding.setColumnName("Detail");
			columnBinding.setColumnClass(Boolean.class);
			// Marcelo-INICIO
			columnBinding.setSourceNullValue(false);
			// Marcelo-FIN
			// Columna -> Search
			columnBinding =
				jTableBinding
					.addColumnBinding(org.jdesktop.beansbinding.ELProperty
						.create("${search}"));
			columnBinding.setColumnName("Search");
			columnBinding.setColumnClass(Boolean.class);
			// Marcelo-INICIO
			columnBinding.setSourceNullValue(false);
			// Marcelo-FIN
			// Columna -> grid
			columnBinding =
				jTableBinding
					.addColumnBinding(org.jdesktop.beansbinding.ELProperty
						.create("${grid}"));
			columnBinding.setColumnName("Grid");
			columnBinding.setColumnClass(Boolean.class);
			// Marcelo-INICIO
			columnBinding.setSourceNullValue(false);
			// Marcelo-FIN
			// Columna -> create
			columnBinding =
				jTableBinding
					.addColumnBinding(org.jdesktop.beansbinding.ELProperty
						.create("${create}"));
			columnBinding.setColumnName("Create");
			columnBinding.setColumnClass(Boolean.class);
			// Marcelo-INICIO
			columnBinding.setSourceNullValue(false);
			// Marcelo-FIN
			// Columna -> modify
			columnBinding =
				jTableBinding
					.addColumnBinding(org.jdesktop.beansbinding.ELProperty
						.create("${modify}"));
			columnBinding.setColumnName("Modify");
			columnBinding.setColumnClass(Boolean.class);
			// Marcelo-INICIO
			columnBinding.setSourceNullValue(false);
			// Marcelo-FIN
			// Columna -> delete
			columnBinding =
				jTableBinding
					.addColumnBinding(org.jdesktop.beansbinding.ELProperty
						.create("${delete}"));
			columnBinding.setColumnName("Delete");
			columnBinding.setColumnClass(Boolean.class);
			// Marcelo-INICIO
			columnBinding.setSourceNullValue(false);

			// nn
//			columnBinding =
//				jTableBinding
//					.addColumnBinding(org.jdesktop.beansbinding.ELProperty
//						.create("${modificar}"));
//			columnBinding.setColumnName("Padre");
//			 columnBinding.setColumnClass(Boolean.class);
//			// Marcelo-INICIO
//			 columnBinding.setSourceNullValue(false);

//			columnBinding.setColumnClass(String.class);
//			columnBinding.setConverter(new TypeConverter());

			// Marcelo-FIN

			bindingGroup.addBinding(jTableBinding);
			jTableBinding.bind();

			jScrollPane1.setName("jScrollPane1"); // NOI18N
			jScrollPane1.setViewportView(attributesTable);

			attributesTable.getColumnModel().getColumn(0)
				.setHeaderValue("Name");
			attributesTable.getColumnModel().getColumn(1)
				.setHeaderValue("Multi");
			attributesTable.getColumnModel().getColumn(2)
				.setHeaderValue("Type"); // NOI18N
			attributesTable.getColumnModel().getColumn(2)
				.setCellEditor(new DefaultCellEditor(typeCmbbox));
			attributesTable.getColumnModel().getColumn(2).setResizable(true);
			attributesTable.getColumnModel().getColumn(3).setHeaderValue("Id");
			attributesTable.getColumnModel().getColumn(4)
				.setHeaderValue("Detail");
			attributesTable.getColumnModel().getColumn(5)
				.setHeaderValue("Search");
			attributesTable.getColumnModel().getColumn(6)
				.setHeaderValue("Grid");
			attributesTable.getColumnModel().getColumn(7)
				.setHeaderValue("Create");
			attributesTable.getColumnModel().getColumn(8)
				.setHeaderValue("Modify");
			attributesTable.getColumnModel().getColumn(9)
				.setHeaderValue("Delete");
//			attributesTable.getColumnModel().getColumn(10)
//				.setHeaderValue("Edit");
			
			// ComoBox de tipos
			// Marcelo-INICIO
			// Se modifica la forma de obtención para poder agregar las clases
			// del Proyecto como tipo de dato
			// Map<String, Type> typesMap = ((Features) ContextHelper
			// .getBean(ContextHelper.FEATURES)).getTypes();
			MainFrame.getInstancia().getProject().setearTypes();

			Map<String, Type> typesMap =
				MainFrame.getInstancia().getProject().getTypes();

			// Marcelo-FIN
			ArrayList<Type> types = new ArrayList<Type>();
			types.addAll(typesMap.values());

			// Marcelo-Inicio
			// // List<ClassElement> clases = ((MainFrame) owner).getProject()
			// // .getClasses();
			// if (clases != null) {
			// Collections.sort(clases);
			// for (ClassElement clElement : clases) {
			// Type typeClase = new Type();
			// typeClase.setName(clElement.getName());
			// types.add(typeClase);
			// }
			// }
			// Marcelo-Fin

			typeCmbbox.setName("typeJComboBox");
			typeCmbbox.setRenderer(new DefaultListCellRenderer() {

				@Override
				public Component getListCellRendererComponent(	JList list,
																Object value,
																int index,
																boolean isSelected,
																boolean cellHasFocus) {

					if (value instanceof Type) {
						Type type = (Type) value;
						setText(type.getName());
					}
					// Marcelo-INICIO
					// if (isSelected) {
					// setBackground(list.getSelectionBackground());
					// setForeground(list.getSelectionForeground());
					// if (-1 < index) {
					// Type type = MainFrame.getInstancia().getProject()
					// .getType((String) value);
					// list.setToolTipText(type.getName());
					// }
					// } else {
					// setBackground(list.getBackground());
					// setForeground(list.getForeground());
					// }
					// Marcelo-FIN
					return super.getListCellRendererComponent(list, value,
						index, isSelected, cellHasFocus);
				}
			});

			JComboBoxBinding<Type, List<Type>, JComboBox> jComboBoxBinding =
				SwingBindings.createJComboBoxBinding(
					AutoBinding.UpdateStrategy.READ_WRITE, types, typeCmbbox);

			jComboBoxBinding.setConverter(new TypeListConverter());
			
			
			bindingGroup.addBinding(jComboBoxBinding);
			jComboBoxBinding.bind();
			
//			attributesTable.getColumnModel().getColumn(10).setCellRenderer(
//				new ButtonRenderer());
//			attributesTable.getColumnModel().getColumn(10)
//				.setCellEditor(new JTableButtonModel(new JCheckBox(),owner, this1, attributesTable));								
			

		}
		return attributesTable;
	}

	public List<Attribute> getAtributosList(boolean inicializar) {
	
		if (inicializar) {
			fakeList = new ArrayList<Attribute>();		
	
			for (Attribute at:listaAtributos) {
				Attribute a = new Attribute();
				a.setCreate(at.getCreate());
				a.setDelete(at.getDelete());
				a.setDetail(at.getDetail());
				a.setGenericTypeValue(at.getGenericTypeValue());
				a.setGrid(at.getGrid());
				a.setId(at.getId());
				a.setIdCustomTypeValue(at.getIdCustomTypeValue());
//				a.setModificar(at.getModificar());
				a.setModify(at.getModify());
				a.setMulti(at.getMulti());
				a.setName(at.getName());
				a.setSearch(at.getSearch());
				a.setType(at.getType());
				a.setViewFieldCustomTypeValue(at.getViewFieldCustomTypeValue());
				fakeList.add(a);
			}
		}
		
		return fakeList;
	}

	/**
	 * 
	 * @return
	 */
	private JScrollPane getAtributteTableScroll() {

		if (attributesTableScroll == null) {
			attributesTableScroll = new JScrollPane();
			attributesTableScroll.getViewport().add(getAtributteTable(true));
		}
		return attributesTableScroll;
	}

	private void addRowToTable() {

		Attribute newAttribute = new Attribute();
		// Marcelo-INICIO
		// ((ClassElement)
		// ContextHelper.getBean(ContextHelper.CLASS_ELEMENT_TEMP))
		// .getAttributes().add(newAttribute);
		fakeList.add(newAttribute);
		// Marcelo-FIN
		attributesTableScroll.getViewport().removeAll();
		attributesTable = null;
		attributesTableScroll.getViewport().add(getAtributteTable(false));
	}

	/**
	 * 
	 * @return
	 */
	private DefaultTableModel getAtributteTableModel() {

		if (attributesTableModel == null) {
			attributesTableModel = new NewDomainClassAttributeTable();
		}
		return attributesTableModel;
	}

	// Fran I
	// /**
	// *
	// * @return
	// */
	// private JButton getCancelButton(){
	// if(cancelButton==null){
	// cancelButton = new JButton("Cancelar");
	// cancelButton.setPreferredSize(BUTTON_DIMENSION);
	// }
	// return cancelButton;
	// }
	// Fran F
	/**
	 * 
	 * @return
	 */
	private JButton getCancelButton() {

		if (cancelButton == null) {
			cancelButton = new JButton("Cancel");
			cancelButton.setPreferredSize(BUTTON_DIMENSION);
			cancelButton.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {

					// Fran I
					// owner.dispose();
					((MainFrame) owner).cleanPanelRight();
					// Fran F
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
			createButton = new JButton("Save");
			createButton.setPreferredSize(BUTTON_DIMENSION);
			// Marcelo-INICIO
			createButton.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					
//					if(MainFrame.getInstancia().getProject().haveSelectedItems(claseActual.getName())){
//						JOptionPane.showMessageDialog(MainFrame.getInstancia(), "El atributo [" + claseActual.getName() + "] No puede tener seleccionado ninguna columna");
//					} else {
					
						List <Attribute> attribsDeAttribs = new ArrayList(claseActual.getAttributesOfAttributes());
						claseActual = new ClassElement();
						claseActual.setName(classNameInput.getText());
						if (tipoLlamada.equals(TiposLlamadaVentana.ALTA)) {
							try {
//								validarSeleccionadosClase();
								validarNuevaClase();
								crearNuevaClase();
							} catch (ClaseException ex) {
								mensajeError(ex);
							} catch (AtributoException ex) {
								mensajeError(ex);
							}
						} else if (tipoLlamada
							.equals(TiposLlamadaVentana.MODIFICACION)) {
							modificarClase(attribsDeAttribs);
						}					
					}
//				}
			});
			// Marceclo-FIN
		}
		createButton.setName("Save");
		return createButton;
	}

	/**
	 * 
	 * @return
	 */
	private JPanel getClassPanel() {

		if (classPanel == null) {
			classPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
			classPanel.add(getClassNameLabel());
			classPanel.add(getClassNameInput());
			classPanel.add(getClassLeyend());
		}
		return classPanel;
	}

	/**
	 * 
	 * @return
	 */
	private JPanel getExtendsPanel() {

		if (extendsPanel == null) {
			extendsPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
			extendsPanel.add(getExtendsLabel());
			extendsPanel.add(getExtendsCombo());
		}
		return extendsPanel;
	}

	/**
	 * 
	 * @return
	 */
	// private JPanel getImplementsPanel() {
	// if (implementsPanel == null) {
	// implementsPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
	// implementsPanel.add(getImplementsLabel());
	// implementsPanel.add(getImplementsCombo());
	// implementsPanel.add(getAddInterfaceButton());
	// }
	// return implementsPanel;
	// }
	//
	// /**
	// *
	// * @return
	// */
	// private JPanel getInterfacesListPanel() {
	// if (interfacesListPanel == null) {
	// interfacesListPanel = new JPanel();
	// interfacesListPanel.add(getInterfacesListScroll());
	// interfacesListPanel.add(getRemoveInterfaceButton());
	// }
	// return interfacesListPanel;
	// }

	private JPanel getAddAtributtePanel() {

		if (addAtributtePanel == null) {
			addAtributtePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
			addAtributtePanel.add(getAddAtrubutteButton());
						
			// Marcelo-INICIO
			addAtributtePanel.add(getRemoveOneAtrubutteButton());
			addAtributtePanel.add(getRemoveAtributteButton());
			// Marcelo-FIN
			// nn
			addAtributtePanel.add(getClassParent());	
			addAtributtePanel.add(getClassParentInfo());
			JButton back = getBackButton();
			back.setVisible(false);
			addAtributtePanel.add(back);	
			
		}
		return addAtributtePanel;
	}

	/**
	 * 
	 * @return
	 */
	private JPanel getAtributteTablePanel() {

		if (atributtesTablePanel == null) {
			atributtesTablePanel = new JPanel();
			atributtesTablePanel.add(getAtributteTableScroll());
		}
		return atributtesTablePanel;
	}

	/**
	 * 
	 * @return
	 */
	private JPanel getRemoveAtributtesPanel() {

		if (removeAtributtesPanel == null) {
			removeAtributtesPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));			
		}
		return removeAtributtesPanel;
	}

	private JButton getBackButton() {

		if (backButton == null) {
			backButton = new JButton("Volver");
//			backButton.setEnabled(false);

			backButton.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
//					claseActual.setAttributesOfAttributes(new ArrayList<Attribute>());
					MainFrame padre = ((MainFrame) owner);
					Iterator<Attribute> it = getTableAttributes().iterator();
					int a = 0;
					while (it.hasNext()) {
						Attribute attrib = it.next();
						attrib.setName(classParentInfo.getText() + "."
							+ attrib.getName());
						claseActual.addAttributeOfAttribute(attrib);
						a++;
					}
					padre.getPila().pop();
					padre.crearVentanaNewClass(
						padre.getPila().pop().toString(),
						TiposLlamadaVentana.MODIFICACION);

					// nn
					classParentInfo.setText("");
					classParent.setVisible(false);
					classParentInfo.setVisible(false);					
					setViewInception(true);
				}
			});
		}
		return backButton;
	}

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
			finalPanel.add(getClassPanel());
			finalPanel.add(getExtendsPanel());
			// finalPanel.add(getImplementsPanel());
			// finalPanel.add(getInterfacesListPanel());
			finalPanel.add(getAddAtributtePanel());
			finalPanel.add(getAtributteTablePanel());
			finalPanel.add(getRemoveAtributtesPanel());
			finalPanel.add(getBottomButtonsPanel());
		}
		return finalPanel;
	}

	private void crearNuevaClaseEditada(List<Attribute> attribsDeAttribs) {

		if (extendsCombo.getSelectedIndex() != -1
			&& extendsCombo.getSelectedIndex() != 0) {

			claseActual.setExtend(MainFrame.getInstancia().getProject()
				.getClassByName((String) extendsCombo.getSelectedItem()));
		}
		Iterator<Attribute> it = getTableAttributes().iterator();
		while (it.hasNext()) {
			Attribute attrib = it.next();
			claseActual.addAttribute(attrib);
		}
		it = attribsDeAttribs.iterator();		
		while (it.hasNext()) {
			Attribute attrib = it.next();
			claseActual.addAttributeOfAttribute(attrib);
		}		
		MainFrame.getInstancia().getProject().addClasses(claseActual);
		MainFrame.getInstancia().agregarNodoClase(claseActual);

//		inicializarValores();
	}	
	
	// Marcelo-INICIO
	private void crearNuevaClase() {

		if (extendsCombo.getSelectedIndex() != -1
			&& extendsCombo.getSelectedIndex() != 0) {

			claseActual.setExtend(MainFrame.getInstancia().getProject()
				.getClassByName((String) extendsCombo.getSelectedItem()));
		}
		Iterator<Attribute> it = getTableAttributes().iterator();
		while (it.hasNext()) {
			Attribute attrib = it.next();
			claseActual.addAttribute(attrib);
		}
		MainFrame.getInstancia().getProject().addClasses(claseActual);
		MainFrame.getInstancia().agregarNodoClase(claseActual);

		inicializarValores();
	}

	private void modificarClase(List attribsDeAttribs) {
		try {
//			validarSeleccionadosClase();
			validarNuevaClase();
			elimimnarClase();
			crearNuevaClaseEditada(attribsDeAttribs);
		} catch (ClaseException e) {
			mensajeError(e);
		} catch (AtributoException e) {
			mensajeError(e);
		}

	}

	private void validarNuevaClase() throws ClaseException, AtributoException {

		ValidadorObjetos.validarModificacionClase(claseActual);
		Iterator<Attribute> it = getTableAttributes().iterator();
		while (it.hasNext()) {
			Attribute attrib = it.next();
			ValidadorObjetos.validarAtributo(attrib, getTableAttributes());
		}
	}
	
	private void elimimnarClase() {

		ClassElement claseAEliminar = new ClassElement();
		claseAEliminar.setName(nombreClaseAEliminar);
		MainFrame.getInstancia().getProject().removeClasses(claseAEliminar);
		MainFrame.getInstancia().removerNodoClase(claseAEliminar);
	}

	private List<Attribute> getTableAttributes() {

		List<Attribute> atributos = new ArrayList<Attribute>();
		for (int i = 0; i < attributesTable.getRowCount(); i++) {
			Attribute at = new Attribute();

			at.setName((String) attributesTable.getValueAt(i, 0));

			at.setMulti((Boolean) attributesTable.getValueAt(i, 1));

			Type tipo = new Type();
			tipo.setName((String) attributesTable.getValueAt(i, 2));
			at.setType(tipo);

			at.setId((Boolean) attributesTable.getValueAt(i, 3));
			at.setDetail((Boolean) attributesTable.getValueAt(i, 4));
			at.setSearch((Boolean) attributesTable.getValueAt(i, 5));
			at.setGrid((Boolean) attributesTable.getValueAt(i, 6));
			at.setCreate((Boolean) attributesTable.getValueAt(i, 7));
			at.setModify((Boolean) attributesTable.getValueAt(i, 8));
			at.setDelete((Boolean) attributesTable.getValueAt(i, 9));
			atributos.add(at);
		}
		return atributos;
	}

	private void inicializarValores() {

		classNameInput.setText("");
		cargarComboExtends();
		// extendsCombo.setSelectedIndex(1);
		// implementsCombo.setSelectedIndex(0);
		borrarAtributos();
	}

	private void inicializarTablaAtributos() {

		// // Marcelo-INICIO
		// listaAtributos = new ArrayList<Attribute>();
		// // Marcelo-FIN
		org.jdesktop.swingbinding.JTableBinding jTableBinding =
			org.jdesktop.swingbinding.SwingBindings
				.createJTableBinding(
					org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE,
					getAtributosList(true), attributesTable);

		org.jdesktop.swingbinding.JTableBinding.ColumnBinding columnBinding =
			jTableBinding.addColumnBinding(org.jdesktop.beansbinding.ELProperty
				.create("${name}"));
		columnBinding.setColumnName("Nombree");
		columnBinding.setColumnClass(String.class);

		bindingGroup.addBinding(jTableBinding);
		jTableBinding.bind();

		jScrollPane1.setViewportView(attributesTable);
		attributesTable.getColumnModel().getColumn(0)
			.setHeaderValue("atributos.title0");

		// Marcelo-INICIO
		// ((ClassElement)
		// ContextHelper.getBean(ContextHelper.CLASS_ELEMENT_TEMP))
		// .getAttributes().removeAll(getAtributosList());
		// Marcelo-FIN
		claseActual.getAttributes().removeAll(getAtributosList(true));
	}

	private void mensajeError(Exception e) {

		JOptionPane.showMessageDialog(new JFrame(), e.getMessage(),
			"Validation", JOptionPane.ERROR_MESSAGE);
	}

	public void inicializarValores(String clase, TiposLlamadaVentana tipoLlamada) {

		inicializarValores();
		habilitarModificacion(true);
		inicializarSegunLlamada(tipoLlamada, clase);
	}

	private void borrarAtributos() {

		/*
		 * org.jdesktop.swingbinding.JTableBinding jTableBinding =
		 * org.jdesktop.swingbinding.SwingBindings .createJTableBinding(
		 * org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, getAtributosList(),
		 * atributtesTable); org.jdesktop.swingbinding.JTableBinding.ColumnBinding columnBinding =
		 * jTableBinding .addColumnBinding(org.jdesktop.beansbinding.ELProperty .create("${name}"));
		 * columnBinding.setColumnName("Nombree"); columnBinding.setColumnClass(String.class);
		 * bindingGroup.addBinding(jTableBinding); jTableBinding.bind();
		 * jScrollPane1.setViewportView(atributtesTable);
		 * atributtesTable.getColumnModel().getColumn(0) .setHeaderValue("atributos.title0");
		 */
		listaAtributos = new ArrayList<Attribute>();
		attributesTableScroll.getViewport().remove(attributesTable);
		attributesTable = null;
		attributesTableScroll.getViewport().add(getAtributteTable(false));
		this.validate();
	}

	public void inicializarSegunLlamada(TiposLlamadaVentana llamada,
										String clase) {

		tipoLlamada = llamada;

		// PackageElement
		if (tipoLlamada.equals(TiposLlamadaVentana.ALTA)) {
			inicializarAlta();
		} else if (tipoLlamada.equals(TiposLlamadaVentana.MODIFICACION)) {
			inicializarModificacion(MainFrame.getInstancia().getProject()
				.getClassByName(clase));

		} else if (tipoLlamada.equals(TiposLlamadaVentana.SOLO_LECTURA)) {
			inicializarSoloLectura(MainFrame.getInstancia().getProject()
				.getClassByName(clase));

		} else if (tipoLlamada.equals(TiposLlamadaVentana.INCEPTION)) {
			inicializarInception(MainFrame.getInstancia().getProject()
				.getClassByName(clase));
		}
	}

	private void inicializarSoloLectura(ClassElement clase) {

		claseActual = clase;
		cargarClase(claseActual);
		habilitarModificacion(false);

	}

	private void habilitarModificacion(boolean b) {

		classNameInput.setEnabled(b);
		attributesTable.setEnabled(b);
		extendsCombo.setEnabled(b);
		// implementsCombo.setEnabled(b);
		addAttributeButton.setEnabled(b);
		createButton.setEnabled(b);
		cancelButton.setEnabled(b);
		removeAttributeButton.setEnabled(b);
		removeOneAttributeButton.setEnabled(b);
	}

	// nn
	public void setViewInception(boolean b) {
		
		classNameInput.setEnabled(b);
		attributesTable.setEnabled(b);
		extendsCombo.setEnabled(b);
		addAttributeButton.setVisible(b);
		removeOneAttributeButton.setVisible(b);
		removeAttributeButton.setVisible(b);		
		createButton.setEnabled(b);
		cancelButton.setEnabled(b);
		removeAttributeButton.setEnabled(b);
		removeOneAttributeButton.setEnabled(b);
		backButton.setVisible(!b);
	}

	private void inicializarInception(ClassElement clasePasadaPorParametro) {

		// nn
		setViewInception(false);
		classNameInput.setText(claseActual.getName());
		classParentInfo.setText(claseActual.getName() + "." + clasePasadaPorParametro.getName());
		classParent.setVisible(true);
		classParentInfo.setVisible(true);
		
		inicializarTablaAtributos();
		
		/* si no tiene seteados los atributos de atributos aun, inicializamos el array */
		if (claseActual.getAttributesOfAttributes() == null	|| claseActual.getAttributesOfAttributes().size() == 0) {
			claseActual.setAttributesOfAttributes(new ArrayList<Attribute>());
		}
		boolean flagEstaLaClase = false;
		
		/* Recorremos los "atributos de atributos" de la clase actual 
		 * y chequeamos si la clase pasada por param ya fue ingresada
		 */
		Iterator<Attribute> it = claseActual.getAttributesOfAttributes().iterator();
		while (it.hasNext()) {
			Attribute actual = it.next();
			if ((actual.getName().contains(clasePasadaPorParametro.getName()))) {
				flagEstaLaClase = true;
				break;
			}
		}
		/* Si no está agregamos sus atributos a los "atributos de atributos" de la actual */
		if (flagEstaLaClase == false) {
			// lista de atributos a rellenar, copia de los atributos de la clasepasadaporparametro
			List<Attribute> attribsAAgregar = new ArrayList<Attribute>();			
 			for (Attribute aa : clasePasadaPorParametro.getAttributes()) {
				Attribute aaux = new Attribute();
				aaux.setType(aa.getType());
				aaux.setName(aa.getName());
				attribsAAgregar.add(aaux);
			}
 			// la iteramos
			Iterator<Attribute> it2 = attribsAAgregar.iterator();
			while (it2.hasNext()) {
				/* ponemos todos sus campos en false */
				Attribute atributoActual2 = it2.next();
				atributoActual2.setCreate(false);
				atributoActual2.setDelete(false);
				atributoActual2.setDetail(false);
				atributoActual2.setGrid(false);
				atributoActual2.setId(false);
				atributoActual2.setModify(false);
				atributoActual2.setMulti(false);
				atributoActual2.setSearch(false);
				/* le ponemos el nombre de la clase actual + la clase atributo + el nombre del atrib */
				atributoActual2.setName(claseActual.getName() + "."	+ clasePasadaPorParametro.getName() + "." + atributoActual2.getName());
				/* lo agregamos */
				claseActual.getAttributesOfAttributes().add(atributoActual2);
			}
		}
		/*
		 * iteramos los atributos de la clase atributo y cambiamos los nombres a la forma normal,
		 * sin la pertenencia
		 */
		it = claseActual.getAttributesOfAttributes().iterator();
		while (it.hasNext()) {
			Attribute actual = it.next();
			/*
			 * si es un atributo de la clase pasada por parámetro 
			 */
			if (actual.getName().contains(clasePasadaPorParametro.getName())) {
				/*
				 * lo agregamos a la tabla y si contiene un . solo se agrega el nombre del atributo,
				 * que está al final
				 */
//				if (actual.getName().contains(".")) {
					actual.setName(actual.getName().substring(
						actual.getName().lastIndexOf(".") + 1));
//				}
				listaAtributos.add(actual);
			}
		}
		attributesTableScroll.getViewport().removeAll();
		attributesTable = null;
		attributesTableScroll.getViewport().add(getAtributteTable(false));

	}

	private void inicializarModificacion(ClassElement clase) {

		nombreClaseAEliminar = claseActual.getName();
		claseActual = clase;
		cargarClase(claseActual);
	}

	private void cargarClase(ClassElement clase) {

		classNameInput.setText(clase.getName());
		inicializarTablaAtributos();
		cargarAtributos(clase);
		// inicializarTablaAtributos();
		// Iterator<Attribute> it = atributos.iterator();
		// while (it.hasNext()){
		// it.next().getName();
		// }
	}

	private void cargarAtributos(ClassElement clase) {

		Iterator<Attribute> it = clase.getAttributes().iterator();
		while (it.hasNext()) {
			listaAtributos.add(it.next());
		}
		attributesTableScroll.getViewport().removeAll();
		attributesTable = null;
		attributesTableScroll.getViewport().add(getAtributteTable(true));
	}

	private void inicializarAlta() {

		claseActual = new ClassElement();

	}

	private void cargarComboExtends() {

		List<ClassElement> clasesAExtender =
			MainFrame.getInstancia().getProject().getClasses();
		Collections.sort(clasesAExtender);
		extendsCombo.removeAllItems();
		extendsCombo.addItem("");
		Iterator<ClassElement> it = clasesAExtender.iterator();
		while (it.hasNext()) {
			extendsCombo.addItem(it.next().getName());
		}
		extendsCombo.setSelectedIndex(0);
	}
	// Marcelo-FIN

}
