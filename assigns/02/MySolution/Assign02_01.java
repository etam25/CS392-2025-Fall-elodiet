public class Assign02_01 {
    
        public void main(String[] argv) {
            int powerOf2 = 1;
            int bitCount = 1;

            while (powerOf2 > 0) {
                powerOf2 = powerOf2 * 2; // equivalent to a left shift by 2
                bitCount++;
            }

            System.out.println("The bit length of int is: " + bitCount);
        }
}
