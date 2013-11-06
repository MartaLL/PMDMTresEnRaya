package com.example.pmdmtresenraya;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		Button btnJugar1=(Button) findViewById(R.id.btnJugar1);
		btnJugar1.setOnClickListener(new OnClickListener(){
			public void onClick(View arg0) {
				Intent intent=new Intent(MainActivity.this,Juego1.class);
				startActivity(intent);
			}
		});
		
		Button btnJugar2=(Button) findViewById(R.id.btnJugar2);
		btnJugar2.setOnClickListener(new OnClickListener(){
			public void onClick(View arg0) {
				Intent intent=new Intent(MainActivity.this,Juego2.class);
				startActivity(intent);
			}
		});
		
		Button btnAcercaDe=(Button) findViewById(R.id.btnAcercaDe);
		btnAcercaDe.setOnClickListener(new OnClickListener(){
			public void onClick(View arg0) {
				Intent intent=new Intent(MainActivity.this,AcercaDe.class);
				startActivity(intent);
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return false;
	}

}
