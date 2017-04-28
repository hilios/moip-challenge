# MOIP Challenge

### A simple webhook log parser.

A CLI application to parse a HTTP serve log file and retrieve the most requested URLs and status.

Design considerations:
- Parse file as a stream;
- Use immutable structures;
- Maximum algorithm complexity O(N);

### Run

First you will have to download and install the [SBT](http://www.scala-sbt.org/download.html) tool, then you can compile the code:

```shell
$ sbt assembly
# ...
[info] SHA-1: 6b66f060834bd9a95803d16913274f3b8ddd042a
[info] Packaging /your/path/moip-challenge/target/scala-2.12/moip-challenge.jar ...
[info] Done packaging.
```

To run just execute the `jar` (with Java 8) passing the path to the log file as arguments and the output will be displayed:

```shell
$ java -jar target/scala-2.12/moip-challenge.jar ./src/test/resources/log.txt

Top 10 URLs:
  https://eagerhaystack.com - 750
  https://surrealostrich.com.br - 734
  https://grimpottery.net.br - 732
  https://abandonedpluto.com - 731
  https://easterncobra.com.br - 730
  https://solidstreet.net - 725
  https://notoriouslonesome.com - 724
  https://solidwindshield.net.br - 713
  https://intensecloud.us - 712
  https://grotesquemoon.de - 706

Top 10 response status:
  404 - 1474
  503 - 1451
  400 - 1440
  500 - 1428
  200 - 1417
  201 - 1402
  204 - 1388
```

Or if you prefer run through the SBT (it's a little bit slower).

```
$ sbt "run ./src/test/resources/log.txt"
```

### Unit tests

Run the tests through [SBT](http://www.scala-sbt.org/download.html):

```shell
$ sbt test
[info] WebhookParserSpec:
[info] .apply
[info] - should return a WebhookParser from a string iterator
[info] #top10Urls
[info] - should return the top 10 requests url
[info] #top10Status
[info] - should return the top 10 requests url
[info] WebhookRequestSpec:
[info] .parse
[info] - should return an WebhookRequest if line match log pattern
[info] - should return None when it's not able to parse
[info] Run completed in 1 second, 369 milliseconds.
[info] Total number of tests run: 5
[info] Suites: completed 2, aborted 0
[info] Tests: succeeded 5, failed 0, canceled 0, ignored 0, pending 0
[info] All tests passed.
```