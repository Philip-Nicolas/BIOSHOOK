package com.mygdx.game;

import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.physics.box2d.World;

/**
 * Created by Philip-Nicolas on 2017-06-04.
 * <p>
 * Handles an array of GlucoseTransporter cells.
 *
 * @author phil.nic
 * @see GlucoseTransporter
 */
class GlucoseTransporters extends OrganicCells {

    GlucoseTransporters (World world, TiledMap map, int maxCells) {
        super (world, map, maxCells);
    }

    /**
     * Should add a cell to the cells array.
     *
     * @return A new cell to be added to the array.
     */
    @Override
    AutomatedSimpleOrganicCell newCell () {
        return new GlucoseTransporter (super.getWorld (), super.getMap (), 1, (int) (Math.random () * 2));
    }
}