package com.accenture.assets.utils;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Text;

public class XML {

	private Document document;

	// raiz del archivo XML
	// Element raiz = document.createElement("appGenerator"); // creamos
	// el elemento raiz
	private Element rRaiz;// = document.getDocumentElement(); // creamos el
							// elemento raiz
	// nombre proyecto
	private Element eProjectName;// = document.createElement("project");
	// Group ID
	private Element eGroupID;// = document.createElement("groupId"); // creamos el
	// elemento GrupoID Artifact ID
	private Element eArtifactID;// = document.createElement("artifactID"); // creamos
	// el
	// elemento
	// Artifact
	// Verion
	private Element eVersion;// = document.createElement("version"); // creamos el
	// elemento
	// Version
	// Tecnologia
	private Element eTecnologia;// = document.createElement("tecnologia"); // creamos
	// el
	// elemento
	// Tecnologia

	// Codigo, separamos por CLASES
	private Element rClasses;// = document.createElement("classes");

	// Codigo, separamos por CLASE
	private Element rClass;// = document.createElement("class");

	// Codigo, separamos por ClassName
	private Element eClassName;// = document.createElement("className");

	// Codigo, separamos por ClassExtends
	private Element eClassExtends;// = document.createElement("classExtends");

	// Codigo, separamos por ClassImplements
	private Element rClassImplements;// = document.createElement("implements");

	// Codigo, separamos por ClassImplement
	private Element eClassImplement;// = document.createElement("classImplement");

	// Codigo, separamos por ClassAttibutes
	private Element rClassAttributes;// = document.createElement("attributes");

	//Codigo, separamos por ClassAttibutesOfAttibures
	private Element rClassAttributesOfAttibutes;
	
	// Codigo, separamos por ClassAttibute
	private Element rClassAttribute;// = document.createElement("attribute");

	// Codigo, separamos por ClassAttibuteName
	private Element eClassAttributeName;// = document.createElement("attributeName");

	// Codigo, separamos por ClassAttibuteType
	private Element eClassAttributeType;// = document.createElement("attributeType");

	// Codigo, separamos por ClassAttibuteID
	private Element eClassAttributeId;// = document.createElement("id");

	// Codigo, separamos por ClassAttibuteDetail
	private Element eClassAttributeDetail;// = document.createElement("detail");

	// Codigo, separamos por ClassAttibuteSearch
	private Element eClassAttributeSearch;// = document.createElement("search");

	// Codigo, separamos por ClassAttibuteGrid
	private Element eClassAttributeGrid;// = document.createElement("grid");

	// Codigo, separamos por ClassAttibuteCreate
	private Element eClassAttributeCreate;// = document.createElement("create");

	// Codigo, separamos por ClassAttibuteModify
	private Element eClassAttributeModify;// = document.createElement("modify");

	// Codigo, separamos por ClassAttibuteDelete
	private Element eClassAttributeDelete;// = document.createElement("delete");
	
	//*** Codigo para los GENERIC
	private Element multi;
	private Element eClassGGenericType; //@genericType
	private Element eClassGCustomType; //@customType
	private Element eClassGIdCustomType; //@idCustomType
	private Element eClassGViewFiledCustomType;//@viewFieldCustomType
	
	//Fin Codigo para los GENERIC

	// Codigo, separamos por Interfaces
	private Element rInterfaces;// = document.createElement("interfaces");

	// Codigo, separamos por Interface
	private Element rInterface;// = document.createElement("interface");

	// Codigo, separamos por InterfaceName
	private Element eInterfaceName;// = document.createElement("interfaceName");

	// Codigo, separamos por InterfaceExtend
	private Element eInterfaceExtend;// = document.createElement("interfaceExtend");

	// Codigo, separamos por InterfaceMethods
	private Element eInterfaceMethods;// = document.createElement("methods");

	// Codigo, separamos por InterfaceMethod
	private Element eInterfaceMethod;// = document.createElement("method");

	// Codigo, separamos por InterfaceMethodName
	private Element eInterfaceMethodName;// = document.createElement("methodName");

	// Codigo, separamos por InterfaceMethodType
	private Element eInterfaceMethodType;// = document.createElement("methodType");

//	// Codigo, separamos por Packages
//	private Element rPackages;// = document.createElement("packages");
//
//	// Codigo, separamos por Package
//	private Element rPackage;// = document.createElement("package");
//
//	// Codigo, separamos por PackageName
//	private Element ePackageName;// = document.createElement("packageName");

