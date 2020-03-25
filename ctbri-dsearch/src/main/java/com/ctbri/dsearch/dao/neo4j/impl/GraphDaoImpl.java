package com.ctbri.dsearch.dao.neo4j.impl;

import java.util.HashMap;
import java.util.Map;

import org.neo4j.driver.v1.Record;
import org.neo4j.driver.v1.StatementResult;
import org.neo4j.driver.v1.Value;
import org.springframework.stereotype.Repository;

import com.ctbri.dao.neo4j.core.Neo4jSearchExtractor;
import com.ctbri.dao.neo4j.core.Neo4jTemplate;
import com.ctbri.dsearch.dao.neo4j.GraphDao;

/**
 * neo4j图形数据层接口
 * 
 * @author Hogan
 *
 */
@Repository("graphDao")
public class GraphDaoImpl implements GraphDao {

	private Neo4jTemplate neo4jTemplate;

	@Override
	public Map<String, Double> findRelationWords(String word, int limit) {
		String cypher = "match(n{content:'?'})-[r]-(f) RETURN n as startNode, r as relation ORDER by r.weight DESC limit ? ";
		return neo4jTemplate.query(cypher, new Object[] { word, limit }, new Neo4jSearchExtractor<Map<String, Double>>() {

			@Override
			public Map<String, Double> extractData(StatementResult sr) {
				Map<String, Double> result = new HashMap<>();
				while (sr.hasNext()) {
					Record record = sr.next();
					Value relation = record.get("relation");
					if (word.equals(relation.get("from").asString())) {
						result.put(relation.get("to").asString(), relation.get("weight").asDouble());
					}
				}
				result.put(word, 10.0);
				return result;
			}
		});
	}

}
