package xdoclet.util;

import java.util.HashSet;
import java.util.Properties;
import java.util.Set;
import java.util.StringTokenizer;

import xdoclet.ConfigParamIntrospector;
import xdoclet.XDocletException;
import xjavadoc.XClass;
import xjavadoc.XField;
import xjavadoc.XTag;

/**
 * 
 * @author matias.j.navarro
 *
 */
public class XDocletUtil {
	
	public static final String PROPERTY_TAG_FILTER="tagFilter";
	public static final String PROPERTY_CONDITION="condition";
	public static final String PROPERTY_COMMA_SEPARATED="commaSeparated";
	public static final String PROPERTY_TYPE="type";
	
	public static final String PROPERTY_AS_FIELD_NAME="asFieldName";
	public static final String PROPERTY_SPACED="spaced";
	public static final String PROPERTY_CAPITALIZE="capitalize";
	public static final String PROPERTY_LOWER_CASE="lowerCase";
	public static final String PROPERTY_TAG_NAME="tagName";
	public static final String PROPERTY_UPPER_CASE="upperCase";
	
	public static final String CONTEXT_PROPERTY_ROOT_PACKAGE = "rootPackage"; 
	
	public static final String PARAMETER_GENERIC_TYPE 		 	 = "genericType";
	public static final String PARAMETER_CUSTOM_TYPE	     	 = "customType";
	public static final String PARAMETER_ID_CUSTOM_TYPE	     	 = "idCustomType";
	public static final String PARAMETER_VIEW_FIELD_CUSTOM_TYPE	 = "viewFieldCustomType";
	
	public static final String NAME_SUBPACKAGE_BEAN_JAVA 		 = "domain";
	
	private static final String TAG_ID="id";
	private static final String TAG_DETAIL="detail";
	private static final String TAG_SEARCH="search";
	private static final String TAG_GRID="grid";
	private static final String TAG_CREATE="create";
	private static final String TAG_MODIFY="modify";
	private static final String TAG_DELETE="delete";
	private static final String[] TAG_FILTER_VALUES=new String[]{TAG_ID, TAG_DETAIL, TAG_SEARCH, TAG_GRID, TAG_CREATE, TAG_CREATE, TAG_MODIFY, TAG_DELETE};

	public static final String CHARACTER_COMMA=",";
	public static final String CHARACTER_EMPTY="";
	public static final String CHARACTER_ESCAPED_POINT="\\.";
	public static final String CHARACTER_POINT=".";
	public static final String CHARACTER_SPACE=" ";
	public static final String CHARACTER_PARENTHESES_ANGULAR_LEFT  = "<";
	public static final String CHARACTER_PARENTHESES_ANGULAR_RIGHT = ">";
	
	
	/**
	 * Formats the input
	 * @param input
	 * @param attributes: asFieldName, spaced, capitalize, lowerCase
	 * @return
	 */
	public static String format(String input, Properties attributes){
		if(TypeConversionUtil.stringToBoolean(attributes.getProperty(PROPERTY_AS_FIELD_NAME), Boolean.FALSE)){
			input=XDocletUtil.formatTagValueToFieldName(input);
		}
		if(TypeConversionUtil.stringToBoolean(attributes.getProperty(PROPERTY_SPACED), Boolean.FALSE)){
			input=input.replaceAll(CHARACTER_ESCAPED_POINT, CHARACTER_SPACE);
		}
		if(TypeConversionUtil.stringToBoolean(attributes.getProperty(PROPERTY_CAPITALIZE), Boolean.FALSE)){
			input=ConfigParamIntrospector.capitalize(input);
		}
		if(TypeConversionUtil.stringToBoolean(attributes.getProperty(PROPERTY_LOWER_CASE), Boolean.FALSE)){
			input=input.toLowerCase();
		}
		if(TypeConversionUtil.stringToBoolean(attributes.getProperty(PROPERTY_UPPER_CASE), Boolean.FALSE)){
			input=input.toUpperCase();
		}
		return input;
	}
	
	/**
	 * Formats "field1.field2.field3" to "field1Field2Field3"
	 * @param input
	 * @return
	 */
	public static String formatTagValueToFieldName(String input){
		StringBuilder sb=null;
		StringTokenizer subFieldStringTokenizer=new StringTokenizer(input, CHARACTER_POINT);
		while(subFieldStringTokenizer.hasMoreTokens()){
			String field=subFieldStringTokenizer.nextToken();
			if(sb==null){
				sb=new StringBuilder(field);
			}else{
				sb.append(ConfigParamIntrospector.capitalize(field));
			}
		}
		return sb.toString();
	}
	/**
	 * Return the values assigned to the tags passed on the tagFilter attribute
	 * If no tagFilter specified returns all tag values
	 * @param xClass
	 * @param attributes
	 * @return
	 * @throws XDocletException
	 */
	public static Set<String> getTagFilterFields(XClass xClass, Properties attributes) throws XDocletException{
		Set<String> tagFields=new HashSet<String>();
		String tagFilter=attributes.getProperty(PROPERTY_TAG_FILTER);
		if(tagFilter!=null){
			tagFilter=tagFilter.replaceAll(CHARACTER_SPACE, CHARACTER_EMPTY);
        	StringTokenizer tagFilterStringTokenizer=new StringTokenizer(tagFilter, CHARACTER_COMMA);
        	while(tagFilterStringTokenizer.hasMoreTokens()){
        		tagFields.addAll(getTagValues(xClass, tagFilterStringTokenizer.nextToken()));
        	}
		}else{
			for(int i=0; i<TAG_FILTER_VALUES.length; i++){
        		attributes.setProperty(PROPERTY_TAG_FILTER, TAG_FILTER_VALUES[i]);
        		tagFields.addAll(getTagValues(xClass, TAG_FILTER_VALUES[i]));
        	}
		}
        return tagFields;
	}
	/**
	 * Returns the values assigned to the tag 
	 * @param xClass
	 * @param tagName: id, detail, search, grid, modify, create, delete
	 * @return
	 */
	private static Set<String> getTagValues(XClass xClass, String tagName){
		Set<String> tagValues=new HashSet<String>();
		XTag tag=null;
		if(tagName!=null){
			tag=xClass.getDoc().getTag(tagName);
		}
		if(tag!=null){
        	String tagValue=tag.getValue().replaceAll(CHARACTER_SPACE, CHARACTER_EMPTY);
        	StringTokenizer tagFieldStringTokenizer=new StringTokenizer(tagValue, CHARACTER_COMMA);
        	while(tagFieldStringTokenizer.hasMoreTokens()){
        		tagValues.add(tagFieldStringTokenizer.nextToken());
        	}
        }
		return tagValues;
	}
	/**
	 * Returns the field of the class evaluating superclasse
	 * @param xClass
	 * @param fieldName
	 * @return
	 */
	public static XField getField(XClass xClass, String fieldName){
		XField xField=xClass.getField(fieldName);
		if((xField==null)&&(xClass.getSuperclass()!=null)){
			return getField(xClass.getSuperclass(), fieldName);
		}
		return xField;
	}
	
	/**
	 * Formats the name of generic class with the next syntax : '<genericClass>'
	 * 
	 * @param genericClass
	 * @return
	 */
	
	public static String formatGenericName(String genericClass){
		
		if(genericClass!=null){
			StringBuilder builder = new StringBuilder();
			builder.append(CHARACTER_PARENTHESES_ANGULAR_LEFT);
			builder.append(genericClass);
			builder.append(CHARACTER_PARENTHESES_ANGULAR_RIGHT);
		
			return builder.toString();
		}
		
		return CHARACTER_EMPTY;
	}
	
}
