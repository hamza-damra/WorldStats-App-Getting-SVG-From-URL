package com.example.statesinfo;

import com.caverock.androidsvg.SVG;

public class Country {
    private String name;
    private SVG svgImage;
    private String capital;
    private int population;
    private String region;
    private String flagUrl;

    public Country(String name, SVG svgImage, String capital, int population, String region, String flagUrl) {
        this.name = name;
        this.svgImage = svgImage;
        this.capital = capital;
        this.population = population;
        this.region = region;
        this.flagUrl = flagUrl;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public SVG getSvgImage() {
        return svgImage;
    }

    public void setSvgImage(SVG svgImage) {
        this.svgImage = svgImage;
    }

    public String getCapital() {
        return capital;
    }

    public void setCapital(String capital) {
        this.capital = capital;
    }

    public int getPopulation() {
        return population;
    }

    public void setPopulation(int population) {
        this.population = population;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getFlagUrl() {
        return flagUrl;
    }

    public void setFlagUrl(String flagUrl) {
        this.flagUrl = flagUrl;
    }
}
