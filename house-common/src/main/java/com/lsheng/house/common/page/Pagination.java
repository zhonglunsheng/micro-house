package com.lsheng.house.common.page;

import java.util.List;

import com.google.common.collect.Lists;

/**
 * 分页工具
 */
public class Pagination {
	/**
	 * 分页index
	 */
	private int pageNum;
	/**
	 * 每页大小
	 */
	private int pageSize;
	/**
	 * 记录总和
	 */
	private long totalCount;

	/**
	 * 页面显示范围
	 */
	private List<Integer> pages = Lists.newArrayList();

	public Pagination(Integer pageSize,Integer pageNum,Long totalCount) {
	   this.totalCount = totalCount;
	   this.pageNum = pageNum;
	   this.pageSize = pageSize;

	   for(int i=1;i<=pageNum;i++){
		   pages.add(i);
	   }
	   // 是否需要多加一页
	   Long pageCount = totalCount/pageSize + ((totalCount % pageSize == 0 ) ? 0: 1);
	   if (pageCount > pageNum) {
		  for(int i= pageNum + 1; i<= pageCount ;i ++){
			  pages.add(i);
		  }
	   }
	}

	public int getPageNum() {
		return pageNum;
	}

	public void setPageNum(int pageNum) {
		this.pageNum = pageNum;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public long getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(long totalCount) {
		this.totalCount = totalCount;
	}

	public List<Integer> getPages() {
		return pages;
	}

	public void setPages(List<Integer> pages) {
		this.pages = pages;
	}
}
