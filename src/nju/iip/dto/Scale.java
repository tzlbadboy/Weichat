package nju.iip.dto;


/**
 * 量表对应的JavaBean
 * @author wangqiang
 * @since 2015-6-2
 */
public class Scale {
	
	String scaleName;//量表名
	
	String scaleDescription;//量表描述
	
	String shortname;//量表简称

	public String getScaleName() {
		return scaleName;
	}

	public void setScaleName(String scaleName) {
		this.scaleName = scaleName;
	}

	public String getScaleDescription() {
		return scaleDescription;
	}

	public void setScaleDescription(String scaleDescription) {
		this.scaleDescription = scaleDescription;
	}

	public String getShortname() {
		return shortname;
	}

	public void setShortname(String shortname) {
		this.shortname = shortname;
	}
	
	

}
