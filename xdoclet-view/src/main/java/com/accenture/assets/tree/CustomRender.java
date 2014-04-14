package com.accenture.assets.tree;

import java.awt.Component;
import java.awt.Dimension;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreeCellRenderer;

import com.accenture.assets.ui.forms.MainFrame;

public class CustomRender extends JPanel implements TreeCellRenderer {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ImageIcon defaultIcon;
	private ImageIcon projectIcon;
	private ImageIcon packageIcon;
	private ImageIcon classIcon;
	private ImageIcon attributeIcon;
	private ImageIcon folderIcon;
	private ImageIcon atribusProyIcon;

	private JButton botonIcono = new JButton();
	private JButton botonTexto = new JButton();
	Dimension min = new Dimension(15, 15);

	public CustomRender() {
		super();
		setOpaque(false);
		defaultIcon = new ImageIcon(
				CustomRender.class.getResource("/images/default.gif"));

		packageIcon = new ImageIcon(
				CustomRender.class.getResource("/images/package_obj.gif"));

		projectIcon = new ImageIcon(
				CustomRender.class.getResource("/images/projects.gif"));
		classIcon = new ImageIcon(
				CustomRender.class.getResource("/images/class_obj.gif"));

		attributeIcon = new ImageIcon(
				CustomRender.class.getResource("/images/field_private_obj.gif"));

		folderIcon = new ImageIcon(
				CustomRender.class.getResource("/images/output_folder_attrib.gif"));

		atribusProyIcon = new ImageIcon(
				CustomRender.class.getResource("/images/installed_ovr_disabled.gif"));

		botonTexto.setOpaque(false);
		botonTexto.setContentAreaFilled(false);
		botonTexto.setBorderPainted(false);

		botonIcono.setOpaque(false);
		botonIcono.setContentAreaFilled(false);
		botonIcono.setBorderPainted(false);

		botonIcono.setPreferredSize(min);
		add(botonIcono);

		add(botonTexto);
	}

	public Component getTreeCellRendererComponent(JTree tree, Object value,
			boolean selected, boolean expanded, boolean leaf, int row,
			boolean hasFocus) {

		botonIcono.setIcon(defaultIcon);
		botonTexto.setText("");
		DefaultMutableTreeNode nodoARenderizar = (DefaultMutableTreeNode) value;
		String project = MainFrame.getInstancia().getProject().getName();

		if (!((DefaultMutableTreeNode) value).toString().equals(project)) {
			if (!obtenerNodoSuperior(nodoARenderizar, 1).toString().equals(
					project))

				// CLASE
				if (obtenerNodoSuperior(nodoARenderizar, 1).getUserObject()
						.equals("Classes")) {
					botonTexto.setText(((DefaultMutableTreeNode) value)
							.getUserObject().toString());
					botonIcono.setIcon(classIcon);
				} else {
					// ATRIBUTOS DEL PROYECTO (TODOS LOS QUE NO SEAN CLASE!)
					String nombrePadre = obtenerNodoSuperior(nodoARenderizar, 1)
							.getUserObject().toString();
					if (nombrePadre.equals("Group Id")
							|| nombrePadre.equals("Artifact Id")
							|| nombrePadre.equals("Version")
							|| nombrePadre.equals("Technology")) {

						botonTexto.setText(((DefaultMutableTreeNode) value)
								.getUserObject().toString());
						botonIcono.setIcon(atribusProyIcon);
					}

					// CARPETA (Las de Super Clase-Implementa-Atributos)
					else if ((obtenerNodoSuperior(nodoARenderizar, 2)
							.getUserObject().equals("Classes"))) {

						botonTexto.setText(((DefaultMutableTreeNode) value)
								.getUserObject().toString());
						botonIcono.setIcon(folderIcon);
					} else {

						if ((obtenerNodoSuperior(nodoARenderizar, 3)
								.getUserObject().equals("Classes"))) {
							botonTexto.setText(((DefaultMutableTreeNode) value)
									.getUserObject().toString());
							// ATRIBUTOS
							if ((obtenerNodoSuperior(nodoARenderizar, 1)
									.getUserObject().equals("Attributes"))) {
								botonIcono.setIcon(attributeIcon);
							}
							// SUPERCLASES
							else {
								if (!nodoARenderizar.getUserObject().equals(
										"NA")) {
									botonIcono.setIcon(classIcon);
								}
							}
						}
						// else {
						// if ((obtenerNodoSuperior(nodoARenderizar, 4)
						// .getUserObject().equals("Clases"))) {
						//
						// // ATRIBUTOS
						// if (obtenerNodoSuperior(nodoARenderizar, 1)
						// .getUserObject().equals("Atributos")) {
						// botonTexto
						// .setText(((DefaultMutableTreeNode) value)
						// .getUserObject().toString());
						// botonIcono.setIcon(attributeIcon);
						// }
						// }
						// }
					}
				}
			else {
				// HIJOS DE PROYECTO
				botonTexto.setText(((DefaultMutableTreeNode) value)
						.getUserObject().toString());
			}
			// PROYECTO
		} else {
			if (((DefaultMutableTreeNode) nodoARenderizar).getChildCount() != 0) {
				botonIcono.setIcon(projectIcon);
				botonTexto.setText(MainFrame.getInstancia().getProject().getName());
			}
		}

		return this;
	}

	public DefaultMutableTreeNode obtenerNodoSuperior(
			DefaultMutableTreeNode nodo, int jerarquias) {
		DefaultMutableTreeNode nodoSuperior = nodo;
		for (int i = 0; i < jerarquias; i++) {
			nodoSuperior = (DefaultMutableTreeNode) ((DefaultMutableTreeNode) nodoSuperior)
					.getParent();
		}
		return nodoSuperior;
	}
}
