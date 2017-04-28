package br.com.moip.challenge

case class WebhookRequest(url: String, status: Int)

object WebhookRequest {
  val pattern = """request_to="(.+?)".+?response_status="(\d+)"""".r.unanchored

  def parse(line: String): Option[WebhookRequest] = line match {
    case pattern(url, status) => Some(WebhookRequest(url, status.toInt))
    case _ => None
  }
}
