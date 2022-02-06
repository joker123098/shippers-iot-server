package com.hookiesolutions.cat.prototype.ruleengine.property

import com.hookiesolutions.cat.prototype.common.model.LocationEventAction
import org.springframework.stereotype.Component

/**
 *
 * @author Arthur Kazemi<bidadh@gmail.com>
 * @since 25/8/20 13:56
 */
@Component
class PersonalAlertEventRule: PropertyEventRule() {
  override val key: String = PERSONAL_ALERT_PROP_KEY
  override val action = LocationEventAction.PERSONAL_ALERT

  companion object {
    const val PERSONAL_ALERT_PROP_KEY = "personalAlert"
  }
}
