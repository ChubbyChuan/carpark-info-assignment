package gov.sg.CarparkInfo.loader;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class DataLoaderRunner implements CommandLineRunner {

    @Autowired
    private CarParkDataLoader dataLoader;

    @Override
    public void run(String... args) throws Exception {
        String filePath = "src/main/resources/hdb-carpark-information-20220824010400.csv"; // It should be an environment variable in application.properties.
        try {
            dataLoader.loadData(filePath);
            System.out.println("Initial data loading completed.");
        } catch (Exception e) {
            System.err.println("Error during initial data loading: " + e.getMessage());
        }
    }

    @Scheduled(cron = "0 0 1 * * ?") // Runs daily at 1:00 AM, See crontab
    public void scheduleDataLoading() {
        String filePath = "src/main/resources/hdb-carpark-information-20220824010400.csv";
        try {
            dataLoader.loadData(filePath);
            System.out.println("Scheduled data loading completed.");
        } catch (Exception e) {
            System.err.println("Error during scheduled data loading: " + e.getMessage());
        }
    }
}
