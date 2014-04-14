package com.accenture.assets.tree;

import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.tree.TreeNode;
import javax.swing.tree.TreePath;

import com.accenture.assets.beans.Attribute;
import com.accenture.assets.beans.ClassElement;
import com.accenture.assets.enums.TiposLlamadaVentana;
import com.accenture.assets.ui.forms.MainFrame;
import com.accenture.assets.ui.forms.panels.NewDomainClassPanel;

public class TreePopup {
	MainFrame mainFrame;
	JTree tree = new JTree();
	JPopupMenu popup;
	JPopupMenu popup2;

	public TreePopup(MainFrame mainFrame, JTree treeProject) {
		tree = treeProject;
		this.mainFrame = mainFrame;
		popup = new JPopupMenu();
		popup.setInvoker(tree);

		popup2 = new JPopupMenu();
		popup2.setInvoker(tree);

		PopupHandler handler = new PopupHandler(tree, popup, popup2, mainFrame);
		popup.add(getMenuItem("Add Class", handler));

		popup2.add(getMenuItem("Edit Class", handler));
		popup2.add(getMenuItem("View Class", handler));
		popup2.add(getMenuItem("Delete Class", handler));
	}

	protected JMenuItem getMenuItem(String s, ActionListener al) {
		JMenuItem menuItem = new JMenuItem(s);
		menuItem.setActionCommand(s.toUpperCase());
		menuItem.addActionListener(al);
		return menuItem;
	}

	private JScrollPane getTreeComponent() {
		System.out.println("path:");
		System.out.println(tree.getSelectionPath());
		tree.add(popup);
		expand(new TreePath(tree.getModel().getRoot()));
		return new JScrollPane(tree);
	}

	private void expand(TreePath path) {
		TreeNode node = (TreeNode) path.getLastPathComponent();
		if (node.getChildCount() >= 0) {
			java.util.Enumeration e = node.children();
			while (e.hasMoreElements()) {
				TreeNode n = (TreeNode) e.nextElement();
				expand(path.pathByAddingChild(n));
			}
		}
		tree.expandPath(path);
	}
}

class PopupHandler implements ActionListener {
	JTree tree;
	JPopupMenu popup;
	JPopupMenu popup2;
	Point loc;
	MainFrame mainFrame;

	public PopupHandler(JTree tree, JPopupMenu popup, JPopupMenu popup2,
			MainFrame mainFrame) {
		this.tree = tree;
		this.popup = popup;
		this.popup2 = popup2;
		tree.addMouseListener(ma);
		this.mainFrame = mainFrame;
	}

	public void actionPerformed(ActionEvent e) {
		String ac = e.getActionCommand();
		TreePath path = tree.getPathForLocation(loc.x, loc.y);
		// System.out.println("path = " + path);
		// System.out.printf("loc = [%d, %d]%n", loc.x, loc.y);
		if (mainFrame.getNewClassPanel()!=null&&((NewDomainClassPanel)mainFrame.getNewClassPanel()).getTipoLlamada().equals(TiposLlamadaVentana.INCEPTION)) {
			JOptionPane.showMessageDialog(mainFrame, "Press Back");
			return;
		}
		mainFrame.getPila().clear();
		if (ac.equals("ADD CLASS"))
			mainFrame.crearVentanaNewClass("", TiposLlamadaVentana.ALTA);
		// if (ac.equals("AGREGAR INTERFAZ"))
		// mainFrame.crearVentanaNewInterfaz();
		// if (ac.equals("AGREGAR PAQUETE"))
		// mainFrame.crearVentanaNewPaquete();
		if (ac.equals("EDIT CLASS"))
			mainFrame.crearVentanaNewClass(path.getLastPathComponent()
					.toString(), TiposLlamadaVentana.MODIFICACION);
		if (ac.equals("VIEW CLASS"))
			mainFrame.crearVentanaNewClass(path.getLastPathComponent()
					.toString(), TiposLlamadaVentana.SOLO_LECTURA);
		if (ac.equals("DELETE CLASS")) {

			//ff
			String claseDelArbol = path.getLastPathComponent().toString();
			List<ClassElement> clases = mainFrame.getProject().getClasses();

			for (ClassElement ce : clases) {
				List<Attribute> attributes = ce.getAttributes();
				for (Attribute at : attributes) {
					if (at.getType().toString().equals(claseDelArbol)) {
						JOptionPane.showMessageDialog(mainFrame,"The class can't be deleted, it's being used by the " + ce.getName() + " class");
						return;
					}
				}
			}
			
			int confirmado = JOptionPane.showConfirmDialog(mainFrame,
					"Are you sure that you want to delete the class?","Delete Class",1);

			if (JOptionPane.OK_OPTION == confirmado) {
				ClassElement claseAEliminar = new ClassElement();

				claseAEliminar.setName(path.getLastPathComponent().toString());

				MainFrame.getInstancia().getProject()
						.removeClasses(claseAEliminar);
				MainFrame.getInstancia().removerNodoClase(claseAEliminar);
			}
		}
	}

	private MouseListener ma = new MouseAdapter() {
		private void checkForPopup(MouseEvent e) {

			if (e.isPopupTrigger()) {
				Object selPath = tree.getPathForLocation(e.getX(), e.getY())
						.getLastPathComponent();
				Object nodo = selPath.toString();
				if (nodo.toString().equals("Classes")) {
					loc = e.getPoint();
					popup.show(tree, loc.x, loc.y);
				} else {
					Object selPath2 = tree.getPathForLocation(e.getX(),
							e.getY()).getParentPath();
					Object nodo2 = selPath2.toString();
					if (nodo2.toString().endsWith("Classes]")) {
						loc = e.getPoint();
						popup2.show(tree, loc.x, loc.y);
					}
				}
			}

		}

		public void mousePressed(MouseEvent e) {
			checkForPopup(e);

		}

		public void mouseReleased(MouseEvent e) {
			checkForPopup(e);
		}

		public void mouseClicked(MouseEvent e) {
			checkForPopup(e);
		}
	};
}
