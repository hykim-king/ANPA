package com.pcwk.ehr.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.pcwk.ehr.cmn.WorkDiv;
import com.pcwk.ehr.firedata.domain.Firedata;

@Mapper
public interface FireDataMapper extends WorkDiv<Firedata> {

}
