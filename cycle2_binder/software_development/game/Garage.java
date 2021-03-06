/*
Written by: Garrett Sullivan 

Source/Credit: Hollowbit's Youtube video "LibGDX 2D Tutorial #4: Main Menu and Mouse Input"
 https://www.youtube.com/watch?v=67ZCQt8QpNA
*/

package com.mytile.game;

import static com.mytile.game.extras.Constants.BoxH;
import static com.mytile.game.extras.Constants.Window_H;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Garage implements Screen {
	
	public RacingGame g;
	private int backW = 340;	//back button width
	private int BackY = 910;	//back button y-coordinate on screen
	private int BackX = 0;	//back button x-coordinate on screen
	private int On = 10;	//offset variable for the "On" texture
	
	public Garage(RacingGame game) {
		this.g = game;
		//stores the width and height of the app window 
		float w = Gdx.graphics.getWidth();
		float h = Gdx.graphics.getHeight();
		//creates an OrthographicCamera and sets the camera's position inside the window
		g.cam = new OrthographicCamera();
		g.cam.setToOrtho(false, w/2, h/2);

		g.batch = new SpriteBatch();
		//used to store a textures 
		g.background = new Texture("background.png");
		g.back = new Texture("buttons/Back.png");
		g.backOn = new Texture("buttons/BackOn.png");	
	}

	@Override
	public void show() {}

	@Override
	public void render(float delta) {
		//fills the screen with all the necessary textures
		g.batch.begin();
		g.batch.draw(g.background, 0, 0);
		g.batch.draw(g.back,BackX,BackY);
		g.batch.end();
		//calls input method to sense all user mouse input
		userInput();
	}

	@Override
	public void resize(int width, int height) {}

	@Override
	public void pause() {}

	@Override
	public void resume() {}

	@Override
	public void hide() {}

	@Override
	public void dispose() { //dispose of all things on screen
		g.dispose();
		g.B2Drender.dispose();
		g.batch.dispose();	
	}
	
	public void userInput() {
		//checks if the mouse is hovering over the back button
		//then draws texture on the correct back button
		if(Gdx.input.getX() > BackX+On && Gdx.input.getX() < BackX+backW && Window_H - Gdx.input.getY() < BackY+On+BoxH && Window_H - Gdx.input.getY() > BackY+On) {
			
			g.batch.begin();
			g.batch.draw(g.backOn,BackX+On,BackY);
			g.batch.end();
			
			if(Gdx.input.isTouched()) {
				g.setScreen(new Menu(g));	//sets current screen to the menu screen
			}
		}
	}
}
