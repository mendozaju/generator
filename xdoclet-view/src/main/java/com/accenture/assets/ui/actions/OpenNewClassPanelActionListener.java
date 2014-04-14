package com.accenture.assets.ui.actions;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;

public class OpenNewClassPanelActionListener implements ActionListener {

	private JPanel panelToBeShown;
	
	public OpenNewClassPanelActionListener(JPanel panelToBeShown){
		this.panelToBeShown = panelToBeShown;
	}
	
	public void actionPerformed(ActionEvent e) {
		panelToBeShown.setVisible(true);
	}

}
