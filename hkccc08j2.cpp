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
string inp;

int main(){
	cin.sync_with_stdio(0);
    cin.tie(0);
	freopen("input.txt", "r", stdin);
	cin >> N;
	for(int i = 0; i < N; i++){
		cin >> inp;
		int zz = inp.size();
		while(true){
			int total = 0;
			for(int j = 0; j < zz; j++){
				total += (int)inp[j]-'0';
			}
			if(total < 10){
				cout << total << endl;
				break;
			}
			inp = to_string(total);
			zz = inp.size();
		}
	}
	return 0;
}