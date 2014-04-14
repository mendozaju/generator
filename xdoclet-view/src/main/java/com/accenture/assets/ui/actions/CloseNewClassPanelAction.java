package com.accenture.assets.ui.actions;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;

public class CloseNewClassPanelAction implements ActionListener {

	private JPanel panelToBeClosed;
	
	public CloseNewClassPanelAction(JPanel panel){
		this.panelToBeClosed = panel;
	}
	
	public void actionPerformed(ActionEvent e) {
		panelToBeClosed.setVisible(Boolean.FALSE);
	}
}
