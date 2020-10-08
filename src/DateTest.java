import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DateTest {

    @Test
    void isValid() {
        Date testDate1 = new Date(1, 1, 1);
        Date testDate2 = new Date(1, 1, 32);
        Date testDate3 = new Date(1, 2, 29);
        Date testDate4 = new Date(4, 2, 29);
        Date testDate5 = new Date(100, 2, 29);
        Date testDate6 = new Date(400, 2, 29);
        Date testDate7 = new Date(1, 1, 31);
        Date testDate8 = new Date(1, 4, 31);
        Date testDate9 = new Date(1, 4, 30);
        Date testDate10 = new Date(1, 2, 28);
        Date otherDate = new Date(1, 1, 0);
        Date anotherDate = new Date(1, 0, 1);
        Date additionalDate = new Date(0, 1, 1);
        assertTrue(testDate1.isValid());
        assertFalse(testDate2.isValid());
        assertFalse(testDate3.isValid());
        assertTrue(testDate4.isValid());
        assertFalse(testDate5.isValid());
        assertTrue(testDate6.isValid());
        assertTrue(testDate7.isValid());
        assertFalse(testDate8.isValid());
        assertTrue(testDate9.isValid());
        assertTrue(testDate10.isValid());
        assertFalse(otherDate.isValid());
        assertFalse(anotherDate.isValid());
        assertFalse(additionalDate.isValid());
    }

    @Test
    void compareTo() {
        Date testDate = new Date(1, 1, 1);
        Date otherDate = new Date(1, 1, 2);
        Date anotherDate = new Date(1, 2, 1);
        Date additionalDate = new Date(2, 1, 1);
        assertEquals(0, testDate.compareTo(new Date(1, 1, 1)));
        assertEquals(-1, testDate.compareTo(otherDate));
        assertEquals(1, otherDate.compareTo(testDate));
        assertEquals(-1, otherDate.compareTo(anotherDate));
        assertEquals(1, anotherDate.compareTo(otherDate));
        assertEquals(-1, anotherDate.compareTo(additionalDate));
        assertEquals(1, additionalDate.compareTo(anotherDate));
    }

    @Test
    void testToString() {
        Date testDate = new Date(1, 1, 1);
        assertEquals("1/1/1", testDate.toString());
    }

}