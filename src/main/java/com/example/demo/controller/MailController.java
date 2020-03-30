package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dao.MailDao;
import com.example.demo.vo.EmpVo;

@RestController
public class MailController {
	@Autowired
	private MailDao dao;

	public void setDao(MailDao dao) {
		this.dao = dao;
	}

//	@Scheduled(cron = "30 23 17 30 * ?") // 초 분 시 일 월(모든월*) 년
//		@Scheduled(fixedRate = 10000) //10초에 한번 나옴
//	public void pro() {
//		System.out.println("스케쥴드 실행");
//		mail();
//	}

	@Autowired
	private MailSender javaMailSender;

	public void setJavaMailSender(MailSender javaMailSender) {
		this.javaMailSender = javaMailSender;
	}

	@RequestMapping("/mail.do")
	public String mail() {
		String r = "";
		List<EmpVo> list = dao.listAll();
		for (int i = 0; i < list.size(); i++) {
			SimpleMailMessage mailMessage = new SimpleMailMessage();
			mailMessage.setSubject("잘갔나요");
			mailMessage.setFrom("tisso010@naver.com");
			mailMessage.setText("수고하세요");
			mailMessage.setTo(list.get(i).getEmail());
			System.out.println(list.get(i).getEmail());
			try {
				javaMailSender.send(mailMessage);
			} catch (Exception e) {
				// TODO: handle exception'
				System.out.println(e.getMessage());
			}
		}
		return r;
	}

	@RequestMapping("/login.do")
	public String login(String email) {
		int ran = (int) Math.ceil(Math.random () * 10000);
			SimpleMailMessage mailMessage = new SimpleMailMessage();
			mailMessage.setSubject("인증절차입니다");
			mailMessage.setFrom("jj0101065@gmail.com");
			mailMessage.setText(""+ran); //인트를 문자열로 바꾸는 방법
			mailMessage.setTo(email);
			try {
				javaMailSender.send(mailMessage);
			} catch (Exception e) {
				// TODO: handle exception'
				System.out.println(e.getMessage());
			}
		
		return ""+ran;
	}
	
	
	
//	@RequestMapping("/ddd.do")
//	public void list() {
//		List<EmpVo> list = dao.listAll();
//		for (int i = 0; i < list.size(); i++) {
//			System.out.println(list.get(i).getEmail());
//		}
//	}

}
