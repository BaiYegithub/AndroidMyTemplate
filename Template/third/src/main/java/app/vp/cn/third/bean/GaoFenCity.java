package app.vp.cn.third.bean;

/**
 * Created by baiye
 * Date: 2020/8/23
 * Time: 18:26
 * Description:
 */
public class GaoFenCity {
    private String id;
    private String name;

    public GaoFenCity(String id, String name) {
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
