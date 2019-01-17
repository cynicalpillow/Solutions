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
int N;
string Ai;
string st = "";

void setP(string &s, int pos){
	if(s[pos] == '1')
		s[pos] = '0';
	else
		s[pos] = '1';
}

int main(){
	cin.sync_with_stdio(0);
    cin.tie(0);
	freopen("input.txt", "r", stdin);
	cin >> N;
	for(int i = 0; i < N; i++){
		cin >> Ai;
		st+=Ai;
	}
	cout << st << endl;
	string start = "";
	for(int i = 0; i < N; i++)
		start += "0";
	vector<int> moves;
	int count = 0;
	for(int i = 0; i < N; i++){
		if(st == start){
			cout << count << endl;
			for(int j : moves)
				cout << j << endl;
			return 0;
		}
		if(st[i+1] == '1' && st[i-1] == '1' && st[i] == '1'){
			setP(st, i);
			setP(st, i+1);
			setP(st, i-1);
			moves.pb(i+1);
			count++;
		}
	}
	bool check = true;
	while(check && moves.size() < 10000){
		for(int i = 0; i < N; i++){
			if(st == start){
				check = false;
				break;
			}
			if(i == 0){
				setP(st, i);
				setP(st, i+1);
			} else if(i == N-1){
				setP(st, i);
				setP(st, i-1);
			} else {
				setP(st, i);
				setP(st, i+1);
				setP(st, i-1);
			}
			count++;
			moves.pb(i+1);
		}
	}
	cout << count << endl;
	for(int j : moves)
		cout << j << endl;
	return 0;
}