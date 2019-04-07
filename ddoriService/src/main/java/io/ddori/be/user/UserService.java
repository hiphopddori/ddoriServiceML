package io.ddori.be.user;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import io.ddori.be.user.model.UserInfo;

@Service
@Transactional
public class UserService {
	
	@Autowired
	UserMapper userMapper;
	
	public List<UserInfo>getUserInfos(HashMap param){
		return userMapper.selectUserInfos(param);
	}
}
