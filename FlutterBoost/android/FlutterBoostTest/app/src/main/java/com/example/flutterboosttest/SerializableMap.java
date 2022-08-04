package com.example.flutterboosttest;

import java.io.Serializable;
import java.util.Map;

/**
 * @author wangguanghan
 * date:2022/7/27
 * Description:
 */
public class SerializableMap implements Serializable {
    private Map map;

    public Map getMap() {

        return map;

    }

    public void setMap(Map map) {

        this.map = map;

    }
}
