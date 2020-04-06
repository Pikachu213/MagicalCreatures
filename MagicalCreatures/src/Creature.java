import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.time.Period;

public class Creature extends MyObject implements Serializable {
    private static final long serialVersionUID = 1L;
    //private static List<Creature> extent = new ArrayList<>();

    private String name;
    private LocalDate dateOfBirth;                                              //complex attribute
    private CreatureType type;
    private List<String> primalSource = new LinkedList<String>();                    //multi-value attribute
    private int age;                                                            //derived attribute
    private boolean wings;                                                      //optional attribute

    private static int population = 0;                                          //class attribute

    public enum CreatureType { ALL, _Human, _Elve, _Dragon, _Animal}

    private final static List<String> primalSources = new ArrayList<String>();
    static {
        primalSources.add("Sun");
        primalSources.add("Moon");
        primalSources.add("Earth");
        primalSources.add("Sky");
        primalSources.add("Stars");
        primalSources.add("Ocean");
    }

    public Creature(String name, LocalDate dateOfBirth, CreatureType type){
        this.name = name;
        if(name == null)
            throw new IllegalArgumentException("A name of creature must be defined!");
        setDateOfBirth(dateOfBirth);
        setCreatureType(type);
        //extent.add(this);
        population++;
    }
                                                                                    //constructor overloading
    public Creature(String name, LocalDate dateOfBirth, CreatureType type, boolean wings, String primalSource){
        this(name, dateOfBirth, type);
        this.wings = wings;
        StringBuilder sb = new StringBuilder();
            this.addPrimalSource(primalSource);
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        if(dateOfBirth == null)
            throw new IllegalArgumentException("We must know the creature`s birthday");
        if(dateOfBirth.isAfter(LocalDate.now()))
            throw new IllegalArgumentException("Looks like you try to register a creature which has not been born yet. Please wait until it comes to the world");
        this.dateOfBirth = dateOfBirth;
    }

    public int getAge(){
        LocalDate today = LocalDate.now();
        Period diff = Period.between(dateOfBirth, today);
        age = diff.getYears();
        return age;
    }

    public void setCreatureType(CreatureType type) {
        if(type == null)
            throw new IllegalArgumentException("A type of creature must be defined!");
        this.type = type;
    }

    public void addPrimalSource(String pSource) {
        if(pSource==null || "".equals(pSource))
            throw new IllegalArgumentException("Must not be null");
        if(!primalSources.contains(pSource))
            throw new IllegalArgumentException("No such primal source");
        this.primalSource.add(pSource);
    }

    public static int getPopulation() { return population; }                                    //class method

    public static List<String> getPrimalSources() { return new ArrayList<String>(primalSources); }

    public boolean hasWings() { return this.wings; }

    public void setWings(boolean wings) { this.wings = wings; }

    public static void setPopulation(int population) { Creature.population = population; }

    public CreatureType getCreatureType() { return type; }

    public String getName() { return name; }

    public void setName(String name) { this.name = name; }

    public LocalDate getDateOfBirth() { return dateOfBirth; }

    //public static List<Creature> getExtent() { return new ArrayList<Creature>(extent); }

    @Override
    public String toString() {                                                                   //method overriding
        String creatureInfo = " ";
        int age = getAge();
        if(primalSource.isEmpty()) {
            creatureInfo =  name + ", " + type + ", " + age + " years old";
        } else
            creatureInfo =  name + ", " + type + ", " + age + " years old. Knows such primal sources: " + primalSource + ". Has wings - " + wings;

        return creatureInfo;
    }
}
