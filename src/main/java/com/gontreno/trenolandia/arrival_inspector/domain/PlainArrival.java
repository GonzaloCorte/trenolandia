package com.gontreno.trenolandia.arrival_inspector.domain;

import java.time.Duration;
import java.time.OffsetTime;
import java.time.ZonedDateTime;

public class PlainArrival {

    private String trainNumber;
    private String trainStation;
    private ZonedDateTime arrival;

    public PlainArrival(String trainNumber, String trainStation, ZonedDateTime arrival) {
        this.trainNumber = trainNumber;
        this.trainStation = trainStation;
        this.arrival = arrival;
    }

    public String getTrainNumber() {
        return trainNumber;
    }

    public String getTrainStation() {
        return trainStation;
    }

    public ZonedDateTime getArrival() {
        return arrival;
    }

    public OffsetTime formatArrivalToOffsetTime() {
        return arrival.toOffsetDateTime().toOffsetTime();
    }

    public long getDelayFromComparingActualArrival(ZonedDateTime actualArrival) {
        Duration difference = Duration.between(arrival, actualArrival);

        return difference.getSeconds();
    }
}