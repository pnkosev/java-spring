package app.service.factories;

import app.data.models.Gender;
import app.data.models.Hero;

import static app.service.factories.HeroConstants.*;

public class HeroFactoryImpl implements HeroFactory {
    @Override
    public Hero create(String name, Gender gender) {
        Hero hero = new Hero();
        hero.setName(name);
        hero.setGender(gender);
        hero.setLevel(INITIAL_LEVEL);
        hero.setStamina(INITIAL_STAMINA);
        hero.setStrength(INITIAL_STRENGTH);
        hero.setAttack(INITIAL_ATTACK);
        hero.setDefense(INITIAL_DEFENSE);

        return hero;
    }
}
