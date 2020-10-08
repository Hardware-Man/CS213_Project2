import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CheckingTest {

    @Test
    void testToString() {
        Checking acc1 = new Checking(new Profile("Bob", "Bob"), 100, new Date(1, 1, 1), true);
        Checking acc2 = new Checking(new Profile("Joe", "Joe"), 100, new Date(1, 1, 1), false);
        assertEquals("*Checking*Bob Bob* $100.00*1/1/1*direct deposit account*", acc1.toString());
        assertEquals("*Checking*Joe Joe* $100.00*1/1/1", acc2.toString());
    }

    @Test
    void monthlyInterest() {
        Checking acc1 = new Checking(new Profile("Bob", "Bob"), 100, new Date(1, 1, 1), true);
        Checking acc2 = new Checking(new Profile("Joe", "Joe"), 100, new Date(1, 1, 1), false);
        assertEquals(100 * 0.0005 / 12, acc1.monthlyInterest());
        assertEquals(100 * 0.0005 / 12, acc2.monthlyInterest());
    }

    @Test
    void monthlyFee() {
        Checking acc1 = new Checking(new Profile("Bob", "Bob"), 100, new Date(1, 1, 1), true);
        Checking acc2 = new Checking(new Profile("Joe", "Joe"), 100, new Date(1, 1, 1), false);
        Checking acc3 = new Checking(new Profile("Larry", "Larry"), 1500, new Date(1, 1, 1), false);
        assertEquals(0, acc1.monthlyFee());
        assertEquals(25, acc2.monthlyFee());
        assertEquals(0, acc3.monthlyFee());
    }
}