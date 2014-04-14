package com.accenture.assets.ui.forms;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.Stack;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTree;
import javax.swing.KeyStroke;
import javax.swing.text.Position;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.MutableTreeNode;
import javax.swing.tree.TreePath;

import org.apache.tools.ant.ProjectHelper;

import com.accenture.assets.beans.Attribute;
import com.accenture.assets.beans.ClassElement;
import com.accenture.assets.beans.CustomType;
import com.accenture.assets.beans.Interface;
import com.accenture.assets.beans.Project;
import com.accenture.assets.constant.Constants;
import com.accenture.assets.enums.TiposLlamadaVentana;
import com.accenture.assets.tree.CustomRender;
import com.accenture.assets.tree.TreePopup;
import com.accenture.assets.tree.TreeUtilities;
import com.accenture.assets.ui.actions.window.MainFrameWindowListener;
import com.accenture.assets.ui.dialogs.InfoProjectConfigurationDialog;
import com.accenture.assets.ui.dialogs.NewProjectConfigurationDialog;
import com.accenture.assets.ui.dialogs.panels.NewProjectConfigurationDialogPanel;
import com.accenture.assets.ui.dialogs.panels.OpenProjectDialogPanel;
import com.accenture.assets.ui.dialogs.panels.SaveProjectDialogPanel;
import com.accenture.assets.ui.forms.panels.NewDomainClassPanel;
import com.accenture.assets.ui.forms.panels.NewInterfacePanel;
import com.accenture.assets.utils.FileUtils;
import com.accenture.assets.utils.ReadXMLFile;
import com.accenture.assets.utils.SaveBuildProperties;
import com.accenture.assets.utils.SaveJava;
import com.accenture.assets.utils.SaveXML;
import com.accenture.assets.utils.SwingUtils;

public class MainFrame extends JFrame {

	/**	*/
	private static final long serialVersionUID = 1L;
	
	private Stack <String> pila = new Stack<String>();
	
	/** */
	private static final Dimension WINDOW_SIZE = new Dimension(
			Constants.MAIN_WINDOW_WIDTH, Constants.MAIN_WINDOW_HEIGHT);

	private JPanel grilla;
	
	private JTree projectTree;

	// Menu de proyecto y opciones

	private JMenuItem newProjectMenuItem;
	private JMenuItem openProjectMenuItem;
	private JMenuItem saveProjectMenuItem;
	private JMenuItem saveProjectAsMenuItem;	
	private JMenuItem closeProjectMenuItem;
	private JMenuItem exitMenuItem;

	// Menu Herramientas y opciones

	private JMenuItem generateToolsMenuItem;
//	private JMenuItem newClassToolsMenuItem;
	private JMenuItem infoToolsMenuItem;
	// Fran - Inicio
	private JMenuItem newInterfaceMenuItem;
	// Fran - Fin

	// Componentes del formulario

	/** Content Pane */
	private JPanel contentPane;
//	private ImagePanel contentPane;

	// panel Izquierdo
	private JPanel contentPanelDer = new JPanel();

	/** New ClassElement Panel */
	private JPanel newClassPanel;

	// New Interface Panel
	private JPanel newInterfacePanel;

	// Marcelo-INICIO
	private JPanel newPackagePanel;
	// Marcelo-FIN
	// Classes Viewer Panel

	/** Lista de clases */
	private JList classesList;

	/** Scroll Bar de lista de clases */
	private JScrollPane classesListScroll;

	/** Panel visor de lista de clases */
	private JPanel classesViewerPanel;

	// Fran - Inicio
	// Variable que tiene el Proyecto.
	// private Project project = DataUtilTest.getExampleProject();
	private Project project = new Project();
	// Fran - Fin
	// Marcelo-INICIO
	private static MainFrame instancia;
	// Marcelo-FIN
	// Marcelo-PRUEBA-INICIO
	DefaultTreeModel treeModel;
	DefaultMutableTreeNode nodeClasses;

	// Marcelo-PRUEBA-FIN

	// Marcelo-INICIO
	// public MainFrame() {
	private MainFrame() {
		// Marcelo-FIN
		super();
		init();
	}

	// Marcelo-INICIO
	public static MainFrame getInstancia() {
		if (instancia == null)
			instancia = new MainFrame();
		return instancia;
	}

