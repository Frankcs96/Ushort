package tech.fcscode.ushort.controller;


import java.net.URI;
import java.net.URISyntaxException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.ModelAndView;
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
    if (!url.getLongUrl().isEmpty()) {
      url.setCreatedDate(LocalDateTime.now());
      url.setExpirationDate(urlService.getExpirationDate(url.getCreatedDate()));
      urlRepository.save(url);
      String shortUrl = urlService.generateShortUrl(url);
      url.setShortUrl(shortUrl);
      urlRepository.save(url);
      return ResponseEntity.status(201).body(url);
    }


    throw new ResponseStatusException(
        HttpStatus.BAD_REQUEST, "You have to enter a valid URL"
    );

  }


  @GetMapping("/{shortUrl}")
  public ModelAndView getLongUrl(@PathVariable String shortUrl)  {

    Long id = urlService.getIdFromShortUrl(shortUrl);

    Url url = urlRepository.findById(id).orElseThrow(() -> new ResponseStatusException(
        HttpStatus.NOT_FOUND, "URL NOT FOUND"
    ));

    return new ModelAndView("redirect:" + url.getLongUrl());
  }


}
