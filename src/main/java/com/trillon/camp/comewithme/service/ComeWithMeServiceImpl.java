package com.trillon.camp.comewithme.service;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.trillon.camp.comewithme.common.file.FileInfo;
import com.trillon.camp.comewithme.common.file.FileRepositoryYG;
import com.trillon.camp.comewithme.common.file.FileUtilYG;
import com.trillon.camp.comewithme.common.page.Paging;
import com.trillon.camp.comewithme.dto.Answer;
import com.trillon.camp.comewithme.dto.ComeWithMeBoard;
import com.trillon.camp.comewithme.repository.ComeWithMeRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ComeWithMeServiceImpl implements ComeWithMeService{

	Logger logger =  LoggerFactory.getLogger(this.getClass());
	
	private final ComeWithMeRepository comeWithMeRepository;
	private final FileRepositoryYG fileRepository;
	private final FileUtilYG fileUtil;
	
	@Override
	public Map<String, Object> selectBoardList(int page){
		
		int total = comeWithMeRepository.countAllBoard();
		
		Paging paging = Paging.builder()
							  .cntPerPage(10)
							  .currentPage(page)
							  .total(total)
							  .blockCnt(10)
							  .build();
		
		return Map.of("boardList", comeWithMeRepository.selectBoardList(paging), "paging",paging);
	}

	@Override
	public List<ComeWithMeBoard> selectMatchList(Answer answer) {
		
		
		System.out.println("serviceimpl 41" + answer); // 산, 20대, 차박
		List<ComeWithMeBoard> boardList;
		
//		answer.setPlace("산");
//		answer.setCampingWay("차박");
//		answer.setAgeAverage("20대");
		
		boardList = comeWithMeRepository.selectMatchList(answer);
		System.out.println("serviceimpl 49" + comeWithMeRepository.selectMatchList(answer));
		
		return boardList;

	}

	@Override
	public Map<String, Object> selectBoardContentByBdIdx(int bdIdx) {
		ComeWithMeBoard boardList = comeWithMeRepository.selectBoardByBdIdx(bdIdx);
		return Map.of("boardList", boardList);
	}

	@Override
	public void insertBoard(ComeWithMeBoard board, List<MultipartFile> files) {
		
		comeWithMeRepository.insertBoard(board);
		
		FileInfo fileInfo = new FileInfo();
		fileInfo.setGroupName("board");
		fileInfo.setGnIdx(board.getBdIdx());
		fileUtil.uploadFile(fileInfo, files);
	}

	@Override
	public void updateBoard(ComeWithMeBoard board) {
		comeWithMeRepository.updateBoard(board);
	}

	@Override
	public void deleteBoardByBdIdx(int bdIdx) {
		comeWithMeRepository.deleteBoardByBdIdx(bdIdx);
		
		
	}

	
	
	
	

}
