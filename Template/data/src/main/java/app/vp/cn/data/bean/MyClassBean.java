package app.vp.cn.data.bean;

import org.litepal.LitePal;
import org.litepal.crud.LitePalSupport;

import java.util.ArrayList;
import java.util.List;

/**
 * author : by
 * date: 2019/1/29 0029  下午 4:30.
 * describe 班级的Bean 也是数据库里的班级表
 */

public class MyClassBean extends LitePalSupport {

    //主键是integer类型的，自增

    //班级id 唯一
    private String classId;
    //班级名称
    private String myClassName;
    //建立表关联，实例化studentBeanList
    private List<StudentBean> studentBeanList = new ArrayList<StudentBean>();

    public String getClassId() {
        return classId;
    }

    public void setClassId(String classId) {
        this.classId = classId;
    }

    public String getMyClassName() {
        return myClassName;
    }

    public void setMyClassName(String myClassName) {
        this.myClassName = myClassName;
    }

    public List<StudentBean> getStudentBeanList() {

        List<StudentBean> studentBeanList = LitePal.where("classid = ?", classId).find(StudentBean.class);
        return studentBeanList;
    }

}
