package com.mygdx.game;

import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.physics.box2d.World;

/**
 * Created by Philip-Nicolas on 2017-06-05.
 * <p>
 * Manager for an array of Antigens.
 *
 * @author phil.nic
 */
class Antigens extends OrganicCells {
    private String col;
    private float difficulty;

    /**
     * Class constructor.
     *
     * @param world    Box2d world to use.
     * @param map      TiledMap to use.
     * @param maxCells The max amount of living cells.
     */
    Antigens (World world, TiledMap map, int maxCells, float difficulty, String col) {
        super (world, map, maxCells);
        this.difficulty = difficulty;
        this.col = col;
    }

    /**
     * Should add a cell to the cells array.
     *
     * @return A new cell to be added to the array.
     */
    @Override
    AutomatedSimpleOrganicCell newCell () {
        if (Math.random () < difficulty) {
            return new MeanVirus (super.getWorld (), super.getMap (), 1, col);
        }
        return new NiceVirus (super.getWorld (), super.getMap (), 1, col);
    }
}
