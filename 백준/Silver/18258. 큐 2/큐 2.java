import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.rmi.UnexpectedException;

public class Main {

    private static final BufferedReader br = new BufferedReader(
        new InputStreamReader(System.in)
    );

    private static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        int N = Integer.parseInt(br.readLine());
        
        QueueInt test = new QueueInt(N);

        for (int count = 0; count < N; count++) {
            String command = br.readLine();

            if (command.indexOf("push") != -1) {
                int value = Integer.parseInt(command.split(" ")[1]);
                test.push(value);
                continue;
            }

            if (command.indexOf("pop") != -1) {
                sb.append(String.valueOf(test.pop()));
            }

            else if (command.indexOf("size") != -1) {
                sb.append(String.valueOf(test.getSize()));
            } 

            else if (command.indexOf("empty") != -1) {
                sb.append(
                    test.isEmpty() ? "1" : "0"
                );
            }

            else if (command.indexOf("front") != -1) {
                sb.append(String.valueOf(test.peekTail()));
            }

            else if (command.indexOf("back") != -1) {
                sb.append(String.valueOf(test.peekHead()));
            }
            
            else throw new UnexpectedException("Unexpected command encountered");

            sb.append("\n");
        }

        System.out.println(sb.toString());
    }
}

class QueueInt {
    private int[] array;
    private int size;
    private int capacity = 1_000;
    private int index_head;
    private int index_tail;

    private void init() {
        index_head = index_tail = size = 0;
        array = new int[capacity];
    }

    public QueueInt() {
        init();
    }
    public QueueInt(int capacity) {
        this.capacity = capacity;
        init();
    }

    public void push(int data) throws OutOfMemoryError {
        if (isFull())   extend();

        this.array[index_head] = data;
        index_head = (index_head + 1) % capacity;
        size++;
    }
    public int pop() {
        if (isEmpty())  return -1;
        int value = peekTail();
        index_tail = (index_tail + 1) % capacity;
        size--;
        return value;
    }

    private void extend() throws OutOfMemoryError {
        int[] arrayLarger = new int[capacity * 2];

        int index = index_tail;
        int count = 0;
        while (count != size) {

            arrayLarger[count] = this.array[index];
            index = (index + 1) % capacity;

            count++;
        }

        this.array = arrayLarger;
        this.capacity *= 2;
        this.index_tail = 0;
        this.index_head = size;
    }

    
    public int peekHead()   {return size == 0 ? -1 : array[index_head - 1];}
    public int peekTail()   {return size == 0 ? -1 : array[index_tail];}
    
    public boolean isFull() {return size == capacity ? true : false;}
    public boolean isEmpty(){return size == 0 ? true : false;}
    public int getSize()    {return size;}
}
