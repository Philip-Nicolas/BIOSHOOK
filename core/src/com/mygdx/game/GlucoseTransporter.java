package com.mygdx.game;

import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.physics.box2d.World;

/**
 * Created by Philip-Nicolas on 2017-06-04.
 * <p>
 * Glucose transporting cell. Behaves the same as an RBC, but carries glucose.
 */
class GlucoseTransporter extends AutomatedSimpleOrganicCellWithResources {
    GlucoseTransporter (World world, TiledMap map, int entryPointIndex, int size) {
        super (world, map, entryPointIndex, size, "glucose", "glucoseTransporter.png", false);
    }

    /**
     * Slows down the cell.
     */
    @Override
    void dampen () {
        body.setLinearVelocity (body.getLinearVelocity ().scl (0.93f));
    }
}