	public XML(Document doc) {
		document = doc;
		rRaiz = document.getDocumentElement();
		eProjectName = document.createElement("project");
		eGroupID = document.createElement("groupId"); // creamos el
		// elemento GrupoID Artifact ID
		eArtifactID = document.createElement("artifactID"); // creamos
		// el elemento Artifact Verion
		eVersion = document.createElement("version"); // creamos el
		// elemento Version Tecnologia
		eTecnologia = document.createElement("tecnologia"); // creamos
		// el elemento Tecnologia

		// Codigo, separamos por CLASES
		//rClasses = document.createElement("classes");

		// Codigo, separamos por CLASE
		//rClass = document.createElement("class");

		// Codigo, separamos por ClassName
		//eClassName = document.createElement("className");

		// Codigo, separamos por ClassExtends
		//eClassExtends = document.createElement("classExtends");

		// Codigo, separamos por ClassImplements
		//rClassImplements = document.createElement("implements");

		// Codigo, separamos por ClassImplement
		//eClassImplement = document.createElement("classImplement");

		// Codigo, separamos por ClassAttibutes
		//rClassAttributes = document.createElement("attributes");

		// Codigo, separamos por ClassAttibute
		//rClassAttribute = document.createElement("attribute");

		// Codigo, separamos por ClassAttibuteName
		//eClassAttributeName = document.createElement("attributeName");

		// Codigo, separamos por ClassAttibuteType
		//eClassAttributeType = document.createElement("attributeType");

		// Codigo, separamos por ClassAttibuteID
		//eClassAttributeId = document.createElement("id");

		// Codigo, separamos por ClassAttibuteDetail
		//eClassAttributeDetail = document.createElement("detail");

		// Codigo, separamos por ClassAttibuteSearch
		//eClassAttributeSearch = document.createElement("search");

		// Codigo, separamos por ClassAttibuteGrid
		//eClassAttributeGrid = document.createElement("grid");

		// Codigo, separamos por ClassAttibuteCreate
		//eClassAttributeCreate = document.createElement("create");

		// Codigo, separamos por ClassAttibuteModify
		//eClassAttributeModify = document.createElement("modify");

		// Codigo, separamos por ClassAttibuteDelete
		//eClassAttributeDelete = document.createElement("delete");

		// Codigo, separamos por Interfaces
		//rInterfaces = document.createElement("interfaces");

		// Codigo, separamos por Interface
		//rInterface = document.createElement("interface");

		// Codigo, separamos por InterfaceName
		//eInterfaceName = document.createElement("interfaceName");

		// Codigo, separamos por InterfaceExtend
		//eInterfaceExtend = document.createElement("interfaceExtend");

		// Codigo, separamos por InterfaceMethods
		//eInterfaceMethods = document.createElement("methods");

		// Codigo, separamos por InterfaceMethod
		//eInterfaceMethod = document.createElement("method");

		// Codigo, separamos por InterfaceMethodName
		//eInterfaceMethodName = document.createElement("methodName");

		// Codigo, separamos por InterfaceMethodType
		//eInterfaceMethodType = document.createElement("methodType");

		// Codigo, separamos por Packages
		//rPackages = document.createElement("packages");

		// Codigo, separamos por Package
		//rPackage = document.createElement("package");

		// Codigo, separamos por PackageName
		//ePackageName = document.createElement("packageName");
	}

	public Document getDocument() {
		return document;
	}

	public void setDocument(Document document) {
		this.document = document;
	}

	public Element getrRaiz() {
		return rRaiz;
	}

	public Element geteProjectName() {
		return eProjectName;
	}

	public Element geteGroupID() {
		return eGroupID;
	}

	public Element geteArtifactID() {
		return eArtifactID;
	}

	public Element geteVersion() {
		return eVersion;
	}

	public Element geteTecnologia() {
		return eTecnologia;
	}

	public Element getrClasses() {
		return rClasses;
	}

	public Element getrClass() {
		return rClass;
	}

	public Element geteClassName() {
		return eClassName;
	}

	public Element geteClassExtends() {
		return eClassExtends;
	}

	public Element getrClassImplements() {
		return rClassImplements;
	}

	public Element geteClassImplement() {
		return eClassImplement;
	}

	public Element getrClassAttributes() {
		return rClassAttributes;
	}
	
	public Element getrClassAttriburesOfAttributes(){
		return rClassAttributesOfAttibutes;
	}

	public Element getrClassAttribute() {
		return rClassAttribute;
	}

	public Element geteClassAttributeName() {
		return eClassAttributeName;
	}

	public Element geteClassAttributeType() {
		return eClassAttributeType;
	}

	public Element geteClassAttributeId() {
		return eClassAttributeId;
	}

