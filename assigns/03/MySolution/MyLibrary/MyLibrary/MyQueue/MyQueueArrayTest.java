package MyLibrary.MyLibrary.MyQueue;

public class MyQueueArrayTest {
    public static void main(String[] args) {
        MyQueueArray<Integer> itms = new MyQueueArray(10);
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
        itms.irforitm( 
            (i, itm) -> {
                if (i>0) {
                    System.out.print(",");
                }
                System.out.print(itm.toString());
            }
        ); System.out.print(")");
        System.out.println();
    }
}
