package com.ctbri.ctuiinspection.util;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component("globalContext")
@Scope("singleton")
public class GlobalContext {

	private Map<String, List<Integer>> SearchWordMap = new HashMap<>();

	public Map<String, List<Integer>> getSearchWordMap() {
		return SearchWordMap;
	}

	public void setSearchWordMap(Map<String, List<Integer>> searchWordMap) {
		SearchWordMap = searchWordMap;
	}

}
