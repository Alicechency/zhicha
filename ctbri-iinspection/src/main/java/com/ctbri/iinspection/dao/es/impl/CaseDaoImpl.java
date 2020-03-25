package com.ctbri.iinspection.dao.es.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.elasticsearch.search.SearchHit;
import org.springframework.stereotype.Repository;

import com.alibaba.fastjson.util.TypeUtils;
import com.ctbri.dao.es.core.ESDaoTemplate;
import com.ctbri.dao.es.core.ESParam;
import com.ctbri.dao.es.core.QType;
import com.ctbri.dao.es.core.ESSearchExtractor;
import com.ctbri.iinspection.dao.es.CaseDao;
import com.ctbri.iinspection.pojo.Case;

/**
 * 案情数据层实现
 * 
 * @author Hogan
 *
 */
@Repository("caseDao")
public class CaseDaoImpl implements CaseDao {

	@Resource
	private ESDaoTemplate esDaoTemplate;

	@Override
	public List<Case> findCasesByPage(int startCount, int pageSize) {
		return esDaoTemplate.query(startCount, pageSize, new ESSearchExtractor<List<Case>>() {

			@Override
			public List<Case> extractData(Iterator<SearchHit> iterator) {
				List<Case> cases = new ArrayList<Case>();
				while (iterator.hasNext()) {
					Map<String, Object> map = ((SearchHit) iterator.next()).getSource();
					Case cas = new Case();
					cas.setId(cast(map.get("id"), String.class));
					cas.setAuthor(cast(map.get("author"), String.class));
					cas.setTitle(cast(map.get("title"), String.class));
					cas.setCategory(cast(map.get("categoryname"), Integer.class));
					cases.add(cas);
				}
				return cases;
			}

		});
	}

	@Override
	public long findCaseCount() {
		return esDaoTemplate.queryForCount();
	}

	@Override
	public long findCountByOneParam(String key, Object value) {
		ESParam esParam = new ESParam();
		esParam.getqTypes().add(QType.ONE_PARAM);
		esParam.getKeys().add(key);
		esParam.getValues().add(value);
		return esDaoTemplate.queryForCount(esParam);
	}

	@Override
	public long findCountByMultiParams(String key, Object... values) {
		ESParam esParam = new ESParam();
		esParam.getqTypes().add(QType.MUTIL_PARAM);
		esParam.getKeys().add(key);
		esParam.getValues().add(values);
		return esDaoTemplate.queryForCount(esParam);
	}

	@Override
	public List<Case> findCasesByOneParam(int startCount, int pageSize, String key, Object value) {
		ESParam esParam = new ESParam();
		esParam.getqTypes().add(QType.ONE_PARAM);
		esParam.getKeys().add(key);
		esParam.getValues().add(value);
		return esDaoTemplate.query(esParam, startCount, pageSize, new ESSearchExtractor<List<Case>>() {

			@Override
			public List<Case> extractData(Iterator<SearchHit> iterator) {
				List<Case> cases = new ArrayList<Case>();
				while (iterator.hasNext()) {
					Map<String, Object> map = ((SearchHit) iterator.next()).getSource();
					Case cas = new Case();
					cas.setId(cast(map.get("id"), String.class));
					cas.setAuthor(cast(map.get("author"), String.class));
					cas.setTitle(cast(map.get("title"), String.class));
					cas.setCategory(cast(map.get("categoryname"), Integer.class));
					cases.add(cas);
				}
				return cases;
			}
		});
	}

	@Override
	public List<Case> findCasesByMultiParams(int startCount, int pageSize, String key, Object... values) {
		ESParam esParam = new ESParam();
		esParam.getqTypes().add(QType.MUTIL_PARAM);
		esParam.getKeys().add(key);
		esParam.getValues().add(values);
		return esDaoTemplate.query(esParam, startCount, pageSize, new ESSearchExtractor<List<Case>>() {

			@Override
			public List<Case> extractData(Iterator<SearchHit> iterator) {
				List<Case> cases = new ArrayList<Case>();
				while (iterator.hasNext()) {
					Map<String, Object> map = ((SearchHit) iterator.next()).getSource();
					Case cas = new Case();
					cas.setId(cast(map.get("id"), String.class));
					cas.setAuthor(cast(map.get("author"), String.class));
					cas.setTitle(cast(map.get("title"), String.class));
					cas.setCategory(cast(map.get("categoryname"), Integer.class));
					cases.add(cas);
				}
				return cases;
			}
		});
	}

	@Override
	public Case findCaseById(String caseId) {
		ESParam esParam = new ESParam();
		esParam.getqTypes().add(QType.ONE_PARAM);
		esParam.getKeys().add("id");
		esParam.getValues().add(caseId);
		return esDaoTemplate.query(esParam, new ESSearchExtractor<Case>() {

			@Override
			public Case extractData(Iterator<SearchHit> iterator) {
				Case cas = null;
				while (iterator.hasNext()) {
					Map<String, Object> map = ((SearchHit) iterator.next()).getSource();
					cas = new Case();
					cas.setId(cast(map.get("id"), String.class));
					cas.setContent(cast(map.get("contents"), String.class));
					cas.setAuthor(cast(map.get("author"), String.class));
					cas.setTitle(cast(map.get("title"), String.class));
					cas.setCategory(cast(map.get("categoryname"), Integer.class));
					break;
				}
				return cas;
			}
		});
	}

	@Override
	public List<Case> findCaseByFuzzy(int startCount, int pageSize, String key, String value) {
		ESParam esParam = new ESParam();
		esParam.getqTypes().add(QType.FUZZY_PARAM);
		esParam.getKeys().add(key);
		esParam.getValues().add(value);
		return esDaoTemplate.query(esParam, startCount, pageSize, new ESSearchExtractor<List<Case>>() {

			@Override
			public List<Case> extractData(Iterator<SearchHit> iterator) {
				List<Case> cases = new ArrayList<Case>();
				while (iterator.hasNext()) {
					Map<String, Object> map = ((SearchHit) iterator.next()).getSource();
					Case cas = new Case();
					cas.setId(cast(map.get("id"), String.class));
					cas.setAuthor(cast(map.get("author"), String.class));
					cas.setTitle(cast(map.get("title"), String.class));
					cas.setCategory(cast(map.get("categoryname"), Integer.class));
					cases.add(cas);
				}
				return cases;
			}
		});
	}

	@Override
	public long findCountByFuzzy(String key, String value) {
		ESParam esParam = new ESParam();
		esParam.getqTypes().add(QType.FUZZY_PARAM);
		esParam.getKeys().add(key);
		esParam.getValues().add(value);
		return esDaoTemplate.queryForCount(esParam);
	}

	private <T> T cast(Object source, Class<T> target) {
		return TypeUtils.castToJavaBean(source, target);
	}

}
