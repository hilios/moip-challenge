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
        // the get lines return an iterator, so we won't load all file into memory
        val requests = Source.fromFile(path).getLines()
        .map(WebhookRequest.parse)
        .filter(_.nonEmpty)


      } match {
        case Success(requests) => ???
        case Failure(e) =>
          println(s"Could not parse the file: ${e.getMessage}")
      }
    case _ =>
      println("Usage: moip-challenge.jar path/to/log.txt")
  }
}
