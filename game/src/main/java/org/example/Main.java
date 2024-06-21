package org.example;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import org.example.Player;

public class Main {
    public static void main(String[] args) {
        Main.simulateGame();
    }

    public static ArrayList<Player> getRedTeam() {
        ArrayList<Player> players = new ArrayList<>();
        players.add(new Player("lekvid", 19837, true, Rank.Silver, 40));
        players.add(new Player("maxon", 1233, true, Rank.Silver, 23));
        players.add(new Player("qenze", 12, true, Rank.Gold, 52));
        players.add(new Player("linklpis", 2987, true, Rank.Platinum, 142));
        players.add(new Player("jixie", 122, true, Rank.Gold, 76));
        return players;
    }

    public static ArrayList<Player> getBlueTeam() {
        ArrayList<Player> players = new ArrayList<>();
        players.add(new Player("dejuto", 9283, true, Rank.Gold, 76));
        players.add(new Player("awkward", 109832, true, Rank.Silver, 19));
        players.add(new Player("zespees", 3, true, Rank.Gold, 23));
        players.add(new Player("ml7", 8276, true, Rank.Gold, 98));
        players.add(new Player("jax2", 987, true, Rank.Platinum, 123));
        return players;
    }

    public static void simulateGame() {
        ArrayList<Player> blueTeam = getBlueTeam();
        ArrayList<Player> redTeam = getRedTeam();
        GameServer server = new GameServer(blueTeam, redTeam, new GameMap("Rialto", 12, GameMode.DeathMatch));

        //deathmatch game
        server.redTeamStats.get(12).registerKill();
        server.redTeamStats.get(122).registerAssist();
        server.blueTeamStats.get(109832).registerDeath();

        server.blueTeamStats.get(3).registerKill();
        server.redTeamStats.get(122).registerDeath();

        server.blueTeamStats.get(8276).registerKill();
        server.redTeamStats.get(1233).registerDeath();

        server.blueTeamStats.get(9283).registerKill();
        server.redTeamStats.get(2987).registerDeath();

        server.redTeamStats.get(12).registerKill();
        server.blueTeamStats.get(9283).registerDeath();

        server.blueTeamStats.get(8276).registerKill();
        server.blueTeamStats.get(3).registerAssist();
        server.redTeamStats.get(12).registerDeath();

        server.blueTeamStats.get(3).registerKill();
        server.blueTeamStats.get(8276).registerAssist();
        server.redTeamStats.get(19837).registerDeath();


        //calculating stats
        blueTeam.forEach(x -> {
            if(server.blueTeamStats.get(x.id).getKDR() < 1) {
                x.rankDown();
            } else if (server.blueTeamStats.get(x.id).getKDR() > 1) {
                x.rankUp();
            }
        });

        redTeam.forEach(x -> {
            if(server.redTeamStats.get(x.id).getKDR() < 1) {
                x.rankDown();
            } else if (server.redTeamStats.get(x.id).getKDR() > 1) {
                x.rankUp();
            }
        });

        saveGameResults(server, "game/src/main/resources/game.json");
    }

    public static void saveGameResults(GameServer gameServer, String filePath) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        String jsonString = gson.toJson(gameServer);

        try (FileWriter fileWriter = new FileWriter(filePath)) {
            fileWriter.write(jsonString);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}