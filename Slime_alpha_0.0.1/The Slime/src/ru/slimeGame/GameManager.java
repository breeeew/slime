package ru.slimeGame;

import java.util.ArrayList;

import ru.SlimeEngine.mScene;

import android.content.res.AssetManager;

public abstract class GameManager {
	public ArrayList<Base> gameBases;
	public abstract void createGame(AssetManager am, mScene scene);
	public abstract boolean checkEndOfGame();
	public abstract void step();
}
