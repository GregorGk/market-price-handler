package org.gregorgk.market.price.handler.data;

import java.io.IOException;
import java.io.StringReader;
import java.util.Collections;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;

@Slf4j
public class MessageUtils {

  private MessageUtils() {
  }

  public static List<String> toSingleLines(String stringMessage) {
    try {
      return Collections.unmodifiableList(IOUtils.readLines(new StringReader(stringMessage)));
    } catch (IOException e) {
      log.error("could not read message: " + stringMessage, e);
      return Collections.emptyList();
    }
  }
}
