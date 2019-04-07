package io.ddori.be.admin;

import java.util.HashMap;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.ddori.be.common.model.ResultInfo;

@RequestMapping("/api/admin/*")
@RestController
public class AdminController {
	

	@PostMapping("/adminTest")
	public ResultInfo getAdminData(@RequestBody HashMap param) {
		
		ResultInfo rs = new ResultInfo();
		
		return rs;
		
	}
	
}
