package br.com.moip.challenge

case class WebhookParser(requests: Seq[WebhookRequest]) {

  def top10Urls: Seq[(String, Int)] = {
    requests.groupBy(_.url).mapValues(_.length).toSeq.sortBy(_._2).reverse.take(10)
  }

  def top10Status = {
    requests.groupBy(_.status).mapValues(_.length).toSeq.sortBy(_._2).reverse.take(10)
  }
}

object WebhookParser {
  def apply(log: Iterator[String]) = {
    new WebhookParser(log.map(WebhookRequest.parse).filter(_.nonEmpty).map(_.get).toSeq)
  }
}
