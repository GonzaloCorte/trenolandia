package com.gontreno.trenolandia.csv_helper;

import com.gontreno.trenolandia.arrival_inspector.domain.CheckedArrival;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.StringWriter;
import java.util.List;

public class CsvGenerator {

    private static final String CSV_HEADERS = "Numero treno,Destinazione,Ora pianificata,Ora effettiva,Ritardo";

    public static String generateCsv(List<CheckedArrival> checkedArrivals) {
        StringBuilder csvContent = new StringBuilder();

        csvContent.append(CSV_HEADERS).append("\n");

        checkedArrivals.forEach(checkedArrival ->
                csvContent
                    .append(checkedArrival.getTrainNumber())
                    .append(checkedArrival.getTrainStation())
                    .append(checkedArrival.getPlannedArrival())
                    .append(checkedArrival.getActualArrivals())
                    .append(checkedArrival.getDelay())
                        .append("\n")
                );

        return csvContent.toString();
    }
}
