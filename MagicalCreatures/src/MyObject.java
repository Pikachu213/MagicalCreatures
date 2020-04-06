import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

public class MyObject implements Serializable {
    private static final long serialVersionUID = 1L;
    private static Map<Class, List<MyObject>> allExtents = new Hashtable<>();

    public MyObject() {
        List<MyObject> extent = null;
        Class theClass = this.getClass();

        if(allExtents.containsKey(theClass)){
            // An extent of this class already exist
            extent = allExtents.get(theClass);
        }
        else{
            // An extent does not exist - create a new one
            extent = new ArrayList<>();
            allExtents.put(theClass, extent);
        }
        extent.add(this);
    }

    public static void writeExtents(ObjectOutputStream stream) throws IOException {
        stream.writeObject(allExtents);
    }

    public static void readExtents(ObjectInputStream stream) throws  IOException, ClassNotFoundException{
        allExtents = (Hashtable) stream.readObject();
    }

    public static void showExtent(Class theClass) throws Exception {
        List<MyObject> extent = null;
        if(allExtents.containsKey(theClass)) {
            // Extent of this class already exist
            extent = allExtents.get(theClass);
        }
        else {
            throw new Exception("Unknown class " + theClass);
        }
        System.out.println("Extent of the class: " + theClass.getSimpleName());
        for(Object obj : extent) {
            System.out.println(obj + "\n");
        }
    }

}