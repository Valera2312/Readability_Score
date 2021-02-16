package readability;

import javax.xml.transform.sax.SAXResult;
import java.io.File;
import java.io.FileNotFoundException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        File file = new File(args[0]);
        Scanner scanner = new Scanner(file);
        StringBuilder textB = new StringBuilder();
        while (scanner.hasNext()){

            String line = scanner.nextLine();
            textB.append(line);
        }
        String textA = String.valueOf(textB);

        int characters = textA.replaceAll(" |\n|\t","").split("").length;
        int words = textA.split(" |\n|\t").length;
        int sentences = textA.split("\\.|\\?|!").length;

        float score = (float) (4.71 * characters/words + 0.5 * words/sentences - 21.43);


        BigDecimal result = new BigDecimal(score);
        result = result.setScale(2, BigDecimal.ROUND_DOWN);

        System.out.println("Words: " + words);
        System.out.println("Sentences: "+ sentences);
        System.out.println("Characters: " + characters);
        System.out.println("The score is: " + result);

        if(score >= 12 && score < 13){

            System.out.println("This text should be understood by 18-24-year-olds.");
        }

        else if(score >= 5 && score < 6){

            System.out.println("This text should be understood by 11-12-year-olds.");
        }

        else if(score >= 4 && score < 5){

            System.out.println("This text should be understood by 9-10-year-olds.");
        }

        else if(score >= 3 && score < 4){

            System.out.println("This text should be understood by 7-9-year-olds.");
        }
        else if(score >= 2 && score < 3){

            System.out.println("This text should be understood by 6-7-year-olds.");
        }
        else if(score >= 11 && score < 12){

            System.out.println("This text should be understood by 17-18-year-olds.");
        }


            scanner.close();
            file.deleteOnExit();

    }




    }



