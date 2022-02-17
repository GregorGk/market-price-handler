package org.gregorgk.market.price.handler.data;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import org.junit.jupiter.api.Test;

class MarketDataEntryTests {

  @Test
  void shouldBeReadable() {
    // given
    final MarketDataEntry entry = MarketDataEntry.builder()
        .uniqueId("106")
        .instrument("EUR/USD")
        .bid(new BigDecimal("1.1000"))
        .ask(new BigDecimal("1.2000"))
        .timestamp(1591012861001L)
        .build();

    // then
    assertEquals("106,EUR/USD,1.1000,1.2000,01-06-2020 12:01:01:001", entry.toString());
  }

  @Test
  void shouldParseUniqueId() {
    // given
    final SingleLineMessage singleLineMessage = SingleLineMessage.builder()
        .uniqueId("106")
        .instrument("EUR/USD")
        .bid("1.1000")
        .ask("1.2000")
        .timestamp("01-06-2020 12:01:01:001")
        .build();

    // when
    final MarketDataEntry entry = MarketDataEntry.fromSingleLineMessage(singleLineMessage);

    // then
    assertEquals(singleLineMessage.getUniqueId(), entry.getUniqueId());
  }

  @Test
  void shouldParseInstrument() {
    // given
    final SingleLineMessage singleLineMessage = SingleLineMessage.builder()
        .uniqueId("106")
        .instrument("EUR/USD")
        .bid("1.1000")
        .ask("1.2000")
        .timestamp("01-06-2020 12:01:01:001")
        .build();

    // when
    final MarketDataEntry entry = MarketDataEntry.fromSingleLineMessage(singleLineMessage);

    // then
    assertEquals(singleLineMessage.getInstrument(), entry.getInstrument());
  }

  @Test
  void shouldParseBid() {
    // given
    final SingleLineMessage singleLineMessage = SingleLineMessage.builder()
        .uniqueId("106")
        .instrument("EUR/USD")
        .bid("1.1000")
        .ask("1.2000")
        .timestamp("01-06-2020 12:01:01:001")
        .build();

    // when
    final MarketDataEntry entry = MarketDataEntry.fromSingleLineMessage(singleLineMessage);

    // then
    assertEquals(new BigDecimal(singleLineMessage.getBid()), entry.getBid());
  }

  @Test
  void shouldParseAsk() {
    // given
    final SingleLineMessage singleLineMessage = SingleLineMessage.builder()
        .uniqueId("106")
        .instrument("EUR/USD")
        .bid("1.1000")
        .ask("1.2000")
        .timestamp("01-06-2020 12:01:01:001")
        .build();

    // when
    final MarketDataEntry entry = MarketDataEntry.fromSingleLineMessage(singleLineMessage);

    // then
    assertEquals(new BigDecimal(singleLineMessage.getAsk()), entry.getAsk());
  }

  @Test
  void shouldParseTimestamp() {
    // given
    final SingleLineMessage singleLineMessage = SingleLineMessage.builder()
        .uniqueId("106")
        .instrument("EUR/USD")
        .bid("1.1000")
        .ask("1.2000")
        .timestamp("01-06-2020 12:01:01:001")
        .build();
    final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss:SSS");

    // when
    final MarketDataEntry entry = MarketDataEntry.fromSingleLineMessage(singleLineMessage);

    // then
    assertEquals(
        LocalDateTime.parse(singleLineMessage.getTimestamp(), formatter).toInstant(ZoneOffset.UTC)
            .toEpochMilli(),
        entry.getTimestamp());
  }
}
