package com.ctbri.iinspection.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.ctbri.common.controller.CJSONObject;
import com.ctbri.common.type.ErrorCode;
import com.ctbri.iinspection.dao.mapper.SupervisoryAssociationMapper;
import com.ctbri.iinspection.pojo.SupervisoryAssociation;
import com.ctbri.iinspection.service.RelationGraphService;
import com.ctbri.iinspection.service.bean.SupervisoryAssociations;
import com.ctbri.iinspection.service.bean.SupervisoryAssociations.Category;
import com.ctbri.iinspection.service.bean.SupervisoryAssociations.Link;
import com.ctbri.iinspection.service.bean.SupervisoryAssociations.Node;

/**
 * 关联业务层实现
 * 
 * @author Hogan
 *
 */
@Service("relationGraphService")
public class RelationGraphServiceImpl implements RelationGraphService {

	@Resource
	private SupervisoryAssociationMapper supervisoryAssociationMapper;


	@Override
	public CJSONObject loadSuspectRelationship(JSONObject jParams) {
		CJSONObject result = new CJSONObject();
		JSONObject detail = new JSONObject();
		String word = jParams.getString("word");
		Integer firstLimit = jParams.getInteger("firstLimit");
		Integer secondLimit = jParams.getInteger("secondLimit");
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("word", word);
		map.put("limit", firstLimit);
		List<SupervisoryAssociation> firstAssociations = supervisoryAssociationMapper.findByStartWord(map);
		Set<String> suspectIds = new HashSet<String>();
		if (firstAssociations != null && firstAssociations.size() > 0) {
			List<Node> nodes = new ArrayList<Node>();
			List<Link> links = new ArrayList<Link>();
			List<Category> categories = new ArrayList<Category>();
			SupervisoryAssociations supervisoryAssociations = new SupervisoryAssociations();
			Set<Object> set = new HashSet<Object>();
			//中心节点
			Node centerNode = new Node();
			centerNode.setValue(firstAssociations.get(0).getStartPersonId());
			centerNode.setCategory(firstAssociations.get(0).getStartCategory());
			centerNode.setDraggable("true");
			centerNode.setName(firstAssociations.get(0).getStartPersonName());
			centerNode.setSymbolSize(40);
			nodes.add(centerNode);
			//中心节点类型
			if (!set.contains(firstAssociations.get(0).getStartCategory())) {
				set.add(firstAssociations.get(0).getStartCategory());
				Category category = new Category();
				category.setName(firstAssociations.get(0).getStartCategory());
				categories.add(category);
			}
			for (SupervisoryAssociation first : firstAssociations) {
				//中心节点类型与二级节点的连接
				Link firstLink = new Link();
				firstLink.setSource(word);
				firstLink.setTarget(first.getEndPersonName());
				links.add(firstLink);
				suspectIds.add(first.getEndPersonId());//<<-添加嫌疑人id
				//二级节点
				if (!set.contains(first.getEndPersonName())) {
					set.add(first.getEndPersonName());
					Node secondNode = new Node();
					secondNode.setValue(first.getEndPersonId());
					secondNode.setCategory(first.getEndCategory());
					secondNode.setDraggable("true");
					secondNode.setName(first.getEndPersonName());
					secondNode.setSymbolSize(35);
					nodes.add(secondNode);
				}
				//二级节点类型
				if (!set.contains(first.getEndCategory())) {
					set.add(first.getEndCategory());
					Category secondCategory = new Category();
					secondCategory.setName(first.getEndCategory());
					categories.add(secondCategory);
				}
				//查询三级节点
				map.put("word", first.getEndPersonName());
				map.put("limit", secondLimit);
				map.put("excludeWord", word);
				List<SupervisoryAssociation> secondAssociations = supervisoryAssociationMapper.findByStartWordAndExcludeEndWord(map);
				if (secondAssociations != null && secondAssociations.size() > 0) {
					for (SupervisoryAssociation second : secondAssociations) {
						//二级节点类型与三级节点的连接
						Link secondLink = new Link();
						secondLink.setSource(second.getStartPersonName());
						secondLink.setTarget(second.getEndPersonName());
						links.add(secondLink);
						suspectIds.add(second.getEndPersonId());//<<-添加嫌疑人id
						//三级节点
						if (!set.contains(second.getEndPersonName())) {
							set.add(second.getEndPersonName());
							Node thridNode = new Node();
							thridNode.setValue(second.getEndPersonId());
							thridNode.setCategory(second.getEndCategory());
							thridNode.setDraggable("true");
							thridNode.setName(second.getEndPersonName());
							thridNode.setSymbolSize(30);
							nodes.add(thridNode);
						}
						//三级节点类型
						if (!set.contains(second.getEndCategory())) {
							set.add(second.getEndCategory());
							Category thridCategory = new Category();
							thridCategory.setName(second.getEndCategory());
							categories.add(thridCategory);
						}
					}
				}
			}
			supervisoryAssociations.setSuspectIds(suspectIds);
			supervisoryAssociations.setCategories(categories);
			supervisoryAssociations.setLinks(links);
			supervisoryAssociations.setNodes(nodes);
			detail.put("supervisoryAssociations", supervisoryAssociations);
		}
		result.setDetail(detail);
		result.setErrorCode(ErrorCode.SUCCESS);
		return result;
	}

}
