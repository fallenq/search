package com.test;

import java.util.UUID;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.service.dao.nozzle.SparrowTestServiceI;
import com.service.dao.nozzle.SparrowUserServiceI;
import com.service.function.impl.SpfUserImpl;
import com.service.tool.EncodeTool;
import com.service.tool.StringTool;
import com.service.tool.UniqId;
import com.sparrow.dao.model.SparrowTest;
import com.sparrow.dao.model.SparrowUser;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:config/spring/spring.xml"})
public class TestMybatis {

//	private ApplicationContext ac;
	private SparrowUserServiceI testService;

//	@Before
//	public void before() {
//		ac = new ClassPathXmlApplicationContext(
//				new String[] {  });
//		testService = (SparrowTestServiceI) ac.getBean("sparrowTestService");
//	}

	public SparrowUserServiceI getTestService() {
		return testService;
	}

	@Autowired
	public void setTestService(SparrowUserServiceI testService) {
		this.testService = testService;
	}

	@Test
	public void test() {
		SparrowUser user = testService.getSparrowUserByPhone("13212345678", 1);
		try {
			System.out.println(user.getNickname());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println(user);
		}
		UUID uuid = UUID.randomUUID();
		String s = uuid.toString();//用来生成数据库的主键id非常不错..
		System.out.println(s);
		UniqId uniqId = UniqId.getInstance();
		System.out.println(uniqId.getUniqID());
		System.out.println(StringTool.uuCode("-"));
		
		System.out.println(EncodeTool.encrypt("123"));
		System.out.println(EncodeTool.match("123", "0cde0dea90bdbe33a6a4001b85abe5341babdea2265f6b36e97bfe275eab8f4158e15fd30bb42f47"));
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
