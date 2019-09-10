import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

public class EncryptTest {


		private Encrypt sha256;
		
		@Before
		public void setUp() {
			sha256 = new Encrypt();
		}

		@Test
		public void shouldEncryptValidText() {
			String encryptedText = sha256.Encrypt("ÕÙ“µfdfdddfdfdf≈‡","");
			assertEquals("031b2bd66fb1c8798de1fe05cfbf17c6bf8af7080dac2a54bb5fb917bbcd5b45", encryptedText);
		}
	}

