package ru.SlimeEngine;

import java.util.ArrayList;

import android.content.res.AssetManager;

public abstract class GameManager {
	public ArrayList<Base> gameBases;
	public abstract void createGame(AssetManager am, Scene scene);
	public abstract boolean checkEndOfGame();
	public abstract void step();
}
