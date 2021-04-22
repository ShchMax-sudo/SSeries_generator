package com.shchmax;

import com.shchmax.Frac;
import java.util.ArrayList;

public class Serie {
    private ArrayList<Frac> fracs;

    public Serie() {
        this.fracs = new ArrayList<Frac>();
        this.fracs.add(new Frac(1, 1));
    }

    public Serie(int sz) {
        this.fracs = new ArrayList<Frac>();
        for (int i = 0; i < sz; ++i) {
            this.fracs.add(new Frac());
        }
    }

    public Serie(ArrayList<Serie> series) {
        this(series.get(series.size() - 1).size() + 1);
        Serie back = series.get(series.size() - 1);
        this.add(back.shift());
        this.add(back.multiply(new Frac(1, 2)));
        for (int i = 2; i < back.size(); ++i) {
            this.add(series.get(series.size() - i).multiply(back.get(i).negate()));
        }
        this.fracs = this.multiply(new Frac(back.size(), back.size() + 1)).get();
    }

    public int size() {
        return this.fracs.size();
    }

    public Frac get_end(int i) {
        return this.fracs.get(this.fracs.size() - 1 - i);
    }

    public ArrayList<Frac> get() {
        return this.fracs;
    }

    public Frac get(int i) {
        return this.fracs.get(i);
    }

    public void set(int i, Frac s) {
        this.fracs.set(i, s);
    }

    public void add(Serie s) {
        for (int i = 0; i < s.size(); ++i) {
            int ind = this.size() - 1 - i;
            this.fracs.set(ind, this.fracs.get(ind).add(s.get_end(i)));
        }
    }

    public Serie shift() {
        Serie ans = new Serie(this.size() + 1);
        for (int i = 0; i < this.size(); ++i) {
            ans.set(i, this.fracs.get(i));
        }
        return ans;
    }

    public Serie multiply(Frac k) {
        Serie ans = new Serie(this.size());
        for (int i = 0; i < this.size(); ++i) {
            ans.set(i, this.fracs.get(i).multiply(k));
        }
        return ans;
    }

    public void print() {
        for (Frac p : this.fracs) {
            p.print();
        }
        System.out.println();
    }
}
