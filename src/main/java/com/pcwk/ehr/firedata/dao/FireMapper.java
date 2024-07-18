package com.pcwk.ehr.firedata.dao;

import org.apache.ibatis.annotations.Mapper;

import com.pcwk.ehr.cmn.WorkDiv;
import com.pcwk.ehr.firedata.domain.Firedata;

@Mapper
public interface FireMapper extends WorkDiv<Firedata> {

}
