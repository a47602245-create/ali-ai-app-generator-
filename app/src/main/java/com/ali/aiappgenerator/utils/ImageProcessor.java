package com.ali.aiappgenerator.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.Paint;

public class ImageProcessor {
    private Context context;
    private Bitmap originalBitmap;
    private Bitmap processedBitmap;

    public ImageProcessor(Context context) {
        this.context = context;
    }

    public void setBitmap(Bitmap bitmap) {
        this.originalBitmap = bitmap;
        this.processedBitmap = bitmap.copy(bitmap.getConfig(), true);
    }

    public Bitmap adjustBrightness(float brightness) {
        ColorMatrix colorMatrix = new ColorMatrix();
        colorMatrix.set(new float[]{
                1, 0, 0, 0, brightness,
                0, 1, 0, 0, brightness,
                0, 0, 1, 0, brightness,
                0, 0, 0, 1, 0
        });
        return applyColorMatrix(colorMatrix);
    }

    public Bitmap adjustContrast(float contrast) {
        ColorMatrix colorMatrix = new ColorMatrix();
        colorMatrix.set(new float[]{
                contrast, 0, 0, 0, 0,
                0, contrast, 0, 0, 0,
                0, 0, contrast, 0, 0,
                0, 0, 0, 1, 0
        });
        return applyColorMatrix(colorMatrix);
    }

    public Bitmap adjustSaturation(float saturation) {
        ColorMatrix colorMatrix = new ColorMatrix();
        colorMatrix.setSaturation(saturation);
        return applyColorMatrix(colorMatrix);
    }

    public Bitmap rotateImage() {
        Bitmap rotated = Bitmap.createBitmap(processedBitmap.getHeight(), processedBitmap.getWidth(), processedBitmap.getConfig());
        Canvas canvas = new Canvas(rotated);
        canvas.rotate(90, rotated.getWidth() / 2f, rotated.getHeight() / 2f);
        canvas.drawBitmap(processedBitmap, (rotated.getWidth() - processedBitmap.getWidth()) / 2f, (rotated.getHeight() - processedBitmap.getHeight()) / 2f, null);
        return rotated;
    }

    private Bitmap applyColorMatrix(ColorMatrix colorMatrix) {
        Bitmap result = Bitmap.createBitmap(processedBitmap.getWidth(), processedBitmap.getHeight(), processedBitmap.getConfig());
        Canvas canvas = new Canvas(result);
        Paint paint = new Paint();
        paint.setColorFilter(new ColorMatrixColorFilter(colorMatrix));
        canvas.drawBitmap(processedBitmap, 0, 0, paint);
        return result;
    }
}