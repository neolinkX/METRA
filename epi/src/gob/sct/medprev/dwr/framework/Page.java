/**
 * 
 */
package gob.sct.medprev.dwr.framework;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;

public class Page extends GenericResponse implements Serializable {

	public Page(List elements, int firstRecord, int totalElements) {
		this(elements, firstRecord, 15, totalElements);
	}

	/**
	 * @deprecated Method Page is deprecated
	 */

	@Deprecated
	public Page(List elements, long firstRecord, int totalElements) {
		this(elements, firstRecord, 15, totalElements);
	}

	/**
	 * @deprecated Method Page is deprecated
	 */

	@Deprecated
	public Page(List elements, long first, int aPageSize, long total) {
		this(elements, (int) first, aPageSize, (int) total);
	}

	public Page(List elements, int first, int aPageSize, int total) {
		pageSize = 15;
		totalElements = -1;
		hasNextPage = false;
		pageFirstElementNumber = first;
		pageSize = aPageSize;
		totalElements = total;
		if (elements == null)
			this.elements = Collections.emptyList();
		else
			this.elements = elements;
		if (pageFirstElementNumber < 0)
			pageFirstElementNumber = 0;
		if (pageSize < 0)
			pageSize = 15;
		sanityCheck();
		if (pageFirstElementNumber == 0)
			pageNumber = 0;
		else
			pageNumber = pageFirstElementNumber / pageSize;
		if (totalElements < 0) {
			numberOfPages = -1;
		} else {
			numberOfPages = totalElements / pageSize;
			numberOfPages += totalElements % pageSize != 0 ? 1 : 0;
		}
	}

	public boolean isHasNextPage() {
		if (getTotal() < 0)
			return hasNextPage;
		else
			return getPageNumber() < getNumberOfPages() - 1;
	}

	public void setHasNextPage(boolean flag) {
		hasNextPage = true;
	}

	public boolean isHasPreviousPage() {
		return getPageNumber() > 0;
	}

	public List getElements() {
		return elements;
	}

	public int getTotal() {
		return totalElements;
	}

	/**
	 * @deprecated Method getTotalNumberOfElements is deprecated
	 */

	@Deprecated
	public long getTotalNumberOfElements() {
		return (long) totalElements;
	}

	public int getFirstElementIndex() {
		return pageFirstElementNumber;
	}

	/**
	 * @deprecated Method getPageFirstElementNumber is deprecated
	 */

	@Deprecated
	public long getPageFirstElementNumber() {
		return (long) pageFirstElementNumber;
	}

	public int getLastElementIndex() {
		return (getFirstElementIndex() + getElements().size()) - 1;
	}

	/**
	 * @deprecated Method getPageLastElementNumber is deprecated
	 */

	@Deprecated
	public long getPageLastElementNumber() {
		return (getPageFirstElementNumber() + (long) getElements().size()) - 1L;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getPageNumber() {
		return pageNumber;
	}

	public int getNumberOfPages() {
		return numberOfPages;
	}

	public void setNumberOfPages(int numberOfPages) {
		this.numberOfPages = numberOfPages;
	}

	private void sanityCheck() {
		if (totalElements == 0 && elements != null && elements.size() != 0)
			throw new RuntimeException(
					"The total number of elements cannot be 0 if the page contains elements");
		if ((elements == null) & (totalElements > 0))
			throw new RuntimeException(
					"There cannot be an empty page if total number of elements is not 0");
		if (elements != null && totalElements > 0
				&& totalElements < elements.size())
			throw new RuntimeException(
					"Total number of elements cannot be less than the number of elements in the page.");
		if (elements != null && pageSize < elements.size())
			throw new RuntimeException(
					"The page size cannot be less than the number of elements of the page!");
		if (elements != null && elements.size() == 0 && totalElements > 0)
			throw new RuntimeException(
					"There cannot be an empty page if total number of elements > 0");
		else
			return;
	}

	public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append("Total elements=").append(
				getTotal() < 0 ? "Unkown" : ((Object) (Integer
						.valueOf(getTotal()))));
		sb.append("\nPage number=").append(getPageNumber());
		sb.append("\nPage size =").append(getPageSize());
		sb.append("\nActual page size=").append(getElements().size());
		sb.append("\nFirst element on page =").append(getFirstElementIndex());
		sb.append("\nLast element on page =").append(getLastElementIndex());
		sb.append("\nNumber of pages=").append(
				getNumberOfPages() < 0 ? "Unkown" : ((Object) (Integer
						.valueOf(getNumberOfPages()))));
		sb.append("\nHas next page=").append(isHasNextPage());
		return sb.toString();
	}

	public static final int DEFAULT_PAGE_SIZE = 15;
	private static final long serialVersionUID = 1L;
	private List elements;
	private int pageSize;
	private int pageNumber;
	private int pageFirstElementNumber;
	private int totalElements;
	private int numberOfPages;
	private boolean hasNextPage;
}
