package com.prettyyes.user.app.mvpPresenter;

import android.content.Intent;
import android.widget.ImageView;

import com.bigkoo.alertview.AlertView;
import com.bigkoo.alertview.OnItemClickListener;
import com.luck.picture.lib.config.PictureMimeType;
import com.luck.picture.lib.entity.LocalMedia;
import com.prettyyes.user.R;
import com.prettyyes.user.api.NetReqCallback;
import com.prettyyes.user.api.netXutils.ApiImpls.UserApiImpl;
import com.prettyyes.user.api.netXutils.GsonHelper;
import com.prettyyes.user.api.netXutils.HttpAccess;
import com.prettyyes.user.api.netXutils.HttpUploadManager;
import com.prettyyes.user.api.netXutils.ProgressCallback;
import com.prettyyes.user.api.netXutils.requests.UserEditRequest;
import com.prettyyes.user.app.account.AccountDataRepo;
import com.prettyyes.user.app.base.BaseApplication;
import com.prettyyes.user.app.mvpView.PersonInfoView;
import com.prettyyes.user.app.ui.person.InputTxtActivity;
import com.prettyyes.user.app.view.TopShowViewPorxy;
import com.prettyyes.user.app.view.app.SettingItemView;
import com.prettyyes.user.core.AppUploadListener;
import com.prettyyes.user.core.SelectMediaHandler;
import com.prettyyes.user.core.containter.JumpPageManager;
import com.prettyyes.user.core.event.TaskCompleteEvent;
import com.prettyyes.user.core.utils.AppRxBus;
import com.prettyyes.user.core.utils.ImageLoadUtils;
import com.prettyyes.user.model.AlertMessageResponse;
import com.prettyyes.user.model.UploadImgModel;
import com.prettyyes.user.model.user.UserInfo;

import java.util.ArrayList;
import java.util.List;

import static com.prettyyes.user.app.account.Constant.TYPE_PAPER;

/**
 * Project Name: user
 * Package Name: com.prettyyes.user.app.mvpPresenter
 * Author: SmileChen
 * Created on: 2016/10/30
 * Description: Nothing
 */
public class PersonPresenter {
    private final SelectMediaHandler selectMediaHandler;
    private final TopShowViewPorxy topShowViewPorxy;
    private PersonInfoView personInfoView;
    private String path;

    public PersonPresenter(PersonInfoView personInfoView) {
        this.personInfoView = personInfoView;

        selectMediaHandler = SelectMediaHandler.create(personInfoView.getThis()).setMax(1).setChooseMode(PictureMimeType.ofImage());

        topShowViewPorxy = new TopShowViewPorxy(personInfoView.getCoordinatorLayout());
    }

    public void getPersonInfo() {

        new UserApiImpl().userInfo(BaseApplication.UUID, "", new NetReqCallback<com.prettyyes.user.model.user.UserInfo>() {
            @Override
            public void apiRequestFail(String message, String method) {
                personInfoView.getThis().loadFail();
            }

            @Override
            public void apiRequestSuccess(com.prettyyes.user.model.user.UserInfo userInfo, String method) {
                personInfoView.getThis().loadSuccess();

                setData(userInfo);
            }
        });


//        setData(AccountDataRepo.appacountinstance.getAccount());
    }

