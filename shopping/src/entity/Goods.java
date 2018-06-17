package entity;


import javax.sql.rowset.CachedRowSet;

import com.mysql.cj.protocol.Resultset;
import com.mysql.cj.xdevapi.Result;
import com.sun.rowset.*;

/**
 * 商品实体类
 * @author felixcui42
 *
 */
public class Goods 
{
	CachedRowSet rowSet= null;
	private int pageSize = 5;	//每页显示多少条记录(默认为5条)
	private int currentPage = 1;//当前页数
	private int totalRecord = 1;//总记录数
	private int totalPage = 1;	 //总页数
	
	public Goods(){
	}
	
	public Goods(CachedRowSet rowSet, int pageSize, int currentPage,
			int totalPRecord, int totalPage)
	{
		this.rowSet = rowSet;
		this.pageSize = pageSize;
		this.currentPage = currentPage;
		this.totalRecord = totalPRecord;
		this.totalPage = totalPage;
	}

    public CachedRowSet getRowSet()
    {
        return rowSet;
    }

    public void setRowSet(CachedRowSet rowSet)
    {
        this.rowSet = rowSet;
    }

    public int getPageSize()
    {
        return pageSize;
    }

    public void setPageSize(int pageSize)
    {
        this.pageSize = pageSize;
    }

    public int getCurrentPage()
    {
        return currentPage;
    }

    public void setCurrentPage(int currentPage)
    {
        this.currentPage = currentPage;
    }

    public int getTotalRecord()
    {
        return totalRecord;
    }

    public void setTotalRecord(int totalRecord)
    {
        this.totalRecord = totalRecord;
    }

    public int getTotalPage()
    {
        return totalPage;
    }

    public void setTotalPage(int totalPage)
    {
        this.totalPage = totalPage;
    }

	
}