	// Marcelo-FIN
	/**
	 * Inicializa el formulario
	 */
	private void init() {
		setSize(WINDOW_SIZE);
		// Move the window
		setLocation(SwingUtils.getCenterPoint(this));
		setTitle("Generator Point");
		setResizable(true);
		addWindowListener(new MainFrameWindowListener());
		setJMenuBar(getMainFramenMenu());
		
		
//		contentPane = new ImagePanel(new ImageIcon(ImagePanel.class.getResource("/images/backgroundGen.png")).getImage());
//		setContentPane(contentPane);
		
		setContentPane(getContent());
		
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
//		ImagePanel panelImage = new ImagePanel(new ImageIcon(ImagePanel.class.getResource("/images/backgroundGen.png")).getImage());
//		getContentPane().add(panelImage);
		
	}

	/**
	 * Dibujamos el menu principal
	 * 
	 * @return
	 */
	private JMenuBar getMainFramenMenu() {

		// Manejador de eventos del menu
		ActionListener menuItemActionListener = new MenuItemActionListener();

		// Barra de Menu
		JMenuBar menuBar = new JMenuBar();

		// Menu de Proyecto
		JMenu projectMenu = new JMenu("Project");

		// Opcion de nuevo proyecto
		newProjectMenuItem = new JMenuItem("New Project");
		newProjectMenuItem.addActionListener(menuItemActionListener);
		projectMenu.add(newProjectMenuItem);

		// Opcion de Abrir proyecto
		openProjectMenuItem = new JMenuItem("Open existing Project");
		openProjectMenuItem.addActionListener(menuItemActionListener);
		projectMenu.add(openProjectMenuItem);

		// Opcion de Guardar proyecto
		saveProjectMenuItem = new JMenuItem("Save project...");
		saveProjectMenuItem.setEnabled(false);
		saveProjectMenuItem.setAccelerator(KeyStroke.getKeyStroke(
				KeyEvent.VK_S, ActionEvent.CTRL_MASK));
		saveProjectMenuItem.addActionListener(menuItemActionListener);
		projectMenu.add(saveProjectMenuItem);

		saveProjectAsMenuItem = new JMenuItem("Save project as...");
		saveProjectAsMenuItem.setEnabled(false);
		saveProjectAsMenuItem.setAccelerator(KeyStroke.getKeyStroke(
				KeyEvent.VK_A, ActionEvent.CTRL_MASK));
		saveProjectAsMenuItem.addActionListener(menuItemActionListener);
		projectMenu.add(saveProjectAsMenuItem);
		
		// Opcion de Cerrar proyecto
//		closeProjectMenuItem = new JMenuItem("Cerrar proyecto");
//		closeProjectMenuItem.setEnabled(false);
//		closeProjectMenuItem.addActionListener(menuItemActionListener);
//		projectMenu.add(closeProjectMenuItem);

		// Separador
		projectMenu.add(new JSeparator());

		// Opcion de Salir de la aplicacion
		exitMenuItem = new JMenuItem("Exit");
		exitMenuItem.addActionListener(menuItemActionListener);
		projectMenu.add(exitMenuItem);

		menuBar.add(projectMenu);

		// Menu de Herramientas
		JMenu toolsMenu = new JMenu("Tools");

		// Opcion de Información de proyecto
		infoToolsMenuItem = new JMenuItem("Existing project information...");
		infoToolsMenuItem.setEnabled(false);
		infoToolsMenuItem.addActionListener(menuItemActionListener);
		toolsMenu.add(infoToolsMenuItem);

		// Separador
		toolsMenu.add(new JSeparator());

		// Opcion de nueva clase de dominio
//		newClassToolsMenuItem = new JMenuItem("Nueva clase de dominio");
//		newClassToolsMenuItem.setEnabled(true);
//		newClassToolsMenuItem.setAccelerator(KeyStroke.getKeyStroke(
//				KeyEvent.VK_N, ActionEvent.CTRL_MASK));
//		newClassToolsMenuItem.addActionListener(menuItemActionListener);
//		toolsMenu.add(newClassToolsMenuItem);

		// Fran - Inicio
		// Opcion de Nueva interface
//		newInterfaceMenuItem = new JMenuItem("Nueva interface");
//		newInterfaceMenuItem.setEnabled(true);
//		newInterfaceMenuItem.setAccelerator(KeyStroke.getKeyStroke(
//				KeyEvent.VK_I, ActionEvent.CTRL_MASK));
//		newInterfaceMenuItem.addActionListener(menuItemActionListener);
//		toolsMenu.add(newInterfaceMenuItem);
		// Fran - Fin

		// Separador
		toolsMenu.add(new JSeparator());

		// Opcion de Generar Codigo
		generateToolsMenuItem = new JMenuItem("Generate code!");
		generateToolsMenuItem.setEnabled(false);
		generateToolsMenuItem.addActionListener(menuItemActionListener);
		toolsMenu.add(generateToolsMenuItem);

		menuBar.add(toolsMenu);

		return menuBar;
	}

