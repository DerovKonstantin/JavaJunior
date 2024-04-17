package Control;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class dataControl {

    public static void save(String file, Object object) throws IOException {

        try (FileOutputStream fileOut = new FileOutputStream(file);
        ObjectOutputStream out = new ObjectOutputStream(fileOut)){
            out.writeObject(object);
            System.out.println("Объект StudentGroupData сериализован.");
        } 
    }

    public static Object load(String file) throws ClassNotFoundException, IOException {

        try (FileInputStream fileIn = new FileInputStream(file);
             ObjectInputStream in = new ObjectInputStream(fileIn))
        {
            // loadedGroup = (StudentGroup)in.readObject();
            System.out.println("Объект StudentGroupData десериализован.");
            return in.readObject();
        }
    }
    
}
