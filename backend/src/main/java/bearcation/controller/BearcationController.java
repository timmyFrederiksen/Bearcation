package bearcation.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import bearcation.model.Park;
import bearcation.service.NPService;

//@CrossOrigin(origins = "*")
@RestController
@RequestMapping("bearcation/")
public class BearcationController {
  @Autowired
  private NPService npService;

  private Park park;

  @GetMapping("parks")
  public String getTest(){
    park = new Park();
    return park.test();
  }
}
