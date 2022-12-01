package com.example.memory.Models;

import com.example.memory.MainActivity;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Playground extends MainActivity {
    private Card[][] _cards;
    private boolean _whosOnTurn;
    private int[] _score; // score[0] ... score für !whosOnTurn; score [1] ... score für whosOnTurn
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

    public int getScore(boolean player){
        if(player){
            return _score[1];
        } else{
            return _score[0];
        }

    }
    public void setScore(int s, boolean player){
        if(player){
            _score[1] = s;
        } else {
            _score[0] = s;
        }
    }

    public void init(){
        setIsWhosOnTurn(false);
        List<Integer> randCards = new ArrayList<>();

        for(int i = 0; i < getNrPairs(); i++){
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
                setCard(new Position(row, col), new Card(true, randCards.get(randNum)));
                randCards.remove(randCards.get(randNum));
            }
        }

    }
    public Card play(Position pos){
        return new Card();
    }

    public boolean finished(){
        for (Card[] cardRow: _cards) {
            for (Card card: cardRow) {
                if(card.getIsVisible()) return false;
            }
        }
        return true;
    }
    public boolean isPair(Position pos1, Position pos2){
        if(getCard(pos1).getValue() == getCard(pos2).getValue() && getCard(pos1).getIsVisible() && getCard(pos2).getIsVisible()){
            getCard(pos1).setIsVisible(false);
            getCard(pos2).setIsVisible(false);
            return true;
        }
        return false;
    }
    public Card getCard(Position pos){
        return _cards[pos.x][pos.y];
    }
    private int getNrPairs(){
        return _cards.length * _cards[0].length / 2;
    }

    public Playground(int x, int y){
        _cards = new Card[x][y];
        rand = new Random();
        _score = new int[]{0, 0};
        init();
    }

    public String toString(){
        return getCards().toString() + " " + getIsWhosOnTurn() + " ";
    }
}
