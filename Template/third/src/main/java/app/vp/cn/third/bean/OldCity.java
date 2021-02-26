package app.vp.cn.third.bean;

/**
 * Created by baiye
 * Date: 2020/8/23
 * Time: 17:50
 * Description:
 */
public class OldCity {
    private String id;
    private String name;

    public OldCity(String id, String name) {
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
}
