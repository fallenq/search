package com.test;

import java.util.Date;
import java.util.UUID;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.service.sparrow.dao.nozzle.SparrowUserServiceI;
import com.service.sparrow.nozzle.SpUserServiceI;
import com.service.tool.EncodeTool;
import com.sparrow.entity.SparrowUser;
//import com.service.tool.EncodeTool;
//import com.service.tool.StringTool;
//import com.service.tool.UniqId;
//import com.sparrow.dao.model.SparrowTest;
//import com.sparrow.dao.model.SparrowUser;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:config/spring/spring.xml"})
public class TestMybatis {

//	private SparrowUserServiceI testService;
//
//	public SparrowUserServiceI getTestService() {
//		return testService;
//	}
//
//	@Autowired
//	public void setTestService(SparrowUserServiceI testService) {
//		this.testService = testService;
//	}
	
	private SpUserServiceI userService;

	public SpUserServiceI getUserService() {
		return userService;
	}
	
	@Autowired
	public void setUserService(SpUserServiceI userService) {
		this.userService = userService;
	}

	@Test
	public void test() {
		System.out.println(new Date());
		
//		SparrowUser user = userService.getUserByMobile("13212345678");
//		SparrowUser user = userService.getUserById(1);
//		SparrowUser user = userService.getUserByParams("123d3sss3", 2);
//		try {
//			System.out.println(user.toString());
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			System.out.println(user);
//		}
//		user.setNickname("1233ss3");
//		System.out.println(userService.updateById(user));
		
//		SparrowUser user = new SparrowUser();
//		user.setNickname("test");
//		System.out.println(userService.insert(user));
//		System.out.println(user.getId());
		
//		System.out.println(testService.deleteById(1));
//		UUID uuid = UUID.randomUUID();
//		String s = uuid.toString();//用来生成数据库的主键id非常不错..
//		System.out.println(s);
//		UniqId uniqId = UniqId.getInstance();
//		System.out.println(uniqId.getUniqID());
//		System.out.println(StringTool.uuCode("-"));
//		
//		System.out.println(EncodeTool.encrypt("123"));
//		System.out.println(EncodeTool.match("123", "0cde0dea90bdbe33a6a4001b85abe5341babdea2265f6b36e97bfe275eab8f4158e15fd30bb42f47"));
//		SparrowUser sparrowTest = testService.getSparrowUserById(1, 2);
//		System.out.println(sparrowTest.getNickname());
//		System.out.println(new SpfUserImpl().createUserPwd());
//		try {
//			SparrowTest sparrowTest = testService.selectByPrimaryKey(2);
//			System.out.println(sparrowTest.getName());
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			System.out.println("记录不存在！");
//		}
	}

}
