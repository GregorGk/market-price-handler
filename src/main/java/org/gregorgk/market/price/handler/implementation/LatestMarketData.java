package org.gregorgk.market.price.handler.implementation;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import lombok.extern.slf4j.Slf4j;
import org.gregorgk.market.price.handler.data.MarketDataEntry;
import org.gregorgk.market.price.handler.service.MarketDataListenerService;
import org.gregorgk.market.price.handler.service.MarketDataService;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public final class LatestMarketData implements MarketDataService, MarketDataListenerService {

  private final Map<String, MarketDataEntry> latestPrices = new ConcurrentHashMap<>();

  @Override
  public String getBid(String instrument) {
    return latestPrices.containsKey(instrument) ?
        latestPrices.get(instrument).getBid().toString() : "";
  }

  @Override
  public String getAsk(String instrument) {
    return latestPrices.containsKey(instrument) ?
        latestPrices.get(instrument).getAsk().toString() : "";
  }

  @Override
  public String getPrices(String instrument) {
    return latestPrices.containsKey(instrument) ?
        latestPrices.get(instrument).toString() : ",,,,";
  }

  @Override
  public synchronized void addMarketData(MarketDataEntry entry) {
    if (latestPrices.containsKey(entry.getInstrument())) {
      final MarketDataEntry latestSoFar = latestPrices.get(entry.getInstrument());
      if (entry.getTimestamp() >= latestSoFar.getTimestamp()) {
        latestPrices.put(entry.getInstrument(), entry);
        log.debug("latest market data added: " + entry);
      } else {
        log.debug("skipped market data: " + entry);
      }
    } else {
      latestPrices.put(entry.getInstrument(), entry);
      log.debug("latest market data added: " + entry);
    }
  }
}
