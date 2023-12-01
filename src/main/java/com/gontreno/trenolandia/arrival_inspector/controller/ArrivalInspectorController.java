package com.gontreno.trenolandia.arrival_inspector.controller;

import com.gontreno.trenolandia.arrival_inspector.domain.CheckedArrival;
import com.gontreno.trenolandia.arrival_inspector.domain.PlainArrival;
import com.gontreno.trenolandia.arrival_inspector.inspector.ArrivalInspector;
import com.gontreno.trenolandia.csv_helper.CsvGenerator;
import com.gontreno.trenolandia.csv_helper.CvsReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/arrivals")
public class ArrivalInspectorController {

    private static final String OUTPUT_FILE_NAME = "output";
    private static final String OUTPUT_FILE_FORMAT = ".csv";

    @Autowired
    private ArrivalInspector arrivalInspector;

    @PostMapping( value = "/calculateDelay", produces = "text/csv")
    public ResponseEntity<byte[]> inspectArrivalsTiming(
            @RequestParam("plannedArrivals") MultipartFile expectedArrivalsFile,
            @RequestParam("actualArrivals") MultipartFile actualArrivalsFile) {

        List<PlainArrival> plannedArrivals = CvsReader.csvToArrivals(expectedArrivalsFile);
        List<PlainArrival> actualArrivals = CvsReader.csvToArrivals(actualArrivalsFile);

        List<CheckedArrival> fiveMoreDelayed = arrivalInspector.filterFiveTrainsMoreDelayed(plannedArrivals, actualArrivals);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        headers.setContentDispositionFormData("attachment", OUTPUT_FILE_NAME + OUTPUT_FILE_FORMAT);

        byte[] csvBytes = CsvGenerator.generateCsv(fiveMoreDelayed).getBytes();

        return new ResponseEntity<>(csvBytes, headers, HttpStatus.OK);
    }
}
