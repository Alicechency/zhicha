package com.ctbri.iinspection.service.bean;

import java.util.ArrayList;
import java.util.List;

import com.ctbri.iinspection.pojo.Suspect;
import com.ctbri.iinspection.pojo.SuspectDetail;

/**
 * 嫌疑人业务实体
 * 
 * @author Hogan
 *
 */
public class SuspectServiceBean {
	
	private Suspect suspect;
	private Integer index;
	private List<Double[]> coords;
	private List<Position> positions;

	public Suspect getSuspect() {
		return suspect;
	}

	public void setSuspect(Suspect suspect) {
		this.suspect = suspect;
	}

	public Integer getIndex() {
		return index;
	}

	public void setIndex(Integer index) {
		this.index = index;
	}

	public List<Double[]> getCoords() {
		if (this.coords == null && this.suspect != null && this.suspect.getSuspectDetails() != null) {
			List<Double[]> result = new ArrayList<Double[]>();
			List<SuspectDetail> details = this.suspect.getSuspectDetails();
			for (SuspectDetail detail : details) {
				if (detail.getActivityLatitude() != null && detail.getActivityLongtitude() != null) {
					Double[] coord = new Double[2];
					coord[0] = detail.getActivityLongtitude();
					coord[1] = detail.getActivityLatitude();
					result.add(coord);
				}
			}
			if (result.size() > 0) {
				coords = result;
			}
		}
		return coords;
	}

	public void setCoords(List<Double[]> coords) {
		this.coords = coords;
	}

	public List<Position> getPositions() {
		if (this.positions == null && this.suspect != null && this.suspect.getSuspectDetails() != null) {
			List<Position> result = new ArrayList<Position>();
			List<SuspectDetail> details = this.suspect.getSuspectDetails();
			for (SuspectDetail detail : details) {
				if (detail.getActivityLatitude() != null && detail.getActivityLongtitude() != null) {
					Position position = new Position();
					Double[] coord = new Double[2];
					coord[0] = detail.getActivityLongtitude();
					coord[1] = detail.getActivityLatitude();
					position.setName(detail.getActivityDate());
					position.setValue(coord);
					result.add(position);
				}
			}
			if (result.size() > 0) {
				positions = result;
			}
		}
		return positions;
	}

	public void setPositions(List<Position> positions) {
		this.positions = positions;
	}

	/**
	 * 轨迹需要用到的数据实体
	 * 
	 * @author Hogan
	 *
	 */
	public static class Position {

		private String name;
		private Double[] value;

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public Double[] getValue() {
			return value;
		}

		public void setValue(Double[] value) {
			this.value = value;
		}

	}
}
