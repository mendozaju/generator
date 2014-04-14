package com.accenture.assets.xdoclet.build;

/**
 * Application Constants for handling the project in build.xml.
 * 
 * @author adrian.musante
 *
 */
public class BuildNames {
	
	private BuildNames(){}
	
	
	// Reference Directories
	// -----------------------------------------------------------------------
	
	public static final String LOC_INPUT_SOURCES = "srcFiles";
	
	public static final String LOC_WORKSPACE = "workspaceLoc";
	
	// Maven
	// -----------------------------------------------------------------------
	
	public static final String MVN_GROUP_ID = "groupId";
	
	public static final String MVN_ARTIFACT_ID = "artifactId";
	
	public static final String MVN_VERSION = "version";
	
	// Definitions
	// -----------------------------------------------------------------------
	
	public static final String RSC_XDOCLET_FILES = "/xdoclet-store";

	public static final String RSC_BUILD_XML = RSC_XDOCLET_FILES + "/build.xml";
	
	// Task: Target names
	// -----------------------------------------------------------------------
	
	public static final String TARGET_ALL = "all";

	public static final String TARGET_GENERATE = "generate";
	
	public static final String TARGET_MAVEN = "maven";

}
