package org.example.agentmain.utils;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import java.io.IOException;
import java.time.LocalDateTime;

public class CustomLocalDateTimeDeserializer extends JsonDeserializer<LocalDateTime> {
  @Override
  public LocalDateTime deserialize(JsonParser jsonParser, DeserializationContext ctxt)
      throws IOException, JsonProcessingException {
    int[] dateTimeArray = jsonParser.readValueAs(int[].class);

    // Преобразуем массив в LocalDateTime
    // Формат массива: [год, месяц, день, час, минута, секунда, наносекунды]
    if (dateTimeArray != null && dateTimeArray.length >= 6) {
      int year = dateTimeArray[0];
      int month = dateTimeArray[1];
      int day = dateTimeArray[2];
      int hour = dateTimeArray[3];
      int minute = dateTimeArray[4];
      int second = dateTimeArray[5];

      // Наносекунды могут отсутствовать
      int nano = 0;
      if (dateTimeArray.length >= 7) {
        nano = dateTimeArray[6];
      }

      return LocalDateTime.of(year, month, day, hour, minute, second, nano);
    }

    return null;
  }
}

