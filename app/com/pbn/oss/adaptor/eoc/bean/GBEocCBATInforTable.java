package com.pbn.oss.adaptor.eoc.bean;

public class GBEocCBATInforTable { 
	
	    private java.lang.Integer cpuLoad = 0;
	    private java.lang.Integer memoryUsed = 0;
	    private java.lang.Integer temperatureThresholdMax = 0;
	    private java.lang.Integer currentTemperature = 0;
	    private java.lang.String modelType;
	    private java.lang.String fwVersion;
	    private java.lang.String hwVersion;
	    private java.lang.String mac;
	    private java.lang.String vendor;
	    private java.lang.Long upTime;
	    
	    private java.lang.String ip;
	    private java.lang.Integer alarmDetection;
	    
	    private java.lang.Integer onlineMacLimit;
	    private java.lang.Integer onlineAmount;
	    private java.lang.String serialNumber;

 
	    
	    private String id;
	    private int snmpVersion;
	    private String readCommunity;
	    private String writeCommunity;
	    
	    public GBEocCBATInforTable() {
			super();
		}

		//for adaptor_eoc_wanlong
	    public GBEocCBATInforTable(java.lang.String vendor,
                java.lang.String modelType, 
                java.lang.String hwVersion,
                java.lang.String fwVersion, 
                java.lang.String mac,			          
                java.lang.String ip,
                java.lang.Integer onlineAmount, 
                java.lang.Integer onlineMacLimit,
                java.lang.String id) {

			this.vendor = vendor;
			this.modelType = modelType;
			this.hwVersion = hwVersion;
			this.fwVersion = fwVersion;
			this.mac = mac;
			//this.upTime = upTime;
			this.ip = ip;
			this.onlineAmount = onlineAmount;
			this.onlineMacLimit = onlineMacLimit;
			this.id = id;
		
		}
	    
	    public GBEocCBATInforTable (
	    	java.lang.Integer cpuLoad,
		    java.lang.Integer memoryUsed,
		    java.lang.Integer temperatureThresholdMax,
		    java.lang.Integer currentTemperature,
		    java.lang.String modelType,
		    java.lang.String fwVersion,
		    java.lang.String hwVersion,
		    java.lang.String mac,
		    java.lang.String vendor,
		    java.lang.Long upTime,
		    java.lang.String ip,
		    java.lang.Integer alarmDetection,
		    java.lang.Integer onlineMacLimit,
		    java.lang.Integer onlineAmount,
		    java.lang.String serialNumber,
		    java.lang.String id) {
	    	
	        this.cpuLoad = cpuLoad;
	        this.memoryUsed = memoryUsed;
	        this.temperatureThresholdMax = temperatureThresholdMax;
	        this.currentTemperature = currentTemperature;
	        
	        this.modelType = modelType;
	        this.fwVersion = fwVersion;
	        this.hwVersion = hwVersion;
	        this.mac = mac;
	        
	        this.vendor = vendor;
	        this.upTime = upTime;
	        this.ip = ip;
	        
	        this.alarmDetection = alarmDetection;
	        this.onlineMacLimit = onlineMacLimit;
	        this.onlineAmount = onlineAmount;
	        this.serialNumber = serialNumber;
	        
	        this.id = id;
	    }
	    
	    public GBEocCBATInforTable (
			    java.lang.Integer currentTemperature,
			    java.lang.String modelType,
			    java.lang.String fwVersion,
			    java.lang.String hwVersion,
			    java.lang.String mac,
			    java.lang.String vendor,
			    java.lang.String ip,
			    java.lang.Integer alarmDetection,
			    java.lang.Integer onlineMacLimit,
			    java.lang.Integer onlineAmount,
			    java.lang.String serialNumber,
			    java.lang.String id) {
		    	
		        this.currentTemperature = currentTemperature;
		        
		        this.modelType = modelType;
		        this.fwVersion = fwVersion;
		        this.hwVersion = hwVersion;
		        this.mac = mac;
		        
		        this.vendor = vendor;
		        this.ip = ip;
		        
		        this.alarmDetection = alarmDetection;
		        this.onlineMacLimit = onlineMacLimit;
		        this.onlineAmount = onlineAmount;
		        this.serialNumber = serialNumber;
		        
		        this.id = id;
		    }
	    //for gcable
	    public GBEocCBATInforTable(Integer cpuLoad, Integer memoryUsed,
				Integer temperatureThresholdMax, Integer currentTemperature,
				String modelType, String fwVersion, String hwVersion,
				String mac, String vendor, String ip, Integer alarmDetection,
				Integer onlineMacLimit, Integer onlineAmount,
				String serialNumber, String id) {
			super();
			this.cpuLoad = cpuLoad;
			this.memoryUsed = memoryUsed;
			this.temperatureThresholdMax = temperatureThresholdMax;
			this.currentTemperature = currentTemperature;
			this.modelType = modelType;
			this.fwVersion = fwVersion;
			this.hwVersion = hwVersion;
			this.mac = mac;
			this.vendor = vendor;
			this.ip = ip;
			this.alarmDetection = alarmDetection;
			this.onlineMacLimit = onlineMacLimit;
			this.onlineAmount = onlineAmount;
			this.serialNumber = serialNumber;
			this.id = id;
		}

