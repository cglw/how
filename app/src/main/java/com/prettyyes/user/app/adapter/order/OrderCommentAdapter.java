package com.prettyyes.user.app.adapter.order;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.prettyyes.user.R;
import com.prettyyes.user.app.view.progress.StarBar;
import com.prettyyes.user.app.view.tvbtnetv.EditTextDel;
import com.prettyyes.user.model.order.OrderCommentList;

import java.util.ArrayList;

/**
 * Project Name: user
 * Package Name: com.prettyyes.user.app.adapter.order
 * Author: SmileChen
 * Created on: 2016/11/17
 * Description: Nothing
 */
public class OrderCommentAdapter extends BaseAdapter {


    private Context context;
    private ArrayList<OrderCommentList.ListEntity> datas;

    public OrderCommentAdapter(Context context) {
        this.context = context;
        this.datas = new ArrayList<>();
    }

    @Override
    public int getCount() {
        return datas.size();
    }

    @Override
    public Object getItem(int position) {
        return datas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }
    public ArrayList<OrderCommentList.ListEntity> getData()
    {
        return datas;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        convertView = LayoutInflater.from(context).inflate(R.layout.item_lv_comment_order, null);
        bindViews(convertView);
        tv_title.setText(datas.get(position).getUnit_name());
        starBar_server.setStarMark(datas.get(position).getWish_level());
        starBar_pretty.setStarMark(datas.get(position).getPretty_level());
        starBar_wish.setStarMark(datas.get(position).getServer_level());
        edit_info.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                datas.get(position).setContent(s.toString() + "");
            }
        });
        return convertView;
    }

    private TextView tv_title;
    private StarBar starBar_pretty;
    private StarBar starBar_wish;
    private StarBar starBar_server;
    private EditTextDel edit_info;

    // End Of Content View Elements

    private void bindViews(View view) {

        tv_title = (TextView) view.findViewById(R.id.tv_orderCommentAdp_title);
        starBar_pretty = (StarBar) view.findViewById(R.id.starBar_commentOrderAdp_pretty);
        starBar_wish = (StarBar) view.findViewById(R.id.starBar_commentOrderAdp_wish);
        starBar_server = (StarBar) view.findViewById(R.id.starBar_commentOrderAdp_server);
        edit_info = (EditTextDel) view.findViewById(R.id.edit_commentOrderAdp_info);
        starBar_pretty.setIntegerMark(true);
        starBar_wish.setIntegerMark(true);
        starBar_server.setIntegerMark(true);

    }

    public void addAll(ArrayList<OrderCommentList.ListEntity> datas) {
        this.datas.addAll(datas);
        for (int i = 0; i < datas.size(); i++) {
            this.datas.get(i).setPretty_level(5);
            this.datas.get(i).setServer_level(5);
            this.datas.get(i).setWish_level(5);
            this.datas.get(i).setContent("");
        }
        notifyDataSetChanged();
    }

    public void clear() {
        datas.clear();
        notifyDataSetChanged();
    }


}
