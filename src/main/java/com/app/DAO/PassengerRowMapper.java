package com.app.DAO;

import com.app.Models.Passenger;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class PassengerRowMapper implements RowMapper<Passenger> {
    @Override
    public Passenger mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new Passenger(
                rs.getInt("passengerId"),
                rs.getString("survived"),
                rs.getInt("pclass"),
                rs.getString("name"),
                rs.getString("sex"),
                rs.getFloat("age"),
                rs.getInt("sibSp"),
                rs.getInt("parch"),
                rs.getString("ticket"),
                rs.getFloat("fare"),
                rs.getString("cabin"),
                rs.getString("embarked")
        );
    }
}
