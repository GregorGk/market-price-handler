package org.gregorgk.market.price.handler.data;


import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class SingleLineMessageTests {

  @Test
  void shouldReadUniqueIdFromString() {
    // given
    final String messageString = "106, EUR/USD, 1.1000,1.2000,01-06-2020 12:01:01:001";

    // when
    final SingleLineMessage singleLineMessage = SingleLineMessage.fromString(messageString);

    // then
    assertEquals("106", singleLineMessage.getUniqueId());
  }

  @Test
  void shouldReadInstrumentFromString() {
    // given
    final String messageString = "107, EUR/JPY, 119.60,119.90,01-06-2020 12:01:02:002";

    // when
    final SingleLineMessage singleLineMessage = SingleLineMessage.fromString(messageString);

    // then
    assertEquals("EUR/JPY", singleLineMessage.getInstrument());
  }

  @Test
  void shouldReadBidFromString() {
    // given
    final String messageString = "108, GBP/USD, 1.2500,1.2560,01-06-2020 12:01:02:002";

    // when
    final SingleLineMessage singleLineMessage = SingleLineMessage.fromString(messageString);

    // then
    assertEquals("1.2500", singleLineMessage.getBid());
  }

  @Test
  void shouldReadAskFromString() {
    // given
    final String messageString = "109, GBP/USD, 1.2499,1.2561,01-06-2020 12:01:02:100";

    // when
    final SingleLineMessage singleLineMessage = SingleLineMessage.fromString(messageString);

    // then
    assertEquals("1.2561", singleLineMessage.getAsk());
  }

  @Test
  void shouldReadTimestampFromString() {
    // given
    final String messageString = "110, EUR/JPY, 119.61,119.91,01-06-2020 12:01:02:110";

    // when
    final SingleLineMessage singleLineMessage = SingleLineMessage.fromString(messageString);

    // then
    assertEquals("01-06-2020 12:01:02:110", singleLineMessage.getTimestamp());
  }
}
