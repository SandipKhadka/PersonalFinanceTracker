package org.app.dao;

import java.util.List;

import org.app.model.DataModel;

public interface DataDao {
    List<DataModel> getUserData(String uid);
}
