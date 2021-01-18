package com.example.smproject.sqlite;

public class setings {
    int _id;
    String _name;
    int _mode;
    int _mode2;
    public setings(){   }
    public setings(int id, String name, int _mode, int _mode2){
        this._id = id;
        this._name = name;
        this._mode = _mode;
        this._mode2 = _mode2;
    }

    public setings(String name, int _mode, int _mode2){
        this._name = name;
        this._mode = _mode;
        this._mode2 = _mode2;
    }
    public int getID(){
        return this._id;
    }

    public void setID(int id){
        this._id = id;
    }

    public String getName(){
        return this._name;
    }

    public void setName(String name){
        this._name = name;
    }

    public int getMode(){
        return this._mode;
    }

    public void setMode(int mode){
        this._mode = mode;
    }

    public int getMode2(){
        return this._mode2;
    }

    public void setMode2(int mode2){
        this._mode2 = mode2;
    }
}