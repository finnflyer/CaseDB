/**
 * 
 */
package com.lenovo.ittools.ctd.common;

import java.util.List;

/**
 * @author Kylin Zhang 2011-11-14
 * @description , Save pagination info
 * 
 */

@SuppressWarnings("unchecked")
public class PageBean {

	private List list; // Current page record

	private int allRow;
	private int totalPage;
	private int currentPage;
	private int pageSize;

	
	@SuppressWarnings("unused")
	private boolean isFirstPage;
	@SuppressWarnings("unused")
	private boolean isLastPage;
	@SuppressWarnings("unused")
	private boolean hasPreviousPage;
	@SuppressWarnings("unused")
	private boolean hasNextPage;
	
	private String ohql;//save hql for next page
	
	public int getTotalPage() {
		return totalPage;
	}
	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}
	public List getList() {
		return list;
	}
	public void setList(List list) {
		this.list = list;
	}
	public int getAllRow() {
		return allRow;
	}
	public void setAllRow(int allRow) {
		this.allRow = allRow;
	}
	public int getCurrentPage() {
		return currentPage;
	}
	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public boolean isFirstPage() {
		return currentPage == 1;
	}
	public void setFirstPage(boolean isFirstPage) {
		this.isFirstPage = isFirstPage;
	}
	public boolean isLastPage() {
		return currentPage == totalPage;
	}
	public void setLastPage(boolean isLastPage) {
		this.isLastPage = isLastPage;
	}
	public boolean isHasPreviousPage() {
		return currentPage != 1;
	}
	public void setHasPreviousPage(boolean hasPreviousPage) {
		this.hasPreviousPage = hasPreviousPage;
	}
	public boolean isHasNextPage() {
		return currentPage != totalPage;
	}
	public void setHasNextPage(boolean hasNextPage) {
		this.hasNextPage = hasNextPage;
	}
	public String getOhql() {
		return ohql;
	}
	public void setOhql(String ohql) {
		this.ohql = ohql;
	}
	public void init(){
		this.isFirstPage = isFirstPage();
		this.isLastPage = isLastPage();
		this.hasPreviousPage = isHasPreviousPage();
		this.hasNextPage = isHasNextPage();
	}
	


	public static int countTotalPage(final int pageSize, final int allRow) {
		int totalPage = allRow % pageSize == 0 ? allRow / pageSize : allRow / pageSize + 1;
		return totalPage;
	}


	public static int countOffset(final int pageSize, final int currentPage) {
		final int offset = pageSize * (currentPage - 1);
		return offset;
	}


	public static int countCurrentPage(int page){
		final int curPage = (page==0?1:page);
		return curPage;
	}
	
	
}
