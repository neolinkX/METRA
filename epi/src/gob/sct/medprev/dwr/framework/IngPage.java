/**
 * 
 */
package gob.sct.medprev.dwr.framework;

import java.util.List;

/**
 * @author croblesm
 * 
 */
public class IngPage extends Object {

	public static Page getPage(List lista, int start, int limit) {
		Page page = null;

		if (limit >= lista.size()) {
			page = new Page(lista, 0, limit, lista.size());
			page.setPageSize(limit);
		} else {
			if ((limit + start) > lista.size()) {
				limit = lista.size();
			} else if (start != 0) {
				limit = start + limit;
			}
			page = new Page(lista.subList(start, limit), 0, limit, lista.size());
			page.setPageSize(limit);
		}

		return page;
	}
}
