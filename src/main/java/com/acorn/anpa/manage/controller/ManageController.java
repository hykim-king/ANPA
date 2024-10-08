package com.acorn.anpa.manage.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.acorn.anpa.cmn.Message;
import com.acorn.anpa.cmn.PLog;
import com.acorn.anpa.cmn.Search;
import com.acorn.anpa.cmn.StringUtil;
import com.acorn.anpa.code.domain.Code;
import com.acorn.anpa.code.service.CodeService;
import com.acorn.anpa.firedata.domain.Firedata;
import com.acorn.anpa.firedata.service.FireDataService;
import com.acorn.anpa.member.domain.Member;
import com.acorn.anpa.member.service.MemberService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;


@Controller
@RequestMapping("manage")
public class ManageController implements PLog{

	@Autowired
	FireDataService fireDataService;

	@Autowired
	MemberService memberService;
	
	@Autowired
	CodeService codeService;

	public ManageController() {
		log.debug("┌──────────────────────────────────────────────");
		log.debug("│ FireDataController()");
		log.debug("└──────────────────────────────────────────────");
	}	
	
	@RequestMapping(
			value = "/doSelectCode.do",
			method = RequestMethod.GET,
			produces = "text/plain;charset=UTF-8"
	)		// produces : 화면으로 전송할 때 encoding
	@ResponseBody
	public String doSelectCode(String masterCode,int subCode) throws SQLException{
		log.debug("┌──────────────────────────────────────────────");
		log.debug("│ doSelectCode()");
		log.debug("└──────────────────────────────────────────────");	
		
		String jsonString = "";
	
		Code code = new Code();
		code.setMasterCode(masterCode);
		code.setMainCode(subCode);
		
		List<Code> codeList = this.codeService.doSelectCode(code);			
		
		jsonString = new Gson().toJson(codeList);
		
		return jsonString;
	}
	
	@RequestMapping(
			value = "/doDeleteMember.do",
			method = RequestMethod.GET,
			produces = "text/plain;charset=UTF-8"
	)		// produces : 화면으로 전송할 때 encoding
	@ResponseBody
	public String doDeleteMember(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
		Member user = (Member) request.getSession().getAttribute("user");
        // 관리자 여부 확인
        if (user == null || !(user.getAdminYn() == 1 || "1".equals(user.getAdminYn()))) {
            // 관리자 권한이 없으면 리디렉션
            String targetURL = "/ehr/main/index.do";
            response.sendRedirect(targetURL); // 클라이언트를 지정된 URL로 리디렉션합니다
        }
		
		String jsonString = "";
		
		// HTTP 요청에서 userIds를 받아옵니다.
    	String userIdsJson = request.getParameter("userIds");
    	log.debug("userIdsJson" + userIdsJson);
		
    	// Gson을 사용하여 JSON 문자열을 배열로 변환합니다.
        Gson userIdsgson = new Gson();
    	String[] userIds = userIdsgson.fromJson(userIdsJson, String[].class);
    	log.debug("userIds" + Arrays.toString(userIds));
    	
    	// 삭제 결과를 저장할 변수
        int totalDeleted = 0;
        int flag = 0;
        if (userIds != null && userIds.length > 0) {
        	// 각 userId에 대해 삭제 작업을 수행합니다.
            for (String userId : userIds) {
            	Member inVO = new Member();
            	inVO.setUserId(userId.trim()); // 공백 제거 후 화면에서 가져온 userId 부여
            	log.debug("inVO" + inVO);
            	
            	flag = memberService.doDelete(inVO);
            	log.debug("flag : " + flag);
            	totalDeleted += flag;
            	}
        }else {
        	log.debug("userIds가 null이거나 길이가 0입니다.");
        }
        
        log.debug("총 {} 건 삭제되었습니다.", totalDeleted);
        
		String message = "";		
		if(totalDeleted > 0) {
			message = totalDeleted + "건의 회원정보가 삭제되었습니다.";
		}else {
			message = "삭제 실패! 존재하지 않는 데이터입니다.";			
		}
		
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		jsonString = gson.toJson(new Message(totalDeleted, message));
		
		return jsonString;
	}
	
