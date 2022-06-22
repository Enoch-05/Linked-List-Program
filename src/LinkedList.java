//These are all the procedures to be performed in a linked list.

import java.util.Scanner;
import java.util.Objects;
public class LinkedList {
    //Using object-oriented programming to create a RECORD of type 'Node'.
    static class Node {

        int Value;
        int Ptr;

        public Node (int v, int p) {
            Value = v;
            Ptr = p;
        }

        public int GetValue (){
            return Value;
        }

        public int GetPtr (){
            return Ptr;
        }

        public void SetValue(int v){
            Value = v;
        }

        public void SetPtr(int p){
            Ptr = p;
        }
    }

    //Declaration.
    public static int StartPointer = -1;
    public static int PreviousPointer;
    public static int ThisNodePointer;
    public static int FreeListPtr = 0;
    public static Node [] MyLinkedList = new Node [10];
    public static int index;
    public static int NullPointer = -1;
    public static int NewNodePointer;
    public static boolean Action = false;
    public static int item;

    public static void main(String[] args) {
        Scanner myObj = new Scanner (System.in);
        String Reply;
        int Find;

        //Initialise the linked list.
        for (index = 0; index < 9; index++){
            MyLinkedList[index] = new Node(0,index+1);
        }
        MyLinkedList[9] = new Node(0,-1);

        //Asking the user whether they want to insert the node, and asking for the insert value.
        System.out.println("Please enter 'yes' to insert a node. Please enter 'no' to exit the program.");
        Reply = myObj.nextLine();
        if (Objects.equals(Reply, "yes")){
            Action = true;
        }
        while (Action){
            System.out.println("Please enter the item you wish to enter into the linked list.");
            item = myObj.nextInt();
            InsertNode(item);
            System.out.println("The start pointer is " + StartPointer);
            View();
            System.out.println("Enter 'yes' to continue inserting nodes. Enter 'no' to exit.");
            Reply = myObj.next();
            System.out.println(" ");
            if (Objects.equals(Reply, "no")){
                Action = false;
            }
        }

        //Showing what those nodes look like.
        System.out.println("The start pointer is " + StartPointer);
        System.out.println("After those insertions, the linked list is as follows: ");
        View();

        //Searching for a node.
        System.out.println("Please enter the item you would like to find.");
        item = myObj.nextInt();
        Find = SearchLinkedList(item);
        System.out.println(Find);

        //Deleting a node.
        Action = false;
        //Asking whether the user would like to delete some nodes.
        System.out.println("Enter 'D' if you would like to delete a node.");
        Reply = myObj.next();
        if (Objects.equals(Reply, "D")){
            Action = true;
        }
        while (Action){
            System.out.println("Please enter the value you would like to delete.");
            item = myObj.nextInt();
            Delete(item);
            View();
            System.out.println("Enter 'D' to delete an item. Enter 'N' to stop.");
            Reply = myObj.next();
            if (Objects.equals(Reply, "N")){
                Action = false;
                System.out.println("You have left the linked list.");
            }
        }

        //Showing what those nodes now look like.
        if (Objects.equals(Reply, "N")){
            System.out.println("The linked list is as follows");
            View();
        }
    }

    //This procedure is used to insert a node into the linked list.
    public static void InsertNode (int NewItem){
        if (FreeListPtr != NullPointer){
            //There is space in the linked list to insert nodes.
            NewNodePointer = FreeListPtr;
            MyLinkedList[NewNodePointer].SetValue(NewItem);
            FreeListPtr = MyLinkedList[FreeListPtr].GetPtr();

            //Find the insertion point.
            ThisNodePointer = StartPointer;
            PreviousPointer = StartPointer;
            while ((ThisNodePointer != NullPointer) && (MyLinkedList[ThisNodePointer].GetValue() < NewItem)){
                PreviousPointer = ThisNodePointer;
                ThisNodePointer = MyLinkedList[ThisNodePointer].GetPtr();
            }
            if (PreviousPointer == ThisNodePointer){
                MyLinkedList[NewNodePointer].SetPtr(StartPointer);
                StartPointer = NewNodePointer;
            } else {
                MyLinkedList[NewNodePointer].SetPtr(MyLinkedList[PreviousPointer].GetPtr());
                MyLinkedList[PreviousPointer].SetPtr(NewNodePointer);
            }
        } else {
            System.out.println("The linked list is now full, no new nodes can be inserted now.");
            Action = false;
        }
    }

    //This procedure deletes a node from the linked list.
    public static void Delete (int Data){
        ThisNodePointer = StartPointer;
        while ((ThisNodePointer != NullPointer) && (MyLinkedList[ThisNodePointer].GetValue() != Data)){
            PreviousPointer = ThisNodePointer;
            ThisNodePointer = MyLinkedList[ThisNodePointer].GetPtr();
        }
        if (ThisNodePointer != NullPointer){
            if (ThisNodePointer == StartPointer){
                StartPointer = MyLinkedList[StartPointer].GetPtr();
            } else{
                MyLinkedList[PreviousPointer].SetPtr(MyLinkedList[ThisNodePointer].GetPtr());
            }
            MyLinkedList[ThisNodePointer].SetPtr(FreeListPtr);
            FreeListPtr = ThisNodePointer;
        } else{
            System.out.println("This data value does not exist in this linked list.");
        }
    }

    //This procedure outputs all nodes.
    public static void View () {
        index = 0;
        ThisNodePointer = StartPointer;
        while (ThisNodePointer != NullPointer) {
            System.out.println("At index " + ThisNodePointer);
            System.out.print("     ");
            System.out.println(MyLinkedList[ThisNodePointer].GetValue());
            System.out.print("     ");
            System.out.println(MyLinkedList[ThisNodePointer].GetPtr());
            ThisNodePointer = MyLinkedList[ThisNodePointer].GetPtr();
        }
    }

    //This function is used to search for an item in the linked list.
    //Returns the index at which it is found, if not found, it returns -1.
    public static int SearchLinkedList (int SearchItem){
        boolean Found = false;
        int SearchIndex = -1;
        ThisNodePointer = StartPointer;

        while ((ThisNodePointer != NullPointer) && (!Found)){
            if (Objects.equals(MyLinkedList[ThisNodePointer].GetValue(), SearchItem)){
                Found = true;
                SearchIndex = ThisNodePointer;
            } else{
                ThisNodePointer = MyLinkedList[ThisNodePointer].GetPtr();
            }
        }
        return SearchIndex;
    }
}