package com.example.smartcardpicker;

import static androidx.camera.camera2.Camera2Config.*;

import android.app.Application;

import androidx.camera.camera2.Camera2Config;
import androidx.camera.core.CameraXConfig;

public class MyCameraConfig extends Application implements CameraXConfig.Provider {
    @Override
    public CameraXConfig getCameraXConfig(){
        return Camera2Config.defaultConfig();
    }
}
