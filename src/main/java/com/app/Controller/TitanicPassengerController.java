package com.app.Controller;

import com.app.Models.Passenger;
import com.app.Models.PassengerAttributes;
import com.app.Service.TitanicPassengerService;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.google.gson.Gson;

import java.util.List;

@RestController
public class TitanicPassengerController {

    private static final Gson gson = new Gson();

    @Autowired
    TitanicPassengerService titanicPassengerService;

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = "/get_histogram",produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<String> getHistogram() {
        try{
            JSONObject message = titanicPassengerService.getHistogram();
            return ResponseEntity.ok(message.toString());
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = "/get_passenger_data",produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<String> getPassengerData(Integer passengerId) {
        try{
            JSONObject message = titanicPassengerService.getPassengerData(passengerId);
            return ResponseEntity.ok(message.toString());

        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @ResponseStatus(HttpStatus.OK)
    @PostMapping(value = "/get_requested_attribute", consumes = {MediaType.APPLICATION_JSON_VALUE},produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<String> getRequestedAttributes(@RequestBody PassengerAttributes passengerAttributes) {
        try{
            JSONObject requestedAttributes = titanicPassengerService.getRequestedAttributes(passengerAttributes.getPassengerId(), passengerAttributes.getAttributeList());
            return ResponseEntity.ok(requestedAttributes.toString());
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = "/get_all_passengers",produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<String> getAllPassengers() {
        try{
            List<Passenger> passengers = titanicPassengerService.getAllPassengers();
            return ResponseEntity.ok(gson.toJson(passengers));
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
