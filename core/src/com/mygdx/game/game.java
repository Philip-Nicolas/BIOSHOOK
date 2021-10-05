package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.assets.loaders.resolvers.InternalFileHandleResolver;
import com.badlogic.gdx.graphics.FPSLogger;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapRenderer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.HexagonalTiledMapRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;

public class game extends ApplicationAdapter {

    private SpriteBatch batch;
    private TiledMapRenderer renderer;
    private OrthographicCamera camera;
    private TiledMap map;
    private World world;
    private Player player;
    private RBCs rbcs;
    private FPSLogger logger;
    private float zoom;
    private float physicsStep;
    private float cameraX;
    private float cameraY;

    private void moveCamera () {
        if (Gdx.input.isKeyPressed (Input.Keys.O) && zoom > 0.2f) {
            zoom *= 0.9f;
        }

        if (Gdx.input.isKeyPressed (Input.Keys.L) && zoom < 5f) {
            zoom /= 0.9f;
        }

        if (cameraX != player.getX () * 32f) {
            cameraX += (player.getX () * 32f - cameraX) * 0.1;
        }

        if (cameraY != player.getY () * 32f) {
            cameraY += (player.getY () * 32f - cameraY) * 0.2;
        }

        camera.position.set (cameraX, cameraY, 1f);
        camera.zoom = zoom;
    }

    private void physicsStepControl () {
        if (Gdx.input.isKeyPressed (Input.Keys.C) && physicsStep > 1) {
            physicsStep -= 1f;
        }

        if (Gdx.input.isKeyPressed (Input.Keys.X)) {
            physicsStep = 60;
        }

        if (Gdx.input.isKeyPressed (Input.Keys.Z) && physicsStep < 120) {
            physicsStep += 1f;
        }
    }

    @Override
    public void resize (int width, int height) {
        super.resize (width, height);
        camera.setToOrtho (false, Gdx.graphics.getWidth (), Gdx.graphics.getHeight ());
        camera.position.set (player.getX () * 32f, player.getY () * 32f, 1f);
    }

    @Override
    public void create () {
        AssetManager assetManager = new AssetManager ();
        logger = new FPSLogger ();

        zoom = 0.5f;
        physicsStep = 60;
        camera = new OrthographicCamera ();
        camera.setToOrtho (false, Gdx.graphics.getWidth (), Gdx.graphics.getHeight ());
        camera.zoom = zoom;
        batch = new SpriteBatch ();
        world = new World (new Vector2 (0f, 0f), true);

        assetManager.setLoader (TiledMap.class, new TmxMapLoader (new InternalFileHandleResolver ()));
        assetManager.load ("maps/arteries/arteries.tmx", TiledMap.class);
        assetManager.finishLoading ();

        map = assetManager.get ("maps/arteries/arteries.tmx");

        new TMXToBox2d (world, map).populateBox2dWorld ();

        player = new Player (world, map, 1, 2, "neutrophil");
        rbcs = new RBCs (world, map, 500);

        renderer = new HexagonalTiledMapRenderer (map, 1f);
    }

    @Override
    public void render () {
        logger.log ();
        Gdx.gl.glClear (GL20.GL_COLOR_BUFFER_BIT);

        player.handleUserInput ();
        player.dampen ();
        player.addVector (map);
        rbcs.step (player, 35f * zoom);
        physicsStepControl ();
        moveCamera ();

        camera.update ();

        renderer.setView (camera);
        renderer.render ();
        batch.setProjectionMatrix (camera.combined);
        batch.begin ();
        {
            rbcs.draw (batch);
            player.draw (batch);
        }
        batch.end ();

        world.step (1 / physicsStep, 6, 2);
    }

    @Override
    public void dispose () {
        batch.dispose ();
    }
}