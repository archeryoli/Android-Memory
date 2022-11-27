package com.example.memory.Models;


public class Card {
    private boolean _visible;
    private int _value;

    public boolean getIsVisible() {
        return _visible;
    }
    public void setIsVisible(boolean b){
        _visible = b;
    }
    public int getValue(){
        return _value;
    }
    public void setValue(int v){
        _value = v;
    }
    public Card(){
        this(false, -1);
    }
    public Card(boolean isVisible, int value){
        setIsVisible(isVisible);
        setValue(value);
    }

    public String toString(){
        return _visible + " " + _value + " ";
    }
    public boolean equals(Object o){
        return ((Card) o).getIsVisible() == this.getIsVisible() && ((Card) o).getValue() == this.getValue();
    }

}
