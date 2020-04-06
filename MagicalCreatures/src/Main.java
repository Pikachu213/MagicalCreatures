import java.io.*;
import java.time.LocalDate;

public class Main {
    public static void main(String[] args) throws Exception{
        if(new File("Creatures").isFile())
        {
            try
            {
                FileInputStream fileInput = new FileInputStream("Creatures");
                ObjectInputStream streamInput = new ObjectInputStream(fileInput);

                MyObject.readExtents(streamInput);
                streamInput.close();
                fileInput.close();
            }
            catch(IOException i){
                i.printStackTrace();
                return;
            }
            catch(ClassNotFoundException c){
                System.out.println("Class not found.");
                c.printStackTrace();
                return;
            }
        }

        LocalDate date = LocalDate.of(1800, 1, 1);
        Creature creature = new Creature("Rayla", date, Creature.CreatureType._Elve, false, "Moon");

        LocalDate date1 = LocalDate.of(2002, 1, 1);
        Creature creature1 = new Creature("Callum", date1, Creature.CreatureType._Human, true, "Sky");
        creature1.addPrimalSource("Stars");

        LocalDate date2 = LocalDate.of(2008, 1, 1);
        Creature creature2 = new Creature("Ezran", date2, Creature.CreatureType._Human);
        MyObject.showExtent(Creature.class);

        //System.out.println(Creature.getExtent());
        //

        try
        {
            FileOutputStream fileOutput = new FileOutputStream("Creatures");
            ObjectOutputStream StreamOutput = new ObjectOutputStream(fileOutput);
            MyObject.writeExtents(StreamOutput);
            StreamOutput.close();
            fileOutput.close();
        }
        catch(IOException i)
        {
            i.printStackTrace();
        }

        System.out.println("The population of Xadia now is " + Creature.getPopulation() + " persons");

    }
}
