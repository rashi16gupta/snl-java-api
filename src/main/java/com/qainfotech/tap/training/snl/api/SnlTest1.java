package rashi.snl;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.UUID;

import org.json.JSONException;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class SnlTest1 {
	Board br;
	GameInProgressException gipe;
	InvalidTurnException itc;
	UUID uid1;
	UUID uid;
	MaxPlayersReachedException mpre;
	NoUserWithSuchUUIDException nuwsue;
	PlayerExistsException existplayer;
	JSONObject newPlayer;
	public SnlTest1()
	{
		
	}

	@BeforeClass
	public void oneTimeSetUp() throws FileNotFoundException, UnsupportedEncodingException, IOException {

		uid= UUID.randomUUID();
		br = new Board();
		gipe = new GameInProgressException();
		itc=  new InvalidTurnException(uid);
		mpre= new MaxPlayersReachedException(5);
		nuwsue = new NoUserWithSuchUUIDException("auid");
		existplayer= new PlayerExistsException("aname");
		newPlayer = new JSONObject();
	}

	@Test (expectedExceptions =  MaxPlayersReachedException.class) 
	public void registerPlayerTest() throws FileNotFoundException, UnsupportedEncodingException, PlayerExistsException, GameInProgressException,  MaxPlayersReachedException, IOException
	{
		for(int i =0; i<5 ;i++)
		br.registerPlayer("Rashi"+ i);
	
	}
	@Test (expectedExceptions = PlayerExistsException.class)
	public void registerPlayerExists() throws FileNotFoundException, UnsupportedEncodingException, PlayerExistsException, GameInProgressException, MaxPlayersReachedException, IOException
	{
		for(int i =0; i<2 ;i++)
		br.registerPlayer("Rashi");
	
	}
	@Test (expectedExceptions = NullPointerException.class)
	public void delete_user_without_register() throws FileNotFoundException, UnsupportedEncodingException, PlayerExistsException, GameInProgressException, MaxPlayersReachedException, IOException, NoUserWithSuchUUIDException
	{
		
		br.deletePlayer(null);
	
	}
	@Test (expectedExceptions = NullPointerException.class)
	public void roll_dice_without_register() throws FileNotFoundException, UnsupportedEncodingException, PlayerExistsException, GameInProgressException, MaxPlayersReachedException, IOException, NoUserWithSuchUUIDException, InvalidTurnException
	{
		
		br.rollDice(null);
	
	}

	//game started
	@Test (priority=3)
	public void Game_In_Progress_Exception_GAme_started() throws MaxPlayersReachedException, FileNotFoundException, UnsupportedEncodingException, PlayerExistsException, GameInProgressException, MaxPlayersReachedException, IOException, InvalidTurnException 
	{
		String name="Rashi";
		br.registerPlayer(name);
		try
		{
		uid1 = (UUID) br.getData().getJSONArray("players").getJSONObject(0).get("uuid");
		System.out.println("uid"+uid);
		br.rollDice(uid1);
		br.registerPlayer("abc");
		}
		catch (Exception e) {
            Assert.assertEquals(e.getClass(), new GameInProgressException().getClass());
        }

		
	
	}
	@Test 
	public void Game_In_Progress_Exception() 
	{
		
		Assert.assertEquals("New player cannot join since the game has started", gipe.getMessage());
	
	}
	@Test 
	public void Invalid_Turn_Exception() 
	{
		
		Assert.assertEquals("Player '"+uid+"' does not have the turn", itc.getMessage());
	
	}
	@Test 
	public void Max_Players_Reached_Exeption() 
	{
		
		Assert.assertEquals("The board already has maximum allowed Player: " + 5, mpre.getMessage());
	
	}
	@Test 
	public void Player_Exists_Exception() 
	{
		
		Assert.assertEquals("Player '" +"aname"+"' already exists on board", existplayer.getMessage());
	
	}
	@Test 
	public void NoUser_With_Such_UUIDException() 
	{
		
		Assert.assertEquals("No Player with uuid '"+"auid"+"' on board", nuwsue.getMessage());
	
	}
	
	
}


