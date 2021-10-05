package com.mygdx.game;

import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.physics.box2d.World;

/**
 * Created by Philip-Nicolas on 2017-06-06.
 * <p>
 * Nice virus class.
 *
 * @author phil.nic
 * @see AutomatedSimpleOrganicCell
 */
class NiceVirus extends AutomatedSimpleOrganicCell {

    /**
     * Class constructor.
     *
     * @param world           Box2d world to use.
     * @param map             Map to be used.
     * @param entryPointIndex Entry point to use.
     */
    NiceVirus (World world, TiledMap map, int entryPointIndex, String col) {
        super (world, map, entryPointIndex, (int) (Math.random () * 2), "badguys/viruses/nice/" + col + ".png", true);
    }
}