package org.gregorgk.market.price.handler.implementation;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.math.BigDecimal;
import org.gregorgk.market.price.handler.data.MarketDataEntry;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

class LatestMarketDataTests {

  private static final Logger log = LoggerFactory.getLogger(LatestMarketDataTests.class);

  @Test
  void shouldRetainOnlyLatestDataRegardlessOfOrder() {
    // given
    long latestDataTimestamp = 1591012861001L;
    final LatestMarketData latestMarketData = new LatestMarketData();
    final String instrument = "EUR/USD";

    // when
    latestMarketData.addMarketData(MarketDataEntry.builder()
        .uniqueId("106")
        .instrument(instrument)
        .bid(new BigDecimal("1.1000"))
        .ask(new BigDecimal("1.2000"))
        .timestamp(latestDataTimestamp)
        .build());
    latestMarketData.addMarketData(MarketDataEntry.builder()
        .uniqueId("107")
        .instrument(instrument)
        .bid(new BigDecimal("1.1000"))
        .ask(new BigDecimal("1.2000"))
        .timestamp(latestDataTimestamp - 10000L)
        .build());

    // then
    final String expected = "106,EUR/USD,1.1000,1.2000,01-06-2020 12:01:01:001";
    final String actual = latestMarketData.getPrices(instrument);
    log.info("expected: " + expected);
    log.info("actual: " + actual);
    assertEquals(expected, actual);
  }

  @Test
  void shouldReturnBid() {
    // given
    final LatestMarketData latestMarketData = new LatestMarketData();
    final String instrument = "EUR/USD";

    // when
    latestMarketData.addMarketData(MarketDataEntry.builder()
        .uniqueId("106")
        .instrument(instrument)
        .bid(new BigDecimal("1.1000"))
        .ask(new BigDecimal("1.2000"))
        .timestamp(1591012861001L)
        .build());

    // then
    assertEquals(
        "1.1000",
        latestMarketData.getBid(instrument));
  }

  @Test
  void shouldReturnAsk() {
    // given
    final LatestMarketData latestMarketData = new LatestMarketData();
    final String instrument = "EUR/USD";

    // when
    latestMarketData.addMarketData(MarketDataEntry.builder()
        .uniqueId("106")
        .instrument(instrument)
        .bid(new BigDecimal("1.1000"))
        .ask(new BigDecimal("1.2000"))
        .timestamp(1591012861001L)
        .build());

    // then
    assertEquals(
        "1.2000",
        latestMarketData.getAsk(instrument));
  }

  @Test
  void shouldReturnEmpty() {
    // given
    final LatestMarketData latestMarketData = new LatestMarketData();
    final String instrument = "EUR/USD";

    // when
    latestMarketData.addMarketData(MarketDataEntry.builder()
        .uniqueId("106")
        .instrument(instrument)
        .bid(new BigDecimal("1.1000"))
        .ask(new BigDecimal("1.2000"))
        .timestamp(1591012861001L)
        .build());

    // then
    assertEquals(
        ",,,,",
        latestMarketData.getPrices("ILS/USD"));
  }
}
