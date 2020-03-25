package com.ctbri.ctuiinspection.dao.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ctbri.ctuiinspection.pojo.Topic;

/**
 * 主题数据层接口
 * 
 * @author Hogan
 *
 */
public interface TopicMapper {

	/**
	 * 
	 * @param lawyerName
	 * @return
	 */
	public List<Topic> findTopicsByLawyerName(@Param("lawyerName") String lawyerName);

	/**
	 * 
	 * @param lawyerId
	 * @return
	 */
	public List<String> findTopicByLawyerId(@Param("lawyerId") Integer lawyerId);

}
