import java.io.IOException;
import java.net.URL;
import java.util.Scanner;

public class WebScraper {
    /**
     * Retrieve contents from a URL and return them as a string.
     *
     * @param url url to retrieve contents from
     * @return the contents from the url as a string, or an empty string on error
     */
    public static String urlToString(final String url, final String specificWord) {
        Scanner urlScanner;
        try {
            urlScanner = new Scanner(new URL(url).openStream(), "UTF-8");
        } catch (IOException e) {
            return "";
        }
        String contents = urlScanner.useDelimiter("\\A").next();
        urlScanner.close();
        int wordCount = 0;
        for (int i = 0; i < contents.length(); i++) {
            for (int j = i; j < contents.length(); j++) {
                if (contents.substring(j,j+1).equals(" ")) {
                    wordCount++;
                    i = j+1;
                    break;
                }
            }
        }
        System.out.println("word count = " + wordCount);
        int wordLength = specificWord.length();
        int specificCount = 0;
        for (int k = 0; k < contents.length() - wordLength; k++) {
            if (contents.substring(k,k + wordLength).equals(specificWord)) {
                specificCount++;
            }
        }
        System.out.println("word count for " + specificWord + " is " + specificCount);
        return contents;
    }



    public static void main(String[] unused) {
        System.out.println(urlToString("https://www.bls.gov/tus/charts/chart9.txt","Data"));
    }


}
