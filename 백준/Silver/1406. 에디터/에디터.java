import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.rmi.UnexpectedException;
import java.util.StringTokenizer;

public class Main {

    private static final BufferedReader br = new BufferedReader(
        new InputStreamReader(System.in)
    );

    public static void main(String[] args) throws IOException {
        LinkedListWithCursor<String> test = new LinkedListWithCursor<String>(br.readLine().split(""));

        int M = Integer.parseInt(br.readLine());

        for (int i = 0; i < M; i++) {
            StringTokenizer tokenizer = new StringTokenizer(br.readLine());

            switch (tokenizer.nextToken()) {
                case "L" :
                    test.moveCursorLeft();
                    break;

                case "D" : 
                    test.moveCursorRight();
                    break;

                case "B" : 
                    test.remove();
                    break;

                case "P" : 
                    String letter = tokenizer.nextToken();
                    test.push(letter);
                    break;
            
                default : throw new UnexpectedException("Unexpected command encountered");
            }
        }

        System.out.println(test.toString());
    }
}

class LinkedListWithCursor<T extends CharSequence> {
    private class Node {
        T data;
        Node prev = null;
        Node next = null;;

        public Node(T data)             {this.data = data;}

        public void setNext(Node next)  {this.next = next;}
        public void setPrev(Node prev)  {this.prev = prev;}
        public T getData()              {return data;}

        public void prepend(Node node)  {
            Node prev = this.prev;
            
            if (prev != null)   prev.setNext(node);
            node.setPrev(prev);
            node.setNext(this);
            this.setPrev(node);
        }
        public void append(Node node)   {
            Node next = this.next;

            if (next != null)   next.setPrev(node);
            node.setNext(next);
            node.setPrev(this);
            this.setNext(node);
        }
    }

    private int indexCursor, length;
    private Node nodeCursor, nodeHead;

    /*
    *   [nodeCursor.prev] <-->  [nodeCursor] <-- [indexCursor] --> [nodeCursor.next]
    *
    *   indexCursor always indicates right pos of nodeCursor
    *   therefore if and only if indexCursor == 0, nodeCursor == null.
    */

    public LinkedListWithCursor(T[] array) {

        for (int i = 0; i < array.length; i++)  this.push(array[i]);

        // nodeCursor = nodeHead = new Node(array[0]);

        // for (int i = 1; i < array.length; i++)  this.push(array[i]);
    
        // length = indexCursor = array.length;
    }

    public void moveCursorLeft() {
        if (length == 0 || indexCursor == 0)        return; // no Node exists || cursor on most left

        nodeCursor = nodeCursor.prev;       // if only 1 Node exists & indexCursor were 1, nodeCursor will be set as null
        indexCursor--;
    }
    public void moveCursorRight() {
        if (length == 0 || indexCursor == length)   return; // no Node exists || cursor on most right

        if (nodeCursor == null) nodeCursor = nodeHead;
        else                    nodeCursor = nodeCursor.next;
        indexCursor++;
    }

    public void remove() {
        if (length == 0 || indexCursor == 0)        return; // no Node exists || cursor on most left

        Node prev = nodeCursor.prev;
        Node next = nodeCursor.next;

        if (length == 1)    {           // only 1 Node exists & remove it
            nodeHead = nodeCursor = null;
        }
        else if (indexCursor == 1)  {   // remove nodeHead (which is nodeCursor)
            nodeHead = next;
            nodeCursor.setNext(null);
            nodeCursor = null;
            next.setPrev(null);
        }
        else    {                       // remove ndoeCursor
            nodeCursor.setPrev(null);
            nodeCursor.setNext(null);
            prev.setNext(next);
            if (next != null)   next.setPrev(prev);
            nodeCursor = prev;
        }

        indexCursor--;
        length--;
    }

    public void push(T data) {
        Node nodeNew = new Node(data);
        if (length == 0)    nodeHead = nodeNew;     // no Node were exists, set as nodeHead & nodeCursor
        else if (indexCursor == 0)  {               // push to nodeHead, replace nodeHead as new one
            nodeHead.prepend(nodeNew);
            nodeHead = nodeNew;
        }
        else    nodeCursor.append(nodeNew);         // append newNode to nodeCursor

        nodeCursor = nodeNew;
        indexCursor++;
        length++;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        Node current = nodeHead;

        while (current != null) {
            sb.append(String.valueOf(current.getData()));
            current = current.next;
        }

        return sb.toString();
    }
}
