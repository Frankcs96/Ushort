package tech.fcscode.ushort.service;

import java.net.URL;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import org.apache.commons.validator.Validator;
import org.apache.commons.validator.routines.UrlValidator;
import org.apache.tomcat.jni.Local;
import org.hibernate.validator.internal.constraintvalidators.hv.URLValidator;
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

  public LocalDateTime getExpirationDate(LocalDateTime date) {

    return date.plusDays(10);

  }

  public String checkProtocol(String url) {

    String result = url.toLowerCase();

    if (!result.startsWith("http://") && !result.startsWith("https://")) {
      return "http://" + result;
    }

    return result;

  }

  public boolean checkIfUrlIsValid(String url) {

    UrlValidator urlValidator = new UrlValidator(UrlValidator.ALLOW_2_SLASHES);

    if (urlValidator.isValid(url)) {
      return true;
    }

    return false;

  }


}
