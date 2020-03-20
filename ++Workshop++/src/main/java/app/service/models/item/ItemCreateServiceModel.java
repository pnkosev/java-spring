package app.service.models.item;

import app.data.models.Slot;

public class ItemCreateServiceModel {

    private String name;

    private Slot slot;

    private int stamina;

    private int strength;

    private int attack;

    private int defense;

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Slot getSlot() {
        return this.slot;
    }

    public void setSlot(Slot slot) {
        this.slot = slot;
    }

    public int getStamina() {
        return this.stamina;
    }

    public void setStamina(int stamina) {
        this.stamina = stamina;
    }

    public int getStrength() {
        return this.strength;
    }

    public void setStrength(int strength) {
        this.strength = strength;
    }

    public int getAttack() {
        return this.attack;
    }

    public void setAttack(int attack) {
        this.attack = attack;
    }

    public int getDefense() {
        return this.defense;
    }

    public void setDefense(int defense) {
        this.defense = defense;
    }
}
