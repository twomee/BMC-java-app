package com.app.Models;

import com.opencsv.bean.CsvBindByName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.json.JSONObject;
import java.util.HashMap;
import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Passenger{
    @CsvBindByName(column = "PassengerId")
    private Integer passengerId;
    @CsvBindByName(column = "Survived")
    private String survived;
    @CsvBindByName(column = "Pclass")
    private Integer pclass;
    @CsvBindByName(column = "Name")
    private String name;
    @CsvBindByName(column = "Sex")
    private String sex;
    @CsvBindByName(column = "Age")
    private Float age;
    @CsvBindByName(column = "Sibsp")
    private Integer sibSp;
    @CsvBindByName(column = "Parch")
    private Integer parch;
    @CsvBindByName(column = "Ticket")
    private String ticket;
    @CsvBindByName(column = "Fare")
    private Float fare;
    @CsvBindByName(column = "Cabin")
    private String cabin;
    @CsvBindByName(column = "Embarked")
    private String embarked;

    public JSONObject toJSON() {
        JSONObject jo = new JSONObject();
        jo.put("passengerId", this.passengerId);
        jo.put("survived", this.survived);
        jo.put("pclass", this.pclass);
        jo.put("name", this.name);
        jo.put("sex", this.sex);
        jo.put("age", this.age);
        jo.put("sibSp", this.sibSp);
        jo.put("parch", this.parch);
        jo.put("ticket", this.ticket);
        jo.put("fare", this.fare);
        jo.put("cabin", this.cabin);
        jo.put("embarked", this.embarked);
        return jo;
    }

    public Map<String, String> toMap() {
        Map<String, String> map = new HashMap<>();
        map.put("passengerId", String.valueOf(this.passengerId));
        map.put("survived", String.valueOf(this.survived));
        map.put("pclass", String.valueOf(this.pclass));
        map.put("name", String.valueOf(this.name));
        map.put("sex", String.valueOf(this.sex));
        map.put("age", String.valueOf(this.age));
        map.put("sibSp", String.valueOf(this.sibSp));
        map.put("parch", String.valueOf(this.parch));
        map.put("ticket", String.valueOf(this.ticket));
        map.put("fare", String.valueOf(this.fare));
        map.put("cabin", String.valueOf(this.cabin));
        map.put("embarked", String.valueOf(this.embarked));
        return map;
    }

}
