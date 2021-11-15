package com.bjpowernode.dao;

import org.apache.ibatis.annotations.Param;

public interface IAccountDao {
    void insertAccount(@Param("aname") String aname, @Param("money") double money);

    int updateAccount(@Param("aname") String aname, @Param("amount") int amount, @Param("price") double price, @Param("flag") boolean flag);
}
