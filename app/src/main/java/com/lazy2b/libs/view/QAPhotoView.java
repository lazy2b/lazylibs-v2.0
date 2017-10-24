package com.lazy2b.libs.view;


import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;
import android.graphics.Typeface;
import android.util.AttributeSet;

import com.github.chrisbanes.photoview.PhotoView;

import java.util.List;


/**
 * Created by lazy2b on 17/10/24.
 */

public class QAPhotoView extends PhotoView {
    public QAPhotoView(Context context) {
        super(context);
    }

    public QAPhotoView(Context context, AttributeSet attr) {
        super(context, attr);
    }

    public QAPhotoView(Context context, AttributeSet attr, int defStyle) {
        super(context, attr, defStyle);
    }

    List<Point> points = null;

    public void setPoints(List<Point> points) {
        this.points = points;
        invalidate();
    }

    Paint pointPaint, linePaint;

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (points == null) return;
        if (pointPaint == null) {
            pointPaint = new Paint();
            pointPaint.setColor(Color.BLUE);
            pointPaint.setStrokeWidth(15);
            pointPaint.setStyle(Paint.Style.FILL);
            linePaint = new Paint();
            linePaint.setColor(Color.BLACK);
            linePaint.setTypeface(Typeface.DEFAULT);
            linePaint.setStrokeWidth(10);
            linePaint.setStyle(Paint.Style.STROKE);
        }

        if (points.size() == 5) {
            Path path = new Path();
            Point point = points.get(0);
            path.moveTo(point.x, point.y);
            for (int i = 1; i < points.size(); i++) {
                path.lineTo(points.get(i).x, points.get(i).y);
            }
            path.close();
            canvas.drawPath(path, linePaint);
        }
        for (Point point : points) {
            canvas.drawPoint(point.x, point.y, pointPaint);
        }
    }
}
