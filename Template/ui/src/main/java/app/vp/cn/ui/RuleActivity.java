package app.vp.cn.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import yanzhikai.ruler.BooheeRuler;
import yanzhikai.ruler.KgNumberLayout;

public class RuleActivity extends AppCompatActivity {

    private BooheeRuler mBooheeRuler;
    private KgNumberLayout mKgNumberLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rule);

        mBooheeRuler = (BooheeRuler) findViewById(R.id.br);
        mKgNumberLayout = (KgNumberLayout) findViewById(R.id.knl);
        mKgNumberLayout.bindRuler(mBooheeRuler);
    }
}