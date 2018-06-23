import java.io.IOException;

import de.mpii.clausie.ClausIE;
import de.mpii.clausie.Clause;
import de.mpii.clausie.Proposition;

public class Example {
    public static void main(String[] args) throws IOException {
        ClausIE clausIE = new ClausIE();
        clausIE.initParser();
        clausIE.getOptions().print(System.out, "# ");

        // input sentence
        // String sentence =
        // "Bell, a telecommunication company, which is based in Los Angeles, makes and distributes electronic, computer and building products.";
        // String sentence = "There is a ghost in the room";
        // sentence = "Bell sometimes makes products";
        // sentence = "By using its experise, Bell made great products in 1922 in Saarland.";
        // sentence = "Albert Einstein remained in Princeton.";
        // sentence = "Albert Einstein is smart.";
        //String sentence = " Bell makes electronic, computer and building products.";
         String sentence = "Rep. Jack Kemp, a New York Republican, urged President Reagan to oppose any legislation to impose a tax on stock transactions. In a statement Kemp called the tax \"a money grab to permit Democrats to begin another government spending spree.\" \"I call upon President Reagan to signal immediately that he will veto any tax increase on stock transfers,\" Kemp said. He added that the tax would punish the 50 million stockholders. House Speaker James Wright, a Texas Democrat, has asked congressional tax analysts to look at a 0.25 to 0.5 pct tax on stock trades along with other tax proposals to help reduce the federal budget deficit.";

        System.out.println("Input sentence   : " + sentence);

        // parse tree
        System.out.print("Parse time       : ");
        long start = System.currentTimeMillis();
        clausIE.parse(sentence);
        long end = System.currentTimeMillis();
        System.out.println((end - start) / 1000. + "s");
        System.out.print("Dependency parse : ");
        System.out.println(clausIE.getDepTree().pennString()
                .replaceAll("\n", "\n                   ").trim());
        System.out.print("Semantic graph   : ");
        System.out.println(clausIE.getSemanticGraph().toFormattedString()
                .replaceAll("\n", "\n                   ").trim());

        // clause detection
        System.out.print("ClausIE time     : ");
        start = System.currentTimeMillis();
        clausIE.detectClauses();
        clausIE.generatePropositions();
        end = System.currentTimeMillis();
        System.out.println((end - start) / 1000. + "s");
        System.out.print("Clauses          : ");
        String sep = "";
        for (Clause clause : clausIE.getClauses()) {
            System.out.println(sep + clause.toString(clausIE.getOptions()));
            sep = "                   ";
        }

        // generate propositions
        System.out.print("Propositions     : ");
        sep = "";
        for (Proposition prop : clausIE.getPropositions()) {
            System.out.println(sep + prop.toString());
            sep = "                   ";
        }
    }
}
