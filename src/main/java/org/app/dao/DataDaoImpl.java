package org.app.dao;

import java.util.List;

import org.app.DBRowMapping.UserDataMapping;
import org.app.model.DataModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class DataDaoImpl implements DataDao {
    String sql;
    @Autowired
    JdbcTemplate jdbcTemplate;

    public DataDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<DataModel> getUserData(String uid) {
        sql = "SELECT * FROM testing WHERE uid='"+uid+"'";
        List<DataModel> dataModels =jdbcTemplate.query(sql, new UserDataMapping());
        return dataModels;
    }

    // private DataModel extracted(ResultSet rs) throws SQLException {
    // DataModel dataModel;
    // dataModel = new DataModel();
    // dataModel.setId(rs.getInt("id"));
    // dataModel.setUid(rs.getString("uid"));
    // dataModel.setData(rs.getInt("data"));
    // return dataModel;
    // }
}
