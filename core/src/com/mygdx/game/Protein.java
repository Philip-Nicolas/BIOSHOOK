package com.mygdx.game;

import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.physics.box2d.World;

/**
 * Created by Philip-Nicolas on 2017-06-04.
 *
 * Edible protein class.
 *
 * @author phil.nic
 */
public class Protein extends AutomatedSimpleOrganicCellWithResources {
    Protein (World world, TiledMap map, int entryPointIndex) {
        super (world, map, entryPointIndex, 0, "protein", "protein.png", false);
    }
}
