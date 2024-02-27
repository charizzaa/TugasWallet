import org.example.Wallet;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class WalletTest {
    @Test
    public void testAddMoney() {
        Wallet wallet = new Wallet("Test Owner");
        wallet.addMoney(10000);
        assertEquals(1, wallet.getMoneys().get(10000).intValue());
        wallet.addMoney(5000);
        assertEquals(1, wallet.getMoneys().get(5000).intValue());
    }

    @Test
    public void testAddInvalidMoney() {
        Wallet wallet = new Wallet("Test Owner");
        wallet.addMoney(200); // Adding invalid money
        assertNull(wallet.getMoneys().get(200));
    }

    @Test
    public void testTakeMoney() {
        Wallet wallet = new Wallet("Test Owner");
        wallet.addMoney(10000);
        wallet.addMoney(5000);
        wallet.takeMoney(10000);
        assertEquals(0, wallet.getMoneys().get(10000).intValue());
    }

    @Test
    public void testAddCoin() {
        Wallet wallet = new Wallet("Test Owner");
        wallet.addCoin(100);
        assertEquals(1, wallet.getCoins().get(100).intValue());
    }

    @Test
    public void testTakeCoin() {
        Wallet wallet = new Wallet("Test Owner");
        wallet.addCoin(500);
        wallet.addCoin(500);
        wallet.takeCoin(500);
        assertEquals(1, wallet.getCoins().get(500).intValue());
    }

    @Test
    public void testCardOperations() {
        Wallet wallet = new Wallet("Test Owner");
        wallet.addCard("Card 1");
        wallet.addCard("Card 2");
        assertTrue(wallet.getCards().contains("Card 1"));
        assertTrue(wallet.getCards().contains("Card 2"));
        wallet.removeCard("Card 1");
        assertFalse(wallet.getCards().contains("Card 1"));
    }

    @Test
    public void testGetMoneyAvailable() {
        Wallet wallet = new Wallet("Test Owner");
        wallet.addMoney(10000);
        wallet.addMoney(5000);
        wallet.addCoin(100);
        assertEquals(15000, wallet.getMoneyAvailable());
    }

    @Test
    public void testAddAndTakeCoins() {
        Wallet wallet = new Wallet("Test Owner");
        wallet.addCoin(100);
        wallet.addCoin(500);
        wallet.addCoin(1000);
        assertEquals(1600, wallet.getMoneyAvailable()); // Total should be 1600 (coin values: 100 + 500 + 1000)
        wallet.takeCoin(500);
        assertEquals(1100, wallet.getMoneyAvailable()); // Total should be 1100 (coin values: 100 + 1000)
    }

    @Test
    public void testCalculateCoinsAndMoneys() {
        Wallet wallet = new Wallet("Test Owner");
        wallet.addMoney(20000);
        wallet.addCoin(500);
        wallet.addCoin(100);
        assertEquals(20500, wallet.getMoneyAvailable()); // Total should be 20500 (20000 + 500 + 100)
        assertEquals(1500, wallet.calculateCoins());
        assertEquals(20000, wallet.calculateMoneys());
    }
}
