package com.mygdx.game;

import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.physics.box2d.*;

import java.util.ArrayList;

/**
 * Created by Philip-Nicolas on 2017-05-28.
 * Takes a TiledMap with a mainLayer, vectorLayer, and enterExitLayer, and populates a box2d world with the data.
 * <p>
 * Tiles in the mainLayer shall have a property named "collidable", lest the program crash.
 *
 * @author phil.nic
 */
class TMXToBox2d {

    private TiledMap map;
    private World world;

    /**
     * Class constructor.
     *
     * @param world Box2d world to populate.
     * @param map   Map to be used.
     */
    TMXToBox2d (World world, TiledMap map) {
        this.world = world;
        this.map = map;
    }

    /**
     * Takes info from map provided, and adds it to the box2d world.
     */
    void populateBox2dWorld () {
        BodyDef wallBodyDef;
        PolygonShape polygon;

        TiledMapTileLayer mainLayer = (TiledMapTileLayer) map.getLayers ().get ("mainLayer");

        ArrayList<int[]> cellsToBeCollidableCoords = new ArrayList<int[]> ();

        /* loop through main layer and find cells that need to be collidable */
        for (int x = 0; x < mainLayer.getWidth (); x++) {
            for (int y = 0; y < mainLayer.getHeight (); y++) {
                if (shouldBeCollidable (mainLayer, x, y)) {
                    cellsToBeCollidableCoords.add (new int[]{x, y});
                }
            }
        }

        for (int[] cellCoords : cellsToBeCollidableCoords) {
            Body cellBody;

            wallBodyDef = new BodyDef ();
            polygon = new PolygonShape ();

            wallBodyDef.type = BodyDef.BodyType.StaticBody;

            float xOffset = 1f;

            float xSpace = 97f;
            float ySpace = 112f;
            wallBodyDef.position.set ((cellCoords[0] * xSpace) / 32f + xOffset, (cellCoords[1] * ySpace + (cellCoords[0] % 2 == 0 ? ySpace / 2 : 0)) / 32f);

            float size = 31.7f;
            polygon.set (new float[]{0f / size, 0f / size, -32f / size, 55f / size, 0f / size, 111f / size, 64f / size, 111f / size, 96f / size, 55f / size, 64f / size, 0f / size});

            cellBody = world.createBody (wallBodyDef);
            cellBody.createFixture (polygon, 0f);

            polygon.dispose ();
        }
    }

    /**
     * Determines whether collisions should be enabled for that cell.
     *
     * @param layer Tile layer of the cell to verify.
     * @param tileX The x coordinate of the cell to verify.
     * @param tileY The y coordinate of the cell to verify.
     * @return Whether the cell should have collisions enabled.
     */
    private boolean shouldBeCollidable (TiledMapTileLayer layer, int tileX, int tileY) {
        TiledMapTileLayer.Cell cell = layer.getCell (tileX, tileY);
        ArrayList<int[]> cellCoordsToCheck;

        /* check for collidable in questioned cell */
        if (cell.getTile ().getProperties ().get ("collidable").toString ().equals ("false")) {
            return false;
        }

        /* init the array of other cells to check */
        cellCoordsToCheck = new ArrayList<int[]> ();

        /* Verifications for map edges, and addition to array of cells to check */
        if (tileY > 0) {
            cellCoordsToCheck.add (new int[]{tileX, tileY - 1});
        }

        if (tileY + 1 < layer.getHeight ()) {
            cellCoordsToCheck.add (new int[]{tileX, tileY + 1});
        }

        if (tileX > 0) {
            cellCoordsToCheck.add (new int[]{tileX - 1, tileY});
        }

        if (tileX + 1 < layer.getWidth ()) {
            cellCoordsToCheck.add (new int[]{tileX + 1, tileY});
        }

        if (tileX % 2 == 1) {
            if (tileX > 0 && tileY > 0) {
                cellCoordsToCheck.add (new int[]{tileX - 1, tileY - 1});
            }

            if (tileX + 1 < layer.getWidth () && tileY > 0) {
                cellCoordsToCheck.add (new int[]{tileX + 1, tileY - 1});
            }
        } else {
            if (tileX > 0 && tileY + 1 < layer.getHeight ()) {
                cellCoordsToCheck.add (new int[]{tileX - 1, tileY + 1});
            }

            if (tileX + 1 < layer.getWidth () && tileY + 1 < layer.getHeight ()) {
                cellCoordsToCheck.add (new int[]{tileX + 1, tileY + 1});
            }
        }

        /* verify each adjacent cell; if not collidable, this one should be */
        for (int[] cellToCheck : cellCoordsToCheck) {
            if (layer.getCell (cellToCheck[0], cellToCheck[1]).getTile ().getProperties ().get ("collidable").toString ().equals ("false")) {
                return true;
            }
        }

        /* no adjacent free cells; this one should be disregarded */
        return false;
    }
}