	/**
	 * 
	 * @author pablo.m.ibarrola
	 * 
	 */

	// nn
	private boolean cancel = false;
	
	public boolean getCancel() {
		return cancel;
	}
	
	public void setCancel(boolean cancel) {
		this.cancel = cancel;
	}
	//nn
	
	private class MenuItemActionListener implements ActionListener {

		public void actionPerformed(ActionEvent e) {

			if (e.getSource() == newProjectMenuItem) {

				NewProjectConfigurationDialog dialog = new NewProjectConfigurationDialog(getMainFrameReference());
				dialog.setVisible(true);
				
				if (!getCancel()) {
					
					saveProjectMenuItem.setEnabled(true);
					saveProjectAsMenuItem.setEnabled(true);					
					infoToolsMenuItem.setEnabled(true);
					// closeProjectMenuItem.setEnabled(true);
					// newClassToolsMenuItem.setEnabled(true);
					// Fran I
					generateToolsMenuItem.setEnabled(true);
					// Marcelo-INICIO
					// setProject((Project)
					// ContextHelper.getBean(ContextHelper.PROJECT_CONFIGURATION));
					cleanPanelRight();
					refrescarArbolProyecto();
					// Marcelo-FIN
					// Fran F
				} else {
					cancel = false;
				}
			}

			if (e.getSource() == openProjectMenuItem) {
				// Fran - Inicio
				OpenProjectDialogPanel openD = new OpenProjectDialogPanel(getMainFrameReference());
				
				if(openD.getArchivo() != null){
					//nn
					project = new Project();
					
					project.setArchivo(openD.getArchivo());
					ReadXMLFile readXML = new ReadXMLFile();
					Project projectTmp = new Project();
					projectTmp = readXML.leerXML(project);
					// project = new Project();
					
					//nn
					projectTmp.setArchivo(openD.getArchivo());
					projectTmp.setLocWorkSpace(openD.getArchivo().getParent().toString() + "/" + projectTmp.getName());
					
					setProject(projectTmp);
	
					saveProjectMenuItem.setEnabled(true);
					saveProjectAsMenuItem.setEnabled(true);					
					generateToolsMenuItem.setEnabled(true);
					infoToolsMenuItem.setEnabled(true);
	//				closeProjectMenuItem.setEnabled(true);
	
					// cleanPanelRight();
					// classesViewerPanel.remove(classesListScroll);
					// classesViewerPanel.add(getClassesListScroll());
					// classesViewerPanel.repaint();
					// getMainFrameReference().validate();
					refrescarArbolProyecto();
					// Fran - Fin
				}
			}

			if (e.getSource() == saveProjectMenuItem) {
				grabaProyecto();				
			}
			if (e.getSource() == saveProjectAsMenuItem) {
				// fran I
				SaveProjectDialogPanel SaveD = new SaveProjectDialogPanel(
						getMainFrameReference());
				project.setArchivo(SaveD.getArchivo());
				grabaProyecto();
				generateToolsMenuItem.setEnabled(true);
				// fran F

			}

//			if (e.getSource() == closeProjectMenuItem) {
//				saveProjectMenuItem.setEnabled(false);
//				infoToolsMenuItem.setEnabled(false);
////				newClassToolsMenuItem.setEnabled(false);
//				generateToolsMenuItem.setEnabled(false);
//				
//				instancia = null;
//				instancia = new MainFrame();
//				init();
//			}

			if (e.getSource() == exitMenuItem) {
				//MainFrame.EXIT_ON_CLOSE;
				System.exit(0);
			}

			if (e.getSource() == generateToolsMenuItem) {

				// Fran I
				// 1 primero chequear si grabo el Proyecto
//				if ( project.getArchivo() != null) {
////					SaveProjectDialogPanel SaveD = new SaveProjectDialogPanel(
////							getMainFrameReference());
////					project.setArchivo(SaveD.getArchivo());
//					grabaProyecto();
//				}

				// 1.2 Definimos el directorio donde guardar los JAVA

//				if (null == project.getLocWorkSpace()) {
//					SaveProjectDialogPanel SaveD = new SaveProjectDialogPanel(
//							getMainFrameReference(), "");
//					if (null != SaveD.getArchivo()) {
//						project.setLocWorkSpace(SaveD.getArchivo().toString());
//					}
//				}
				// por si apreto el boton CANCEL
				if (null != project.getLocWorkSpace() && null != project.getArchivo()) {
					grabaProyecto();

//					// 2 llamar a SaveJava enviando el projecto
//					SaveJava saveJ = new SaveJava();
//					saveJ.generateJava(project);
//					
//					//3 Genero el archivo de configuracion build.properties
//					SaveBuildProperties saveBuildP = new SaveBuildProperties();
//					saveBuildP.generaBuildProperties(project);
					
					// 4 llamar la tarea ANT para generar el codigo

					//TEST
					
					//File buildFile = new File(project.getArchivo().getParent() + "/build.xml");
					//Produccion
					//File buildFile = new File("build.xml");
					
//					File buildFile = new File("build.xml");
//					org.apache.tools.ant.Project p = new org.apache.tools.ant.Project();
//					p.setUserProperty("ant.file",buildFile.getAbsolutePath());
//					p.init();
//					ProjectHelper helper = ProjectHelper.getProjectHelper();
//					p.addReference("ant.projectHelper", helper);
//					helper.parse(p, buildFile);
//					p.executeTarget(p.getDefaultTarget());
//					JOptionPane.showMessageDialog(getInstancia(), "Codigo generado en: [" + project.getArchivo().getParent() + "]");

					//nn
//					final JDialog dlg = new JDialog(getInstancia(), "Progress Dialog", true);
//				    final JProgressBar dpb = new JProgressBar();
//				    
//				    dpb.setIndeterminate(true);
//				    dlg.add(BorderLayout.CENTER, dpb);
//				    dlg.add(BorderLayout.NORTH, new JLabel("Progress..."));
//				    dlg.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
//				    dlg.setSize(300, 75);
//				    dlg.setLocationRelativeTo(getInstancia());
//			
//				    
////				    SwingWorker.
////					    SwingUtilities.invokeLater(new Runnable() {
					    Thread t = new Thread(new Runnable() {
							public void run() {	
								
								FileUtils.resetDirectory(new File(project.getLocWorkSpace()));
		
								SaveJava saveJ = new SaveJava();
								saveJ.generateJava(project);

								SaveBuildProperties saveBuildP = new SaveBuildProperties();
								saveBuildP.generaBuildProperties(project);
								
								File buildFile = new File("build.xml");
								org.apache.tools.ant.Project p = new org.apache.tools.ant.Project();
								p.setUserProperty("ant.file",buildFile.getAbsolutePath());
								p.init();
								ProjectHelper helper = ProjectHelper.getProjectHelper();
								p.addReference("ant.projectHelper", helper);
								helper.parse(p, buildFile);
								p.executeTarget(p.getDefaultTarget());
								
//								dlg.setVisible(true);
								JOptionPane.showMessageDialog(getInstancia(), "Project was created in: [" + project.getArchivo().getParent() + "\\webapp\\]");

							}
						});
					    t.start();						
					    
					    //dlg.setVisible(false);
//						notify();
//						t.interrupted();
					
					
					//nn
					
				}
				// Fran F

			}

			// if (e.getSource() == newClassToolsMenuItem) {
			// Marcelo-Inicio- Pongo en un método el crear ventana de crear
			// clases
			// para poder invocarlo desde fuera
			// Fran - Inicio
			// crearVentanaNewClass();
			// Fran - Fin
			// Marcelo-Fin
			// }
			// Marcelo-Inicio- Pongo en un método el crear ventana de crear
			// clases
			// para poder invocarlo desde fuera
			// Fran - Inicio
//			if (e.getSource() == newInterfaceMenuItem) {
//				crearVentanaNewInterfaz();
//			}
			// Fran - Fin
			// Marcelo-Fin
			if (e.getSource() == infoToolsMenuItem) {
				InfoProjectConfigurationDialog dialog = new InfoProjectConfigurationDialog(
						getMainFrameReference());
				dialog.setVisible(true);
			}

		}
	}

