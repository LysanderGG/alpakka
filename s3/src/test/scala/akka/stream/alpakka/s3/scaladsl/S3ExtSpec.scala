/*
 * Copyright (C) 2016-2019 Lightbend Inc. <http://www.lightbend.com>
 */

package akka.stream.alpakka.s3.scaladsl

import akka.actor.ActorSystem
import com.typesafe.config.ConfigFactory
import akka.stream.alpakka.s3.S3Ext

import scala.collection.JavaConverters._
import org.scalatest.flatspec.AnyFlatSpecLike
import org.scalatest.matchers.should.Matchers

class S3ExtSpec extends AnyFlatSpecLike with Matchers {
  it should "reuse application config from actor system" in {
    val config = ConfigFactory.parseMap(
      Map(
        "alpakka.s3.endpoint-url" -> "http://localhost:8001",
        "alpakka.s3.path-style-access" -> true
      ).asJava
    )
    implicit val system = ActorSystem.create("s3", config)
    val ext = S3Ext(system)
    ext.settings.endpointUrl shouldBe Some("http://localhost:8001")
    ext.settings.pathStyleAccess shouldBe true
  }
}
