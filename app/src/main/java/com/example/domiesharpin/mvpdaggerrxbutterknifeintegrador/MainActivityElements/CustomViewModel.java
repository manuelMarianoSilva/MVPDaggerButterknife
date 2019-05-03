package com.example.domiesharpin.mvpdaggerrxbutterknifeintegrador.MainActivityElements;

class CustomViewModel {
    String name;
    String country;

    public CustomViewModel(String name, String country) {
        this.name = name;
        this.country = country;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }
}