	/**
	 * limpa y re pinta el Panel Contenedor de la IZQ.
	 */

	// Fran - Inicio
	public void cleanPanelRight() {
		contentPanelDer.removeAll();
		contentPanelDer.repaint();
	}

	// Fran - Fin

	/**
	 * 
	 * @return
	 */
	private JFrame getMainFrameReference() {
		return this;
	}

	/**
	 * 
	 * @return
	 */
	private JPanel getContent() {
		if (contentPane == null) {
			contentPane = new JPanel();		
			contentPane.setLayout(new BorderLayout());
			contentPane.add(BorderLayout.CENTER, contentPanelDer);
			
//			ImagePanel imagePanel = new ImagePanel(new ImageIcon(ImagePanel.class.getResource("/images/backgroundGen.png")).getImage());
//			contentPane.add(BorderLayout.EAST, imagePanel);

			
//			contentPane = new ImagePanel(new ImageIcon(ImagePanel.class.getResource("/images/backgroundGen.png")).getImage());
//			ImagePanel panelImage = new ImagePanel(new ImageIcon(ImagePanel.class.getResource("/images/backgroundGen.png")).getImage());
//			getContentPane().add(panelImage);
//			getContentPane().add();
//			contentPane.add(new ImageIcon("images/background.png").getImage());
//			ImagePanel panel = new ImagePanel(new ImageIcon("images/background.png").getImage());
//			pathButton.setIcon(new ImagePanel(NewProjectConfigurationDialogPanel.class.getResource("/images/open.png")));
			
			
			// contentPane.add(BorderLayout.EAST, getNewClassPanel());
			// contentPane.add(BorderLayout.EAST, getNewInterfacePanel());
			// Prueba!!!!!!!!Marce
			// contentPane.add(BorderLayout.WEST, getClassesViewerPanel());
			//contentPane.add(BorderLayout.WEST, getClassesViewerPanel());
			// Prueba-fin
		}
		return contentPane;
	}

