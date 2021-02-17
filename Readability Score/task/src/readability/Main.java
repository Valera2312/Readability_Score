package readability;

import org.w3c.dom.Text;

import javax.xml.transform.sax.SAXResult;
import java.io.File;
import java.io.FileNotFoundException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        File file = new File(args[0]);

        Scanner scanner = new Scanner(file);
        StringBuilder textB = new StringBuilder();
        while (scanner.hasNext()){

            String line = scanner.nextLine();
            textB.append(line);
        }

        scanner.close();
        file.deleteOnExit();

        String textA = String.valueOf(textB);

        int characters = textA.replaceAll(" |\n|\t","").split("").length;

        String[] ArrayWords = textA.split(" |\n|\t");

        int words = ArrayWords.length;
        int sentences = textA.split("\\.|\\?|!").length;

        //Находжение кололичство слов с более 2 слогами--------------------------------------
        int count = 0;
        int countPolysyllables = 0;
        int countSyllables = 0;

        for(int i = 0 ; i < words; i++) {

            countSyllables +=  countSyllablesM(ArrayWords[i]);

            if(countSyllablesM(ArrayWords[i])>2){
                countPolysyllables++;
            }

/*
                ArrayWords[i] = ArrayWords[i].replaceAll("e\\b", "");
                ArrayWords[i] = ArrayWords[i].replaceAll("you", "a");
                ArrayWords[i] = ArrayWords[i].replaceAll("[aeiouy]{2}", "a");
                ArrayWords[i] = ArrayWords[i].replaceAll(" th ", " a ");
                ArrayWords[i] = ArrayWords[i].replaceAll(",", "");

                Pattern splitter = Pattern.compile("[^aeiouy]*[aeiouy]+");
                Matcher m = splitter.matcher(ArrayWords[i]);
                while (m.find()) {
                    count++;
                }
                countSyllables += count;

                if (count > 2) {
                    countPolysyllables++;
                }
                count = 0;
*/
        }
//-------------------------------------------------------------------------------------------

        //---------Расчет очков-------------------------------------------------------------------------
        float score = (float) (4.71 * characters/words + 0.5 * words/sentences - 21.43);
        float  FKRScope = (float) (0.39 * words/sentences + 11.8 * countSyllables / words - 15.59);
        float  SMOGScope = (float) (1.043 * Math.sqrt(countPolysyllables * 30 / sentences) + 3.1291);

        //for CLScope ------------------------------------------------
        float L = characters / words * 100;
        float S = sentences / words * 100;
        float CLScope = (float) (0.0588 * L - 0.296 * S - 15.8);

        //--------------------------------------------------------------
        //-----------------------------------------------------------------------------------


        BigDecimal result = new BigDecimal(score);
        result = result.setScale(2, BigDecimal.ROUND_DOWN);

        System.out.println("Words: " + words);
        System.out.println("Sentences: "+ sentences);
        System.out.println("Characters: " + characters);
        System.out.println("Syllables: " + countSyllables);
        System.out.println("Polysyllables: "  +  countPolysyllables);

        System.out.println("Enter the score you want to calculate (ARI, FK, SMOG, CL, all):");

        Scanner scanner1 = new Scanner(System.in);
        String choice = scanner1.nextLine();

        int w = (int) (6 + score);
        int r = (int) (6 + FKRScope);
        int t = (int) (6 + SMOGScope);
        int y = (int) (6 + CLScope);

        switch (choice){
            case "all":
                System.out.println("Automated Readability Index: " + score + " (about " + w +"-year-olds).");
                System.out.println("Flesch–Kincaid readability tests: " + FKRScope + " (about " + r +"-year-olds).");
                System.out.println("Simple Measure of Gobbledygook: " + SMOGScope + " (about " + t +"-year-olds).");
                System.out.println("Coleman–Liau index: " + CLScope + " (about " + y +"-year-olds).");
        }

        //System.out.println("The score is: " + result);

    }

    public static int countSyllablesM(String word)
    {
        char[] vowels = { 'a', 'e', 'i', 'o', 'u', 'y' };
        char[] currentWord = word.toCharArray();
        int numVowels = 0;
        boolean lastWasVowel = false;
        for (char wc : currentWord) {
            boolean foundVowel = false;
            for (char v : vowels)
            {
                //don't count diphthongs
                if ((v == wc) && lastWasVowel)
                {
                    foundVowel = true;
                    lastWasVowel = true;
                    break;
                }
                else if (v == wc && !lastWasVowel)
                {
                    numVowels++;
                    foundVowel = true;
                    lastWasVowel = true;
                    break;
                }
            }
            // If full cycle and no vowel found, set lastWasVowel to false;
            if (!foundVowel)
                lastWasVowel = false;
        }
        // Remove es, it's _usually? silent
        if (word.length() > 2 &&
                word.substring(word.length() - 2) == "es")
            numVowels--;
            // remove silent e
        else if (word.length() > 1 &&
                word.substring(word.length() - 1) == "e")
            numVowels--;
        return numVowels;
    }


}



