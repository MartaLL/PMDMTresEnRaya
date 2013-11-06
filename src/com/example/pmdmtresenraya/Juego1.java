package com.example.pmdmtresenraya;

import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TimePicker;

public class Juego1 extends Activity {
	private int turno=1;
	private int i,j;
	private int [][] seleccionado;
	private Button[][] boton;
	private static final int[] idArrayFilas={1,2,3};
	private static final int[] idArrayColumnas={1,2,3};
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		tablero();
		iniciar();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.juego, menu);
		return true;
	}

	public boolean onOptionsItemSelected(MenuItem item){
		if(item.getItemId()==R.id.item1){
			Intent intent=new Intent(Juego1.this,MainActivity.class);
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
							boton[x][y].setEnabled(false);
							cambiarTurno();
							fin();
						}
						if(devolverTurno()==2){
							aleatorio();
							cambiarTurno();
							fin();
						}
					}
				});
			}
		}
//		boton[0][0]=(Button)findViewById(R.id.button11);
//		boton[0][1]=(Button)findViewById(R.id.button12);
//		boton[0][2]=(Button)findViewById(R.id.button13);
//		boton[1][0]=(Button)findViewById(R.id.button21);
//		boton[1][1]=(Button)findViewById(R.id.button22);
//		boton[1][2]=(Button)findViewById(R.id.button23);
//		boton[2][0]=(Button)findViewById(R.id.button31);
//		boton[2][1]=(Button)findViewById(R.id.button32);
//		boton[2][2]=(Button)findViewById(R.id.button33);
		iniciar();
		if(devolverTurno()==2)
			turno=1;
//		botonesListener();	
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

	public void aleatorio(){
		int [] a={0,1,2};
		int [] b={0,1,2};
		int aleatorioA,aleatorioB,x,y;
		do{
			aleatorioA= (int) (Math.random()*a.length+0);
			aleatorioB=(int) (Math.random()*b.length+0);
			x=a[aleatorioA];
			y=b[aleatorioB];
			boton[x][y].setBackgroundResource(R.drawable.cruz);
			seleccionado[x][y]=2;
		}while(seleccionado[x][y]==0);
	}

//	public void botonesListener(){
//		for(i=0;i<3;i++){
//			for(j=0;j<3;j++){
//				boton[i][j].setOnClickListener(new OnClickListener(){
//					int x = i;
//					int y = j;
//					public void onClick(View arg0) {
//						if(devolverTurno()==1){
//							boton[x][y].setBackgroundResource(R.drawable.circulo);
//							seleccionado[x][y]=1;
//							boton[x][y].setEnabled(false);
//							cambiarTurno();
//							fin();
//						}
//						if(devolverTurno()==2){
//							aleatorio();
//							cambiarTurno();
//							fin();
//						}
//					}
//				});
//			}
//		}
//	}

	public void iniciar(){
		for(int i=0;i<3;i++){
			for(int j=0;j<3;j++){
				boton[i][j].setBackgroundResource(R.drawable.casilla);
			}
		}
	}

	public void dialogoGanar(){
		cambiarTurno();
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setMessage("Ha ganado el jugador "+devolverTurno())
		.setTitle("¡¡Enhorabuena!!")
		.setCancelable(false)
		.setNeutralButton("Reiniciar Juego",
				new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int id) {
				dialog.cancel();
				tablero();
			}
		});
		AlertDialog alert = builder.create();
		alert.show();
	}

	public void fin(){
		if(seleccionado[0][0]==1&&seleccionado[0][1]==1&&seleccionado[0][2]==1){
			dialogoGanar();
		}
		else if(seleccionado[0][0]==2&seleccionado[0][1]==2&&seleccionado[0][2]==2){
			dialogoGanar();
		}
		else if(seleccionado[0][0]==1&seleccionado[1][0]==1&&seleccionado[2][0]==1){
			dialogoGanar();
		}
		else if(seleccionado[0][0]==2&seleccionado[1][0]==2&&seleccionado[2][0]==2){
			dialogoGanar();
		}
		else if(seleccionado[0][0]==1&seleccionado[1][1]==1&&seleccionado[2][2]==1){
			dialogoGanar();
		}
		else if(seleccionado[0][0]==2&seleccionado[1][1]==2&&seleccionado[2][2]==2){
			dialogoGanar();
		}
		else if(seleccionado[1][0]==1&seleccionado[1][1]==1&&seleccionado[1][2]==1){
			dialogoGanar();
		}
		else if(seleccionado[1][0]==2&seleccionado[1][1]==2&&seleccionado[1][2]==2){
			dialogoGanar();
		}
		else if(seleccionado[2][0]==1&seleccionado[2][1]==1&&seleccionado[2][2]==1){
			dialogoGanar();
		}
		else if(seleccionado[2][0]==2&seleccionado[2][1]==2&&seleccionado[2][2]==2){
			dialogoGanar();
		}
		else if(seleccionado[0][1]==1&seleccionado[1][1]==1&&seleccionado[2][1]==1){
			dialogoGanar();
		}
		else if(seleccionado[0][1]==2&seleccionado[1][1]==2&&seleccionado[2][1]==2){
			dialogoGanar();
		}
		else if(seleccionado[0][2]==1&seleccionado[1][2]==1&&seleccionado[2][2]==1){
			dialogoGanar();
		}
		else if(seleccionado[0][2]==2&seleccionado[1][2]==2&&seleccionado[2][2]==2){
			dialogoGanar();
		}
		else if(seleccionado[0][2]==1&seleccionado[1][1]==1&&seleccionado[2][0]==1){
			dialogoGanar();
		}
		else if(seleccionado[0][2]==2&seleccionado[1][1]==2&&seleccionado[2][0]==2){
			dialogoGanar();
		}
		else if(seleccionado[0][0]!=0&&seleccionado[0][1]!=0&&seleccionado[0][2]!=0&&seleccionado[1][0]!=0&&seleccionado[1][1]!=0&&seleccionado[1][2]!=0&&seleccionado[2][0]!=0&&seleccionado[2][1]!=0&&seleccionado[2][2]!=0){
			AlertDialog.Builder builder = new AlertDialog.Builder(this);
			builder.setMessage("Empate")
			.setTitle("¡¡Fin del juego!!")
			.setCancelable(false)
			.setNeutralButton("Reiniciar Juego",
					new DialogInterface.OnClickListener() {
				public void onClick(DialogInterface dialog, int id) {
					dialog.cancel();
					tablero();
				}
			});
			AlertDialog alert = builder.create();
			alert.show();
		}
		//00 01 02/00 10 20/00 11 22/10 11 12/20 21 22/01 11 21/02 12 22/02 11 20
	}
}

