package io.spring.cloud.samples.commerce.ui.services.items;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * @author sgupta
 * @since 2/28/16.
 */
@ConfigurationProperties(prefix = "items")
@RefreshScope
public class ItemProperties {

  private static final Random RANDOM = new Random();

  List<String> messages = new ArrayList<>();

  public List<String> getMessages() {
    return messages;
  }

  public void setMessages(List<String> messages) {
    this.messages = messages;
  }

  public void addMessage(String message) {
    this.messages.add(message);
  }

  public String getRandomItemFromProperty() {
    if(messages == null || messages.size() == 0) {
      return new Item(9999L, "Not available", "Not available", "Not available").toString();
    }
    else {
      int index = RANDOM.nextInt(messages.size());
      return new Item((long) index, messages.get(index), messages.get(index), messages.get(index)).toString();
    }
  }
}
