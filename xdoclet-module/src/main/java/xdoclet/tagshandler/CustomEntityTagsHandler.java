package xdoclet.tagshandler;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;
import java.util.Set;
import java.util.StringTokenizer;

import xdoclet.XDocletException;
import xdoclet.util.TypeConversionUtil;
import xdoclet.util.XDocletUtil;
import xjavadoc.XClass;
import xjavadoc.XField;

/**
 * 
 * @author matias.j.navarro
 *
 */
public class CustomEntityTagsHandler extends FieldTagsHandler{

	private String headerEntity;
	private boolean isLastEntity=false;
	
	/**
	 * Iterates over all the entities declared on the class header filtered by tagFilter attribute
	 * @param template
	 * @param attributes
	 * @throws XDocletException
	 */
	public void forAllHeaderEntities(String template, Properties attributes) throws XDocletException{
		Map<String, XClass> entities=getHeaderFieldsEntities(attributes);
		for(Entry<String, XClass> entity : entities.entrySet()){
			headerEntity=entity.getKey();
			generate(template);
		}
	}
	/**
	 * Iterates over all field of the current entity
	 * @param template
	 * @param attributes
	 * @throws XDocletException
	 */
	public void forAllHeaderEntitiesFields(String template, Properties attributes) throws XDocletException{
		XClass startingClass=getCurrentClass();
		XField startingField=getCurrentField();		
		String[] subFields=headerEntity.split(XDocletUtil.CHARACTER_ESCAPED_POINT);
		for(int i=0; i<subFields.length; i++){
			if(i==(subFields.length-1)){
				isLastEntity=true;
			}else{
				isLastEntity=false;
			}
			setCurrentField(XDocletUtil.getField(getCurrentClass(), subFields[i]));
			generate(template);
			setCurrentClass(getCurrentField().getType());
		}
		setCurrentClass(startingClass);
		setCurrentField(startingField);
	}
	/**
	 * Evaluates body if is the last entity declaration field
	 * @param template
	 * @param attributes
	 * @throws XDocletException
	 */
	public void ifIsLastEntity(String template, Properties attributes) throws XDocletException{
		boolean condition = TypeConversionUtil.stringToBoolean(attributes.getProperty(XDocletUtil.PROPERTY_CONDITION), Boolean.TRUE);
		if(isLastEntity==condition){
			generate(template);
		}
	}
	/**
	 * Iterates over all the entity types declared on the class header filtered by tagFilter attribute
	 * @param template
	 * @param attributes
	 * @throws XDocletException
	 */
	public void forAllHeaderEntityTypes(String template, Properties attributes) throws XDocletException{
		Map<String, XClass> entities=getHeaderFieldsEntities(attributes);
		Set<XClass> entityTypes=new HashSet<XClass>();
		for(Entry<String, XClass> entity : entities.entrySet()){
			if(!entity.getValue().equals(getCurrentClass())){
				entityTypes.add(entity.getValue());
			}
		}
		XClass startingClass=getCurrentClass();
		for(XClass entity: entityTypes){
			setCurrentClass(entity);
			generate(template);
		}
		setCurrentClass(startingClass);
	}
	/**
	 * Returns a map of field declarations and XClass object referencing to the fields entities
	 * The fields are selected from tag assigned to the tagFilter attribute
	 * If no tagFilter specified returns all tag values
	 * @param attributes
	 * @return
	 * @throws XDocletException
	 */
	private  Map<String, XClass> getHeaderFieldsEntities(Properties attributes) throws XDocletException{
		Map<String, XClass> fieldsHash=new HashMap<String, XClass>();
		Set<String> tagFields=XDocletUtil.getTagFilterFields(getCurrentClass(), attributes);
		for(String fieldName : tagFields){
        	if(fieldName.contains(XDocletUtil.CHARACTER_POINT)){
        		//XField subField=null;
        		XClass subClass=getCurrentClass();
        		StringTokenizer subFieldStringTokenizer=new StringTokenizer(fieldName, XDocletUtil.CHARACTER_POINT);
        		StringBuilder sb=null;
        		while(subFieldStringTokenizer.hasMoreTokens()){
                	String subFieldName=subFieldStringTokenizer.nextToken();
                	if(subFieldStringTokenizer.hasMoreElements()){
                    	if(sb==null){
                    		sb=new StringBuilder();
                    	}else{
                    		sb.append(XDocletUtil.CHARACTER_POINT);
                    	}
                    	sb.append(subFieldName);
                    	subClass=XDocletUtil.getField(subClass, subFieldName).getType();
                    	fieldsHash.put(sb.toString(), subClass);
                    }
               	}
        	}
		}
		return fieldsHash;
	}
	
}

