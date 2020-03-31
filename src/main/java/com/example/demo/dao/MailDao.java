package com.example.demo.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.example.demo.db.MailManager;
import com.example.demo.vo.EmpVo;

@Repository
public class MailDao {
	public List<EmpVo>listAll(){
		return MailManager.listAll();
	}
	public int insert(EmpVo e) {
		return MailManager.insert(e);
	}
}
