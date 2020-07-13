package com.nexogichealthcare.Util;

import org.sql2o.Sql2o;

public class DbUtil {
static Sql2o sql2o;
public static Sql2o  getSql2oConnection() {
	        sql2o = new Sql2o("jdbc:postgresql://localhost:5432/nexogicdb", "postgres", "123456");
	        System.out.println("connection : "+sql2o);
	        return sql2o;
}
}
