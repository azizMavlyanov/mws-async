package com.amazonservices.mws.orders._2013_09_01

import scala.language.postfixOps
import MarketplaceWebServiceOrders._

object MarketplaceWebServiceOrdersMain extends App {
  val client = getClient
//  client.getOrder(/* GetOrderRequest here */) it returns Future[Either[MwsException, GetOrderResponse]]
//  client.getServiceStatus(/* GetServiceStatusRequest here here */) it returns Future[Either[MwsException, GetSystemStatusResponse]]
}
