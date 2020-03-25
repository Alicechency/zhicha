package com.ctbri.ctuiinspection.dao.neo4j.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.neo4j.driver.v1.Record;
import org.neo4j.driver.v1.StatementResult;
import org.neo4j.driver.v1.Value;
import org.neo4j.driver.v1.util.Function;
import org.springframework.stereotype.Repository;

import com.ctbri.ctuiinspection.dao.neo4j.CaseRelationDao;
import com.ctbri.ctuiinspection.service.impl.LawyerServiceImpl.LawyerRelationship;
import com.ctbri.ctuiinspection.type.RelationCategory;
import com.ctbri.dao.neo4j.core.Neo4jSearchExtractor;
import com.ctbri.dao.neo4j.core.Neo4jTemplate;

@Repository("CaseRelationDao")
public class CaseRelationDaoImpl implements CaseRelationDao {

	@Resource
	private Neo4jTemplate neo4jTemplate;

	private static final Function<Value, LawyerRelationship> LawyerRelationship = new Function<Value, LawyerRelationship>() {
		public LawyerRelationship apply(Value val) {
			LawyerRelationship result = new LawyerRelationship();
			result.setId(val.get("node").asString());
			result.setCategory(RelationCategory.byId(val.get("category").asString()).getDescription());
			result.setName(val.get("content").asString());
			return result;
		}
	};

	@Override
	public List<List<LawyerRelationship>> findRelationCaseByName(Integer caseId) {
		String cypher = "match p=(n)-[*1..3]-(x) where n.node='?' return nodes(p) as node_list ";
		return neo4jTemplate.query(cypher, new Object[] { caseId },
				new Neo4jSearchExtractor<List<List<LawyerRelationship>>>() {

					@Override
					public List<List<LawyerRelationship>> extractData(StatementResult sr) {
						List<List<LawyerRelationship>> result = new ArrayList<>();
						while (sr.hasNext()) {
							Record record = sr.next();
							Value value = record.get("node_list");
							result.add(value.asList(LawyerRelationship));
						}
						return result;
					}
				});
	}

}
