public class SimpleList{

  private class Node{
    Node next;
    int item;

    public Node(){
      // PROMISES: Creates an empty node
    }

    public Node(int d){
      // PROMISES: initializes a new node
      item = d;
    }
  }

  public Node headM;
  public int sizeM;

  public SimpleList(){
    // PROMISES: Creates an empty list
    headM = null;
    sizeM = 0;
  }

  public int size(){
    // PROMISES: Returns the size of the list
    return sizeM;
  }

  public int get(int n){
    /* PROMISES:
          An item is return at the nth position in the list
          if n is less than 0 or greater than or equal to sizeM, gives error
          message: "Illegal Access" and Terminates the program.*/
    if(n < 0 || n >= sizeM){
        System.out.println("Illegal Access. program Terminates...");
        System.exit(1);
    }
    Node p = headM;
    for(int i= 0; i < n; i++)
        p = p.next;

    return p.item;
  }

  public void push_front(int new_item){
    /* PROMISES:
          Adss a node with an item to the beginning of the list, and increments sizeM*/
    Node new_node = new Node ();
    new_node.item = new_item;
    new_node.next = headM;
    headM = new_node;
    sizeM++;
  }

  public void push_back(int new_item){
    /* PROMISES:
          Adss a node with an item to the end of the list, and increments sizeM*/
    Node new_node = new Node (new_item);
    if(new_node == null){
      System.out.println("No memory available to create a node");
      System.exit(1);
    }
    if(headM == null){
      new_node.next = headM;
      headM = new_node;
    }
    else{
      Node p = headM;
      while(p.next != null){
        p = p.next;
      }
      p.next = new_node;
      new_node.next = null;
    }
    sizeM++;
  }

  public void set (int n, int v){
    // PROMISES: Puts the value of vat the i-th position
    if(n < 0 || n >= sizeM)
      System.out.println("Illegal access, program terminated");

    Node p = headM;
    for(int i = 0; i < n; i++)
      p = p.next;

    p.item = v;
  }

  public void remove(int n){
    // PROMISES: Does nothing if n < 0 or n > sizeM-1. Otherwise, if list is not empty, removes the node at the position n
    if(headM == null || n < 0 || n >= sizeM){return;}
    Node to_be_deleted;
    Node before;

    if(n == 0){
      to_be_deleted = headM;
      headM = headM.next;
    }
    else{
      before = headM;
      to_be_deleted = before.next;

      int i = 1;
      while (i < n){
        before = to_be_deleted;
        to_be_deleted = before.next;
        i++;
      }
      before.next = to_be_deleted.next;
    }
    sizeM--;
  }

  public void insert(int itemA, int n){
    /* PROMISES:
          A node with a copy of theItem is inserted at the nth position, and sizeM
          will be incremented if the operation of insert was successfull
          if n == sizeM calls push_back
          if n == 0 calls push_front
          if n < 0 or n > sizeM returns and does nothing
    */
    if(n < 0 || n > sizeM) return;
    else if(n == 0) push_front(itemA);
    else if(n == sizeM) push_back(itemA);
    else{
      Node new_node = new Node ();
      if(new_node == null){
        System.out.println("Sorry, memory is unavialable to create a new node");
        return;
      }
      new_node.item = itemA;

      Node before = headM;
      Node after = headM.next;
      int i = 1;
      while(i < n){
        before = after;
        after = after.next;
        i++;
      }
      new_node.next = after;
      before.next = new_node;
      sizeM++;
    }
  }

  public void clear(){
    // PROMISES: Deallocate all nodes, and sets headM to zero, and size to zero
    Node p = headM;
    while(p != null){
      headM = headM.next;
      p = headM;
    }
    headM = null;
    sizeM = 0;
  }

  public void print(SimpleList list){
    // PROMISES : display items in the list
    for(int i = 0; i < list.size(); i++)
      System.out.printf(list.get(i) + " ");
    System.out.printf("\n");
  }

  public static void main(String [] args){
    SimpleList list = new SimpleList();

    System.out.println("List just after creation -- is empty.");
    list.print(list);

    list.push_front(50);
    System.out.println("After calling push_front. list must have: 50");
    list.print(list);

    list.push_back(440);

    list.set(0, 770);
    System.out.println("After calling push_back and set function, list must have 770 440");
    list.print(list);

    list.push_back(330);
    list.push_back(220);
    list.push_back(110);
    System.out.println("After three more calls to push_back, list must have: 770, 440, 330, 220, 110");
    list.print(list);

    list.remove(0);
    list.remove(2);
    System.out.println("After removing two nodes, list must have 440, 330, 110");
    list.print(list);

    list.insert(40, 3);
    list.insert(20, -1);
    list.insert(30, 30000);
    list.insert(10, 0);
    list.insert(33, 2);
    System.out.println("Two more nodes inserted, mush have 10, 440, 33, 330, 110, 40");
    list.print(list);

    list.remove(0);
    list.remove(1);
    list.remove(2);
    list.remove(3);
    list.remove(4);
    list.remove(5);
    System.out.println("After six removes, list must have: 440, 330, 40");
    list.print(list);

    list.clear();
    System.out.println("After call to clear, list must be empty");
    list.print(list);

    list.push_back(331);
    list.push_back(221);
    list.push_back(111);
    System.out.println("After three calls to push_back, list mush have: 331, 221, 111");
    list.print(list);
  }
}
