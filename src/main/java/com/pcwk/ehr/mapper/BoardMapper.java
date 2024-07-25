package com.pcwk.ehr.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.pcwk.ehr.board.domain.Board;
import com.pcwk.ehr.cmn.WorkDiv;

@Mapper
public interface BoardMapper extends WorkDiv<Board> {

}
