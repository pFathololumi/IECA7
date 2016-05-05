//package ir.ramtung.polling;
//
//import static org.junit.Assert.*;
//
//import ir.ramtung.polling.Poll;
//import ir.ramtung.polling.PollRepository;
//
//import java.util.Collection;
//import java.util.HashSet;
//
//import org.junit.Test;
//
//public class PollRepositoryTest {
//	@Test
//	public void storesPoll() {
//		PollRepository pr = new PollRepository();
//		Poll p = new Poll("Which color do you prefer?", "Red", "Green");
//		pr.store(p);
//
//		assertNotNull(p.getCode());
//		assertEquals(p, pr.findByCode(p.getCode()));
//	}
//
//	@Test
//	public void findsAllPolls() {
//		PollRepository pr = new PollRepository();
//		pr.store(new Poll("Which color do you prefer?", "Red", "Green"));
//		pr.store(new Poll("Are you Chinese?", "Yes", "No"));
//		pr.store(new Poll("How many children do you have?", "1", "More"));
//
//		Collection<Poll> result = pr.findAll();
//		System.err.println(result);
//		assertEquals(3, result.size());
//
//		// check the codes are unique
//		HashSet<String> codes = new HashSet<String>();
//		for (Poll p : result) {
//			assertFalse(codes.contains(p.getCode()));
//			codes.add(p.getCode());
//		}
//	}
//}
