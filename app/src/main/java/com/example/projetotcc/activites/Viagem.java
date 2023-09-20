package com.example.projetotcc.activites;

import java.sql.Date;

public class Viagem {

    public Viagem(int mId, Long mLongMatch, Long mLongArrival) {
        this.mId = mId;
        this.mLongMatch = mLongMatch;
        this.mLongArrival = mLongArrival;
    }

    public Viagem(Long mLongMatch, Long mLongArrival) {
        this.mLongMatch = mLongMatch;
        this.mLongArrival = mLongArrival;
    }

    @Override
    public String toString() {
        return "Viagem{" +
                "mId=" + mId +
                ", mLongMatch=" + mLongMatch +
                ", mLongArrival=" + mLongArrival +
                '}';
    }

    public static final String TAG="Viagens Table";
    private int mId;
    private Long mLongMatch;
    private  Long mLongArrival;

    public int getId() {
        return mId;
    }

    public void setId(int mId) {
        this.mId = mId;
    }

    public Long getLongMatch() {
        return mLongMatch;
    }

    public void setLongMatch(Long mLongMatch) {
        this.mLongMatch = mLongMatch;
    }

    public Long getLongArrival() {
        return mLongArrival;
    }

    public void setLongArrival(Long mLongArrival) {
        this.mLongArrival = mLongArrival;
    }
}
