package com.accenture.assets.ui.dialogs.panels;

import java.awt.Dimension;
import java.awt.Window;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.accenture.assets.ui.actions.ShowDirectoryChooserActionListener;
import com.accenture.assets.utils.SwingUtils;

public class OpenProjectDialogPanel extends JDialog{
	private static final Dimension INPUT_SIZE = new Dimension(150,20);
	private static final Dimension WINDOWS_SIZE = new Dimension(350,230);
	private Window owner;
	private File archivo;
	/** Folder Root Input*/
	private JTextField folderRootDisplay;
	
	public OpenProjectDialogPanel(Window owner){
		this.owner=owner;
		init();
	}
	
	private void init(){
		setTitle("Open Project");
		setResizable(false);
		setSize(WINDOWS_SIZE);
		setLocation(SwingUtils.getCenterPoint(this));
		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		getBrowse();
	}
	
	/**
	 * 
	 * @return
	 */
	private JTextField getFolderRootDisplay(){
		if(folderRootDisplay==null){
			folderRootDisplay = new JTextField();
			folderRootDisplay.setPreferredSize(INPUT_SIZE);
			folderRootDisplay.setEditable(false);
		}
		return folderRootDisplay;
	}
	
	private void getBrowse(){
		archivo = SwingUtils.openFileChooser(owner);
		//return new ShowDirectoryChooserActionListener(owner,getFolderRootDisplay());
	}

	public File getArchivo() {
		return archivo;
	}
	
	
}
