package learnfinagle

import com.twitter.finagle.Http.Client
import com.twitter.util.{Duration, Future}
import java.util.concurrent.TimeUnit.MILLISECONDS

import com.twitter.finagle.{Http, ListeningServer, Service, http}
import com.twitter.finagle.http.{Request, Response}

import scala.io.StdIn

object HiHttpServer extends App {

  val httpClient: Client = new Client().withStreaming(false).withRequestTimeout(Duration(400, MILLISECONDS))

  val address = "localhost:8080"

  val httpService: Service[Request, Response] = httpClient.newService(address)
  httpService.apply(http.Request(http.Method.Get, "/"))

  val request = http.Request(http.Method.Get, "/")
  request.host = address

  val server: ListeningServer = Http.serve(address, httpService)

  StdIn.readLine()
  server.close()
  System.exit(0)
}
