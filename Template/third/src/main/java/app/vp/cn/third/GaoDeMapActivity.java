package app.vp.cn.third;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import app.vp.cn.third.bean.GaoFenCity;
import app.vp.cn.third.bean.OldCity;
import app.vp.cn.third.db.CityDB;
import app.vp.cn.third.db.GaoFenDB;

public class GaoDeMapActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gao_de_map);
        List<OldCity> oldCity = CityDB.getCityDB(GaoDeMapActivity.this).getOldCity();

        Map<String, String> OldMap = new HashMap<>();
        for (int i = oldCity.size() - 1; i >= 0; i--) {
            if (!oldCity.get(i).getId().startsWith("CN")) {
                oldCity.remove(i);
            } else {
                String StrId = oldCity.get(i).getId();
                String subId = StrId.substring(2, StrId.length());
                Log.i("bai", "onCreate: subId是" + subId);
//                oldCity.get(i).setId(subId);
                OldMap.put(subId, oldCity.get(i).getName());
            }
        }

        List<GaoFenCity> gaoFenCity = GaoFenDB.getGaoFenDB(GaoDeMapActivity.this).getGaoFenCity();

        Map<String, String> gaoFenMap = new HashMap<>();
        for (int i = 0; i < gaoFenCity.size(); i++) {
            GaoFenCity gaoFenCityBean = gaoFenCity.get(i);
            if (OldMap.containsKey(gaoFenCityBean.getId()) && (gaoFenCityBean.getName().startsWith(OldMap.get(gaoFenCityBean.getId()))
                    || OldMap.get(gaoFenCityBean.getId()).startsWith(gaoFenCityBean.getName()))) {
                OldMap.remove(gaoFenCity.get(i).getId());
            } else {
                gaoFenMap.put(gaoFenCityBean.getId(), gaoFenCityBean.getName());
            }
        }
        Log.i("bai", "onCreate: Oldmap的size是" + OldMap.size());
        Log.i("bai", "onCreate: gaoFenMap的size是" + gaoFenMap.size());
        int i = 0;
        int j = 0;
        for (Map.Entry<String, String> entry : OldMap.entrySet()) {
            if (gaoFenMap.containsKey(entry.getKey())) {
                i++;
                Log.i("bai名称不一致的", "onCreate: "+entry.getKey()+gaoFenMap.get(entry.getKey()));
                Log.i("bai", "onCreate:名称不一致的个数有 "+i);
            }else {
                j++;
                Log.i("bai无", "onCreate:高分天气没有的 "+entry.getKey()+ entry.getValue());
//                Log.i("bai无", "onCreate:高分天气没有的个数有 "+j);
            }
        }

    }
}
