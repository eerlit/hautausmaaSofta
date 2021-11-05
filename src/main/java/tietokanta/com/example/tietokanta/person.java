package tietokanta.com.example.tietokanta;

public class person {
    private String Name = null;
    private String hautaustapa = null;
    private String paikka = null;
    private String tuhkausPaikka = null;
    private String hautausPvm = null;

    public person(String name, String hautaustapa, String paikka, String tuhkausPaikka, String hautauspvm){
    this.Name = name;
    this.hautaustapa = hautaustapa;
    this.paikka = paikka;
    this.tuhkausPaikka = tuhkausPaikka;
    this.hautausPvm = hautauspvm;
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
    public String getTuhkausPaikka(){
        return tuhkausPaikka;
    }
    public String getHautausPvm(){
        return hautausPvm;
    }




}
