package com.shchmax;

import com.shchmax.Frac;
import java.util.ArrayList;

public class App {
    public static void main(String[] args) {
        ArrayList<Serie> series = new ArrayList<Serie>();
        series.add(new Serie());
        Serie first = new Serie(2);
        first.set(0, new Frac(1, 2));
        first.set(1, new Frac(1, 2));
        series.add(first);
        for (int i = 1; i < 20; ++i) {
            series.add(new Serie(series));
        }
        for (Serie p : series) {
            p.print();
        }
        Serie ans = new Serie(0);
        for (Serie p : series) {
            ans.add(p.get().get(p.size() - 1));
        }
        ans.print();
    }
}
