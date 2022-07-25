package org.gregorgk.market.price.handler.service;

import org.springframework.stereotype.Service;

/**
 * Used to provide the latest textual representation of prices.
 */
@Service
public interface MarketDataService {

  String getBid(String instrument);

  String getAsk(String instrument);

  String getPrices(String instrument);
}
