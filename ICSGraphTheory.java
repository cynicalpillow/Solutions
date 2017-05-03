import java.util.*;
public class ICSGraphTheory {
	//City implementation with adjancency list for fast dijkstra with priorityqueue
	private static class City implements Comparable<City>{
		City previous;
		String city;
		int id;
		int cost = Integer.MAX_VALUE; //In dollars
		ArrayList<Edge> adj = new ArrayList<>();
		public City(String y, int i){
			city = y;
			id = i;
		}
		@Override
		public int compareTo(City arg0) {
			if(arg0.cost > this.cost)return -1;
			else if (arg0.cost < this.cost) return 1;
			return 0;
		}
	}
	//Edge class with v as the destination and cost is the cost of the plane ticket
	private static class Edge{
		int cost;
		City v;
		public Edge(City y, int c){
			v = y;
			cost = c;
		}
	}
	public static void main(String args[]) throws Exception {
		//Initialization. This is hardcoded, but can be easily modified to allow user input
		City[] cities = new City[5];
		for(int i = 0; i < 5; i++){
			if(i == 0)cities[i] = new City("Boston", i);
			else if (i == 1)cities[i] = new City("Paris", i);
			else if (i == 2)cities[i] = new City("Tokyo", i);
			else if (i == 3)cities[i] = new City("London", i);
			else cities[i] = new City("New York", i);
		}
		//Generate for Boston
	    
	    //Boston - Tokyo
	    cities[0].adj.add(new Edge(cities[2], 575));
	    cities[2].adj.add(new Edge(cities[0], 575));
	    
	    //Boston - London
	    cities[0].adj.add(new Edge(cities[3], 200));
	    cities[3].adj.add(new Edge(cities[0], 200));
	    
	    //Boston - New York
	    cities[0].adj.add(new Edge(cities[4], 70));
	    cities[4].adj.add(new Edge(cities[0], 70));
	    
	    //Generate for Paris
	    //Paris - Tokyo
	    cities[1].adj.add(new Edge(cities[2], 225));
	    cities[2].adj.add(new Edge(cities[1], 225));
	    
	    //Paris - London
	    cities[1].adj.add(new Edge(cities[3], 50));
	    cities[3].adj.add(new Edge(cities[1], 50));
	    
	    //Paris - New York
	    cities[1].adj.add(new Edge(cities[4], 125));
	    cities[4].adj.add(new Edge(cities[1], 125));
	    
	    //Get shortest path and print into standard output
	    int cost = dijkstra(cities, 4, 2);
	    printRoute(cities, 4, 2);
	    System.out.println(cost == Integer.MAX_VALUE ? -1 : "Cost: $" + cost);
	}
	//Dijkstra's algorithm for shortest path
	public static int dijkstra(City[] cities, int u, int v){
		boolean[] visited = new boolean[cities.length]; //Array for checking if item is in queue
		PriorityQueue<City> q = new PriorityQueue<>();
		cities[u].cost = 0;
		q.add(cities[u]);
		visited[cities[u].id] = true;
		while(!q.isEmpty()){
			City c = q.poll();
			visited[c.id] = false;
			for(Edge i : c.adj){
				if(i.v.cost > c.cost + i.cost){
					i.v.cost = c.cost + i.cost;
					i.v.previous = c;
					if(!visited[i.v.id])q.add(i.v); //If not already in queue, add it
				}
			}
		}
		return cities[v].cost;
	}
	public static void printRoute(City[] cities, int u, int v){
		//Work backwards and get the path
		if(cities[v].cost != Integer.MAX_VALUE){
			StringBuilder x = new StringBuilder();
			City print = cities[v];
			while(print != null){
				x.insert(0, print.city);
				if(print.id != u)x.insert(0, " -> ");
				print = print.previous;
			}
			System.out.println("Route: " + x.toString());
		}
	}
}
