package com.mygdx.game;

import com.badlogic.gdx.maps.tiled.TiledMap;

/**
 * Created by Philip-Nicolas on 2017-06-04.
 *
 * Map class that contains the various info used to manage a scene.
 *
 * @author phil.nic
 */
abstract class BionicMap {
    TiledMap map;

    abstract void populate (RBCs rbcs, GlucoseTransporters glucoseTransporters, Proteins proteins);
}