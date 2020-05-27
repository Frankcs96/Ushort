package tech.fcscode.ushort.controller;


import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import tech.fcscode.ushort.model.Url;
import tech.fcscode.ushort.repository.UrlRepository;
import tech.fcscode.ushort.service.UrlService;


@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class UrlController {


  private UrlRepository urlRepository;
  private UrlService urlService;



  @Autowired
  public UrlController(UrlRepository urlRepository, UrlService urlService) {
    this.urlRepository = urlRepository;
    this.urlService = urlService;

  }

  @PostMapping("/")
  public ResponseEntity<?> createUrl(@RequestBody Url url) {
    if(url != null) {
      url.setCreatedDate(new Date());
      url.setExpirationDate(new Date());
      urlRepository.save(url);
      String shortUrl = urlService.generateShortUrl(url);
      url.setShortUrl(shortUrl);
      urlRepository.save(url);

      return ResponseEntity.status(201).body(url);
    }

    return ResponseEntity.noContent().build();

  }


  @GetMapping("/{shortUrl}")
    public ResponseEntity<?> getLongUrl(@PathVariable String shortUrl) {

    // transform shortUrl in ID
    Long id = urlService.getIdFromShortUrl(shortUrl);

    Url url = urlRepository.findById(id).orElse(null);

    if (url == null) {
      throw new ResponseStatusException(
          HttpStatus.NOT_FOUND, "URL not found"
      );
    }

    return ResponseEntity.ok(url);

  }


}
