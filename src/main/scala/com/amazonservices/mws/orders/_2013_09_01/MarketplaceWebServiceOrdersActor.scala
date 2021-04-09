package com.amazonservices.mws.orders._2013_09_01

import akka.actor.{Actor, ActorLogging}
import com.amazonservices.mws.orders._2013_09_01.model.{GetOrderRequest, GetServiceStatusRequest}
import com.amazonservices.mws.orders._2013_09_01.samples.MarketplaceWebServiceOrdersSampleConfig

object MarketplaceWebServiceOrdersActor {
  case class GetOrder(request: GetOrderRequest)
  case class GetServiceStatus(request: GetServiceStatusRequest)
}

class MarketplaceWebServiceOrdersActor extends Actor with ActorLogging {
  import MarketplaceWebServiceOrdersActor._

  val client: MarketplaceWebServiceOrdersClient = MarketplaceWebServiceOrdersSampleConfig.getClient

  override def receive: Receive = {
    case GetOrder(request) =>
      log.info(s"Getting order...")
      sender() ! client.getOrder(request)
    case GetServiceStatus(request) =>
      log.info(s"Getting status of service...")
      sender() ! client.getServiceStatus(request)
  }
}
