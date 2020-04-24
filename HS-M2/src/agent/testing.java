package agent;

import java.awt.List;
import java.io.IOException;
import java.util.ArrayList;

import controller.Controller;
import engine.Game;
import exceptions.FullHandException;
import model.cards.minions.Minion;
import model.heroes.Hero;
import model.heroes.Paladin;

public class testing
{

	public static void test(Agent agent, Controller controller) throws FullHandException, CloneNotSupportedException, IOException
	{
		while(controller.getModel()==null)
		{
			;
		}
		agent.generateAllPossibleMinionAttacks();
//		System.out.println(agent.allPossibleMinonAttacks);
//		ArrayList<MinionMove> list = new ArrayList<MinionMove>();
//		list.add(new MinionMove(new Minion("7amasa", 10, null, 10, 10, false, false, false),new Minion("7amasa2", 10, null, 10, 10, false, false, false)));
//		list.add(new MinionMove(new Minion("7amasa3", 10, null, 10, 10, false, false, false),new Minion("7amasa4", 10, null, 10, 10, false, false, false)));
//		list.add(new MinionMove(new Minion("7amasa5", 10, null, 10, 10, false, false, false),new Minion("7amasa6", 10, null, 10, 10, false, false, false)));
//		list.add(new MinionMove(new Minion("7amasa7", 10, null, 10, 10, false, false, false),new Minion("7amasa8", 10, null, 10, 10, false, false, false)));
//		list.add(new MinionMove(new Minion("7amasa9", 10, null, 10, 10, false, false, false),new Minion("7amasa10", 10, null, 10, 10, false, false, false)));
//		agent.allPossibleMinonAttacks = list;
		ArrayList<ArrayList<MinionMove>> eachMinionArrayList = agent.generateEachPossibleMinionAttacks();
		ArrayList<MinionMove> resultArrayList = new ArrayList<MinionMove>();
		generate(eachMinionArrayList,resultArrayList,0);
		for (MinionMove minionMove : resultArrayList)
		{
			printMinionMove(minionMove);
		}
		
		
	}
		static void generate(ArrayList<ArrayList<MinionMove>> lists,ArrayList<MinionMove> result, int index)
		{
			if (index >= lists.size()) {
				System.out.println(String.valueOf(result));
			return;
		}
			ArrayList<MinionMove> list = lists.get(index);
			for (int i = 0; i < list.size(); i++) {
				result.add(list.get(i));
				generate(lists, result, index + 1);
				result.remove(result.size() - 1);
			}
		}
		
		
		static void printMinionMove(MinionMove move)
		{
			String string = "" + move.getAttackedMinion().getName() + "is attacking ";
			if(move.getAttackedMinion()!=null)
			{
				string=string+move.getAttackedMinion().getName();
			}
			else {
				string=string+move.getAttackedHero().getName();
			}
			
			System.out.println(string);
		}
}


