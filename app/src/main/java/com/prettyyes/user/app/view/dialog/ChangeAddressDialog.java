package com.prettyyes.user.app.view.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.prettyyes.user.R;
import com.prettyyes.user.api.NetReqCallback;
import com.prettyyes.user.api.netXutils.ApiImpls.OtherApiImpl;
import com.prettyyes.user.app.base.BaseApplication;
import com.prettyyes.user.app.view.wheeladapter.AbstractWheelTextAdapter;
import com.prettyyes.user.app.view.wheelviews.OnWheelChangedListener;
import com.prettyyes.user.app.view.wheelviews.OnWheelScrollListener;
import com.prettyyes.user.app.view.wheelviews.WheelView;
import com.prettyyes.user.model.other.ClientRegion;

import java.util.ArrayList;


/**
 * 更改封面对话框
 *
 * @author ywl
 */
public class ChangeAddressDialog extends Dialog implements View.OnClickListener {

    private WheelView wvProvince;
    private WheelView wvCitys;
    private WheelView wvArea;
    private View lyChangeAddress;
    private View lyChangeAddressChild;
    private TextView btnSure;
    private TextView btnCancel;

    private Context context;

    private ArrayList<ClientRegion.ListEntity> arrProvincesNew = new ArrayList<ClientRegion.ListEntity>();
    private ArrayList<ClientRegion.ListEntity> arrCitysNew = new ArrayList<ClientRegion.ListEntity>();
    private ArrayList<ClientRegion.ListEntity> arrAreasNew = new ArrayList<ClientRegion.ListEntity>();
    private AddressTextAdapter provinceAdapter;
    private AddressTextAdapter cityAdapter;
    private AddressTextAdapter areaAdapter;

    private String strProvince = "";
    private int idProvince = 1;
    private String strCity = "";
    private int idCity = 1;
    private String strArea = "";
    private int idArea = 1;
    private OnAddressCListener onAddressCListener;

    private ClientRegion.ListEntity provinceEntity;
    private ClientRegion.ListEntity cityEntity;
    private ClientRegion.ListEntity areaEntity;


    private int maxsize = 20;
    private int minsize = 14;

    public ChangeAddressDialog(Context context) {
        super(context, R.style.ShareDialog);
        this.context = context;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_myinfo_changeaddress);

        wvProvince = (WheelView) findViewById(R.id.wv_address_province);
        wvCitys = (WheelView) findViewById(R.id.wv_address_city);
        wvArea = (WheelView) findViewById(R.id.wv_address_area);
        lyChangeAddress = findViewById(R.id.ly_myinfo_changeaddress);
        lyChangeAddressChild = findViewById(R.id.ly_myinfo_changeaddress_child);
        btnSure = (TextView) findViewById(R.id.btn_myinfo_sure);
        btnCancel = (TextView) findViewById(R.id.btn_myinfo_cancel);

        lyChangeAddress.setOnClickListener(this);
        lyChangeAddressChild.setOnClickListener(this);
        btnSure.setOnClickListener(this);
        btnCancel.setOnClickListener(this);

        initProvinces();
        wvProvince.addChangingListener(new OnWheelChangedListener() {

            @Override
            public void onChanged(WheelView wheel, int oldValue, int newValue) {
                if (wheel.getCurrentItem() > provinceAdapter.getItemsCount() - 1) {
                    return;
                }

                ClientRegion.ListEntity p = provinceAdapter.getItem(wheel.getCurrentItem());
                strProvince = p.getName();
                idProvince = p.getRid();
                setTextviewSize(strProvince, provinceAdapter);
            }
        });

        wvProvince.addScrollingListener(new OnWheelScrollListener() {

            @Override
            public void onScrollingStarted(WheelView wheel) {
            }

            @Override
            public void onScrollingFinished(WheelView wheel) {
                ClientRegion.ListEntity p = provinceAdapter.getItem(wheel.getCurrentItem());
                strProvince = p.getName();
                idProvince = p.getRid();
                setTextviewSize(strProvince, provinceAdapter);
                initCity(idProvince);
            }
        });

        wvCitys.addChangingListener(new OnWheelChangedListener() {

            @Override
            public void onChanged(WheelView wheel, int oldValue, int newValue) {
                if (wheel.getCurrentItem() > cityAdapter.getItemsCount() - 1) {
                    return;
                }

                ClientRegion.ListEntity c = cityAdapter.getItem(wheel.getCurrentItem());
                strCity = c.getName();
                idCity = c.getRid();
                setTextviewSize(strCity, cityAdapter);
            }
        });

        wvCitys.addScrollingListener(new OnWheelScrollListener() {

            @Override
            public void onScrollingStarted(WheelView wheel) {
            }

            @Override
            public void onScrollingFinished(WheelView wheel) {
                ClientRegion.ListEntity c = cityAdapter.getItem(wheel.getCurrentItem());
                strCity = c.getName();
                idCity = c.getRid();
                setTextviewSize(strCity, cityAdapter);
                initAreas(idCity);

            }
        });


