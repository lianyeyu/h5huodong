package com.jeecg.p3.gzbargain.util;

import java.io.Serializable;
import java.util.List;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

/**
 * 封装分页2.0
 * @Title: Pagination.java 
 * @Package com.yc.util.help 
 * @Description: 
 * @author lihongxuan
 * @date 2015年6月18日 下午4:16:18 
 * @version V1.0
 */
public class Pagination<T> implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 8469497721222526711L;


	private int curPage = 1; // 当前页码

	private int pageSize = 10; // 每页条数

	private List<T> content; // 记录集

	private int rowCount = 0; // 总记录数
	
	private int pageCount = 0; // 总页数

	/**
	 * 默认构造函数
	 */
	public Pagination() {
		super();

	}

	/**
	 * 根据属性构造分页函数
	 * 
	 * @param curPage
	 *            --当前页码
	 * @param pageSize
	 *            --每页条数
	 * @param content
	 *            --记录集
	 * @param rowCount
	 *            --总记录数
	 */
	public Pagination(int curPage, int pageSize, List<T> content, int rowCount) {
		super();
		this.curPage = curPage;
		this.pageSize = pageSize;
		this.content = content;
		this.rowCount = rowCount;
	}


	public int getCurPage() {
		return curPage;
	}

	public void setCurPage(int curPage) {
		this.curPage = curPage;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public List<T> getContent() {
		return content;
	}

	public void setContent(List<T> content) {
		this.content = content;
	}

	public int getRowCount() {
		return rowCount;
	}

	public void setRowCount(int rowCount) {
		this.rowCount = rowCount;
	}

	public int getPageCount() {
		if (rowCount == 0) {
			return 0;
		} else if (curPage < 0) {
			if (content != null && content.size() > 0) {
			    pageCount = 1;
			} else {
				pageCount = 0;
			}
		} else {
			pageCount = rowCount / pageSize + (rowCount % pageSize > 0 ? 1 : 0);
		}
		return pageCount;
	}

	public int getStartRow() {
		if (content != null && content.size() > 0) {
			if (curPage < 0) {
				return 1;
			} else {
				return (curPage - 1) * pageSize + 1;
			}
		} else {
			return 0;
		}
	}

	public int getEndRow() {
		if (content != null && content.size() > 0) {
			return getStartRow() + content.size();
		} else {
			return 0;
		}
	}

	@Override
	public String toString() {
		ToStringBuilder builder = new ToStringBuilder(this,
				ToStringStyle.DEFAULT_STYLE);
		builder.append("curPage", curPage);
		builder.append("pageSize", pageSize);
		builder.append("content", content);
		builder.append("rowCount", rowCount);
		return builder.toString();
	}

	@Override
	public boolean equals(Object other) {
		boolean equals = false;
		if (other instanceof Pagination) {
			if (this == other) {
				equals = true;
			} else {
				Pagination cast = (Pagination) other;
				EqualsBuilder builder = new EqualsBuilder();
				builder.append(curPage, cast.curPage);
				builder.append(pageSize, cast.pageSize);
				builder.append(content, cast.content);
				builder.append(rowCount, cast.rowCount);
				equals = builder.isEquals();
			}
		}
		return equals;
	}

	@Override
	public int hashCode() {
		HashCodeBuilder builder = new HashCodeBuilder();
		builder.append(curPage);
		builder.append(pageSize);
		builder.append(content);
		builder.append(rowCount);
		return builder.toHashCode();
	}
}
