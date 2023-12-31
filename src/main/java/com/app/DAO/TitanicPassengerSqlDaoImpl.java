package com.app.DAO;

import com.app.Models.Passenger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import java.util.List;
import java.util.Map;
public class TitanicPassengerSqlDaoImpl extends TitanicPassengerDaoAbstract{

    @Autowired
    private JdbcTemplate jdbcTemplate;

//    public TitanicPassengerSqlDaoImpl(){
//        this.passengerMap = new HashMap<>();
//        this.getData();
//    }

    @Override
    public List<Passenger> loadData(){
        List<Passenger> passengerList = jdbcTemplate.query("SELECT * FROM users", new PassengerRowMapper());
        return passengerList;
    }
}
