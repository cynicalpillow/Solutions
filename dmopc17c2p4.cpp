#include <bits/stdc++.h>
using namespace std;
#define pii pair<int,int>
#define mp make_pair
#define pb push_back

int INFINITE = 0x3f3f3f3f;
string st;

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

string manachers(string s){
	string T = preprocess(s); //Preprocess to account for odd/even palindromes
	int n = T.length();
	int *P = new int[n]; //Values for each index that represents the center of a palindrome
	int C = 0, R = 0; //Center index and right index
	for(int i = 1; i < n-1; i++){
		int i_mirror = 2*C-i; //Mirror from around C
		P[i] = (R > i) ? min(R-i, P[i_mirror]):0; 
		while(T[i+1+P[i]] == T[i-1-P[i]]){ //Find max around i
			cout << T.substr(i-1-P[i], i+1+P[i]) << endl;
			P[i]++;
		}
		//Make new center at i, and expand it past the original right edge to the new right edge
		if(i + P[i] > R){
			C = i;
			R = i + P[i];
		}
	}
	int max_len = 0;
	int center_idx = 0;
	for(int i = 1; i < n-1; i++){
		if(P[i] > max_len){
			max_len = P[i];
			center_idx = i;
		}
	}
	return s.substr((center_idx - 1 - max_len)/2, max_len);
}

int main(){
	//cin.sync_with_stdio(0);
    //cin.tie(0);
	freopen("input.txt", "r", stdin);
	cin >> st;
	manachers(st);
	return 0;
}