	/**
	 * 
	 * @return
	 */
	// Marcelo-INICIO
	private JPanel getNewClassPanel(String clase,TiposLlamadaVentana tipoLlamada) {
		if (newClassPanel == null || !tipoLlamada.equals(TiposLlamadaVentana.INCEPTION)) {
			newClassPanel = new NewDomainClassPanel(this, clase,tipoLlamada);
			newClassPanel.setVisible(false);
		}
		((NewDomainClassPanel) newClassPanel).inicializarValores(clase,tipoLlamada);
		newClassPanel.setName("clases");
		return newClassPanel;
	}

	// Marcelo-FIN
	/**
	 * Hace visible el panel de nueva clase de dominio
	 */
	public void showNewClassPanel() {
		newClassPanel.setVisible(true);
	}

	public JPanel getNewClassPanel() {
		return newClassPanel;
	}

	// Fran - Inicio

	private JPanel getNewInterfacePanel() {
		if (newInterfacePanel == null) {
			newInterfacePanel = new NewInterfacePanel(this);
			newInterfacePanel.setVisible(false);
		}
		return newInterfacePanel;
	}

	/*
	 * Hace visible el panel de nueva Interface
	 */
	public void showNewInterface() {
		newInterfacePanel.setVisible(true);
	}
	// ReestructuracionPaquete
	// Fran - Fin
	// Marcelo-INICIO
//	private JPanel getNewPackagePanel() {
//		if (newPackagePanel == null) {
//			newPackagePanel = new NewPackagePanel(this);
//			newPackagePanel.setVisible(false);
//		}
//		((NewPackagePanel) newPackagePanel).inicializarPanel();
//		return newPackagePanel;
//	}

	/*
	 * Hace visible el panel de nueva Interface
	 */
	public void showNewPackagePanel() {
		newPackagePanel.setVisible(true);
	}

	// Marcelo-FIN
	/**
	 * Oculta el panel de nueva clase de dominio
	 */
	public void hideNewClassPanel() {
		newClassPanel.setVisible(false);
	}

	/**
	 * 
	 * @return
	 */
	private JList getClassesList() {
		if (classesList == null) {
			classesList = new JList();
		}
		return classesList;
	}

