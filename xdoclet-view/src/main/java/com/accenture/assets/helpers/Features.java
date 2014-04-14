package com.accenture.assets.helpers;

import static com.accenture.assets.utils.StreamUtils.getResource;

import java.io.InputStream;
import java.io.Serializable;
import java.util.Map;

import org.apache.log4j.Logger;

import com.accenture.assets.beans.Type;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

/**
 * @author adrian.musante
 *
 */
public class Features implements Serializable {
	
	private static final long serialVersionUID = -5113476719774883585L;
	private static final Logger log = Logger.getLogger(Features.class);
	
	private static final String PATH = "/xstream/";
	private static final String ENCODE = "UTF-8";
	
	Map<String, String> technologies;
	Map<String, Type> types;
	//Marcelo-Inicio
	Map<String,String> reservedWords;
	//Marcelo-Fin

	/**
	 * Initialize method.
	 */
	public void initialize(){
		log.info("Initialize class: "+ this.getClass().getName());
		
		loadTechnologies();
		loadTypes();
		//Marcelo-INICIO
		loadReservedWords();
		//Marcelo-FIN
	}
	
	// Load Methods
	// -----------------------------------------------------------------------
	
	@SuppressWarnings("unchecked")
	public void loadTechnologies(){
		XStream parser = new XStream(new DomDriver(ENCODE));
		InputStream in = getResource(PATH + "technologyComboBox.xml");
		setTechnologies((Map<String,String>) parser.fromXML(in));
	}
	
	@SuppressWarnings("unchecked")
	public void loadTypes(){
		XStream parser = new XStream(new DomDriver(ENCODE));
		parser.alias("type", Type.class);
		parser.aliasField("name", Type.class, "name");
		InputStream in = getResource(PATH + "types.xml");
		setTypes((Map<String,Type>) parser.fromXML(in));
	}
	
	//Marcelo-INICIO
	public void loadReservedWords(){
		XStream parser = new XStream(new DomDriver(ENCODE));
		InputStream in = getResource(PATH + "reservedWords.xml");
		setReservedWords((Map<String,String>) parser.fromXML(in));
	}
	//Marcelo-FIN
	// Access attributes
	// -----------------------------------------------------------------------
	
	/**
	 * 
	 */
	public Map<String, String> getTechnologies() {
		return technologies;
	}

	/**
	 * 
	 * @param technologies
	 */
	private void setTechnologies(Map<String, String> technologies) {
		this.technologies = technologies;
	}
	//Marcelo-INICIO
	private void setReservedWords(Map<String, String> reservedWords) {
		this.reservedWords = reservedWords;
	}
	
	public Map<String,String> getReservedWords() {
		return reservedWords;
	}
	//Marcelo-FIN
	/**
	 * 
	 * @return
	 */
	public Map<String, Type> getTypes() {
		return types;
	}
	
	/**
	 * 
	 * @param types
	 */
	private void setTypes(Map<String, Type> types) {
		this.types = types;
	}
	
}
