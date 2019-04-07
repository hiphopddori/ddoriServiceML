package io.ddori.be.user;

import java.util.HashMap;
import java.util.List;


import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import io.ddori.be.user.model.UserInfo;

@Mapper
public interface UserMapper {
	
	List<UserInfo> selectUserInfos(HashMap param);
}

