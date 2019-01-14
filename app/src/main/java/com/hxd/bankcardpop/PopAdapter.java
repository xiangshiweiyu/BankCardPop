package com.hxd.bankcardpop;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Content：
 * Actor：韩小呆 ヾ(✿ﾟ▽ﾟ)ノ
 * Time:  2019/01/05 22:09
 * Update:
 * Time:
 */
public class PopAdapter extends RecyclerView.Adapter<PopAdapter.MyViewHolder> {

    private Context mContext;
    private List<CardBean> cards;
    private OnItemClickListener mOnItemClickListener;

    private HashMap<Integer, Boolean> map;


    @SuppressLint("UseSparseArrays")
    PopAdapter(Context mContext) {
        this.mContext = mContext;
        map = new HashMap<>();
        cards = new ArrayList<>();
    }


    public void setData(List<CardBean> cardBeans) {
        this.cards.clear();
        this.cards.addAll(cardBeans);

        map.clear();
        for (int i = 0; i < this.cards.size(); i++) {
            map.put(i, false);
        }
    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new MyViewHolder(LayoutInflater.from(mContext).inflate(R.layout.item_pop, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder myViewHolder, final int i) {
        CardBean card = cards.get(i);

        myViewHolder.tvName.setText(card.getCard_bank());

        if (card.getId() == 0) {
            myViewHolder.rbSelect.setVisibility(View.INVISIBLE);
            myViewHolder.ibDel.setVisibility(View.INVISIBLE);
        } else {
            myViewHolder.rbSelect.setVisibility(View.VISIBLE);
            myViewHolder.ibDel.setVisibility(View.VISIBLE);
        }

        myViewHolder.rbSelect.setChecked(map.get(i));

        myViewHolder.ibDel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int pos = myViewHolder.getLayoutPosition();
                mOnItemClickListener.onDelClick(myViewHolder.ibDel, pos);
            }
        });

        myViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int pos = myViewHolder.getLayoutPosition();
                mOnItemClickListener.onSelectClick(myViewHolder.itemView, pos);

                myViewHolder.rbSelect.setChecked(true);

                map.put(pos, map.get(pos));
                notifyDataSetChanged();
                singleSelect(pos);


            }
        });


    }


    //设置单选
    private void singleSelect(int pos) {
        Set<Map.Entry<Integer, Boolean>> entries = map.entrySet();
        for (Map.Entry<Integer, Boolean> entry : entries) {
            entry.setValue(false);
        }
        map.put(pos, true);
        notifyDataSetChanged();
    }


    @Override
    public int getItemCount() {
        return cards.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        private TextView tvName;
        private ImageButton ibDel;
        private RadioButton rbSelect;

        MyViewHolder(@NonNull View itemView) {
            super(itemView);

            tvName = itemView.findViewById(R.id.tv_card_name);
            ibDel = itemView.findViewById(R.id.ib_del);
            rbSelect = itemView.findViewById(R.id.cb_card);

        }
    }

    public interface OnItemClickListener {
        void onDelClick(View view, int pos);

        void onSelectClick(View view, int pos);
    }

    void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        mOnItemClickListener = onItemClickListener;
    }
}
