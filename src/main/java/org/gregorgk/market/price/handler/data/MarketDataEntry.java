package org.gregorgk.market.price.handler.data;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.List;
import lombok.Builder;
import lombok.Value;
import lombok.extern.slf4j.Slf4j;

@Builder
@Value
@Slf4j
public class MarketDataEntry {

  private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern(
      "dd-MM-yyyy HH:mm:ss:SSS");

  String uniqueId;
  String instrument;
  BigDecimal bid;
  BigDecimal ask;
  long timestamp;

  public static MarketDataEntry fromSingleLineMessage(final SingleLineMessage singleLineMessage) {
    try {
      return MarketDataEntry.builder()
          .uniqueId(singleLineMessage.getUniqueId())
          .instrument(singleLineMessage.getInstrument())
          .bid(new BigDecimal(singleLineMessage.getBid()))
          .ask(new BigDecimal(singleLineMessage.getAsk()))
          .timestamp(LocalDateTime.parse(singleLineMessage.getTimestamp(), formatter)
              .toInstant(ZoneOffset.UTC)
              .toEpochMilli())
          .build();
    } catch (Exception e) {
      log.error("exception", e);
      log.error("returning an empty market entry");
      return MarketDataEntry.builder().build();
    }
  }

  public String toString() {
    return String.join(",",
        List.of(
            this.uniqueId,
            this.instrument,
            this.bid.toString(),
            this.ask.toString(),
            LocalDateTime.ofInstant(Instant.ofEpochMilli(timestamp), ZoneOffset.UTC)
                .format(formatter)
        )
    );
  }
}
