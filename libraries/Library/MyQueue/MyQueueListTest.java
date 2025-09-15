public class MyQueueListTest {
    public static void main(String[] args) {
	MyQueue<Integer> itms = new MyQueueList();
	itms.enque$exn(1);
	itms.enque$exn(2);
	itms.enque$exn(3);
	itms.deque$exn(); itms.deque$exn();
	itms.enque$exn(4);
	itms.enque$exn(5);
	itms.System$out$print(); System.out.println();
    }
}
