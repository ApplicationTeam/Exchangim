package com.appteam.exchange.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.appteam.exchange.R;
import com.appteam.exchange.database.ExchangeSqliteHelper;

public class MainActivity extends Activity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		Button button = (Button) findViewById(R.id.buttonId);
		button.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				
				ExchangeSqliteHelper exchangeSqliteHelper = new ExchangeSqliteHelper(getApplicationContext());
			}
		});
	}

}
