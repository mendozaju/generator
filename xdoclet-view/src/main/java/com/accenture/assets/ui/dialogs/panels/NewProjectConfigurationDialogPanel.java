package com.accenture.assets.ui.dialogs.panels;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Image;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.BoxLayout;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.filechooser.FileSystemView;

import org.apache.commons.lang.StringUtils;
import org.jdesktop.application.Application;

import com.accenture.assets.beans.Project;
import com.accenture.assets.helpers.JComboBoxProvider;
import com.accenture.assets.tree.CustomRender;
import com.accenture.assets.ui.forms.MainFrame;
import com.accenture.assets.utils.SaveXML;


/**
* This code was edited or generated using CloudGarden's Jigloo
* SWT/Swing GUI Builder, which is free for non-commercial
* use. If Jigloo is being used commercially (ie, by a corporation,
* company or business for any purpose whatever) then you
* should purchase a license for each developer using Jigloo.
* Please visit www.cloudgarden.com for details.
* Use of Jigloo implies acceptance of these licensing terms.
* A COMMERCIAL LICENSE HAS NOT BEEN PURCHASED FOR
* THIS MACHINE, SO JIGLOO OR THIS CODE CANNOT BE USED
* LEGALLY FOR ANY CORPORATE OR COMMERCIAL PURPOSE.
*/
public class NewProjectConfigurationDialogPanel extends JPanel {

	private static final Dimension LABEL_SIZE = new Dimension(100, 20);
	private static final Dimension INPUT_SIZE = new Dimension(150, 20);
	private static final Dimension BUTTON_SIZE = new Dimension(80, 20);

	/**	 */
	private static final long serialVersionUID = 1L;

	// Owner

	private Window owner;

	// COMPONENTS
	
	/** Nombre Proyecto LABEL */
	private JLabel projectNameLabel;
	
	/** Nombre Proyecto Input */
	private JTextField projectNameInput;

	/** Group ID Label */
	private JLabel groupIdLabel;

	/** Group ID Input */
	private JTextField groupIdInput;

	/** Artifact ID Label */
	private JLabel artifactIdLabel;
	private JButton pathButton;

	/** Artifact ID Input */
	private JTextField artifactIdInput;

	/** Version ID Label */
	private JLabel versionLabel;
	
	/** Path ID Label */
	private JLabel pathLabel;

	/** Version ID Input */
	private JTextField versionInput;

	//nn
	/** Version ID Input */
	private JTextField pathInput;
	
	//nn
	/** Proyect new Proyect */
	private Project project = new Project();
	
	/** Folder Root Label */
	//private JLabel folderRootLabel;

	/** Folder Root Input */
	//private JTextField folderRootDisplay;

	/** Technology Label */
	private JLabel technologyLabel;

	/** Technology Combo */
	private JComboBox technologyCombo;

	/** Browse Button */
	private JButton browseButton;

	/** Boton cancelar */
	private JButton cancelButton;

	/** Boton Crear */
	private JButton createButton;

	// Panels
	
	/** Panel Nombre Proyecto */ 
	private JPanel projectNamePanel;
	
	/** Panel Group ID */
	private JPanel groupIdPanel;

	/** Panel Artifact ID */
	private JPanel artifactIdPanel;

	/** Panel Version */
	private JPanel versionPanel;
	
	//nn
	/** Panel Version */
	private JPanel pathPanel;

	/** Panel Root */
	private JPanel rootPanel;

	/** Technology Panel */
	private JPanel technologyPanel;

	/** Panel Botones */
	private JPanel buttonsPanel;

	/** Panel final */
	private JPanel finalPanel;

	public NewProjectConfigurationDialogPanel(Window owner) {
		this.owner = owner;
		init();
	}

	/**
	 * 
	 */
	private void init() {
		add(getFinalPanel());
	}

