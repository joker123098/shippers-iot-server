package com.hookiesolutions.cat.prototype

import org.bson.Document
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.data.mongodb.core.convert.MappingMongoConverter

@SpringBootTest
class PrototypeApplicationTests {

  @Autowired
  lateinit var c: MappingMongoConverter

  @Test
  fun contextLoads() {
  }

  @Test
  internal fun locations() {
/*
    val position = GeoJsonPoint(-106.0, -40.0)
    val enterpriseId = "5ef36031cb509c53a57dd1c7"
    enterpriseRepository.locationsFor(position, enterpriseId)
        .test()
        .assertNext {
          assertThat(it.name).isEqualTo("Location 4")
        }
        .verifyComplete()
*/
  }
}
