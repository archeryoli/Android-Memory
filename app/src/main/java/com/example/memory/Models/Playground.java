package com.example.memory.Models;

import com.example.memory.MainActivity;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Playground extends MainActivity {
    private Card[][] _cards;
    private boolean _whosOnTurn;
    private int _score;
    private Random rand;

    public boolean getIsWhosOnTurn() {
        return _whosOnTurn;
    }
    public void setIsWhosOnTurn(boolean p){
        _whosOnTurn = p;
    }

    public Card[][] getCards() {
        return _cards;
    }
    public void setCard(Position pos, Card card){
        this._cards[pos.x][pos.y] = card;
    }

    public int getScore(){
        return _score;
    }
    public void setScore(int s){
        _score = s > 0 ? s : -1;
    }

    public void init(){
        List<Integer> randCards = new ArrayList<>();

        for(int i = 0; i < 6; i++){
            boolean worked = false;
            int num = 0;
            do {
                num = rand.nextInt(20);
                if(randCards.contains(num)){
                    continue;
                }
                worked = true;
            } while(!worked);
            randCards.add(num);
            randCards.add(num);
        }

        for(int row = 0; row < _cards.length; row++){
            for(int col = 0; col < _cards[row].length; col++){
                int randNum = rand.nextInt(randCards.size());
                setCard(new Position(row, col), new Card(false, randCards.get(randNum)));
                randCards.remove(randCards.get(randNum));
            }
        }

    }
    public Card play(Position pos){
        return new Card();
    }
    public boolean finished(){
        return false;
    }
    public boolean isPair(Position pos1, Position pos2){
        return getCard(pos1).equals(getCard(pos2));
    }
    public Card getCard(Position pos){
        return _cards[pos.x][pos.y];
    }
    private int getNrPairs(){
        return 0;
    }

    public Playground(int x, int y){
        _cards = new Card[x][y];
        rand = new Random();
        init();
    }

    public String toString(){
        return getCards().toString() + " " + getIsWhosOnTurn() + " " + getScore() + " ";
    }
}
