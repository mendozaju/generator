package com.accenture.assets.ui.forms;

import org.uispec4j.Button;
import org.uispec4j.MenuBar;
import org.uispec4j.MenuItem;
import org.uispec4j.Panel;
import org.uispec4j.Table;
import org.uispec4j.Tree;
import org.uispec4j.UISpec4J;
import org.uispec4j.UISpecTestCase;
import org.uispec4j.Window;
import org.uispec4j.interception.BasicHandler;
import org.uispec4j.interception.FileChooserHandler;
import org.uispec4j.interception.MainClassAdapter;
import org.uispec4j.interception.PopupMenuInterceptor;
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

		// 1. Retrieve the components
		Window window = getMainWindow();
		MenuBar menuBar = window.getMenuBar();
		MenuItem menuProyecto = menuBar.getMenu("Proyecto");
		MenuItem menuAbrir =
			menuProyecto.getSubMenu("Abrir proyecto existente");
		MenuItem menuHerramientas = menuBar.getMenu("Herramientas");
		MenuItem menuGenerar = menuHerramientas.getSubMenu("Generar Codigo");

		// WindowInterceptor.init(menuNuevo.triggerClick()).
		// process(BasicHandler.init().assertContainsText("pepe").
		// triggerButtonClick("Guardar")).run();

		WindowInterceptor.init(menuAbrir.triggerClick())
		.process(FileChooserHandler.init().select("C:\\app\\pepe.xml"))
		.run();

		Tree tree = window.getTree();
		assertTrue(tree.contentEquals("pepe\n" + "  Group Id\n" + "    1\n"
			+ "  Artifact Id\n" + "    1\n" + "  Version\n" + "    1\n"
			+ "  Tecnología\n" + "    Spring\n" + "  Clases\n" + "    C1\n"
			+ "      Super clase\n" + "        No aplica\n"
			+ "      Interfaces\n" + "      Atributos\n"
			+ "        idc1 <Double>"));
		tree.select("Clases/C1");

		PopupMenuInterceptor.run(tree.triggerRightClickInSelection())
		.getSubMenu("Editar").click();

		Panel newDomainClassPanel = window.getPanel("clases");
		Table tablaAtributos = newDomainClassPanel.getTable();
		System.out.println(tablaAtributos.getContentAt(0, 0));
		System.out.println(tablaAtributos.getContentAt(0, 1));
		System.out.println(tablaAtributos.getContentAt(0, 2));
		System.out.println(tablaAtributos.getContentAt(0, 3));
		System.out.println(tablaAtributos.getContentAt(0, 4));
		System.out.println(tablaAtributos.getContentAt(0, 5));
		System.out.println(tablaAtributos.getContentAt(0, 6));
		System.out.println(tablaAtributos.getContentAt(0, 7));
		System.out.println(tablaAtributos.getContentAt(0, 8));
		System.out.println(tablaAtributos.getContentAt(0, 9));
		System.out.println(tablaAtributos.getContentAt(0, 10));
		Button crear = newDomainClassPanel.getButton("Crear");

		menuGenerar.click();

	}

	public void testGeneratingANewProject() throws Exception {

		// 1. Retrieve the components
		Window window = getMainWindow();
		MenuBar menuBar = window.getMenuBar();
		MenuItem menuProyecto = menuBar.getMenu("Proyecto");
		MenuItem menuNuevo = menuProyecto.getSubMenu("Nuevo Proyecto");		
		MenuItem menuHerramientas = menuBar.getMenu("Herramientas");
		MenuItem menuGenerar = menuHerramientas.getSubMenu("Generar Codigo");

		WindowInterceptor.init(menuNuevo.triggerClick()).
		process(BasicHandler.init().triggerButtonClick("Guardar")).run();

		//		WindowInterceptor.init(menuAbrir.triggerClick())
		//			.process(FileChooserHandler.init().select("C:\\app\\pepe.xml"))
		//			.run();

		Tree tree = window.getTree();
		assertTrue(tree.contentEquals("pepe\n" + "  Group Id\n" + "    1\n"
			+ "  Artifact Id\n" + "    1\n" + "  Version\n" + "    1\n"
			+ "  Tecnología\n" + "    Spring\n" + "  Clases\n" + "    C1\n"
			+ "      Super clase\n" + "        No aplica\n"
			+ "      Interfaces\n" + "      Atributos\n"
			+ "        idc1 <Double>"));
		tree.select("Clases/C1");

		PopupMenuInterceptor.run(tree.triggerRightClickInSelection())
		.getSubMenu("Editar").click();

		Panel newDomainClassPanel = window.getPanel("clases");
		Table tablaAtributos = newDomainClassPanel.getTable();
		//		tablaAtributos.getContentAt(row, column);
		System.out.println(tablaAtributos.getContentAt(0, 0));
		System.out.println(tablaAtributos.getContentAt(0, 1));
		System.out.println(tablaAtributos.getContentAt(0, 2));
		System.out.println(tablaAtributos.getContentAt(0, 3));
		System.out.println(tablaAtributos.getContentAt(0, 4));
		System.out.println(tablaAtributos.getContentAt(0, 5));
		System.out.println(tablaAtributos.getContentAt(0, 6));
		System.out.println(tablaAtributos.getContentAt(0, 7));
		System.out.println(tablaAtributos.getContentAt(0, 8));
		System.out.println(tablaAtributos.getContentAt(0, 9));
		System.out.println(tablaAtributos.getContentAt(0, 10));
		Button crear = newDomainClassPanel.getButton("Crear");

		menuGenerar.click();
	}
}
