package com.pbn.oss.adaptor.eoc.dongyan;

 

/**
* Copyright (c) 2011,  All rights reserved
* <p>Xml2Entity
* <p> 
*   
* </p>
* @author   Zhijun Yu
* @version  1.00 2011/05/15    
*/

import java.io.File;
import java.net.MalformedURLException;
import java.util.Iterator;
import java.util.List;
import java.util.ArrayList;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import org.snmp4j.smi.OID;

public class Xml2Entity {

	public Document read(String fileName) throws MalformedURLException,
			DocumentException {
		SAXReader reader = new SAXReader();
		Document document = reader.read(new File(fileName));
		return document;
	}

	public Element getRootElement(Document doc) {
		return doc.getRootElement();
	}

	public List<ClassDefine> getEntities(Document doc) {
		List<ClassDefine> returnvalue = new ArrayList<ClassDefine> ();
		encapsulateXml(doc,"table",returnvalue);
		encapsulateXml(doc,"subtable",returnvalue);
		return returnvalue;
	}
	
	public void encapsulateXml(Document doc,String tableProperty,List<ClassDefine> returnvalue){
		List<?> tables = doc.selectNodes("//tables/"+tableProperty);
		for (Iterator<?> iterTables = tables.iterator(); iterTables.hasNext();) {
			Object obj = iterTables.next();
			if (!Element.class.isInstance(obj)) {
				continue;
			}
			Element table = (Element)obj;
			String name = table.elementText("name");
			String operation = table.elementText("operation");
			String depoid = null;
			if(tableProperty.equalsIgnoreCase("subtable")){
				depoid = table.elementText("depoid");
			}

			List<OID2Property> entities = new ArrayList<OID2Property>();
			for (Iterator<?> iter = table.elementIterator("column"); iter.hasNext();) {
				Element column = (Element) iter.next();
				String oid = column.elementText("oid");
				String propertyName = column.elementText("propertyName");
				String type = column.elementText("type");
				//String processClass = column.elementText("processClass");
				String processClass = "";
				OID oidtemp = new OID(oid);
				OID2Property oid2property = new OID2Property(oidtemp,
						propertyName, type, processClass);
				entities.add(oid2property);
			}
			returnvalue.add(new ClassDefine(name, operation, entities,tableProperty,depoid));
		}
	}
	
	
	
	public List<String> getTables(Document doc) {
		List<String> returnvalue = new ArrayList<String> ();
		List<?> tables = doc.selectNodes("//tables/table");
		for (Iterator<?> iterTables = tables.iterator(); iterTables.hasNext();) {
			Object obj = iterTables.next();
			if (!Element.class.isInstance(obj)) {
				continue;
			}
			Element table = (Element)obj;
			String name = table.elementText("name");
			returnvalue.add(name);
		}
		
		return returnvalue;
	}

	public List<ClassDefine> getEntities(String filename) throws Exception {
		Xml2Entity xml2entity = new Xml2Entity();
		Document doc = xml2entity.read(filename);
		return xml2entity.getEntities(doc);
	}
	
	public String getPojoPath(Document doc) {
		Element root = doc.getRootElement();
		return root.elementText("pojofilepath");
	}

	public static void main(String[] args) throws Exception {
		Xml2Entity test = new Xml2Entity();
		Document doc = test.read("g:\\test.xml");
		test.getEntities(doc);
	}
}
