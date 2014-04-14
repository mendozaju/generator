package com.accenture.assets.utils;

import java.awt.Dimension;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.Window;
import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

public class SwingUtils {
	/**
	 * 
	 * @param owner
	 * @return El directorio seleccionado
	 */
	public static File openDirectoryChooser(Window owner){
		int userResponse;
		JFileChooser fileChooser = new JFileChooser();
		fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		userResponse = fileChooser.showOpenDialog(owner);
		if(JFileChooser.APPROVE_OPTION == userResponse){
			return fileChooser.getSelectedFile();
		}
		return null;
	}
	
	/**
	 * 
	 * @param owner
	 * @return El directorio seleccionado
	 */
	public static File openFileChooser(Window owner){
		int userResponse;
		JFileChooser fileChooser = new JFileChooser();
		FileNameExtensionFilter filter = new FileNameExtensionFilter("XML", "xml");
		fileChooser.setFileFilter(filter);
		userResponse = fileChooser.showOpenDialog(owner);
		if(JFileChooser.APPROVE_OPTION == userResponse){
			return fileChooser.getSelectedFile();
		}
		return null;
	}
	
	/**
	 * 
	 * @param owner
	 * @return El directorio seleccionado
	 */
	public static File saveFileChooser(Window owner){
		int userResponse;
		JFileChooser fileChooser = new JFileChooser();
		FileNameExtensionFilter filter = new FileNameExtensionFilter("XML", "xml");
		fileChooser.setFileFilter(filter);
		userResponse = fileChooser.showSaveDialog(owner);
		if(JFileChooser.APPROVE_OPTION == userResponse){
			return fileChooser.getSelectedFile();
		}
		return null;
	}
	
	/**
	 * 
	 * @return The middle point on the screen, for the window
	 */
	public static Point getCenterPoint(Window window){
		 // Get the size of the screen
	    Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
	    
	    // Determine the new location of the window
	    int w = window.getSize().width;
	    int h = window.getSize().height;
	    int x = (dim.width-w)/2;
	    int y = (dim.height-h)/2;
	    
		return new Point(x,y);
	}
}
