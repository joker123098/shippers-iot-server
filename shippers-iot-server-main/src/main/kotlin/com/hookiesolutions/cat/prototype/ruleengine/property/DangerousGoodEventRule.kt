package com.hookiesolutions.cat.prototype.ruleengine.property

import com.hookiesolutions.cat.prototype.common.model.LocationEventAction
import org.springframework.stereotype.Component

/**
 *
 * @author Arthur Kazemi<bidadh@gmail.com>
 * @since 25/8/20 00:45
 */
@Component
class DangerousGoodEventRule: PropertyEventRule() {
  override val key: String = DANGEROUS_GOOD_PROP_KEY
  override val action = LocationEventAction.DG_ALERT

  companion object {
    const val DANGEROUS_GOOD_PROP_KEY = "dangerousGood"
  }
}