    private void uploadHeadImg(final String path) {
        new UserApiImpl().userUploadImg(personInfoView.getThis().getUUId(), "headimg···", path, new ProgressCallback() {
            @Override
            public void onFail(String paramString1, String paramString2) {
                personInfoView.showFailedError(paramString1);
            }

            @Override
            public void onFinish() {
            }

            @Override
            public void onLoading(long total, long current, boolean isDownloading) {

            }

            @Override
            public void onStart() {
            }

            @Override
            public void onSuccess(String result) {

                personInfoView.showFailedError("上传成功");
                try {
                    UploadImgModel uploadImgModel = GsonHelper.getGson().fromJson(result, UploadImgModel.class);
                    UserInfo userInfo = AccountDataRepo.getAccountManager().getAccount();
                    userInfo.setHeadimg(uploadImgModel.getExtra().getPic_url());

                    personInfoView.setHeadImg(uploadImgModel.getExtra().getPic_url());

                    uploadPersonInfo(userInfo);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

        });

    }

    public void setTagByAccount() {
        ArrayList<String> a = new ArrayList();
        for (int i = 0; i < personInfoView.getThis().getAccount().getTags_info().size(); i++) {
            a.add(personInfoView.getThis().getAccount().getTags_info().get(i).getTag_name());
        }
        personInfoView.setTag(a);

    }

    public void selectImg() {
        if (selectMediaHandler != null) {
            selectMediaHandler.config(null).enableCrop(true).rotateEnabled(true).scaleEnabled(true).circleDimmedLayer(true); // 裁剪是否可旋转图片
            selectMediaHandler.start();
        }
    }

    public void goChangeNameActivity() {

        JumpPageManager.getManager().goInputTxtActivity(personInfoView.getThis(),InputTxtActivity.CodeName,personInfoView.getName());
    }

    public void goChangeAceActivity() {

        JumpPageManager.getManager().goInputTxtActivity(personInfoView.getThis(),InputTxtActivity.CodeAce,personInfoView.getAce());

    }

    public void goChangeInformationActivity() {
        JumpPageManager.getManager().goInputTxtActivity(personInfoView.getThis(),InputTxtActivity.CodeInfo,personInfoView.getInfo());
    }

    public AlertView selectSex(final SettingItemView settingItemView) {
        AlertView alertView = new AlertView("选择性别", null, "取消", null,
                new String[]{"男", "女"}, personInfoView.getThis(), AlertView.Style.ActionSheet, new OnItemClickListener() {
            @Override
            public void onItemClick(Object o, int position) {
                String sex = position == 0 ? "男" : "女";
                settingItemView.setLeftText(sex);
                UserInfo userInfo = personInfoView.getThis().getAccount();
                userInfo.setGender(position + 1);
                uploadPersonInfo(userInfo);


            }
        });
        alertView.setCancelable(true).show();
        return alertView;
    }

    /**
     * 上传个人信息
     *
     * @param account
     */
    private void uploadPersonInfo(final UserInfo account) {

        new UserEditRequest().setGender(account.getGender() + "").setHeadimg(account.getHeadimg()).post(new NetReqCallback<AlertMessageResponse>() {
            @Override
            public void apiRequestFail(String message, String method) {

            }

            @Override
            public void apiRequestSuccess(AlertMessageResponse o, String method) {
                if (o.isCompleteNewBie())
                    AppRxBus.getInstance().post(new TaskCompleteEvent());

                UserInfo userInfo = new com.prettyyes.user.model.user.UserInfo();
                userInfo.setHeadimg(account.getHeadimg());
                AccountDataRepo.getAccountManager().remove();
                AccountDataRepo.getAccountManager().save(account);
                AppRxBus.getInstance().post(userInfo);


            }
        });

    }

    private void setData(UserInfo userInfo) {
        personInfoView.setName(userInfo.getNickname());
        personInfoView.setHeadImg(userInfo.getHeadimg());
        personInfoView.setSex(userInfo.getGender() == 1 ? "男" : "女");
        personInfoView.setAce(userInfo.ace_txt);
        personInfoView.setInfo(userInfo.getInformation());
        ArrayList<String> a = new ArrayList();
        for (int i = 0; i < userInfo.getTags_info().size(); i++) {
            a.add(userInfo.getTags_info().get(i).getTag_name());
        }
        personInfoView.setTag(a);
    }

    public void setHeadimg(ImageView view, String url) {
        ImageLoadUtils.loadHeadImg(personInfoView.getThis(),url, view);
    }


    public void onActivityResultt(int requestCode, int resultCode, Intent data) {

        List<LocalMedia> selectList = selectMediaHandler.onActivityResult(requestCode, resultCode, data);
        if (selectList.size() > 0) {
            personInfoView.getThis().showProgressDialog(R.string.upload_ing);
            path = selectList.get(0).getCutPath();
            new HttpUploadManager().startUpload(path, TYPE_PAPER, new AppUploadListener() {
                @Override
                public void onProgress(long currentBytesCount, long totalBytesCount) {

                }

                @Override
                public void onNext(Object resulte, String url) {
                    personInfoView.getThis().dismissProgressDialog();

                    topShowViewPorxy.setProgressImageText(1, 1);
                    UserInfo userInfo = AccountDataRepo.getAccountManager().getAccount();
                    userInfo.setHeadimg(url);
                    personInfoView.setHeadImg(url);
                    uploadPersonInfo(userInfo);
                }

                @Override
                public void onError(String erroe) {
                    personInfoView.getThis().dismissProgressDialog();
                }
            });
        }


    }

    public void release() {

        if (topShowViewPorxy != null)
            topShowViewPorxy.release();
        HttpAccess.getInstance().cancelUpLoad(path);
    }


}
