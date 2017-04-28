package br.com.moip.challenge

import org.scalatest.{FlatSpec, Matchers}

class WebhookParserSpec extends FlatSpec with Matchers {
  ".apply" should "return a WebhookParser from a string iterator" in {
    val lines = Iterator(
      """level=info response_body="" request_to="https://woodenoyster.com.br" response_headers=map[Content-Type:[application/json; charset=utf-8] Connection:[keep-alive] Runscope-Me      ssage-Id:[fb814900-c6bc-4002-8007-e7e06b52abb0] Access-Control-Allow-Credentials:[true] Server:[Runscope-Gateway/1.0]] response_status="503"""",
      """level=info response_body="" request_to="https://solidwindshield.net.br" response_h      eaders=map[Keep-Alive:[timeout=20] Cache-Control:[max-age=0, private, must-revalid      ate] Status:[200] Etag:[7215ee9c7d9dc229d2921a40e899ec5f] Vary:[Accept-Encoding] X      -Ua-Compatible:[IE=Edge,chrome=1] Server:[nginx] X-Request-Id:[1381e8cb388db085cdc      3bac457dab9bf] Date:[Tue, 07 Jul 2015 18:29:52 GMT] Set-Cookie:[X-Mapping-fjhppofk      =A67D55AC8119CAD031E35EC35B4BCFFD; path=/] Content-Type:[text/html; charset=utf-8]       X-Powered-By:[Phusion Passenger (mod_rails/mod_rack) 3.0.17] X-Rack-Cache:[invali      date, pass] X-Runtime:[0.034645] Connection:[keep-alive]] response_status="404""""
    )

    WebhookParser(lines) shouldBe WebhookParser(Seq(
      WebhookRequest("https://woodenoyster.com.br", 503),
      WebhookRequest("https://solidwindshield.net.br", 404)
    ))
  }

  val requests: Seq[WebhookRequest] = (0 to 15).flatMap({ i =>
    val url = s"http://${(97 + i).toChar.toString}.com"
    val status = 200 + i
    (1 to (20 - i)).map(_ => WebhookRequest(url, status))
  })

  "#top10Urls" should "return the top 10 requests url" in {
    WebhookParser(requests).top10Urls shouldBe Seq(
      ("http://a.com", 20),
      ("http://b.com", 19),
      ("http://c.com", 18),
      ("http://d.com", 17),
      ("http://e.com", 16),
      ("http://f.com", 15),
      ("http://g.com", 14),
      ("http://h.com", 13),
      ("http://i.com", 12),
      ("http://j.com", 11)
    )
  }

  "#top10Status" should "return the top 10 responded status" in {
    WebhookParser(requests).top10Status shouldBe Seq(
      (200, 20),
      (201, 19),
      (202, 18),
      (203, 17),
      (204, 16),
      (205, 15),
      (206, 14),
      (207, 13),
      (208, 12),
      (209, 11)
    )
  }
}
