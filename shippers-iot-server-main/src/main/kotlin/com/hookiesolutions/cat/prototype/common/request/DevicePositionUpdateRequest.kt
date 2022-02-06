package com.hookiesolutions.cat.prototype.common.request

import com.fasterxml.jackson.databind.annotation.JsonDeserialize
import com.hookiesolutions.cat.prototype.config.GeoJsonDeserializer
import org.springframework.data.mongodb.core.geo.GeoJsonPoint
import javax.validation.constraints.NotNull

/**
 *
 * @author Arthur Kazemi<bidadh@gmail.com>
 * @since 25/6/20 02:30
 */
data class DevicePositionUpdateRequest(
    @NotNull
    @JsonDeserialize(using = GeoJsonDeserializer::class)
    val position: GeoJsonPoint,
    val properties: Map<String, Any> = emptyMap()
)

data class DevicePositionUpdateWebhookMessage(
    val enterpriseId: String,
    val deviceId: String,
    @NotNull
    @JsonDeserialize(using = GeoJsonDeserializer::class)
    val position: GeoJsonPoint,
    val properties: Map<String, Any> = emptyMap()
)
