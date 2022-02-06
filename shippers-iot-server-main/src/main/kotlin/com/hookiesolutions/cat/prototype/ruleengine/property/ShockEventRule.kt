package com.hookiesolutions.cat.prototype.ruleengine.property

import com.hookiesolutions.cat.prototype.common.model.LocationEventAction
import org.springframework.stereotype.Component

/**
 *
 * @author Arthur Kazemi<bidadh@gmail.com>
 * @since 25/8/20 13:56
 */
@Component
class ShockEventRule: PropertyEventRule() {
  override val key: String = SHOCK_PROP_KEY
  override val action = LocationEventAction.SHOCK

  companion object {
    const val SHOCK_PROP_KEY = "shockAlert"
  }
}
