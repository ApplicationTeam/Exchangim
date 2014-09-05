package com.appteam.exchange.service;

import java.io.IOException;
import java.util.Date;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONException;
import org.json.JSONObject;

import android.util.Log;

import com.appteam.exchange.model.ExchangeItemModel;

public class ExchangeService {

	public ExchangeItemModel download(){
		
		String result = null;

		String URL = "http://rate-exchange.appspot.com/currency?";
		String q = "from=USD&to=TRY";
		HttpClient httpclient = new DefaultHttpClient();
		HttpGet request = new HttpGet(URL + q);
		ResponseHandler<String> handler = new BasicResponseHandler();
		
		try {
			result = httpclient.execute(request, handler);
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		httpclient.getConnectionManager().shutdown();	
		ExchangeItemModel exchangeItemModel = setExchangeItemModel(result);
		
		
		
		return exchangeItemModel;
	}
	
	
	public ExchangeItemModel setExchangeItemModel(String result) {

		try {

			ExchangeItemModel exchangeItemModel = new ExchangeItemModel();
			JSONObject jsonObject = new JSONObject(result);
			exchangeItemModel.setTo(jsonObject.getString("to"));
			exchangeItemModel.setRate(jsonObject.getString("rate"));
			exchangeItemModel.setFrom(jsonObject.getString("from"));
			exchangeItemModel.setCurrenttime(new Date().getTime());
			return exchangeItemModel;
			
		} catch (JSONException e) {
			Log.v("Exchange Service", e.getMessage());
			return null;
		}

	}	
	
}
