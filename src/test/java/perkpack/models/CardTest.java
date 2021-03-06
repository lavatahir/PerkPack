package perkpack.models;

import org.junit.Before;
import org.junit.Test;

import java.util.Collection;
import java.util.HashSet;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

public class CardTest {

    private static String name = "Visa";
    private static String description = "Credit Card";
    private static Card card;
    private static Category food = new Category("Food");

    @Before
    public  void setup()
    {
        card = new Card(name, description);
    }

    @Test
    public void getNameTest() throws Exception {
        assertEquals(card.getName(), name);
    }

    @Test
    public void getDescriptionTest() throws Exception {
        assertEquals(card.getDescription(), description);
    }

    @Test
    public void getUsersTest() throws Exception {
        Collection<Account> emptyAccounts = new HashSet<>();
        assertEquals(card.getAccounts(), emptyAccounts);
    }

    @Test
    public void addUserTest() throws Exception {
        Account u = new Account("Lava", "Tahir", "lavatahir@gmail.com", "password");
        card.addUser(u);
        assertTrue(card.getAccounts().contains(u));
    }

    @Test
    public void removeUserTest() throws Exception {
        Account u = new Account("Lava", "Tahir", "lavatahir@gmail.com", "password");
        card.addUser(u);
        Account u2 = new Account("Lava", "Tahir", "lavatahir@gmail.com", "password");
        card.removeUser(u2);
        assertTrue(!card.getAccounts().contains(u));
    }

    @Test
    public void getPerksTest() throws Exception {
        Collection<Perk> emptyPerks = new HashSet<>();
        assertEquals(card.getPerks(), emptyPerks);
    }


    @Test
    public void addPerkTest() throws Exception {
        Perk p = new Perk();
        card.addPerk(p);
        assertTrue(card.getPerks().contains(p));
    }

    @Test
    public void removePerkTest() throws Exception {
        Perk p = new Perk("Lava", "Some perk", food);
        card.addPerk(p);

        Perk removePerk = new Perk("Lava", "Some perk", food);
        card.removePerk(removePerk);
        assertTrue(!card.getPerks().contains(p));
    }

    @Test
    public void dontRemovePerkTest() throws Exception {
        Perk p = new Perk("Lava", "Some perk", food);
        card.addPerk(p);

        Perk removePerk = new Perk("Lava2", "Some perk", food);
        card.removePerk(removePerk);
        assertTrue(card.getPerks().contains(p));
    }

    @Test
    public void checkEqualsTest() throws Exception {
        Card c = new Card(name, description);
        assertEquals(card, c);
    }

    @Test
    public void checkEqualsWithPerksAndUsersTest() throws Exception {
        Card c = new Card(name, description);

        Perk p1 = new Perk("Perk 1", "desc1", food);
        Perk p2 = new Perk("Perk 2", "desc2", food);
        Perk p3 = new Perk("Perk 3", "desc3", food);

        card.addPerk(p1);
        card.addPerk(p2);
        card.addPerk(p3);

        c.addPerk(p1);
        c.addPerk(p2);
        c.addPerk(p3);

        Account u1 = new Account("U1", "L1", "ul1@gmail.com", "password");
        Account u2 = new Account("U2", "L2", "ul2@gmail.com", "password");
        Account u3 = new Account("U3", "L3", "ul3@gmail.com", "password");

        card.addUser(u1);
        card.addUser(u2);
        card.addUser(u3);

        c.addUser(u1);
        c.addUser(u2);
        c.addUser(u3);

        assertEquals(card, c);
    }

    @Test
    public void checkNotEqualsTest() throws Exception {
        Card c = new Card("Weirdo", description);
        assertNotEquals(card, c);
    }

}
