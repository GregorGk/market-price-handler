package org.gregorgk.market.price.handler.data;


import com.opencsv.CSVParser;
import java.io.IOException;
import lombok.Builder;
import lombok.Value;
import lombok.extern.slf4j.Slf4j;

@Builder
@Value
@Slf4j
public class SingleLineMessage {

  private static final CSVParser parser = new CSVParser();

  String uniqueId;
  String instrument;
  String bid;
  String ask;
  String timestamp;

  public static SingleLineMessage fromString(String singleLineMessageString) {
    try {
      final String[] values = parser.parseLine(singleLineMessageString);
      return SingleLineMessage.builder()
          .uniqueId(values[0].trim())
          .instrument(values[1].trim())
          .bid(values[2].trim())
          .ask(values[3].trim())
          .timestamp(values[4].trim())
          .build();
    } catch (IOException e) {
      log.error("could not read a single line message from string: %s" + singleLineMessageString,
          e);
      log.error("returning an empty single line message");
      return SingleLineMessage.builder().build();
    }
  }
}
