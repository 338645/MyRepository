package com.bjpowernode.dao.impl;

import com.bjpowernode.dao.IStockDao;
import org.springframework.jdbc.core.support.JdbcDaoSupport;

public class StockDao extends JdbcDaoSupport implements IStockDao {
    @Override
    public void insertStock(String sname, int amount, double price) {
        String sql = "insert into t_stock(sname,count,price) values (?,?,?)";
        this.getJdbcTemplate().update(sql, sname, amount, price);
    }

    @Override
    public int updateStock(String sname, int amount, boolean flag) {
        String sql = "update t_stock set count = count - ? where sname =?";
        if (flag) {
            sql = "update t_stock set count = count + ? where sname =?";
        }
        int update = this.getJdbcTemplate().update(sql, amount, sname);
        return update;
    }
}
