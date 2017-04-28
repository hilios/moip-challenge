package br.com.moip.challenge

import scala.io.Source
import scala.util.{Failure, Success, Try}

/**
  * A simple CLI application that parses some webhook log file.
  */
object Main extends App {
  args match {
    case Array(path) =>
      Try {
        // Returns an iterator, so we won't load all file into memory
        val log = Source.fromFile(path).getLines()
        WebhookParser(log)
      } match {
        case Success(requests) =>
          println(
            s"""
              #Top 10 URLs:
              #${requests.top10Urls.map(x => s"  ${x._1} - ${x._2}").mkString("\n")}
              #
              #Top 10 response status:
              #${requests.top10Status.map(x => s"  ${x._1} - ${x._2}").mkString("\n")}
            """.stripMargin('#'))

        case Failure(e) =>
          println(s"Could not parse the file: ${e.getMessage}")
      }
    case _ =>
      println("Usage: moip-challenge.jar path/to/log.txt")
  }
}
