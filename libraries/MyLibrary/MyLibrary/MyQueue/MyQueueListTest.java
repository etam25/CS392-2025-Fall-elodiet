import MyQueue.*;

public class MyQueueListTest {
    public static void main(String[] args) {
        MyQueueList<Integer> itms = new MyQueueList();
        itms.enqueexn(1);
        itms.enqueexn(2);
        itms.enqueexn(3);
        itms.dequeexn();
        itms.dequeexn();
        itms.enqueexn(4);
        itms.enqueexn(5);
        itms.SystemOutPrint();
        System.out.println();
        System.out.print("MyQueueRev(");
        itms.irforitm ( 
            (i, itm) -> { 
                if (i > 0) {
                    System.out.print(",");
                }
                System.out.print(itm.toString());
            }
        ); 
        System.out.print(")");
        System.out.println();
    }
}
