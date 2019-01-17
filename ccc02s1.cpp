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
int P, G, R, O, N;
int counts[4];
int c = 0;
int smallest = INFINITE;

void recurse(int curr, int n){
	counts[curr]++;
	if(n == 0){
		cout << "# of PINK is " << counts[0] << " # of GREEN is " << counts[1] << " # of RED is " << counts[2] << " # of ORANGE is " << counts[3] << "\n";
		c++;
		smallest = min(smallest, counts[0]+counts[1]+counts[2]+counts[3]);
		counts[curr]--;
		return;
	} else if(n < 0){
		counts[curr]--;
		return;
	}
	if(curr == 0){
		recurse(0, n-P);
		recurse(1, n-G);
		recurse(2, n-R);
		recurse(3, n-O);
	} else if(curr == 1){
		recurse(1, n-G);
		recurse(2, n-R);
		recurse(3, n-O);
	} else if(curr == 2){
		recurse(2, n-R);
		recurse(3, n-O);
	} else if(curr == 3){
		recurse(3, n-O);
	}
	counts[curr]--;
}

int main(){
	cin.sync_with_stdio(0);
    cin.tie(0);
	freopen("input.txt", "r", stdin);
	cin >> P >> G >> R >> O >> N;
	recurse(0, N-P);
	recurse(1, N-G);
	recurse(2, N-R);
	recurse(3, N-O);
	cout << "Total combinations is " << c << ".\n";
	cout << "Minimum number of tickets to print is " << smallest << ".";
	return 0;
}