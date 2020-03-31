package com.example.demo.db;

import java.io.Reader;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.springframework.stereotype.Repository;

import com.example.demo.vo.EmpVo;

public class MailManager {
	private static SqlSessionFactory factory;
	static {
		try {
			Reader reader = Resources.getResourceAsReader("com/example/demo/db/SqlMapConfig.xml");
			factory = new SqlSessionFactoryBuilder().build(reader);
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	public static List<EmpVo> listAll() {
		SqlSession session = factory.openSession();
		List<EmpVo> list = session.selectList("emp.list");
		session.close();
		return list;
	}
	public static int insert(EmpVo e) {
		int re = -1;
		SqlSession session = factory.openSession(true);
		re = session.insert("emp,insert",e);
		session.close();
		return re;
	}
}
