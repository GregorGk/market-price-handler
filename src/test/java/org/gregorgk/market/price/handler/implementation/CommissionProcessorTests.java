package org.gregorgk.market.price.handler.implementation;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.math.BigDecimal;
import org.gregorgk.market.price.handler.data.MarketDataEntry;
import org.junit.jupiter.api.Test;

class CommissionProcessorTests {

  @Test
  void shouldChangeBid() {
    // given
    final MarketDataEntry given = MarketDataEntry.builder()
        .uniqueId("108")
        .instrument("GBP/USD")
        .bid(new BigDecimal("1.2500"))
        .ask(new BigDecimal("1.2560"))
        .timestamp(1591012861001L)
        .build();
    final CommissionProcessor commissionProcessor = new CommissionProcessor();

    // when
    final MarketDataEntry processed = commissionProcessor.process(given);

    // then
    assertEquals("1.2487500", processed.getBid().toString());
  }

  @Test
  void shouldChangeAsk() {
    // given
    final MarketDataEntry given = MarketDataEntry.builder()
        .uniqueId("108")
        .instrument("GBP/USD")
        .bid(new BigDecimal("1.2500"))
        .ask(new BigDecimal("1.2560"))
        .timestamp(1591012861001L)
        .build();
    final CommissionProcessor commissionProcessor = new CommissionProcessor();

    // when
    final MarketDataEntry processed = commissionProcessor.process(given);

    // then
    assertEquals("1.2572560", processed.getAsk().toString());
  }

  @Test
  void shouldNotChangeAnythingOtherThanBidAsk() {
    // given
    final MarketDataEntry given = MarketDataEntry.builder()
        .uniqueId("108")
        .instrument("GBP/USD")
        .bid(new BigDecimal("1.2500"))
        .ask(new BigDecimal("1.2560"))
        .timestamp(1591012861001L)
        .build();
    final CommissionProcessor commissionProcessor = new CommissionProcessor();

    // when
    final MarketDataEntry processed = commissionProcessor.process(given);

    // then
    assertEquals("108", processed.getUniqueId());
    assertEquals("GBP/USD", processed.getInstrument());
    assertEquals(1591012861001L, processed.getTimestamp());
  }
}
