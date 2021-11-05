package tietokanta.com.example.tietokanta;

public class person {
    private String Name = null;
    private String hautaustapa = null;
    private String paikka = null;
    private boolean tuhkausPaikka = false;

    public person(String name, String hautaustapa, String paikka, boolean tuhkausPaikka){
    this.Name = name;
    this.hautaustapa = hautaustapa;
    this.paikka = paikka;
    this.tuhkausPaikka = tuhkausPaikka;
    }
    public String getName(){
        return Name;
    }
    public String getHautaustapa(){
        return hautaustapa;
    }
    public String getPaikka(){
        return paikka;
    }
    public boolean getTuhkausPaikka(){
        return tuhkausPaikka;
    }




}