		public GBEocCBATInforTable (
			    java.lang.String modelType,
			    java.lang.String fwVersion,
			    java.lang.String hwVersion,
			    java.lang.String mac,
			    java.lang.String vendor,
			    java.lang.String ip,
			    java.lang.Integer alarmDetection,
			    java.lang.Integer onlineMacLimit,
			    java.lang.Integer onlineAmount,
			    java.lang.String serialNumber,
			    java.lang.String id) {
		        
		        this.modelType = modelType;
		        this.fwVersion = fwVersion;
		        this.hwVersion = hwVersion;
		        this.mac = mac;
		        
		        this.vendor = vendor;
		        this.ip = ip;
		        
		        this.alarmDetection = alarmDetection;
		        this.onlineMacLimit = onlineMacLimit;
		        this.onlineAmount = onlineAmount;
		        this.serialNumber = serialNumber;
		        
		        this.id = id;
		    }
		//for orientview
		public GBEocCBATInforTable(String fwVersion, String hwVersion,
				String mac, String ip, Integer alarmDetection, String id) {
			super();
			this.fwVersion = fwVersion;
			this.hwVersion = hwVersion;
			this.mac = mac;
			this.ip = ip;
			this.alarmDetection = alarmDetection;
			this.id = id;
		}

		public int getSnmpVersion() {
			return snmpVersion;
		}

		public void setSnmpVersion(int snmpVersion) {
			this.snmpVersion = snmpVersion;
		}

		public String getReadCommunity() {
			return readCommunity;
		}

		public void setReadCommunity(String readCommunity) {
			this.readCommunity = readCommunity;
		}

		public String getWriteCommunity() {
			return writeCommunity;
		}

		public void setWriteCommunity(String writeCommunity) {
			this.writeCommunity = writeCommunity;
		}

		public java.lang.Integer getAlarmDetection() {
			return alarmDetection;
		}


		public void setAlarmDetection(java.lang.Integer alarmDetection) {
			this.alarmDetection = alarmDetection;
		}


		public java.lang.Integer getCpuLoad() {
			return cpuLoad;
		}


		public void setCpuLoad(java.lang.Integer cpuLoad) {
			this.cpuLoad = cpuLoad;
		}


		public java.lang.Integer getCurrentTemperature() {
			return currentTemperature;
		}


		public void setCurrentTemperature(java.lang.Integer currentTemperature) {
			this.currentTemperature = currentTemperature;
		}


		public java.lang.String getFwVersion() {
			return fwVersion;
		}


		public void setFwVersion(java.lang.String fwVersion) {
			this.fwVersion = fwVersion;
		}


		public java.lang.String getHwVersion() {
			return hwVersion;
		}


		public void setHwVersion(java.lang.String hwVersion) {
			this.hwVersion = hwVersion;
		}
		
		public java.lang.String getIp() {
			return ip;
		}


		public void setIp(java.lang.String ip) {
			this.ip = ip;
		}


		public java.lang.String getMac() {
			return mac;
		}


		public void setMac(java.lang.String mac) {
			this.mac = mac;
		}


		public java.lang.Integer getMemoryUsed() {
			return memoryUsed;
		}


		public void setMemoryUsed(java.lang.Integer memoryUsed) {
			this.memoryUsed = memoryUsed;
		}


		public java.lang.String getModelType() {
			return modelType;
		}


		public void setModelType(java.lang.String modelType) {
			this.modelType = modelType;
		}

		public java.lang.Integer getOnlineAmount() {
			return onlineAmount;
		}


		public void setOnlineAmount(java.lang.Integer onlineAmount) {
			this.onlineAmount = onlineAmount;
		}


		public java.lang.Integer getOnlineMacLimit() {
			return onlineMacLimit;
		}


		public void setOnlineMacLimit(java.lang.Integer onlineMacLimit) {
			this.onlineMacLimit = onlineMacLimit;
		}

 


		public java.lang.Integer getTemperatureThresholdMax() {
			return temperatureThresholdMax;
		}


		public void setTemperatureThresholdMax(java.lang.Integer temperatureThresholdMax) {
			this.temperatureThresholdMax = temperatureThresholdMax;
		}




		public java.lang.Long getUpTime() {
			return upTime;
		}


		public void setUpTime(java.lang.Long upTime) {
			this.upTime = upTime;
		}


		public java.lang.String getVendor() {
			return vendor;
		}


		public void setVendor(java.lang.String vendor) {
			this.vendor = vendor;
		}

	    public java.lang.String getId () {
	        return id;
	    }
	    public void setId ( java.lang.String id ) {
	        this.id = id;
	    }

		public java.lang.String getSerialNumber() {
			return serialNumber;
		}

		public void setSerialNumber(java.lang.String serialNumber) {
			this.serialNumber = serialNumber;
		}
}
