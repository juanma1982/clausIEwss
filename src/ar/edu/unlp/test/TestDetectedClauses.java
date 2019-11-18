package ar.edu.unlp.test;

import java.io.ByteArrayOutputStream;
import java.io.OutputStream;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import de.mpii.clausie.ClausIE;
import joptsimple.OptionParser;
import joptsimple.OptionSet;

public class TestDetectedClauses {

	public static final String SENTENCE = "Bell, a telecommunication company, which is based in Los Angeles, makes and distributes electronic, computer and building products.";
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void test() {
		ClausIE clausIE = new ClausIE();
		OutputStream out = System.out;
		clausIE.initParser();
		OptionParser optionParser = new OptionParser();
		String[] args = new String[1];
		args[0] = "";
		OptionSet oset = optionParser.parse(args);
		
		ByteArrayOutputStream streamout = new ByteArrayOutputStream();
		
		//ClausIE.processSentence(SENTENCE,oset,clausIE,1,out);		
		ClausIE.processSentence(SENTENCE,oset,clausIE,1,streamout);
		String finalString = new String(streamout.toByteArray());
        System.out.println(finalString);
	}

}
