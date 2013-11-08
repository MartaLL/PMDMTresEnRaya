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

public class Juego2 extends Activity {
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
		if(devolverTurno()==2)
			turno=1;
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

	public void dialogoSalir()
	{
		AlertDialog.Builder dialogo=new AlertDialog.Builder(this);
		dialogo.setTitle("Salir");
		dialogo.setMessage("¿Esta seguro que desea salir?");
		dialogo.setPositiveButton("Si", new DialogInterface.OnClickListener(){
			public void onClick(DialogInterface dialog, int which){
				Juego2.this.finish();
			}
		});
		dialogo.setNegativeButton("No", new DialogInterface.OnClickListener(){
			public void onClick(DialogInterface dialog, int which){
				dialog.cancel();
			}
		});
		dialogo.show();
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

	public void dialogoGanar(){
		cambiarTurno();
		AlertDialog.Builder dialogo = new AlertDialog.Builder(this);
		dialogo.setMessage("Ha ganado el jugador "+devolverTurno());
		dialogo.setTitle("¡¡Enhorabuena!!");
		dialogo.setCancelable(false);
		dialogo.setNeutralButton("Reiniciar Juego",new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int id) {
				dialog.cancel();
				tablero();
			}
		});
		dialogo.show();
	}

	public void fin(){
		if((seleccionado[0][0]==1&&seleccionado[0][1]==1&&seleccionado[0][2]==1)||(seleccionado[0][0]==2&seleccionado[0][1]==2&&seleccionado[0][2]==2)||
			(seleccionado[0][0]==1&seleccionado[1][0]==1&&seleccionado[2][0]==1)||(seleccionado[0][0]==2&seleccionado[1][0]==2&&seleccionado[2][0]==2)||
			(seleccionado[0][0]==1&seleccionado[1][1]==1&&seleccionado[2][2]==1)||(seleccionado[0][0]==2&seleccionado[1][1]==2&&seleccionado[2][2]==2)||
			(seleccionado[1][0]==1&seleccionado[1][1]==1&&seleccionado[1][2]==1)||(seleccionado[1][0]==2&seleccionado[1][1]==2&&seleccionado[1][2]==2)||
			(seleccionado[2][0]==1&seleccionado[2][1]==1&&seleccionado[2][2]==1)||(seleccionado[2][0]==2&seleccionado[2][1]==2&&seleccionado[2][2]==2)||
			(seleccionado[0][1]==1&seleccionado[1][1]==1&&seleccionado[2][1]==1)||(seleccionado[0][1]==2&seleccionado[1][1]==2&&seleccionado[2][1]==2)||
			(seleccionado[0][2]==1&seleccionado[1][2]==1&&seleccionado[2][2]==1)||(seleccionado[0][2]==2&seleccionado[1][2]==2&&seleccionado[2][2]==2)||
			(seleccionado[0][2]==1&seleccionado[1][1]==1&&seleccionado[2][0]==1)||(seleccionado[0][2]==2&seleccionado[1][1]==2&&seleccionado[2][0]==2)){
			dialogoGanar();
		}else if(seleccionado[0][0]!=0&&seleccionado[0][1]!=0&&seleccionado[0][2]!=0&&seleccionado[1][0]!=0&&seleccionado[1][1]!=0&&seleccionado[1][2]!=0&&seleccionado[2][0]!=0&&seleccionado[2][1]!=0&&seleccionado[2][2]!=0){
			AlertDialog.Builder dialogo = new AlertDialog.Builder(this);
			dialogo.setMessage("Empate");
			dialogo.setTitle("¡¡Fin del juego!!");
			dialogo.setCancelable(false);
			dialogo.setNeutralButton("Reiniciar Juego", new DialogInterface.OnClickListener() {
				public void onClick(DialogInterface dialog, int id) {
					dialog.cancel();
					tablero();
				}
			});
			dialogo.show();
		}
	}
}

