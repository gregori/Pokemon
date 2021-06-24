package tech.gregori.pokemon.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Pokemon {
    @SerializedName("base_experience")
    private int baseExperience;
    private int height;
    private int id;
    private String name;
    private int weight;
    private Sprites sprites;
    private List<Type> types;

    public Pokemon(int baseExperience, int height, int id, String name, int weight, Sprites sprites, List<Type> types) {
        this.baseExperience = baseExperience;
        this.height = height;
        this.id = id;
        this.name = name;
        this.weight = weight;
        this.sprites = sprites;
        this.types = types;
    }

    public Sprites getSprites() {
        return sprites;
    }

    public void setSprites(Sprites sprites) {
        this.sprites = sprites;
    }

    public int getBaseExperience() {
        return baseExperience;
    }

    public void setBaseExperience(int baseExperience) {
        this.baseExperience = baseExperience;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public String getNameUCFirst() {
        return name.substring(0,1).toUpperCase() + name.substring(1);
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public List<Type> getTypes() {
        return types;
    }

    public void setTypes(List<Type> types) {
        this.types = types;
    }

    @Override
    public String toString() {
        return "Pokemon{" +
                "baseExperience=" + baseExperience +
                ", height=" + height +
                ", id=" + id +
                ", name='" + name + '\'' +
                ", weight=" + weight +
                ", sprites=" + sprites +
                ", types=" + types +
                '}';
    }
}
