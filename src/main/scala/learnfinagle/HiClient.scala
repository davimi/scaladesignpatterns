package learnfinagle

import com.twitter.finagle.{Http, Service, http}
import com.twitter.util.Await
import com.twitter.util.{Future => TwitterFuture}

import scala.io.StdIn

object HiClient extends App {

  HiServer.start()

  val host = "localhost" //www.scala-lang.org"
  val port: Int = 8080

  val client: Service[http.Request, http.Response] = Http.newService(s"$host:$port")

  val request = http.Request(http.Method.Get, "/")
  request.host = host

  val response: TwitterFuture[http.Response] = client(request)

  Await.result(response.onSuccess { rep: http.Response =>
    println("GET success: " + rep.toString())
  })

  //would not print otherwise, why?
  Thread.sleep(10)

}



