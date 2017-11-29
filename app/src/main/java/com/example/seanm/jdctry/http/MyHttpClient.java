package com.example.seanm.jdctry.http;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import java.util.HashMap;

/**
 * Created by SeanM on 2017/10/16.
 */

public class MyHttpClient {
    private static  int CONNECTTMEOUT=15*1000;
    private static AsyncHttpClient client;
    private MyHttpClient(){

    }
    public static AsyncHttpClient getHttpClient(){
        if (client==null){
            client=new AsyncHttpClient();
            client.setTimeout(CONNECTTMEOUT);
        }
        return client;
    }
    public void sendGet(String urlString, RequestParams params,
                        AsyncHttpResponseHandler res){
        client.get(urlString,params,res);
    }
    public void sendGet(String url,AsyncHttpResponseHandler responseHandler){
		client.get(url, responseHandler);
	}

	public void sendPost(String url, RequestParams params, HashMap<String, String> vaules, AsyncHttpResponseHandler responseHandler){

		client.post(url, params, responseHandler);
	}
}