	/**
	 * 
	 * @return
	 */
	private JLabel getProjectNameLabel(){
		if(projectNameLabel == null){
			projectNameLabel = new JLabel ("Project name:");
			projectNameLabel.setPreferredSize(LABEL_SIZE);
			
		}
		return projectNameLabel;
	}
	
	
	/**
	 * 
	 * @return
	 */
	private JLabel getGroupIdLabel() {
		if (groupIdLabel == null) {
			groupIdLabel = new JLabel("Group ID:");
			groupIdLabel.setPreferredSize(LABEL_SIZE);
		}
		return groupIdLabel;
	}

	
	/**
	 * 
	 * @return
	 */
	private JTextField getProjectNameInput() {
		if (projectNameInput == null) {
			projectNameInput = new JTextField();
			projectNameInput.setName("projectNameInput");
			projectNameInput.setPreferredSize(INPUT_SIZE);
		}
		return projectNameInput;
	}
	
	
	/**
	 * 
	 * @return
	 */
	private JTextField getGroupIdInput() {
		if (groupIdInput == null) {
			groupIdInput = new JTextField();
			groupIdInput.setPreferredSize(INPUT_SIZE);
			groupIdInput.setName("groupIdInput");
		}
		return groupIdInput;
	}

	/**
	 * 
	 * @return
	 */
	private JLabel getArtifactIdLabel() {
		if (artifactIdLabel == null) {
			artifactIdLabel = new JLabel("Artifact ID:");
			artifactIdLabel.setPreferredSize(LABEL_SIZE);
		}
		return artifactIdLabel;
	}

	/**
	 * 
	 * @return
	 */
	private JTextField getArtifactIdInput() {
		if (artifactIdInput == null) {
			artifactIdInput = new JTextField();
			artifactIdInput.setName("artifactIdInput");
			artifactIdInput.setPreferredSize(INPUT_SIZE);
		}
		return artifactIdInput;
	}

	/**
	 * 
	 * @return
	 */
	private JLabel getVersionLabel() {
		if (versionLabel == null) {
			versionLabel = new JLabel("Version:");
			versionLabel.setPreferredSize(LABEL_SIZE);
		}
		return versionLabel;
	}
	
	private JLabel getPathLabel() {
		if (pathLabel == null) {
			pathLabel = new JLabel("Path");
			pathLabel.setPreferredSize(LABEL_SIZE);
		}
		return pathLabel;
	}

	/**
	 * 
	 * @return
	 */
	private JTextField getVersionInput() {
		if (versionInput == null) {
			versionInput = new JTextField();
			versionInput.setPreferredSize(INPUT_SIZE);
			versionInput.setName("versionInput");
		}
		return versionInput;
	}
	
	//nn
	private JTextField getPathInput() {
		if (pathInput == null) {
			pathInput = new JTextField();
			pathInput.setPreferredSize(new java.awt.Dimension(110, 20));
			pathInput.setEnabled(false);
			pathInput.setName("pathInput");
		}
		return pathInput;
	}

	/**
	 * 
	 * @return
	 */
//	private JLabel getFolderRootLabel() {
//		if (folderRootLabel == null) {
//			folderRootLabel = new JLabel("Directorio:");
//			folderRootLabel.setPreferredSize(LABEL_SIZE);
//		}
//		return folderRootLabel;
//	}

	/**
	 * 
	 * @return
	 */
//	private JTextField getFolderRootDisplay() {
//		if (folderRootDisplay == null) {
//			folderRootDisplay = new JTextField();
//			folderRootDisplay.setPreferredSize(INPUT_SIZE);
//			folderRootDisplay.setEditable(false);
//		}
//		return folderRootDisplay;
//	}

	/**
	 * 
	 * @return
	 */
//	private JButton getBrowseButton() {
//		if (browseButton == null) {
//			browseButton = new JButton("Examinar");
//			browseButton.setPreferredSize(BUTTON_SIZE);
//			browseButton.addActionListener(new ShowDirectoryChooserActionListener(owner, getFolderRootDisplay()));
//		}
//		return browseButton;
//	}

	/**
	 * 
	 * @return
	 */
	private JLabel getTechnologyLabel() {
		if (technologyLabel == null) {
			technologyLabel = new JLabel("Technology:");
			technologyLabel.setPreferredSize(LABEL_SIZE);
		}
		return technologyLabel;
	}

