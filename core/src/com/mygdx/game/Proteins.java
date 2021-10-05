package com.mygdx.game;

import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.physics.box2d.World;

/**
 * Created by Philip-Nicolas on 2017-06-04.
 *
 * Manager for an array of proteins.
 *
 * @author phil.nic
 */
public class Proteins extends OrganicCells{
    /**
     * Class constructor.
     *
     * @param world    Box2d world to use.
     * @param map      TiledMap to use.
     * @param maxCells The max amount of living cells.
     */
    Proteins (World world, TiledMap map, int maxCells) {
        super (world, map, maxCells);
    }

    /**
     * Should add a cell to the cells array.
     *
     * @return A new cell to be added to the array.
     */
    @Override
    AutomatedSimpleOrganicCell newCell () {
        return new Protein (super.getWorld (), super.getMap (), 1);
    }
}