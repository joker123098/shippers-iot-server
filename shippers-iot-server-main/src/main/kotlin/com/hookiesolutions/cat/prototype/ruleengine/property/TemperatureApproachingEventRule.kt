package com.hookiesolutions.cat.prototype.ruleengine.property

import com.hookiesolutions.cat.prototype.common.model.LocationEventAction
import org.springframework.stereotype.Component

/**
 *
 * @author Arthur Kazemi<bidadh@gmail.com>
 * @since 25/8/20 00:45
 */
@Component
class TemperatureApproachingEventRule: PropertyEventRule() {
  override val key: String = TEMPERATURE_APPROACHING_PROP_KEY
  override val action = LocationEventAction.TEMPERATURE_APPROACHING

  companion object {
    const val TEMPERATURE_APPROACHING_PROP_KEY = "temperatureApproaching"
  }
}
