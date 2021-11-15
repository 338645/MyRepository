package com.bjpowernode.dao;

import org.apache.ibatis.annotations.Param;

public interface IStockDao {
    void insertStock(@Param("sname") String sname, @Param("count") int amount, @Param("price") double price);

    int updateStock(@Param("sname") String sname, @Param("count") int amount, @Param("flag") boolean flag);
}
