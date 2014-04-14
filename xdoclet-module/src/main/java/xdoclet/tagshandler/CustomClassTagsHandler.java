package xdoclet.tagshandler;

import java.util.Properties;

import xdoclet.XDocletException;
import xdoclet.util.XDocletUtil;

/**
 * 
 * @author matias.j.navarro
 *
 */
public class CustomClassTagsHandler extends ClassTagsHandler{
	
	/**
	 * Return the current class name
	 * @param attributes: asFieldName, spaced, capitalize, capitalize
	 * @return
	 * @throws XDocletException
	 */
	public String className(Properties attributes) throws XDocletException{
		return XDocletUtil.format(getCurrentClass().getName(), attributes);
    }
	/**
	 * Evaluates body if the current class has a superclass
	 * @param template
	 * @param attributes
	 * @throws XDocletException
	 */
	public void ifHasSuperClass(String template, Properties attributes) throws XDocletException{
		if(getCurrentClass().getSuperclass()!=null){
			generate(template);
		}
    }
	/**
	 * Return the class name of the superclass of the current class 
	 * @param attributes: asFieldName, spaced, capitalize, capitalize
	 * @return
	 * @throws XDocletException
	 */
	public String superClassName(Properties attributes) throws XDocletException{
		return XDocletUtil.format(getCurrentClass().getSuperclass().getName(), attributes);
    }
	
}
