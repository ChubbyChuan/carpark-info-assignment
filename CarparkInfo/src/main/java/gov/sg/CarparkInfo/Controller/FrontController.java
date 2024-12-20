package gov.sg.CarparkInfo.Controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import gov.sg.CarparkInfo.Repository.CarParkRepository;
import gov.sg.CarparkInfo.Service.CarParkService;
import gov.sg.CarparkInfo.Model.CarPark;

@Controller
@RequestMapping("/carparks")
@CrossOrigin(origins = "*")
public class FrontController {

    @Autowired
    CarParkService CarParkSvc;

    @Autowired
    CarParkRepository CarParkRepo;

    @GetMapping("/filter")
    @ResponseBody
    public ResponseEntity<?> filterCarParks(
            @RequestParam(required = false) String freeParking,
            @RequestParam(required = false) String nightParking,
            @RequestParam(required = false) Float height,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        try {

            List<CarPark> filteredCarParks = CarParkSvc.filterCarParks(freeParking, nightParking, height, page, size);

            return ResponseEntity.ok(filteredCarParks);
        } catch (Exception e) {
            return ResponseEntity.status(404).body("No parking space found");
        }
    }

    @GetMapping("/filterGeo") // For frontend dev, they will need in the scenario where the user just want to
                              // find the nearest carpark based on their location
    @ResponseBody
    public ResponseEntity<?> filterCarParksGeo(
            @RequestParam(required = false) String freeParking,
            @RequestParam(required = false) String nightParking,
            @RequestParam(required = false) Float height,
            @RequestParam(required = false) Float xCoord,
            @RequestParam(required = false) Float yCoord,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {

        try {
            List<CarPark> filteredCarParks = CarParkSvc.filterCarParksWithGeo(freeParking, nightParking, height, xCoord,
                    yCoord, page, size);
            return ResponseEntity.ok(filteredCarParks);
        } catch (Exception e) {
            return ResponseEntity.status(404).body("No parking space found");
        }
    }

    @GetMapping("/favourites")
    @ResponseBody
    public ResponseEntity<?> getFavourites(
            @RequestParam(required = true) String user,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        try {
            List<CarPark> filteredCarParks = CarParkSvc.filterFavourites(user, page, size);
            System.out.println("Output ->" + filteredCarParks);
            return ResponseEntity.ok(filteredCarParks);
        } catch (Exception e) {
            return ResponseEntity.status(404).body("No parking space found");
        }
    }

    @PostMapping("/favourites")
    @ResponseBody
    public ResponseEntity<?> postFavourite(
            @RequestParam String user,
            @RequestBody String favourites) {
        try {
            CarParkSvc.addFavourite(user, favourites);
            return ResponseEntity.status(201).body("Added successfully!");
        } catch (Exception e) {
            return ResponseEntity.status(404).body("Failed to add favourites");
        }
    }
}