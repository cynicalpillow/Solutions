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
vector<string> last_syllables;

char easytolower(char in){
  if(in<='Z' && in>='A')
    return in-('Z'-'z');
  return in;
}

int main(){
	cin.sync_with_stdio(0);
    cin.tie(0);
	freopen("input.txt", "r", stdin);
	cin >> N;
	int count = 0;
	string z;
	getline(cin, z);
	for(int i = 0; i < 4*N; i++){
		count++;
		string s;
		getline(cin, s);
		int n = s.length()-1;
		for(int j = 0; j <= n; j++){
			s[j] = easytolower(s[j]);
		}
		string end_part = "";
		for(int j = n; j >= 0; j--){
			if(s[j] == ' ')break;
			end_part = s[j] + end_part;
			if(s[j] == 'a' || s[j] == 'e' || s[j] == 'i' || s[j] == 'o' || s[j] == 'u')break;
		}
		last_syllables.pb(end_part);
		if(count == 4){
			count %= 4;
			if(last_syllables[0] == last_syllables[1] 
				&& last_syllables[1] == last_syllables[2] 
				&& last_syllables[2] == last_syllables[3]){
				cout << "perfect\n";
			} else if(last_syllables[0] == last_syllables[1]
				&& last_syllables[2] == last_syllables[3]){
				cout << "even\n";
			} else if(last_syllables[0] == last_syllables[2]
				&& last_syllables[1] == last_syllables[3]){
				cout << "cross\n";
			} else if(last_syllables[0] == last_syllables[3]
				&& last_syllables[1] == last_syllables[2]){
				cout << "shell\n";
			} else {
				cout << "free\n";
			}
			last_syllables.clear();
		}
	}
	return 0;
}