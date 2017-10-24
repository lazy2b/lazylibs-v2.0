/*******************************************************************************
 * Copyright 2011, 2012 Chris Banes.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *******************************************************************************/
package com.lazy2b.demo.activity;


import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Point;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.github.chrisbanes.photoview.OnMatrixChangedListener;
import com.github.chrisbanes.photoview.OnPhotoTapListener;
import com.github.chrisbanes.photoview.OnSingleFlingListener;
import com.github.chrisbanes.photoview.OnViewTapListener;
import com.lazy2b.demo.R;
import com.lazy2b.libs.view.QAPhotoView;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class SimpleSampleActivity extends AppCompatActivity {

    static final String PHOTO_TAP_TOAST_STRING = "Photo Tap! X: %.2f %% Y:%.2f %% ID: %d";
    static final String SCALE_TOAST_STRING = "Scaled to: %.2ff";
    static final String FLING_LOG_STRING = "Fling velocityX: %.2f, velocityY: %.2f";
    static final String TAP_LOG_STRING = "View Tap x:%.2f, y: %.2f";

    private QAPhotoView mPhotoView;
    private TextView mCurrMatrixTv;

    private Toast mCurrentToast;

    private Matrix mCurrentDisplayMatrix = null;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.pv_main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        switch (item.getItemId()) {
            case R.id.menu_zoom_toggle:
                mPhotoView.setZoomable(!mPhotoView.isZoomEnabled());
                item.setTitle(mPhotoView.isZoomEnabled() ? R.string.menu_zoom_disable : R.string.menu_zoom_enable);
                return true;

            case R.id.menu_scale_fit_center:
                mPhotoView.setScaleType(ImageView.ScaleType.CENTER);
                return true;

            case R.id.menu_scale_fit_start:
                mPhotoView.setScaleType(ImageView.ScaleType.FIT_START);
                return true;

            case R.id.menu_scale_fit_end:
                mPhotoView.setScaleType(ImageView.ScaleType.FIT_END);
                return true;

            case R.id.menu_scale_fit_xy:
                mPhotoView.setScaleType(ImageView.ScaleType.FIT_XY);
                return true;

            case R.id.menu_scale_scale_center:
                mPhotoView.setScaleType(ImageView.ScaleType.CENTER);
                return true;

            case R.id.menu_scale_scale_center_crop:
                mPhotoView.setScaleType(ImageView.ScaleType.CENTER_CROP);
                return true;

            case R.id.menu_scale_scale_center_inside:
                mPhotoView.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
                return true;

            case R.id.menu_scale_random_animate:
            case R.id.menu_scale_random:
                Random r = new Random();

                float minScale = mPhotoView.getMinimumScale();
                float maxScale = mPhotoView.getMaximumScale();
                float randomScale = minScale + (r.nextFloat() * (maxScale - minScale));
                mPhotoView.setScale(randomScale, item.getItemId() == R.id.menu_scale_random_animate);

                showToast(String.format(SCALE_TOAST_STRING, randomScale));

                return true;
            case R.id.menu_matrix_restore:
                if (mCurrentDisplayMatrix == null)
                    showToast("You need to capture display matrix first");
                else
                    mPhotoView.setDisplayMatrix(mCurrentDisplayMatrix);
                return true;
            case R.id.menu_matrix_capture:
                mCurrentDisplayMatrix = new Matrix();
                mPhotoView.getDisplayMatrix(mCurrentDisplayMatrix);
                return true;
        }
        return false;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_simple_sample);
        mPhotoView = (QAPhotoView) findViewById(R.id.iv_photo);
        mCurrMatrixTv = (TextView) findViewById(R.id.tv_current_matrix);

        Drawable bitmap = ContextCompat.getDrawable(this, R.drawable.cat1);
        Bitmap local = read();
        if (local != null) {
            mPhotoView.setImageBitmap(local);
        } else {
            mPhotoView.setImageDrawable(bitmap);
        }
        // Lets attach some listeners, not required though!
        mPhotoView.setOnMatrixChangeListener(new MatrixChangeListener());
        mPhotoView.setOnPhotoTapListener(new PhotoTapListener());
        mPhotoView.setOnSingleFlingListener(new SingleFlingListener());
        mPhotoView.setOnViewTapListener(new OnViewTapListener() {
            @Override
            public void onViewTap(View view, float x, float y) {
                Log.d("PhotoView", String.format(TAP_LOG_STRING, x, y));
//                points.clear();
                points.add(new Point((int) x, (int) y));
                mPhotoView.setPoints(points);
//                DataBindingUtils.
            }
        });
//        if (Build.VERSION.SDK_INT >= 23 && !hasWritePermission()) {
        ActivityCompat.requestPermissions(SimpleSampleActivity.this, permissions, 10086);
//        }


    }

    String[] permissions = new String[]{android.Manifest.permission.WRITE_EXTERNAL_STORAGE, android.Manifest.permission.MOUNT_UNMOUNT_FILESYSTEMS};

    List<Point> points = new ArrayList<>();


    private class PhotoTapListener implements OnPhotoTapListener {

        @Override
        public void onPhotoTap(ImageView view, float x, float y) {

        }
    }

    private void showToast(CharSequence text) {
        if (mCurrentToast != null) {
            mCurrentToast.cancel();
        }

        mCurrentToast = Toast.makeText(SimpleSampleActivity.this, text, Toast.LENGTH_SHORT);
        mCurrentToast.show();
    }

    private class MatrixChangeListener implements OnMatrixChangedListener {

        @Override
        public void onMatrixChanged(RectF rect) {
            mCurrMatrixTv.setText(rect.toString());
        }
    }

    private class SingleFlingListener implements OnSingleFlingListener {

        @Override
        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
            Log.d("PhotoView", String.format(FLING_LOG_STRING, velocityX, velocityY));
            return true;
        }
    }

    Bitmap read() {

        File file = new File(Environment.getExternalStorageDirectory(), path);

        if (file.exists()) {
            return BitmapFactory.decodeFile(file.getPath());
        }
        return null;
    }

    String path = "cat01.jpg";

    void save(Bitmap drawingCache) {

        try {
            File imageFile = new File(Environment.getExternalStorageDirectory(), path);
            FileOutputStream outStream;
            outStream = new FileOutputStream(imageFile);
            drawingCache.compress(Bitmap.CompressFormat.JPEG, 100, outStream);
            outStream.flush();
            outStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    Bitmap getImageBitMap(View view) {
        if (view == null) {
            return null;
        }
        Bitmap bitmap = Bitmap.createBitmap(view.getWidth(), view.getHeight(), Bitmap.Config.ARGB_4444);
        Canvas canvas = new Canvas(bitmap);
        view.draw(canvas);
        return bitmap;

    }


//    @Override
//    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
//
//        if(requestCode == 10086){
//            save(getImageBitMap(mPhotoView));
//        }
//
//        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
//    }

    boolean hasWritePermission() {
        return ActivityCompat.checkSelfPermission(this, permissions[0]) == PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(this, permissions[1]) == PackageManager.PERMISSION_GRANTED;
    }

    @Override
    protected void onDestroy() {
//        if (hasWritePermission()) {
        mPhotoView.post(new Runnable() {
            @Override
            public void run() {
                save(getImageBitMap(mPhotoView));
            }
        });
//        }
        super.onDestroy();

    }
}
