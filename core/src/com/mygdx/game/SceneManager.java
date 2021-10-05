package com.mygdx.game;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.TiledMapRenderer;

import java.util.jar.Attributes;

/**
 * Created by Philip-Nicolas on 2017-06-04.
 * <p>
 * Manages map, level, camera, and player data.
 *
 * @author phil.nic
 */
class SceneManager {
    private Attributes currentSave;
    private OrthographicCamera camera;

    private RBCs rbcs;
    private GlucoseTransporters glucoseTransporters;
    private Proteins proteins;


    SceneManager (Attributes currentSave, OrthographicCamera camera) {
        this.currentSave = currentSave;
        this.camera = camera;
    }

    void draw (TiledMapRenderer renderer, SpriteBatch batch) {
        renderer.setView (camera);

        renderer.render ();
        proteins.draw (batch);
        glucoseTransporters.draw (batch);
        rbcs.draw (batch);
    }

    void switchScenes () {

    }
}