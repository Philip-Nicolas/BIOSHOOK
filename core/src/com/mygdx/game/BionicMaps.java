package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;

/**
 * Created by Philip-Nicolas on 2017-06-04.
 * <p>
 * Stores all of the maps in the maps folder.
 *
 * @author phil.nic
 * @see SceneManager
 */
class BionicMaps {
    String[] mapNames;
    BionicMap[] maps;
    FileHandle[] mapFiles;

    BionicMaps () {
        int length = Gdx.files.local ("maps").list ().length;

        mapFiles = new FileHandle[length];
        mapNames = new String[length];

        for (int i = 0; i < length; i++) {
            if (Gdx.files.local ("maps").list ()[i].name ().equals (".DS_Store")) {
                continue;
            }
            mapNames[i] = Gdx.files.local ("maps").list ()[i].name ();
            mapFiles[i] = Gdx.files.local ("maps").list ()[i].list (".tmx")[0];
        }
    }
}