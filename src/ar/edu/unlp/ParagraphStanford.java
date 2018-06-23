package ar.edu.unlp;

import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

import edu.stanford.nlp.ling.CoreLabel;
import edu.stanford.nlp.process.CoreLabelTokenFactory;
import edu.stanford.nlp.process.PTBTokenizer;
import edu.stanford.nlp.process.WordToSentenceProcessor;

public class ParagraphStanford {
	
	private AbbreviationDic ad = null;
	
	public ParagraphStanford() throws IOException{
		ad = new AbbreviationDic();
	}
	
	public String[] splitIntoSentences(String paragraph){
		String lparagraph = ad.replaceAbbrevDot(paragraph);
		lparagraph = lparagraph.replaceAll(";", ".");
		List<String> sentenceList;
		List<CoreLabel> tokens = new ArrayList<CoreLabel>();
		PTBTokenizer<CoreLabel> tokenizer = new PTBTokenizer<CoreLabel>(new StringReader(lparagraph), new CoreLabelTokenFactory(), "");
		while (tokenizer.hasNext()) {
		    tokens.add(tokenizer.next());
		}
		//// Split sentences from tokens
		List<List<CoreLabel>> sentences = new WordToSentenceProcessor<CoreLabel>().process(tokens);
		//// Join back together
		int end;
		int start = 0;
		sentenceList = new ArrayList<String>();
		for (List<CoreLabel> sentence: sentences) {
		    end = sentence.get(sentence.size()-1).endPosition();
		    sentenceList.add(ad.removeAbbrevSpecialChar(lparagraph.substring(start, end).trim()));
		    start = end;
		}
		return sentenceList.toArray(new String[sentenceList.size()]);
	}

}
