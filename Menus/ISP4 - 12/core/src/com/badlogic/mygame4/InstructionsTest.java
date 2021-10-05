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

public class InstructionsTest extends ApplicationAdapter implements Screen {
	private Stage stage;
    private Label outputLabel;
    Texture img;
    SpriteBatch batch;

    @Override
    public void create () {
        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);
        
        batch = new SpriteBatch ();
        img = new Texture("InstructionsBIOSHOOK.png");
 
        int Help_Guides = 12;
        int row_height = Gdx.graphics.getWidth() / 30;
        int col_width = Gdx.graphics.getWidth() / 12;
 
        Skin mySkin = new Skin(Gdx.files.internal("skin/flat-earth-ui.json"));
 
        //title for screen "Instructions"
        Label title = new Label("BIOSHOOK, a game made by Bionic Entertainment, has the goal of educating its users while",mySkin);
        Label title2 = new Label ("still being fun and engaging! Bionic Entertainment has created this game for VK, CEO of VK", mySkin);
        Label title3 = new Label ("Enterprices. This game has been created by Philip Nicolas Varga, team leader, and Elen Misura,", mySkin);
        Label title4 = new Label ("team member, of Bionic Entertainment. A small installment of ___ dollars is required everytime", mySkin);
        Label title5 = new Label ("this game, BIOSHOOK, is played or opened.", mySkin);
        Label title6 = new Label ("Instructions: ", mySkin);
        Label title7 = new Label ("You, the single player of this game, must rid your host of all diseases/illnesses before the", mySkin);
        Label title8 = new Label ("host has been injured too severely, beyond means of healing, or has been killed.", mySkin);
        
        title.setSize(Gdx.graphics.getWidth(),row_height*2);
        title2.setSize(Gdx.graphics.getWidth(), row_height*2);
        title3.setSize(Gdx.graphics.getWidth(), row_height*2);
        title4.setSize(Gdx.graphics.getWidth(), row_height*2);
        title5.setSize(Gdx.graphics.getWidth(), row_height*2);
        title6.setSize(Gdx.graphics.getWidth(), row_height*2);
        title7.setSize(Gdx.graphics.getWidth(), row_height*2);
        title8.setSize(Gdx.graphics.getWidth(), row_height*2);
        
        title.setPosition(0,Gdx.graphics.getHeight()-row_height*5);
        title2.setPosition(0,Gdx.graphics.getHeight()-row_height*6);
        title3.setPosition(0,Gdx.graphics.getHeight()-row_height*7);
        title4.setPosition(0,Gdx.graphics.getHeight()-row_height*8);
        title5.setPosition(0,Gdx.graphics.getHeight()-row_height*9);
        title6.setPosition(0,Gdx.graphics.getHeight()-row_height*11);
        title7.setPosition(0,Gdx.graphics.getHeight()-row_height*12);
        title8.setPosition(0,Gdx.graphics.getHeight()-row_height*13);
        
        title.setAlignment(Align.center);
        title2.setAlignment(Align.center);
        title3.setAlignment(Align.center);
        title4.setAlignment(Align.center);
        title5.setAlignment(Align.center);
        title6.setAlignment(Align.center);
        title7.setAlignment(Align.center);
        title8.setAlignment(Align.center);
        
        stage.addActor(title);
        stage.addActor(title2);
        stage.addActor(title3);
        stage.addActor(title4);
        stage.addActor(title5);
        stage.addActor(title6);
        stage.addActor(title7);
        stage.addActor(title8);
 
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
       // Button button2 = new TextButton("Back",mySkin);
        Button button15 = new TextButton ("Back", mySkin);
        //Button button2 = new TextButton("Text Button",mySkin);
        //Button button2 = new TextButton("Text Button",mySkin,"small");
        //button2.setSize(col_width*4,row_height);
        button15.setSize(col_width*2, row_height*2);
        //button2.setPosition(col_width*7,Gdx.graphics.getHeight()-row_height*6);
        button15.setPosition(col_width*1, Gdx.graphics.getHeight () - row_height*20);
        /*button2.addListener(new InputListener(){
            @Override
            public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
                outputLabel.setText("A button without text");
                //outputLabel.setText("Press a Button");
            }
            @Override
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                outputLabel.setText("Play level 3 of BIOSHOOK"); //needs to go to dif. screen instead
                //outputLabel.setText("Pressed Text Button");
                return true;
            }
        });
        stage.addActor(button2);*/
        
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
    	//Gdx.gl.glClearColor(255/255f, 128/255f, 0/255f, 1);
    	Gdx.gl.glClearColor(51/255f, 0/255f, 25/255f, 1);
    	Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.act();
        stage.draw();
        
        batch.begin();
		batch.draw(img, 210, 430);
		batch.end();
 
    }

	@Override
	public void show() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void render(float delta) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub
		
	}
}