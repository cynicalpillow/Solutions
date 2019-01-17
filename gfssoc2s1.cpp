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
int S, M, L;
double A, B, C;

int main(){
	cin.sync_with_stdio(0);
    cin.tie(0);
	freopen("input.txt", "r", stdin);
	cin >> S >> M >> L >> A >> B >> C;
	int bought = 0;
	double total = 0;
	while(true){
		if(S == 0 && M == 0 && L == 0)break;
		if(S > 0){
			total += A;
			S--;
			bought++;
		} else if(M > 0){
			total += B;
			M--;
			bought++;
		} else if(L > 0){
			total += C;
			L--;
			bought++;
		}
		if(bought % 3 == 0){
			if(L > 0){
				L--;
			} else if(M > 0){
				M--;
			} else if(L > 0){
				S--;
			}
		}
	}
	printf("%.2lf", total);
	return 0;
}