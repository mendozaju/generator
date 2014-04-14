package com.accenture.assets.services;

import java.io.Serializable;
import java.util.Map;

import com.accenture.assets.beans.Project;

/**
 * Service interface for working with projects.
 * 
 * @author adrian.musante
 *
 */
public interface ProjectService extends Serializable {
	
	/**
	 * Execute tasks of the project.
	 * 
	 * @param properties
	 *            Map of properties to set in project.
	 * @param targets
	 *            Array with keys for execute in project. Remember it, the index
	 *            is very important for the execute of targets.
	 */
	public void perform(Map<String, String> properties, String... targets);

	/**
	 * Execute task for create a project from input data.
	 * 
	 * @param config
	 *            Object with parameters of configuration
	 * 
	 * @param compileWithMvn
	 *            <code>true</code>, if the project created should be compilated
	 *            with maven2. Otherwise, set in <code>false</code>.
	 */
	public void perform(Project config, boolean compileWithMvn);

}
