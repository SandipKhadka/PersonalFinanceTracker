package org.app.DBRowMapping;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.app.model.DataModel;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.lang.Nullable;

public class UserDataMapping implements RowMapper<DataModel> {

    @Override
    @Nullable
    public DataModel mapRow(ResultSet arg0, int arg1) throws SQLException {
        DataModel dataModel = new DataModel();
        dataModel.setId(arg0.getInt("id"));
        dataModel.setUid(arg0.getString("uid"));
        dataModel.setData(arg0.getInt("data"));
        return dataModel;
    }

}
