package tech.fcscode.ushort.service;

import org.springframework.stereotype.Service;

@Service
public class Converter {
  private static final String allowedString = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
  private char[] allowedCharacters = allowedString.toCharArray();
  private int base = allowedCharacters.length;

  public String convertToBase62(Long input) {
    StringBuilder encodedString = new StringBuilder();

    if(input == 0) {
      return String.valueOf(allowedCharacters[0]);
    }

    while (input > 0) {
      encodedString.append(allowedCharacters[(int) (input % base)]);
      input = input / base;
    }

    return encodedString.reverse().toString();
  }


  public long decode(String input) {
    char[] characters = input.toCharArray();
    int length = characters.length;

    int decoded = 0;


    int counter = 1;
    for (int i = 0; i < length; i++) {
      decoded += allowedString.indexOf(characters[i]) * Math.pow(base, length - counter);
      counter++;
    }
    return decoded;
  }
}
