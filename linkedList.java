package dsaproject;
import java.io.Serializable;

public class linkedList implements Serializable {
    protected class node implements Serializable {
        public dictionaryWord word;
        public node next;
        public node(dictionaryWord w, node nextNode){
            word = w;
            next = nextNode;
        }
    };
    private node sentinel;
    private int listSize;

    public linkedList(){
        sentinel = new node(null, null);
        listSize = 0;
    }

    public void insertAtHead(dictionaryWord newWord){
        sentinel.next = new node(newWord, sentinel.next);
        listSize++;
    }

    public dictionaryWord getWord(String searchTerm){
        //if(isEmpty()){return null;}
        node traverser = sentinel;
        while(traverser.next != null){
            traverser = traverser.next;
            if(traverser.word.getName().equals(searchTerm)){return traverser.word;}
            //System.out.println(traverser.word.getName());
        }
        return null; //if word not found return null
    }

    public node getFirstElement(){
        return sentinel.next;
    }

    public void removeFromHead(){
        sentinel.next = sentinel.next.next;
        listSize--;
    }

    public int getSize(){
        return listSize;
    }

    public boolean isEmpty(){
        return listSize == 0;
    }

    public void printList(){
        node traverser = sentinel;
        while(traverser.next != null){
            traverser = traverser.next;
            traverser.word.printWord();
        }
    }
}

