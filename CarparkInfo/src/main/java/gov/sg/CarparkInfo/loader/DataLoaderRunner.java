package gov.sg.CarparkInfo.loader;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import gov.sg.CarparkInfo.loader.CarParkDataLoader;

@Component
public class DataLoaderRunner implements CommandLineRunner {

    @Autowired
    private CarParkDataLoader dataLoader;

    @Override
    public void run(String... args) throws Exception {
        String filePath = "src/main/resources/hdb-carpark-information-20220824010400.csv";
        dataLoader.loadData(filePath);
    }
}