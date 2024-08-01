package com.pcwk.ehr.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.pcwk.ehr.cmn.WorkDiv;
import com.pcwk.ehr.member.domain.Member;

@Mapper
public interface MemberMapper extends WorkDiv<Member>{

}
