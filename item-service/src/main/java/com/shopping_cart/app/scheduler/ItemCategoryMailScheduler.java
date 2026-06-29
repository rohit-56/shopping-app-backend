package com.shopping_cart.app.scheduler;

import com.shopping_cart.app.common.enums.CategoryType;
import com.shopping_cart.app.http.response.ItemResponse;
import com.shopping_cart.app.service.EmailService;
import com.shopping_cart.app.service.IItemService;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class ItemCategoryMailScheduler {

  private final IItemService itemService;
  private final EmailService emailService;
  private final String recipient;

  public ItemCategoryMailScheduler(
      IItemService itemService,
      EmailService emailService,
      @Value("${app.scheduler.recipient:rohitmou25@gmail.com}") String recipient) {
    this.itemService = itemService;
    this.emailService = emailService;
    this.recipient = recipient;
  }

  @Scheduled(cron = "${app.scheduler.cron:0 0 9 * * *}")
  public void sendDailyCategoryDetails() {
    log.info("Starting scheduled item category email dispatch to {}", recipient);

    Map<String, List<ItemResponse>> itemsByCategory = new LinkedHashMap<>();
    for (CategoryType categoryType : CategoryType.values()) {
      List<ItemResponse> items = itemService.getItemsByCategory(categoryType.getCategoryType());
      itemsByCategory.put(categoryType.getCategoryType(), items);
    }

    boolean hasAnyItems = itemsByCategory.values().stream().anyMatch(list -> !list.isEmpty());
    if (!hasAnyItems) {
      log.info("No items found for any category. Skipping email dispatch.");
      return;
    }

    emailService.sendCategoryItemsEmail(recipient, itemsByCategory);
    log.info("Scheduled item category email dispatch completed");
  }
}
