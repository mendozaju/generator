package com.accenture.assets.utils;

import java.io.BufferedWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.accenture.assets.beans.Attribute;
import com.accenture.assets.beans.Type;

public class FormatJava {

	public BufferedWriter fomatToJava(BufferedWriter bWirter, AttrClass attC, Map<String,Type> mapaVariable) {

		try {

			// package XXXXXXX;
			// bWirter.write(attC.getPaquete() + " " +
			// attC.getNombrePaquete()+attC.getPuntos());
			// bWirter.newLine();

			// /**
			bWirter.write(attC.getComentI());
			//
			bWirter.newLine();

			// Pongo todos los Annot
			formatAnnotations(bWirter, attC);

			// cierro el Annot
			bWirter.write(attC.getComentC());

			//
			bWirter.newLine();

			// armo el PUBLIC CLASS
			publicClass(bWirter, attC);
			// {
			bWirter.write(attC.getLlaveAb());
			bWirter.newLine();

			// Creo todas las variables da la forma PRIVATE TYPE NAME
			privateTypeName(bWirter, attC, mapaVariable);

			bWirter.newLine();
			bWirter.write(attC.getLlaveCe());
			// } cierro llave del Public

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return bWirter;

	}

	private BufferedWriter privateTypeName(BufferedWriter bW, AttrClass attC, Map<String,Type> mapaVariable) throws IOException {

		HashMap hMap = new HashMap();
		hMap = attC.getVariable();
		Iterator it = hMap.entrySet().iterator();
		while (it.hasNext()) {
			Map.Entry e = (Map.Entry) it.next();
			List<Attribute> tmpLista = new ArrayList<Attribute>();
			tmpLista = (List<Attribute>) e.getValue();
			while (null != tmpLista && !tmpLista.isEmpty()) {
				Attribute attribute = new Attribute();
				attribute = tmpLista.get(0);
				if (!attribute.getName().contains(".")) {
					// si tiene Generic, lo agregamos
					// if(null!=attribute.getGenericTypeValue()&&!attribute.getGenericTypeValue().equals("")){
					if (attribute.getMulti()) {
						formatGeneric(bW, attC, attribute, mapaVariable);
						bW.write(" " + attC.getPrivado() + " " + "java.util.List" + " " + attribute.getName() + ";");
					} else {
						bW.write(" " + attC.getPrivado() + " " + e.getKey() + " " + attribute.getName() + ";");
					}

					bW.newLine();
				}
				tmpLista.remove(0);
			}

		}
		return bW;
	}

	// public class XXX extends YYYY implements ZZZZ, AAAAA
	private BufferedWriter publicClass(BufferedWriter bW, AttrClass attC) throws IOException {

		// public class
		bW.write(attC.getPublCl() + " " + attC.getNombreClase());

		// extends
		if (null != attC.getExtendClase() && !("").equals(attC.getExtendClase())) {
			bW.write(" " + attC.getExtiende() + " " + attC.getExtendClase());
		}

		// implements
		if (null != attC.getImplementaLista() && !("").equals(attC.getImplementaLista())) {
			String implementa = armarLista(attC.getImplementaLista());
			bW.write(" " + implementa);
		}

		return bW;
	}

	private BufferedWriter formatGeneric(BufferedWriter bW, AttrClass attC, Attribute attribute, Map<String,Type> mapaVariable) throws IOException {
		// /**
		bW.write(attC.getComentI());
		//
		bW.newLine();

		/**
		 * @genericType Role
		 * @customType true
		 * @idCustomType id
		 * @viewFieldCustomType name
		 */
		// * @genericType XXXX
		// if (!attribute.getGenericTypeValue().equals("")) {
		if (!attribute.getType().getName().equals("")) {
			String tmp = " " + attC.getArrGeneric() + " " + attribute.getType().getName();
			bW.write(tmp);
			bW.newLine();
			
			//compruebo si es tipo GENE
			attribute.setMultiMapa(attribute.getMulti(), mapaVariable);
			tmp = " " + attC.getArrCustomType() + " " + new Boolean(attribute.isCustomTypeValue()).toString();
			bW.write(tmp);
			bW.newLine();

		}

		// pro

		// * @customType true/false
		// if (null!=attribute.isCustomTypeValue() &&
		// !attribute.getGenericTypeValue().equals("")) {
		// if (attribute.isCustomTypeValue()) {
		// String tmp = " " + attC.getArrCustomType() + " " + new
		// Boolean(attribute.isCustomTypeValue()).toString();
		// bW.write(tmp);
		// bW.newLine();
		// }
		// @idCustomType XXX
		if (!attribute.getIdCustomTypeValue().equals("")) {
			String tmp = " " + attC.getArrIdCustomType() + " " + attribute.getIdCustomTypeValue();
			bW.write(tmp);
			bW.newLine();
		}
		// @viewFieldCustomType XXXX
		if (!attribute.getViewFieldCustomTypeValue().equals("")) {
			String tmp = " " + attC.getArrViewFieldCustomType() + " " + attribute.getViewFieldCustomTypeValue();
			bW.write(tmp);
			bW.newLine();
		}
		// */
		bW.write(attC.getComentC());
		//
		bW.newLine();

		return bW;
	}

	private BufferedWriter formatAnnotations(BufferedWriter bW, AttrClass attC) throws IOException {

		// * @id etc,etc, etc
		String id = " " + attC.getArrID() + " " + armarLista(attC.getId());
		bW.write(id);
		bW.newLine();

		// * @detail
		String detail = " " + attC.getArrDetail() + " " + armarLista(attC.getDetail());
		bW.write(detail);
		bW.newLine();

		// Search
		String search = " " + attC.getArrSearch() + " " + armarLista(attC.getSearch());
		bW.write(search);
		bW.newLine();

		// grid
		String grid = " " + attC.getArrGrid() + " " + armarLista(attC.getGrid());
		bW.write(grid);
		bW.newLine();

		// create
		String create = " " + attC.getArrCreate() + " " + armarLista(attC.getCreate());
		bW.write(create);
		bW.newLine();

		// modify
		String modify = " " + attC.getArrModify() + " " + armarLista(attC.getModify());
		bW.write(modify);
		bW.newLine();

		// delete
		String delete = " " + attC.getArrDelete() + " " + armarLista(attC.getDelete());
		bW.write(delete);
		bW.newLine();

		return bW;
	}

	private String armarLista(java.util.List<String> lista) {
		String result = "";
		if (null != lista) {
			while (!lista.isEmpty()) {
				String tmp = lista.get(0);

				// * @X ""
				if (("").equals(result)) {
					result = tmp;
				}
				// * @X A,B
				else {

					result = result + ", " + tmp;
				}

				lista.remove(0);
			}
		}

		return result;

	}
}
