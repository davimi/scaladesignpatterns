package learnfinagle

import com.twitter.finagle.http.{Request, Response}
import com.twitter.finagle.util.DefaultTimer
import com.twitter.finagle.{Http, ListeningServer, Service, http}
import com.twitter.util.{Future => TwitterFuture}
import com.twitter.util.Duration._

import scala.io.StdIn

object TimeOutServer extends App {

  private val backEndservice: Service[http.Request, http.Response] = new Service[http.Request, http.Response] {
    def apply(req: http.Request): TwitterFuture[http.Response] = {
      TwitterFuture {
        Thread.sleep(2000)
        val res = http.Response(req.version, http.Status.Ok)
        res.setContentString("\nhi")
        res
      }
    }
  }

  private val timeoutFilter = new TimeOutFilter[http.Request, http.Response](fromSeconds(1), DefaultTimer)

  private val serviceWithTimeOut: Service[http.Request, http.Response] =
    timeoutFilter
      .andThen(backEndservice)

  val server: ListeningServer = Http.serve("localhost:8080", serviceWithTimeOut)
  //curl -D - http://localhost:8080
  //no timeout?

  StdIn.readLine()
  server.close()
  System.exit(0)
}
