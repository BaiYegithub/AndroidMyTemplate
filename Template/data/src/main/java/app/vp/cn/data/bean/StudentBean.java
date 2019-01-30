package app.vp.cn.data.bean;

import org.litepal.LitePal;
import org.litepal.crud.LitePalSupport;

import java.util.List;

/**
 * author : by
 * date: 2019/1/29 0029  下午 4:37.
 * describe 学生表 用来存储学生信息
 */

public class StudentBean extends LitePalSupport {


    private String studentId;
    private String studentName;

    private String classId;
    private MyClassBean myClassBean;

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getClassId() {
        return classId;
    }

    public void setClassId(String classId) {
        this.classId = classId;
    }

    public MyClassBean getMyClassBean() {
        List<MyClassBean> myClassBeanList = LitePal.where("classid = ?", classId).find(MyClassBean.class);
        if (myClassBeanList.size() > 0) {
            return myClassBeanList.get(0);
        }

        return null;
    }

}
