package com.accenture.assets.main;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import com.accenture.assets.beans.Project;
import com.accenture.assets.helpers.ContextHelper;
import com.accenture.assets.services.ProjectService;
import com.accenture.assets.xdoclet.build.BuildNames;

/**
 * @author adrian.musante
 *
 */
public class ServiceMain {

	
	public static ProjectService projectService = (ProjectService) ContextHelper.getBean("projectService");
	
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Project g = new Project(); 
		g.setLocInputSources(new File("C://input").getPath());
		g.setLocWorkSpace(new File("C://work-test1").getPath());
		g.setGroupId("com.accenture.assets.example");
		g.setArtifactId("AppGen");
		g.setVersion("9.9");
		
		projectService.perform(g, true);
		
//		Other usages: alternative use of the service		
//		Project g2 = new Project(); 
//		g2.setLocInputSources(new File("C://input").getPath());
//		g2.setLocWorkSpace(new File("C://work-test2").getPath());
//		g2.setGroupId("com.accenture.assets.example1");
//		g2.setArtifactId("ppep");
//		g2.setVersion("9.9");
//		
//		projectService.perform(g2, false);
//		
//		Map<String, String> properties = new HashMap<String, String>();
//		properties.put(BuildNames.LOC_INPUT_SOURCES,g2.getLocInputSources());
//		properties.put(BuildNames.LOC_WORKSPACE,  g2.getLocWorkSpace());
//		properties.put(BuildNames.MVN_GROUP_ID, g2.getGroupId());
//		properties.put(BuildNames.MVN_ARTIFACT_ID, g2.getArtifactId());
//		properties.put(BuildNames.MVN_VERSION, g2.getVersion());
//		
//		projectService.perform(properties, BuildNames.TARGET_GENERATE, BuildNames.TARGET_MAVEN);
		
		System.out.println("Ok");
	}

}
