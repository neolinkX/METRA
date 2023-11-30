/**
 * 
 */
package gob.sct.medprev.dwr.framework;

import java.io.Serializable;

/**
 * @author admin
 * 
 */
public class PagedRequest implements Serializable {

	public PagedRequest() {
		dir = "ASC";
	}

	public int getStart() {
		return start;
	}

	/**
	 * @deprecated Method setStart is deprecated
	 */

	@Deprecated
	public void setStart(long firstRecord) {
		start = (int) firstRecord;
	}

	public void setStart(int firstRecord) {
		start = firstRecord;
	}

	public int getLimit() {
		return limit;
	}

	public void setLimit(int pageSize) {
		limit = pageSize;
	}

	public String getSort() {
		return sort;
	}

	public void setSort(String sort) {
		this.sort = sort;
	}

	public String getDir() {
		return dir;
	}

	public void setDir(String order) {
		dir = order;
	}

	public boolean isAscendingOrder() {
		return !"DESC".equals(dir);
	}

	private static final long serialVersionUID = 0xb1b3de01027c6f3cL;
	public static final String ORDER_ASC = "ASC";
	public static final String ORDER_DESC = "DESC";
	private int start;
	private int limit;
	private String sort;
	private String dir;

}
