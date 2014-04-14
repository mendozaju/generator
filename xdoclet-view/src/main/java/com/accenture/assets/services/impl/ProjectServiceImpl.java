package com.accenture.assets.services.impl;

import static com.accenture.assets.utils.FileUtils.getResource;
import static org.apache.commons.lang.StringUtils.isBlank;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Vector;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.apache.tools.ant.BuildException;
import org.apache.tools.ant.MagicNames;
import org.apache.tools.ant.Project;
import org.apache.tools.ant.ProjectHelper;

import com.accenture.assets.exceptions.AppRuntimeException;
import com.accenture.assets.services.ProjectService;
import com.accenture.assets.utils.FileUtils;
import com.accenture.assets.xdoclet.build.BuildNames;

/**
 * Service implementation for working with projects. Provides facilities for the
 * execution of tasks.
 * 
 * @author adrian.musante
 * 
 */
public class ProjectServiceImpl implements ProjectService {

	private static final long serialVersionUID = 7169017245904157032L;
	private static final Logger log = Logger.getLogger(ProjectServiceImpl.class);

	private static final URL BUILD_XML = getResource(BuildNames.RSC_BUILD_XML);
	private static final File BASEDIR =  createBaseDir();
	
	/**
	 * Initialize method.
	 */
	public void initialize(){
		log.info("Initialize class: "+ this.getClass().getName());
	}
	
	/* (non-Javadoc)
	 * @see com.accenture.assets.services.ProjectService#perform(java.util.Map, java.lang.String[])
	 */
	public void perform(Map<String, String> properties, String... targets) {
		Project project = prepareProject(properties);
		
		if (isNotNull(targets)) {
			project.executeTargets(new Vector<String>(Arrays.asList(targets)));
		}
	}

	/* (non-Javadoc)
	 * @see com.accenture.assets.services.ProjectService#perform(com.accenture.assets.config.GeneratorConfig, boolean)
	 */
	public void perform(com.accenture.assets.beans.Project config, boolean compileWithMvn) {
		Map<String, String> properties = new HashMap<String, String>();
		
		properties.put(BuildNames.LOC_INPUT_SOURCES,config.getLocInputSources());
		properties.put(BuildNames.LOC_WORKSPACE,  config.getLocWorkSpace());
		
		properties.put(BuildNames.MVN_GROUP_ID, config.getGroupId());
		properties.put(BuildNames.MVN_ARTIFACT_ID, config.getArtifactId());
		properties.put(BuildNames.MVN_VERSION, config.getVersion());
		
		Project project = prepareProject(properties);

		project.executeTarget(BuildNames.TARGET_GENERATE);

		if(compileWithMvn){
			project.executeTarget(BuildNames.TARGET_MAVEN);
		}
	}
	
	/**
	 * Instance and configure {@link Project} for execute tasks.
	 * 
	 * @param properties
	 *            Map of properties to set in project.
	 * @return a {@link Project} configured.
	 */
	private Project prepareProject(Map<String, String> properties){
		Project project = newProject();
		
		if(isNotNull(properties)){
			for(Entry<String, String> param : properties.entrySet()){
				project.setProperty(param.getKey(), checkParam(param.getValue()));
			}
		}
		
		bindHelper(project);
		
		return project;
	}
	
	/**
	 * Create and configure for a new Ant project.
	 * 
	 * @throws BuildException
	 *             if the configuration is invalid or cannot be read
	 */
	private Project newProject() throws BuildException{
		Project project = new Project();
		
		log.debug("Load build.xml file: "+ BUILD_XML.toExternalForm());
		project.setUserProperty(MagicNames.ANT_FILE, BUILD_XML.getPath());
		project.setBaseDir(BASEDIR);
		project.init();
		
		log.debug("Instance of Project: SUCCESSFUL. Ref: " + project.toString());
		
		return project;
	}

	/**
	 * Bind to helper project.
	 * 
	 * @param project The project for the resulting ProjectHelper to configure.
     *                Must not be <code>null</code>.
	 */
	private void bindHelper(Project project){
		ProjectHelper helper = ProjectHelper.getProjectHelper();
		project.addReference(MagicNames.REFID_PROJECT_HELPER, helper);
		helper.parse(project, BUILD_XML);
	}
	
	/**
	 * Creates a BaseDir for the project work.
	 * 
	 * @return {@link File}
	 * 
	 * @see FileUtils#createTempDirectory(boolean)
	 * 
	 */
	private static File createBaseDir() {
		File baseDir = null;
		try{
			baseDir = FileUtils.createTempDirectory(true);
			URL urlFiles = getResource(BuildNames.RSC_XDOCLET_FILES);
			log.debug("Directory of Xdoclet files: " + urlFiles.toExternalForm());
			FileUtils.copyResourcesRecursively(urlFiles, baseDir);
		} catch (SecurityException e) {
			throw new AppRuntimeException(
					"User can not permit the temporal directory to be created.",
					e);
		} catch (IOException e) {
			throw new AppRuntimeException("Failed to create a basedir", e);
		}
		
		log.debug("Temporal directory path: "+ baseDir.getAbsolutePath());
		
		
		return baseDir;
	}
	
	/**
	 * Checks if a String is whitespace, empty ("") or null.
	 * 
	 * @param value
	 *            the String to check
	 * @return value with {@link String#trim()}
	 * 
	 * @see StringUtils#isBlank(String)
	 * 
	 * @throws IllegalArgumentException
	 *             If <i>value</i> is whitespace, empty ("") or null.
	 * 
	 */
	private String checkParam(String value) throws IllegalArgumentException{
		if (isBlank(value.toString())){
			throw new IllegalArgumentException("Empty parameter.");
		}
		
		return value.trim();
	}
	
	/**
	 * Determine whether the given object is not <code>null</code>.
	 * 
	 * @param array
	 *            the object to check
	 * @return <code>true</code>, If the object is not null. Otherwise is
	 *         <code>false</code>.
	 */
	private boolean isNotNull(Object obj){
		return obj != null;
	}
	
	/**
	 * Determine whether the given array is not <code>null</code> or
	 * <code>empty</code>.
	 * 
	 * @param array
	 *            the array to check
	 * @return <code>true</code>, If the array is not null. Otherwise is
	 *         <code>false</code>.
	 */
	private boolean isNotNull(Object[] array) {
		return (array != null && array.length > 0);
	}
}
