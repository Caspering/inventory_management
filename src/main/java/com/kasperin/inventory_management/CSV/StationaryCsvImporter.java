package com.kasperin.inventory_management.CSV;

import com.kasperin.inventory_management.domain.Stationary;
import com.kasperin.inventory_management.repository.StationaryRepository;
import com.univocity.parsers.common.record.Record;
import com.univocity.parsers.csv.CsvParser;
import com.univocity.parsers.csv.CsvParserSettings;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnResource;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@Slf4j
@ConditionalOnResource(resources = StationaryCsvImporter.RESOURCE_LOCATION)
@Service
public class StationaryCsvImporter {

    public static final String RESOURCE_LOCATION = "/stationary.csv";
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

        //Predicate<Stationary> exists = stationary -> (stationaryRepository.existsByBarcode(stationary.getBarcode()));

        List<Record> records = this.parser
                .parseAllRecords(getReader(RESOURCE_LOCATION));

        List<Stationary> stationarys = records
                .stream()
                .map(Stationary::new)
              //  .filter(exists)
                .collect(Collectors.toList());
        //stationarys.removeIf(stationary -> (stationaryRepository.existsByBarcode(stationary.getBarcode())));

        insertData(stationarys);

    }

    private Reader getReader(String s) throws UnsupportedEncodingException {

                return new InputStreamReader(this.getClass()
                        .getResourceAsStream(s), "UTF-8");
    }

    private void insertData(List<Stationary> stationarys) {
//        for (Stationary stationary : stationarys) {
//            if (!(stationaryRepository.existsByBarcode(stationary.getBarcode()))) {
                stationaryRepository.saveAll(stationarys);

//                log.info("The stationary item with name: " + stationary.getName() + ", has been imported");
//
//            } else
//
//                log.info("The stationary with name: " + stationary.getName() +
//                        "and barcode: "+stationary.getBarcode()+
//                        " was not imported because it already exists");
//
//        }
    }

}
