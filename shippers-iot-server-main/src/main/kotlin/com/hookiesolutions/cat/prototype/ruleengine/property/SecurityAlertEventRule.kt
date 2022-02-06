package com.hookiesolutions.cat.prototype.ruleengine.property

import com.hookiesolutions.cat.prototype.common.model.LocationEventAction
import org.springframework.stereotype.Component

/**
 *
 * @author Arthur Kazemi<bidadh@gmail.com>
 * @since 25/8/20 13:56
 */
@Component
class SecurityAlertEventRule: PropertyEventRule() {
  override val key: String = SECURITY_ALERT_PROP_KEY
  override val action = LocationEventAction.SECURITY_ALERT

  companion object {
    const val SECURITY_ALERT_PROP_KEY = "securityAlert"
  }
}
