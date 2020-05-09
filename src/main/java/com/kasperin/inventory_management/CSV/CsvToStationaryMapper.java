//package com.kasperin.inventory_management.CSV;
//
//import com.kasperin.inventory_management.domain.Stationary;
//import com.univocity.parsers.csv.CsvParser;
//import com.univocity.parsers.csv.CsvParserSettings;
//import org.springframework.stereotype.Component;
//
//import java.io.InputStreamReader;
//import java.io.Reader;
//import java.io.UnsupportedEncodingException;
//import java.util.List;
//
//
//@Component
//public class CsvToStationaryMapper {
//
//    Stationary stationary;
//    CsvParserSettings settings = new CsvParserSettings();
//
//    CsvParser parser = new CsvParser(settings);
//
//    // parses all records in one go.
//    List<Stationary> stationaries;
//
//    {
//        try {
//            stationaries = parser.parseAllRecords(getReader("/examples/example.csv"));
//        } catch (UnsupportedEncodingException e) {
//            e.printStackTrace();
//        }
//    }
//
//    private Reader getReader(String s) throws UnsupportedEncodingException {
//        return new InputStreamReader(this.getClass().getResourceAsStream(s
//        ), "UTF-8");
//    }
//
//}
//
//
//
//
//
//
//
//
//
//
////    Stationary csvToStationary(Csv csv) {
////       if (csv == null) {
////           return null;
////       }
////
////       Stationary stationary = new Stationary();
////       //stationary.setId( csv.getId() );
////       stationary.setName( csv.getName() );
////       stationary.setBarcode( csv.getBarcode() );
////       stationary.setPrice( csv.getPrice() );
////       stationary.setInStockQuantity( csv.getInStockQuantity() );
////
////       return stationary;
//
////    }
//
//
//
//}
//
