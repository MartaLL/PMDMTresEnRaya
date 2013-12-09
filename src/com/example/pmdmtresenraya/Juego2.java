package com.example.pmdmtresenraya;

import android.os.Bundle;
import android.os.Vibrator;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Juego2 extends Activity {
	private int turno=1;
	private int i,j,ganaO,ganaX,jugadas;
	private int [][] seleccionado;
	private Button[][] boton;
	private static final int[] idArrayFilas={1,2,3};
	private static final int[] idArrayColumnas={1,2,3};
	private Dialog dialogo=null;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		tablero();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.juego, menu);
		return true;
	}

	public boolean onOptionsItemSelected(MenuItem item){
		if(item.getItemId()==R.id.item1){
			Intent intent=new Intent(Juego2.this,MainActivity.class);
			startActivity(intent);
			return true;
		}
		else if(item.getItemId()==R.id.item2){
			tablero();
			return true;
		}
		else
		{
			return super.onOptionsItemSelected(item);
		}	
	}

	public void tablero(){
		setContentView(R.layout.activity_juego2);
		jugadas+=1;
		boton=new Button[3][3];
		seleccionado=new int[3][3];
		for(i=0;i<3;i++){
			for(j=0;j<3;j++){
				int a = idArrayFilas[i];
				int b = idArrayColumnas[j];
				int botonId = getResources().getIdentifier("button" + a + b , "id", getPackageName());
				boton[i][j]=(Button)findViewById(botonId);
				boton[i][j].setOnClickListener(new OnClickListener(){
					int x = i;
					int y = j;
					public void onClick(View arg0) {
						if(devolverTurno()==1){
							boton[x][y].setBackgroundResource(R.drawable.circulo);
							seleccionado[x][y]=1;
						}
						if(devolverTurno()==2){
							boton[x][y].setBackgroundResource(R.drawable.cruz);	
							seleccionado[x][y]=2;
						}
						boton[x][y].setEnabled(false);
						cambiarTurno();
						fin();
					}
				});
				boton[i][j].setBackgroundResource(R.drawable.casilla);
			}
		}
	}

	public void cambiarTurno(){
		if(turno==1)
			turno=2;
		else
			turno=1;
	}

	public int devolverTurno(){
		return turno;
	}

	public void fin(){
		if((seleccionado[0][0]==1&&seleccionado[0][1]==1&&seleccionado[0][2]==1)||(seleccionado[0][0]==1&seleccionado[1][0]==1&&seleccionado[2][0]==1)||
				(seleccionado[0][0]==1&seleccionado[1][1]==1&&seleccionado[2][2]==1)||(seleccionado[1][0]==1&seleccionado[1][1]==1&&seleccionado[1][2]==1)||
				(seleccionado[2][0]==1&seleccionado[2][1]==1&&seleccionado[2][2]==1)||(seleccionado[0][1]==1&seleccionado[1][1]==1&&seleccionado[2][1]==1)||
				(seleccionado[0][2]==1&seleccionado[1][2]==1&&seleccionado[2][2]==1)||(seleccionado[0][2]==1&seleccionado[1][1]==1&&seleccionado[2][0]==1)){
			ganaO+=1;
			dialogoGanar();
		}else if((seleccionado[0][0]==2&seleccionado[0][1]==2&&seleccionado[0][2]==2)||(seleccionado[0][0]==2&seleccionado[1][0]==2&&seleccionado[2][0]==2)||
				(seleccionado[0][0]==2&seleccionado[1][1]==2&&seleccionado[2][2]==2)||(seleccionado[1][0]==2&seleccionado[1][1]==2&&seleccionado[1][2]==2)||
				(seleccionado[2][0]==2&seleccionado[2][1]==2&&seleccionado[2][2]==2)||(seleccionado[0][1]==2&seleccionado[1][1]==2&&seleccionado[2][1]==2)||
				(seleccionado[0][2]==2&seleccionado[1][2]==2&&seleccionado[2][2]==2)||(seleccionado[0][2]==2&seleccionado[1][1]==2&&seleccionado[2][0]==2)){
			ganaX+=1;
			dialogoGanar();
		}else if(seleccionado[0][0]!=0&&seleccionado[0][1]!=0&&seleccionado[0][2]!=0&&seleccionado[1][0]!=0&&seleccionado[1][1]!=0&&seleccionado[1][2]!=0&&seleccionado[2][0]!=0&&seleccionado[2][1]!=0&&seleccionado[2][2]!=0){
			Vibrator vibra=(Vibrator)getSystemService(Context.VIBRATOR_SERVICE);
			vibra.vibrate(200);
			dialogo=new Dialog(this);
			dialogo.setCancelable(false);
			dialogo.setTitle("¡¡EMPATE!!");
			dialogo.setContentView(R.layout.empate_layout);
			Button btnReiniciar=(Button)dialogo.findViewById(R.id.btnReiniciarE);
			btnReiniciar.setOnClickListener(new OnClickListener(){
				@Override
				public void onClick(View v) {
					dialogo.dismiss();
					tablero();
				}
			});
			Button btnMenu=(Button)dialogo.findViewById(R.id.btnMenuE);
			btnMenu.setOnClickListener(new OnClickListener(){
				public void onClick(View w)
				{
					dialogo.dismiss();
					Juego2.this.finish();
				}
			});
			dialogo.show();
		}
	}
	
	public void dialogoGanar()
	{
		cambiarTurno();
		Vibrator vibra=(Vibrator)getSystemService(Context.VIBRATOR_SERVICE);
		vibra.vibrate(200);
		dialogo=new Dialog(this);
		dialogo.setCancelable(false);
		dialogo.setTitle("¡¡HA GANADO!!");
		dialogo.setContentView(R.layout.ganar_layout);
		SharedPreferences prefe=getSharedPreferences("datos",Context.MODE_PRIVATE);
		Editor editor=prefe.edit();
		editor.putString("ganaO","El jugador O ha ganado "+ganaO);
		editor.putString("ganaX","El jugador X ha ganado "+ganaX);
		editor.putString("jugadas"," en "+jugadas+" jugadas");
		editor.commit();
		SharedPreferences pref=getSharedPreferences("datos",Context.MODE_PRIVATE);
		TextView estadisticas=(TextView)dialogo.findViewById(R.id.textView1);
		estadisticas.setText(pref.getString("ganaO", "")+pref.getString("jugadas", "")+"\n"+pref.getString("ganaX", "")+pref.getString("jugadas", ""));
		Button btnReiniciar=(Button)dialogo.findViewById(R.id.btnReiniciar);
		btnReiniciar.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View v) {
				dialogo.dismiss();
				tablero();
			}
		});
		Button btnMenu=(Button)dialogo.findViewById(R.id.btnMenu);
		btnMenu.setOnClickListener(new OnClickListener(){
			public void onClick(View w)
			{
				dialogo.dismiss();
				Juego2.this.finish();
			}
		});
		dialogo.show();
	}

	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			Juego2.this.finish();
			// Si el listener devuelve true, significa que el evento esta procesado, y nadie debe hacer nada mas
			return true;
		}
		//para las demas cosas, se reenvia el evento al listener habitual
		return super.onKeyDown(keyCode, event);
	} 
}

