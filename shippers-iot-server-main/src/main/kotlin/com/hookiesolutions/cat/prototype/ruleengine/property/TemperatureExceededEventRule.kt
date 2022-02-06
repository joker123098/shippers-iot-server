package com.hookiesolutions.cat.prototype.ruleengine.property

import com.hookiesolutions.cat.prototype.common.model.LocationEventAction
import org.springframework.stereotype.Component

/**
 *
 * @author Arthur Kazemi<bidadh@gmail.com>
 * @since 25/8/20 00:45
 */
@Component
class TemperatureExceededEventRule: PropertyEventRule() {
  override val key: String = TEMPERATURE_EXCEEDED_PROP_KEY
  override val action = LocationEventAction.TEMPERATURE_EXCEEDED

  companion object {
    const val TEMPERATURE_EXCEEDED_PROP_KEY = "temperatureExceeded"
  }
}
