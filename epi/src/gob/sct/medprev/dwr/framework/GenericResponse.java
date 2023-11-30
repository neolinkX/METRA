/**
 * 
 */
package gob.sct.medprev.dwr.framework;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @author croblesm
 * 
 */
public class GenericResponse implements Serializable {

	public static class FieldMessage implements Serializable {

		public String getId() {
			return id;
		}

		public void setId(String id) {
			this.id = id;
		}

		public String getMsg() {
			return msg;
		}

		public void setMsg(String msg) {
			this.msg = msg;
		}

		private static final long serialVersionUID = 1L;
		private String id;
		private String msg;

		public FieldMessage(String id, String msg) {
			this.id = id;
			this.msg = msg;
		}
	}

	public GenericResponse() {
		success = true;
		errors = new ArrayList();
		messages = new ArrayList();
	}

	public GenericResponse(boolean success, Object data, String messages[]) {
		this.success = true;
		errors = new ArrayList();
		this.messages = new ArrayList();
		setSuccess(success);
		setData(data);
		if (messages != null) {
			String as[];
			int j = (as = messages).length;
			for (int i = 0; i < j; i++) {
				String message = as[i];
				addMessage(message);
			}

		}
	}

	public GenericResponse(boolean success, String messages[]) {
		this(success, null, messages);
	}

	public GenericResponse(String messages[]) {
		this(true, null, messages);
	}

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public List getErrors() {
		return errors;
	}

	public List getMessages() {
		return messages;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	public void addMessage(String msg) {
		messages.add(msg);
	}

	public void addFieldMessage(String id, String msg) {
		errors.add(new FieldMessage(id, msg));
		setSuccess(false);
	}

	private static final long serialVersionUID = 1L;
	private boolean success;
	private List errors;
	private List messages;
	private Object data;

}
