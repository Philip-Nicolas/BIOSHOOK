package com.badlogic.mygame4.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.badlogic.mygame4.InstructionsTest;
import com.badlogic.mygame4.Level1MenuTest;
import com.badlogic.mygame4.Level2MenuTest;
import com.badlogic.mygame4.Level3MenuTest;
import com.badlogic.mygame4.MainMenuTest;
import com.badlogic.mygame4.SkinTest;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		//new LwjglApplication(new MainMenu(), config);
		//new LwjglApplication(new Instructions(), config);
		//new LwjglApplication(new Level1Menu(), config);
		//new LwjglApplication(new Level2Menu(), config);
		//new LwjglApplication(new Level3Menu(), config);
		
		//new LwjglApplication(new MainMenuTest(), config);
		new LwjglApplication(new InstructionsTest(), config);
		//new LwjglApplication(new Level1MenuTest(), config);
		//new LwjglApplication(new Level2MenuTest(), config);
		//new LwjglApplication(new Level3MenuTest(), config);
	}
}
