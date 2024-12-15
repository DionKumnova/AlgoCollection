package assignment;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

class Trie {

    /**
     * This function inserts a word in to the trie
     * @param word you can assume the word is lowercase
     */

    Node firstNode = new Node("");

    public Trie(){
    }

    class Node{

        private String letter;
        private ArrayList<Node> children = new ArrayList<>();
        private Boolean isLetterFinal = false;

        public Node(String letter){
            this.letter = letter;
        }

        public String getLetter() {
            return letter;
        }

        public void printChildren() {
            for (Node child : children) {
                System.out.println(child.letter);
            }
        }

        public void sortChildren() {
            Collections.sort(children, Comparator.comparing(Node::getLetter));
        }
    }

    public void insert(String word){
        
        String[] parts = word.split("");

        Node pointer = firstNode;

        int teller = 1;
        for(String s : parts){

            Node newNode = new Node(s);
            Boolean found = false;

            if(teller == parts.length){
                newNode.isLetterFinal = true;
            } 

            teller++;

            for(Node children : pointer.children){

                if(children.letter.equals(s)){
                    if(teller == parts.length+1){
                        children.isLetterFinal = true;
                    } 
                    pointer = children;
                    found = true;
                    break;
                } 
            }
            
            if(found){continue;}

            pointer.children.add(newNode);
            pointer = newNode;
        }
    }

    /**
     * This function checks if a word has been inserted into the trie
     * @param word you can assume it's always lowercase
     * @return returs true if the word is found, returns flase if the word is not found, if hight is inserted and the function recives high it should return false, only inserted words should give true
     */
    public Boolean lookUp(String word){
        
        String[] parts = word.split("");

        Node pointer = firstNode;

        Boolean found = false;

        for(String i : parts){

            found = false;

            for(Node n : pointer.children){

                if(i.equals(n.letter)){
                    pointer = n;
                    found = true;
                    break;
                }
                found = false;
            }
            if(!found){return false;}
        }
        if(pointer.isLetterFinal == true){
        return found; 
        }
        return false;//you can remove this line
    }

    /**
     * This function prints the trie in alphabetical order
     * example:
     * internet: (internet)
     * interview: (inter(net)(view))
     * inter: (inter(net)(view))
     * @return retuns a string that shows the state of the trie
     */
    public String printTree(){

        return "(" + findWord("", firstNode, false) + ")";
    }


    public String findWord(String result, Node current, boolean subTreeEnd){

        result += current.letter;

        if(!(current.children.size()>1) && current.isLetterFinal){result += ")";}

        if(current.isLetterFinal && subTreeEnd && current.children.size()<2){result += ")";}

        current.sortChildren();
    
        for (int i = 0; i < current.children.size(); i++) {
            if(current.children.size()>1){
                subTreeEnd = false;
                result+="(";
            }

            if(i != 0  && !"".equals(current.letter)){
                subTreeEnd = true;
            }

            result = findWord(result, current.children.get(i), subTreeEnd);  
        }     
        return result;
    }
}