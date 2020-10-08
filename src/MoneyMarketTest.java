import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MoneyMarketTest {

    @Test
    void debit() {
        MoneyMarket acc1 = new MoneyMarket(new Profile("Bob","Bob"),100,new Date(1,1,1),0);
        assertEquals("*Money Market*Bob Bob* $100.00*1/1/1*0 withdrawals*",acc1.toString());
        acc1.debit(100);
        assertEquals("*Money Market*Bob Bob* $0.00*1/1/1*1 withdrawals*",acc1.toString());
    }

    @Test
    void monthlyInterest() {
        MoneyMarket acc1 = new MoneyMarket(new Profile("Bob","Bob"),100,new Date(1,1,1),0);
        assertEquals(100 * 0.0065 / 12,acc1.monthlyInterest());
    }

    @Test
    void monthlyFee() {
        MoneyMarket acc1 = new MoneyMarket(new Profile("Bob","Bob"),100,new Date(1,1,1),0);
        MoneyMarket acc2 = new MoneyMarket(new Profile("Joe","Joe"),2500,new Date(1,1,1),0);
        MoneyMarket acc3 = new MoneyMarket(new Profile("Larry","Larry"),2500,new Date(1,1,1),7);
        assertEquals(12,acc1.monthlyFee());
        assertEquals(0,acc2.monthlyFee());
        assertEquals(12,acc3.monthlyFee());
    }
}