	@GetMapping("/doRetrieveMember.do")
	public String doRetrieveMember(Model model, HttpServletRequest req, HttpServletResponse response) throws SQLException, IOException {
		Member user = (Member) req.getSession().getAttribute("user");
        // 관리자 여부 확인
        if (user == null || !(user.getAdminYn() == 1 || "1".equals(user.getAdminYn()))) {
            // 관리자 권한이 없으면 리디렉션
            String targetURL = "/ehr/main/index.do";
            response.sendRedirect(targetURL); // 클라이언트를 지정된 URL로 리디렉션합니다
        }
        
		String viewName = "manage/manageMember";
		
		Search search = new Search();	
		
		//searchDiv = "" (기본값)
		String searchDiv = StringUtil.nvl(req.getParameter("searchDiv"), "");
		//searchWord = "" (기본값)
		String searchWord = StringUtil.nvl(req.getParameter("searchWord"), "");
		
		//pageSize=10 (기본값)
		String pageSize = StringUtil.nvl(req.getParameter("pageSize"), "10");		
		//pageNo=1 (기본값)
		String pageNo = StringUtil.nvl(req.getParameter("pageNo"), "1");

		search.setSearchDiv(searchDiv);
		search.setSearchWord(searchWord);
		search.setPageSize(Integer.parseInt(pageSize));
		search.setPageNo(Integer.parseInt(pageNo));
		
		List<Member> list = memberService.doRetrieve(search);
		
		log.debug("┌──────────────────────────────────────────────");
		log.debug("│ search : " + search);
		log.debug("│ list : " + list);
		log.debug("└──────────────────────────────────────────────");	
		
		// 모델에 튜닝 리스트, 검색 조건
		model.addAttribute("list", list);
		model.addAttribute("search", search);
		// 모델에 튜닝 리스트, 검색 조건
		
		Code code = new Code();
		// 모델에 튜닝 검색조건
		code.setMasterCode("MEMBER_SEARCH");
		List<Code> memberSearch = codeService.doRetrieve(code);
		model.addAttribute("MEMBER_SEARCH", memberSearch);
		// 모델에 튜닝 검색조건			
		
		// 모델에 튜닝 토탈 카운트
		int totalCnt = 0;
		if(null!=list && list.size()>0) {
			Member member = list.get(0);
			totalCnt = member.getTotalCnt();
		}		
		model.addAttribute("totalCnt", totalCnt);
		// 모델에 튜닝 토탈 카운트
		
		// 모델에 튜닝 페이지 사이즈
		code.setMasterCode("COM_PAGE_SIZE"); // 페이지 사이즈
		List<Code> comPageSize = this.codeService.doRetrieve(code);
		model.addAttribute("COM_PAGE_SIZE", comPageSize); // 검색조건
		// 모델에 튜닝 페이지 사이즈
		
		return viewName;
	}
	
