package org.gregorgk.market.price.handler.implementation;

import java.math.BigDecimal;
import org.gregorgk.market.price.handler.data.MarketDataEntry;
import org.gregorgk.market.price.handler.service.ProcessorService;
import org.springframework.stereotype.Component;

@Component
public class CommissionProcessor implements ProcessorService {

  private static final BigDecimal ON_BID_FACTOR = new BigDecimal("0.999");
  private static final BigDecimal ON_ASK_FACTOR = new BigDecimal("1.001");

  @Override
  public MarketDataEntry process(final MarketDataEntry entry) {
    return MarketDataEntry.builder()
        .uniqueId(entry.getUniqueId())
        .instrument(entry.getInstrument())
        .bid(entry.getBid().multiply(ON_BID_FACTOR))
        .ask(entry.getAsk().multiply(ON_ASK_FACTOR))
        .timestamp(entry.getTimestamp())
        .build();
  }
}
