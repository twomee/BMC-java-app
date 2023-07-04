package com.app.DAO;

import com.app.Models.Passenger;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public abstract class TitanicPassengerDaoAbstract {
    protected Map<Integer, Passenger> passengerMap;

    protected abstract List<Passenger> loadData();

    protected Map<Integer, Passenger> getData() {
        List<Passenger> passengerList = this.loadData();
        this.convertPassengerListToPassengerMap(passengerList);
        return this.passengerMap;
    }


    protected void convertPassengerListToPassengerMap(List<Passenger> passengerList){
        for(Passenger passenger : passengerList){
            this.passengerMap.put(passenger.getPassengerId(), passenger);
        }
    }

    public Passenger getPassengerData(Integer passengerId){
        return this.passengerMap.get(passengerId);
    }

    public List<Passenger> getAllPassengers(){
        return new ArrayList<>(this.passengerMap.values());
    }

    public List<String> getRequestedAttributes(Integer passengerId, List<String>  attributesList){
        List<String> lowerAttributeList = lowerAllStringInAttributeList(attributesList);
        List<String> requestedAttributes = new ArrayList<>();
        Passenger passenger = this.getPassengerData(passengerId);
        if(passenger != null) {
            for (Map.Entry<String, String> entry : passenger.toMap().entrySet()) {
                String attributeKey = entry.getKey();
                String attributeValue = entry.getValue();
                if (lowerAttributeList.contains(attributeKey)) {
                    requestedAttributes.add(attributeValue);
                }
            }
        }
        return requestedAttributes;
    }

    private List<String> lowerAllStringInAttributeList(List<String> attributesList) {
        List<String> lowerAttributeList = new ArrayList<>();
        for(String attribute : attributesList){
            lowerAttributeList.add(attribute.toLowerCase());
        }
        return lowerAttributeList;
    }
}
