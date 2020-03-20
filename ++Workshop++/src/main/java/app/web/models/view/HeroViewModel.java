package app.web.models.view;

public class HeroViewModel {

    private String heroName;

    private int level;

    private String gender;

    public String getHeroName() {
        return this.heroName;
    }

    public void setHeroName(String heroName) {
        this.heroName = heroName;
    }

    public int getLevel() {
        return this.level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public String getGender() {
        return this.gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }
}
