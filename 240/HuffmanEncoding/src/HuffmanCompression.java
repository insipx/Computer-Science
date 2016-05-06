/**
 * Created by insidious on 5/5/16.
 */
public class HuffmanCompression implements HuffmanCompressionInterface {


    public String compress(String str) {
        String result = "";
        HuffmanTree tree = new HuffmanTree(str);

        for(int i = 0; i < str.length(); i++){
           char c = str.charAt(i);
            result += tree.getEncodedSymbol(c);
            //whitespace here for output
            result += " ";
        }

        return result;
    }


    public int getSize(String str) {
        return str.split("\\s+").length;
    }
}
