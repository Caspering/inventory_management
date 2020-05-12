package com.kasperin.inventory_management.CSV;

import com.kasperin.inventory_management.domain.Stationary;
import com.kasperin.inventory_management.repository.StationaryRepository;
import com.univocity.parsers.common.record.Record;
import com.univocity.parsers.csv.CsvParser;
import com.univocity.parsers.csv.CsvParserSettings;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class StationaryCsvImporter {

    private final StationaryRepository stationaryRepository;

    private final CsvParser parser;

    StationaryCsvImporter(StationaryRepository stationaryRepository) {
        this.stationaryRepository = stationaryRepository;

        CsvParserSettings settings = new CsvParserSettings();
        settings.setHeaderExtractionEnabled(true);
        settings.setLineSeparatorDetectionEnabled(true);
        this.parser = new CsvParser(settings);
    }

    @PostConstruct
    public void read() throws IOException{
        List<Record> records = this.parser
                .parseAllRecords(getReader("/stationary.csv"));

        List<Stationary> stationary = records.stream()
                .map(Stationary::new)
                .collect(Collectors.toList());
        insertData(stationary);
    }

    private Reader getReader(String s) throws UnsupportedEncodingException {
        return new InputStreamReader(this.getClass()
                .getResourceAsStream(s), "UTF-8");
    }

    private void insertData(List<Stationary> stationary) {
        stationaryRepository.saveAll(stationary);
    }

}
