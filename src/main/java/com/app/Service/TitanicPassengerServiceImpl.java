package com.app.Service;

import com.app.DAO.TitanicPassengerCsvDaoImpl;
import com.app.Models.Passenger;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.*;

public class TitanicPassengerServiceImpl implements TitanicPassengerService {
    @Autowired
    private TitanicPassengerCsvDaoImpl titanicPassengerDao;

    @Override
    public JSONObject getHistogram() {
        List<Float> farePrices = this.getFareData();
        Map<String, Object> histogramData = this.createHistogramData(farePrices);
        return this.createHistogramJsonObject(histogramData);
    }

    private List<Float> getFareData(){
        List<Float> fareData = new ArrayList<>();
        List<Passenger> passengers = titanicPassengerDao.getAllPassengers();
        for(Passenger passenger: passengers){
            fareData.add(passenger.getFare());
        }
        return fareData;
    }

    private Map<String, Object> createHistogramData(List<Float> farePrices){
        int numBins = farePrices.size()/10;
        double minPrice = Collections.min(farePrices);
        double maxPrice = Collections.max(farePrices);
        double binSize = (maxPrice - minPrice) / numBins;

        int[] histogram = new int[numBins];

        for (double farePrice : farePrices) {
            int binIndex = (int) ((farePrice - minPrice) / binSize);
            if (binIndex >= 0 && binIndex < numBins) {
                histogram[binIndex]++;
            }
        }

        Map<String, Object> histogramData = new HashMap<>();
        histogramData.put("numBins", numBins);
        histogramData.put("binSize", binSize);
        histogramData.put("histogram", histogram);
        return histogramData;
    }

    private JSONObject createHistogramJsonObject(Map<String, Object> histogramData){
        JSONObject message = new JSONObject();
        message.put("result", histogramData);
        return message;
    }

    @Override
    public JSONObject getPassengerData(Integer passengerId) {
        Passenger passenger = titanicPassengerDao.getPassengerData(passengerId);
        JSONObject message = new JSONObject();
        if(passenger != null){
            message.put("passenger",passenger.toJSON());
        } else{
            message.put("message","passenger doesn't exist");
        }
        return message;
    }

    @Override
    public JSONObject getRequestedAttributes(Integer passengerId, List<String>  attributesList) {
        List<String> attributes = titanicPassengerDao.getRequestedAttributes(passengerId, attributesList);
        JSONObject attributesJson = new JSONObject();
        attributesJson.put("requested_attributes",attributes);
        return attributesJson;
    }

    @Override
    public List<Passenger> getAllPassengers() {
        return titanicPassengerDao.getAllPassengers();
    }



}
