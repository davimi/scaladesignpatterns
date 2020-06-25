package learnfinagle

import com.twitter.finagle.{Http, Service}
import com.twitter.finagle.http.{Request, Response}
import com.twitter.util.Await

object Proxy extends App {
  val client: Service[Request, Response] =
    Http.newService("twitter.com:80") //dispatch request to this address

  val server = Http.serve(":8080", client)
  Await.ready(server)
}