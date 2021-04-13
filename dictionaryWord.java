package dsaproject;
import java.io.Serializable;

public class dictionaryWord implements Serializable{
    public dictionaryWord(String name, String meaning, String type){
        this.name = name;
        numberOfDefinitions = 1;
        definitionList = new definitions[numberOfDefinitions];
        definitionList[0] = new definitions(meaning, type);
    }

    public dictionaryWord(){
        this("", "", "");
    }


    private String name;
    private definitions[] definitionList;
    private int numberOfDefinitions;

    protected class definitions implements Serializable {
        definitions(String type, String meaning){
            this.meaning = meaning;
            this.type = type;
        }
        private String meaning;
        private String type;
     }

     public void addDefinition(String type, String meaning) {
        numberOfDefinitions++;
        definitions[] temp = new definitions[numberOfDefinitions];
        for(int i = 0; i < numberOfDefinitions; i++){
            temp[i] = new definitions(null, null);
        }
        System.arraycopy(definitionList, 0, temp, 0, numberOfDefinitions-1);
        temp[numberOfDefinitions - 1].meaning = meaning;
        temp[numberOfDefinitions - 1].type = type;
        definitionList = temp;
    }

    public String getName(){
        return name;
    }

    public String getMeaning(){
        return definitionList[0].meaning;
    }


    public String getType(){
        return definitionList[0].type;
    }

    public void setName(String n){
        name = n;
    }

    public void printWord(){
        System.out.println(name);
        int i = 1;
        for(definitions def:definitionList){
            System.out.println(i++);
            System.out.println("\t" + def.type);
            System.out.println("\t" + def.meaning);
        }
    }
}
