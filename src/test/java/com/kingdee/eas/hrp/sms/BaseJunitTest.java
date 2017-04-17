package com.kingdee.eas.hrp.sms;

import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;

import com.kingdee.eas.hrp.sms.util.SMSSpringJUnit4ClassRunner;

@RunWith(SMSSpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:/config/spring-mvc.xml", "classpath:/config/applicationContext.xml" })
public class BaseJunitTest {

}
