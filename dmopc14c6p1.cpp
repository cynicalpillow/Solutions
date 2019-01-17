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
string lst[15];
int N;

int main(){
	cin.sync_with_stdio(0);
    cin.tie(0);
	freopen("input.txt", "r", stdin);
	cin >> N;
	string fucku;
	getline(cin, fucku);
	for(int i = 0; i < N; i++){
		getline(cin, lst[i]);
	}
	for(int i = 1; i < N-1; i++){
		if(lst[i] == "Bessarion"){
			if((lst[i-1] == "Leslie" && lst[i+1] == "Bayview") || (lst[i-1] == "Bayview" && lst[i+1] == "Leslie")){
				cout << "Y";
				return 0;
			}
		}
	}
	if(lst[0] == "Bessarion" && ((lst[1] == "Leslie" && lst[N-1] == "Bayview") || (lst[N-1] == "Leslie" && lst[1] == "Bayview")))cout << "Y";
	else if(lst[N-1] == "Bessarion" && ((lst[0] == "Leslie" && lst[N-2] == "Bayview") || (lst[N-2] == "Leslie" && lst[0] == "Bayview")))cout << "Y";
	else cout << "N";
	return 0;
}