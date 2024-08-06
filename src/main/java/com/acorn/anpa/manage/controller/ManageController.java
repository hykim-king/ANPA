package com.acorn.anpa.manage.controller;

import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.acorn.anpa.cmn.Message;
import com.acorn.anpa.cmn.PLog;
import com.acorn.anpa.firedata.domain.Firedata;
import com.acorn.anpa.firedata.service.FireDataService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;


@Controller
@RequestMapping("manage")
public class ManageController implements PLog{

	@Autowired
	FireDataService fireDataService;
	
	public ManageController() {
		log.debug("┌──────────────────────────────────────────────");
		log.debug("│ FireDataController()");
		log.debug("└──────────────────────────────────────────────");
	}
	
	@RequestMapping(
			value = "/doSaveData.do",
			method = RequestMethod.POST,
			produces = "text/plain;charset=UTF-8"
	)		// produces : 화면으로 전송할 때 encoding
	@ResponseBody
	public String doSaveData(Firedata inVO) throws SQLException {
		String jsonString = "";
		
		log.debug("param FireData" + inVO);
		
		int flag = fireDataService.doSaveData(inVO);
		log.debug("flag : " + flag);
		
		String message = "";
		
		if (1 <= flag) {
			message = "등록이 완료되었습니다 " + flag + "건의 메일이 전송되었습니다";
		}else {
			message = "등록 실패 혹은 예상밖의 오류입니다";
		}
		
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		jsonString = gson.toJson(new Message(flag, message));
		
		return jsonString;
	}
}
