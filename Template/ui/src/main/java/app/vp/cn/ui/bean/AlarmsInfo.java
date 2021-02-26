package app.vp.cn.ui.bean;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * 灾害预警-中国特有--k扩展国外
 */
public class AlarmsInfo implements Serializable {
	private static final long serialVersionUID = 1L;
	/**标题*/
	private String title;
	/**类型*/
	private String type;
	/**级别*/
	private String level;
	/**状态*/
	private String stat;
	/**描述*/
	private String txt;

	public AlarmsInfo(String title, String type, String level, String stat, String txt) {
		this.title = title;
		this.type = type;
		this.level = level;
		this.stat = stat;
		this.txt = txt;
	}

	public static long getSerialVersionUID() {
		return serialVersionUID;
	}

	public String getTitle() {
		return title == null ? "" : title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getType() {
		return type == null ? "" : type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getLevel() {
		return level == null ? "" : level;
	}

	public void setLevel(String level) {
		this.level = level;
	}

	public String getStat() {
		return stat == null ? "" : stat;
	}

	public void setStat(String stat) {
		this.stat = stat;
	}

	public String getTxt() {
		return txt == null ? "" : txt;
	}

	public void setTxt(String txt) {
		this.txt = txt;
	}
}
