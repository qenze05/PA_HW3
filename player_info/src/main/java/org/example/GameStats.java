package org.example;

public class GameStats {
    public Player player;
    public int kills;
    public int deaths;
    public int assists;

    public GameStats(Player player) {
        this.player = player;
        this.player.gamesCount++;

        this.kills = 0;
        this.deaths = 0;
        this.assists = 0;
    }

    public double getKDR() {
        return (double) kills / (double) deaths;
    }

    public void registerKill() {
        kills++;
    }

    public void registerDeath() {
        deaths++;
    }

    public void registerAssist() {
        assists++;
    }
}
