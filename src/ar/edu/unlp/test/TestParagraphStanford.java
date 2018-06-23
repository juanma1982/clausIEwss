package ar.edu.unlp.test;

import static org.junit.Assert.*;


import org.junit.Before;
import org.junit.Test;


import ar.edu.unlp.ParagraphStanford;

public class TestParagraphStanford {
	private ParagraphStanford paragraph;
	private String fulltext = "Rep. Jack Kemp, a New York Republican, urged President Reagan to oppose any legislation to impose a tax on stock transactions. In a statement Kemp called the tax \"a money grab to permit Democrats to begin another government spending spree.\" \"I call upon President Reagan to signal immediately that he will veto any tax increase on stock transfers,\" Kemp said. He added that the tax would punish the 50 million stockholders. House Speaker James Wright, a Texas Democrat, has asked congressional tax analysts to look at a 0.25 to 0.5 pct tax on stock trades along with other tax proposals to help reduce the federal budget deficit.";
	private String fulltextSemicolon = "Rep. Jack Kemp, a New York Republican, urged President Reagan to oppose any legislation to impose a tax on stock transactions; In a statement Kemp called the tax \"a money grab to permit Democrats to begin another government spending spree.\" \"I call upon President Reagan to signal immediately that he will veto any tax increase on stock transfers,\" Kemp said; He added that the tax would punish the 50 million stockholders. House Speaker James Wright, a Texas Democrat, has asked congressional tax analysts to look at a 0.25 to 0.5 pct tax on stock trades along with other tax proposals to help reduce the federal budget deficit.";
	private String[] expectedSentences ={
			"Rep. Jack Kemp, a New York Republican, urged President Reagan to oppose any legislation to impose a tax on stock transactions.",
			"In a statement Kemp called the tax \"a money grab to permit Democrats to begin another government spending spree.\"",
			"\"I call upon President Reagan to signal immediately that he will veto any tax increase on stock transfers,\" Kemp said.",
			"He added that the tax would punish the 50 million stockholders.",
			"House Speaker James Wright, a Texas Democrat, has asked congressional tax analysts to look at a 0.25 to 0.5 pct tax on stock trades along with other tax proposals to help reduce the federal budget deficit."
	};
	private String numberTestText = "0.5 6 45 4.5 452.125 0.236 0.3";
	private String acronymTestText = "U.S.A. S.W. nada A.D.";
	private String abbrevTestText = "Veg. Physiol. etc. nada Venet. test word Astronaut.";
	
	@Before
	public void init(){
		try {
			this.paragraph = new ParagraphStanford();
		} catch (Exception e) {
			e.printStackTrace();
			fail(e.getMessage());
		}
		System.out.println("==> ParagraphStanford created ");
	}
	
	@Test	
	public void testSplitIntoSentencesNumbers() {
		System.out.println("Number test");		
		String aux[] = paragraph.splitIntoSentences(numberTestText);
		for (String string : aux) {
			System.out.println(string);
		}
		if(!aux[0].equals(numberTestText)){
			fail("not equal");
		}
	}
	
	@Test	
	public void testSplitIntoSentencesAcronyms() {
		System.out.println("Acronym test");		
		String aux[] = paragraph.splitIntoSentences(acronymTestText);
		for (String string : aux) {
			System.out.println(string);
		}
		if(!aux[0].equals(acronymTestText)){
			fail("not equal");
		}
	}
	
	@Test	
	public void testSplitIntoSentencesAbbrev01() {
		System.out.println("Abbreviations test");		
		String aux[] = paragraph.splitIntoSentences(abbrevTestText);
		for (String string : aux) {
			System.out.println(string);
		}
		if(!aux[0].equals(abbrevTestText)){
			fail("not equal");
		}
	}
	
	@Test	
	public void testSplitIntoSentencesAbbrev02() {
		System.out.println("Abbreviations test 2");		
		String aux[] = paragraph.splitIntoSentences(fulltext);
		for(int i=0;i<aux.length;i++){
			if(!aux[i].equals(expectedSentences[i])){
				System.out.println("FAIL. Sentence "+i);
				System.out.println(aux[i]);
				fail("not equal. The sentence "+i+" is not the same.");
			}
		}
	}
	
	@Test	
	public void testSplitIntoSentencesAbbrev03() {
		System.out.println("Abbreviations test 3 - Semicolon split");		
		String aux[] = paragraph.splitIntoSentences(fulltextSemicolon);
		for(int i=0;i<aux.length;i++){
			if(!aux[i].equals(expectedSentences[i])){
				System.out.println("FAIL. Sentence "+i);
				System.out.println(aux[i]);
				fail("not equal. The sentence "+i+" is not the same.");
			}
		}
	}

}
