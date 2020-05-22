package com.kasperin.inventory_management.CSV;

import com.kasperin.inventory_management.domain.ProcessedFood;
import com.kasperin.inventory_management.repository.ProcessedFoodRepo;
import com.univocity.parsers.common.DataProcessingException;
import com.univocity.parsers.common.processor.BeanListProcessor;
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
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@ConditionalOnResource(resources = ProcessedFoodCsvImporter.RESOURCE_LOCATION)
@Service
public class ProcessedFoodCsvImporter {

    public static final String RESOURCE_LOCATION = "/processed_food.csv";
    private final ProcessedFoodRepo processedFoodRepo;
    private final CsvParser parser;

    BeanListProcessor<ProcessedFood> rowProcessor = new BeanListProcessor<ProcessedFood>(ProcessedFood.class);


    ProcessedFoodCsvImporter(ProcessedFoodRepo processedFoodRepo) {
        this.processedFoodRepo = processedFoodRepo;
        CsvParserSettings settings = new CsvParserSettings();
        settings.setHeaderExtractionEnabled(true);
        settings.setLineSeparatorDetectionEnabled(true);
        settings.setProcessor(rowProcessor);
//        settings.setProcessor(new RowProcessor() {
//            @Override
//            public void processStarted(ParsingContext parsingContext) {
//                System.out.println("Started to process rows of data.");
//
//            }
//
//            @Override
//            public void rowProcessed(String[] strings, ParsingContext parsingContext) {
//
//            }
//
//            @Override
//            public void processEnded(ParsingContext parsingContext) {
//
//            }
//        });

        this.parser = new CsvParser(settings);
    }


    @PostConstruct
    public void read() throws IOException, DataProcessingException {
/*
        //Map to store and compare barcodes in record for duplicates
        Map<String, Record> records = new HashMap<>();

        // call beginParsing to read records one by one, iterator-style.
        parser.beginParsing(getReader(RESOURCE_LOCATION));

        Record record;

        while ((record = parser.parseNextRecord()) != null) {

            if (!records.containsKey(record.getString("barcode"))) {
              try {


 //                 String expDate = DateTimeFormatter.ofPattern("yyyy-MM-dd").format((TemporalAccessor) record.getDate("exp"));
                  Date expDate = ((Date)(record.getDate("exp")));
 //                 String mfgDate = DateTimeFormatter.ofPattern("yyyy-MM-dd").format((TemporalAccessor) record.getDate("mfg"));
                  Date mfgDate = ((Date)(record.getDate("mfg")));

                  //compare dates
                  int diff = expDate.compareTo(mfgDate);

                  if (diff == 0) {

                      System.out.println(expDate + " is Equal to " + mfgDate);

                  } else if (diff > 0) {

                      records.put(record.getString("barcode"), record);
                      System.out.println(expDate + " is AFTER " + mfgDate);

                  } else {

                      System.out.println(expDate + " is BEFORE " + mfgDate);

                  }

              }catch (DataProcessingException e){
                  log.error("can not convert: " + e);
              }

            }
        }*/

/*        //convert Map to List
   //     List<Record> recordList = new ArrayList<>(records.values());
        List<ProcessedFood> processedFoods = rowProcessor.getBeans();

//       List<ProcessedFood> processedFoods = recordList
//                .stream()
//                .map(ProcessedFood::new)
//               .collect(Collectors.toList());

        insertData(processedFoods);*/



    parser.parse(getReader(RESOURCE_LOCATION));
    List<ProcessedFood> processedFoods = rowProcessor.getBeans();
//    List<ProcessedFood> filtered = new ArrayList<>();

//                for (ProcessedFood processedFood : processedFoods) {
//            if (processedFoodRepo.existsByBarcode(processedFood.getBarcode()))
                //filtered.add(processedFood);
//            processedFoods.remove(processedFood);
//            }
        insertData(processedFoods);
//                    insertData(filtered);
//                log.info("This in ");
//           }else
//               log.info("we are in the else of the if method in read meaning barcode exists so duplicate caugth");
//       }




    }


    private Reader getReader(String s) throws UnsupportedEncodingException {
        return new InputStreamReader(this.getClass()
                .getResourceAsStream(s), "UTF-8");
    }


    private void insertData(List<ProcessedFood> processedFoods) {
        for (ProcessedFood processedFood : processedFoods) {
            if (!(processedFoodRepo.existsByBarcode(processedFood.getBarcode()))) {
                processedFoodRepo.save(processedFood);

                log.info("The processed food item: " + processedFood.getName() + ", has been imported");

            } else

                log.info("The processed food item with name: " + processedFood.getName() +
                        "and barcode: "+processedFood.getBarcode()+
                        " was not imported because it already exists");

        }
    }



}
