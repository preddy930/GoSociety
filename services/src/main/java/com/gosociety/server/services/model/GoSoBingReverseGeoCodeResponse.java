package com.gosociety.server.services.model;

import java.util.List;

public class GoSoBingReverseGeoCodeResponse {

	public List<ResourceSets> resourceSets;
	
	public List<ResourceSets> getResourceSets() {
		return resourceSets;
	}

	public void setResourceSets(List<ResourceSets> resourceSets) {
		this.resourceSets = resourceSets;
	}
	
	public String getCity() {
		return resourceSets.get(0).getResources().get(0).getAddress().getLocality();
	}
	
	public String getState() {
		return resourceSets.get(0).getResources().get(0).getAddress().getAdminDistrict();
	}
	
	public static class ResourceSets {
	
		int estimatedTotal;
		public List<Resources> resources;

		public List<Resources> getResources() {
			return resources;
		}

		public void setResources(List<Resources> resources) {
			this.resources = resources;
		}
	
		public static class Resources {
		
			String name;
			String confidence;
			String entityType;
			BingAddress address;

			public BingAddress getAddress() {
				return address;
			}

			public void setAddress(BingAddress address) {
				this.address = address;
			}
		
			public static class BingAddress {
			
				String adminDistrict;
				String locality;
				String postalCode;
				String addressLine;
			
				public String getLocality() {
					return locality;
				}
				
				public void setLocality(String locality) {
					this.locality = locality;
				}
				
				public String getPostalCode() {
					return postalCode;
				}
				
				public void setPostalCode(String postalCode) {
					this.postalCode = postalCode;
				}
				
				public String getAddressLine() {
					return addressLine;
				}
			
				public void setAddressLine(String addressLine) {
					this.addressLine = addressLine;
				}

				public String getAdminDistrict() {
					return adminDistrict;
				}

				public void setAdminDistrict(String adminDistrict) {
					this.adminDistrict = adminDistrict;
				}
			}

			public String getName() {
				return name;
			}

			public void setName(String name) {
				this.name = name;
			}

			public String getConfidence() {
				return confidence;
			}

			public void setConfidence(String confidence) {
				this.confidence = confidence;
			}

			public String getEntityType() {
				return entityType;
			}

			public void setEntityType(String entityType) {
				this.entityType = entityType;
			}
		}

		public int getEstimatedTotal() {
			return estimatedTotal;
		}

		public void setEstimatedTotal(int estimatedTotal) {
			this.estimatedTotal = estimatedTotal;
		}
	}
}
