package com.txznet.sdk;

interface HvacCallback {

    oneway void onTemperatureChanged(double temperature);

}