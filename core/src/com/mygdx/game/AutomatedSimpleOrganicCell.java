package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.objects.PolygonMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.World;

import java.util.ArrayList;

/**
 * Created by Philip-Nicolas on 2017-06-04.
 * <p>
 * Template for simple, non-playable OrganicCells
 *
 * @author phil.nic
 */
class AutomatedSimpleOrganicCell extends OrganicCell {
    private boolean toBeKilled;

    /**
     * Class constructor.
     *
     * @param world           Box2d world to use.
     * @param map             Map to be used.
     * @param entryPointIndex Entry point to use.
     * @param size            Initial size of cell.
     */
    AutomatedSimpleOrganicCell (World world, TiledMap map, int entryPointIndex, int size, String texture, boolean fixedRotation) {
        toBeKilled = false;

        BodyDef bodyDef = new BodyDef ();
        CircleShape circle = new CircleShape ();
        FixtureDef fixtureDef = new FixtureDef ();
        float[] xyCoords = super.initXY (map, entryPointIndex);

        switch (size) {
            default:
            case 0:
                textureSize = 40;
                circle.setRadius (0.43f);
                break;
            case 1:
                textureSize = 56;
                circle.setRadius (0.60f);
                break;
            case 2:
                textureSize = 72;
                circle.setRadius (0.77f);
                break;
        }

        bodyDef.type = BodyDef.BodyType.DynamicBody;
        bodyDef.position.set (xyCoords[0], xyCoords[1]);
        bodyDef.fixedRotation = fixedRotation;

        fixtureDef.shape = circle;
        fixtureDef.density = 0.5f;
        fixtureDef.friction = 0f;
        fixtureDef.restitution = 0.5f;

        body = world.createBody (bodyDef);
        body.setUserData (this);
        body.createFixture (fixtureDef);

        this.texture = new Texture (texture);

        circle.dispose ();
    }

    /**
     * Returns cells that have left the effective range to a random position within it, and decide whether of not it should be killed.
     *
     * @param map    TiledMap to be used.
     * @param player Player to move RBCs relative to.
     * @param dist   The effective distance to check.
     */
    void returnToPlayer (TiledMap map, Player player, float dist) {
        PolygonMapObject temp;

        try {
            if (((TiledMapTileLayer) map.getLayers ().get ("mainLayer")).getCell ((int) getUpdatedCellCoords ().x, (int) getUpdatedCellCoords ().y).getTile ().getProperties ().get ("collidable").toString ().equals ("true")) {
                toBeKilled = true;
                return;
            }
        } catch (NullPointerException npe) {
            toBeKilled = true;
            return;
        }

        for (MapObject object : map.getLayers ().get ("enterExitLayer").getObjects ()) {
            if ((int) (Math.random () * 100) == 0 && object.getProperties ().get ("type").toString ().equals ("out")) {
                temp = ((PolygonMapObject) object);
                if (new Vector2 (temp.getPolygon ().getX (), temp.getPolygon ().getY ()).dst (body.getPosition ().scl (32f)) < 2000f) {
                    if (Intersector.isPointInPolygon (temp.getPolygon ().getTransformedVertices (), 0, temp.getPolygon ().getTransformedVertices ().length, this.getX () * 32f, this.getY () * 32f)) {
                        toBeKilled = true;
                        return;
                    }
                }
            }
        }

        if (player.getPosition ().dst (this.getPosition ()) > dist) {
            try {
                body.setTransform (player.getX () + (float) (Math.random () * dist - dist / 2), player.getY () + (float) (Math.random () * dist - dist / 2), 0f);
                if (((TiledMapTileLayer) map.getLayers ().get ("mainLayer")).getCell ((int) getUpdatedCellCoords ().x, (int) getUpdatedCellCoords ().y).getTile ().getProperties ().get ("collidable").toString ().equals ("true")) {
                    toBeKilled = true;
                }
            } catch (NullPointerException npe) {
                toBeKilled = true;
            }
        }
    }

    /**
     * Getter for toBeKilled.
     *
     * @return The value of toBeKilled.
     */
    boolean isToBeKilled () {
        return toBeKilled;
    }

    /**
     * Sets toBeKilled to true. Used if a subclass needs to introduce a death condition.
     */
    void toBeKilled () {
        toBeKilled = true;
    }
}