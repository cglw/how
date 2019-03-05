package com.prettyyes.user.app.ui.template;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.support.v4.content.ContextCompat;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.HorizontalScrollView;
import android.widget.ImageButton;

import com.luck.picture.lib.config.PictureMimeType;
import com.luck.picture.lib.entity.LocalMedia;
import com.prettyyes.user.R;
import com.prettyyes.user.api.NetReqCallback;
import com.prettyyes.user.api.netXutils.HttpAccess;
import com.prettyyes.user.api.netXutils.HttpUploadManager;
import com.prettyyes.user.api.netXutils.requests.PaperUpdateRequest;
import com.prettyyes.user.api.netXutils.requests.SpuDetailRequest;
import com.prettyyes.user.api.netXutils.response.AddTempleteRes;
import com.prettyyes.user.app.base.BaseActivity;
import com.prettyyes.user.app.ui.appview.EditUrlDialog;
import com.prettyyes.user.app.view.TopShowViewPorxy;
import com.prettyyes.user.core.AppUploadListener;
import com.prettyyes.user.core.DialogHelper;
import com.prettyyes.user.core.SelectMediaHandler;
import com.prettyyes.user.core.containter.IntentParams;
import com.prettyyes.user.core.containter.JumpPageManager;
import com.prettyyes.user.core.containter.ZBundleCore;
import com.prettyyes.user.core.event.AddTemplateOrSelectSuccessEvent;
import com.prettyyes.user.core.event.TaskCompleteEvent;
import com.prettyyes.user.core.utils.AppRxBus;
import com.prettyyes.user.core.utils.AppUtil;
import com.prettyyes.user.core.utils.RxCallback;
import com.prettyyes.user.core.utils.SoftKeyboardUtil;
import com.prettyyes.user.model.v8.SpuInfoEntity;

import java.util.List;

import jp.wasabeef.richeditor.RichEditor;

import static com.prettyyes.user.app.account.Constant.TYPE_PAPER;
import static com.prettyyes.user.core.utils.StringUtils.getImageSrc;
import static com.prettyyes.user.core.utils.StringUtils.getImageUrl;

public class AddPaperActivity extends BaseActivity implements View.OnClickListener {

    private String module_id;
    private SpuDetailRequest spuDetailRequest;
    private SpuInfoEntity spuInfoEntity;

    private SelectMediaHandler selectMediaHandler;
    private String path;

    @Override
    public void needLoading(boolean need) {
        super.needLoading(true);
    }

    @Override
    public void setSoftModel() {
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE | WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
    }


    private EditText edit_title;
    private View view;
    private RichEditor mEditor;
    private HorizontalScrollView horizontalScrollView;
    private ImageButton ibn_image;
    private ImageButton ibn_bold;
    private ImageButton ibn_buttle;
    private ImageButton ibn_link;
    private ImageButton ibn_seperator;
    private ImageButton ibn_undo;
    private ImageButton ibn_redo;
    private View view_root;

    // End Of Content View Elements

    private void bindViews() {

        edit_title = (EditText) findViewById(R.id.edit_title);
        view = (View) findViewById(R.id.view);
        mEditor = (RichEditor) findViewById(R.id.editor);
        horizontalScrollView = (HorizontalScrollView) findViewById(R.id.horizontalScrollView);
        ibn_image = (ImageButton) findViewById(R.id.ibn_image);
        ibn_bold = (ImageButton) findViewById(R.id.ibn_bold);
        ibn_buttle = (ImageButton) findViewById(R.id.ibn_buttle);
        ibn_link = (ImageButton) findViewById(R.id.ibn_link);
        ibn_seperator = (ImageButton) findViewById(R.id.ibn_seperator);
        ibn_undo = (ImageButton) findViewById(R.id.ibn_undo);
        ibn_redo = (ImageButton) findViewById(R.id.ibn_redo);
        view_root = findViewById(R.id.view_root);
    }


    @Override
    protected void initVariables() {
        super.initVariables();
        IntentParams intentParmas = JumpPageManager.getManager().getIntentParmas(this);
        if (intentParmas != null) {
            module_id = intentParmas.getModule_id();
        }
        spuInfoEntity = new SpuInfoEntity();
        spuInfoEntity.setSpu_type(TYPE_PAPER);
        selectMediaHandler = SelectMediaHandler.create(this).setMax(1).setChooseMode(PictureMimeType.ofImage());


    }



    @Override
    protected int bindLayout() {
        return R.layout.activity_add_paper;
    }

    public String getPaperTitle() {
        return edit_title.getText().toString();
    }

