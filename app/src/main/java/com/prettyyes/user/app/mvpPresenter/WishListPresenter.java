package com.prettyyes.user.app.mvpPresenter;

import com.prettyyes.user.api.NetReqCallback;
import com.prettyyes.user.api.netXutils.ApiImpls.OrderApiImpl;
import com.prettyyes.user.app.mvpView.WishListView;
import com.prettyyes.user.app.ui.RegisterActivity;
import com.prettyyes.user.core.order.OrderManager;
import com.prettyyes.user.model.order.WishListEntity;

import java.util.ArrayList;
import java.util.List;

/**
 * Project Name: user
 * Package Name: com.prettyyes.user.app.mvpPresenter
 * Author: SmileChen
 * Created on: 2016/10/26
 * Description: Nothing
 */
public class WishListPresenter {
    private WishListView wishListView;
    private OrderApiImpl orderApi;
    private WishListEntity wishListEntityData;

    public WishListPresenter(WishListView wishListView) {
        this.wishListView = wishListView;
        orderApi = new OrderApiImpl();
    }

    public void getWishlist(final String uuid) {
        if (uuid == null) {
            RegisterActivity.getRegister(wishListView.getThis());
            return;
        }
        orderApi.cartList(uuid,1, new NetReqCallback<WishListEntity>() {
            @Override
            public void apiRequestFail(String message,String method) {
                wishListView.showFailedError(message);
            }

            @Override
            public void apiRequestSuccess(WishListEntity wishListEntity,String method) {
                handlerData(wishListEntity);
            }
        });
    }

    private void handlerData(WishListEntity wishListEntity) {

        wishVpArray.clear();
        for (int i = 0; i < wishListEntity.getList().size(); i++) {
            WishVpEntity wishVpEntity = new WishVpEntity();
            wishVpEntity.setHeadimg(wishListEntity.getList().get(i).getSeller_headimg());
            wishVpEntity.setFamous_type(wishListEntity.getList().get(i).getFamous_type());
            wishVpEntity.setSeller_id(wishListEntity.getList().get(i).getSeller_id());
            wishVpArray.add(wishVpEntity);

            for (int k = 0; k < wishListEntity.getList().get(i).getSuit().size(); k++) {

                WishListEntity.ListEntity.SuitEntity s = wishListEntity.getList().get(i).getSuit().get(k);
                WishVpEntity.WishVpListEntity vplist = new WishVpEntity.WishVpListEntity();
                vplist.setType("title");
                vplist.setSuit_id(s.getSuit_id());
                vplist.setSuit_name(s.getSuit_name());
                wishVpEntity.getSuitlist().add(vplist);
                for (int l = 0; l < s.getSuit_unit().size(); l++) {
                    WishListEntity.ListEntity.SuitEntity.SuitUnitEntity uint = s.getSuit_unit().get(l);
                    WishVpEntity.WishVpListEntity vplistdata = new WishVpEntity.WishVpListEntity();
                    vplistdata.setType("");
                    vplistdata.setCart_id(uint.getCart_id());
                    vplistdata.setCart_status(uint.getCart_status());
                    vplistdata.setImg(uint.getImg());
                    vplistdata.setNum(uint.getNum());
                    vplistdata.setPrice(uint.getPrice());
                    vplistdata.setUnit_name(uint.getUnit_name());
                    vplistdata.setUnit_id(uint.getUnit_id());
                    wishVpEntity.getSuitlist().add(vplistdata);
                }

            }

            if (wishListEntity.getList().get(i).getTaobao().size() > 0) {
                WishVpEntity.WishVpListEntity vplist = new WishVpEntity.WishVpListEntity();
                vplist.setType("title");
                vplist.setSuit_name("淘寶套系");
                wishVpEntity.getSuitlist().add(vplist);
            }
            for (int j = 0; j < wishListEntity.getList().get(i).getTaobao().size(); j++) {
                WishListEntity.ListEntity.TaobaoEntity taobao = wishListEntity.getList().get(i).getTaobao().get(j);
                WishVpEntity.WishVpListEntity vplistdata = new WishVpEntity.WishVpListEntity();
                vplistdata.setType("");
                vplistdata.setCart_id(taobao.getCart_id());
                vplistdata.setCart_status(taobao.getCart_status());
                vplistdata.setImg(taobao.getMain_image());
                vplistdata.setNum(taobao.getNum());
                vplistdata.setPrice(taobao.getPrice());
                vplistdata.setUnit_name(taobao.getTitle());
                vplistdata.setUnit_id(taobao.getTaobao_id());
                wishVpEntity.getSuitlist().add(vplistdata);
            }
        }
        wishListView.setVpdata(wishVpArray);

    }


    public void cartedit(String cart_id, int num, int cart_staus) {
        new OrderApiImpl().cartEdit(wishListView.getThis().getUUId(), cart_id, num, cart_staus, new NetReqCallback() {
            @Override
            public void apiRequestFail(String message,String method) {
                wishListView.showFailedError(message);
            }

            @Override
            public void apiRequestSuccess(Object o,String method) {
            }
        });
    }

