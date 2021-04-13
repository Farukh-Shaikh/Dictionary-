package dsaproject;
import java.io.Serializable;

public class hashTable implements Serializable {
    private int maxListSize;
    private int tableSize;
    private linkedList[] table;

    public hashTable(){
        maxListSize = 32;
        tableSize = 64;
        table = new linkedList[tableSize];
        for(int i = 0; i < tableSize; i++){
            table[i] = new linkedList();
        }
    }

    public void insert(dictionaryWord word, linkedList[] hTable){
        int tempKey = Math.abs(word.getName().hashCode());//hashCode function may return a negative value
        int key = tempKey % hTable.length;
        //System.out.println(word.getName());
        //check if word already in hash table
        //System.out.println("htable length" + hTable.length + "key" + key);
        //if(word.getName().equals("Acutilobate")){
            //doNo
        //}
        dictionaryWord dict = hTable[key].getWord(word.getName());
        if(dict != null){
            dict.addDefinition(word.getType(), word.getMeaning());
        }else {
            hTable[key].insertAtHead(word);
            if (hTable[key].getSize() > maxListSize) {
                resize();
            }
        }
    }

    public void insert(dictionaryWord word){
        insert(word, table);
    }

    private void moveElementsToNewTable(linkedList[] newTable, int oldSize){
        for(int i = 0; i < oldSize; i++){
            while(!table[i].isEmpty()){
                insert(table[i].getFirstElement().word, newTable);
                table[i].removeFromHead();
            }
        }
    }

    public void resize(){

        //System.out.println("Resize called at " + tableSize + "with table elements:");
        //printTable();
        int oldSize = tableSize;
        tableSize *= 2;
        linkedList[] tempList = new linkedList[tableSize];
        for(int i = 0; i < tableSize; i++){
            tempList[i] = new linkedList();
        }
        moveElementsToNewTable(tempList, oldSize);
        table = tempList;
    }

    public void search(String searchTerm){

        int key = Math.abs(searchTerm.hashCode()) % tableSize;
        //System.out.println("tableSize " + tableSize + " key " + key);
        dictionaryWord word = table[key].getWord(searchTerm);
        if(word != null){
            word.printWord();
        }
        else {
            System.out.println("This word does not exist in the dictionary.");
        }
    }

    public int getTableSize(){
        return tableSize;
    }


    public void printTable(){
        for(int i = 0; i < tableSize; i++){
            table[i].printList();
        }
    }
}

