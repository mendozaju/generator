/**
 *	@id id1, id2
 *	@detail name, firstName, lastName, password
 *	@search name, firstName, lastName
 *	@grid name, firstName, lastName
 *	@create name, firstName, lastName, password, list , list2
 *	@delete name, firstName, lastName, password
 *	@modify id1, id2, name, firstName, lastName, password, booleanField, byteField, characterField, doubleField, floatField, integerField, longField, shortField, stringField, bigDecimalField, calendarField, dateField, role.id, role.name, bytePrimitiveField, shortPrimitiveField, intPrimitiveField, longPrimitiveField, floatPrimitiveField, doublePrimitiveField, booleanPrimitiveField, charPrimitiveField, list, list2
 */
public class User{
	private String id1;
	private String id2;
	private String name;
	private String firstName;
	private String lastName;
	private String password;
	private Role role;
	
	
	private Boolean booleanField;
	private Byte byteField;
	private Character characterField;
	private Double doubleField;
	private Float floatField;
	private Integer integerField;
	private Long longField;
	private Short shortField;
	private String stringField;
	private java.math.BigDecimal bigDecimalField;
	private java.util.Calendar calendarField;
	private java.util.Date dateField;
	
	private byte bytePrimitiveField;
	private short shortPrimitiveField;
	private int intPrimitiveField;
	private long longPrimitiveField;
	private float floatPrimitiveField;
	private double doublePrimitiveField;
	private boolean booleanPrimitiveField;
	private char charPrimitiveField;
	
	/**
	 * @genericType Role
	 * @customType  true
	 * @idCustomType id
	 * @viewFieldCustomType name 
	 */
	private java.util.List list;
	
	/**
	 * @genericType java.lang.String
	 * @customType  false
	 */
	private java.util.List list2;
}
