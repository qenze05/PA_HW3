package org.example;

public class Player {
    public String name;
    public int id;
    public boolean isOnline;
    public Rank rank;
    public int gamesCount;

    public Player(String name, int id, boolean isOnline, Rank rank, int gamesCount) {
        this.name = name;
        this.id = id;
        this.isOnline = isOnline;
        this.rank = rank;
        this.gamesCount = gamesCount;
    }

    public void rankUp() {
        Rank[] values = Rank.values();
        int currentIndex = rank.ordinal();
        int nextIndex = (currentIndex + 1) % values.length;
        if(nextIndex == 0) return;
        rank = values[nextIndex];
    }

    public void rankDown() {
        Rank[] values = Rank.values();
        int currentIndex = rank.ordinal();
        int nextIndex = (currentIndex - 1) % values.length;
        if(nextIndex == values.length - 1) return;
        rank = values[nextIndex];
    }

}
