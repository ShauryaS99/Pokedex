package com.jackie.pokedex;

public class Pokemon {
    private String _name;
    private int _number;
    private int _atk;
    private int _def;
    private String _flavorText;
    private int _hp;
    private int _spHp;
    private int _spDef;
    private String _species;
    private int _speed;
    private int _total;
    private String[] _type;

    public Pokemon(String name) {
        _name = name;

    }

    /** <---------- GETTER METHODS. ---------->*/

    public String getName() {
        return _name;
    }

    public int getNumber() {
        return _number;
    }

    public int getAtk() {
        return _atk;
    }

    public int getDef() {
        return _def;
    }

    public String getFlavorText() {
        return _flavorText;
    }

    public int getHp() {
        return _hp;
    }

    public int getSpHp() {
        return _spHp;
    }

    public int getSpDef() {
        return _spDef;
    }

    public String getSpecies() {
        return _species;
    }

    public int getSpeed() {
        return _speed;
    }

    public int getTotal() {
        return _total;
    }

    public String[] getType() {
        return _type;
    }
}
