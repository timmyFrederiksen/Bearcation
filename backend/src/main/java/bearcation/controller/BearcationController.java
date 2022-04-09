package bearcation.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import bearcation.model.Park;
import bearcation.service.NPService;

//@CrossOrigin(origins = "*")
@RestController
@RequestMapping("bearcation/")
public class BearcationController {
  @Autowired
  private NPService npService;

  // TODO: Remove later
  @GetMapping("parksTest")
  public String getTest(){
    return npService.getTest();
  }

  @GetMapping("parks")
  public ResponseEntity<String> getParks(){
    return npService.getParks();
  }

  @GetMapping("countries")
  public List<Object> getCountries(){
    String url = "https://restcountries.eu/rest/v2/all";
    RestTemplate restTemplate = new RestTemplate();

    Object[] countries = restTemplate.getForObject(url, Object[].class);

    return Arrays.asList(countries);
  }
}
