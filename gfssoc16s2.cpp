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
long long A, B;
vector<string> nums;

void dfs(string s, int length, int x){
	if(length == x)nums.pb(s);
	else {
		dfs(s+"A", length+1, x);
		dfs(s+"C", length+1, x);
		dfs(s+"E", length+1, x);
	}
}

long long convert(string s){
	int n = s.length();
	long long total = 0;
	int count = 0;
	for(int i = n-1; i >= 0; i--){
		if(s[i] == 'A'){
			total += 10*pow(16, count);
		} else if(s[i] == 'C'){
			total += 12*pow(16, count);
		} else {
			total += 14*pow(16, count);
		}
		count++;
	}
	return total;
}

int main(){
	cin.sync_with_stdio(0);
    cin.tie(0);
	freopen("input.txt", "r", stdin);
	cin >> A >> B;
	for(int i = 1; i <= 9; i++)dfs("", 0, i);
	long long ans = 0;
	for(string s : nums){
		long long y = convert(s);
		if(y >= A && y <= B) ans++;
		else if(y > B)break;
	}
	cout << ans << endl;
	return 0;
}