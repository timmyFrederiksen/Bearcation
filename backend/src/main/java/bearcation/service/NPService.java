package bearcation.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import bearcation.model.Park;

import java.util.Arrays;
import java.util.List;

@Service
public class NPService {
  private String baseUrl = "https://developer.nps.gov/api/v1/";
  private String api_key = "api_key=os9gdUMtfjWN14L4zo5a33m7m4beCQAC1xrZAjh9";

  private String url = baseUrl + "parks?limit=500&" + api_key;

  // TODO: Remove Later
  public String getTest(){
    return url;
  }

  public List<Object> getParks(){
    RestTemplate restTemplate = new RestTemplate();

    Object[] parks = restTemplate.getForObject(url, Object[].class);

    return Arrays.asList(parks);
  }
}
