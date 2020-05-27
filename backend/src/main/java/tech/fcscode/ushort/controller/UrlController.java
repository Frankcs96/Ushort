package tech.fcscode.ushort.controller;


import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import tech.fcscode.ushort.model.Url;
import tech.fcscode.ushort.repository.UrlRepository;


@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class UrlController {


  private UrlRepository urlRepository;


  @Autowired
  public UrlController(UrlRepository urlRepository) {
    this.urlRepository = urlRepository;

  }

  @PostMapping("/")
  public ResponseEntity<?> createUrl(@RequestBody Url url) {
    if(url != null) {
      url.setCreatedDate(new Date());
      url.setExpirationDate(new Date());
      urlRepository.save(url);

      return ResponseEntity.status(201).body("Url saved");
    }

    return ResponseEntity.noContent().build();

  }


}