	/**
	 * 
	 * @return
	 */
	private JComboBox getTechnologyCombo() {
		if (technologyCombo == null) {
			technologyCombo = JComboBoxProvider.getTechnologies();
			technologyCombo.setName("technologyCombo");
			technologyCombo.setPreferredSize(INPUT_SIZE);
		}
		return technologyCombo;
	}

	/**
	 * 
	 * @return
	 */
	private JButton getCancelButton() {
		if (cancelButton == null) {
			cancelButton = new JButton("Cancel");
			createButton.setName("cancelButton");			
			cancelButton.setPreferredSize(BUTTON_SIZE);
			cancelButton.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					//nn
					MainFrame.getInstancia().setCancel(true);
					owner.dispose();
				}
			});
		}
		return cancelButton;
	}


	/**
	 * 
	 * @return
	 */
	private JButton getCreateButton() {
		if (createButton == null) {
			createButton = new JButton("Save");
			createButton.setPreferredSize(BUTTON_SIZE);
			createButton.setName("createButton");
			createButton.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					if (!hasErrors()) {
						//Marcelo-INICIO
//						Project project = (Project) ContextHelper.getBean(ContextHelper.PROJECT_CONFIGURATION);
						//Project project = new Project();
						//Marceclo-FIN
						
						project.setName(projectNameInput.getText());
						project.setArtifactId(artifactIdInput.getText());
						project.setGroupId(groupIdInput.getText());
						project.setVersion(versionInput.getText());
						//project.setLocWorkSpace(folderRootDisplay.getText());
						project.setTechnology((String) technologyCombo.getSelectedItem());
						
						//nn
						project.setLocWorkSpace(project.getArchivo().getParent().toString() + "\\" + project.getName());
						
						//Marcelo-INICIO
						MainFrame.getInstancia().setProject(project);
						//Marcelo-FIN
						
						//nn
						SaveXML saveXML = new SaveXML();
						saveXML.saveData(project);
						
						owner.dispose();

					}
				}
			});
		}
		return createButton;
	}

	// Panels
	
	/**
	 * 
	 */
	private JPanel getProjectNamePanel(){
		if(projectNamePanel==null){
			projectNamePanel = new JPanel (new FlowLayout (FlowLayout.LEFT));
			projectNamePanel.add(getProjectNameLabel());
			projectNamePanel.add(getProjectNameInput());
			projectNamePanel.setName("projectNamePanel");
		}
		return projectNamePanel;
	}
	
	/**
	 * 
	 * @return
	 */
	private JPanel getGroupIdPanel() {
		if (groupIdPanel == null) {
			groupIdPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
			groupIdPanel.add(getGroupIdLabel());
			groupIdPanel.add(getGroupIdInput());
			groupIdPanel.setName("groupIdPanel");
		}
		return groupIdPanel;
	}

	/**
	 * 
	 * @return
	 */
	private JPanel getArtifactIdPanel() {
		if (artifactIdPanel == null) {
			artifactIdPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
			artifactIdPanel.add(getArtifactIdLabel());
			artifactIdPanel.add(getArtifactIdInput());
			artifactIdPanel.setName("artifactIdPanel");
		}
		return artifactIdPanel;
	}

	/**
	 * 
	 * @return
	 */
	private JPanel getVersionPanel() {
		if (versionPanel == null) {
			versionPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
			versionPanel.add(getVersionLabel());
			versionPanel.add(getVersionInput());
			versionPanel.setName("versionPanel");			
		}
		return versionPanel;
	}
	
	
	//nn
	private JPanel getPathPanel() {
		if (pathPanel == null) {
			pathPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
			pathPanel.setName("pathPanel");
			pathPanel.add(getPathLabel());
			pathPanel.add(getPathInput());
			pathPanel.add(getPathButton());
		}
		return pathPanel;
	}

