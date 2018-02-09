package com.jblandii.paint_jam;


import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.View;


/**
 * Created by jairo on 08/02/2018.
 */

public class Lienzo extends View {

    /*
    Path que utilizaré para pintar las lineas.
     */
    private Path drawPath;
    /*
    Paint de dibujar y Paint de Canvas.
     */
    private static Paint drawPaint;
    private Paint canvasPaint;
    /*
    Color por defecto.
     */
    private static int paintColor = 0xFFFF0000;
    /*
    Canvas.
     */
    private Canvas drawCanvas;
    /*
    canvas para guardar
     */
    private Bitmap canvasBitmap;

    static float TamanyoPunto;
    private static boolean borrado = false;

    public Lienzo(Context context, AttributeSet attrs) {
        super(context, attrs);
        setupDrawing();
    }

    /**
     * Configuración del area sobre la que se va a pintar.
     */
    private void setupDrawing() {
        drawPath = new Path();
        drawPaint = new Paint();
        drawPaint.setColor(paintColor);
        drawPaint.setAntiAlias(true);
        drawPaint.setStrokeWidth(20);
        drawPaint.setStyle(Paint.Style.STROKE);
        drawPaint.setStrokeJoin(Paint.Join.ROUND);
        drawPaint.setStrokeCap(Paint.Cap.ROUND);
        canvasPaint = new Paint(Paint.DITHER_FLAG);
    }

    /**
     * Tamaño que se asigna a la vista.
     *
     * @param w
     * @param h
     * @param oldw
     * @param oldh
     */
    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        canvasBitmap = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);
        drawCanvas = new Canvas(canvasBitmap);
    }

    /**
     * Pinta la vista y es llamado desde el OnTouchEvent.
     *
     * @param canvas
     */
    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawBitmap(canvasBitmap, 0, 0, canvasPaint);
        canvas.drawPath(drawPath, drawPaint);
    }

    /**
     * Registra los touch que va haciendo el usuario.
     *
     * @param event
     * @return
     */
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float touchX = event.getX();
        float touchY = event.getY();

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                drawPath.moveTo(touchX, touchY);
                break;
            case MotionEvent.ACTION_MOVE:
                drawPath.lineTo(touchX, touchY);
                break;
            case MotionEvent.ACTION_UP:
                drawPath.lineTo(touchX, touchY);
                drawCanvas.drawPath(drawPath, drawPaint);
                drawPath.reset();
                break;
            default:
                return false;
        }
        invalidate();
        return true;

    }

    /**
     * Actualiza el color.
     *
     * @param newColor
     */
    public void setColor(String newColor) {
        invalidate();
        paintColor = Color.parseColor(newColor);
        drawPaint.setColor(paintColor);
    }

    /**
     * Asigna el tamaño del punto.
     *
     * @param nuevoTamanyo
     */
    public static void setTamanyoPunto(float nuevoTamanyo) {
        drawPaint.setStrokeWidth(nuevoTamanyo);
    }

    /**
     * Set borrado (true o false).
     *
     * @param estaborrado
     */
    public static void setBorrado(boolean estaborrado) {
        borrado = estaborrado;
        if (borrado) {
            drawPaint.setColor(Color.WHITE);

        } else {
            drawPaint.setColor(paintColor);
        }
    }

    /**
     * Para vaciar la vista.
     */
    public void NuevoDibujo() {
        drawCanvas.drawColor(0, PorterDuff.Mode.CLEAR);
        invalidate();

    }
}

