package com.badlogic.mygame4;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Pixmap.Format;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.ImageTextButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener.ChangeEvent;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

public class Levels extends ApplicationAdapter {
	private Stage stage;
    private Label outputLabel;
    Texture img;
    SpriteBatch batch;

    @Override
    public void create () {
        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);
        
        batch = new SpriteBatch ();
        //img = new Texture("Level2.png");
 
        int Help_Guides = 12;
        int row_height = Gdx.graphics.getWidth() / 12;
        int col_width = Gdx.graphics.getWidth() / 12;
 
        Skin mySkin = new Skin(Gdx.files.internal("skin/flat-earth-ui.json"));
 
        Label title = new Label("Bionic Entertainment",mySkin);
        title.setSize(Gdx.graphics.getWidth(),row_height*2);
        title.setPosition(0,Gdx.graphics.getHeight()-row_height*9);
        title.setAlignment(Align.center);
        stage.addActor(title);
 
        /*// Button
        Button button1 = new Button(mySkin);
        // Button button1 = new Button(mySkin, "small");
        button1.setSize(col_width*4,row_height);
        button1.setPosition(col_width,Gdx.graphics.getHeight()-row_height*3);
        button1.addListener(new InputListener(){
            @Override
            public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
                outputLabel.setText("Press a Button");
            }
            @Override
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                outputLabel.setText("Pressed Button");
                return true;
            }
        });
        stage.addActor(button1);*/
 
        // Text Button
        Button button = new TextButton ("Level 1", mySkin);
        Button button2 = new TextButton("Level 2",mySkin);
        Button button3 = new TextButton ("Level 3", mySkin);
        Button button15 = new TextButton ("Main Menu", mySkin);
        //Button button2 = new TextButton("Text Button",mySkin);
        //Button button2 = new TextButton("Text Button",mySkin,"small");
       
        button.setSize(col_width*4,row_height);
        button2.setSize(col_width*4,row_height);
        button3.setSize(col_width*4,row_height);
        button15.setSize(col_width*4, row_height);
        
        button.setPosition(col_width*4,Gdx.graphics.getHeight()-row_height*2);
        button2.setPosition(col_width*4,Gdx.graphics.getHeight()-row_height*4);
        button3.setPosition(col_width*4,Gdx.graphics.getHeight()-row_height*6);
        button15.setPosition(col_width*4, Gdx.graphics.getHeight () - row_height*8);
        
        button.addListener(new InputListener(){
            /*@Override
            public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
                outputLabel.setText("A button without text");
                //outputLabel.setText("Press a Button");
            }*/
            @Override
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
            	((Game)Gdx.app.getApplicationListener()).setScreen(new   
            		     Level1MenuTest());
            	//outputLabel.setText("Play level 1 of BIOSHOOK"); //needs to go to dif. screen instead
                //outputLabel.setText("Pressed Text Button");
                return true;
            }
        });
        stage.addActor(button);
        
        button2.addListener(new InputListener(){
            /*@Override
            public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
                outputLabel.setText("A button without text");
                //outputLabel.setText("Press a Button");
            }*/
            @Override
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
            	((Game)Gdx.app.getApplicationListener()).setScreen(new   
           		     Level2MenuTest());
            	//outputLabel.setText("Play level 2 of BIOSHOOK"); //needs to go to dif. screen instead
                //outputLabel.setText("Pressed Text Button");
                return true;
            }
        });
        stage.addActor(button2);
        
        button3.addListener(new InputListener(){
            /*@Override
            public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
                outputLabel.setText("A button without text");
                //outputLabel.setText("Press a Button");
            }*/
            @Override
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
            	((Game)Gdx.app.getApplicationListener()).setScreen(new   
           		     Level3MenuTest());
            	//outputLabel.setText("Play level 3 of BIOSHOOK"); //needs to go to dif. screen instead
                //outputLabel.setText("Pressed Text Button");
                return true;
            }
        });
        stage.addActor(button3);
        
        //trying 
        button15.addListener (new InputListener() {
        	/*@Override
            public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
                outputLabel.setText("A button without text");
                //outputLabel.setText("Press a Button");
            }*/
            @Override
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
            	((Game)Gdx.app.getApplicationListener()).setScreen(new   
           		     MainMenuTest());
            	//outputLabel.setText("Go back to Main Menu"); //needs to go to dif. screen instead
                //outputLabel.setText("Pressed Text Button");
                return true;
            }
        });
        stage.addActor(button15);
 
       /* // ImageButton
        ImageButton button3 = new ImageButton(mySkin);
        button3.setSize(col_width*4,(float)(row_height*2));
        //button3.getStyle().imageUp = new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("switch_off.png"))));
        //button3.getStyle().imageDown = new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("switch_on.png"))));
        button3.setPosition(col_width,Gdx.graphics.getHeight()-row_height*6);
        button3.addListener(new InputListener(){
            @Override
            public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
                outputLabel.setText("Press a Button");
            }
            @Override
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                outputLabel.setText("Pressed Image Button");
                return true;
            }
        });
        stage.addActor(button3);*/
 
        /*//ImageTextButton
        ImageTextButton button4 = new ImageTextButton("ImageText Btn",mySkin);
        //ImageTextButton button4 = new ImageTextButton("ImageText Btn",mySkin,"small");
        button4.setSize(col_width*4,(float)(row_height*2));
        //button4.getStyle().imageUp = new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("switch_off.png"))));
        //button4.getStyle().imageDown = new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("switch_on.png"))));
        button4.setPosition(col_width*7,Gdx.graphics.getHeight()-row_height*6);
        button4.addListener(new InputListener(){
            @Override
            public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
                outputLabel.setText("Press a Button");
            }
            @Override
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                outputLabel.setText("Pressed Image Text Button");
                return true;
            }
        });
        stage.addActor(button4);*/
 
        //can get rid of this later
        outputLabel = new Label("Press a Button",mySkin);
        //outputLabel = new Label("Press a Button",mySkin,"black");
        outputLabel.setSize(Gdx.graphics.getWidth(),row_height);
        outputLabel.setPosition(0,row_height);
        outputLabel.setAlignment(Align.center);
        stage.addActor(outputLabel);
    }
 
    @Override
    public void render () {
        //Gdx.gl.glClearColor(1, 1, 1, 1);
    	Gdx.gl.glClearColor(255/255f, 128/255f, 0/255f, 1);
    	Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.act();
        stage.draw();
        
        //batch.begin();
		//batch.draw(img, 220, 275);
		//batch.end();
 
    }
}