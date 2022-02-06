package com.hookiesolutions.cat.prototype.service

import com.hookiesolutions.cat.prototype.common.request.DevicePositionUpdateRequest
import com.hookiesolutions.cat.prototype.common.request.DevicePositionUpdateWebhookMessage
import com.hookiesolutions.cat.prototype.common.service.ReactiveObjectMapper
import com.hookiesolutions.cat.prototype.ruleengine.PropertyRuleEngine
import io.swagger.v3.oas.annotations.Parameter
import io.swagger.v3.oas.annotations.enums.ParameterIn
import org.springframework.amqp.rabbit.core.RabbitTemplate
import org.springframework.http.MediaType
import org.springframework.stereotype.Service
import reactor.core.publisher.Mono

/**
 *
 * @author Arthur Kazemi<bidadh@gmail.com>
 * @since 25/6/20 02:45
 */
@Service
class PositionService(
  private val rabbitTemplate: RabbitTemplate,
  private val om: ReactiveObjectMapper,
  private val propertyRuleEngine: PropertyRuleEngine
) {
  fun publishPositionWebhookMessage(
      @Parameter(`in` = ParameterIn.PATH) enterpriseId: String,
      @Parameter(`in` = ParameterIn.PATH) deviceId: String,
      devicePositionUpdateRequest: DevicePositionUpdateRequest
  ): Mono<String> {
    val webhookMessage = DevicePositionUpdateWebhookMessage(
      enterpriseId,
      deviceId,
      devicePositionUpdateRequest.position,
      devicePositionUpdateRequest.properties
    )

    val topic = propertyRuleEngine.topic(webhookMessage) ?: "DevicePositionUpdate"

    return om.writeValueAsBytes(webhookMessage)
      .doOnNext {
        val messageBuilder = org.springframework.amqp.core.MessageBuilder
          .withBody(it)
          .setHeader("content_type", MediaType.APPLICATION_JSON_VALUE)
          .setHeader("wh-topic", topic)

        rabbitTemplate.send("wh-customer", "event", messageBuilder.build())
      }
      .map { "OK" }

  }

}
