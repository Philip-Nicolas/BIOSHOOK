package com.mygdx.game;

import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.physics.box2d.World;


/**
 * Created by Philip-Nicolas on 2017-05-31.
 * <p>
 * Handles an array of red blood cells.
 *
 * @author phil.nic
 * @see RBC
 */
class RBCs extends OrganicCells {

    /**
     * Class constructor.
     *
     * @param world    Box2d world to use.
     * @param map      TiledMap to use.
     * @param maxCells The max amount of living cells.
     */
    RBCs (World world, TiledMap map, int maxCells) {
        super (world, map, maxCells);
    }

    /**
     * Creates a cell to be added to the cells array.
     *
     * @return An RBC
     */
    @Override
    AutomatedSimpleOrganicCell newCell () {
        return new RBC (super.getWorld (), super.getMap (), 1, (int) (Math.random () * 2));
    }
}