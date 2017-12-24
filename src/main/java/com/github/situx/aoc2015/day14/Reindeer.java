package com.github.situx.aoc2015.day14;

/**
 * Created by timo on 24.12.17 .
 */
public class Reindeer {
    public Reindeer(Integer resttime, Integer speedtime, Integer speed) {
        this.resttime = resttime;
        this.speedtime = speedtime;
        this.speed = speed;
    }

    Integer resttime;

    Integer speedtime;

    Integer speed;

    public Integer getResttime() {
        return resttime;
    }

    public void setResttime(Integer resttime) {
        this.resttime = resttime;
    }

    public Integer getSpeedtime() {
        return speedtime;
    }

    public void setSpeedtime(Integer speedtime) {
        this.speedtime = speedtime;
    }

    public Integer getSpeed() {
        return speed;
    }

    public void setSpeed(Integer speed) {
        this.speed = speed;
    }
}
