package com.accenture.assets.ui.dialogs.panels;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import com.accenture.assets.beans.Project;
import com.accenture.assets.helpers.ContextHelper;
import com.accenture.assets.helpers.JComboBoxProvider;
import com.accenture.assets.ui.actions.ShowDirectoryChooserActionListener;
import com.accenture.assets.ui.forms.MainFrame;

/**
 * @author pablo.m.ibarrola
 *
 */
public class InfoProjectConfigurationDialogPanel extends JPanel {

	
	private static final long serialVersionUID = 1L;
	
	/**
	 * 
	 * @param owner
	 */
	public InfoProjectConfigurationDialogPanel(final Window owner) {
		
		Dimension LABEL_SIZE  = new Dimension(70, 20);
		Dimension INPUT_SIZE  = new Dimension(150, 20);
		Dimension BUTTON_SIZE = new Dimension(80, 20);
		
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		
		//setLayout(new BoxLayout(finalPanel, BoxLayout.Y_AXIS));
		
		// /////////////////////////
		// Group Id
		// /////////////////////////
		JPanel groupIdPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		
		JLabel groupIdLabel = new JLabel("Group ID:");
		groupIdLabel.setPreferredSize(LABEL_SIZE);
		groupIdPanel.add(groupIdLabel);

		JTextField groupIdInput = new JTextField();
		groupIdInput.setPreferredSize(INPUT_SIZE);
		groupIdPanel.add(groupIdInput);
		
		groupIdInput.setEnabled(false);
		
		add(groupIdPanel);
		
		// /////////////////////////
		// Artifact Id
		// /////////////////////////
		JPanel artifactIdPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		JLabel artifactIdLabel = new JLabel("Artifact ID:");
		artifactIdLabel.setPreferredSize(LABEL_SIZE);
		artifactIdPanel.add(artifactIdLabel);
		
		JTextField artifactIdInput = new JTextField();
		artifactIdInput.setPreferredSize(INPUT_SIZE);
		artifactIdPanel.add(artifactIdInput);

		artifactIdInput.setEnabled(false);
		
		add(artifactIdPanel);

		// /////////////////////////
		// Version
		// /////////////////////////
		JPanel versionPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));

		JLabel versionLabel = new JLabel("Version:");
		versionLabel.setPreferredSize(LABEL_SIZE);
		versionPanel.add(versionLabel);
		
		JTextField versionInput = new JTextField();
		versionInput.setPreferredSize(INPUT_SIZE);
		versionPanel.add(versionInput);
		
		versionInput.setEnabled(false);
		
		add(versionPanel);
		
		// /////////////////////////
		// Root directory
		// /////////////////////////
		JPanel rootPanel= new JPanel(new FlowLayout(FlowLayout.LEFT));
		
		JLabel folderRootLabel = new JLabel("Path:");
		folderRootLabel.setPreferredSize(LABEL_SIZE);
		rootPanel.add(folderRootLabel);
		
		JTextField folderRootDisplay = new JTextField();
		folderRootDisplay.setPreferredSize(INPUT_SIZE);
		folderRootDisplay.setEditable(false);
		rootPanel.add(folderRootDisplay);
		
		JButton browseButton = new JButton("Open");
		browseButton.setPreferredSize(BUTTON_SIZE);
		browseButton.setEnabled(false);
		browseButton.addActionListener(new ShowDirectoryChooserActionListener(owner, folderRootDisplay));
		rootPanel.add(browseButton);
		
		add(rootPanel);
		
		// /////////////////////////
		// Tecnologia
		// /////////////////////////
		JPanel technologyPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		
		JLabel technologyLabel = new JLabel("Technology:");
		technologyLabel.setPreferredSize(LABEL_SIZE);
		technologyPanel.add(technologyLabel);
		
		JComboBox technologyCombo = JComboBoxProvider.getTechnologies();
		technologyCombo.setPreferredSize(INPUT_SIZE);
		technologyPanel.add(technologyCombo);
		
		technologyCombo.setEnabled(false);
		
		add(technologyPanel);
		
		// /////////////////////////
		// Botones
		// /////////////////////////
		JPanel buttonsPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		
		JButton cancelButton = new JButton("OK");
		cancelButton.setPreferredSize(BUTTON_SIZE);
		cancelButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				owner.dispose();
			}
		});		
		buttonsPanel.add(cancelButton);
		
		add(buttonsPanel);
		
		
		// //////////////////////////////
		// Carga de datos del proyecto
		// //////////////////////////////
		
		//Fran Inicio - Recupero la ventana padre y con ello el PROJECT
		Object prueba =SwingUtilities.getWindowAncestor(owner); 
		MainFrame mF;
		Project project = null; 
		if(prueba instanceof JFrame){
			project = new Project();
			mF = (MainFrame)(prueba);
			project = mF.getProject();
		}
//Fran Fin
		
		 //project = (Project) ContextHelper.getBean(ContextHelper.PROJECT_CONFIGURATION);
		
		artifactIdInput.setText(project.getArtifactId());
		groupIdInput.setText(project.getGroupId());	
		versionInput.setText(project.getVersion());
		folderRootDisplay.setText(project.getLocWorkSpace().replace("/", "\\")+"\\");
		technologyCombo.setSelectedItem(project.getTechnology());
		
	}
}
