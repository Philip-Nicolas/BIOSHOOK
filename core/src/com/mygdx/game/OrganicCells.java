package com.mygdx.game;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.physics.box2d.World;

import java.util.ArrayList;

/**
 * Created by Philip-Nicolas on 2017-06-04.
 * <p>
 * Manager for a group of OrganicCells.
 *
 * @author phil.nic
 */
abstract class OrganicCells {
    private int cellMultiplier;
    private World world;
    private TiledMap map;
    private ArrayList<AutomatedSimpleOrganicCell> cells;

    /**
     * Class constructor.
     *
     * @param world    Box2d world to use.
     * @param map      TiledMap to use.
     * @param maxCells The max amount of living cells.
     */
    OrganicCells (World world, TiledMap map, int maxCells) {
        this.world = world;
        this.map = map;
        this.cellMultiplier = maxCells / 5;

        cells = new ArrayList<AutomatedSimpleOrganicCell> ();
    }

    /**
     * Draws all the active RBCs.
     *
     * @param batch The SpriteBatch to draw to.
     */
    void draw (SpriteBatch batch) {
        for (AutomatedSimpleOrganicCell cell : cells) {
            cell.draw (batch);
        }
    }

    /**
     * Does all the required mid-step calculations.
     *
     * @param player Required for the returnToPlayer method.
     */
    void step (Player player, float zoom) {
        ArrayList<AutomatedSimpleOrganicCell> cellsToRemove = new ArrayList<AutomatedSimpleOrganicCell> ();
        int maxCells = (int) (player.surroundingFreeSpaceRatio ((TiledMapTileLayer) map.getLayers ().get ("mainLayer"), 4) * cellMultiplier * zoom);

        for (AutomatedSimpleOrganicCell cell : cells) {
            cell.returnToPlayer (map, player, zoom * 35f);
            if (map.getProperties ().get ("hasVectors").toString ().equals ("1")) {
                cell.addVector (map);
            }
            cell.addBrownianMotion ();
            cell.dampen ();
            if (cell.isToBeKilled ()) {
                world.destroyBody (cell.body);
                cellsToRemove.add (cell);
            }
        }

        for (AutomatedSimpleOrganicCell cell : cellsToRemove) {
            cells.remove (cell);
        }

        if (cells.size () < maxCells) {
            cells.add (newCell ());
        }
    }

    /**
     * Getter for the cells array.
     *
     * @return The cells array.
     */
    ArrayList<AutomatedSimpleOrganicCell> getCells () {
        return cells;
    }

    /**
     * Should add a cell to the cells array.
     *
     * @return A new cell to be added to the array.
     */
    abstract AutomatedSimpleOrganicCell newCell ();

    /**
     * Getter for getCellMultiplier.
     *
     * @return The value of getCellMultiplier.
     */
    int getCellMultiplier () {
        return cellMultiplier;
    }

    /**
     * Getter for getMap.
     *
     * @return The value of getMap.
     */
    TiledMap getMap () {
        return map;
    }

    /**
     * Getter for getWorld.
     *
     * @return The value of getWorld.
     */
    World getWorld () {
        return world;
    }
}