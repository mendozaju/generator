package xdoclet.tagshandler;

import java.util.HashMap;
import java.util.Iterator;
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
import xjavadoc.XTag;

/**
 * 
 * @author matias.j.navarro
 *
 */
public class CustomFieldTagsHandler extends FieldTagsHandler{
	
	private static final String CLASS_BINARY="xjavadoc.BinaryClass";
	private static final String CLASS_PRIMITIVE="xjavadoc.Primitive";
	
	private XClass headerRootClass;
	private String headerFieldName;
	private String headerSubfieldName;
	private boolean isLastSubfield=true;
	
	 public String fieldType() throws XDocletException{
		 
		 return getFieldTypeFor(getCurrentField()) + XDocletUtil.formatGenericName(fieldGenericType());
	 }
	
	/**
	 * Iterates over header definitions
	 * If no tagFilter specified iterates over all filters
	 * @param template
	 * @param attributes: tagFilter, commaSeparated
	 * @throws XDocletException
	 */
	public void forAllHeaderFields(String template, Properties attributes) throws XDocletException{
		headerRootClass=getCurrentClass();
		boolean commaSeparated = TypeConversionUtil.stringToBoolean(attributes.getProperty(XDocletUtil.PROPERTY_COMMA_SEPARATED), Boolean.FALSE);
		Map<String, XField> fields = getHeaderFields(attributes);
		for(Iterator<Entry<String, XField>> i=fields.entrySet().iterator(); i.hasNext(); ){
			Entry<String, XField> entry=i.next();
			headerFieldName=entry.getKey();
			setCurrentField(entry.getValue());
			
			if((commaSeparated)&&(i.hasNext())){
        		StringBuilder stringToRender=new StringBuilder(template);
            	stringToRender.append(XDocletUtil.CHARACTER_COMMA);
            	generate(stringToRender.toString());
        	}else{
        		generate(template);
        	}
        	setCurrentField(entry.getValue());
		}
		setCurrentClass(headerRootClass);
	}
	/**
	 * Returns the root class name for a header fields iteration 
	 * @param attributes: asFieldName, spaced, capitalize, capitalize
	 * @return
	 * @throws XDocletException
	 */
	public String headerRootClassName(Properties attributes) throws XDocletException{
        return XDocletUtil.format(headerRootClass.getName(), attributes);
    }	
	/**
	 * Returns the current header field name, example x.y.z 
	 * @param attributes: asFieldName, spaced, capitalize, capitalize
	 * @return
	 * @throws XDocletException
	 */
	public String headerFieldName(Properties attributes) throws XDocletException{
		return XDocletUtil.format(headerFieldName, attributes);
    }
	/**
	 * Iterates over all subfields of a header field 
	 * @param template
	 * @param attributes
	 * @throws XDocletException
	 */
	public void forAllHeaderFieldsSubFields(String template, Properties attributes) throws XDocletException{
		setCurrentClass(headerRootClass);
		StringTokenizer subFieldStringTokenizer=new StringTokenizer(headerFieldName,XDocletUtil.CHARACTER_POINT);
		while(subFieldStringTokenizer.hasMoreTokens()){
			headerSubfieldName=subFieldStringTokenizer.nextToken();
			setCurrentField(XDocletUtil.getField(getCurrentClass(), headerSubfieldName));
        	if(subFieldStringTokenizer.hasMoreElements()){
        		isLastSubfield=false;
        	}else{
        		isLastSubfield=true;
        	}
        	generate(template);
        	setCurrentClass(getCurrentField().getType());
		}
	}
	/**
	 * Returns the current header field name, example y 
	 * @param attributes: asFieldName, spaced, capitalize, capitalize
	 * @return
	 * @throws XDocletException
	 */	
	public String headerSubfieldName(Properties attributes) throws XDocletException{
        return XDocletUtil.format(headerSubfieldName, attributes);
    }
	/**
	 * Condition over lastSubfield
	 * @param template
	 * @param attributes: condition
	 * @throws XDocletException
	 */
	public void ifIsLastSubfield(String template, Properties attributes) throws XDocletException{
		boolean condition = TypeConversionUtil.stringToBoolean(attributes.getProperty(XDocletUtil.PROPERTY_CONDITION), Boolean.TRUE);
		if(condition==isLastSubfield){
			generate(template);
		}
	}
	/**
	 * Evaluates the body if the current field is of the type specified 
	 * @param template
	 * @param attributes: type
	 * @throws XDocletException
	 */
	public void ifIsOfType(String template, Properties attributes) throws XDocletException{
		
		boolean condition = TypeConversionUtil.stringToBoolean(attributes.getProperty(XDocletUtil.PROPERTY_CONDITION), Boolean.TRUE);
		boolean isTypeOk  = Boolean.FALSE;
		
		String types=attributes.getProperty(XDocletUtil.PROPERTY_TYPE);
		if (types == null) {
			throw new XDocletException("type == null!!!");
		}
		StringTokenizer typesStringTokenizer=new StringTokenizer(types.replaceAll(XDocletUtil.CHARACTER_SPACE, XDocletUtil.CHARACTER_EMPTY), XDocletUtil.CHARACTER_COMMA);
		while(typesStringTokenizer.hasMoreTokens() && !isTypeOk){
			String type=typesStringTokenizer.nextToken();
			
			if(getCurrentField().getType().getQualifiedName().equals(type)){
				isTypeOk = Boolean.TRUE;				
			}
		}	
		
	
		if(condition == isTypeOk){
			generate(template);
		}

	}
	/**
	 * Evaluates the body if the current field is a source class
	 * @param template
	 * @param attributes
	 * @throws XDocletException
	 */
	public void ifIsSourceClass(String template, Properties attributes) throws XDocletException{
		if (getCurrentField().getType().getClass()==xjavadoc.SourceClass.class) {
			generate(template);
		}
	}
	/**
	 * Evaluates the body if the current field is a binary class
	 * @param template
	 * @param attributes
	 * @throws XDocletException
	 */
	public void ifIsBinaryClass(String template, Properties attributes) throws XDocletException{
		if (getCurrentField().getType().getClass().getName().equals(CLASS_BINARY)) {
			generate(template);
		}
	}
	/**
	 * Evaluates the body if the current field is a primitive class
	 * @param template
	 * @param attributes
	 * @throws XDocletException
	 */
	public void ifIsPrimitive(String template, Properties attributes) throws XDocletException{
		if (getCurrentField().getType().getClass().getName().equals(CLASS_PRIMITIVE)) {
			generate(template);
		}
	}
	/**
	 * Returns a map of field declarations and XField object
	 * The fields are selected from tag assigned to the tagFilter attribute
	 * If no tagFilter specified returns all tag values
	 * @param attributes
	 * @return
	 * @throws XDocletException
	 */
	private  Map<String, XField> getHeaderFields(Properties attributes) throws XDocletException{
		Map<String, XField> fieldsHash=new HashMap<String, XField>();
		Set<String> tagFields=XDocletUtil.getTagFilterFields(getCurrentClass(), attributes);
		for(String fieldName : tagFields){
        	if(fieldName.contains(XDocletUtil.CHARACTER_POINT)){
        		XField subField=null;
        		XClass subClass=getCurrentClass();
        		StringTokenizer subFieldStringTokenizer=new StringTokenizer(fieldName, XDocletUtil.CHARACTER_POINT);
        		while(subFieldStringTokenizer.hasMoreTokens()){
                	String subFieldName=subFieldStringTokenizer.nextToken();
                	subField=XDocletUtil.getField(subClass, subFieldName);
                	if (subField.getType().getClass()==xjavadoc.SourceClass.class) {
                		subClass=subField.getType();
            		}else{
            			break;
            		}
        		}
        		fieldsHash.put(fieldName, subField);
        	}else{
        		fieldsHash.put(fieldName, XDocletUtil.getField(getCurrentClass(), fieldName));
        	}
		}
		return fieldsHash;
	}
	