    @Override
    protected void setListener() {
        super.setListener();
        mSubscription = AppRxBus.getInstance().subscribeEvent(new RxCallback<AddTemplateOrSelectSuccessEvent>() {
            @Override
            protected void back(AddTemplateOrSelectSuccessEvent obj) {
                if (AddPaperActivity.this != null)
                    AddPaperActivity.this.finish();
            }
        });
        mEditor.setOnTextChangeListener(new RichEditor.OnTextChangeListener() {
            @Override
            public void onTextChange(String text) {
                preview_txt = text;
                if (can_upload_img)
                    mEditor.setBold(bold);

            }
        });
        mEditor.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {

                switch (event.getAction()) {


                    case MotionEvent.ACTION_DOWN:
                    case MotionEvent.ACTION_UP:
                        if (!v.hasFocus()) {
                            v.requestFocus();
                        }
                        if (!can_upload_img)
                            mEditor.setBold(bold);

                        can_upload_img = true;
                        break;
                }
                return false;
            }
        });

        edit_title.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                    case MotionEvent.ACTION_UP:
                        if (!v.hasFocus()) {
                            v.requestFocus();
                            can_upload_img = false;
                        }
                        break;
                }
                return false;
            }
        });
    }

    public boolean can_upload_img = false;
    String[] items = new String[]{"立即发布", "容我预览"};


    @Override
    protected void initViews() {
        setActivtytitle("创作好文");

        setRightTvListener("发布", new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                DialogHelper.getInstance().getDialogListNoCancel(items, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if (which == 0) {
                            postData();
                        } else if (which == 1) {
                            JumpPageManager.getManager().goPaperPreActivity(getThis(), module_id, getPaperTitle(), preview_txt);

                        }

                    }
                }).show();
            }
        });
        bindViews();
        ibn_image.setOnClickListener(this);
        ibn_bold.setOnClickListener(this);
        ibn_buttle.setOnClickListener(this);
        ibn_link.setOnClickListener(this);
        ibn_seperator.setOnClickListener(this);
        ibn_undo.setOnClickListener(this);
        ibn_redo.setOnClickListener(this);


        mEditor.setEditorHeight(200);
        mEditor.setEditorFontSize(15);
        mEditor.setEditorFontColor(Color.BLACK);
        mEditor.setEditorBackgroundColor(ContextCompat.getColor(this, R.color.white));
        //mEditor.setBackgroundColor(Color.BLUE);
        //mEditor.setBackgroundResource(R.drawable.bg);
