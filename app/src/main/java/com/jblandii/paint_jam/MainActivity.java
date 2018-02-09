package com.jblandii.paint_jam;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.UUID;

import yuku.ambilwarna.AmbilWarnaDialog;

public class MainActivity extends AppCompatActivity implements OnClickListener {

    private static Lienzo lienzo;
    float tamanoElegido;
    ImageButton trazo;
    ImageButton anyadir;
    ImageButton borrar;
    ImageButton guardar;
    ImageButton paletaColores;
    Button colorEscogido;
    int colorBoton;
    SeekBar seekBar;
    TextView tv_progess;

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
        paletaColores.setOnClickListener(this);

        lienzo = findViewById(R.id.v_lienzo);
        lienzo.setColor((String) colorEscogido.getTag());
        tamanoElegido = 20;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.paleta_colores:
                dialogColorPicker();
                break;
            case R.id.trazo:
                dialogoTamanyo("pintar");
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
                dialogoTamanyo("borrar");
                break;
            case R.id.guardar:

                AlertDialog.Builder salvarDibujo = new AlertDialog.Builder(this);
                salvarDibujo.setTitle("Salvar dibujo");
                salvarDibujo.setMessage("¿Salvar Dibujo a la galeria?");
                salvarDibujo.setPositiveButton("Aceptar", new DialogInterface.OnClickListener()

                {
                    public void onClick(DialogInterface dialog, int which) {

                        /*
                        Guardar el dibujo.
                         */
                        lienzo.setDrawingCacheEnabled(true);
                        String imgSaved = MediaStore.Images.Media.insertImage(
                                getContentResolver(), lienzo.getDrawingCache(),
                                UUID.randomUUID().toString() + ".png", "drawing");
                        /*
                        Mensaje de todo correcto
                         */
                        if (imgSaved != null) {
                            Toast savedToast = Toast.makeText(getApplicationContext(),
                                    "¡Dibujo guardado en la galeria con exito!", Toast.LENGTH_SHORT);
                            savedToast.show();
                        } else {
                            Toast unsavedToast = Toast.makeText(getApplicationContext(),
                                    "¡Error! La imagen no ha podido ser guardada.", Toast.LENGTH_SHORT);
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

    /**
     * Cuadro de dialogo con paleta de todos los colores.
     */
    public void dialogColorPicker() {
        AmbilWarnaDialog colorPicker = new AmbilWarnaDialog(this, colorBoton, new AmbilWarnaDialog.OnAmbilWarnaListener() {
            @Override
            public void onCancel(AmbilWarnaDialog dialog) {
            }

            @Override
            public void onOk(AmbilWarnaDialog dialog, int color) {
                String hexColor = String.format("#%06X", (0xFFFFFF & color));
                colorBoton = color;
                colorEscogido.setTag(hexColor);
                colorEscogido.setBackgroundColor(Color.parseColor(hexColor));
                lienzo.setColor(hexColor);

            }
        });
        colorPicker.show();
    }

    /**
     * Cuadro de dialogo que usaré tanto para borrar como para pintar en el que tendre que elegir el tamaño del pincel.
     * @param eleccion para saber si se va a borrar o pintar.
     */
    public void dialogoTamanyo(String eleccion) {
        final Dialog tamanyoPincel = new Dialog(this);
        final boolean booleanEleccion;
        tamanyoPincel.setContentView(R.layout.progress);
        final Button btn_aceptar = tamanyoPincel.findViewById(R.id.btn_aceptar);
        Button btn_cancelar = tamanyoPincel.findViewById(R.id.btn_cancelar);
        tv_progess = tamanyoPincel.findViewById(R.id.tv_mostrar);
        seekBar = tamanyoPincel.findViewById(R.id.sb_seekBar);
        seekBar.setMax(100);
        tv_progess.setText(((int) tamanoElegido) + "");
        seekBar.setProgress((int) tamanoElegido);
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                tv_progess.setText(progress + "");
            }

            public void onStartTrackingTouch(SeekBar arg0) {
            }

            public void onStopTrackingTouch(SeekBar seekBar) {
                if (seekBar.getProgress() < 1) {
                    btn_aceptar.setEnabled(false);
                    Toast.makeText(MainActivity.this, "El tamaño minimo del pincel es " + 1 + ".", Toast.LENGTH_LONG).show();
                } else {
                    btn_aceptar.setEnabled(true);
                }
            }
        });
        switch (eleccion) {
            case "borrar":
                booleanEleccion = true;
                break;
            case "pintar":
                booleanEleccion = false;
                break;
            default:
                booleanEleccion = false;
                break;
        }

        btn_aceptar.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                Lienzo.setBorrado(booleanEleccion);
                tamanoElegido = Float.parseFloat(tv_progess.getText().toString());
                Lienzo.setTamanyoPunto(tamanoElegido);
                tamanyoPincel.dismiss();
            }
        });
        btn_cancelar.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                tamanyoPincel.dismiss();
            }
        });
        tamanyoPincel.show();
    }
}