	/**
	 * obtains the generic type of a list
	 * 
	 * @return
	 * @throws XDocletException
	 */
	public String fieldGenericType() throws XDocletException{
		 
		String genericType = null;
		
		XTag tag = getCurrentField().getDoc().getTag(XDocletUtil.PARAMETER_GENERIC_TYPE);
		
		if(tag!=null){
			boolean isCustomType = new Boolean(getCurrentField().getDoc().getTag(XDocletUtil.PARAMETER_CUSTOM_TYPE).getValue());
			
			if(isCustomType){
				StringBuilder builder = new StringBuilder();
				builder.append(getDocletContext().getProperties().get(XDocletUtil.CONTEXT_PROPERTY_ROOT_PACKAGE));
				builder.append(XDocletUtil.CHARACTER_POINT);
				builder.append(XDocletUtil.NAME_SUBPACKAGE_BEAN_JAVA);
				builder.append(XDocletUtil.CHARACTER_POINT);
				builder.append(tag.getValue());
				
				genericType = builder.toString();
			}else{
				genericType = tag.getValue();
			}
		}
		
		
		return genericType;
    }
	
	/**
	 * checks if the entity supported by the list corresponds with custom type or not
	 * 
	 * @param template
	 * @throws XDocletException
	 */
	
	public void ifIsCustomType(String template, Properties attributes) throws XDocletException{
		
		boolean condition = TypeConversionUtil.stringToBoolean(attributes.getProperty(XDocletUtil.PROPERTY_CONDITION), Boolean.TRUE);
		
		boolean isCustomType = new Boolean(getCurrentField().getDoc().getTag(XDocletUtil.PARAMETER_CUSTOM_TYPE).getValue());
		
		if(isCustomType == condition){
			generate(template);
		}
		
	}
	
	
	/**
	 * gets the field id of an custom type list, this field will be used in the backend of the application 
	 * @return
	 * @throws XDocletException
	 */
	
	public String getIdCustomType() throws XDocletException{
		 
		XTag tag = getCurrentField().getDoc().getTag(XDocletUtil.PARAMETER_ID_CUSTOM_TYPE);
		
		return (tag!=null)?tag.getValue():null;
    }
	
	/**
	 * gets the field name of an custom type list, this field will show in the view of the application
	 * 
	 * @return
	 * @throws XDocletException
	 */
	public String getFieldViewCustomType() throws XDocletException{

		XTag tag = getCurrentField().getDoc().getTag(XDocletUtil.PARAMETER_VIEW_FIELD_CUSTOM_TYPE);
		
		return (tag!=null)?tag.getValue():null;
    }
	
	
	/** 
	 * gets the tag value of javadoc defined for the field iterated, the specific tag is defined in the "tagName" parameter
	 * 
	 * @see superclass method
	 */
	public String fieldTagValueCustom(Properties attributes) throws XDocletException{
        
		String propertyName = attributes.getProperty(XDocletUtil.PROPERTY_TAG_NAME);
		
		return XDocletUtil.format(getCurrentField().getDoc().getTag(propertyName).getValue(), attributes); 
		
    }
	
}

