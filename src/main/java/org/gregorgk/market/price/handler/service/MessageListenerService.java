package org.gregorgk.market.price.handler.service;

import org.springframework.stereotype.Service;

@Service
public interface MessageListenerService {

  void onMessage(String message);
}
