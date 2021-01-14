package com.example.smproject.sqlite;

public class setings {
    int _id;
    String _name;
    int _mode;
    public setings(){   }
    public setings(int id, String name, int mode){
        this._id = id;
        this._name = name;
        this._mode = _mode;
    }

    public setings(String name, int _mode){
        this._name = name;
        this._mode = _mode;
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
}