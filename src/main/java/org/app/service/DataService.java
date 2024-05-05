package org.app.service;

import java.util.List;

import org.app.dao.DataDao;
import org.app.model.DataModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.servlet.http.HttpSession;

@Service
public class DataService {
    @Autowired
    HttpSession session;
    @Autowired
    DataDao dataDao;
    public DataService (DataDao dataDao) {
        this.dataDao = dataDao;
    }

    public List<DataModel> getDataService() {
        String userName= (String) session.getAttribute("user");
        return dataDao.getUserData(userName);
    }
}
