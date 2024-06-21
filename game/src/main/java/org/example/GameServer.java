package org.example;

import java.util.ArrayList;
import java.util.HashMap;

public class GameServer {
    public HashMap<Integer, GameStats> blueTeamStats;
    public HashMap<Integer, GameStats> redTeamStats;

    public GameMap map;

    public GameServer(ArrayList<Player> blueTeam, ArrayList<Player> redTeam, GameMap map) {
        this.map = map;

        this.blueTeamStats = new HashMap<>();

        this.redTeamStats = new HashMap<>();

        blueTeam.forEach(x -> {
            this.blueTeamStats.put(x.id, new GameStats(x));
        });

        redTeam.forEach(x -> {
            this.redTeamStats.put(x.id, new GameStats(x));
        });
    }



}
