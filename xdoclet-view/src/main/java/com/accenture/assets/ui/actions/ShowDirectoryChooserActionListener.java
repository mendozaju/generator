package com.accenture.assets.ui.actions;

import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JTextField;

import com.accenture.assets.utils.SwingUtils;

public class ShowDirectoryChooserActionListener implements ActionListener {

	private Window parent;
	
	private JTextField display;
	
	public ShowDirectoryChooserActionListener(Window parent,JTextField display){
		this.parent = parent;
		this.display = display;
	}
	
	public void actionPerformed(ActionEvent e) {
		File fileSelected =	SwingUtils.openDirectoryChooser(parent);
		if(fileSelected!=null){			
			display.setText(fileSelected.getAbsolutePath());
		}
	}

}
