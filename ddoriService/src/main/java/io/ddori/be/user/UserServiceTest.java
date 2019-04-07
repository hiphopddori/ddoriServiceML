package io.ddori.be.user;

import java.util.HashMap;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import io.ddori.be.user.model.UserInfo;



@SpringBootTest
@RunWith(SpringRunner.class)
public class UserServiceTest {
	
	@Autowired
	UserService service;
	
	@Test
    public void getUserInfos() {
        /*
		HashMap param = new HashMap<String,Object>();
        param.put("userId", "sunsee78");
		List<UserInfo> users = service.getUserInfos(param);
		System.out.print("TEST");
		*/
		//log.info("city : {}", users);
    }
	
}
