package com.test;

import java.util.ArrayList;
import java.util.Date;
import java.util.UUID;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.service.config.CommonConfig;
import com.service.config.ServiceConfig;
import com.service.config.SparrowConfig;
import com.service.config.enums.ResponseStatusEnum;
import com.service.model.LoginInfoModel;
import com.service.sparrow.dao.nozzle.SparrowUserServiceI;
import com.service.sparrow.nozzle.SpUserServiceI;
import com.service.sparrow.nozzle.SpVersionServiceI;
import com.service.tool.CommonTool;
import com.service.tool.EncodeTool;
import com.service.tool.MobileTool;
import com.service.tool.RedisTool;
import com.service.tool.SessionTool;
import com.service.tool.StringTool;
import com.service.tool.WarnMsgTool;
import com.service.tool.impl.RedisImpl;
import com.service.tool.nozzle.RedisServiceI;
import com.sparrow.common.ValidateTool;
import com.sparrow.common.impl.MobileAccessValidateImpl;
import com.sparrow.common.impl.MobileSendValidateImpl;
import com.sparrow.common.nozzle.ValidateModelServiceI;
import com.sparrow.entity.SparrowUser;
//import com.service.tool.EncodeTool;
//import com.service.tool.StringTool;
//import com.service.tool.UniqId;
//import com.sparrow.dao.model.SparrowTest;
//import com.sparrow.dao.model.SparrowUser;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:config/spring/spring.xml"})
public class TestMybatis {


	@Autowired
	private SpVersionServiceI versionService;
	
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
	
//	private SpUserServiceI userService;
//	@Autowired
//	private RedisServiceI redisService;
//
//	public SpUserServiceI getUserService() {
//		return userService;
//	}
//	
//	@Autowired
//	public void setUserService(SpUserServiceI userService) {
//		this.userService = userService;
//	}
	
	@SuppressWarnings("unchecked")
	public <T> T getSessionRedis(String name, String sessionId) {
		if (!StringTool.isAvailableString(name)) {
			return null;
		}
		String sessionKey = CommonConfig.SESSION_DATA_PREFIX + sessionId;
		String sessionDataKey = CommonConfig.SESSION_COLUMN_PREFIX + name;
		return (T) RedisTool.getCommonRedis().hgetField(sessionKey, sessionDataKey);
	}

	@Test
	public void test() {
		String sessionId = "a81cf755-44de-48be-9574-02f179c51aae";
		String sessionKey = CommonConfig.SESSION_DATA_PREFIX + sessionId;
		String sessionDataKey = CommonConfig.SESSION_COLUMN_PREFIX + ServiceConfig.USER_LOGIN_INFO;
		System.out.println(sessionId);
		System.out.println(sessionKey);
		System.out.println(sessionDataKey);
		JSONObject loginInfo = getSessionRedis(ServiceConfig.USER_LOGIN_INFO, sessionId);
		System.out.println(loginInfo);
		System.out.println(StringTool.parseMobile("13212345678", 1));
//		System.out.println(versionService.compareLastVersion(1, "0.0.1"));
//		System.out.println(JSON.toJSONString(versionService.compareLastVersion(1, "0.0.1")));
//		ArrayList<String> params = CommonTool.combineList("1", "2");
//		System.out.println(params);
//		System.out.println(ResponseStatusIntEnum.SUCCESS.getValue());
//		ValidateTool validateTool = ValidateTool.getInstance();
//		ValidateModelServiceI validateService = validateTool.getValidateService(3);
//		System.out.println(validateTool.determine(3, validateService, "13212345678", "127.0.0.1"));
//		System.out.println(.getRedisKey());
//		String[] numbers = StringTool.splitString("127.0.0.1", "\\.");
//		for (String number: numbers) {
//			System.out.println("=>" + number);
//		}
//		System.out.println(WarnMsgConfig.getSparrowValue(WarnMsgConfig.SPARROW_USER_LOGININFO_ERROR));
//		MobileAccessValidateImpl sendService = MobileAccessValidateImpl.getInstance();
//		sendService.setRedisKey("127.0.0.1");
//		System.out.println(sendService.getRedisKey());
//		sendService.setRedisValue("123");
//		sendService.removeRedisValue();
//		System.out.println(sendService.getRedisValue());
//		RedisServiceI redisService = RedisImpl.getInstance();
//		System.out.println(redisService);
//		redisService.set("test", "1");
//		MobileTool mTool = new MobileTool();
//		RedisServiceI redisService = RedisTool.getCommonRedis(1);
//		System.out.println(redisService.ping());
//		redisService.set("test1", "test");
//		System.out.println(RedisTool.getCommonRedis(1).set("test", "test"));
//		System.out.println(MobileTool.getInstance().sendMobileCode("13212345679", "1234", "127.0.0.1"));
//		System.out.println(WarnMsgConfig.getSparrowValue(WarnMsgConfig.SPARROW_USER_LOGININFO_ERROR));
//		redisService.set("test", "test1");
//		System.out.println(redisService.get("test"));
//		SparrowUser user = userService.getUserByMobile("13212345678");
//		SparrowUser user = userService.getUserById(1);
//		System.out.println(user.getNickname());
//		System.out.println(user.getUserMobile().getMobile());
//		SparrowUser user = userService.getUserByParams("123d3sss3", 2);
//		try {
//			System.out.println(user.toString());
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			System.out.println(user);
//		}
//		user.setNickname("test2");
//		System.out.println(userService.updateById(user));
		
//		SparrowUser user = new SparrowUser();
//		user.setNickname("test");
//		System.out.println(userService.insert(user));
		
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
