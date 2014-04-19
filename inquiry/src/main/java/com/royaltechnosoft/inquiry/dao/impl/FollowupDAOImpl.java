package com.royaltechnosoft.inquiry.dao.impl;

import com.royaltechnosoft.inquiry.dao.FollowupDAO;
import com.royaltechnosoft.inquiry.model.Followup;

public class FollowupDAOImpl extends DAOSupport<Followup> implements
		FollowupDAO {
	public FollowupDAOImpl() {
		setGenericType(Followup.class);
	}

}
