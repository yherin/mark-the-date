/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mtd.view;

import java.awt.Component;
import java.util.HashMap;

/**
 *
 * @author sjack
 */
public class GUIComponentMap {

    private static HashMap<GUIComponent, Component> map = new HashMap<>();

    public static Component getComponentByEnum(GUIComponent id) {
        return map.get(id);
    }

    public static void setComponentToEnum(GUIComponent id, Component comp) {
        map.put(id, comp);
    }

    public static boolean mapContainsComponent(GUIComponent id) {

        return map.containsKey(id);
    }
}
