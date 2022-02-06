package com.hookiesolutions.cat.prototype.web

import com.hookiesolutions.cat.prototype.common.service.ReactiveObjectMapper
import org.slf4j.Logger
import org.springframework.amqp.core.MessageBuilder
import org.springframework.amqp.rabbit.core.RabbitTemplate
import org.springframework.boot.availability.AvailabilityChangeEvent
import org.springframework.boot.availability.ReadinessState
import org.springframework.context.ApplicationEventPublisher
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import reactor.core.publisher.Mono
import reactor.kotlin.core.publisher.toMono
import javax.validation.Valid

/**
 *
 * @author Arthur Kazemi<bidadh@gmail.com>
 * @since 6/7/20 13:49
 */
@RestController
@RequestMapping("/demo")
class DemoController(
  private val log: Logger,
  private val rabbitTemplate: RabbitTemplate,
  private val om: ReactiveObjectMapper,
  private val publisher: ApplicationEventPublisher,
) {
  @PostMapping("/route/{enterpriseId}/{asset}",
      consumes = [MediaType.APPLICATION_JSON_VALUE],
      produces = [MediaType.TEXT_PLAIN_VALUE]
  )
  fun uiRouteUpdate(
      @PathVariable enterpriseId: String,
      @PathVariable asset: String,
      @RequestBody @Valid routeRequest: RouteRequest
  ): Mono<String> {
    val coordinates = routeRequest.coordinates
    log.info("Sending route to the client for asset: '{}' with '{}' coordinates", asset, coordinates.size)

    val webhookMessage = RouteRequestMessage(
      enterpriseId,
      asset,
      routeRequest.coordinates,
      routeRequest.forceFit,
      routeRequest.forceFollow
    )

    return om.writeValueAsBytes(webhookMessage)
      .doOnNext {
        val message = MessageBuilder
          .withBody(it)
          .setHeader("content_type", MediaType.APPLICATION_JSON_VALUE)
          .setHeader("wh-topic", "DeviceRoute")
          .build()
        rabbitTemplate.send("wh-customer", "event", message)
      }
      .map { "OK" }
  }

  @PostMapping("/zoomAt/{enterpriseId}/{asset}",
      consumes = [MediaType.APPLICATION_JSON_VALUE],
      produces = [MediaType.TEXT_PLAIN_VALUE]
  )
  fun finishLegAt(
      @PathVariable enterpriseId: String,
      @PathVariable asset: String,
      @RequestBody @Valid request: FinishLegRequest
  ): Mono<String> {
    val webhookMessage = FinishLegMessage(
      enterpriseId,
      asset,
      request.points
    )

    return om.writeValueAsBytes(webhookMessage)
      .doOnNext {
        val message = MessageBuilder
          .withBody(it)
          .setHeader("content_type", MediaType.APPLICATION_JSON_VALUE)
          .setHeader("wh-topic", "DeliveredAt")
          .build()
        rabbitTemplate.send("wh-customer", "event", message)
      }
      .map { "OK" }
  }

  @PostMapping("/enterprise",
      consumes = [MediaType.APPLICATION_JSON_VALUE],
      produces = [MediaType.TEXT_PLAIN_VALUE]
  )
  fun setEnterprise(@Valid @RequestBody request: SelectedEnterpriseRequest): Mono<String> {
    log.info("Setting enterprise: '{}'", request)

//    val build = MessageBuilder
//        .withPayload(GenericSSENotification("SetEnterprise", request))
//        .build()
//    sseChannel.send(build)

    return "OK".toMono()
  }

  @GetMapping("/hello")
  fun hello(): String {
    return "Hello World!"
  }

  @GetMapping("/down")
  fun down(): String {
    AvailabilityChangeEvent.publish(publisher, this, ReadinessState.REFUSING_TRAFFIC)
    return "Down"
  }

  @GetMapping("/up")
  fun up(): String {
    AvailabilityChangeEvent.publish(publisher, this, ReadinessState.REFUSING_TRAFFIC)
    return "Up"
  }
}

data class RouteRequest(
    val coordinates: List<List<Double>>,
    val forceFit: Boolean,
    val forceFollow: Boolean
)

data class RouteRequestMessage(
  val enterpriseId: String,
  val assetId: String,
  val coordinates: List<List<Double>>,
  val forceFit: Boolean,
  val forceFollow: Boolean
)

data class FinishLegRequest(
    val points: List<List<Double>>
)

data class FinishLegMessage(
  val enterpriseId: String,
  val assetId: String,
  val points: List<List<Double>>
)

data class SelectedEnterpriseRequest(
    val id: String,
    val center: MapPoint,
    val zoom: Int
)

data class MapPoint(
    val lng: Double,
    val lat: Double
)
