/**
 * GatewayOrderQuery.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.ry.util.bill.com._99bill.sandbox.apipay.services.gatewayOrderQuery;

import com.ry.util.bill.com.bill99.seashell.domain.dto.gatewayquery.GatewayOrderQueryRequest;
import com.ry.util.bill.com.bill99.seashell.domain.dto.gatewayquery.GatewayOrderQueryResponse;

public interface GatewayOrderQuery extends java.rmi.Remote {
    public GatewayOrderQueryResponse gatewayOrderQuery(GatewayOrderQueryRequest request) throws java.rmi.RemoteException;
}
