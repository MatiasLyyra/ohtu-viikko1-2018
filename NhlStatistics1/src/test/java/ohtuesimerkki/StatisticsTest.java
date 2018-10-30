package ohtuesimerkki;

import org.junit.*;
import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.Assert.*;

public class StatisticsTest {
    Reader readerStub = new Reader() {
        @Override
        public List<Player> getPlayers() {
            ArrayList<Player> players = new ArrayList<Player>();

            players.add(new Player("Lemieux", "PIT", 1, 2));
            players.add(new Player("Yzerman", "DET", 3, 5));
            players.add(new Player("Gretzky", "EDM", 32, 89));
            players.add(new Player("Semenko", "EDM", 1, 1));
            players.add(new Player("Kurri",   "EDM", 2, 2));

            return players;
        }
    };

    Statistics stats;

    @Before
    public void setUp() {
        stats = new Statistics(readerStub);
    }

    @Test
    public void searchReturnsPlayer() {
        Player searchResult = stats.search("Kurri");
        assertEquals("Kurri", searchResult.getName());
    }

    @Test
    public void searchReturnsNullForNonExistentPlayer() {
        Player searchResult = stats.search("FooBar");
        assertNull(searchResult);
    }

    @Test
    public void gettingPlayersByTeamWorks() {
        ArrayList<String> expectedPlayerNames = new ArrayList();
        List<String> players = getPlayerNames(stats.team("EDM"));
        expectedPlayerNames.add("Gretzky");
        expectedPlayerNames.add("Kurri");
        expectedPlayerNames.add("Semenko");
        assertTrue(players.containsAll(expectedPlayerNames));
        assertEquals(3, players.size());
    }

    @Test
    public void topScorersReturnsCorrectPlayers() {
        ArrayList<String> expectedPlayerNames = new ArrayList();
        List<String> players = getPlayerNames(stats.topScorers(3));
        expectedPlayerNames.add("Gretzky");
        expectedPlayerNames.add("Yzerman");
        expectedPlayerNames.add("Kurri");
        assertEquals(expectedPlayerNames, players);
    }

    private List<String> getPlayerNames(List<Player> players) {
        return players.stream().map(player -> player.getName()).collect(Collectors.toList());
    }
}