    private ArrayList<WishVpEntity> wishVpArray = new ArrayList<>();



    public void selectWishListGoods() {
        String cart_ids = "";
        for (int i = 0; i < wishListView.getAdapter().getCount(); i++) {
            for (int j = 0; j < wishListView.getAdapter().get(i).getSuitlist().size(); j++) {
                if (wishListView.getAdapter().get(i).getSuitlist().get(j).getCart_status() == 1)
                    cart_ids += wishListView.getAdapter().get(i).getSuitlist().get(j).getCart_id() + ";";
            }
        }

    }

    public void deleteWishListGoods() {
        String cart_ids = "";
        for (int i = 0; i < wishListView.getAdapter().getCount(); i++) {
            for (int j = 0; j < wishListView.getAdapter().get(i).getSuitlist().size(); j++) {
                if (wishListView.getAdapter().get(i).getSuitlist().get(j).getCart_status() == 1)
                    cart_ids += wishListView.getAdapter().get(i).getSuitlist().get(j).getCart_id() + ";";
            }
        }
        wishListView.showProgressDialog("正在提交请求");
        orderApi.cartdelgoods(wishListView.getThis().getUUId(), cart_ids, new NetReqCallback() {
            @Override
            public void apiRequestFail(String message,String method) {
                wishListView.dismisProgreDialog();
                wishListView.showFailedError(message);
            }

            @Override
            public void apiRequestSuccess(Object o,String method) {
                wishListView.dismisProgreDialog();

                wishListView.showFailedError("删除成功");
//                for (int i = wishListView.getAdapter().getCount() - 1; i >= 0; i--) {
//                    for (int j = wishListView.getAdapter().get(i).getSuitlist().size() - 1; j >= 0; j--) {
//                        if (wishListView.getAdapter().get(i).getSuitlist().get(j).getCart_status() == 1)
//                            wishListView.getAdapter().get(i).getSuitlist().remove(j);
//                    }
//                }
                getWishlist(wishListView.getThis().getUUId());
                OrderManager.getInstance().refreshWishListNum();


            }
        });
    }



    public static class WishVpEntity {
        private String headimg;
        private int seller_id;
        private int famous_type = 0;

        public int getFamous_type() {
            return famous_type;
        }

        public void setFamous_type(int famous_type) {
            this.famous_type = famous_type;
        }

        private List<WishVpListEntity> suitlist = new ArrayList<>();

        public int getSeller_id() {
            return seller_id;
        }

        public void setSeller_id(int seller_id) {
            this.seller_id = seller_id;
        }

        public static class WishVpListEntity {
            private String type;//"title"跟 普通的
            private String suit_name;
            private int suit_id;
            private int unit_id;
            private String unit_name;
            private String price;
            private String img;
            private int num;
            private int cart_id;
            private int cart_status;
            private boolean select = false;//是否被选中


            @Override
            public String toString() {
                return "WishVpListEntity{" +
                        "unit_name='" + unit_name + '\'' +
                        ", num=" + num +
                        ", cart_id=" + cart_id +
                        ", unit_id=" + unit_id +
                        ", select=" + select +
                        '}';
            }

            public String getSuit_name() {
                return suit_name;
            }

            public void setSuit_name(String suit_name) {
                this.suit_name = suit_name;
            }

            public String getImg() {

                return img;
            }

            public void setImg(String img) {
                this.img = img;
            }

            public String getType() {
                return type;
            }

            public void setType(String type) {
                this.type = type;
            }

            public int getSuit_id() {
                return suit_id;
            }

            public void setSuit_id(int suit_id) {
                this.suit_id = suit_id;
            }

            public int getUnit_id() {
                return unit_id;
            }

            public void setUnit_id(int unit_id) {
                this.unit_id = unit_id;
            }

            public String getUnit_name() {
                return unit_name;
            }

            public void setUnit_name(String unit_name) {
                this.unit_name = unit_name;
            }

            public String getPrice() {
                return price;
            }

            public void setPrice(String price) {
                this.price = price;
            }

            public int getNum() {
                return num;
            }

            public void setNum(int num) {
                this.num = num;
            }

            public int getCart_id() {
                return cart_id;
            }

            public void setCart_id(int cart_id) {
                this.cart_id = cart_id;
            }

            public int getCart_status() {
                return cart_status;
            }

            public void setCart_status(int cart_status) {
                this.cart_status = cart_status;
            }

            public boolean isSelect() {
                return select;
            }

            public void setSelect(boolean select) {
                this.select = select;
            }
        }

        public String getHeadimg() {
            return headimg;
        }

        public void setHeadimg(String headimg) {
            this.headimg = headimg;
        }

        public List<WishVpListEntity> getSuitlist() {
            return suitlist;
        }

        public void setSuitlist(List<WishVpListEntity> suitlist) {
            this.suitlist = suitlist;
        }
    }


}