        wvArea.addChangingListener(new OnWheelChangedListener() {

            @Override
            public void onChanged(WheelView wheel, int oldValue, int newValue) {
                if (wheel.getCurrentItem() > areaAdapter.getItemsCount() - 1) {
                    return;
                }
                ClientRegion.ListEntity a = areaAdapter.getItem(wheel.getCurrentItem());
                strArea = a.getName();
                idArea = a.getRid();
                setTextviewSize(strArea, areaAdapter);
            }
        });
        wvArea.addScrollingListener(new OnWheelScrollListener() {
            @Override
            public void onScrollingStarted(WheelView wheel) {

            }

            @Override
            public void onScrollingFinished(WheelView wheel) {
                ClientRegion.ListEntity a = areaAdapter.getItem(wheel.getCurrentItem());
                strArea = a.getName();
                idArea = a.getRid();
                setTextviewSize(strArea, areaAdapter);

            }
        });
    }


    private class AddressTextAdapter extends AbstractWheelTextAdapter {
        ArrayList<ClientRegion.ListEntity> list;

        protected AddressTextAdapter(Context context, ArrayList list, int currentItem, int maxsize, int minsize) {
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
            return list.get(index).getName();
        }

        public ClientRegion.ListEntity getItem(int index) {
            return list.get(index);
        }
    }

    /**
     * 设置字体大小
     *
     * @param curriteItemText
     * @param adapter
     */
    public void setTextviewSize(String curriteItemText, AddressTextAdapter adapter) {
        ArrayList<View> arrayList = adapter.getTestViews();
        int size = arrayList.size();
        String currentText;
        for (int i = 0; i < size; i++) {
            TextView textvew = (TextView) arrayList.get(i);
            currentText = textvew.getText().toString();
            if (curriteItemText.equals(currentText)) {
                textvew.setTextSize(maxsize);
            } else {
                textvew.setTextSize(minsize);
            }
        }
    }

    public void setAddresskListener(OnAddressCListener onAddressCListener) {
        this.onAddressCListener = onAddressCListener;
    }

    @Override
    public void onClick(View v) {
        // TODO Auto-generated method stub
        if (v == btnSure) {
            if (onAddressCListener != null) {
                onAddressCListener.onClick(strProvince, strCity, strArea, idProvince, idCity, idArea);
            }
        } else if (v == btnCancel) {

        } else if (v == lyChangeAddressChild) {
            return;
        } else {
            dismiss();
        }
        dismiss();
    }

    /**
     * 回调接口
     *
     * @author Administrator
     */
    public interface OnAddressCListener {
        public void onClick(String province, String city, String area, int provinceId, int cityId, int areaId);
        // public void onBackId(int provinceId, int cityId, int areaId);
    }


    /**
     * 初始化省会
     */
    public void initProvinces() {

        new OtherApiImpl().clientGetRegion(BaseApplication.UUID, 1, 1, new NetReqCallback<ClientRegion>() {
            @Override
            public void apiRequestFail(String message, String method) {

            }

            @Override
            public void apiRequestSuccess(ClientRegion clientRegion, String method) {
                for (int i = 0; i < clientRegion.getList().size(); i++) {

                    arrProvincesNew.add(clientRegion.getList().get(i));
                }
                provinceAdapter = new AddressTextAdapter(context, arrProvincesNew, 0, maxsize, minsize);
                wvProvince.setVisibleItems(5);
                wvProvince.setViewAdapter(provinceAdapter);
                wvProvince.setCurrentItem(0);
                idProvince = arrProvincesNew.get(0).getRid();
                strProvince = arrProvincesNew.get(0).getName();
                initCity(arrProvincesNew.get(0).getRid());

            }
        });
    }

    public void initCity(int pid) {
        new OtherApiImpl().clientGetRegion(BaseApplication.UUID, pid, 2, new NetReqCallback<ClientRegion>() {
            @Override
            public void apiRequestFail(String message, String method) {
            }

            @Override
            public void apiRequestSuccess(ClientRegion clientRegion, String method) {
                arrCitysNew.clear();
                for (int i = 0; i < clientRegion.getList().size(); i++) {

                    arrCitysNew.add(clientRegion.getList().get(i));
                }
                cityAdapter = new AddressTextAdapter(context, arrCitysNew, 0, maxsize, minsize);
                wvCitys.setVisibleItems(5);
                wvCitys.setViewAdapter(cityAdapter);
                wvCitys.setCurrentItem(0);

                idCity = arrCitysNew.get(0).getRid();
                strCity = arrCitysNew.get(0).getName();
                initAreas(arrCitysNew.get(0).getRid());

            }
        });
    }

    public void initAreas(int pid) {
        new OtherApiImpl().clientGetRegion(BaseApplication.UUID, pid, 3, new NetReqCallback<ClientRegion>() {
            @Override
            public void apiRequestFail(String message, String method) {

            }

            @Override
            public void apiRequestSuccess(ClientRegion clientRegion, String method) {
                arrAreasNew.clear();
                for (int i = 0; i < clientRegion.getList().size(); i++) {

                    arrAreasNew.add(clientRegion.getList().get(i));
                }
                areaAdapter = new AddressTextAdapter(context, arrAreasNew, 0, maxsize, minsize);
                wvArea.setVisibleItems(5);
                wvArea.setViewAdapter(areaAdapter);
                wvArea.setCurrentItem(0);
                idArea = arrAreasNew.get(0).getRid();
                strArea = arrAreasNew.get(0).getName();
            }
        });

    }


}