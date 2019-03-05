package com.prettyyes.user.core.utils;

import android.graphics.Typeface;
import android.support.v4.content.ContextCompat;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;
import android.text.style.StyleSpan;
import android.widget.TextView;

import com.prettyyes.user.R;
import com.prettyyes.user.app.base.BaseApplication;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Project Name: user
 * Package Name: com.prettyyes.user.core.utils
 * Author: SmileChen
 * Created on: 2016/8/11
 * Description: Nothing
 */
public class StringUtils {

    /**
     * 解析出url请求的路径，包括页面
     *
     * @param strURL url地址
     * @return url路径
     */
    public static String UrlPage(String strURL) {
        String strPage = null;
        String[] arrSplit = null;

        strURL = strURL.trim().toLowerCase();

        arrSplit = strURL.split("[?]");
        if (strURL.length() > 0) {
            if (arrSplit.length > 1) {
                if (arrSplit[0] != null) {
                    strPage = arrSplit[0];
                }
            }
        }

        return strPage;
    }

    /**
     * 去掉url中的路径，留下请求参数部分
     *
     * @param strURL url地址
     * @return url请求参数部分
     */
    private static String TruncateUrlPage(String strURL) {
        String strAllParam = null;
        String[] arrSplit = null;

        strURL = strURL.trim().toLowerCase();

        arrSplit = strURL.split("[?]");
        if (strURL.length() > 1) {
            if (arrSplit.length > 1) {
                if (arrSplit[1] != null) {
                    strAllParam = arrSplit[1];
                }
            }
        }

        return strAllParam;
    }

    /**
     * 解析出url参数中的键值对
     * 如 "index.jsp?Action=del&id=123"，解析出Action:del,id:123存入map中
     *
     * @param URL url地址
     * @return url请求参数部分
     */
    public static Map<String, String> URLRequest(String URL) {
        Map<String, String> mapRequest = new HashMap<String, String>();

        String[] arrSplit = null;

        String strUrlParam = TruncateUrlPage(URL);
        if (strUrlParam == null) {
            return mapRequest;
        }
        //每个键值为一组 www.2cto.com
        arrSplit = strUrlParam.split("[&]");
        for (String strSplit : arrSplit) {
            String[] arrSplitEqual = null;
            arrSplitEqual = strSplit.split("[=]");

            //解析出键值
            if (arrSplitEqual.length > 1) {
                //正确解析
                mapRequest.put(arrSplitEqual[0], arrSplitEqual[1]);

            } else {
                if (arrSplitEqual[0] != "") {
                    //只有参数没有值，不加入
                    mapRequest.put(arrSplitEqual[0], "");
                }
            }
        }
        return mapRequest;
    }

    public static String getSplitImgs(ArrayList<String> images) {
        if (images == null)
            return "";
        String res = "";
        for (int i = 0; i < images.size(); i++) {
            res += images.get(i) + ";";
        }
        return res;
    }

    public static String[] convertStrToArray(String str) {
        String[] strArray = null;
        strArray = str.split(";"); //拆分字符为";" ,然后把结果交给数组strArray
        return strArray;
    }

    public static String BackStringToJson(Map<String, String> map) {
        StringBuilder sb = new StringBuilder();
        Set<String> keys = map.keySet();
        for (String key : keys) {
            sb.append(key + "=" + map.get(key).toString() + "\r\n");
        }
        LogUtil.i("TAG", "转化的");
        return sb.toString();
    }

    public static String changebugNumShow(int num) {
        if (num <= 9999)
            return num + "";
        else
            return num / 10000 + "万";
    }

    public static boolean strIsEmpty(String s) {
        if (s == null || s.trim().length() <= 0 || s.equals("null")) {
            return true;
        }
        return false;
    }

    //手机号码正则
    public static final String PHONE_REG = "^(0|86|17951)?(13[0-9]|15[012356789]|17[0678]|18[0-9]|14[57])[0-9]{8}$";

    public static String getPhone(String s) {
        Pattern p = Pattern.compile("^(0|86|17951)?(13[0-9]|15[012356789]|17[0678]|18[0-9]|14[57])[0-9]{8}$");
        Matcher matcher = p.matcher(s);

        if (matcher.find()) {
            LogUtil.i("位置", matcher.start() + "--" + matcher.end());
            return matcher.group();

        }

        return "";
    }


    private static final String IMGURL_REG = "<img.*src=(.*?)[^>]*?>";
    private static final String IMGSRC_REG = "http(.*?)(\"|>|\\s+)";

    public static List<String> getImageUrl(String HTML) {
        List<String> listImgUrl = new ArrayList<String>();

        if (HTML == null)
            return listImgUrl;
        Matcher matcher = Pattern.compile(IMGURL_REG).matcher(HTML);
        while (matcher.find()) {
            listImgUrl.add(matcher.group());
        }
        return listImgUrl;
    }

    public static List<String> getImageSrc(List<String> listImageUrl) {
        List<String> listImgSrc = new ArrayList<String>();
        if (listImageUrl == null)
            return listImgSrc;
        for (String image : listImageUrl) {
            Matcher matcher = Pattern.compile(IMGSRC_REG).matcher(image);
            while (matcher.find()) {
                listImgSrc.add(matcher.group().substring(0, matcher.group().length() - 1));
            }
        }

        return listImgSrc;
    }

    public static String getPrice(String price) {
        if (price == null)
            return "";
        return String.format("¥ %s", price);
    }

