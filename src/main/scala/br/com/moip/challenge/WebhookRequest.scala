package br.com.moip.challenge

/**
  * A webhook request log input
  */
case class WebhookRequest(url: String, status: Int)

object WebhookRequest {
  val pattern = """request_to="(.+?)".+?response_status="(\d+)"""".r.unanchored

  /**
    * Parses a log string and return a matching WebhookRequest if possible.
    * @param line The string to parse
    * @return An optional WebhookRequest parser
    */
  def parse(line: String): Option[WebhookRequest] = line match {
    case pattern(url, status) => Some(WebhookRequest(url, status.toInt))
    case _ => None
  }
}