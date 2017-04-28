package br.com.moip.challenge

import org.scalatest.{FlatSpec, Matchers}

class WebhookRequestParserSpec extends FlatSpec with Matchers {
  ".parse" should "return an WebhookRequest if line match log pattern " in {
    val line = """level=info response_body="" request_to="https://woodenoyster.com.br" response_headers=map[Content-Type:[application/json; charset=utf-8] Connection:[keep-alive] Runscope-Me      ssage-Id:[fb814900-c6bc-4002-8007-e7e06b52abb0] Access-Control-Allow-Credentials:[true] Server:[Runscope-Gateway/1.0]] response_status="503""""
    WebhookRequestParser.parse(line) should contain (WebhookRequestParser("https://woodenoyster.com.br", 503))
  }

  it should "return None when it's not able to parse" in {
    WebhookRequestParser.parse("""Lorem ipsum dolor""") shouldBe empty
  }
}
