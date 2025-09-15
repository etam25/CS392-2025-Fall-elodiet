public class MyStackListTest {
    public static void main(String[] args) {
	MyStack<Integer> itms = new MyStackList();
	itms.push$exn(1);
	itms.push$exn(2);
	itms.push$exn(3);
	itms.pop$exn(); itms.pop$exn();
	itms.push$exn(4);
	itms.push$exn(5);
	itms.System$out$print(); System.out.println();
    }
}
