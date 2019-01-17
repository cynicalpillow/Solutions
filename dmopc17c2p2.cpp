#include <bits/stdc++.h>
using namespace std;
#define pii pair<int,int>
#define mp make_pair
#define pb push_back

int INFINITE = 0x3f3f3f3f;
string s;
stack<char> st;

int main(){
	//cin.sync_with_stdio(0);
    //cin.tie(0);
	freopen("input.txt", "r", stdin);
	cin >> s;
	int size = s.length();
	for(int i = 0; i < size; i++){
		if(s[i] == '(')st.push('(');
		else if(s[i] == ')'){
			if(st.size() > 0 && st.top() == '(')st.pop();
			else st.push(')');
		}
	}
	if(st.size() > 2)cout << "NO" << endl;
	else cout << "YES" << endl;
	return 0;
}