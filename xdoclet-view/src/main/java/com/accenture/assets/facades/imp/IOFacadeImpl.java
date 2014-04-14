package com.accenture.assets.facades.imp;

import java.io.File;
import java.util.List;

import com.accenture.assets.facades.IOFacade;
import com.thoughtworks.xstream.XStream;

public class IOFacadeImpl implements IOFacade {
	
	private String dataTypePath;
	
	private XStream dataTypeXstream;
	
	private List dataTypeList;
	
	public IOFacadeImpl(){
		init();
	}

	private void init() {
		
	}
	
	private void parseDataTypeList(){
		dataTypeXstream = new XStream();
		dataTypeList = (List) dataTypeXstream.fromXML(new File(dataTypePath));
	}

	public String getDataTypePath() {
		return dataTypePath;
	}

	public void setDataTypePath(String dataTypePath) {
		this.dataTypePath = dataTypePath;
	}

	public List getDataTypeList() {
		return dataTypeList;
	}

	public void setDataTypeList(List dataTypeList) {
		this.dataTypeList = dataTypeList;
	}
	
	
}
