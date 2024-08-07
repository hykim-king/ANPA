package com.acorn.anpa.manage.controller;

import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.acorn.anpa.cmn.Message;
import com.acorn.anpa.cmn.PLog;
import com.acorn.anpa.cmn.StringUtil;
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
		
		if (0 <= flag) {
			message = "등록이 완료되었습니다 " + flag + "건의 메일이 전송되었습니다";
		}else {
			message = "등록 실패 혹은 예상밖의 오류입니다";
		}
		
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		jsonString = gson.toJson(new Message(flag, message));
		
		return jsonString;
	} // doSaveData끝
	
	@RequestMapping(
			value = "/doUpdateData.do",
			method = RequestMethod.POST,
			produces = "text/plain;charset=UTF-8"
	)		// produces : 화면으로 전송할 때 encoding
	@ResponseBody
	public String doUpdateData(Firedata inVO) throws SQLException {
		String jsonString = "";
		
		log.debug("param FireData : " + inVO);
		
		//TODO : session 처리 예정
		String modId = StringUtil.nvl(inVO.getModId(), "admin1"); 
		inVO.setModId(modId);
		
		int flag = fireDataService.doUpdate(inVO);
		log.debug("flag : " + flag);
		
		String message = "";
		
		if(1==flag) {
			message = "화재정보가 수정되었습니다.";
		}else {
			message = "수정 실패! 존재하지 않는 화재정보입니다.";			
		}
		
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		jsonString = gson.toJson(new Message(flag, message));
		
		return jsonString;
	}
	
	@RequestMapping(
			value = "/doDeleteData.do",
			method = RequestMethod.GET,
			produces = "text/plain;charset=UTF-8"
	)		// produces : 화면으로 전송할 때 encoding
	@ResponseBody
	public String doDelete(Firedata inVO)throws SQLException {
		String jsonString = "";
		
		log.debug("param Firedata : " + inVO);
		
		int flag = fireDataService.doDelete(inVO);
		log.debug("flag : " + flag);
		
		String message = "";
		
		if(1==flag) {
			message = "화재 데이터가 삭제되었습니다.";
		}else {
			message = "삭제 실패! 존재하지 않는 데이터입니다.";			
		}
		
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		jsonString = gson.toJson(new Message(flag, message));
		
		return jsonString;
	}
	
	@RequestMapping(
			value = "/doSelectOne.do",
			method = RequestMethod.GET,
			produces = "text/plain;charset=UTF-8"
	)
	@ResponseBody
	public String doSelectOne(Firedata inVO) throws SQLException, NullPointerException {
		log.debug("┌──────────────────────────────────────────────");
		log.debug("│ doSelectOne()");
		log.debug("└──────────────────────────────────────────────");	
		
		String jsonString = "";		
		
		log.debug("1. param : " + inVO);
		Firedata outVO = fireDataService.doSelectOne(inVO);
		
		String message = "";
		int flag = 0;
		if (null != outVO) {
			message = inVO.getFireSeq() + " 의 데이터가 조회되었습니다";
			flag = 1;
		}else {
			message = inVO.getFireSeq() + " 조회 실패!";
		}
		
		//message
		jsonString = new Gson().toJson(new Message(flag, message));
		log.debug("3. jsonString : " + jsonString);
		
		//user
		String jsonFireData = new Gson().toJson(outVO);
		
		String allMessage = "{\"firedata\" : "+jsonFireData + ",\"message\": "+jsonString+"}";		
		
		return allMessage;
	}
}
