package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.FileSystemNotFoundException;
import java.util.NoSuchElementException;
import java.util.jar.Attributes;

/**
 * Created by Philip-Nicolas on 2017-06-03.
 * <p>
 * Allows users to read and write save files, and get and modify settings.
 *
 * @author phil.nic
 */
public class SaveGame {
    private Attributes attributes;
    private final String[] ALLOWED_ATTRIBUTES = new String[]{"name", "difficulty", "maxlevel", "points", "level", "xcoord", "ycoord", "kills", "health", "oxygen", "energy", "protein", "size", "time"};

    /**
     * Sets an attribute in the active Attributes.
     *
     * @param name  The name of the attribute to set.
     * @param value The value to set the attribute to.
     * @throws NoSuchElementException When the named attribute is invalid.
     */
    void setAttribute (String name, String value) throws NoSuchElementException {
        boolean attributeAllowed = false;
        for (String attribute : ALLOWED_ATTRIBUTES) {
            if (name.equals (attribute)) {
                attributeAllowed = true;
                break;
            }
        }
        if (!attributeAllowed) {
            throw new NoSuchElementException ("The attribute you are trying to set does not exist.");
        }

        attributes.put (name, value);
    }

    /**
     * Looks up all available save files to be loaded.
     *
     * @return All of the names of save files.
     * @throws FileSystemNotFoundException When local storage is unavailable
     */
    String[] getSaveNames () throws FileSystemNotFoundException {
        String[] names;

        if (!Gdx.files.isLocalStorageAvailable ()) {
            throw new FileSystemNotFoundException ("Local storage is unavailable on your platform.");
        }

        names = new String[Gdx.files.local ("saves").list ().length];

        for (int i = 0; i < Gdx.files.local ("saves").list ().length; i++) {
            names[i] = Gdx.files.local ("saves").list ()[i].name ();
        }

        return names;
    }

    /**
     * Writes a save to a save file.
     *
     * @param name The name of the save file.
     * @throws FileSystemNotFoundException When local storage is not supported.
     */
    void writeSaveFile (String name) throws FileSystemNotFoundException {
        FileHandle saveFile;

        if (!Gdx.files.isLocalStorageAvailable ()) {
            throw new FileSystemNotFoundException ("Local storage is unavailable on your platform.");
        }

        /* create or access save file */
        saveFile = Gdx.files.local ("saves/" + name);

        for (String attribute : saveFile.readString ().split ("\n")) {
            saveFile.writeString (attribute.split ("=")[0] + "=" + attribute.split ("=")[1], true);
        }
    }

    /**
     * Reads a save from a save file. If an attribute is incorrect, it will be skipped.
     *
     * @param name The name of the save to be read.
     * @throws FileSystemNotFoundException When local storage is not supported.
     * @throws FileNotFoundException       When the specified save file is not found.
     * @throws IOException                 When the file is corrupted.
     */
    void readSaveFile (String name) throws FileSystemNotFoundException, IOException {
        FileHandle saveFile = null;
        boolean skip;

        if (!Gdx.files.isLocalStorageAvailable ()) {
            throw new FileSystemNotFoundException ("Local storage is unavailable on your platform.");
        }

        for (FileHandle save : Gdx.files.local ("saves").list ()) {
            if (save.name ().equals (name)) {
                saveFile = save;
                break;
            }
        }

        /* if file is not found */
        if (saveFile == null) {
            throw new FileNotFoundException ("No such save file.");
        }

        try {
            /* clear current attributes, just in case */
            attributes = new Attributes ();

            /* gets and stores all attributes from file */
            for (String attribute : saveFile.readString ().split ("\n")) {
                skip = true;
                for (String allowedAttribute : ALLOWED_ATTRIBUTES) {
                    if (attribute.equals (allowedAttribute)) {
                        skip = false;
                        break;
                    }
                }

                if (!skip) {
                    attributes.put (attribute.split ("=")[0], attribute.split ("=")[1]);
                }
            }
        } catch (ArrayIndexOutOfBoundsException arrErr) {
            throw new IOException ("The specified save file seems to be corrupted.");
        }
    }

    /**
     * Gets an attribute from the currently loaded save.
     *
     * @param name The name of the requested attribute.
     * @return The value of the attribute requested.
     * @throws NoSuchElementException When the attribute requested does not exist.
     */
    String getValueOfAttribute (String name) throws NoSuchElementException {
        if (!attributes.containsKey (name)) {
            throw new NoSuchElementException ("There is no such attribute.");
        }

        return attributes.getValue (name);
    }

    /**
     * Getter for attributes.
     *
     * @return attributes.
     */
    Attributes getAttributes () {
        return attributes;
    }
}