package com.go2wheel.util;

public class MyJsonApiUrlBuilder {

		private StringBuilder fieldsb = new StringBuilder();
		private StringBuilder includesb = new StringBuilder();
		private StringBuilder pagesb = new StringBuilder();
		
		private String groupPrefix = "";
		
		private String prepend;
		
		public MyJsonApiUrlBuilder(String prepend) {
			this.prepend = prepend;
		}
		
		public MyJsonApiUrlBuilder includes(String...resourceNames) {
			includesb = new StringBuilder();
			includesb.append("include=");
			String prefix = "";
			for(String rn : resourceNames) {
				includesb.append(prefix);
				prefix = ",";
				includesb.append(rn);
			}
			return this;
		}
		
		public MyJsonApiUrlBuilder filters(String fieldName, String value) {
			fieldsb.append(groupPrefix);
			groupPrefix = "&";
			fieldsb.append("filter[")
			.append(fieldName)
			.append("]=")
			.append(value);
			return this;
		}
		
		public MyJsonApiUrlBuilder filters(String fieldName, Long value) {
			return filters(fieldName, String.valueOf(value));
		}
		
		
		public MyJsonApiUrlBuilder page(int limit, int offset) {
			pagesb = new StringBuilder();
			pagesb.append(groupPrefix);
			groupPrefix = "&";
			pagesb.append("page[limit]=").append(limit).append(groupPrefix).append("page[offset]=").append(offset);
			return this;
		}
		
		public MyJsonApiUrlBuilder resouceFields(String resourceName, String...fieldNames) {
			fieldsb.append(groupPrefix);
			groupPrefix = "&";
			fieldsb.append("fields[")
			.append(resourceName)
			.append("]=");
			String prefix = "";
			for(String fn : fieldNames) {
				fieldsb.append(prefix);
				prefix = ",";
				fieldsb.append(fn);
			}
			return this;
		}
		
		public String build() {
			if (includesb.length() > 0) {
				return includesb.insert(0, prepend).append("&").append(fieldsb).append(pagesb).toString();
			} else {
				return includesb.insert(0, prepend).append(fieldsb).append(pagesb).toString();
			}
			
		}
	
}
