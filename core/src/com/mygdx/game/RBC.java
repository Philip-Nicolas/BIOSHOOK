package com.mygdx.game;

import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.physics.box2d.World;

/**
 * Created by Philip-Nicolas on 2017-05-31.
 * <p>
 * Basic Red Blood Cell config for AutomatedSimpleOrganicCell
 *
 * @author phil.nic
 * @see AutomatedSimpleOrganicCell
 */
class RBC extends AutomatedSimpleOrganicCellWithResources {
    /**
     * Class constructor.
     *
     * @param world           Box2d world to be used.
     * @param map             TiledMap to be used.
     * @param entryPointIndex Spawn index of cell.
     * @param size            Initial size of cell.
     */
    RBC (World world, TiledMap map, int entryPointIndex, int size) {
        super (world, map, entryPointIndex, size, "oxygen", "redBloodCell.png", true);
    }
}