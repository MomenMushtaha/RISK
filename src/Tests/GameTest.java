package Tests;

import Objects.*;
import Logic.Gameplay;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.*;
import static org.junit.Assert.*;


public class GameTest {
    public static final String HAMILTON = "Hamilton";
    public static final String WINDSOR = "Windsor";
    public static final String TORONTO = "Toronto";
    public static final String WATERLOO = "Waterloo";
    public static final String OSHAWA = "Oshawa";
    Gameplay gameTest;

     @Before
    public void setUp() {
        gameTest = new Gameplay();
    }

    @After
    public void tearDown() {
        gameTest = null;
    }

    @Test
    public void testGetCurrentPlayer(){
         gameTest.startGame(2);
        assertEquals(gameTest.getPlayers(0),gameTest.getCurrentPlayer());
    }

    @Test
    public void testAttackerWin(){
        Territory Attacking = new Territory("Attacker");
        Territory Defending = new Territory("Defender");
        Attacking.addTroops(5);
        Defending.addTroops(2);
        gameTest.attack(Defending,Attacking,Attacking.getTroops());
        assertEquals(5, Attacking.getTroops());
        assertEquals(2, Defending.getTroops());
    }

    @Test
    public void testAttackerLose(){
        Territory Attacking = new Territory("Attacker");
        Territory Defending = new Territory("Defender");
        Attacking.addTroops(2);
        Defending.addTroops(5);
        gameTest.attack(Defending,Attacking,Attacking.getTroops());
        assertEquals(2, Attacking.getTroops());
        assertEquals(5, Defending.getTroops());
    }



    @Test
    public void testMoveAfterAttackMoreThan3(){
        Territory Attacker = new Territory("Attacker");
        Territory target = new Territory("target");
        Attacker.addTroops(10);
        gameTest.MoveAfterAttack(target,Attacker,6);
        assertEquals(4, Attacker.getTroops());
        assertEquals(6, target.getTroops());
    }

    @Test
    public void testAttackResultsPlayerLostTerritory(){
        Territory Attacking = new Territory("Attacker");
        Territory Defending = new Territory("Defender");
        Territory extra = new Territory("extra Territory");
        Attacking.addTroops(3);
        Defending.addTroops(0);
        Player pl1 = new Player( "Player 1");
        Player pl2 = new Player("Player 2");
        Attacking.changeOwner(pl1);
        Defending.changeOwner(pl2);
        extra.changeOwner(pl2);
        gameTest.attack(Defending,Attacking,Attacking.getTroops());

        assertEquals(1, Attacking.getTroops());
        assertEquals(2, Defending.getTroops());
        assertEquals(pl1, Attacking.getPlayer());
        assertEquals(pl1, Defending.getPlayer());
    }
    //end of unit style tests and more Functional style testing is below


    @Test
    public void testBordering(){
        Territory Alaska = new Territory("Alaska");
        Territory Alberta = new Territory("Alberta");
        Alaska.addBorderTerritories(Alberta);
        Alberta.addBorderTerritories(Alaska);
        assertEquals("Alaska",Alberta.getBorderTerritories().get(0).getName());
        assertEquals("Alberta",Alaska.getBorderTerritories().get(0).getName());
    }



    @Test
    public void testPlayerTerritories(){
        Territory Toronto = new Territory(TORONTO);
        Territory Waterloo = new Territory(WATERLOO);
        Territory Oshawa = new Territory(OSHAWA);
        Toronto.addBorderTerritories(Oshawa);
        Toronto.addBorderTerritories(Waterloo);
        Oshawa.addBorderTerritories(Toronto);
        Oshawa.addBorderTerritories(Waterloo);
        Waterloo.addBorderTerritories(Toronto);
        Waterloo.addBorderTerritories(Oshawa);
        Toronto.addTroops(33);
        Waterloo.addTroops(21);
        Oshawa.addTroops(11);
        Player pl1 = new Player("pl1");
        Player pl2 = new Player("pl2");
        Toronto.changeOwner(pl1);
        Waterloo.changeOwner(pl2);
        Oshawa.changeOwner(pl2);

        assertEquals(pl1, Toronto.getPlayer());
        assertEquals(pl2, Waterloo.getPlayer());
        assertEquals(pl2, Oshawa.getPlayer());
    }




    @Test
    public void testBoardTerritoriesSize()
    {
        gameTest.startGame(2);
        assertEquals(42,gameTest.getBoard().territoriesList.length);
    }

    @Test
    public void testBonusTroopNoTerritories() {
        Player pl = new Player("pl");
        assertEquals(3,gameTest.get_bonus(pl));
    }

    @Test
    public void testNewTroopersForThreeTradesMade() {
         gameTest.startGame(2);
         gameTest.trade();
         gameTest.trade();
         gameTest.trade();
        assertEquals(8,gameTest.getCurrentPlayer().getNewTroopers());
    }

    @Test
    public void testBonusTroopCalculationOneContinent() {
         gameTest.startGame(2);
        Continent NorthAmerica = new Continent("North", 5,new ArrayList<>());
        gameTest.getCurrentPlayer().addContinents(NorthAmerica);
        assertEquals(12,gameTest.get_bonus(gameTest.getCurrentPlayer()));
    }



}