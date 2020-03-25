package com.ctbri.dao.es.core;

import java.util.Iterator;
import java.util.List;

import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;

import com.ctbri.dao.es.pool.ESPool;

/**
 * ES数据层模板
 * 
 * @author Hogan
 *
 */
public class ESDaoTemplate {

	/**
	 * 索引
	 */
	private String index;
	/**
	 * ES连接池
	 */
	private ESPool esPool;

	/**
	 * 带参数查询
	 * 
	 * @param esParam
	 * @param se
	 * @return
	 */
	public <T> T query(ESParam esParam, ESSearchExtractor<T> se) {
		if (esParam == null) {
			return null;
		}
		class QueryCallback implements ESSearchCallback<T> {

			@Override
			public T doInSearch(TransportClient conn) {
				Iterator<SearchHit> iterator = conn.prepareSearch(index).setQuery(generateQueryBuilder(esParam)).get()
						.getHits().iterator();
				return se.extractData(iterator);
			}

		}
		return execute(new QueryCallback());
	}

	/**
	 * 无参数查询
	 * 
	 * @param se
	 * @return
	 */
	public <T> T query(ESSearchExtractor<T> se) {
		class QueryCallback implements ESSearchCallback<T> {

			@Override
			public T doInSearch(TransportClient conn) {
				Iterator<SearchHit> iterator = conn.prepareSearch(index).get().getHits().iterator();
				return se.extractData(iterator);
			}

		}
		return execute(new QueryCallback());
	}

	/**
	 * 带参数分页查询
	 * 
	 * @param esParam
	 * @param startCount
	 * @param pageSize
	 * @param se
	 * @return
	 */
	public <T> T query(ESParam esParam, int startCount, int pageSize, ESSearchExtractor<T> se) {
		class QueryCallback implements ESSearchCallback<T> {

			@Override
			public T doInSearch(TransportClient conn) {
				Iterator<SearchHit> iterator = conn.prepareSearch(index).setQuery(generateQueryBuilder(esParam))
						.setFrom(startCount).setSize(pageSize).get().getHits().iterator();
				return se.extractData(iterator);
			}

		}
		return execute(new QueryCallback());
	}

	/**
	 * 不带参数分页查询
	 * 
	 * @param startCount
	 * @param pageSize
	 * @param se
	 * @return
	 */
	public <T> T query(int startCount, int pageSize, ESSearchExtractor<T> se) {
		class QueryCallback implements ESSearchCallback<T> {

			@Override
			public T doInSearch(TransportClient conn) {
				Iterator<SearchHit> iterator = conn.prepareSearch(index).setFrom(startCount).setSize(pageSize).get()
						.getHits().iterator();
				return se.extractData(iterator);
			}

		}
		return execute(new QueryCallback());
	}

	/**
	 * 带参数查询记录数
	 * 
	 * @param esParam
	 * @return
	 */
	public long queryForCount(ESParam esParam) {
		class QueryCallback implements ESSearchCallback<Long> {

			@Override
			public Long doInSearch(TransportClient conn) {
				return conn.prepareSearch(index).setQuery(generateQueryBuilder(esParam)).get().getHits().getTotalHits();
			}

		}
		return execute(new QueryCallback());
	}

	/**
	 * 不带参数查询记录数
	 * 
	 * @return
	 */
	public long queryForCount() {
		class QueryCallback implements ESSearchCallback<Long> {

			@Override
			public Long doInSearch(TransportClient conn) {
				return conn.prepareSearch(index).get().getHits().getTotalHits();
			}

		}
		return execute(new QueryCallback());
	}

	/**
	 * 执行查询
	 * 
	 * @param searchCallback
	 * @return
	 */
	private <T> T execute(ESSearchCallback<T> searchCallback) {
		TransportClient conn = null;
		try {
			conn = esPool.borrowObject();
			if (conn != null) {
				return searchCallback.doInSearch(conn);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			esPool.returnObject(conn);
		}
		return null;
	}

	/**
	 * 组成对应的queryBuilder
	 * 
	 * @param esParam
	 * @return
	 */
	private QueryBuilder generateQueryBuilder(ESParam esParam) {
		QueryBuilder result = null;
		if (esParam != null) {
			List<QType> qTypes = esParam.getqTypes();
			List<String> keys = esParam.getKeys();
			List<Object> values = esParam.getValues();
			if (qTypes != null && keys != null && values != null) {
				for (int i = 0; i < qTypes.size(); i++) {
					switch (qTypes.get(i)) {
					case ONE_PARAM:
						result = QueryBuilders.termQuery(keys.get(i), values.get(i));
						break;
					case MUTIL_PARAM:
						result = QueryBuilders.termsQuery(keys.get(i), values.get(i));
					case FUZZY_PARAM:
						result = QueryBuilders.matchPhraseQuery(keys.get(i), values.get(i));
					default:
						break;
					}
				}
			}
		}
		return result;
	}

	public String getIndex() {
		return index;
	}

	public void setIndex(String index) {
		this.index = index;
	}

	public ESPool getEsPool() {
		return esPool;
	}

	public void setEsPool(ESPool esPool) {
		this.esPool = esPool;
	}

}
