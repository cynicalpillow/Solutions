#include <bits/stdc++.h>
using namespace std;
#define pii pair<int,int>
#define mp make_pair
#define pb push_back

int INFINITE = 0x3f3f3f3f;
int N;
string s;
int vals[6];

int dfs(int curr, int amount[], int l){
	int m = l;
	for(int i = 0; i < 6; i++){
		if(curr == i)continue;
		if(amount[i] > 0){
			amount[i]--;
			m = max(m, dfs(i, amount, l+1));
			amount[i]++;
		}
	}
	return m;
}

int main(){
	//freopen("input.txt", "r", stdin);
	memset(vals, 0, sizeof(vals));
	scanf("%i", &N);
	for(int i = 0; i < N; i++){
		cin >> s;
		if(s == "red")vals[0]++;
		else if(s == "orange")vals[1]++;
		else if(s == "yellow")vals[2]++;
		else if(s == "green")vals[3]++;
		else if(s == "blue")vals[4]++;
		else vals[5]++;
	}
	int m = 0;
	for(int i = 0; i < 6; i++){
		m = max(m, dfs(i, vals, 0));
	}
	cout << m;
	return 0;
}