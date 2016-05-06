/**
 * Created by insidious on 5/5/16.
 */
public class HuffmanCompression{


    public static String compress(String str) {
        String result = "";
        HuffmanTree tree = new HuffmanTree(str);

        for(int i = 0; i < str.length(); i++){
           char c = str.charAt(i);
            result += tree.getEncodedSymbol(c);
            result += " ";
        }

        return result;
    }

    public String decompress(String str) {
        String[] deStr = str.split("\\s+");



        return null;
    }

    public int getSize(String str) {
        return str.split("\\s+").length;
    }
}
