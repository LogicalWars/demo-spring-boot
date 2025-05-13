package ru.netology.demospringboot.profile.impl;

import ru.netology.demospringboot.profile.SystemProfile;

public class ProductionProfile implements SystemProfile {
    @Override
    public String getProfile() {
        return "Current profile is production";
    }
}
