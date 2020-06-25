package learnfinagle

import com.twitter.finagle.{Http, ListeningServer, Service, http}
import com.twitter.util.{Await, Future}

import scala.io.StdIn


object HiServer extends App {

  def start() = {

     val service = new Service[http.Request, http.Response] {
      def apply(req: http.Request): Future[http.Response] =
        Future.value(
          http.Response(req.version, http.Status.Ok)
        )
    }

    val server: ListeningServer = Http.serve("localhost:8080", service)

    StdIn.readLine()
    server.close()
    System.exit(0)

  }

  start()
}


