package com.ctbri.dao.es.core;

import java.util.Iterator;

import org.elasticsearch.search.SearchHit;

import com.ctbri.common.dao.SearchExtractor;

/**
 * 搜索结果封装
 * 
 * @author Hogan
 *
 * @param <T>
 */
public interface ESSearchExtractor<T> extends SearchExtractor<T, Iterator<SearchHit>>{

}
