package com.hookiesolutions.cat.prototype.ruleengine

import com.hookiesolutions.cat.prototype.common.request.DevicePositionUpdateWebhookMessage
import com.hookiesolutions.cat.prototype.ruleengine.property.PropertyEventRule
import org.slf4j.Logger
import org.springframework.stereotype.Component

/**
 *
 * @author Arthur Kazemi<bidadh@gmail.com>
 * @since 25/8/20 14:37
 */
@Component
class PropertyRuleEngine(
  private val rules: List<PropertyEventRule>,
  private val log: Logger
) {

  fun topic(payload: DevicePositionUpdateWebhookMessage): String? {
    log.info("Processing position update for: '{}'", payload.deviceId)
    return rules
      .filter { it.accept(payload) }
      .map { it.action.name }
      .firstOrNull()
  }

  @Suppress("unused")
  fun accept(source: DevicePositionUpdateWebhookMessage): Boolean {
    val availableKeys = rules.map { it.key }
    return availableKeys
        .stream()
        .anyMatch {
          source.properties.containsKey(it)
        }
  }
}
