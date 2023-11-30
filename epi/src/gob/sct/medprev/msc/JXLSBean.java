package gob.sct.medprev.msc;

import java.util.*;

/**
 * 
 * <p>
 * Title:
 * </p>
 * <p>
 * Description:
 * </p>
 * <p>
 * Copyright: Copyright (c) 2006
 * </p>
 * <p>
 * Company:
 * </p>
 * 
 * @author Rarael Alcocer Caldera
 * @version 1.0
 */
public class JXLSBean {

	private String nombre;
	private Object bean;
	private List list = new ArrayList();

	public JXLSBean(String nombre) {
		this.nombre = nombre;
	}

	public JXLSBean(String nombre, Object bean, List list) {
		this.nombre = nombre;
		this.bean = bean;
		this.list = list;
	}

	public void addBean(Object bean) {
		list.add(bean);
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public List getList() {
		return list;
	}

	public void setList(List list) {
		this.list = list;
	}

	public Object getBean() {
		return bean;
	}

	public void setBean(Object bean) {
		this.bean = bean;
	}
}
