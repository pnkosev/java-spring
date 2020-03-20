package app.service.models.hero;

import app.data.models.Gender;
import app.service.models.item.ItemServiceModel;

public class HeroDetailsServiceModel {

    private String name;

    private Gender gender;

    private int level;

    private int stamina;

    private int strength;

    private int attack;

    private int defense;

    private ItemServiceModel helmet;
    private ItemServiceModel weapon;
    private ItemServiceModel pauldron;
    private ItemServiceModel gauntlets;
    private ItemServiceModel pads;

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

    public ItemServiceModel getHelmet() {
        return this.helmet;
    }

    public void setHelmet(ItemServiceModel helmet) {
        this.helmet = helmet;
    }

    public ItemServiceModel getWeapon() {
        return this.weapon;
    }

    public void setWeapon(ItemServiceModel weapon) {
        this.weapon = weapon;
    }

    public ItemServiceModel getPauldron() {
        return this.pauldron;
    }

    public void setPauldron(ItemServiceModel pauldron) {
        this.pauldron = pauldron;
    }

    public ItemServiceModel getGauntlets() {
        return this.gauntlets;
    }

    public void setGauntlets(ItemServiceModel gauntlets) {
        this.gauntlets = gauntlets;
    }

    public ItemServiceModel getPads() {
        return this.pads;
    }

    public void setPads(ItemServiceModel pads) {
        this.pads = pads;
    }
}
