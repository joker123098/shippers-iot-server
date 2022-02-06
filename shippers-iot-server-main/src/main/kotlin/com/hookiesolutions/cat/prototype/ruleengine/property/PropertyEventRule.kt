package com.hookiesolutions.cat.prototype.ruleengine.property

import com.hookiesolutions.cat.prototype.common.model.LocationEventAction
import com.hookiesolutions.cat.prototype.common.request.DevicePositionUpdateWebhookMessage

/**
 *
 * @author Arthur Kazemi<bidadh@gmail.com>
 * @since 25/8/20 14:02
 */
abstract class PropertyEventRule {
  abstract val key: String
  abstract val action: LocationEventAction
  private lateinit var positionUpdate: DevicePositionUpdateWebhookMessage

  val value: Any
    get() = positionUpdate.properties.getValue(key)

  fun accept(payload: DevicePositionUpdateWebhookMessage): Boolean {
    return payload.properties.containsKey(key)
  }

  fun process(payload: DevicePositionUpdateWebhookMessage) {
    this.positionUpdate = payload
  }
}

