#include <bits/stdc++.h>
using namespace std;
#define pii pair<int,int>
#define mp make_pair
#define pb push_back

int INFINITE = 0x3f3f3f3f;
int M, A, B;
bool visited[1005][1005];

struct State {
	int moves;
	vector<string> operations;
	int VA;
	int VB;
} result;

void bfs(){
	queue<State> q;
	struct State start;
	start.moves = 0;
	start.VA = 0;
	start.VB = 0;
	visited[0][0] = true;
	q.push(start);
	while(!q.empty()){
		struct State x = q.front();
		q.pop();
		if(x.VA == M || x.VB == M){
			if(x.moves < result.moves){
				result = x;
				break;
			}
		}
		//fill
		if(!visited[A][x.VB]){
			struct State next;
			next.operations = x.operations; next.moves = x.moves;
			next.VA = A; next.VB = x.VB; next.moves++; next.operations.pb("fill A");
			visited[A][x.VB] = true;
			q.push(next);
		}
		if(!visited[x.VA][B]){
			struct State next;
			next.operations = x.operations; next.moves = x.moves;
			next.VA = x.VA; next.VB = B; next.moves++; next.operations.pb("fill B");
			visited[x.VA][B] = true;
			q.push(next);
		}
		//pour
		//a to b
		int diffA;
		int diffB;
		if(x.VA <= (B-x.VB)){
			diffA = 0;
			diffB = x.VB + x.VA;
		} else {
			diffA = x.VA - (B-x.VB);
			diffB = B;
		}
		if(!visited[diffA][diffB]){
			struct State next;
			next.operations = x.operations; next.moves = x.moves;
			next.VA = diffA; next.VB = diffB; next.moves++; next.operations.pb("pour A B");
			visited[diffA][diffB] = true;
			q.push(next);
		}
		//b to a
		if(x.VB <= (A-x.VA)){
			diffB = 0;
			diffA = x.VA + x.VB;
		} else {
			diffB = x.VB - (A-x.VA);
			diffA = A;
		}
		if(!visited[diffA][diffB]){
			struct State next;
			next.operations = x.operations; next.moves = x.moves;
			next.VA = diffA; next.VB = diffB; next.moves++; next.operations.pb("pour B A");
			visited[diffA][diffB] = true;
			q.push(next);
		}
		//chug
		if(!visited[0][x.VB]){
			struct State next;
			next.operations = x.operations; next.moves = x.moves;
			next.VA = 0; next.VB = x.VB; next.moves++; next.operations.pb("chug A");
			visited[0][x.VB] = true;
			q.push(next);
		}
		if(!visited[x.VA][0]){
			struct State next;
			next.operations = x.operations; next.moves = x.moves;
			next.VA = x.VA; next.VB = 0; next.moves++; next.operations.pb("chug B");
			visited[x.VA][0] = true;
			q.push(next);
		}
	}
}

int main(){
	//freopen("input.txt", "r", stdin);
	scanf("%i%i%i", &A, &B, &M);
	result.moves = INFINITE;
	bfs();
	if(result.moves == INFINITE)cout << "Not possible";
	else {
		for(string s : result.operations){
			cout << s << "\n";
		}
	}
	return 0;
}