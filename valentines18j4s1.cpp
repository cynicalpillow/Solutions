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
int N, M;

bool check(string s){
	int n = s.length();
	for(int i = 0; i < n/2; i++){
		if(s[i] != s[n-1-i])return false;
	}
	return true;
}

int main(){
	cin.sync_with_stdio(0);
    cin.tie(0);
	freopen("input.txt", "r", stdin);
	cin >> N >> M;
	int maxDist = 0;
	int num;
	for(int i = (N+M)/2; i >= N; i--){
		if(check(to_string(i))){
			if(maxDist < min(abs(i-N), abs(i-M))){
				maxDist = min(abs(i-N), abs(i-M));
				num = i;
				break;
			}
		}
	}
	for(int i = (N+M)/2; i <= M; i++){
		if(check(to_string(i))){
			if(maxDist < min(abs(i-N), abs(i-M))){
				maxDist = min(abs(i-N), abs(i-M));
				num = i;
				break;
			} else if(maxDist == min(abs(i-N), abs(i-M))){
				num = i;
				break;
			}
		}
	}
	cout << num;
	return 0;
}