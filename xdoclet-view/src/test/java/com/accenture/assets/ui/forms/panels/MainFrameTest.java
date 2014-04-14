package com.accenture.assets.ui.forms.panels;

import org.uispec4j.Button;
import org.uispec4j.MenuBar;
import org.uispec4j.MenuItem;
import org.uispec4j.Panel;
import org.uispec4j.Table;
import org.uispec4j.TextBox;
import org.uispec4j.Tree;
import org.uispec4j.Trigger;
import org.uispec4j.UISpec4J;
import org.uispec4j.UISpecTestCase;
import org.uispec4j.Window;
import org.uispec4j.interception.FileChooserHandler;
import org.uispec4j.interception.MainClassAdapter;
import org.uispec4j.interception.PopupMenuInterceptor;
import org.uispec4j.interception.WindowHandler;
import org.uispec4j.interception.WindowInterceptor;

import com.accenture.assets.ui.forms.test.GUITest;

public class MainFrameTest
extends UISpecTestCase {

	static {
		UISpec4J.init();
	}

	protected void setUp() throws Exception {

		setAdapter(new MainClassAdapter(GUITest.class, new String[0]));
	}

	public void testGeneratingAnExistingProject() throws Exception {

		Window window = getMainWindow();
		MenuBar menuBar = window.getMenuBar();
		MenuItem menuProyecto = menuBar.getMenu("Proyecto");
		MenuItem menuAbrir =
			menuProyecto.getSubMenu("Abrir proyecto existente");
		MenuItem menuHerramientas = menuBar.getMenu("Herramientas");
		MenuItem menuGenerar = menuHerramientas.getSubMenu("Generar Codigo");

		WindowInterceptor.init(menuAbrir.triggerClick())
		.process(FileChooserHandler.init().select("C:\\app\\pepe.xml"))
		.run();
		
		menuGenerar.click();
		window.dispose();
	}

	public void testCreatingAProyectAndPressingInfo() throws Exception {
		// 1. Retrieve the components
		Window window = getMainWindow();
		MenuBar menuBar = window.getMenuBar();
		MenuItem menuProyecto = menuBar.getMenu("Proyecto");
		MenuItem menuNuevo = menuProyecto.getSubMenu("Nuevo Proyecto");
		MenuItem menuHerramientas = menuBar.getMenu("Herramientas");

		WindowInterceptor.init(menuNuevo.triggerClick())
		.process(new WindowHandler() {

			public Trigger process(Window dialog) {

				dialog.getInputTextBox("projectNameInput").setText(
					"PruebaAutoTest");
				dialog.getInputTextBox("groupIdInput").setText(
					"groupidpruebaautotest");
				dialog.getInputTextBox("artifactIdInput").setText(
					"artifactpruebaautotest");
				dialog.getInputTextBox("versionInput").setText("1");

				WindowInterceptor
				.init(dialog.getButton("pathButton").triggerClick())
				.process(
					FileChooserHandler.init().select(
						"C:\\app\\proyectopruebaautotest.xml")).run();

				dialog.getComboBox().select("Spring");
				return dialog.getButton("Guardar").triggerClick();
			}
		}).run();
		MenuItem menuInfo = menuHerramientas.getSubMenu("Información proyecto existente");
		WindowInterceptor.init(menuInfo.triggerClick())
		.process(new WindowHandler() {

			public Trigger process(Window dialog) {

				assert(dialog.getInputTextBox("projectNameInput").getText().equals("PruebaAutoTest"));
				assert(dialog.getInputTextBox("groupIdInput").getText().equals("groupidpruebaautotest"));
				assert(dialog.getInputTextBox("artifactIdInput").getText().equals("artifactpruebaautotest"));
				assert(dialog.getInputTextBox("versionInput").getText().equals("1"));
				assert(dialog.getInputTextBox("pathInput").getText().equals("C:\\app\\proyectopruebaautotest.xml"));
				dialog.getComboBox().selectionEquals("Spring");
				return dialog.getButton("Cancelar").triggerClick();
			}
		}).run();
		
		window.dispose();
	}	
	
	public void testGeneratingANewProject() throws Exception {

		Window window = getMainWindow();
		MenuBar menuBar = window.getMenuBar();
		MenuItem menuProyecto = menuBar.getMenu("Proyecto");
		MenuItem menuNuevo = menuProyecto.getSubMenu("Nuevo Proyecto");
		MenuItem menuHerramientas = menuBar.getMenu("Herramientas");
		MenuItem menuGenerar = menuHerramientas.getSubMenu("Generar Codigo");

		WindowInterceptor.init(menuNuevo.triggerClick())
		.process(new WindowHandler() {

			public Trigger process(Window dialog) {

				dialog.getInputTextBox("projectNameInput").setText(
					"PruebaAutoTest");
				dialog.getInputTextBox("groupIdInput").setText(
					"groupidpruebaautotest");
				dialog.getInputTextBox("artifactIdInput").setText(
					"artifactpruebaautotest");
				dialog.getInputTextBox("versionInput").setText("1");

				WindowInterceptor
				.init(dialog.getButton("pathButton").triggerClick())
				.process(
					FileChooserHandler.init().select(
						"C:\\app\\proyectopruebaautotest.xml")).run();

				dialog.getComboBox().select("Spring");
				return dialog.getButton("Guardar").triggerClick();
			}
		}).run();

		Tree tree = window.getTree();
		tree.select("Clases");
		PopupMenuInterceptor.run(tree.triggerRightClickInSelection())
		.getSubMenu("Agregar Clase").click();

		Panel newDomainClassPanel = window.getPanel("clases");
		TextBox nombreClase = newDomainClassPanel.getInputTextBox(); 		
		Button crear = newDomainClassPanel.getButton("Crear");	
		Button nuevo = newDomainClassPanel.getButton("Nuevo");

		nuevo.click();
		nombreClase.setText("C1");
		Table tablaAtributos = newDomainClassPanel.getTable();		
		tablaAtributos.editCell(0, 0).getInputTextBox().setText("c1");
		tablaAtributos.editCell(0, 2).getComboBox().select("Double");
		crear.click();
		menuGenerar.click();
		window.dispose();		
	}
	
	public void testDeletingAClass() throws Exception {
		Window window = getMainWindow();
		MenuBar menuBar = window.getMenuBar();
		MenuItem menuProyecto = menuBar.getMenu("Proyecto");
		MenuItem menuNuevo = menuProyecto.getSubMenu("Nuevo Proyecto");

		WindowInterceptor.init(menuNuevo.triggerClick())
		.process(new WindowHandler() {

			public Trigger process(Window dialog) {

				dialog.getInputTextBox("projectNameInput").setText(
					"PruebaAutoTest");
				dialog.getInputTextBox("groupIdInput").setText(
					"groupidpruebaautotest");
				dialog.getInputTextBox("artifactIdInput").setText(
					"artifactpruebaautotest");
				dialog.getInputTextBox("versionInput").setText("1");

				WindowInterceptor
				.init(dialog.getButton("pathButton").triggerClick())
				.process(
					FileChooserHandler.init().select(
						"C:\\app\\proyectopruebaautotest.xml")).run();

				dialog.getComboBox().select("Spring");
				return dialog.getButton("Guardar").triggerClick();
			}
		}).run();

		Tree tree = window.getTree();
		tree.select("Clases");
		PopupMenuInterceptor.run(tree.triggerRightClickInSelection())
		.getSubMenu("Agregar Clase").click();

		Panel newDomainClassPanel = window.getPanel("clases");
		TextBox nombreClase = newDomainClassPanel.getInputTextBox(); 		
		Button crear = newDomainClassPanel.getButton("Crear");	
		Button nuevo = newDomainClassPanel.getButton("Nuevo");

		nuevo.click();
		nombreClase.setText("C1");
		Table tablaAtributos = newDomainClassPanel.getTable();
		tablaAtributos.editCell(0, 0).getInputTextBox().setText("idc1");
		tablaAtributos.editCell(0, 2).getComboBox().select("Double");
		crear.click();	
		
		tree.select("Clases/C1");
		
		WindowInterceptor.init(PopupMenuInterceptor.run(tree.triggerRightClickInSelection())
			.getSubMenu("Borrar Clase").triggerClick())
		.process(new WindowHandler() {

			public Trigger process(Window dialog) {
				return dialog.getButton("Sí").triggerClick();
			}
		}).run();		
		
		assertTrue(tree.contentEquals("PruebaAutoTest\n" + 
									  "  Group Id\n" +
									  "    groupidpruebaautotest\n"+
									  "  Artifact Id\n" +
									  "    artifactpruebaautotest\n" +
									  "  Version\n" +
									  "    1\n"	+
									  "  Tecnología\n" +
									  "    Spring\n" +
									  "  Clases"));				
		window.dispose();
	}

	public void testCreatingAClass() throws Exception {
		// 1. Retrieve the components
		Window window = getMainWindow();
		MenuBar menuBar = window.getMenuBar();
		MenuItem menuProyecto = menuBar.getMenu("Proyecto");
		MenuItem menuNuevo = menuProyecto.getSubMenu("Nuevo Proyecto");

		WindowInterceptor.init(menuNuevo.triggerClick())
		.process(new WindowHandler() {

			public Trigger process(Window dialog) {

				dialog.getInputTextBox("projectNameInput").setText(
					"PruebaAutoTest");
				dialog.getInputTextBox("groupIdInput").setText(
					"groupidpruebaautotest");
				dialog.getInputTextBox("artifactIdInput").setText(
					"artifactpruebaautotest");
				dialog.getInputTextBox("versionInput").setText("1");

				WindowInterceptor
				.init(dialog.getButton("pathButton").triggerClick())
				.process(
					FileChooserHandler.init().select(
						"C:\\app\\proyectopruebaautotest.xml")).run();

				dialog.getComboBox().select("Spring");
				return dialog.getButton("Guardar").triggerClick();
			}
		}).run();

		Tree tree = window.getTree();
		tree.select("Clases");
		PopupMenuInterceptor.run(tree.triggerRightClickInSelection())
		.getSubMenu("Agregar Clase").click();

		Panel newDomainClassPanel = window.getPanel("clases");
		TextBox nombreClase = newDomainClassPanel.getInputTextBox(); 		
		Button crear = newDomainClassPanel.getButton("Crear");	
		Button nuevo = newDomainClassPanel.getButton("Nuevo");

		nuevo.click();
		nombreClase.setText("C1");
		Table tablaAtributos = newDomainClassPanel.getTable();
		tablaAtributos.editCell(0, 0).getInputTextBox().setText("idc1");
		tablaAtributos.editCell(0, 2).getComboBox().select("Double");
		crear.click();	
		
		tree = window.getTree();
		assertTrue(tree.contentEquals("PruebaAutoTest\n" + 
									  "  Group Id\n" +
									  "    groupidpruebaautotest\n"+
									  "  Artifact Id\n" +
									  "    artifactpruebaautotest\n" +
									  "  Version\n" +
									  "    1\n"	+
									  "  Tecnología\n" +
									  "    Spring\n" +
									  "  Clases\n" +
									  "    C1\n" +
									  "      Super clase\n" +
									  "        No aplica\n"	+
									  "      Interfaces\n" +
									  "      Atributos\n" +
									  "        idc1 <Double>"));				
		window.dispose();
	}
	
	public void testCreatingAClassWithTwoPrimitiveAttributesAndRemovingOne() throws Exception {
		// 1. Retrieve the components
		Window window = getMainWindow();
		MenuBar menuBar = window.getMenuBar();
		MenuItem menuProyecto = menuBar.getMenu("Proyecto");
		MenuItem menuNuevo = menuProyecto.getSubMenu("Nuevo Proyecto");

		WindowInterceptor.init(menuNuevo.triggerClick())
		.process(new WindowHandler() {

			public Trigger process(Window dialog) {

				dialog.getInputTextBox("projectNameInput").setText(
					"PruebaAutoTest");
				dialog.getInputTextBox("groupIdInput").setText(
					"groupidpruebaautotest");
				dialog.getInputTextBox("artifactIdInput").setText(
					"artifactpruebaautotest");
				dialog.getInputTextBox("versionInput").setText("1");

				WindowInterceptor
				.init(dialog.getButton("pathButton").triggerClick())
				.process(
					FileChooserHandler.init().select(
						"C:\\app\\proyectopruebaautotest.xml")).run();

				dialog.getComboBox().select("Spring");
				return dialog.getButton("Guardar").triggerClick();
			}
		}).run();

		Tree tree = window.getTree();
		tree.select("Clases");
		PopupMenuInterceptor.run(tree.triggerRightClickInSelection())
		.getSubMenu("Agregar Clase").click();

		Panel newDomainClassPanel = window.getPanel("clases");
		TextBox nombreClase = newDomainClassPanel.getInputTextBox(); 		
		Button crear = newDomainClassPanel.getButton("Crear");	
		Button nuevo = newDomainClassPanel.getButton("Nuevo");

		nuevo.click();
		nuevo.click();		
		nombreClase.setText("C1");
		Table tablaAtributos = newDomainClassPanel.getTable();
		tablaAtributos.editCell(0, 0).getInputTextBox().setText("idc1");
		tablaAtributos.editCell(0, 2).getComboBox().select("Double");
		tablaAtributos.editCell(1, 0).getInputTextBox().setText("nombrec1");
		tablaAtributos.editCell(1, 2).getComboBox().select("String");
		Button remover = newDomainClassPanel.getButton("Remover");
		tablaAtributos.selectRow(1);
		remover.click();
		crear.click();	
		
		tree = window.getTree();
		assertTrue(tree.contentEquals("PruebaAutoTest\n" + 
									  "  Group Id\n" +
									  "    groupidpruebaautotest\n"+
									  "  Artifact Id\n" +
									  "    artifactpruebaautotest\n" +
									  "  Version\n" +
									  "    1\n"	+
									  "  Tecnología\n" +
									  "    Spring\n" +
									  "  Clases\n" +
									  "    C1\n" +
									  "      Super clase\n" +
									  "        No aplica\n"	+
									  "      Interfaces\n" +
									  "      Atributos\n" +
									  "        idc1 <Double>"));				
		window.dispose();
	}	

	public void testCreatingAClassWithTwoPrimitiveAttributesAndRemovingAll() throws Exception {
		// 1. Retrieve the components
		Window window = getMainWindow();
		MenuBar menuBar = window.getMenuBar();
		MenuItem menuProyecto = menuBar.getMenu("Proyecto");
		MenuItem menuNuevo = menuProyecto.getSubMenu("Nuevo Proyecto");

		WindowInterceptor.init(menuNuevo.triggerClick())
		.process(new WindowHandler() {

			public Trigger process(Window dialog) {

				dialog.getInputTextBox("projectNameInput").setText(
					"PruebaAutoTest");
				dialog.getInputTextBox("groupIdInput").setText(
					"groupidpruebaautotest");
				dialog.getInputTextBox("artifactIdInput").setText(
					"artifactpruebaautotest");
				dialog.getInputTextBox("versionInput").setText("1");

				WindowInterceptor
				.init(dialog.getButton("pathButton").triggerClick())
				.process(
					FileChooserHandler.init().select(
						"C:\\app\\proyectopruebaautotest.xml")).run();

				dialog.getComboBox().select("Spring");
				return dialog.getButton("Guardar").triggerClick();
			}
		}).run();

		Tree tree = window.getTree();
		tree.select("Clases");
		PopupMenuInterceptor.run(tree.triggerRightClickInSelection())
		.getSubMenu("Agregar Clase").click();

		Panel newDomainClassPanel = window.getPanel("clases");
		TextBox nombreClase = newDomainClassPanel.getInputTextBox(); 		
		Button crear = newDomainClassPanel.getButton("Crear");	
		Button nuevo = newDomainClassPanel.getButton("Nuevo");

		nuevo.click();
		nuevo.click();		
		nombreClase.setText("C1");
		Table tablaAtributos = newDomainClassPanel.getTable();
		tablaAtributos.editCell(0, 0).getInputTextBox().setText("idc1");
		tablaAtributos.editCell(0, 2).getComboBox().select("Double");
		tablaAtributos.editCell(1, 0).getInputTextBox().setText("nombrec1");
		tablaAtributos.editCell(1, 2).getComboBox().select("String");
		Button eliminar = newDomainClassPanel.getButton("Eliminar");
		tablaAtributos.selectRow(1);
		eliminar.click();
		crear.click();
		
		tree = window.getTree();
		assertTrue(tree.contentEquals("PruebaAutoTest\n" + 
									  "  Group Id\n" +
									  "    groupidpruebaautotest\n"+
									  "  Artifact Id\n" +
									  "    artifactpruebaautotest\n" +
									  "  Version\n" +
									  "    1\n"	+
									  "  Tecnología\n" +
									  "    Spring\n" +
									  "  Clases\n" +
									  "    C1\n" +
									  "      Super clase\n" +
									  "        No aplica\n"	+
									  "      Interfaces\n" +
									  "      Atributos"));				
		window.dispose();
	}	
	
	public void testGeneratingAClassWithAClassAttribute() {
		// 1. Retrieve the components
		Window window = getMainWindow();
		MenuBar menuBar = window.getMenuBar();
		MenuItem menuProyecto = menuBar.getMenu("Proyecto");
		MenuItem menuNuevo = menuProyecto.getSubMenu("Nuevo Proyecto");
		
		WindowInterceptor.init(menuNuevo.triggerClick())
		.process(new WindowHandler() {
			
			public Trigger process(Window dialog) {
				
				dialog.getInputTextBox("projectNameInput").setText(
					"PruebaAutoTest");
				dialog.getInputTextBox("groupIdInput").setText(
					"groupidpruebaautotest");
				dialog.getInputTextBox("artifactIdInput").setText(
					"artifactpruebaautotest");
				dialog.getInputTextBox("versionInput").setText("1");

				WindowInterceptor
				.init(dialog.getButton("pathButton").triggerClick())
				.process(
					FileChooserHandler.init().select(
						"C:\\app\\proyectopruebaautotest.xml")).run();

				dialog.getComboBox().select("Spring");
				return dialog.getButton("Guardar").triggerClick();
			}
		}).run();

		Tree tree = window.getTree();
		tree.select("Clases");
		PopupMenuInterceptor.run(tree.triggerRightClickInSelection())
		.getSubMenu("Agregar Clase").click();

		Panel newDomainClassPanel = window.getPanel("clases");
		TextBox nombreClase = newDomainClassPanel.getInputTextBox();
		Button crear = newDomainClassPanel.getButton("Crear");
		Button nuevo = newDomainClassPanel.getButton("Nuevo");

		nombreClase.setText("C1");
		nuevo.click();		
		Table tablaAtributos = newDomainClassPanel.getTable();
		tablaAtributos.editCell(0, 0).getInputTextBox().setText("idc1");
		tablaAtributos.editCell(0, 2).getComboBox().select("Double");
		crear.click();

		nombreClase.setText("C2");
		nuevo.click();		
		nuevo.click();
		tablaAtributos = newDomainClassPanel.getTable();		
		tablaAtributos.editCell(0, 0).getInputTextBox().setText("idc2");
		tablaAtributos.editCell(0, 2).getComboBox().select("Double");		
		tablaAtributos.editCell(1, 0).getInputTextBox().setText("c1");
		tablaAtributos.editCell(1, 2).getComboBox().select("C1");		
		crear.click();		
		
		tree = window.getTree();
		assertTrue(tree.contentEquals("PruebaAutoTest\n" + 
									  "  Group Id\n" +
									  "    groupidpruebaautotest\n"+
									  "  Artifact Id\n" +
									  "    artifactpruebaautotest\n" +
									  "  Version\n" +
									  "    1\n"	+
									  "  Tecnología\n" +
									  "    Spring\n" +
									  "  Clases\n" +
									  "    C1\n" +
									  "      Super clase\n" +
									  "        No aplica\n"	+
									  "      Interfaces\n" +
									  "      Atributos\n" +
									  "        idc1 <Double>\n"+
									  "    C2\n" +
									  "      Super clase\n" +
									  "        No aplica\n"	+
									  "      Interfaces\n" +
									  "      Atributos\n" +
									  "        idc2 <Double>\n" +
									  "        c1 <C1>"));		
		
		window.dispose();
	}

	public void testGeneratingAClassWithAnAttributeAndPressingCancel() {
		// 1. Retrieve the components
		Window window = getMainWindow();
		MenuBar menuBar = window.getMenuBar();
		MenuItem menuProyecto = menuBar.getMenu("Proyecto");
		MenuItem menuNuevo = menuProyecto.getSubMenu("Nuevo Proyecto");
		
		WindowInterceptor.init(menuNuevo.triggerClick())
		.process(new WindowHandler() {
			
			public Trigger process(Window dialog) {
				
				dialog.getInputTextBox("projectNameInput").setText(
					"PruebaAutoTest");
				dialog.getInputTextBox("groupIdInput").setText(
					"groupidpruebaautotest");
				dialog.getInputTextBox("artifactIdInput").setText(
					"artifactpruebaautotest");
				dialog.getInputTextBox("versionInput").setText("1");

				WindowInterceptor
				.init(dialog.getButton("pathButton").triggerClick())
				.process(
					FileChooserHandler.init().select(
						"C:\\app\\proyectopruebaautotest.xml")).run();

				dialog.getComboBox().select("Spring");
				return dialog.getButton("Guardar").triggerClick();
			}
		}).run();

		Tree tree = window.getTree();
		tree.select("Clases");
		PopupMenuInterceptor.run(tree.triggerRightClickInSelection())
		.getSubMenu("Agregar Clase").click();

		Panel newDomainClassPanel = window.getPanel("clases");
		TextBox nombreClase = newDomainClassPanel.getInputTextBox();
		Button cancelar = newDomainClassPanel.getButton("Cancelar");
		Button nuevo = newDomainClassPanel.getButton("Nuevo");

		nombreClase.setText("C1");
		nuevo.click();		
		Table tablaAtributos = newDomainClassPanel.getTable();
		tablaAtributos.editCell(0, 0).getInputTextBox().setText("idc1");
		tablaAtributos.editCell(0, 2).getComboBox().select("Double");
		cancelar.click();
		
		tree = window.getTree();
		assertTrue(tree.contentEquals("PruebaAutoTest\n" + 
									  "  Group Id\n" +
									  "    groupidpruebaautotest\n"+
									  "  Artifact Id\n" +
									  "    artifactpruebaautotest\n" +
									  "  Version\n" +
									  "    1\n"	+
									  "  Tecnología\n" +
									  "    Spring\n" +
									  "  Clases"));		
		
		window.dispose();
	}
	
	
	public void testGeneratingAClassWithAClassAttributeEditingItAndGoingIntoTheAttribute() {
		// 1. Retrieve the components
		Window window = getMainWindow();
		MenuBar menuBar = window.getMenuBar();
		MenuItem menuProyecto = menuBar.getMenu("Proyecto");
		MenuItem menuNuevo = menuProyecto.getSubMenu("Nuevo Proyecto");
		
		WindowInterceptor.init(menuNuevo.triggerClick())
		.process(new WindowHandler() {
			
			public Trigger process(Window dialog) {
				
				dialog.getInputTextBox("projectNameInput").setText(
					"PruebaAutoTest");
				dialog.getInputTextBox("groupIdInput").setText(
					"groupidpruebaautotest");
				dialog.getInputTextBox("artifactIdInput").setText(
					"artifactpruebaautotest");
				dialog.getInputTextBox("versionInput").setText("1");

				WindowInterceptor
				.init(dialog.getButton("pathButton").triggerClick())
				.process(
					FileChooserHandler.init().select(
						"C:\\app\\proyectopruebaautotest.xml")).run();

				dialog.getComboBox().select("Spring");
				return dialog.getButton("Guardar").triggerClick();
			}
		}).run();

		Tree tree = window.getTree();
		tree.select("Clases");
		PopupMenuInterceptor.run(tree.triggerRightClickInSelection())
		.getSubMenu("Agregar Clase").click();

		Panel newDomainClassPanel = window.getPanel("clases");
		TextBox nombreClase = newDomainClassPanel.getInputTextBox();
		Button crear = newDomainClassPanel.getButton("Crear");
		Button nuevo = newDomainClassPanel.getButton("Nuevo");

		nombreClase.setText("C1");
		nuevo.click();		
		Table tablaAtributos = newDomainClassPanel.getTable();
		tablaAtributos.editCell(0, 0).getInputTextBox().setText("idc1");
		tablaAtributos.editCell(0, 2).getComboBox().select("Double");
		crear.click();

		nombreClase.setText("C2");
		nuevo.click();		
		nuevo.click();
		tablaAtributos = newDomainClassPanel.getTable();		
		tablaAtributos.editCell(0, 0).getInputTextBox().setText("idc2");
		tablaAtributos.editCell(0, 2).getComboBox().select("Double");		
		tablaAtributos.editCell(1, 0).getInputTextBox().setText("c1");
		tablaAtributos.editCell(1, 2).getComboBox().select("C1");		
		crear.click();		
		
		tree = window.getTree();
		assertTrue(tree.contentEquals("PruebaAutoTest\n" + 
									  "  Group Id\n" +
									  "    groupidpruebaautotest\n"+
									  "  Artifact Id\n" +
									  "    artifactpruebaautotest\n" +
									  "  Version\n" +
									  "    1\n"	+
									  "  Tecnología\n" +
									  "    Spring\n" +
									  "  Clases\n" +
									  "    C1\n" +
									  "      Super clase\n" +
									  "        No aplica\n"	+
									  "      Interfaces\n" +
									  "      Atributos\n" +
									  "        idc1 <Double>\n"+
									  "    C2\n" +
									  "      Super clase\n" +
									  "        No aplica\n"	+
									  "      Interfaces\n" +
									  "      Atributos\n" +
									  "        idc2 <Double>\n" +
									  "        c1 <C1>"));				
		
		tree.select("Clases/C1");
		
		PopupMenuInterceptor.run(tree.triggerRightClickInSelection())
		.getSubMenu("Editar Clase").click();
		
		newDomainClassPanel.getInputTextBox().setText("C2");
		tablaAtributos.selectRow(1);
		tablaAtributos.editCell(1, 10).getButton().click();
		newDomainClassPanel = window.getPanel("clases");
		tablaAtributos = newDomainClassPanel.getTable();
		assert(tablaAtributos.editCell(0, 0).getTextBox().getText().equals("idc1"));
		Button volver = newDomainClassPanel.getButton("volver");
		volver.click();
		newDomainClassPanel = window.getPanel("clases");
		tablaAtributos = newDomainClassPanel.getTable();
		System.out.println("IDROW0: "+tablaAtributos.editCell(0, 0).getTextBox().getText());	
		window.dispose();		
	}		
}
