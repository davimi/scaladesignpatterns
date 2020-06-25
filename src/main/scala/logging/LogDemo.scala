package logging

import org.slf4j.{Logger, LoggerFactory}

object LogDemo extends App {

  val log: Logger = LoggerFactory.getLogger(this.getClass.getName)

  log.debug("Hello")

  val x = "world"
  log.debug("Hello again {}", x) //logback specific implementation. Will not evaluate x is logging level is not configured

  (new OtherLogger).hi()

}

class OtherLogger {

  val log = LoggerFactory.getLogger(this.getClass.getName)
  def hi() = log.warn("Should not be printed") //is configured for ERROR level
}
