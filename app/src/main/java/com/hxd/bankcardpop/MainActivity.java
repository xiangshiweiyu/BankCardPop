package com.hxd.bankcardpop;

import android.annotation.SuppressLint;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, PopAdapter.OnItemClickListener {


    private EditText etName;
    private EditText etBank;
    private EditText etBankNum;
    private EditText etPhone;
    private Button btnShow;
    private Button btnAdd;
    private RelativeLayout rlMain;

    private PopupWindow popCard;
    private PopAdapter adapter;
    private View view;
    private List<CardBean> cardBeans;
    private int tag = 5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rlMain = findViewById(R.id.rl_main);
        etName = findViewById(R.id.et_name);
        etBank = findViewById(R.id.et_bank);
        etBankNum = findViewById(R.id.et_bank_num);
        etPhone = findViewById(R.id.et_phone);
        btnShow = findViewById(R.id.btn_show);
        btnAdd = findViewById(R.id.btn_add);

        adapter = new PopAdapter(this);

        adapter.setOnItemClickListener(this);
        btnAdd.setOnClickListener(this);
        btnShow.setOnClickListener(this);

        cardBeans = new ArrayList<>();

        for (int i = 1; i < 5; i++) {
            cardBeans.add(new CardBean(i, "a" + i, "韩小呆" + i, "招商银行" + i, "622700013209087200" + i, "1383146022" + i));
        }
        cardBeans.add(new CardBean(0, "0", "0", "使用新卡提现", "0", "0"));

        initPopView();
    }

    @SuppressLint("InflateParams")
    private void initPopView() {
        view = getLayoutInflater().inflate(R.layout.pop_card, null, false);
        FrameLayout flPop = view.findViewById(R.id.fl_pop);
        RecyclerView rlvPop = view.findViewById(R.id.rlv_pop);

        rlvPop.setLayoutManager(new LinearLayoutManager(this));
        rlvPop.setAdapter(adapter);
        flPop.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {

        int id = v.getId();

        switch (id) {
            case R.id.btn_add:
                addData();
                break;
            case R.id.btn_show:
                showPop();
                break;
            case R.id.fl_pop:
                popCard.dismiss();
                break;
        }

    }

    private void addData() {
        tag++;
        String username = etName.getText().toString().trim();
        String bank = etBank.getText().toString().trim();
        String bankNum = etBankNum.getText().toString().trim();
        String phone = etPhone.getText().toString().trim();

        if (TextUtils.isEmpty(bank)) {
            Toast.makeText(this, "必须输入银行名称！", Toast.LENGTH_SHORT).show();
        } else {
            cardBeans.remove(cardBeans.size() - 1);
            cardBeans.add(new CardBean(tag, "" + tag, username, bank + tag, bankNum, phone));
            cardBeans.add(new CardBean(0, "0", "0", "使用新卡提现", "0", "0"));
            Toast.makeText(this, "添加成功！", Toast.LENGTH_LONG).show();

        }
    }


    private void showPop() {
        if (popCard == null) {
            popCard = new PopupWindow(view, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT, true);
        }
        popCard.setBackgroundDrawable(new ColorDrawable());
        popCard.showAtLocation(rlMain, Gravity.CENTER, 0, 0);
        adapter.setData(cardBeans);
        adapter.notifyDataSetChanged();

    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (popCard != null) {
            popCard.dismiss();
        }
    }


    @Override
    public void onDelClick(View view, int pos) {
        cardBeans.remove(pos);
        Toast.makeText(this, "删除第 " + (pos + 1) + " 条数据成功", Toast.LENGTH_LONG).show();
        popCard.dismiss();
    }

    @Override
    public void onSelectClick(View view, int pos) {
        Toast.makeText(this, "选择第 " + (pos + 1) + " 条数据成功", Toast.LENGTH_LONG).show();
//        popCard.dismiss();
    }
}
