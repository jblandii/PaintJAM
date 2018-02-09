package com.jblandii.paint_jam;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.UUID;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private static Lienzo lienzo;
    float ppequenyo;
    float pmediano;
    float pgrande;
    float pdefecto;
    ImageButton trazo;
    ImageButton anyadir;
    ImageButton borrar;
    ImageButton guardar;
    ImageButton paletaColores;
    Button colorEscogido;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        trazo = findViewById(R.id.trazo);
        anyadir = findViewById(R.id.anadir);
        borrar = findViewById(R.id.borrar);
        guardar = findViewById(R.id.guardar);
        paletaColores = findViewById(R.id.paleta_colores);
        colorEscogido = findViewById(R.id.btn_color);

        trazo.setOnClickListener(this);
        anyadir.setOnClickListener(this);
        borrar.setOnClickListener(this);
        guardar.setOnClickListener(this);
        colorEscogido.setOnClickListener(this);
        paletaColores.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Dialog colores = new Dialog(MainActivity.this);
                colores.setContentView(R.layout.paleta_colores);
                final Button botonnegro = colores.findViewById(R.id.botonnegro);
                botonnegro.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String color = botonnegro.getTag().toString();
                        colorEscogido.setTag(color);
                        colorEscogido.setBackgroundColor(Color.parseColor(color));
                        lienzo.setColor(color);
                        colores.cancel();
                    }
                });

                final Button botonblanco = colores.findViewById(R.id.botonblanco);
                botonblanco.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String color = botonblanco.getTag().toString();
                        colorEscogido.setTag(color);
                        colorEscogido.setBackgroundColor(Color.parseColor(color));
                        lienzo.setColor(color);
                        colores.cancel();
                    }
                });

                final Button botongris = colores.findViewById(R.id.botongris);
                botongris.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String color = botongris.getTag().toString();
                        colorEscogido.setTag(color);
                        colorEscogido.setBackgroundColor(Color.parseColor(color));
                        lienzo.setColor(color);
                        colores.cancel();
                    }
                });

                final Button botonrojo = colores.findViewById(R.id.botonrojo);
                botonrojo.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String color = botonrojo.getTag().toString();
                        colorEscogido.setTag(color);
                        colorEscogido.setBackgroundColor(Color.parseColor(color));
                        lienzo.setColor(color);
                        colores.cancel();
                    }
                });

                final Button botonamarillo = colores.findViewById(R.id.botonamarillo);
                botonamarillo.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String color = botonamarillo.getTag().toString();
                        colorEscogido.setTag(color);
                        colorEscogido.setBackgroundColor(Color.parseColor(color));
                        lienzo.setColor(color);
                        colores.cancel();
                    }
                });

                final Button botonazul = colores.findViewById(R.id.botonazul);
                botonazul.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String color = botonazul.getTag().toString();
                        colorEscogido.setTag(color);
                        colorEscogido.setBackgroundColor(Color.parseColor(color));
                        lienzo.setColor(color);
                        colores.cancel();
                    }
                });

                final Button botonverde = colores.findViewById(R.id.botonverde);
                botonverde.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String color = botonverde.getTag().toString();
                        colorEscogido.setTag(color);
                        colorEscogido.setBackgroundColor(Color.parseColor(color));
                        lienzo.setColor(color);
                        colores.cancel();
                    }
                });

                final Button botonnaranja = colores.findViewById(R.id.botonnaranja);
                botonnaranja.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String color = botonnaranja.getTag().toString();
                        colorEscogido.setTag(color);
                        colorEscogido.setBackgroundColor(Color.parseColor(color));
                        lienzo.setColor(color);
                        colores.cancel();
                    }
                });

                final Button botonaqua = colores.findViewById(R.id.botonaqua);
                botonaqua.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String color = botonaqua.getTag().toString();
                        colorEscogido.setTag(color);
                        colorEscogido.setBackgroundColor(Color.parseColor(color));
                        lienzo.setColor(color);
                        colores.cancel();
                    }
                });
                colores.show();
            }
        });

        lienzo = findViewById(R.id.v_lienzo);

        ppequenyo = 10;
        pmediano = 20;
        pgrande = 40;

        pdefecto = pmediano;

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.trazo:
                final Dialog tamanyopunto = new Dialog(this);
                tamanyopunto.setTitle("Tamaño del punto:");
                tamanyopunto.setContentView(R.layout.tamanyo_punto);
                //listen for clicks on tamaños de los botones
                TextView smallBtn = (TextView) tamanyopunto.findViewById(R.id.tpequenyo);
                smallBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Lienzo.setBorrado(false);
                        Lienzo.setTamanyoPunto(ppequenyo);

                        tamanyopunto.dismiss();
                    }
                });
                TextView mediumBtn = (TextView) tamanyopunto.findViewById(R.id.tmediano);
                mediumBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Lienzo.setBorrado(false);
                        Lienzo.setTamanyoPunto(pmediano);

                        tamanyopunto.dismiss();
                    }
                });
                TextView largeBtn = (TextView) tamanyopunto.findViewById(R.id.tgrande);
                largeBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Lienzo.setBorrado(false);
                        Lienzo.setTamanyoPunto(pgrande);

                        tamanyopunto.dismiss();
                    }
                });
                tamanyopunto.show();
                break;
            case R.id.anadir:

                AlertDialog.Builder newDialog = new AlertDialog.Builder(this);
                newDialog.setTitle("Nuevo Dibujo");
                newDialog.setMessage("¿Comenzar nuevo dibujo (perderás el dibujo actual)?");
                newDialog.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        lienzo.NuevoDibujo();
                        dialog.dismiss();
                    }
                });
                newDialog.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
                newDialog.show();
                break;
            case R.id.borrar:

                final Dialog borrarpunto = new Dialog(this);
                borrarpunto.setTitle("Tamaño de borrado:");
                borrarpunto.setContentView(R.layout.tamanyo_punto);
                //listen for clicks on tamaños de los botones
                TextView smallBtnBorrar = (TextView) borrarpunto.findViewById(R.id.tpequenyo);
                smallBtnBorrar.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Lienzo.setBorrado(true);
                        Lienzo.setTamanyoPunto(ppequenyo);

                        borrarpunto.dismiss();
                    }
                });
                TextView mediumBtnBorrar = (TextView) borrarpunto.findViewById(R.id.tmediano);
                mediumBtnBorrar.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Lienzo.setBorrado(true);
                        Lienzo.setTamanyoPunto(pmediano);

                        borrarpunto.dismiss();
                    }
                });
                TextView largeBtnBorrar = (TextView) borrarpunto.findViewById(R.id.tgrande);
                largeBtnBorrar.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Lienzo.setBorrado(true);
                        Lienzo.setTamanyoPunto(pgrande);

                        borrarpunto.dismiss();
                    }
                });
                //show and wait for user interaction
                borrarpunto.show();


                break;
            case R.id.guardar:

                AlertDialog.Builder salvarDibujo = new AlertDialog.Builder(this);
                salvarDibujo.setTitle("Salvar dibujo");
                salvarDibujo.setMessage("¿Salvar Dibujo a la galeria?");
                salvarDibujo.setPositiveButton("Aceptar", new DialogInterface.OnClickListener()

                {
                    public void onClick(DialogInterface dialog, int which) {

                        //Salvar dibujo
                        lienzo.setDrawingCacheEnabled(true);
                        //attempt to save
                        String imgSaved = MediaStore.Images.Media.insertImage(
                                getContentResolver(), lienzo.getDrawingCache(),
                                UUID.randomUUID().toString() + ".png", "drawing");
                        //Mensaje de todo correcto
                        if (imgSaved != null) {
                            Toast savedToast = Toast.makeText(getApplicationContext(),
                                    "¡Dibujo salvado en la galeria!", Toast.LENGTH_SHORT);
                            savedToast.show();
                        } else {
                            Toast unsavedToast = Toast.makeText(getApplicationContext(),
                                    "¡Error! La imagen no ha podido ser salvada.", Toast.LENGTH_SHORT);
                            unsavedToast.show();
                        }
                        lienzo.destroyDrawingCache();
                    }
                });
                salvarDibujo.setNegativeButton("Cancelar", new DialogInterface.OnClickListener()

                {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
                salvarDibujo.show();

                break;
            default:

                break;
        }
    }

    public void ShowDialog() {
        /*final TextView tv_progress;
        final AlertDialog.Builder popDialog = new AlertDialog.Builder(this);
        final SeekBar seek = new SeekBar(this);
        seek.setMax(100);
        tv_progress = findViewById(R.id.tv_mostrar);
        popDialog.setTitle("Escoja el grosor del pincel: ");
        popDialog.setView(seek);
        seek.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean b) {
                tv_progress.setText("Value of : " + progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        popDialog.create();
        popDialog.show();*/
    }
}