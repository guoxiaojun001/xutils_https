package com.lidroid.xutils.sample.fragment;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.exception.DbException;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.ResponseStream;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest;
import com.lidroid.xutils.sample.DownloadListActivity;
import com.lidroid.xutils.sample.R;
import com.lidroid.xutils.sample.download.DownloadManager;
import com.lidroid.xutils.sample.download.DownloadService;
import com.lidroid.xutils.util.PreferencesCookieStore;
import com.lidroid.xutils.util.LogUtils;
import com.lidroid.xutils.view.ResType;
import com.lidroid.xutils.view.annotation.ResInject;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnClick;
import org.apache.http.impl.cookie.BasicClientCookie;

import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Author: wyouflf
 * Date: 13-9-14
 * Time: 下午3:35
 */
public class HttpSFragment extends Fragment {

    //private HttpHandler handler;

    private Context mAppContext;
    private DownloadManager downloadManager;
    private PreferencesCookieStore preferencesCookieStore;
    
    
    
    Button test_upload_btn ;
    TextView result_txt;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.https_fragment, container, false);
        ViewUtils.inject(this, view);
        
        
        return view;
    }
    
    //*****test https
    @ViewInject(R.id.https_get)
    private Button https_get;
    @ViewInject(R.id.https_post)
    private Button https_post;
    
    
    @OnClick(R.id.https_get)
    public void getHttps(View view) {
        RequestParams params = new RequestParams();
//        params.addQueryStringParameter("method", "mkdir");
//        params.addQueryStringParameter("access_token", "3.1042851f652496c9362b1cd77d4f849b.2592000.1377530363.3590808424-248414");
//        params.addBodyParameter("path", "/apps/测试应用/test文件夹");

        HttpUtils http = new HttpUtils();
        http.send(HttpRequest.HttpMethod.POST,
//                "https://ave.bolyartech.com:44401/https_test.html",
                "https://kyfw.12306.cn/otn/",
                
                params,
                new RequestCallBack<String>() {
                    @Override
                    public void onStart() {
                        resultText.setText("conn...");
                    }

                    @Override
                    public void onLoading(long total, long current, boolean isUploading) {
                        resultText.setText(current + "/" + total);
                    }

                    @Override
                    public void onSuccess(ResponseInfo<String> responseInfo) {
                        resultText.setText("upload response:" + responseInfo.result);
                    }

                    @Override
                    public void onFailure(HttpException error, String msg) {
                        resultText.setText(msg);
                    }
                });
    }
    
    @OnClick(R.id.https_post)
    public void postHttps(View view) {

        RequestParams params = new RequestParams();
//        params.addQueryStringParameter("method", "mkdir");
//        params.addQueryStringParameter("access_token", "3.1042851f652496c9362b1cd77d4f849b.2592000.1377530363.3590808424-248414");
//        params.addBodyParameter("path", "/apps/测试应用/test文件夹");

        HttpUtils http = new HttpUtils();
        http.send(HttpRequest.HttpMethod.GET,
//                "https://ave.bolyartech.com:44401/https_test.html", 
                "https://kyfw.12306.cn/otn/",
                params,
                new RequestCallBack<String>() {
                    @Override
                    public void onStart() {
                        resultText.setText("conn...");
                    }

                    @Override
                    public void onLoading(long total, long current, boolean isUploading) {
                        resultText.setText(current + "/" + total);
                    }

                    @Override
                    public void onSuccess(ResponseInfo<String> responseInfo) {
                        resultText.setText("upload response:" + responseInfo.result);
                    }

                    @Override
                    public void onFailure(HttpException error, String msg) {
                        resultText.setText(msg);
                    }
                });
    
    }
    

    @ViewInject(R.id.result_txt)
    private TextView resultText;

    @ResInject(id = R.string.download_label, type = ResType.String)
    private String label;


}