	public Element geteClassAttributeDetail() {
		return eClassAttributeDetail;
	}

	public Element geteClassAttributeSearch() {
		return eClassAttributeSearch;
	}

	public Element geteClassAttributeGrid() {
		return eClassAttributeGrid;
	}

	public Element geteClassAttributeCreate() {
		return eClassAttributeCreate;
	}

	public Element geteClassAttributeModify() {
		return eClassAttributeModify;
	}

	public Element geteClassAttributeDelete() {
		return eClassAttributeDelete;
	}

	
	public Element getMulti() {
		return multi;
	}

	public void setMulti(Element multi) {
		this.multi = multi;
	}

	public Element geteClassGGenericType() {
		return eClassGGenericType;
	}

	public Element geteClassGcustomType() {
		return eClassGCustomType;
	}

	public Element geteClassGIdCustomType() {
		return eClassGIdCustomType;
	}

	public Element geteClassGViewFiledCustomType() {
		return eClassGViewFiledCustomType;
	}

	public Element getrInterfaces() {
		return rInterfaces;
	}

	public Element getrInterface() {
		return rInterface;
	}

	public Element geteInterfaceName() {
		return eInterfaceName;
	}

	public Element geteInterfaceExtend() {
		return eInterfaceExtend;
	}

	public Element geteInterfaceMethods() {
		return eInterfaceMethods;
	}

	public Element geteInterfaceMethod() {
		return eInterfaceMethod;
	}

	public Element geteInterfaceMethodName() {
		return eInterfaceMethodName;
	}

	public Element geteInterfaceMethodType() {
		return eInterfaceMethodType;
	}

//	public Element getrPackages() {
//		return rPackages;
//	}
//
//	public Element getrPackage() {
//		return rPackage;
//	}
//
//	public Element getePackageName() {
//		return ePackageName;
//	}

	// private void seteRaiz(Element element) {
	// eRaiz.appendChild(element);
	// }

	public void seteProjectName(Text text) {
		eProjectName.appendChild(text);
		rRaiz.appendChild(eProjectName);
	}

	public void seteGroupID(Text text) {

		eGroupID.appendChild(text);
		rRaiz.appendChild(eGroupID);
	}

	public void seteArtifactID(Text text) {
		eArtifactID.appendChild(text);
		rRaiz.appendChild(eArtifactID);
	}

	public void seteVersion(Text text) {
		eVersion.appendChild(text);
		rRaiz.appendChild(eVersion);
	}

	public void seteTecnologia(Text text) {
		eTecnologia.appendChild(text);
		rRaiz.appendChild(eTecnologia);
	}

	public void setrClasses(Element e) {
		rClasses=document.createElement("classes");
		e.appendChild(rClasses);
	}

	public void setrClass(Element e) {
		rClass=document.createElement("class");
		e.appendChild(rClass);
	}

	public void seteClassName(Text text,Element e) {
		eClassName = document.createElement("className");
		eClassName.appendChild(text);
		e.appendChild(eClassName);
	}

	public void seteClassExtends(Text text,Element e) {
		eClassExtends=document.createElement("classExtends");
		eClassExtends.appendChild(text);
		e.appendChild(eClassExtends);
	}

	public void setrClassImplements(Element e) {
		rClassImplements=document.createElement("implements");
		e.appendChild(rClassImplements);
	}

	public void seteClassImplement(Text text,Element e) {
		eClassImplement=document.createElement("classImplement");
		eClassImplement.appendChild(text);
		e.appendChild(eClassImplement);
	}

	public void setrClassAttributes(Element e) {
		rClassAttributes = document.createElement("attributes");
		e.appendChild(rClassAttributes);
	}
	
	public void setrClassAttributesOfAttributes(Element e){
		rClassAttributesOfAttibutes = document.createElement("attributesOfAttribures");
		e.appendChild(rClassAttributesOfAttibutes);
	}

	public void setrClassAttribute(Element e) {
		rClassAttribute=document.createElement("attribute");
		e.appendChild(rClassAttribute);
	}

	public void seteClassAttributeName(Text text,Element e) {
		eClassAttributeName=document.createElement("attributeName");
		eClassAttributeName.appendChild(text);
		e.appendChild(eClassAttributeName);
	}

	public void seteClassAttributeType(Text text,Element e) {
		eClassAttributeType=document.createElement("attributeType");
		eClassAttributeType.appendChild(text);
		e.appendChild(eClassAttributeType);
	}

	public void seteClassAttributeId(Text text,Element e) {
		eClassAttributeId=document.createElement("id");
		eClassAttributeId.appendChild(text);
		e.appendChild(eClassAttributeId);
	}

