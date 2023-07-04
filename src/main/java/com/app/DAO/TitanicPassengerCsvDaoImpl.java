package com.app.DAO;


import com.app.Models.Passenger;
import com.opencsv.bean.CsvToBeanBuilder;
import java.io.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TitanicPassengerCsvDaoImpl extends TitanicPassengerDaoAbstract implements TitanicPassengerDao {

    public TitanicPassengerCsvDaoImpl(){
        this.passengerMap = new HashMap<>();
        this.getData();
    }
    @Override
    public List<Passenger> loadData() {
        String fileName = "titanic.csv";
        InputStream inputStream = getClass().getClassLoader().getResourceAsStream(fileName);

        if (inputStream == null) {
            throw new RuntimeException("CSV file not found in JAR.");
        }
        List<Passenger> passengerList = null;
        try (InputStreamReader reader = new InputStreamReader(inputStream)){
            passengerList = new CsvToBeanBuilder<Passenger>(reader)
                    .withType(Passenger.class)
                    .build()
                    .parse();
        } catch (Exception e) {
            throw new RuntimeException("Failed to read CSV file: " + e.getMessage());
        }
        return passengerList;
    }

}
