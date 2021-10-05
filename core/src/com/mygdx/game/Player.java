package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;

/**
 * Created by Philip-Nicolas on 2017-05-28.
 * <p>
 * Player class. Handles creation of physics body, texture choosing, and player cell mechanics.
 *
 * @author phil.nic
 */
class Player extends OrganicCell {
    private Texture nucleusTexture;
    private float health;
    private float oxygen;
    private float protein;
    private float atp;
    private int pts;

    /**
     * Class constructor.
     *
     * @param world           Physics world to be used.
     * @param map             Map to be used.
     * @param entryPointIndex Entrance point of the player. Refers to object on map.
     * @param size            Starting player size.
     * @param nucleusType     Nucleus skin to be used.
     */
    Player (World world, TiledMap map, int entryPointIndex, int size, String nucleusType) {
        BodyDef bodyDef = new BodyDef ();
        CircleShape circle = new CircleShape ();
        FixtureDef fixtureDef = new FixtureDef ();
        float[] xyCoords = super.initXY (map, entryPointIndex);

        health = 1;
        oxygen = 1;
        atp = 1;
        protein = 1;

        switch (size) {
            default:
            case 0:
                textureSize = 40;
                circle.setRadius (0.52f);
                break;
            case 1:
                textureSize = 56;
                circle.setRadius (0.76f);
                break;
            case 2:
                textureSize = 72;
                circle.setRadius (1f);
                break;
        }

        bodyDef.type = BodyDef.BodyType.DynamicBody;
        bodyDef.position.set (xyCoords[0], xyCoords[1]);
        bodyDef.fixedRotation = true;

        fixtureDef.shape = circle;
        fixtureDef.density = 0.5f;
        fixtureDef.friction = 0f;
        fixtureDef.restitution = 0.8f;

        body = world.createBody (bodyDef);
        body.setUserData (this);
        body.createFixture (fixtureDef);

        System.out.println (body.getUserData ());

        texture = new Texture ("whiteBloodCellBKND.png");
        nucleusTexture = new Texture (nucleusType + "Nucleus.png");

        circle.dispose ();
    }

    /**
     * UI Handler. Applies impulses to player based on keyboard input.
     */
    private void handleUserInput () {
        if (Gdx.input.isKeyPressed (Input.Keys.H) && protein > 0.1f && atp > 0.1 && health != 1) {
            incrementResources ("health", 0.1f);
            protein -= 0.1f;
            atp -= 0.1f;
        }
        if (Gdx.input.isKeyPressed (Input.Keys.LEFT) || Gdx.input.isKeyPressed (Input.Keys.A)) {
            body.applyLinearImpulse (-1f * body.getMass (), 0f, body.getWorldCenter ().x, body.getWorldCenter ().y, true);
            atp -= 0.0001;
        }
        if (Gdx.input.isKeyPressed (Input.Keys.RIGHT) || Gdx.input.isKeyPressed (Input.Keys.D)) {
            body.applyLinearImpulse (1f * body.getMass (), 0f, body.getWorldCenter ().x, body.getWorldCenter ().y, true);
            atp -= 0.0001;
        }
        if (Gdx.input.isKeyPressed (Input.Keys.DOWN) || Gdx.input.isKeyPressed (Input.Keys.S)) {
            body.applyLinearImpulse (0f, -1f * body.getMass (), body.getWorldCenter ().x, body.getWorldCenter ().y, true);
            atp -= 0.0001;
        }
        if (Gdx.input.isKeyPressed (Input.Keys.UP) || Gdx.input.isKeyPressed (Input.Keys.W)) {
            body.applyLinearImpulse (0f, 1f * body.getMass (), body.getWorldCenter ().x, body.getWorldCenter ().y, true);
            atp -= 0.0001;
        }
    }

    /**
     * Does various tasks needed at every cycle.
     */
    void step (TiledMap map) {
        if (map.getProperties ().get ("hasVectors").toString ().equals ("1")) {
            this.addVector (map);
        }
        this.addBrownianMotion ();
        this.handleUserInput ();
        this.dampen ();
        oxygen -= 0.0001;
        atp -= 0.0002;

        atp = atp < 0 ? 0 : atp;
    }

    /**
     * Gives the free space around.
     *
     * @param mainLayer  Map layer to use.
     * @param searchDist Spread of search. Keep low to improve speed. Searches searchDist^2 cells.
     * @return The amount of free space surrounding the player as a ration.
     */
    float surroundingFreeSpaceRatio (TiledMapTileLayer mainLayer, int searchDist) {
        float collidable = 0;
        float open = 0;

        for (int i = -searchDist; i < searchDist; i++) {
            for (int j = -searchDist; j < searchDist; j++) {
                if (mainLayer.getCell ((int) this.getUpdatedCellCoords ().x + i, (int) this.getUpdatedCellCoords ().y + j).getTile ().getProperties ().get ("collidable").toString ().equals ("true")) {
                    collidable += 1;
                } else {
                    open += 1;
                }
            }
        }

        return searchDist != 0 ? open / (collidable + open) : -1f;
    }

    /**
     * Slows down the player.
     */
    @Override
    void dampen () {
        body.setLinearVelocity (body.getLinearVelocity ().scl (0.95f));
    }

    /**
     * Draws the player, and the correct nucleus.
     *
     * @param batch The SpriteBatch to draw to.
     */
    @Override
    void draw (SpriteBatch batch) {
        Vector2 pos = body.getPosition ();
        batch.draw (texture, pos.x * 32f - textureSize / 2, pos.y * 32f - textureSize / 2, textureSize, textureSize);
        batch.draw (nucleusTexture, pos.x * 32f - textureSize / 4, pos.y * 32f - textureSize / 4, textureSize / 2, textureSize / 2);
    }

    void incrementResources (String resource, float amount) {
        switch (resource) {
            case "oxygen":
                oxygen += amount;
                oxygen = oxygen > 1 ? 1 : oxygen;
                break;
            case "glucose":
                atp += amount;
                atp = atp > 1 ? 1 : atp;
                break;
            case "protein":
                protein += amount;
                protein = protein > 1 ? 1 : protein;
                break;
            case "health":
                health += amount;
                health = health > 1 ? 1 : health;
                break;
            default:
        }
    }

    /**
     * Accessor for health. Decrements health. To be used when colliding with mean baddies,
     */
    void takeDamage () {
        health -= 0.03;
    }

    /**
     * Accessor for pts. Adds one to pts.
     */
    void incrementPts () {
        pts++;
    }

    /**
     * Getter for pts.
     *
     * @return The value of pts.
     */
    int getPts () {
        return pts;
    }

    /**
     * Getter for health.
     *
     * @return The value of health.
     */
    float getHealth () {
        return health;
    }

    /**
     * Getter for atp.
     *
     * @return The value of atp.
     */
    float getAtp () {
        return atp;
    }

    /**
     * Getter for oxygen.
     *
     * @return The value of protein.
     */
    float getOxygen () {
        return oxygen;
    }

    /**
     * Getter for protein.
     *
     * @return The value of protein.
     */
    float getProtein () {
        return protein;
    }
}