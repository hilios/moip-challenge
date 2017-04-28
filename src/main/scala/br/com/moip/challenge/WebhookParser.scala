package br.com.moip.challenge

case class WebhookRequest(url: String, status: Int)

case class WebhookParser(requests: Seq[WebhookRequest]) {
  def top10Urls: Seq[(String, Int)] = {
    requests.groupBy(_.url).mapValues(_.length).toSeq.sortBy(_._2).reverse.take(10)
  }

  def top10Status = {
    requests.groupBy(_.status).mapValues(_.length).toSeq.sortBy(_._2).reverse.take(10)
  }
}

object WebhookParser {
  val pattern = """request_to="(.+?)".+?response_status="(\d+)"""".r.unanchored

  def parse(line: String): Option[WebhookRequest] = line match {
    case pattern(url, status) => Some(WebhookRequest(url, status.toInt))
    case _ => None
  }

  def apply(log: Iterator[String]) = {
    new WebhookParser(log.map(parse).filter(_.nonEmpty).map(_.get).toSeq)
  }
}