    public static String getExpress_price(String express_price) {
        String res = "";
        if (express_price != null)
            res = express_price;
        return String.format("运费：¥ %s", res);
    }

    public static String addSplit(List<String> data, String tag) {
        String res = "";
        for (int i = 0; i < data.size(); i++) {
            if (i < data.size() - 1) {
                res += data.get(i) + tag;
            } else
                res += data.get(i);
        }
        return res;
    }

    public static List<String> getSplitArray(String imgs) {

        List<String> datas = new ArrayList<>();
        if (imgs == null || imgs.length() <= 0)
            return datas;
        if (!imgs.endsWith(";")) {
            imgs += ";";
        }
        String[] split = imgs.split(";");
        for (int i = 0; i < split.length; i++) {
            datas.add(split[i]);
        }
        return datas;
    }

    public static String removeEndString(String target) {
        if (target.endsWith(";") && target.length() > 0)

            return target.substring(0, target.length() - 1);
        return target;
    }

    public static String getAttrs(String attr_value_text) {
        String str = "";
        String input = "";
        if (attr_value_text != null)
            input = attr_value_text + ";";
        String[] split = input.split(";");

        for (int i = 0; i < split.length; i++) {
            int start = split[i].indexOf(":");
            str += split[i].substring(start + 1, split[i].length()) + "/";
        }

        if (str.endsWith("/"))
            str = str.substring(0, str.length() - 1);


        return str;
    }


    public static void addStyleSpan(TextView tv, String str) {
        SpannableString spanString = new SpannableString(str);
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) >= 48 && str.charAt(i) <= 57) {
                StyleSpan span = new StyleSpan(Typeface.BOLD_ITALIC);
                spanString.setSpan(span, i, i + 1, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);

            }
        }
        tv.setText(spanString);
    }

    public static SpannableString addColorSpan(String text, int start, int length, int color) {
        SpannableString spanString = new SpannableString(text);
        ForegroundColorSpan span = new ForegroundColorSpan(color);
        spanString.setSpan(span, start, start + length, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        return spanString;
    }

    public static SpannableString matcherSearchTitle(int color, String text, String keyword) {
        String string = text.toLowerCase();
        String key = keyword.toLowerCase();
        Pattern pattern = Pattern.compile(key);
        Matcher matcher = pattern.matcher(string);
        SpannableString ss = new SpannableString(text);
        while (matcher.find()) {
            int start = matcher.start();
            int end = matcher.end();
            ss.setSpan(new ForegroundColorSpan(color), start, end,
                    Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        }
        return ss;
    }

    //
    public static SpannableString matcherSearchTitle(String text, String keyword) {
        String string = text.toLowerCase();
        String key = keyword.toLowerCase();
        SpannableString ss = new SpannableString(text);
        try {
            Pattern pattern = Pattern.compile(key);
            Matcher matcher = pattern.matcher(string);

            while (matcher.find()) {
                int start = matcher.start();
                int end = matcher.end();
                ss.setSpan(new ForegroundColorSpan(ContextCompat.getColor(BaseApplication.getAppContext(), R.color.search_key_word)), start, end,
                        Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            }
        } catch (Exception ee) {
            return ss;
        }

        return ss;
    }

//    public static SpannableString matcherSearchTitle(String text, String specifiedTexts) {
//        List<Integer> sTextsStartList = new ArrayList<>();
//
//        int sTextLength = specifiedTexts.length();
//        String temp = text;
//        int lengthFront = 0;//记录被找出后前面的字段的长度
//        int start = -1;
////        do {
//            start = temp.indexOf(specifiedTexts);
//
//            if (start != -1) {
//                start = start + lengthFront;
//                sTextsStartList.add(start);
//                lengthFront = start + sTextLength;
//                temp = text.substring(lengthFront);
//            }
//
////        } while (start != -1);
//
//        SpannableString styledText = new SpannableString(text);
//        for (Integer i : sTextsStartList) {
//            styledText.setSpan(
//                    new ForegroundColorSpan(ContextCompat.getColor(BaseApplication.getAppContext(), R.color.search_key_word)),
//                    i,
//                    i + sTextLength,
//                    Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
//        }
//
//        return styledText;
//    }


    public static void addIndexStyleSpan(TextView tv, String total, int start, int length) {
        SpannableString spanString = new SpannableString(total);
        StyleSpan span = new StyleSpan(Typeface.BOLD_ITALIC);
        spanString.setSpan(span, start, start + length, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        tv.setText(spanString);

    }

    public static void addIndexStyleSpan(TextView tv, String total, int start, int length, int color) {
        SpannableString spanString = new SpannableString(total);
        StyleSpan span = new StyleSpan(Typeface.BOLD);
        spanString.setSpan(span, start, start + length, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        spanString.setSpan(new ForegroundColorSpan(color), start, start + length, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        tv.setText(spanString);

    }


    public static String getHeadImgByServer(String headimg) {
        if (headimg.startsWith("https://image.prettyyes.com") || headimg.startsWith("http://image.prettyyes.com")) {
            headimg = headimg + ImageLoadUtils.extral_head;
        }
        return headimg;
    }

    public static boolean isVideo(String name) {
        if (name.endsWith(".mp4") || name.endsWith(".avi") || name.endsWith(".3gpp") || name.endsWith(".3gp") || name.startsWith(".mov")) {
            return true;
        }
        return false;
    }



}
