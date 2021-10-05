package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.objects.PolygonMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Polygon;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;

/**
 * Created by Philip-Nicolas on 2017-05-30.
 * <p>
 * Offers basic functionality for cells and viruses.
 *
 * @author phil.nic
 */
class OrganicCell {
    int textureSize;
    Body body;
    Texture texture;

    /**
     * Places the cell within a spawn polygon.
     *
     * @param map             Map to be used to lookup polygon.
     * @param entryPointIndex Entrance to use.
     * @return The coordinates where the cell should be placed.
     */
    float[] initXY (TiledMap map, int entryPointIndex) {
        float[] coords = new float[2];
        try {
            float[] polygonVertices = ((PolygonMapObject) map.getLayers ().get ("enterExitLayer").getObjects ().get ("inHeart" + entryPointIndex)).getPolygon ().getTransformedVertices ();

            Polygon polygon = new Polygon (polygonVertices);
            Rectangle polygonBoundingBox = polygon.getBoundingRectangle ();

            float startX = polygonBoundingBox.getX ();
            float startY = polygonBoundingBox.getY ();
            float width = polygonBoundingBox.getWidth ();
            float height = polygonBoundingBox.getHeight ();

            do {
                coords[0] = (float) Math.random () * width + startX;
                coords[1] = (float) Math.random () * height + startY;
            } while (!Intersector.isPointInPolygon (polygonVertices, 0, polygonVertices.length, coords[0], coords[1]));

            coords[0] /= 32f;
            coords[1] /= 32f;
        } catch (NullPointerException ignored) {
            float[] polygonVertices = ((PolygonMapObject) map.getLayers ().get ("enterExitLayer").getObjects ().get ("inArteries" + entryPointIndex)).getPolygon ().getTransformedVertices ();

            Polygon polygon = new Polygon (polygonVertices);
            Rectangle polygonBoundingBox = polygon.getBoundingRectangle ();

            float startX = polygonBoundingBox.getX ();
            float startY = polygonBoundingBox.getY ();
            float width = polygonBoundingBox.getWidth ();
            float height = polygonBoundingBox.getHeight ();

            do {
                coords[0] = (float) Math.random () * width + startX;
                coords[1] = (float) Math.random () * height + startY;
            } while (!Intersector.isPointInPolygon (polygonVertices, 0, polygonVertices.length, coords[0], coords[1]));

            coords[0] /= 32f;
            coords[1] /= 32f;
        }

        return coords;
    }

    /**
     * Adds the proper vectors from the map to the cell.
     *
     * @param map Map to be used.
     */
    void addVector (TiledMap map) {
        TiledMapTileLayer.Cell cell = ((TiledMapTileLayer) map.getLayers ().get ("vectorLayer")).getCell ((int) getUpdatedCellCoords ().x, (int) getUpdatedCellCoords ().y);
        Vector2 centerOfMass = body.getWorldCenter ();

        try {
            switch (Integer.parseInt (cell.getTile ().getProperties ().get ("direction").toString ())) {
                case 0:
                    body.applyLinearImpulse (0f, -0.4f, centerOfMass.x, centerOfMass.y, true);
                    break;
                case 1:
                    body.applyLinearImpulse (0.3464f, -0.2f, centerOfMass.x, centerOfMass.y, true);
                    break;
                case 2:
                    body.applyLinearImpulse (0.3464f, 0.2f, centerOfMass.x, centerOfMass.y, true);
                    break;
                case 3:
                    body.applyLinearImpulse (0f, 0.4f, centerOfMass.x, centerOfMass.y, true);
                    break;
                case 4:
                    body.applyLinearImpulse (-0.3464f, 0.2f, centerOfMass.x, centerOfMass.y, true);
                    break;
                case 5:
                    body.applyLinearImpulse (-0.3464f, -0.2f, centerOfMass.x, centerOfMass.y, true);
                    break;
                default:
            }
        } catch (NumberFormatException numErr) {
            numErr.printStackTrace (System.out);
        } catch (NullPointerException noSuchCell) {
            /* This is normal. Probably. */
        }
    }


    /**
     * Adds random movement to the cell.
     */
    void addBrownianMotion () {
        Vector2 centerOfMass = body.getWorldCenter ();
        body.applyLinearImpulse ((float) (Math.random () - 0.5), (float) (Math.random () - 0.5), centerOfMass.x, centerOfMass.y, true);
    }

    /**
     * Getter for updated coordinates of cell.
     *
     * @return The coordinates of the cell that the cell is in.
     */
    Vector2 getUpdatedCellCoords () {
        return new Vector2 ((int) ((this.getX () - 1f) * 32 / 97), (int) ((this.getY () * 32f) / 112f));
    }

    /**
     * Slows down the cell.
     */
    void dampen () {
        body.setLinearVelocity (body.getLinearVelocity ().scl (0.99f));
    }

    /**
     * Gets the physics X position of the body.
     *
     * @return physics X position of the body.
     */
    float getX () {
        return body.getPosition ().x;
    }

    /**
     * Gets the physics Y position of the body.
     *
     * @return physics Y position of the body.
     */
    float getY () {
        return body.getPosition ().y;
    }

    /**
     * Gets the physics position of the body.
     *
     * @return physics position of the body.
     */
    Vector2 getPosition () {
        return body.getPosition ();
    }

    void draw (SpriteBatch batch) {
        Vector2 pos = body.getPosition ();
        batch.draw (texture, pos.x * 32f - textureSize / 2, pos.y * 32f - textureSize / 2, textureSize, textureSize);
    }
}