package com.miage.vepick.controller;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.Random;

import com.miage.vepick.model.Bornette;
import com.miage.vepick.model.ModelVelo;
import com.miage.vepick.model.Station;
import com.miage.vepick.model.Velo;
import com.miage.vepick.repository.StationRepository;
import com.miage.vepick.service.BornetteService;
import com.miage.vepick.service.ModelService;
import com.miage.vepick.service.StationService;
import com.miage.vepick.service.VeloService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


import org.springframework.ui.Model;
import lombok.*;
@Controller
public class TestController{

    // @Autowired
    // private StationRepository stationRep;
    @Autowired
    private StationService stationService;

    @Autowired
    private BornetteService bornetteService;

    @Autowired
    private VeloService veloService;

    @Autowired
    private ModelService modelService;
    
    private static final String[] ADRESSES = new String[]{"campus","centre ville","gare", "mairie","stade","jardin de ville","alsace-lorraine"};


    @ResponseBody
    @RequestMapping("/test")
    public String test(){
        String html = "";
        html += "<ul>";
        html += " <li><a href='/testInsert'>Test Insert</a></li>";
        html += " <li><a href='/showAllStations'>Show All Stations</a></li>";
        html += " <li><a href='/deleteAllStations'>Delete All Stations</a></li>";
        html += "</ul>";
        return html;
    }


    ModelVelo model = new ModelVelo("Btwin", 20.0);
    ModelVelo model2= new ModelVelo("Van Rysel", 30.0);
    ModelVelo model3= new ModelVelo("laPierre", 10.0);

    ModelVelo[] MODELS = new ModelVelo[]{model, model2, model3};
    @ResponseBody
    @RequestMapping("/populate")
    public String testInsert(){
        int random = new Random().nextInt(7);
        String adresse=ADRESSES[random];

        Station station = new Station();
        Bornette bornette = new Bornette(station);
        Bornette bornette2 = new Bornette(station);
        Bornette bornette3 = new Bornette(station);
        bornette3.setLibre(true);
        Velo velo = new Velo(MODELS[new Random().nextInt(3)]);
        Velo velo2 = new Velo(MODELS[new Random().nextInt(3)]);
        velo.setBornette(bornette);
        velo2.setBornette(bornette2);
        station.setAdresse(adresse);
        this.modelService.saveModel(model);
        this.modelService.saveModel(model2);
        this.modelService.saveModel(model3);
        this.stationService.saveStation(station);
        this.bornetteService.saveBornette(bornette);
        this.bornetteService.saveBornette(bornette2);
        this.bornetteService.saveBornette(bornette3);
        this.veloService.saveVelo(velo);
        this.veloService.saveVelo(velo2);
        
        return "insertion: "+adresse;//test
    }

    @ResponseBody
    @RequestMapping("/showAllStations")
    public String showAllStations(){
        // Iterable<Station> stations = this.stationRep.findAll();
        Iterable<Station> stations = this.stationService.getStations();
        String html = "";
        for (Station station : stations) {
            html += station +"<br>";
            List<Bornette> bornettes = this.bornetteService.getBornettesByStation(station);
            for (Bornette bornette : bornettes) {
                Optional<Velo> veloOpt = this.veloService.getVeloByBornette(bornette);
                if(veloOpt.isPresent()) {
                    Velo velo = veloOpt.get();
                    html +="    "+bornette + "(bornette) station:"+bornette.getStation().getId()+" <br>";
                    html += "       "+velo + "(velo)"+"<br>";
                }                
            }
        }
        return html;
    }


    
    
    @ResponseBody
    @RequestMapping("/deleteAllStations")
    public String deleteAllEmployee() {

        // this.stationRep.deleteAll();
        this.stationService.deleteAllStations();
        return "stations supprimés!";
    }

    @ResponseBody
    @RequestMapping("/louer-velo")
    public String rentBike(){
        int id=1;//test
        // long id=1;//test
        System.out.println("Conntectez vous a une station");
        Iterable<Station> stations = this.stationService.getStations();
        String html = "";
        for (Station station : stations) {
            html += "n"+station.getId()+": "+ station +"<br>";
        }
        Optional<Station> station = stationService.getStationById(id);

        //votre numero d'util (form)
        //if util exists sinon creer nouveau
        //location unique, liste des station -> clique sur station, premier velo dispo, nouvelle llocation
        //generation mdp et assignation mdp a client et a location,
        //enleve velo de bornette

        //reettre velo, cliqu estation,, premiere bornette libre, assigne station si mdp juste
        //fin location
        return null;
    }

    @ResponseBody
    @RequestMapping("/showLocationForm")
    public String showForm(){
        return null;
    }
}