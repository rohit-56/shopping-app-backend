package com.shopping_cart.app.service;

import com.shopping_cart.app.http.response.ItemResponse;
import java.util.List;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class EmailService {

  private final JavaMailSender mailSender;

  public EmailService(JavaMailSender mailSender) {
    this.mailSender = mailSender;
  }

  public void sendCategoryItemsEmail(String to, Map<String, List<ItemResponse>> itemsByCategory) {
    if (itemsByCategory == null || itemsByCategory.isEmpty()) {
      log.info("No category item data available to send");
      return;
    }

    SimpleMailMessage message = new SimpleMailMessage();
    message.setTo(to);
    message.setSubject("Item category report from item-service");
    message.setText(buildMessage(itemsByCategory));

    try {
      mailSender.send(message);
      log.info("Item category email sent successfully to {}", to);
    } catch (MailException ex) {
      log.error("Failed to send category email to {}", to, ex);
    }
  }

  private String buildMessage(Map<String, List<ItemResponse>> itemsByCategory) {
    StringBuilder builder = new StringBuilder();
    builder.append("Hello,\n\n");
    builder.append("Here are the latest item details by category:\n\n");

    for (Map.Entry<String, List<ItemResponse>> entry : itemsByCategory.entrySet()) {
      builder.append("Category: ").append(entry.getKey()).append("\n");
      if (entry.getValue().isEmpty()) {
        builder.append("  No items available for this category.\n\n");
        continue;
      }

      for (ItemResponse itemResponse : entry.getValue()) {
        builder
            .append("  - ")
            .append(itemResponse.getItemName())
            .append(" (ID: ")
            .append(itemResponse.getItemId())
            .append(")\n");
        builder.append("      Description: ").append(itemResponse.getDescription()).append("\n");
        builder.append("      Amount: ").append(itemResponse.getAmount()).append("\n");
        builder.append("      Rating: ").append(itemResponse.getRating()).append("\n");
        builder.append("      Quantity: ").append(itemResponse.getQuantity()).append("\n\n");
      }
    }

    builder.append("Regards,\nitem-service scheduler\n");
    return builder.toString();
  }
}
