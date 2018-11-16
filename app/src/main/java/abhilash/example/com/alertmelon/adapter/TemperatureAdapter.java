package abhilash.example.com.alertmelon.adapter;

import java.util.ArrayList;

import abhilash.example.com.alertmelon.logrecyclerview.Temperature;

public class TemperatureAdapter {

    private ArrayList<Temperature> temperatureList;

    public TemperatureAdapter() {

    }

    public TemperatureAdapter(ArrayList<Temperature> temperatureList) {
        this.temperatureList = temperatureList;
    }

    public ArrayList<Temperature> getTemperatureList() {
        return temperatureList;
    }

    public void setTemperatureList(ArrayList<Temperature> temperatureList) {
        this.temperatureList = temperatureList;
    }
}
