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
string x;

int main(){
	cin.sync_with_stdio(0);
    cin.tie(0);
	freopen("input.txt", "r", stdin);
	cin >> N;
	for(int i = 0; i < N; i++){
		cin >> x;
		if(x.size() != 10){
			cout << "not a phone number" << endl;
		} else if(x.substr(0, 3) != "416" && x.substr(0, 3) != "647"){
			cout << "not a phone number" << endl;
		} else {
			cout << "(" << x.substr(0, 3) << ")-" << x.substr(3, 3) << "-" << x.substr(6, 4) << endl;
		}
	}
	return 0;
}