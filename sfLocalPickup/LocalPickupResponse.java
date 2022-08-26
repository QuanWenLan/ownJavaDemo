package com.lanwq.util;

import com.google.common.collect.Lists;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LocalPickupResponse extends BaseResponse {
    private List<LocalPickup> pickups = Lists.newArrayList();
    private int count;
    private Map<String, List<LocalPickup>> areaPickups = new HashMap<>();
    
	public List<LocalPickup> getPickups() {
		return pickups;
	}
	public int getCount() {
		return count;
	}
	public void setPickups(List<LocalPickup> pickups) {
		this.pickups = pickups;
	}
	public void setCount(int count) {
		this.count = count;
	}

	public Map<String, List<LocalPickup>> getAreaPickups() {
		return areaPickups;
	}

	public void setAreaPickups(Map<String, List<LocalPickup>> areaPickups) {
		this.areaPickups = areaPickups;
	}
}