	@GetMapping("/doRetrieveData.do")
	public String doRetrieveData(Model model, HttpServletRequest req, HttpServletResponse response) throws SQLException, IOException {
		Member user = (Member) req.getSession().getAttribute("user");
        // 관리자 여부 확인
        if (user == null || !(user.getAdminYn() == 1 || "1".equals(user.getAdminYn()))) {
            // 관리자 권한이 없으면 리디렉션
            String targetURL = "/ehr/main/index.do";
            response.sendRedirect(targetURL); // 클라이언트를 지정된 URL로 리디렉션합니다
        }
		
		String viewName = "manage/manageData";
		
		Search search = new Search();	
		
		//searchDateStart = "" (사고 날짜 시작)
		String searchDateStart = StringUtil.nvl(req.getParameter("dateStart"), "");
		//searchDateEnd = "" (사고 날짜 종류)
		String searchDateEnd = StringUtil.nvl(req.getParameter("dateEnd"), "");

		//searchDiv = "" (화재요인 대분류)
		String searchDiv = StringUtil.nvl(req.getParameter("fBselect"), "");
		//searchWord = "" (화재요인 중분류)
		String searchWord = StringUtil.nvl(req.getParameter("fMselect"), "");

		//BigNm = "" (화재장소 대분류)
		String bigNm = StringUtil.nvl(req.getParameter("lBselect"), "");	
		//MidNm = "" (화재장소 중분류)
		String midNm = StringUtil.nvl(req.getParameter("lMselect"), "");

		//subCityBigNm = "" (시도)
		String subCityBigNm = StringUtil.nvl(req.getParameter("cBselect"), "");
		//subCityMidNm = "" (시군구)
		String subCityMidNm = StringUtil.nvl(req.getParameter("cMselect"), "");
		
		//pageSize=10 (기본값)
		String pageSize = StringUtil.nvl(req.getParameter("pageSize"), "10");		
		//pageNo=1 (기본값)
		String pageNo = StringUtil.nvl(req.getParameter("pageNo"), "1");
		
		search.setSearchDateStart(searchDateStart);
		search.setSearchDateEnd(searchDateEnd);
		search.setSearchDiv(searchDiv);
		search.setSearchWord(searchWord);
		search.setBigNm(bigNm);
		search.setMidNm(midNm);
		search.setSubCityBigNm(subCityBigNm);
		search.setSubCityMidNm(subCityMidNm);
		search.setPageSize(Integer.parseInt(pageSize));
		search.setPageNo(Integer.parseInt(pageNo));
		
		List<Firedata> list = fireDataService.doRetrieve(search);
		log.debug("┌──────────────────────────────────────────────");
		log.debug("│ list : " + list);
		log.debug("└──────────────────────────────────────────────");	
		
		// 모델에 튜닝 리스트, 검색 조건
		model.addAttribute("list", list);
		model.addAttribute("search", search);
		// 모델에 튜닝 리스트, 검색 조건
		
		Code code = new Code();
		// 모델에 튜닝 장소 코드
		code.setMasterCode("location");
		List<Code> locCode = codeService.doMstCode(code);
		model.addAttribute("LocCode", locCode);
		// 모델에 튜닝 장소 코드
		
		// 모델에 튜닝 시군구 코드
		code.setMasterCode("city");
		List<Code> cityCode = codeService.doMstCode(code);
		model.addAttribute("cityCode", cityCode);
		// 모델에 튜닝 시군구 코드
		
		// 모델에 튜닝 화재요인 코드
		code.setMasterCode("factor");
		List<Code> factorCode = codeService.doMstCode(code);
		model.addAttribute("factorCode", factorCode);
		// 모델에 튜닝 화재요인 코드
		
		// 모델에 튜닝 토탈 카운트
		int totalCnt = 0;
		if(null!=list && list.size()>0) {
			Firedata firedata = list.get(0);
			totalCnt = firedata.getTotalCnt();
		}		
		model.addAttribute("totalCnt", totalCnt);
		// 모델에 튜닝 토탈 카운트
		
		// 모델에 튜닝 페이지 사이즈
		code.setMasterCode("COM_PAGE_SIZE"); // 페이지 사이즈
		List<Code> comPageSize = this.codeService.doRetrieve(code);
		model.addAttribute("COM_PAGE_SIZE", comPageSize); // 검색조건
		// 모델에 튜닝 페이지 사이즈
		
		return viewName;
	}
	
