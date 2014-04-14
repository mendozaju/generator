package com.accenture.assets.ui.forms.test;

import java.util.Locale;

import javax.swing.UIManager;

import org.apache.log4j.Logger;

import com.accenture.assets.ui.forms.MainFrame;

/**
 * 
 * @author santiago.a.gonzalez
 *
 */
public class GUITest {
	
	private static final Logger log = Logger.getLogger(GUITest.class);
	
	public static void main(String[] args){
		 try {
//			Seteo de 'Look and Feel'
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
//			UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
//			UIManager.setLookAndFeel(UIManager.getLookAndFeelDefaults();
			
			Locale defaultLocale = new Locale("us","US");
			java.util.Locale.setDefault(defaultLocale);
			
			//Marcelo-INICIO
			MainFrame mainFrame = MainFrame.getInstancia();
			//Marcelo-FIN
			mainFrame.setVisible(true);
		} catch (Exception e) {
			log.error("Error al setear Look and Feel: " , e);
		}
	}
}
