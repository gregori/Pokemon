package tech.gregori.pokemon.model;

import java.util.List;

public class Type {
    private int slot;
    private TypeItem type;

    public Type(int slot, TypeItem type) {
        this.slot = slot;
        this.type = type;
    }

    public int getSlot() {
        return slot;
    }

    public void setSlot(int slot) {
        this.slot = slot;
    }

    public TypeItem getType() {
        return type;
    }

    public void setType(TypeItem type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "Type{" +
                "slot=" + slot +
                ", type=" + type +
                '}';
    }
}
