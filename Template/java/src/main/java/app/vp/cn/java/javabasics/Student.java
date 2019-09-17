package app.vp.cn.java.javabasics;

/**
 * Created by baiye
 * Date: 2019/8/24
 * Time: 16:05
 * Description:
 */
public class Student {
    private String id;
    private String name;

    public Student(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getId() {
        return id == null ? "" : id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name == null ? "" : name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }

    @Override
    public boolean equals(Object obj) {

        if(obj instanceof Student){
           return  ((Student) obj).getId().equals(getId());
        }else {
            return false;
        }
    }
}
