package com.ctbri.ctuiinspection.dao.neo4j.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import org.neo4j.driver.v1.Record;
import org.neo4j.driver.v1.StatementResult;
import org.neo4j.driver.v1.Value;
import org.springframework.stereotype.Repository;

import com.ctbri.ctuiinspection.dao.neo4j.LawyerRelationDao;
import com.ctbri.ctuiinspection.service.impl.LawyerServiceImpl.LawyerRelationship;
import com.ctbri.ctuiinspection.type.RelationCategory;
import com.ctbri.dao.neo4j.core.Neo4jSearchExtractor;
import com.ctbri.dao.neo4j.core.Neo4jTemplate;

/**
 * 律师关系数据层实现
 * 
 * @author Hogan
 *
 */
@Repository("lawyerRelationDao")
public class LawyerRelationDaoImpl implements LawyerRelationDao {

	@Resource
	private Neo4jTemplate neo4jTemplate;

	@Override
	public LawyerRelationship findRelationLawyerByName(String lawyerName) {
		String cypher = "match (n:lawyer)--(x)--(y) where n.content='?' return n as start_node,y as end_node ";
		return neo4jTemplate.query(cypher, new Object[] { lawyerName }, new Neo4jSearchExtractor<LawyerRelationship>() {

			@Override
			public LawyerRelationship extractData(StatementResult sr) {
				Set<String> flag = new HashSet<>();
				LawyerRelationship result = new LawyerRelationship();
				List<LawyerRelationship> sons = new ArrayList<>();
				while (sr.hasNext()) {
					Record record = sr.next();
					if (result.getName() == null) {
						Value startNode = record.get("start_node");
						result.setId(startNode.get("node").asString());
						result.setName(startNode.get("content").asString());
						result.setCategory(RelationCategory.byId(startNode.get("category").asString()).getDescription());
					} else {
						Value endNode = record.get("end_node");
						if (!flag.contains(endNode.get("content").asString())) {
							LawyerRelationship son = new LawyerRelationship();
							son.setId(endNode.get("node").asString());
							son.setName(endNode.get("content").asString());
							son.setCategory(RelationCategory.byId(endNode.get("category").asString()).getDescription());
							sons.add(son);
							flag.add(endNode.get("content").asString());
						}
					}
				}
				result.setSons(sons);
				return result;
			}
		});
	}
}