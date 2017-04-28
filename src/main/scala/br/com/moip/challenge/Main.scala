package br.com.moip.challenge

import scala.io.Source
import scala.util.{Failure, Success, Try}

/**
  * A simple CLI application that parses webhook log file.
  */
object Main extends App {
  args match {
    case Array(path) =>
      Try {
        // Returns an iterator, so we won't load all file into memory
        val log = Source.fromFile(path).getLines()
        WebhookRequestParser(log)
      } match {
        case Success(requests) =>
          println(
            s"""
              #Top URLs:
              #${requests.top10Urls
                .map({ case (url, count) => s"> $url - $count" }).mkString("\n")}
              #
              #Top response status:
              #${requests.top10Status
                .map({ case (status, count) => s"> $status - $count"}).mkString("\n")}
            """.stripMargin('#'))

        case Failure(e) =>
          println(s"Could not parse the file: ${e.getMessage}")
      }
    case _ =>
      println("Usage: moip-challenge.jar path/to/log.txt")
  }
}
