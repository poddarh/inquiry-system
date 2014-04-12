package com.royaltechnosoft.inquiry.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.royaltechnosoft.inquiry.dao.FollowupDAO;
import com.royaltechnosoft.inquiry.dao.InquiryDAO;
import com.royaltechnosoft.inquiry.model.Followup;
import com.royaltechnosoft.inquiry.model.Inquiry;
import com.royaltechnosoft.inquiry.service.FollowupService;

public class FollowupServiceImpl extends ServiceSupport implements
		FollowupService {
	@Autowired
	private FollowupDAO followupDAO;
	@Autowired
	private InquiryDAO inquiryDAO;

	public List<Followup> list(int page) {
		return followupDAO.listScheduledBeforeTime(new Date(), page);
	}

	public int getTotalPageNumber() {
		return followupDAO.countPagesScheduledBeforeTime(new Date());
	}
	
	/*
	 * Try 
	 * 
	 * followup.setTime(new Date());
	 * updateModel.getInquiry().setStatus(Inquiry.STATUS_OPEN);
	 * followupDAO.save(followup);
	 * 
	*/
	public void add(Followup followup, Character inquiryStatus) {
		followup.setTime(new Date());
		followupDAO.save(followup);
		if(followup.getFollowupID()!=null && inquiryStatus==Inquiry.STATUS_FRESH){
			Inquiry updateModel = new Inquiry();
			updateModel.setStatus(Inquiry.STATUS_OPEN);
			inquiryDAO.update(followup.getInquiry().getInquiryID(), updateModel);
		}
	}
	
}
