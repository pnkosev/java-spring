package app.service.models.hero;

import app.data.models.Gender;
import app.service.models.item.ItemServiceModel;

import java.util.List;

public class HeroDetailsServiceModel {

    private String name;

    private Gender gender;

    private int level;

    private int stamina;

    private int strength;

    private int attack;

    private int defense;

    private List<ItemServiceModel> items;

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Gender getGender() {
        return this.gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public int getLevel() {
        return this.level;
    }

    public void setLevel(int level) {
        this.level = level;
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

    public List<ItemServiceModel> getItems() {
        return this.items;
    }

    public void setItems(List<ItemServiceModel> items) {
        this.items = items;
    }
}
