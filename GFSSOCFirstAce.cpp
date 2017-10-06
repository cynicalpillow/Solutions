#include <bits/stdc++.h>
using namespace std;
#define pii pair<int,int>
#define mp make_pair
#define pb push_back

struct Student{
	int V;
	int S;
	int T;
};
int INFINITE = 0x3f3f3f3f;
int N;
Student students[10005];
int memo[10005][10005];

int dp(){

}

int main(){
	freopen("input.txt", "r", stdin);
	scanf("%i", &N);
	for(int i = 0; i < N; i++){
		int v, s, t;
		scanf("%i%i%i", &v, &s, &t);
		students[i].V = v;
		students[i].S = s;
		students[i].T = t;
	}
	dp();
	return 0;
}