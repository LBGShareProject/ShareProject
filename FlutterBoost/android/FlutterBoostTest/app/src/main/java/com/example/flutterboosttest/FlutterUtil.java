package com.example.flutterboosttest;

import android.app.Application;
import android.widget.Toast;

import com.idlefish.flutterboost.EventListener;
import com.idlefish.flutterboost.FlutterBoost;
import com.idlefish.flutterboost.ListenerRemover;

import java.util.Map;

import io.flutter.Log;

/**
 * @author wangguanghan
 * date:2022/7/27
 * Description:
 */
public class FlutterUtil {
    public final static String methodChannel = "flutter.huangye.wuba.com:merchant";
    public final static String pushShowToast = "pushShowToast";
    public final static String channelShowToast = "channelShowToast";

    public static void initFlutter(Application application) {
        EventListener listener = (key, args) -> {
            //deal with your event here
            Log.e("EventListener  -key-   ", key);
            FlutterUtil.handleEvent(args.get("event").toString(),args);
        };
        ListenerRemover remover = FlutterBoost.instance().addEventListener(FlutterUtil.methodChannel, listener);
        remover.remove();
    }

    public static void handleEvent(String key, Map<Object, Object> args){
        switch (key){
            case pushShowToast:
                Log.e("EventListener  -args-   ", args.size()+"");
                Log.e("EventListener  -args-   ", args.toString());
                Map map = (Map) args.get("args");
                Log.e("EventListener  -map-   ", map.size()+"");
                Log.e("EventListener  -map-   ", map.toString());
                Map args1 = (Map)map.get("args");
                String params = args1.get("params").toString();
                Log.e("EventListener  -params-   ", params);
                Toast.makeText(MyApplication.getInstance(), params, Toast.LENGTH_SHORT).show();
                break;
            case channelShowToast:
                Log.e("EventListener  -args-   ", args.size()+"");
                Log.e("EventListener  -args-   ", args.toString());
                Map channelMap = (Map) args.get("args");
                Log.e("EventListener  -map-   ", channelMap.size()+"");
                Log.e("EventListener  -map-   ", channelMap.toString());
                String channelParams = channelMap.get("params").toString();
                Log.e("EventListener  -params-   ", channelParams);
                Toast.makeText(MyApplication.getInstance(), channelParams, Toast.LENGTH_SHORT).show();
                break;
            default:
                break;
        }
    }
}
