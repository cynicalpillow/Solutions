#include <bits/stdc++.h>
using namespace std;
#define pii pair<int,int>
#define mp make_pair
#define pb push_back

int INFINITE = 0x3f3f3f3f;
string str;
int P[200010];
int start[200010];
int finish[200010];

string preprocess(string s){
	//to avoid bounds checking we use ^ and ^
	int n = s.length();
	if(n == 0)return "$^";
	string ret = "$";
	for(int i = 0; i < n; i++)
		ret+="#"+s.substr(i, 1);
	ret += "#^";
	return ret;
}

long long manachers(string s){
	string T = preprocess(s); //Preprocess to account for odd/even palindromes
	int n = T.length();
	int C = 0, R = 0; //Center index and right index
	for(int i = 1; i < n-1; i++){
		int i_mirror = 2*C-i; //Mirror from around C
		P[i] = (R > i) ? min(R-i, P[i_mirror]):0; 
		while(T[i+1+P[i]] == T[i-1-P[i]]) //Find max around i
			P[i]++;
		//Make new center at i, and expand it past the original right edge to the new right edge
		if(i + P[i] > R){
			C = i;
			R = i + P[i];
		}
	}
	for(int i = 1; i < n-1; i++){
		if(P[i] > 0){
			start[(i-1-P[i])/2]++;
			finish[(i-1-P[i])/2]--;
			start[(i-1-P[i])/2+P[i]]--;
			finish[(i-1-P[i])/2+P[i]]++;
		}
	}
	int n1 = s.length();
	for(int i = 1; i < n1; i++)
		start[i] += start[i-1];
	for(int i = n1-1; i >= 0; i--)
		finish[i] += finish[i+1];
	long long ret = 0;
	for(int i = 0; i < n1; i++){
		cout << start[i] << " " << finish[i] << endl;
		ret += start[i+1] * finish[i];
	}
	return ret;
}

int main(){
	cin.sync_with_stdio(0);
    cin.tie(0);
	freopen("input.txt", "r", stdin);
	memset(start, 0, sizeof(start));
	memset(finish, 0, sizeof(finish));
	cin >> str;
	cout << manachers(str);
	return 0;
}