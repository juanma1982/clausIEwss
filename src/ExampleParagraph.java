/*
 * JMR: example
 * */
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import ar.edu.unlp.AbbreviationDic;
import de.mpii.clausie.ClausIE;
import de.mpii.clausie.Proposition;

public class ExampleParagraph {
    public static void main(String[] args) throws IOException {
        ClausIE clausIE = new ClausIE();
        clausIE.initParser();
        clausIE.getOptions().print(System.out, "# ");
        List<Proposition> propositionList=new ArrayList<Proposition>();
        AbbreviationDic paragraph = new AbbreviationDic();

        // input sentence
        // String sentence =
        // "Bell, a telecommunication company, which is based in Los Angeles, makes and distributes electronic, computer and building products.";
        // String sentence = "There is a ghost in the room";
        // sentence = "Bell sometimes makes products";
        // sentence = "By using its experise, Bell made great products in 1922 in Saarland.";
        // sentence = "Albert Einstein remained in Princeton.";
        // sentence = "Albert Einstein is smart.";
        String paragraphStr = "Rep. Jack Kemp, a New York Republican, urged President Reagan to oppose any legislation to impose a tax on stock transactions. In a statement Kemp called the tax \"a money grab to permit Democrats to begin another government spending spree.\" \"I call upon President Reagan to signal immediately that he will veto any tax increase on stock transfers,\" Kemp said. He added that the tax would punish the 50 million stockholders. House Speaker James Wright, a Texas Democrat, has asked congressional tax analysts to look at a 0.25 to 0.5 pct tax on stock trades along with other tax proposals to help reduce the federal budget deficit.";
        String[] sentences = paragraph.splitIntoSentences(paragraphStr);
       
        System.out.print("Parse time       : ");
        long start = System.currentTimeMillis();
        for (String sentence : sentences) {
        	 System.out.println("Input sentence   : " + sentence);
        	 clausIE.parse(sentence);
        	 clausIE.detectClauses();
             clausIE.generatePropositions();
        	 for (Proposition prop : clausIE.getPropositions()){
        		 propositionList.add(prop);
        	 }
		}
        long end = System.currentTimeMillis();
        System.out.println((end - start) / 1000. + "s");

        // generate propositions
        System.out.print("Propositions     : ");
        String sep = "";
        for (Proposition prop : propositionList) {
            System.out.println(sep + prop.toString());
            sep = "                   ";
        }
    }
}
