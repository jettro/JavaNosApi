import static ch.qos.logback.classic.Level.*
import ch.qos.logback.classic.encoder.PatternLayoutEncoder
import ch.qos.logback.core.ConsoleAppender

appender("STDOUT", ConsoleAppender) {
  encoder(PatternLayoutEncoder) {
    pattern = "%d{MMM dd HH:mm:ss.SSS} [%thread] %-5level %logger{36} - %msg%n"
  }
}

root(INFO, ["STDOUT"])

logger("nl.gridshore", DEBUG, ["STDOUT"])
logger("org.springframework.web.client.RESTTemplate", DEBUG,["STDOUT"], false)