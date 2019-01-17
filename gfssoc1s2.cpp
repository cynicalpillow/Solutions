#include <bits/stdc++.h>
using namespace std;
#define pii pair<int,int>
#define mp make_pair
#define pb push_back

int INFINITE = 0x3f3f3f3f;
map<string, string> ftou;
int N;

int main(){
	//cin.sync_with_stdio(0);
    //cin.tie(0);
	freopen("input.txt", "r", stdin);
	scanf("%i", &N);
	for(int i = 0; i < N; i++){
		string x, y;
		cin >> x >> y;
		ftou[y] = x;
	}
	string s;
	getline(cin, s);
	getline(cin, s);
	int ll = s.length();
	string result = "";
	string curr = "";
	for(int i = 0; i < ll; i++){
		if(s[i] == ' '){
			if(ftou[curr] != ""){
				result += ftou[curr];
			} else {
				result += curr;
			}
			result += ' ';
			curr = "";
		} else if(s[i] == '.'){
			if(ftou[curr] != ""){
				result += ftou[curr];
			} else {
				result += curr;
			}
			result += '.';
			curr = "";
		} else {
			curr += s[i];
		}
	}
	cout << result;
	return 0;
}