	/**
	 * 
	 * @return
	 */
	private JScrollPane getClassesListScroll() {

		if (classesListScroll == null) {
			classesListScroll = new JScrollPane();
			// Marcelo-INICIO
			classesListScroll.setPreferredSize(new Dimension(250, 200));
			// Marcelo-FIN
			// Fran I
			// Project exampleProject = DataUtilTest.getExampleProject();
			// Fran F
			// Raiz
			DefaultMutableTreeNode root = new DefaultMutableTreeNode(
					project.getName());
			// Marcelo-INICIO-PRUEBA
			// DefaultTreeModel treeModel = new DefaultTreeModel(root);
			treeModel = new DefaultTreeModel(root);
			// Marcelo-FIN-PRUEBA
			// Atributos del proyecto
			DefaultMutableTreeNode nodeGroupId = new DefaultMutableTreeNode(
					"Group Id");
			DefaultMutableTreeNode nodeArtifactId = new DefaultMutableTreeNode(
					"Artifact Id");
			DefaultMutableTreeNode nodeVersion = new DefaultMutableTreeNode(
					"Version");
			DefaultMutableTreeNode nodeTechnology = new DefaultMutableTreeNode(
					"Technology");
			// Marcelo-INICIO-PRUEBA
			// DefaultMutableTreeNode nodeClasses = new
			// DefaultMutableTreeNode("Clases");
			nodeClasses = new DefaultMutableTreeNode("Classes");
			// Marcelo-FIN-PRUEBA

			int rootLevel = 0;
			treeModel.insertNodeInto(nodeGroupId, root, rootLevel);
			treeModel.insertNodeInto(nodeArtifactId, root, ++rootLevel);
			treeModel.insertNodeInto(nodeVersion, root, ++rootLevel);
			treeModel.insertNodeInto(nodeTechnology, root, ++rootLevel);
			treeModel.insertNodeInto(nodeClasses, root, ++rootLevel);

			// Valores de los atributos del proyecto
			DefaultMutableTreeNode valueNodeGroupId = new DefaultMutableTreeNode(
					project.getGroupId());
			DefaultMutableTreeNode valueNodeArtifactId = new DefaultMutableTreeNode(
					project.getArtifactId());
			DefaultMutableTreeNode valueNodeVersion = new DefaultMutableTreeNode(
					project.getVersion());
			DefaultMutableTreeNode valueNodeTechnology = new DefaultMutableTreeNode(
					project.getTechnology());

			int secondLevel = 0;
			treeModel
					.insertNodeInto(valueNodeGroupId, nodeGroupId, secondLevel);
			treeModel.insertNodeInto(valueNodeArtifactId, nodeArtifactId,
					secondLevel);
			treeModel
					.insertNodeInto(valueNodeVersion, nodeVersion, secondLevel);
			treeModel.insertNodeInto(valueNodeTechnology, nodeTechnology,
					secondLevel);

			// Clases
			// ReestructuracionPaquete
			// // Marcelo-INICIO
			// int packagesLevel = 0;
			// Iterator<PackageElement> itPack =
			// project.getPackages().iterator();
			// while (itPack.hasNext()) {
			// PackageElement packageElement = itPack.next();
			// DefaultMutableTreeNode packageElementNode = new
			// DefaultMutableTreeNode(packageElement.getName());
			// treeModel.insertNodeInto(packageElementNode, nodeClasses,
			// packagesLevel);
			// ReestructuracionPaquete
			// // Marcelo-FIN
			int classesLevel = 0;
			// // Marcelo-INICIO
			Iterator<ClassElement> it = project.getClasses().iterator();
			// Iterator<ClassElement> it =
			// packageElement.getListClassElement().iterator();
			// // Marcelo-FIN
			while (it.hasNext()) {
				ClassElement classElement = it.next();
				// ReestructuracionPaquete
				// Marcelo-INICIO
				generateTreeChild(treeModel, classElement, nodeClasses,
						classesLevel++);
				// generateTreeChild(treeModel, classElement,
				// packageElementNode, classesLevel++);
				// // Marcelo-FIN
			}
			// Marcelo-INICIO
		}
		// Marcelo-FIN

		// private String locInputSources;
		// private String locWorkSpace;
		//
		// // Mvn parameters
		// private String name;
		// private String groupId;
		// private String artifactId;
		// private String version;
		// private String technology;
		//
		// // Model attributes
		// private List<ClassElement> classes;

		// Construccion de los datos del arbol
		// DefaultMutableTreeNode padre = new
		// DefaultMutableTreeNode("padre");
		// DefaultMutableTreeNode tio = new DefaultMutableTreeNode("tio");
		// treeModel.insertNodeInto(padre, root, 0);
		// treeModel.insertNodeInto(tio, root, 1);
		//
		// DefaultMutableTreeNode hijo = new DefaultMutableTreeNode("hijo");
		// DefaultMutableTreeNode hija = new DefaultMutableTreeNode("hija");
		// treeModel.insertNodeInto(hijo, padre, 0);
		// treeModel.insertNodeInto(hija, padre, 1);

		projectTree = new JTree(treeModel);
		// Marcelo-INICIO
		// projectTree.setPreferredSize(new Dimension(250, 200));
		projectTree.setPreferredSize(null);
		TreePopup treePopup = new TreePopup(this, projectTree);
		projectTree.setRowHeight(25);
		projectTree.setCellRenderer(new CustomRender());
		// Marcelo-FIN

		// Construccion y visualizacion de la ventana
		// JFrame v = new JFrame();
		// JScrollPane scroll = new JScrollPane(projectTree);
		// v.getContentPane().add(scroll);
		// v.pack();
		// v.setVisible(true);
		// v.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

		// classesListScroll.getViewport().add(getClassesList());
		classesListScroll.setViewportView(projectTree);
		// classesListScroll.add(projectTree);
		return classesListScroll;
	}

