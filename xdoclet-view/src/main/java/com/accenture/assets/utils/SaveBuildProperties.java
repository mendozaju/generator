package com.accenture.assets.utils;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

import com.accenture.assets.beans.Project;

public class SaveBuildProperties {
	
	public void generaBuildProperties( Project project){
		grabar(project);
	}
	
	
	
	private void grabar( Project project){
		FileWriter fstream;
		try {
			fstream = new FileWriter("build.properties");
		
			BufferedWriter bWriter = new BufferedWriter(fstream);

		bWriter.write("# build properties");
		bWriter.newLine();
		bWriter.write("groupId=" + project.getGroupId());
		bWriter.newLine();
		bWriter.write("artifactId=" + project.getArtifactId());
		bWriter.newLine();
		bWriter.write("version=" + project.getVersion());
		bWriter.newLine();
		bWriter.write("nombreProyecto=" + project.getName());
		bWriter.newLine();
		//bWriter.write("directorioP=" + project.getLocWorkSpace());
		bWriter.write("directorioP=" + project.getArchivo().getParent().replace("\\", "/"));
		bWriter.newLine();
				
		
		bWriter.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		
	}

}
