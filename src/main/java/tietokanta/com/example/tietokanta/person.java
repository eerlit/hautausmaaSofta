package tietokanta.com.example.tietokanta;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class person {
    private StringProperty Name;
    private StringProperty Hautaustapa;
    private StringProperty Paikka;
    private StringProperty tuhkausPaikka;
    private StringProperty HautausPvm;
    private StringProperty Rivi;

    public person(String name, String hautaustapa, String paikka, String rivi, String tuhkausPaikka, String hautauspvm){
    this.Name = new SimpleStringProperty(name);
    this.Hautaustapa = new SimpleStringProperty(hautaustapa);
    this.Paikka =  new SimpleStringProperty(paikka);
    this.tuhkausPaikka =  new SimpleStringProperty(tuhkausPaikka);
    this.HautausPvm =  new SimpleStringProperty(hautauspvm);
    this.Rivi =  new SimpleStringProperty(rivi);
    }
    public String getName(){
        return Name.get();
    }
    public void setName(String nimi){Name.set(nimi);}
    public String getHautaustapa(){
        return Hautaustapa.get();
    }
    public void setHautaustapa(String htapa){Hautaustapa.set(htapa);}
    public String getPaikka(){
        return Paikka.get();
    }
    public void setPaikka(String paikka){Paikka.set(paikka);}
    public String getTuhkausPaikka(){
        return tuhkausPaikka.get();
    }
    public void setTuhkausPaikka(String TuhkausPaikka){tuhkausPaikka.set(TuhkausPaikka);}
    public String getHautausPvm(){
        return HautausPvm.get();
    }
    public void setHautausPvm(String hautausPvm){Hautaustapa.set(hautausPvm);}
    public String getRivi(){return Rivi.get();
    }
    public void setRivi(String rivi){Rivi.set(rivi);}




}
