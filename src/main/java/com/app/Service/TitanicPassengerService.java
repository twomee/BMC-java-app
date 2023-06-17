package com.app.Service;

import com.app.Models.Passenger;
import org.json.JSONObject;
import java.util.List;

public interface TitanicPassengerService {
    JSONObject getHistogram();
    JSONObject getPassengerData(Integer passengerId);
    JSONObject getRequestedAttributes(Integer passenger, List<String>  attributesList);
    List<Passenger> getAllPassengers();
}
