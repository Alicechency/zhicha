package com.ctbri.dao.es.core;

import org.elasticsearch.client.transport.TransportClient;

import com.ctbri.common.dao.SearchCallback;

/**
 * 查询回调函数
 * 
 * @author Hogan
 *
 * @param <T>
 */
public interface ESSearchCallback<T> extends SearchCallback<T, TransportClient> {

}