	/**
	 * 
	 * @param child
	 * @param parent
	 * @param index
	 */
	private void generateTreeChild(DefaultTreeModel treeModel,
			ClassElement classElement, MutableTreeNode parent, int index) {
			
		// La clase
		DefaultMutableTreeNode valueNodeClasses = new DefaultMutableTreeNode(
				classElement.getName());
		treeModel.insertNodeInto(valueNodeClasses, parent, index);

		// La lista de clases de herencia
		DefaultMutableTreeNode classExtends = new DefaultMutableTreeNode(
				"Super class");
		treeModel.insertNodeInto(classExtends, valueNodeClasses, 0);

		DefaultMutableTreeNode valueNodeClassExtends = new DefaultMutableTreeNode(
				"NA");
		if (classElement.getExtend() != null) {
			valueNodeClassExtends = new DefaultMutableTreeNode(classElement
					.getExtend().getName());
		}

		treeModel.insertNodeInto(valueNodeClassExtends, classExtends, 0);

		// La lista de clases que implmenta
		DefaultMutableTreeNode classImplements = new DefaultMutableTreeNode(
				"Interface");
		treeModel.insertNodeInto(classImplements, valueNodeClasses, 1);

		DefaultMutableTreeNode valueNodeClassImplements = new DefaultMutableTreeNode(
				"NA");

		if (classElement.getInterfaces() != null) {
			Iterator<Interface> itClassImplements = classElement
					.getInterfaces().iterator();

			int classImplementsLevel = 0;
			while (itClassImplements.hasNext()) {
				Interface interfaze = itClassImplements.next();

				valueNodeClassImplements = new DefaultMutableTreeNode(
						interfaze.getName());
				treeModel.insertNodeInto(valueNodeClassImplements,
						classImplements, classImplementsLevel++);
			}
		} else {
			treeModel.insertNodeInto(valueNodeClassImplements, classImplements,
					0);
		}

		// La lista de atributos como primer hijo de la clase
		DefaultMutableTreeNode classAattribute = new DefaultMutableTreeNode(
				"Attributes");
		treeModel.insertNodeInto(classAattribute, valueNodeClasses, 2);

		Iterator<Attribute> it2 = classElement.getAttributes().iterator();

		int attributeLevel = 0;
		while (it2.hasNext()) {
			Attribute attribute = it2.next();
			DefaultMutableTreeNode valueNodeAttribute = new DefaultMutableTreeNode(
					attribute.getName() + " <"
							+ attribute.getType().getTypeName() + ">");
			treeModel.insertNodeInto(valueNodeAttribute, classAattribute,
					attributeLevel++);

			// Y aqui debemos empezar a manejar la recursividad, extraer codigo
			// y mandar a un método recursivo
			if (attribute.getType() instanceof CustomType) {
				DefaultMutableTreeNode nodeAttribute = new DefaultMutableTreeNode(
						"Es una clase, hay que abrir el arbol");
				treeModel.insertNodeInto(nodeAttribute, valueNodeAttribute, 0);
			}

		}

	}

