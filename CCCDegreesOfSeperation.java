import java.util.*;
import java.math.*;
import java.io.*;
public class CCCDegreesOfSeperation {
	public static class Friend{
		int id;
		ArrayList<Friend> adj = new ArrayList<>();
		int dist = Integer.MAX_VALUE;
		public Friend(int i){
			id=i;
		}
	}
	public static void main(String args[]) throws Exception {
		BufferedReader s = new BufferedReader(new InputStreamReader(System.in));
		String z = s.readLine();
		Friend[] friends = new Friend[51];
		for(int i = 0; i < 51; i++)friends[i] = new Friend(i);
		friends[1].adj.add(friends[6]);
		friends[6].adj.add(friends[1]);
		friends[2].adj.add(friends[6]);
		friends[6].adj.add(friends[2]);
		friends[6].adj.add(friends[7]);
		friends[7].adj.add(friends[6]);
		friends[6].adj.add(friends[5]);
		friends[5].adj.add(friends[6]);
		friends[6].adj.add(friends[4]);
		friends[4].adj.add(friends[6]);
		friends[6].adj.add(friends[3]);
		friends[3].adj.add(friends[6]);
		friends[5].adj.add(friends[4]);
		friends[4].adj.add(friends[5]);
		friends[3].adj.add(friends[4]);
		friends[4].adj.add(friends[3]);
		friends[5].adj.add(friends[3]);
		friends[3].adj.add(friends[5]);
		friends[3].adj.add(friends[15]);
		friends[15].adj.add(friends[3]);
		friends[8].adj.add(friends[7]);
		friends[7].adj.add(friends[8]);
		friends[8].adj.add(friends[9]);
		friends[9].adj.add(friends[8]);
		friends[9].adj.add(friends[10]);
		friends[10].adj.add(friends[9]);
		friends[11].adj.add(friends[10]);
		friends[10].adj.add(friends[11]);
		friends[9].adj.add(friends[12]);
		friends[12].adj.add(friends[9]);
		friends[11].adj.add(friends[12]);
		friends[12].adj.add(friends[11]);
		friends[13].adj.add(friends[12]);
		friends[12].adj.add(friends[13]);
		friends[13].adj.add(friends[15]);
		friends[15].adj.add(friends[13]);
		friends[13].adj.add(friends[14]);
		friends[14].adj.add(friends[13]);
		friends[16].adj.add(friends[17]);
		friends[17].adj.add(friends[16]);
		friends[16].adj.add(friends[18]);
		friends[18].adj.add(friends[16]);
		friends[18].adj.add(friends[17]);
		friends[17].adj.add(friends[18]);
		while(!z.equals("q")){
			if(z.equals("i")){
				int x = Integer.parseInt(s.readLine());
				int y = Integer.parseInt(s.readLine());
				friends[x].adj.add(friends[y]);
				friends[y].adj.add(friends[x]);
			} else if(z.equals("d")){
				int x = Integer.parseInt(s.readLine());
				int y = Integer.parseInt(s.readLine());
				friends[x].adj.remove(friends[y]);
				friends[y].adj.remove(friends[x]);
			} else if(z.equals("n")){
				int x = Integer.parseInt(s.readLine());
				System.out.println(friends[x].adj.size());
			} else if(z.equals("f")){
				int x = Integer.parseInt(s.readLine());
				System.out.println(count(friends[x]));
			} else if(z.equals("s")){
				int x = Integer.parseInt(s.readLine());
				int y = Integer.parseInt(s.readLine());
				int sep = seperation(friends, friends[x], friends[y]);
				if(sep == -1)System.out.println("Not connected");
				else System.out.println(sep);
			}
			z = s.readLine();
		}
	}
	public static int seperation(Friend[] friends, Friend start, Friend end){
		ArrayDeque<Friend> q = new ArrayDeque<>();
		ArrayDeque<Integer> d = new ArrayDeque<>();
		boolean[] visited = new boolean[friends.length];
		visited[start.id] = true;
		int[] dists = new int[friends.length];
		dists[start.id] = 0;
		q.add(start);
		d.add(0);
		while(!q.isEmpty()){
			Friend x = q.poll();
			int z = d.poll();
			if(x.id == end.id)return z;
			for(Friend f : x.adj){
				if(!visited[f.id]){
					visited[f.id] = true;
					dists[f.id] = z+1;
					q.add(f);
					d.add(z+1);
				}
			}
		}
		return -1;
	}
	public static int count(Friend f){
		HashSet<Friend> set = new HashSet<>();
		HashSet<Friend> dinclude = new HashSet<>();
		dinclude.addAll(f.adj);
		dinclude.add(f);
		for(Friend z : f.adj){
			for(Friend x : z.adj){
				if(!dinclude.contains(x))set.add(x);
			}
		}
		return set.size();
	}
}
