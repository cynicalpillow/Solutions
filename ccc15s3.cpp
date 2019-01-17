#include <bits/stdc++.h>
using namespace std;
#define fori(i, st, en) for(int i = st; i < (int) en; i++)
#define rfori(i, st, en) for(int i = st; i >= (int) en; i--)
#define f first
#define s second
#define pii pair<int,int>
#define mp make_pair
#define pb push_back

int INFINITE = 0x3f3f3f3f;
int G, P, gi;
bool visited[100005];
set<int, greater<int>> s;

int main(){
	freopen("input.txt", "r", stdin);
	scanf("%i%i", &G, &P);
	for(int i = 0; i < G; i++)s.insert(i);
	int c = 0;
	for(int i = 0; i < P; i++){
		scanf("%i", &gi);
		if(s.upper_bound(gi) == s.end())break;
		s.erase(s.upper_bound(gi));
		c++;
	}
	cout << c;
	return 0;
}