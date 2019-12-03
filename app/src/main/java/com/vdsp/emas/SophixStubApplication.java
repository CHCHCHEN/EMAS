package com.vdsp.emas;

import android.content.Context;
import android.util.Log;

import androidx.annotation.Keep;

import com.taobao.sophix.PatchStatus;
import com.taobao.sophix.SophixApplication;
import com.taobao.sophix.SophixEntry;
import com.taobao.sophix.SophixManager;
import com.taobao.sophix.listener.PatchLoadStatusListener;

/**
 * Sophix入口类，专门用于初始化Sophix，不应包含任何业务逻辑。
 * 此类必须继承自SophixApplication，onCreate方法不需要实现。
 * 此类不应与项目中的其他类有任何互相调用的逻辑，必须完全做到隔离。
 * AndroidManifest中设置application为此类，而SophixEntry中设为原先Application类。
 * 注意原先Application里不需要再重复初始化Sophix，并且需要避免混淆原先Application类。
 * 如有其它自定义改造，请咨询官方后妥善处理。
 */
public class SophixStubApplication extends SophixApplication {
    private final String TAG = "SophixStubApplication";
    // 此处SophixEntry应指定真正的Application，并且保证RealApplicationStub类名不被混淆。
    @Keep
    @SophixEntry(MyRealApplication.class)
    static class RealApplicationStub {}
    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
//         如果需要使用MultiDex，需要在此处调用。
//         MultiDex.install(this);
        initSophix();
    }
    private void initSophix() {
        String appVersion = new String();
        try {
            appVersion = this.getPackageManager()
                    .getPackageInfo(this.getPackageName(), 0)
                    .versionName;
        } catch (Exception e) {
        }
        final SophixManager instance = SophixManager.getInstance();
        instance.setContext(this)
                .setAppVersion(appVersion)
                .setSecretMetaData("28119490","8c00670f9b76fb3c8418761c4175084e", "MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQCEWa7XS6NaGWj/nGHP3eTL1Uexgg7RM+dA40Jiqz5evyEl53gd6q8/gMVqRXOMibd0LRsqznmMrHGKha+pirMoGhI5lrrCJlMA1SlTGpv0kpAWtYoZPM+QBwH5slOaVn7PsZBeam8UNMr2xuWRSqiTiE3uv5hUyeKWUuTUCxwFVbtG5FFVKdXKoW8R+yghMY8AuVWKyaf89vuv4KR8DwBPlh+9OkRFQDqpLfm2ZD1E+xDQ/hIrHlZJVPgQf0V3hmieYmd97u5amv2jTjBeGjUhH9U63IkOUxAtKt4hTRt8fgb3t/mniM2f6ldDuN+dKMZk0bpIg40l/kUpfgdGuUSvAgMBAAECggEAOoncTnezEbfpfB10GdfASfPU2Qt4eEmnwEiEZVXuqUOKatR9iWXzC3huWPnNCN0k2mrU+FMI6hKfybAlqSyi31rgsr4bbqI61kRyGcLMNhnZR2IWaeE+wSqIUiPw3HPjdQPJoikbZmmPWCVTBrdp5o9EQRRXUTFWWjzh33PmeejyvO09SQYLpdfqUx2sOg7rsXjzYEOzxTXRoaDNOZwBhmz7fs5AWdXuRJCwJmWmhl+xwieuRxjoCBux/1ZEpZ95RrykKx6WBxQ6kdd3dkBphKUHWpoMbkz6fLAe1PMnv7SWsdwknfhM1YKseg6bPhh7b1Ou8mGGshN/RAaCmR42qQKBgQDekQVmbMlNyCcTEORebQaar6OGjuj0bqSgwhjHd28RAPTUVtpGiYUh8+b/BV5vTZROZtN8yBWYCop7k7d9VjE0eogdFwgdlYyYkAK0CT0sjOtZA0Iqjj/+l7GZuke4BorOskuBO3Zewsg1RL0kRcJEylw3vcR8D7/c6enf4Rzo6wKBgQCYO1MPM/sjo+DZFcyMAkyWSupC8vLHfVDWapfWtW4kL8bopb5YkuZulyvBDlswSayUmRWfWc22Qu0xfVojZXeV9NWTTvhl1yHTy7uHX5hILucBjyNB/3B9mv8zqoDEktpNb7puWmshR8UOqEZEdCrM4pgS/XtK9GZL2a6tqEgiTQKBgGcKEsrxkFzfTh/IkUGaDhWtPPK91OHdG4CFe71U8OmZXL+1mgZQAn/OkGH+lVLIQuHtPvolE8I5FxXRNIDlV5BpLfzxvcMcimPTQD0jjdvDPe4O0SIsizoN9p4qHKv482hwKP6ijdGfMdtfgIV7ek4otXrk80NQc04OSUVpzd2TAoGBAJahBMOjTJlsW1ySLE5RLBT+VaQfHsjzn5w55rol1bDqsWecMKcV2/qLjFi/as1x6Nk0TGfbhSgWOvrgLOOfT9/KXdYfqruiTi9yhMEjftvVxPAPAPUZ2uX+CwzFZhqbsHQ03IS65QwOJtck/DpZs8v9XjpUJL367ImbADOGsrF1AoGAWAMX0Qlz0n3ic/OnC3bAwHRApTKSjcZKt9WEXO0WpSKrIFeYTgmy86B4QHkAgZuFluhHG2lF4fgpyaxN2jklJWraOaLpGLowd9xe/fTanoajocEw6B/vpsERHgPM9SSADhGWA6KE2iCQSuxwqhA8/uwbTnMHW1DwDk2H/wtSPIY=")
                .setEnableDebug(true)
                .setEnableFullLog()
                .setPatchLoadStatusStub(new PatchLoadStatusListener() {
                    @Override
                    public void onLoad(final int mode, final int code, final String info, final int handlePatchVersion) {
                        if (code == PatchStatus.CODE_LOAD_SUCCESS) {
                            Log.i(TAG, "sophix load patch success!");
                        } else if (code == PatchStatus.CODE_LOAD_RELAUNCH) {
                            // 如果需要在后台重启，建议此处用SharePreference保存状态。
                            Log.i(TAG, "sophix preload patch success. restart app to make effect.");
                        }
                    }
                }).initialize();
    }
}
