package com.hookiesolutions.cat.prototype

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.data.mongo.MongoDataAutoConfiguration
import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration
import org.springframework.boot.runApplication


@SpringBootApplication(
  exclude = [MongoAutoConfiguration::class, MongoDataAutoConfiguration::class]
)
class CATProviderApplication

fun main(args: Array<String>) {
  runApplication<CATProviderApplication>(*args)
}