	/**
	 * 
	 * @return
	 */
	private JPanel getClassesViewerPanel() {
		if (classesViewerPanel == null) {
			classesViewerPanel = new JPanel();
			classesViewerPanel.setLayout(new BoxLayout(classesViewerPanel,
					BoxLayout.Y_AXIS));
			classesViewerPanel.add(getClassesListScroll());
		}
		return classesViewerPanel;
	}

	public Stack getPila() {
		return pila;
	}

	// Marcelo-INICIO
	public void crearVentanaNewClass(String clase,TiposLlamadaVentana tipoLlamada) {
		cleanPanelRight();
		grilla = getNewClassPanel(clase, tipoLlamada);
		if (!clase.equals("")&&clase!=null) {
			pila.add(clase);
		}
		contentPanelDer.add(grilla);
		contentPanelDer.setVisible(true);
		showNewClassPanel();
		this.repaint();
		getMainFrameReference().validate();
	}

	public void crearVentanaNewInterfaz() {
		cleanPanelRight();
		contentPanelDer.add(getNewInterfacePanel());
		showNewInterface();
	}
	// ReestructuracionPaquete
//	public void crearVentanaNewPaquete() {
//		cleanPanelRight();
//		newPackagePanel = null;
//		contentPanelDer.add(getNewPackagePanel());
//		showNewPackagePanel();
//	}

	public void agregarNodoClase(ClassElement classElement) {
		/*
		 * classesViewerPanel.remove(classesListScroll); classesListScroll =
		 * null; JScrollPane j = getClassesListScroll();
		 * classesViewerPanel.add(j); classesViewerPanel.revalidate();
		 */
		// ReestructuracionPaquete
		// PackageElement packageElement =
		// project.obtenerPackageElement(packageElementName);
//		DefaultMutableTreeNode padre = TreeUtilities.obtenerNodo(treeModel,
//				packageElementName);
		DefaultMutableTreeNode padre = TreeUtilities.obtenerNodo(treeModel,
				"Classes");
		int index = padre.getChildCount();
		// int index = packageElement.getListClassElement().size() - 1;
		// MutableTreeNode nodePaquete =
		// (MutableTreeNode)path.getLastPathComponent();
		generateTreeChild(treeModel, classElement, padre, index);

	}
	
	public void removerNodoClase(ClassElement classElement) {
		// Find node to remove
		int startRow = 0;
		TreePath path = projectTree.getNextMatch(classElement.getName(), startRow, Position.Bias.Forward);
		MutableTreeNode node = (MutableTreeNode)path.getLastPathComponent();

		// Remove node; if node has descendants, all descendants are removed as well
		treeModel.removeNodeFromParent(node);
		projectTree.repaint();
	}
	// ReestructuracionPaquete
	// public void agregarNodoPaquete(PackageElement packageElement) {
	// int index = project.getPackages().size() - 1;
	// DefaultMutableTreeNode packageElementNode = new
	// DefaultMutableTreeNode(packageElement.getName());
	// treeModel.insertNodeInto(packageElementNode, nodeClasses, index);
	// }

	private void refrescarArbolProyecto() {
		contentPane.add(BorderLayout.WEST, getClassesViewerPanel());
		if (classesListScroll!=null){
			classesViewerPanel.remove(classesListScroll);
			classesListScroll = null;
		}
		classesViewerPanel.add(getClassesListScroll());
		classesViewerPanel.repaint();
		getMainFrameReference().validate();
	}

	// Marcelo-FIN
	// Fran - Inicio
	public Project getProject() {
		return project;
	}

	public void setProject(Project project) {
		this.project = project;
	}

	// graba el proyecto en XML
	private void grabaProyecto() {
		SaveXML saveXML = new SaveXML();
		saveXML.saveData(project);
	}

	public JTree getProjectTree() {
		return projectTree;
	}

	public void setProjectTree(JTree projectTree) {
		this.projectTree = projectTree;
	}

	
	
	// Fran - Fin

}
