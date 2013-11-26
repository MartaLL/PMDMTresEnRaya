package com.example.pmdmtresenraya;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Vibrator;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends Activity {
//partidas jugadas y partidas ganadas por un usuario determinado
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
		
		Button btnSalir=(Button) findViewById(R.id.btnSalir);
		btnSalir.setOnClickListener(new OnClickListener(){
			public void onClick(View arg0) {
				dialogoSalir();
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return false;
	}

	public void dialogoSalir()
	{
		Vibrator vibra=(Vibrator)getSystemService(Context.VIBRATOR_SERVICE);
		vibra.vibrate(200);
		AlertDialog.Builder dialogo=new AlertDialog.Builder(this);
		dialogo.setTitle("Salir");
		dialogo.setMessage("¿Esta seguro que desea salir?");
		dialogo.setNegativeButton("No", new DialogInterface.OnClickListener(){
			public void onClick(DialogInterface dialog, int which){
				dialog.cancel();
			}
		});
		dialogo.setPositiveButton("Si", new DialogInterface.OnClickListener(){
			public void onClick(DialogInterface dialog, int which){
				MainActivity.this.finish();
			}
		});
		dialogo.show();
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			dialogoSalir();
			// Si el listener devuelve true, significa que el evento esta procesado, y nadie debe hacer nada mas
			return true;
		}
		//para las demas cosas, se reenvia el evento al listener habitual
		return super.onKeyDown(keyCode, event);
	} 
}
