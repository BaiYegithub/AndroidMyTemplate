package app.vp.cn.data;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import org.litepal.LitePal;

import java.util.List;

import app.vp.cn.common.util.LogUtil;
import app.vp.cn.data.bean.MyClassBean;
import app.vp.cn.data.bean.StudentBean;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //创建数据库
        SQLiteDatabase db = LitePal.getDatabase();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int i = item.getItemId();
        switch (i) {
            //
            case R.id.one: //增加数据
                MyClassBean myClassBean = new MyClassBean();
                myClassBean.setClassId("1001");
                myClassBean.setMyClassName("高一1班");
                myClassBean.save();


                StudentBean studentBean = new StudentBean();
                studentBean.setStudentId("101");
                studentBean.setStudentName("高渐离");
                studentBean.setClassId(myClassBean.getClassId());
                studentBean.save();
                StudentBean studentBean1 = new StudentBean();
                studentBean1.setStudentId("102");
                studentBean1.setStudentName("韩信");
                studentBean1.setClassId(myClassBean.getClassId());
                studentBean1.save();

                break;
            //
            case R.id.two:
                Toast.makeText(this, "two", Toast.LENGTH_SHORT).show();
                break;
            //
            case R.id.three: //查询
                List<StudentBean> studentBeanList = LitePal.findAll(StudentBean.class);
                for (int j = 0; j < studentBeanList.size(); j++) {
                    LogUtil.i("mydata", "学生姓名:" + studentBeanList.get(j).getStudentName() + "所属班级" + studentBeanList.get(j).getMyClassBean().getMyClassName());
                }
                List<MyClassBean> myClassBeanList = LitePal.findAll(MyClassBean.class);
                List<StudentBean> studentBeanList1 = myClassBeanList.get(0).getStudentBeanList();
                break;
            //
            case R.id.four:
                Toast.makeText(this, "four", Toast.LENGTH_SHORT).show();
                break;
            //
            case R.id.five:
                Toast.makeText(this, "five", Toast.LENGTH_SHORT).show();
                break;
            //
            case R.id.six:
                Toast.makeText(this, "six", Toast.LENGTH_SHORT).show();
                break;
            //
            case R.id.seven:
                Toast.makeText(this, "seven", Toast.LENGTH_SHORT).show();
                break;
            //
            case R.id.eight:
                Toast.makeText(this, "eight", Toast.LENGTH_SHORT).show();
                break;

        }
        return true;
    }
}
