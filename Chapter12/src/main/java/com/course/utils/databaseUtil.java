package com.course.utils;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.Reader;

public class databaseUtil {

    public static SqlSession getSqlSession() throws IOException {

        //get resources of config
        Reader reader = Resources.getResourceAsReader("databaseConfig.xml");
        //load file with class builder tool
        SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(reader);
        // session able to execute sql query directly
        SqlSession session = factory.openSession();

        return session;
    }
}
