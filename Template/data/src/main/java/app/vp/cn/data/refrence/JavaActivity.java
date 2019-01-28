package app.vp.cn.data.refrence;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import app.vp.cn.common.util.LogUtil;
import app.vp.cn.data.R;

public class JavaActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_java);
        setTitle("第一个Activity");

        LogUtil.i1("打印日志");
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
            case R.id.one:
                Toast.makeText(this, "one", Toast.LENGTH_SHORT).show();
                break;
            //
            case R.id.two:
                Toast.makeText(this, "two", Toast.LENGTH_SHORT).show();
                break;
            //
            case R.id.three:
                Toast.makeText(this, "three", Toast.LENGTH_SHORT).show();
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
