package com.bjpowernode.dao.impl;

import com.bjpowernode.dao.IAccountDao;
import org.springframework.jdbc.core.support.JdbcDaoSupport;

public class AccountDao extends JdbcDaoSupport implements IAccountDao {
    @Override
    public void insertAccount(String aname, double money) {
        String sql = "insert  into t_account(aname,balance) values (?,?)";
        this.getJdbcTemplate().update(sql, aname, money);
    }

    @Override
    public int updateAccount(String aname, int amount, double price, boolean flag) {
        String sql = "update t_account set balance = balance + ? where aname =?";
        if (flag) {
            sql = "update t_account set balance = balance - ? where aname =?";
        }
        int update = this.getJdbcTemplate().update(sql, amount * price, aname);
        return update;
    }
}
