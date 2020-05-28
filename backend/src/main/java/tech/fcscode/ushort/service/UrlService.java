package tech.fcscode.ushort.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import org.apache.tomcat.jni.Local;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tech.fcscode.ushort.model.Url;

@Service
public class UrlService {

  private Converter converter;

  @Autowired
  public UrlService(Converter converter) {
    this.converter = converter;
  }

  public String generateShortUrl(Url url) {

    return converter.convertToBase62(url.getId());
  }

  public Long getIdFromShortUrl(String shortUrl) {
    return converter.decode(shortUrl);
  }

  public LocalDateTime getExpirationDate (LocalDateTime date) {

    return date.plusDays(10);

  }

}
