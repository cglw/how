package com.prettyyes.user.app.view.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.prettyyes.user.R;
import com.prettyyes.user.api.NetReqCallback;
import com.prettyyes.user.api.netXutils.requests.BankListRequest;
import com.prettyyes.user.api.netXutils.response.BandListRes;
import com.prettyyes.user.app.view.wheeladapter.AbstractWheelTextAdapter;
import com.prettyyes.user.app.view.wheelviews.OnWheelChangedListener;
import com.prettyyes.user.app.view.wheelviews.OnWheelScrollListener;
import com.prettyyes.user.app.view.wheelviews.WheelView;
import com.prettyyes.user.model.v8.BankEntity;

import java.util.ArrayList;

/**
 * Created by chengang on 2017/6/13.
 */

public class BankSelectDialog extends Dialog {
    private static final String TAG = "BankSelectDialog";
    private int maxTextSize = 24;
    private int minTextSize = 14;
    private ArrayList<BankEntity> banks = new ArrayList<>();

    private WheelView wheelView;
    private TextView btn_sure;
    private TextView btn_cancel;
    private BankAdapter bankAdapter;

    public BankSelectDialog(@NonNull Context context) {

        super(context, R.style.dialog);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_changebank);
        wheelView = (WheelView) findViewById(R.id.wv_bank);
        btn_cancel = (TextView) findViewById(R.id.btn_cancel);
        btn_sure = (TextView) findViewById(R.id.btn_sure);
        btn_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
        btn_sure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
                if (onBankListener != null)
                    onBankListener.onClick(selectBank);
            }
        });

        new BankListRequest().post(new NetReqCallback<BandListRes>() {
            @Override
            public void apiRequestFail(String message, String method) {

            }

            @Override
            public void apiRequestSuccess(BandListRes bandListRes, String method) {

                ArrayList<BankEntity> bankEntities = new ArrayList<BankEntity>();
                for (int i = 0; i < bandListRes.getList().size(); i++) {
                    ArrayList<Object> strings = bandListRes.getList().get(i);
                    BankEntity bankEntity = new BankEntity();
                    bankEntity.setBank_id(strings.get(0) + "");
                    bankEntity.setBank_name(strings.get(1) + "");
                    bankEntities.add(bankEntity);
                }
                setData(bankEntities);
            }
        });
    }

    private BankEntity selectBank;

    public void setBankListener(OnBankListener onSexListener) {
        this.onBankListener = onSexListener;
    }

    private OnBankListener onBankListener;

    public interface OnBankListener {
        public void onClick(BankEntity selectBank);
    }

    public void setData(ArrayList<BankEntity> data) {
        banks.addAll(data);
        selectBank = data.get(0);
        bankAdapter = new BankAdapter(getContext(), data, 0, 24, 14);
        wheelView.setViewAdapter(bankAdapter);

        wheelView.addChangingListener(new OnWheelChangedListener() {

            @Override
            public void onChanged(WheelView wheel, int oldValue, int newValue) {
                // TODO Auto-generated method stub
                String currentText = (String) bankAdapter.getItemText(wheel.getCurrentItem());
                selectBank = bankAdapter.getItem(wheel.getCurrentItem());
                setTextviewSize(currentText, bankAdapter);

            }
        });

        wheelView.addScrollingListener(new OnWheelScrollListener() {

            @Override
            public void onScrollingStarted(WheelView wheel) {
                // TODO Auto-generated method stub

            }

            @Override
            public void onScrollingFinished(WheelView wheel) {
                // TODO Auto-generated method stub
                String currentText = (String) bankAdapter.getItemText(wheel.getCurrentItem());
                setTextviewSize(currentText, bankAdapter);
            }
        });


    }


    private class BankAdapter extends AbstractWheelTextAdapter {
        ArrayList<BankEntity> list;

        protected BankAdapter(Context context, ArrayList list, int currentItem, int maxsize, int minsize) {
            super(context, R.layout.item_dialog_selectcity, NO_RESOURCE, currentItem, maxsize, minsize);
            this.list = list;
            setItemTextResource(R.id.tempValue);
        }

        @Override
        public View getItem(int index, View cachedView, ViewGroup parent) {
            View view = super.getItem(index, cachedView, parent);
            return view;
        }

        @Override
        public int getItemsCount() {
            return list.size();
        }

        @Override
        protected CharSequence getItemText(int index) {
            return list.get(index).getBank_name();
        }

        public BankEntity getItem(int index) {
            return list.get(index);
        }
    }

    /**
     * 设置字体大小
     *
     * @param curriteItemText
     * @param adapter
     */
    private void setTextviewSize(String curriteItemText, AbstractWheelTextAdapter adapter) {
        ArrayList<View> arrayList = adapter.getTestViews();
        int size = arrayList.size();
        String currentText;
        for (int i = 0; i < size; i++) {
            TextView textvew = (TextView) arrayList.get(i);
            currentText = textvew.getText().toString();
            if (curriteItemText.equals(currentText)) {
                textvew.setTextSize(maxTextSize);
            } else {
                textvew.setTextSize(minTextSize);
            }
        }
    }

}
