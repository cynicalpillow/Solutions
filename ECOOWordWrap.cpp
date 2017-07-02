#include <bits/stdc++.h>
using namespace std;
#define pii pair<int,int>
#define mp make_pair
#define pb push_back

int INFINITE = 0x3f3f3f3f;
int W;
string line;

void solve(){
	int prev = 0;
	int count = 0;
	vector<string> words;
	vector<string> print;
	for(int i = 0; i < line.length(); i++){
		if(isspace(line.at(i))){
			words.pb(line.substr(prev, i-prev));
			prev = i+1;
		}
	}
	words.pb(line.substr(prev, line.length() - prev));
	for(string s : words){
		//cout << count << endl;
		if(count + s.length() > W){
			for(int i = 0; i < print.size(); i++){
				if(i != print.size()-1)cout << print[i] << " ";
				else cout << print[i] << endl;
			}
			print.clear();
			if(s.length() > W){
				while(s.length() > W){
					cout << s.substr(0, W) << endl;
					s = s.substr(W);
				}
				count = s.length();
				print.pb(s);
			} else {
				count = s.length();
				print.pb(s);
			}
		} else {
			count += s.length();
			print.pb(s);
		}
		count++;
	}
	for(int i = 0; i < print.size(); i++){
		if(i != print.size()-1)cout << print[i] << " ";
		else cout << print[i] << endl;
	}
	cout << "=====" << endl;
}

int main(){
	//freopen("input.txt", "r", stdin);
	for(int ii = 0; ii < 10; ii++){
		scanf("%i", &W);
		cin.ignore();
		getline(cin, line);
		solve();
	}
	return 0;
}