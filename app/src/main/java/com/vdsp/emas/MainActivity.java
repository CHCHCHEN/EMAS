package com.vdsp.emas;

import android.Manifest;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.taobao.sophix.SophixManager;

import java.util.List;

import pub.devrel.easypermissions.EasyPermissions;

public class MainActivity extends AppCompatActivity implements EasyPermissions.PermissionCallbacks {

    //需要进行检测的权限数组
    protected String[] permissions = {
            Manifest.permission.READ_EXTERNAL_STORAGE};
    private static final int PERMISSON_REQUESTCODE = 0;

    TextView tv_test;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tv_test = findViewById(R.id.tv_test);

        if (EasyPermissions.hasPermissions(getApplicationContext(), permissions)) {
            //已经申请相关权限

        } else {
            //没有打开相关权限、申请权限
            EasyPermissions.requestPermissions(MainActivity.this, "请同意授权相关权限，避免APP出现闪退而无法使用！！！", 1, permissions);
        }

    }

    /**
     * 提示：分别build的apk，提取出来放到不同文件夹方便测试使用
     */
    public void bt_tocoloe(View view) {
        int num = 250;

        //1.0利用下面的方法 ，制造一个bug后，build一个有问题的apk
        tv_test.setText(num);

        //2.0利用下面的方法 ，修复bug后，build一个没问题的
        //tv_test.setText("辣鸡茂="+num);
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        //框架要求必须这么写
        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this);
    }

    //成功打开权限
    @Override
    public void onPermissionsGranted(int requestCode, @NonNull List<String> perms) {
        //Toast.makeText(this, "相关权限获取成功", Toast.LENGTH_SHORT).show();
        initHardwareAccelerate();
    }

    //用户未同意权限
    @Override
    public void onPermissionsDenied(int requestCode, @NonNull List<String> perms) {
        //Toast.makeText(this, "请同意相关权限，否则功能无法使用", Toast.LENGTH_SHORT).show();
    }
    /**
     * 启用硬件加速
     */
    private void initHardwareAccelerate() {
        try {
            if (Integer.parseInt(Build.VERSION.SDK) >= 11) {
                getWindow().setFlags(
                        WindowManager.LayoutParams.FLAG_HARDWARE_ACCELERATED,
                        WindowManager.LayoutParams.FLAG_HARDWARE_ACCELERATED);
            }
        } catch (Exception e) {

        }
    }
}
