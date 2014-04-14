package com.accenture.assets.ui.dialogs;

import java.awt.ComponentOrientation;
import java.awt.Dimension;

import javax.swing.JDialog;
import javax.swing.JFrame;

import com.accenture.assets.ui.dialogs.panels.InfoProjectConfigurationDialogPanel;
import com.accenture.assets.utils.SwingUtils;

/**
 * @author pablo.m.ibarrola
 *
 */
public class InfoProjectConfigurationDialog extends JDialog {

	private static final long serialVersionUID = 1L;
	
	/**
	 * 
	 * @param owner
	 */
	public InfoProjectConfigurationDialog(JFrame owner){
		super(owner,true);
		init();
	}
	
	/**
	 * 
	 * @param owner
	 */
	public InfoProjectConfigurationDialog(JDialog owner){
		super(owner,true);
		init();	
	}
	
	/**
	 * 
	 */
	private void init() {
		setTitle("Project Properties");
		setResizable(false);
		setSize(new Dimension(350, 230));
		setLocation(SwingUtils.getCenterPoint(this));
		setContentPane(new InfoProjectConfigurationDialogPanel(this));
	}
}
