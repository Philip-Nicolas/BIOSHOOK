package com.mygdx.game;

import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.physics.box2d.World;

/**
 * Created by Philip-Nicolas on 2017-06-04.
 *
 * @author phil.nic
 */
public class AutomatedSimpleOrganicCellWithResources extends AutomatedSimpleOrganicCell {
    String resourceType;
    float resourceAmt;

    AutomatedSimpleOrganicCellWithResources (World world, TiledMap map, int entryPointIndex, int size, String resourceType, String texture, boolean fixedRotation) {
        super (world, map, entryPointIndex, size, texture, fixedRotation);

        this.resourceType = resourceType;

        switch (size) {
            default:
            case 0:
                resourceAmt = 0.5f;
                break;
            case 1:
                resourceAmt = 0.7f;
                break;
            case 2:
                resourceAmt = 1f;
        }
    }

    float giveResources () {
        float amt = resourceAmt * 0.1f;
        resourceAmt *= 0.9;
        return amt;
    }

    String getResourceType () {
        return resourceType;
    }
}
