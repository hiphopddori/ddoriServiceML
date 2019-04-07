package io.ddori.be.common.model;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class ResultInfo {
	
	public static final int RESULT_SUCCESS = 1;
	public static final int RESULT_FAIL = 0;
	
	private int flag = RESULT_SUCCESS;
	
	private String desc = null;
	private Object result = null;
	
}
