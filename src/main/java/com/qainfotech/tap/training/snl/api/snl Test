package rashi.snl;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.UUID;
import org.json.JSONObject;
import org.json.JSONArray;
import org.json.JSONException;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class SnlTest {
	Board br;
	 JSONObject data;
	 UUID uid;
	public SnlTest() throws IOException
	{
		// 
	}

	@BeforeClass 
	public void oneTimeSetUp() throws FileNotFoundException, UnsupportedEncodingException, IOException {
		
		br = new Board();
		 uid=UUID.randomUUID();
		  BoardModel.init(uid);
	        data = BoardModel.data(uid);
		
	}
//game started
	@Test (priority=1)
	public void Game_In_Progress_Exception_GAme_started()  {
		System.out.println();
		System.out.println("Game_In_Progress_Exception_GAme_started");
		UUID uid1;
		String name="Rashi";
		
		try
		{
			br.registerPlayer(name);
		uid1 = (UUID) br.getData().getJSONArray("players").getJSONObject(0).get("uuid");
		System.out.println("uid register progress"+uid1);
		br.rollDice(uid1);
		br.registerPlayer(name+"x");
		}
		catch (Exception e) {
		//e.printStackTrace();
		System.out.println("game in progress 1 ="+e.getMessage());	
            Assert.assertEquals(e.getClass(), new GameInProgressException().getClass());
        }
	}
	
		
		@Test (priority = 2)
		public void delete_player_with_uuid() throws FileNotFoundException, UnsupportedEncodingException, NoUserWithSuchUUIDException, PlayerExistsException, GameInProgressException,  MaxPlayersReachedException, IOException {
			System.out.println();
			System.out.println("delete_player_with_uuid");
			String name="Rashi";
			
			try
			{//JSONArray registered =br.registerPlayer(name);
				
				
				/*JSONArray registered =br.registerPlayer(name);
				
				JSONObject obj= registered.getJSONObject(0);
				System.out.println("jsonObj Values" +registered);
				*/
				uid = (UUID) br.getData().getJSONArray("players").getJSONObject(0).get("uuid");
				
				System.out.println("uid"+uid);
				
				br.deletePlayer(uid);
				System.out.println(uid+ "deleted");
				br.deletePlayer(uid);
				
			}
			catch (Exception e) {
			
			System.out.println("message delete  ="+e.getMessage());	
	            Assert.assertEquals(e.getClass(), new NoUserWithSuchUUIDException(uid.toString()).getClass());
	       
			}
			

		
	
	}
		
		@Test (expectedExceptions  = NoUserWithSuchUUIDException.class)
		public void delete_randomplayer_without_register() throws NoUserWithSuchUUIDException, PlayerExistsException, GameInProgressException,  MaxPlayersReachedException, IOException {
		
			
			br.deletePlayer(uid);
	}
		/* deletewithouregister 
		 * 
		 * UUID uid=null;
			uid = UUID.randomUUID();
			 br.deletePlayer(uid);
			 
			 
			 try
		{
		
			
		
		    br.deletePlayer(uid);
		}
		catch (Exception e) {
            System.out.println(e.getMessage());
            Assert.assertEquals(e.getClass(), new NoUserWithSuchUUIDException(uid.toString()).getClass());
        }
	*/

		
		@Test (priority = 0)
		public void invalid_turn() throws NoUserWithSuchUUIDException, NullPointerException, PlayerExistsException, GameInProgressException, MaxPlayersReachedException, IOException, InvalidTurnException {
			System.out.println();
			System.out.println("invalid_turn");
			
			String name="RashiGupta";
           UUID uid1=null;;
           UUID uid2=null;
			int i=1;
			try{
				JSONArray registered =br.registerPlayer(name);
				registered=br.registerPlayer(name +i++);
			uid1 = (UUID) br.getData().getJSONArray("players").getJSONObject(0).get("uuid");
			uid2 = (UUID) br.getData().getJSONArray("players").getJSONObject(1).get("uuid");
			System.out.println("registered " +registered);
			System.out.println(uid1);
			System.out.println(uid2);
			br.rollDice(uid1);
			br.rollDice(uid2);
			br.rollDice(uid2);
			
		}
		catch (Exception e) {
            System.out.println("error in turn" +e.getMessage());
            Assert.assertEquals(e.getClass(), new InvalidTurnException(uid2).getClass());
        }

	}

}
