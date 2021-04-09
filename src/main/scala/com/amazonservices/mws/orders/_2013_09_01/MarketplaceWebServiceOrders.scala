package com.amazonservices.mws.orders._2013_09_01

import akka.actor.{ActorRef, ActorSystem, Props}
import akka.dispatch.MessageDispatcher
import akka.pattern.ask
import akka.util.Timeout
import com.amazonservices.mws.client.MwsException
import com.amazonservices.mws.orders._2013_09_01.MarketplaceWebServiceOrdersActor.{GetOrder, GetServiceStatus}
import com.amazonservices.mws.orders._2013_09_01.model.{GetOrderRequest, GetOrderResponse, GetServiceStatusRequest, GetServiceStatusResponse}

import scala.concurrent.Future
import scala.concurrent.duration.DurationInt
import scala.language.postfixOps

object MarketplaceWebServiceOrders {
  def getClient = new MarketplaceWebServiceOrders
}

class MarketplaceWebServiceOrders {
  implicit val system: ActorSystem = ActorSystem("MarketplaceWebService")
  val marketplaceWebServiceOrdersActor: ActorRef = system.actorOf(Props[MarketplaceWebServiceOrdersActor])
  implicit val timeout: Timeout = Timeout(3 seconds)
  implicit val dispatcher: MessageDispatcher = system.dispatchers.lookup("dedicated-dispatcher")

  def getOrder(request: GetOrderRequest): Future[Either[MwsException, GetOrderResponse]] = {
    val response = (marketplaceWebServiceOrdersActor ? GetOrder(request)).mapTo[GetOrderResponse]
    response.map(getOrderResponse => {
      if (Option(getOrderResponse).isDefined)
        Right(getOrderResponse)
      else
        Left(MwsException)
    })
  }

  def getServiceStatus(request: GetServiceStatusRequest): Future[Either[MwsException, GetServiceStatusResponse]] = {
    val response = (marketplaceWebServiceOrdersActor ? GetServiceStatus(request)).mapTo[GetServiceStatusResponse]
    response.map(getServiceStatusResponse => {
      if (Option(getServiceStatusResponse).isDefined)
        Right(getServiceStatusResponse)
      else
        Left(MwsException)
    })
  }
}
