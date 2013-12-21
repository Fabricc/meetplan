package Vista;

import java.util.Random;

class test{
/**
* @param args the command line arguments
*/
public static void main(String[] args) {

System.out.println(randomString(16));

}
    public static String randomString(int length) {
        Random rand = new Random();
        StringBuffer tempStr = new StringBuffer();
        tempStr.append("");
        for (int i = 0; i < length; i++) {
            int c = rand.nextInt(122 - 48) + 48;
            if ((c >= 58 && c <= 64) || (c >= 91 && c <= 96)) {
                i--;
                continue;
            }
            tempStr.append((char) c);

        }
        return tempStr.toString();
    }


}
