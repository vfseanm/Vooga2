package editor;

import java.awt.Color;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.golden.gamedev.engine.BaseIO;
import com.golden.gamedev.engine.BaseLoader;
import com.google.gson.Gson;

import platforms.AbstractPlatform;
import powerUps.PowerUp;

import sprite.Enemy;

public class Level {

    private String name;

    private List<Enemy> enemies;

    private List<AbstractPlatform> platforms;

    private List<PowerUp> powerUps;

    public String makeJsonString() {
        Gson gson = new Gson();
        HashMap<String, ArrayList<String>> map = new HashMap<String, ArrayList<String>>();
        /*
         * ArrayList<String> enemyJSONList = new ArrayList<String>(); for(Enemy
         * e: enemies) {
         * 
         * }
         */
        ArrayList<String> platformJsonList = new ArrayList<String>();
        for (AbstractPlatform p : platforms) {
            platformJsonList.add(p.makeJsonString());
        }
        map.put(AbstractPlatform.class.toString(), platformJsonList);

        return gson.toJson(map);

    }

}
