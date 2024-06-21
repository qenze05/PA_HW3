package org.example;

public class GameMap {
    public String name;
    public int id;
    public GameMode gameMode;

    public GameMap(String name, int id, GameMode gameMode) {
        this.name = name;
        this.id = id;
        this.gameMode = gameMode;
    }
}