	@RequestMapping(
			value = "/doSaveData.do",
			method = RequestMethod.POST,
			produces = "text/plain;charset=UTF-8"
	)		// produces : 화면으로 전송할 때 encoding
	@ResponseBody
	public String doSaveData(Firedata inVO, HttpServletRequest req, HttpServletResponse response) throws SQLException, IOException {
		Member user = (Member) req.getSession().getAttribute("user");
        // 관리자 여부 확인
        if (user == null || !(user.getAdminYn() == 1 || "1".equals(user.getAdminYn()))) {
            // 관리자 권한이 없으면 리디렉션
            String targetURL = "/ehr/main/index.do";
            response.sendRedirect(targetURL); // 클라이언트를 지정된 URL로 리디렉션합니다
        }
		
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
	public String doUpdateData(Firedata inVO, HttpServletRequest req, HttpServletResponse response) throws SQLException, IOException {
		Member user = (Member) req.getSession().getAttribute("user");
        // 관리자 여부 확인
        if (user == null || !(user.getAdminYn() == 1 || "1".equals(user.getAdminYn()))) {
            // 관리자 권한이 없으면 리디렉션
            String targetURL = "/ehr/main/index.do";
            response.sendRedirect(targetURL); // 클라이언트를 지정된 URL로 리디렉션합니다
        }
        
		String jsonString = "";
		
		log.debug("param FireData : " + inVO);
		
		//TODO : session 처리 예정
		String injuredSum = StringUtil.nvl(inVO.getInjuredSum()+"", "0"); 
		String dead = StringUtil.nvl(inVO.getDead()+"", "0"); 
		String injured = StringUtil.nvl(inVO.getInjured()+"", "0"); 
		String amount = StringUtil.nvl(inVO.getAmount()+"", "0"); 
		String modId = StringUtil.nvl(inVO.getModId(), "admin1"); 
		inVO.setInjuredSum(Integer.parseInt(injuredSum));
		inVO.setDead(Integer.parseInt(dead));
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
	public String doDeleteData(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
		Member user = (Member) request.getSession().getAttribute("user");
        // 관리자 여부 확인
        if (user == null || !(user.getAdminYn() == 1 || "1".equals(user.getAdminYn()))) {
            // 관리자 권한이 없으면 리디렉션
            String targetURL = "/ehr/main/index.do";
            response.sendRedirect(targetURL); // 클라이언트를 지정된 URL로 리디렉션합니다
        }
		
		String jsonString = "";
		
		// HTTP 요청에서 fireSeqs를 받아옵니다.
    	String fireSeqsJson = request.getParameter("fireSeqs");
    	log.debug("fireSeqsJson" + fireSeqsJson);
		
    	// Gson을 사용하여 JSON 문자열을 배열로 변환합니다.
        Gson fireSeqsgson = new Gson();
    	String[] fireSeqs = fireSeqsgson.fromJson(fireSeqsJson, String[].class);
    	log.debug("fireSeqs" + Arrays.toString(fireSeqs));    	
    	
    	// 삭제 결과를 저장할 변수
        int totalDeleted = 0;
        int flag = 0;
        if (fireSeqs != null && fireSeqs.length > 0) {
        	// 각 fireSeqs에 대해 삭제 작업을 수행합니다.
            for (String fireSeq : fireSeqs) {
            	Firedata inVO = new Firedata();
            	inVO.setFireSeq(Integer.parseInt(fireSeq.trim())); // 공백 제거 후 화면에서 가져온 fireSeq 부여
            	log.debug("inVO" + inVO);
            	
            	flag = fireDataService.doDelete(inVO);
            	log.debug("flag : " + flag);
            	totalDeleted += flag;
            	}
        }else {
        	log.debug("fireSeqs가 null이거나 길이가 0입니다.");
        }
        
        log.debug("총 {} 건 삭제되었습니다.", totalDeleted);
		
		String message = "";
		
		if(totalDeleted > 0) {
			message = totalDeleted + "건의 화재데이터가 삭제되었습니다.";
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
			message = "화재정보 " + inVO.getFireSeq() + " 번 데이터가 조회되었습니다";
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
