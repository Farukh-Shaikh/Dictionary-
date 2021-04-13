package dsaproject;

import java.io.*;
import java.util.Scanner;

class reader{
    public hashTable readFromSerializedFile(){
        hashTable ht = new hashTable();
        try {
            String inpath = "./resources/data.ser";
            File inf = new File(inpath);
            ObjectInputStream is = new ObjectInputStream(new BufferedInputStream(new FileInputStream(inf)));
            ht =(hashTable) is.readObject();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ht;
    }

    public void readFromDict(){
        String inpath = "./resources/dictionary.csv";
        String outpath = "./resources/data.ser";
        hashTable ht = new hashTable();
        try{
            File inf = new File(inpath);
            File outf = new File(outpath);
            BufferedReader reader = new BufferedReader(new FileReader(inf));
            String line = null;
            String[] splits = new String[3];
            while((line = reader.readLine()) != null){
                dictionaryWord dict;
                splits[0] = "";
                splits[1] = "";
                splits[2] = "";
                int counter = 0;
                int i = 0;
                char c;
                while (counter < 6) {
                    if ((c = line.charAt(i)) == '"') { counter++; }
                    else if (counter == 1) { splits[0] += c; }
                    else if (counter == 3) { splits[1] += c; }
                    else if (counter == 5) { splits[2] += c; }
                    i++;
                }
                dict = new dictionaryWord(splits[0], splits[1], splits[2]);
                ht.insert(dict);
            }
            ObjectOutputStream os = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(outf)));
            os.writeObject(ht);
            os.close();
        }catch(Exception ex){
            ex.printStackTrace();
        }
    }

    public void displayChoices() {
        System.out.println("Select choice:");
        System.out.println("1. Search word");
        System.out.println("2. Quit");
    }
}



public class dictionaryToHashTable{
    public static void main(String[] args){
        int choice = 0;
        reader misc = new reader();
        misc.readFromDict();
        hashTable ht = misc.readFromSerializedFile();
        Scanner sc = new Scanner(System.in);
        while(choice != 2){
            misc.displayChoices();
            choice = Integer.parseInt(sc.nextLine());
            if(choice == 1){
                System.out.println("Enter search word:");
                String searchTerm = sc.nextLine();
                ht.search(searchTerm);
            }
        }
    }
}

