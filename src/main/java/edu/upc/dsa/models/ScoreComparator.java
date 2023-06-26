package edu.upc.dsa.models;
import java.util.Comparator;

public class ScoreComparator implements Comparator<Score> {
    @Override
    public int compare(Score score1, Score score2) {
        return Integer.compare(score1.score, score2.score);
    }
}
