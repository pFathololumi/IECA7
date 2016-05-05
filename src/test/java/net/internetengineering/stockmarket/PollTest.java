//package ir.ramtung.polling;
//
//import static org.junit.Assert.*;
//import ir.ramtung.polling.Poll;
//
//import org.junit.Test;
//
//
//public class PollTest {
//
//	@Test(expected=java.lang.IllegalArgumentException.class)
//	public void cantHaveNullSubject() {
//		new Poll(null, "Choice 1");
//	}
//
//	@Test(expected=java.lang.IllegalArgumentException.class)
//	public void cantHaveZeroChoices() {
//		new Poll("Subject");
//	}
//
//	@Test(expected=java.lang.IllegalArgumentException.class)
//	public void cantHaveNullChoices() {
//		new Poll("Subject", null);
//	}
//
//	@Test
//	public void convertsToString() {
//		Poll p = new Poll("Which color do you like?", "Blue", "Violet", "Green");
//		String expectedString = "Which color do you like?\n\t[] Blue\n\t[] Violet\n\t[] Green\n";
//		assertEquals(expectedString, p.toString());
//	}
//}
