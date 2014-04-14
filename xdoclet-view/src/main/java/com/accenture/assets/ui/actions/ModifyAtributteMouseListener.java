package com.accenture.assets.ui.actions;

import java.awt.Window;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JTable;

import org.apache.log4j.Logger;

import com.accenture.assets.beans.ClassElement;
import com.accenture.assets.enums.TiposLlamadaVentana;
import com.accenture.assets.ui.forms.MainFrame;
import com.accenture.assets.ui.forms.panels.NewDomainClassPanel;

public class ModifyAtributteMouseListener implements MouseListener {

	private static final Logger log = Logger.getLogger(ModifyAtributteMouseListener.class);
	
	private NewDomainClassPanel newDomainClassPanel;
	private Window parent;
	
	private JTable table;
	
	public ModifyAtributteMouseListener(Window parent, NewDomainClassPanel newDomainClassPanel,JTable table){
		this.parent = parent;
		this.newDomainClassPanel = newDomainClassPanel;
		this.table = table;
	}
	
	public void mouseClicked(MouseEvent e) {
//		PRESIONO DOBLE CLICK	
		if(e.getClickCount()==2 && !newDomainClassPanel.getTipoLlamada().equals(TiposLlamadaVentana.SOLO_LECTURA)){
			
			//NO PERMITE INGRESAR AL ID SI NO ESTA CREADA LA CLASS
			if(MainFrame.getInstancia().getProject().getClassByName(newDomainClassPanel.getClassNameInputText()) != null){
			
				// PARCHE PARA NO EJECUTAR INCEPTION DE INCEPTION (Eliminar cuando se implemente)
				if(!newDomainClassPanel.getTipoLlamada().equals(TiposLlamadaVentana.INCEPTION)){
					int row = table.getSelectedRow();			 
					String valor = table.getValueAt(row, 2).toString();
		
					if( !MainFrame.getInstancia().getProject().getMappTypesJava().containsKey(valor) ) {
						
						MainFrame padre = ((MainFrame)parent); 
						padre.crearVentanaNewClass(valor, TiposLlamadaVentana.INCEPTION);
						log.info("Fila seleccionada: " + (row + 1) + "-" + valor);
						log.info("pila: " + MainFrame.getInstancia().getPila());
					}
				}
			}
		}
		
	}

	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

}