//	/**
//	 * 
//	 * @return
//	 */
//	private JPanel getRootPanel() {
//		if (rootPanel == null) {
//			rootPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
//			rootPanel.add(getFolderRootLabel());
//			rootPanel.add(getFolderRootDisplay());
//			rootPanel.add(getBrowseButton());
//		}
//		return rootPanel;
//	}

	/**
	 * 
	 * @return
	 */
	private JPanel getTechnologyPanel() {
		if (technologyPanel == null) {
			technologyPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
			technologyPanel.add(getTechnologyLabel());
			technologyPanel.add(getTechnologyCombo());
			technologyPanel.setName("technologyPanel");
		}
		return technologyPanel;
	}

	/**
	 * 
	 * @return
	 */
	private JPanel getButtonsPanel() {
		if (buttonsPanel == null) {
			buttonsPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
			buttonsPanel.setName("buttonsPanel");			
		}
		buttonsPanel.add(this.getCreateButton());
		buttonsPanel.add(this.getCancelButton());
		return buttonsPanel;
	}

	/**
	 * 
	 * @return
	 */
	private JPanel getFinalPanel() {
		if (finalPanel == null) {
			finalPanel = new JPanel();
			finalPanel.setLayout(new BoxLayout(finalPanel, BoxLayout.Y_AXIS));
			finalPanel.add(getProjectNamePanel());
			finalPanel.add(getGroupIdPanel());
			finalPanel.add(getArtifactIdPanel());
			
			finalPanel.add(getVersionPanel());
			
			finalPanel.add(getPathPanel());
			
			//finalPanel.add(getRootPanel());
			finalPanel.add(getTechnologyPanel());	
			finalPanel.add(getButtonsPanel());
			finalPanel.setName("finalPanel");
		}
		return finalPanel;
	}

	public boolean hasErrors() {
		StringBuffer message = new StringBuffer();
		boolean hasErrors = false;
		
		if (StringUtils.isEmpty(projectNameInput.getText())) {
			hasErrors = true;
//			message.append("El campo Nombre Proyecto es requerido. \n");
			message.append("The field Project Name is required. \n");
		}
		
		if (StringUtils.isEmpty(groupIdInput.getText())) {
			hasErrors = true;
//			message.append("El campo Group Id es requerido. \n");
			message.append("The field Group Id is required. \n");
		}
		if (StringUtils.isEmpty(artifactIdInput.getText())) {
			hasErrors = true;
//			message.append("El campo Artifact Id es requerido. \n");
			message.append("The field Artifact ID is required. \n");
		}
		if (StringUtils.isEmpty(versionInput.getText())) {
			hasErrors = true;
//			message.append("El campo Version es requerido. \n");
			message.append("The field Version is required. \n");
		}
//		if (StringUtils.isEmpty(folderRootDisplay.getText())) {
//			hasErrors = true;
//			message.append("El campo Directorio es requerido. \n");
//		}
		if (technologyCombo.getSelectedIndex() == 0) {
			hasErrors = true;
//			message.append("El campo Tecnología es requerido. \n");
			message.append("The field Technology is required. \n");
		}
				
//		File f = new File(pathInput.getText());
//		if(!f.canRead() && !f.canWrite() && !f.isFile()){						
//			hasErrors = true;
//			message.append("El campo Path es requerido y debe tener permisos (Read/Write). \n");
//		}
		
		if (hasErrors) {
			JOptionPane.showMessageDialog(new JFrame(), message, "Errors", JOptionPane.ERROR_MESSAGE);
		}
		return hasErrors;
	}
	
	private JButton getPathButton() {
		if(pathButton == null) {
			pathButton = new JButton();
			pathButton.setName("pathButton");
			pathButton.setPreferredSize(new java.awt.Dimension(34, 20));		
//			pathButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/open.png")));
			pathButton.setIcon(new ImageIcon(NewProjectConfigurationDialogPanel.class.getResource("/images/open.png")));
			
			pathButton.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					SaveProjectDialogPanel saveD = new SaveProjectDialogPanel(owner);
					pathInput.setText(saveD.getArchivo().getPath());
					project.setArchivo(saveD.getArchivo());
				}
			});
		}
		return pathButton;
	}
	
	private void initGUI() {
		try {
			Application.getInstance().getContext().getResourceMap(getClass()).injectComponents(this);
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
}
