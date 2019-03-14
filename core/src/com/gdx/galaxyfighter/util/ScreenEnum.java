package com.gdx.galaxyfighter.util;

import com.gdx.galaxyfighter.screen.AbstractScreen;
import com.gdx.galaxyfighter.screen.GameScreen;
import com.gdx.galaxyfighter.screen.LevelSelectScreen;
import com.gdx.galaxyfighter.screen.MainMenuScreen;


public enum ScreenEnum {
	
	MAIN_MENU {
		public AbstractScreen getScreen(Object... params) {
			return new MainMenuScreen();
		}
	},
	
	LEVEL_SELECT {
		public AbstractScreen getScreen(Object... params) {
			return new LevelSelectScreen();
		}
	},
	
	GAME {
		public AbstractScreen getScreen(Object... params) {
			return new GameScreen((Integer) params[0]);
		}
	};
	
	public abstract AbstractScreen getScreen(Object... params);
}
