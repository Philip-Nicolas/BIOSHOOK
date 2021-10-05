package com.mygdx.game.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.mygdx.game.GameLevel2;

/**
 * Launcher for the entire game. Creates a game, and configures the window.
 *
 * @author phil.nic
 */
public class DesktopLauncher1 {

    /**
     * GameLevel3 launcher.
     *
     * @param arg Arguments to run app with.
     */
    public static void main (String[] arg) {
        LwjglApplicationConfiguration config = new LwjglApplicationConfiguration ();

        config.width = 0;
        config.height = 0;

        new LwjglApplication (new GameLevel2 (), config);
    }
}
