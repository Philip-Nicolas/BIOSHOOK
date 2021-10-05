package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.Disposable;

/**
 * Created by Philip-Nicolas on 2017-06-04.
 * <p>
 * Overlay for the GameLevel3 screen which contains the info box, stats, and popups.
 *
 * @author phil.nic
 */
class GameHUD implements Disposable {
    private BitmapFont font;
    private ShapeRenderer shapeRenderer;
    private Player player;

    GameHUD (Player player) {
        this.player = player;

        font = new BitmapFont (Gdx.files.local ("fonts/nexa.fnt"));
        font.setColor (Color.GREEN);
        shapeRenderer = new ShapeRenderer ();
    }

    void draw (SpriteBatch batch, OrthographicCamera camera) {
        float width = camera.viewportWidth;
        float height = camera.viewportHeight;
        float zoom = camera.zoom;

        float boxHeight = height / 5;

        batch.end ();

        Gdx.gl.glEnable (GL20.GL_BLEND);
        shapeRenderer.setProjectionMatrix (camera.projection);

        shapeRenderer.begin (ShapeRenderer.ShapeType.Filled);
        {
            shapeRenderer.setColor (0, 0, 0, 0.3f);
            shapeRenderer.rect (-width / 2 * zoom, -height / 2 * zoom, width * zoom, boxHeight * zoom);

            shapeRenderer.setColor (0, 0, 0, 0.5f);
            shapeRenderer.rect ((-width / 2 + width / 7) * zoom, (-height / 2 + boxHeight / 7) * zoom, (width / 2 / 14 * 9) * zoom, (boxHeight / 7 * 5) * zoom);

            shapeRenderer.setColor (0, 0, 0, 1f);
            shapeRenderer.rect (width / 2 / 7 * zoom, (-height / 2 + boxHeight / 7) * zoom, width / 2 / 7 * 2 * zoom, boxHeight / 7 * 2 * zoom);
            shapeRenderer.rect (width / 2 / 7 * 4 * zoom, (-height / 2 + boxHeight / 7) * zoom, width / 2 / 7 * 2 * zoom, boxHeight / 7 * 2 * zoom);
            shapeRenderer.rect (width / 2 / 7 * zoom, (-height / 2 + boxHeight / 7 * 4) * zoom, width / 2 / 7 * 2 * zoom, boxHeight / 7 * 2 * zoom);
            shapeRenderer.rect (width / 2 / 7 * 4 * zoom, (-height / 2 + boxHeight / 7 * 4) * zoom, width / 2 / 7 * 2 * zoom, boxHeight / 7 * 2 * zoom);

            shapeRenderer.setColor (Color.BLUE);
            shapeRenderer.rect (width / 2 / 7 * zoom, (-height / 2 + boxHeight / 7) * zoom, width / 2 / 7 * 2 * zoom * player.getOxygen (), boxHeight / 7 * 2 * zoom);

            shapeRenderer.setColor (Color.ORANGE);
            shapeRenderer.rect (width / 2 / 7 * 4 * zoom, (-height / 2 + boxHeight / 7) * zoom, width / 2 / 7 * 2 * zoom * player.getProtein (), boxHeight / 7 * 2 * zoom);

            shapeRenderer.setColor (Color.RED);
            shapeRenderer.rect (width / 2 / 7 * zoom, (-height / 2 + boxHeight / 7 * 4) * zoom, width / 2 / 7 * 2 * zoom * player.getHealth (), boxHeight / 7 * 2 * zoom);

            shapeRenderer.setColor (Color.YELLOW);
            shapeRenderer.rect (width / 2 / 7 * 4 * zoom, (-height / 2 + boxHeight / 7 * 4) * zoom, width / 2 / 7 * 2 * zoom * player.getAtp (), boxHeight / 7 * 2 * zoom);
        }
        shapeRenderer.end ();

        Gdx.gl.glDisable (GL20.GL_BLEND);

        batch.setProjectionMatrix (camera.projection);
        batch.begin ();

        batch.draw (new Texture ("info.png"), (-width / 2 + width / 2 / 7 - width / 56) * zoom, (-height / 2 + boxHeight / 7) * zoom, (width / 2 / 7) * zoom, (boxHeight / 7 * 5) * zoom);
        font.draw (batch, "Get 30 Points! You currently have " + player.getPts () + " points!", (-width / 2 + width / 7 + width / 128) * zoom, (-height / 2 + boxHeight / 14 * 11) * zoom, (width / 2 / 14 * 9 - width / 28) * zoom, -1, true);

        batch.draw (new Texture ("healthLogo.png"), (width / 56) * zoom, (-height / 2 + boxHeight / 7 * 4) * zoom, (width / 14 - width / 56) * zoom, boxHeight / 7 * 2 * zoom);
        batch.draw (new Texture ("oxygenLogo.png"), (width / 56) * zoom, (-height / 2 + boxHeight / 7) * zoom, (width / 14 - width / 56) * zoom, boxHeight / 7 * 2 * zoom);
        batch.draw (new Texture ("atpLogo.png"), (width / 56 + width / 2 / 7 * 3) * zoom, (-height / 2 + boxHeight / 7 * 4) * zoom, (width / 14 - width / 56) * zoom, boxHeight / 7 * 2 * zoom);
        batch.draw (new Texture ("proteinLogo.png"), (width / 56 + width / 2 / 7 * 3) * zoom, (-height / 2 + boxHeight / 7) * zoom, (width / 14 - width / 56) * zoom, boxHeight / 7 * 2 * zoom);
    }

    /**
     * Releases all resources of this object.
     */
    @Override
    public void dispose () {
        shapeRenderer.dispose ();
        font.dispose ();
    }
}