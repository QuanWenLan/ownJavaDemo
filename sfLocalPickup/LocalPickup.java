package com.lanwq.util;

public class LocalPickup {

    private String localPickupId;
    private String area;
    private String address;
    private String pickupType;
    private String typeName;
    
	public String getLocalPickupId() {
		return localPickupId;
	}
	public String getArea() {
		return area;
	}
	public String getAddress() {
		return address;
	}
	public String getPickupType() {
		return pickupType;
	}
	public String getTypeName() {
		return typeName;
	}
	public void setLocalPickupId(String localPickupId) {
		this.localPickupId = localPickupId;
	}
	public void setArea(String area) {
		this.area = area;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public void setPickupType(String pickupType) {
		this.pickupType = pickupType;
	}
	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	@Override
	public String toString() {
		return "LocalPickup{" +
				"localPickupId='" + localPickupId + '\'' +
				", area='" + area + '\'' +
				", address='" + address + '\'' +
				", pickupType='" + pickupType + '\'' +
				", typeName='" + typeName + '\'' +
				'}';
	}
}