	public void seteClassAttributeDetail(Text text,Element e) {
		eClassAttributeDetail=document.createElement("detail");
		eClassAttributeDetail.appendChild(text);
		e.appendChild(eClassAttributeDetail);
	}

	public void seteClassAttributeSearch(Text text,Element e) {
		eClassAttributeSearch=document.createElement("search");
		eClassAttributeSearch.appendChild(text);
		e.appendChild(eClassAttributeSearch);
	}

	public void seteClassAttributeGrid(Text text,Element e) {
		eClassAttributeGrid=document.createElement("grid");
		eClassAttributeGrid.appendChild(text);
		e.appendChild(eClassAttributeGrid);
	}

	public void seteClassAttributeCreate(Text text,Element e) {
		eClassAttributeCreate=document.createElement("create");
		eClassAttributeCreate.appendChild(text);
		e.appendChild(eClassAttributeCreate);
	}

	public void seteClassAttributeModify(Text text,Element e) {
		eClassAttributeModify=document.createElement("modify");
		eClassAttributeModify.appendChild(text);
		e.appendChild(eClassAttributeModify);
	}

	public void seteClassAttributeDelete(Text text,Element e) {
		eClassAttributeDelete= document.createElement("delete");
		eClassAttributeDelete.appendChild(text);
		e.appendChild(eClassAttributeDelete);
	}
	
		
	public void seteClassAttributeMulti(Text text,Element e) {
		multi=document.createElement("multi");
		multi.appendChild(text);
		e.appendChild(multi);
	}
	
	
	public void seteClassGGenericType(Text text, Element e){
		eClassGGenericType = document.createElement("genericType");
		eClassGGenericType.appendChild(text);
		e.appendChild(eClassGGenericType);
	}
	
	public void seteClassGCustomType(Text text, Element e){
		eClassGCustomType = document.createElement("customType");
		eClassGCustomType.appendChild(text);
		e.appendChild(eClassGCustomType);
	}
	public void seteClassGIdCustomType(Text text, Element e){
		eClassGIdCustomType = document.createElement("idCustomType");
		eClassGIdCustomType.appendChild(text);
		e.appendChild(eClassGIdCustomType);
	}
	
	public void seteClassGViewFieldCustomType(Text text, Element e){
		eClassGViewFiledCustomType = document.createElement("viewFieldCustomType");
		eClassGViewFiledCustomType.appendChild(text);
		e.appendChild(eClassGViewFiledCustomType);
	}
	
	public void setrInterfaces(Element e) {
		rInterfaces=document.createElement("interfaces");
		e.appendChild(rInterfaces);
	}

	public void setrInterface(Element e) {
		rInterface=document.createElement("interface");
		e.appendChild(rInterface);
	}

	public void seteInterfaceName(Text text,Element e) {
		eInterfaceName=document.createElement("interfaceName");
		eInterfaceName.appendChild(text);
		e.appendChild(eInterfaceName);
	}

	public void seteInterfaceExtend(Text text,Element e) {
		eInterfaceExtend=document.createElement("interfaceExtend");
		eInterfaceExtend.appendChild(text);
		e.appendChild(eInterfaceExtend);
	}

	public void seteInterfaceMethods(Text text,Element e) {
		eInterfaceMethods=document.createElement("methods");
		eInterfaceMethods.appendChild(text);
		e.appendChild(eInterfaceMethods);
	}

	public void seteInterfaceMethod(Text text,Element e) {
		eInterfaceMethod=document.createElement("method");
		eInterfaceMethod.appendChild(text);
		e.appendChild(eInterfaceMethod);
	}

	public void seteInterfaceMethodName(Text text,Element e) {
		eInterfaceMethodName=document.createElement("methodName");
		eInterfaceMethodName.appendChild(text);
		e.appendChild(eInterfaceMethodName);
	}

	public void seteInterfaceMethodType(Text text,Element e) {
		eInterfaceMethodType=document.createElement("methodType");
		eInterfaceMethodType.appendChild(text);
		e.appendChild(eInterfaceMethodType);
	}

//	public void setrPackages(Element e) {
//		rPackages=document.createElement("packages");
//		e.appendChild(rPackages);
//	}
//
//	public void setrPackage(Element e) {
//		rPackage=document.createElement("package");
//		e.appendChild(rPackage);
//	}
//
//	public void setePackageName(Text text,Element e) {
//		ePackageName=document.createElement("packageName");
//		ePackageName.appendChild(text);
//		e.appendChild(ePackageName);
//	}

}
