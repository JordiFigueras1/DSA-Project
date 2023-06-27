package edu.upc.dsa.models;


import java.util.Comparator;

public class Score {
    int id;
    int score;
    public Score(){}
    public Score(Level lvl) {
        this.id = lvl.getId();
        this.score = lvl.getLevel1() + lvl.getLevel2() + lvl.getLevel3() + lvl.getLevel4() + lvl.getLevel5() + lvl.getLevel6() + lvl.getLevel7() + lvl.getLevel8() + lvl.getLevel9() + lvl.getLevel10();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }
}
