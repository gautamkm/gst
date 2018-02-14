package com.codecube.saathii.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import java.util.concurrent.atomic.AtomicLong;
import com.codecube.saathii.dao.ViewInformationDao;
import com.codecube.saathii.entity.ViewInformation;
import com.codecube.saathii.service.ViewInformationService;

@Repository
public class ViewInformationServiceImpl implements ViewInformationService{

	@Autowired
	ViewInformationDao viewInformationDao;
	
	@Override
	public List<ViewInformation> loadInformations(ViewInformation viewInformation) {
		// TODO Auto-generated method stub
		return viewInformationDao.loadInformations(viewInformation);
	}

	@Override
	public ViewInformation getInformationById(String userId) {		
		ViewInformation obj = viewInformationDao.getInformationById(userId);
		return obj;
	}
}
