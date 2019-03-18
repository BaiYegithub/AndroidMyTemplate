package app.vp.cn.frame.bean;

/**
 * author : by
 * date: 2018/11/28 0028  下午 5:33.
 * describe 问答列表的Bean
 */

public class QaBean {
    private int comeFrom;//来源  0是我的 1 是老师的
    private String time; //发送时间
    private String content;//发送内容
    private String url; //头像
    private String teacherName;//老师姓名

    public int getComeFrom() {
        return comeFrom;
    }

    public void setComeFrom(int comeFrom) {
        this.comeFrom = comeFrom;
    }

    public String getTime() {
        return time == null ? "" : time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getContent() {
        return content == null ? "" : content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getUrl() {
        return url == null ? "" : url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getTeacherName() {
        return teacherName == null ? "" : teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }

    public QaBean(int comeFrom, String time, String content, String url, String teacherName) {
        this.comeFrom = comeFrom;
        this.time = time;
        this.content = content;
        this.url = url;
        this.teacherName = teacherName;
    }
}
