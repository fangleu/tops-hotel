package com.travelzen.tops.hotel.elong.connector.service.impl;

import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.security.SecureRandom;

import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import org.apache.commons.httpclient.ConnectTimeoutException;
import org.apache.commons.httpclient.params.HttpConnectionParams;
import org.apache.commons.httpclient.protocol.ControllerThreadSocketFactory;
import org.apache.commons.httpclient.protocol.ReflectionSocketFactory;
import org.apache.commons.httpclient.protocol.SecureProtocolSocketFactory;

public class CustomSSLProtocolSocketFactory implements SecureProtocolSocketFactory {

	@Override
	public Socket createSocket(String host, int port, InetAddress localAddress,
			int localPort) throws IOException, UnknownHostException {
		return getSSLSocketFactory().createSocket(host, port, localAddress,
				localPort);
	}

	@Override
	public Socket createSocket(String host, int port, InetAddress localAddress,int localPort, HttpConnectionParams params) throws IOException,UnknownHostException, ConnectTimeoutException {
		if (params == null) {
			throw new IllegalArgumentException("Parameters may not be null");
		}
		int timeout = params.getConnectionTimeout();
		if (timeout == 0) {
			return createSocket(host, port, localAddress, localPort);
		} else {
			// To be eventually deprecated when migrated to Java 1.4 or above
			Socket socket = ReflectionSocketFactory.createSocket("javax.net.ssl.SSLSocketFactory", host, port, localAddress,localPort, timeout);
			if (socket == null) {
				socket = ControllerThreadSocketFactory.createSocket(this, host,port, localAddress, localPort, timeout);
			}
			return socket;
		}
	}

	@Override
	public Socket createSocket(String host, int port) throws IOException,UnknownHostException {
		return getSSLSocketFactory().createSocket(host, port);
	}

	@Override
	public Socket createSocket(Socket socket, String host, int port,boolean autoClose) throws IOException, UnknownHostException {
		return getSSLSocketFactory().createSocket(socket, host, port, autoClose);
	}

	protected SSLSocketFactory getSSLSocketFactory() {
		SSLContext sc;
		try {
			sc = SSLContext.getInstance("SSL");
			TrustManager[] tmArr = { new X509TrustManager() {
				@Override
				public void checkClientTrusted(
						java.security.cert.X509Certificate[] arg0, String arg1)
						throws java.security.cert.CertificateException {
					// TODO Auto-generated method stub

				}

				@Override
				public void checkServerTrusted(
						java.security.cert.X509Certificate[] arg0, String arg1)
						throws java.security.cert.CertificateException {
					// TODO Auto-generated method stub

				}

				@Override
				public java.security.cert.X509Certificate[] getAcceptedIssuers() {
					// TODO Auto-generated method stub
					return null;
				}
			} };
			sc.init(null, tmArr, new SecureRandom());
			return (SSLSocketFactory) sc.getSocketFactory();
		} catch (Exception e) {
			return (SSLSocketFactory) SSLSocketFactory.getDefault();
		}
	}

}
