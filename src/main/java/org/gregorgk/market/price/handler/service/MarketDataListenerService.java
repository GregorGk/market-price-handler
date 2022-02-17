package org.gregorgk.market.price.handler.service;

import org.gregorgk.market.price.handler.data.MarketDataEntry;
import org.springframework.stereotype.Service;

@Service
public interface MarketDataListenerService {

  void addMarketData(MarketDataEntry entry);
}
