package com.jack.bullpenbook.model;

public class Player {

    private Long id;
    private String name;
    private String position;     // e.g. "SP", "RP", "1B", "CF"
    private String battingHand;  // "R", "L", "S"
    private String throwingHand; // "R", "L"

    public Player() {
    }

    public Player(Long id, String name, String position, String battingHand, String throwingHand) {
        this.id = id;
        this.name = name;
        this.position = position;
        this.battingHand = battingHand;
        this.throwingHand = throwingHand;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getPosition() { return position; }
    public void setPosition(String position) { this.position = position; }

    public String getBattingHand() { return battingHand; }
    public void setBattingHand(String battingHand) { this.battingHand = battingHand; }

    public String getThrowingHand() { return throwingHand; }
    public void setThrowingHand(String throwingHand) { this.throwingHand = throwingHand; }
}
