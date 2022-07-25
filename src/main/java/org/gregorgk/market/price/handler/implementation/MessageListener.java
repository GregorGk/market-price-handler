package org.gregorgk.market.price.handler.implementation;

import java.util.List;
import java.util.stream.Collectors;
import org.gregorgk.market.price.handler.data.MarketDataEntry;
import org.gregorgk.market.price.handler.data.MessageUtils;
import org.gregorgk.market.price.handler.data.SingleLineMessage;
import org.gregorgk.market.price.handler.service.MarketDataListenerService;
import org.gregorgk.market.price.handler.service.MessageListenerService;
import org.gregorgk.market.price.handler.service.ProcessorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MessageListener implements MessageListenerService {

  private final ProcessorService processor;
  private final MarketDataListenerService listener;

  @Autowired
  public MessageListener(
      ProcessorService processorService,
      MarketDataListenerService listenerService) {
    this.processor = processorService;
    this.listener = listenerService;
  }

  @Override
  public void onMessage(final String stringMessage) {
    final List<String> singleLines = MessageUtils.toSingleLines(stringMessage);
    final List<SingleLineMessage> singleLineMessages = singleLines.stream()
        .map(SingleLineMessage::fromString)
        .filter(singleLineMessage -> singleLineMessage.getUniqueId() != null)
        .collect(Collectors.toList());
    final List<MarketDataEntry> entries = singleLineMessages.stream()
        .map(MarketDataEntry::fromSingleLineMessage)
        .filter(marketDataEntry -> marketDataEntry.getUniqueId() != null)
        .collect(Collectors.toList());
    final List<MarketDataEntry> processedEntries = entries.stream()
        .map(processor::process)
        .collect(Collectors.toList());
    processedEntries.forEach(listener::addMarketData);
  }
}
