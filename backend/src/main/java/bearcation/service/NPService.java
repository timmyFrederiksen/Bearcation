package bearcation.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import bearcation.model.Park;

import java.util.List;

@Service
public class NPService {
  // Code will go here
  // This is currently just temporary test code
  @Autowired
  private List<Park> parks;

  public List<Park> getParks(){
    return parks;
  }
}