//        mEditor.setPadding(10, 10, 10, 10);
//        mEditor.setFocusable(true);
//        mEditor.setFocusableInTouchMode(true);
//        mEditor.requestFocus();
        mEditor.setPlaceholder("从功能、外在、能解决的问题等维度详细介绍这个好物，书写时请在心中默念“为什么一定要买它”");


        //mEditor.setBackground("https://raw.githubusercontent.com/wasabeef/art/master/chip.jpg");

        topShowViewPorxy = new TopShowViewPorxy(view_root);

    }


    private String preview_txt;

    @Override
    protected void loadData() {
        spuDetailRequest = new SpuDetailRequest();
        spuDetailRequest.setModule_id(module_id).setSpu_type(TYPE_PAPER).post(new NetReqCallback<SpuInfoEntity>() {
            @Override
            public void apiRequestFail(String message, String method) {
                loadFail();
            }

            @Override
            public void apiRequestSuccess(SpuInfoEntity spuInfoEntity, String method) {
                loadSuccess();
                if (spuInfoEntity != null) {
                    setData(spuInfoEntity.getSpu_name(), spuInfoEntity.getSpu_desc());
                    AddPaperActivity.this.spuInfoEntity = spuInfoEntity;
                }
            }
        });
    }


    public void setData(String title, String html) {
        edit_title.setText(title);
        if (title != null)
            edit_title.setSelection(edit_title.getText().toString().length());

        mEditor.setHtml(html);
        preview_txt = html;
    }

    private boolean bold = false;
    private boolean buttle = false;

    @Override
    public void onClick(View v) {
        if (!can_upload_img) {
            AppUtil.showToastShort("你需要处于编辑内容区域");
            return;
        }
        switch (v.getId()) {

            case R.id.ibn_image:
                SoftKeyboardUtil.closeInputMethod(getThis(), mEditor);
                selectMediaHandler.config(null);
                selectMediaHandler.start();
                break;
            case R.id.ibn_bold:

                bold = !bold;
                if (bold) {
                    mEditor.setBold(true);
                    ibn_bold.setImageResource(R.mipmap.how_richeditor_bold);
                } else {
                    mEditor.setBold(false);
                    ibn_bold.setImageResource(R.mipmap.how_richeditor_unbold);
                }

                break;
            case R.id.ibn_buttle:
                mEditor.setBullets();
                buttle = !buttle;
                if (buttle)
                    ibn_buttle.setImageResource(R.mipmap.bullet_highlighted);
                else
                    ibn_buttle.setImageResource(R.mipmap.bullet);


                break;
            case R.id.ibn_link:
                new EditUrlDialog(this, new EditUrlDialog.DialogCallback() {
                    @Override
                    public void confirm(String name, String url) {
                        mEditor.insertLink(url, name);
                    }
                }).show();

                break;
            case R.id.ibn_seperator:
                mEditor.insertHr();

                break;
            case R.id.ibn_undo:
                mEditor.undo();

                break;
            case R.id.ibn_redo:
                mEditor.redo();
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        List<LocalMedia> selectList = selectMediaHandler.onActivityResult(requestCode, resultCode, data);
        if (selectList.size() > 0) {
            showProgressDialog(R.string.upload_ing);
            path = selectList.get(0).getPath();
            new HttpUploadManager().startUpload(path, TYPE_PAPER, new AppUploadListener() {
                @Override
                public void onProgress(long currentBytesCount, long totalBytesCount) {

                }

                @Override
                public void onNext(Object resulte, String url) {
                    mEditor.insertImage(url,
                            "how");
                    dismissProgressDialog();
                    topShowViewPorxy.setProgressImageText(1, 1);
                }

                @Override
                public void onError(String erroe) {
                    dismissProgressDialog();
                }
            });
        }


    }

    TopShowViewPorxy topShowViewPorxy;

    @Override
    public void onBackPressed() {
        closePage();
    }

    @Override
    public void back() {
        closePage();
    }

    private void closePage() {

        if (module_id != null && module_id.length() > 0) {
            DialogHelper.getInstance().getDialogNoCancel(getString(R.string.template_edit_giveup), new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    finish();
                }
            }).setNegativeButton(getString(R.string.cancel), new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {

                }
            }).setCancelable(true).show();
        } else {
            if (checkNeedSave()) {
                DialogHelper.getInstance().getDialogNoCancel(getString(R.string.template_need_save), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        getSpuInfoEntity().setModule_id(module_id);
                        getSpuInfoEntity().setSpu_name(edit_title.getText().toString());
                        getSpuInfoEntity().setSpu_desc(preview_txt);
                        getSpuInfoEntity().setSpu_type(TYPE_PAPER);
                        spuDetailRequest.saveData(getSpuInfoEntity());
                        finish();
                    }
                }).setNegativeButton(getString(R.string.cancel), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                    }
                }).setCancelable(true).show();


            } else {
                finish();
            }
        }

    }

    public SpuInfoEntity getSpuInfoEntity() {
        if (spuInfoEntity == null)
            return new SpuInfoEntity();
        return spuInfoEntity;
    }

    public boolean checkNeedSave() {
        if (edit_title.getText().toString().length() > 0)
            return true;
        if (mEditor.getHtml() != null && mEditor.getHtml().length() > 0)
            return true;
        return false;
    }


    private void postData() {
        showProgressDialog("正在上传...");
        PaperUpdateRequest paperUpdateRequest = new PaperUpdateRequest();
        paperUpdateRequest.setModule_id(module_id);
        paperUpdateRequest.setSpu_name(edit_title.getText().toString());
        paperUpdateRequest.setSpu_desc(preview_txt);

        List<String> imageUrl = getImageUrl(preview_txt);
        List<String> imageSrc = getImageSrc(imageUrl);
        if (imageSrc != null && imageSrc.size() > 0) {
            paperUpdateRequest.setMain_img(imageSrc.get(0));
        }
        paperUpdateRequest.post(new NetReqCallback<AddTempleteRes>() {
            @Override
            public void apiRequestFail(String message, String method) {
                showToastShort(message);
                dismissProgressDialog();

            }

            @Override
            public void apiRequestSuccess(AddTempleteRes addTempleteRes, String method) {
                if (addTempleteRes.isCompletetNewBie())
                    AppRxBus.getInstance().post(new TaskCompleteEvent());

                AppRxBus.getInstance().post(new AddTemplateOrSelectSuccessEvent(addTempleteRes.getModule_id(), addTempleteRes.getModule_type()));
                if (ZBundleCore.getInstance().isExistKolReplay()) {
                    JumpPageManager.getManager().goKolReplyActivity(getThis());

                }
                dismissProgressDialog();

                finish();
            }
        });
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        topShowViewPorxy.release();
        HttpAccess.getInstance().cancelUpLoad(path);
    }
}
