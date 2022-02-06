package com.hookiesolutions.cat.prototype.config

import com.hookiesolutions.cat.prototype.web.PositionController
import io.swagger.v3.oas.annotations.OpenAPIDefinition
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType
import io.swagger.v3.oas.annotations.info.Info
import io.swagger.v3.oas.annotations.security.SecurityScheme
import org.springdoc.core.GroupedOpenApi
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

/**
 *
 * @author Arthur Kazemi<bidadh@gmail.com>
 * @since 19/6/20 17:55
 */
@Configuration
@OpenAPIDefinition(
    info = Info(
        title = "Ericsson Critical Asset Tracking API",
        version = "1.0.0"
    )
)
@SecurityScheme(
    name = OpenAPIConfig.BASIC_SCHEME,
    type = SecuritySchemeType.HTTP,
    scheme = "basic"
)
class OpenAPIConfig {

  @Bean
  fun locationOpenApi(): GroupedOpenApi {
    val paths = arrayOf("${PositionController.REQUEST_MAPPING}/**")
    return GroupedOpenApi.builder().group("position").pathsToMatch(*paths)
        .build()
  }

  companion object {
    const val BASIC_SCHEME = "basicScheme"
  }
}
